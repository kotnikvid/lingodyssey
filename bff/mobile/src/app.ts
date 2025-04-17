import express from "express";
import cors from "cors";

import flashcardRoutes from "./routes/flashcardRoutes";

const app = express();
const port = process.env.PORT || 8091;

app.use(express.json());
app.use(cors());

if (process.env.NODE_ENV !== "production") {
   console.log("Development mode");
   require("dotenv").config();
}

app.use("/mobile", flashcardRoutes);

app.listen(port, () => {
   console.log(`Server is running on port ${port}`);
});