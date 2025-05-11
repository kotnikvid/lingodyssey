import express from "express";
import * as mongoose from "mongoose";
import swaggerUi from "swagger-ui-express";
import swaggerDocument from "./docs/swagger.json";
import cors from "cors";
import { of, from } from "rxjs";
import { tap, catchError, switchMap } from "rxjs/operators";
import { startConsumer } from "./utils/consumer";

import awardRoutes from "./routes/awardRoutes";
import streakRoutes from "./routes/streakRoutes";
import logger from "./utils/logger";

const app = express();
const port = process.env.PORT || 8085;

app.use(express.json());
app.use(cors());

if (process.env.NODE_ENV !== "production") {
   console.log("Development mode");
   require("dotenv").config();
}

const connectToMongo = () => {
   return from(mongoose.connect(`${process.env.MONGO_URL}/${process.env.DB_NAME}?authSource=admin`)
   ).pipe(
      catchError((error) => {
         console.error("Failed to connect to the database:", error);
         process.exit(1);
      })
   )
};

const { initRabbitMQ } = require("./utils/rabbitmq");

const initializeApp = () => {
   logger.info("Initializing app...");

   return connectToMongo().pipe(
      switchMap((dbConnection) => {
         if (dbConnection) {
            console.log('Connected to MongoDB');
            logger.info('Connected to MongoDB');
            return initRabbitMQ();
         }
         return of(null); // Skip RabbitMQ connection if DB connection fails
      }),
      switchMap((rabbitMQChannel) => {
         if (rabbitMQChannel) {
            console.log('RabbitMQ channel is ready');
            logger.info('RabbitMQ channel is ready');
         }
         return of(null); // Proceed after both DB and RabbitMQ connections are ready
      }),
      tap(() => {
         app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerDocument));
         app.use("/awards", awardRoutes);
         app.use("/streaks", streakRoutes);
      })
   );
};

initializeApp().subscribe({
   next: () => {
      startConsumer(); // Start the RabbitMQ consumer
      console.log("RabbitMQ consumer started");
      logger.info("RabbitMQ consumer started");

      app.listen(port, () => {
         console.log(`Server is running on port ${port}`);
         logger.info(`Server is running on port ${port}`);
      });
   },
   error: (err) => {
      console.error('Error initializing app:', err);
      process.exit(1);
   },
});