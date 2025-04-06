import AwardModel from "../models/Award.model";
import { from, switchMap, Observable } from "rxjs";
import { getRabbitMQChannel } from "../utils/rabbitmq";

import logger from "../utils/logger";

export class AwardService {
    static getAwardById(id: string): Observable<any> {
        logger.info(`Fetching award with ID: ${id}`);
        return from(AwardModel.findById(id)).pipe(
            switchMap((award) => {
                if (award) {
                    logger.info(`Award found with ID: ${id}`);
                } else {
                    logger.warn(`No award found with ID: ${id}`);
                }
                return [award];
            })
        );
    }

    static getAwardsByUserEmail(email: string): Observable<any[]> {
        logger.info(`Fetching awards for user with email: ${email}`);
        return from(AwardModel.find({ userEmails: email }).exec()).pipe(
            switchMap((awards) => {
                if (awards.length > 0) {
                    logger.info(`Found ${awards.length} awards for user: ${email}`);
                } else {
                    logger.warn(`No awards found for user: ${email}`);
                }
                return [awards];
            })
        );
    }

    static createAward(dto: any): Observable<any> {
        logger.info(`Creating award with data: ${JSON.stringify(dto)}`);
        return from(new AwardModel(dto).save()).pipe(
            switchMap((award) => {
                logger.info(`Award created with ID: ${award._id}`);
                return [award];
            })
        );
    }

    static updateAward(id: string, dto: any): Observable<any> {
        logger.info(`Updating award with ID: ${id}`);
        return from(AwardModel.findById(id)).pipe(
            switchMap((award) => {
                if (!award) {
                    logger.warn(`No award found to update with ID: ${id}`);
                    return from([null]);
                }
                Object.assign(award, dto);
                logger.info(`Award with ID: ${id} updated`);
                return from(award.save());
            })
        );
    }

    static deleteAward(id: string): Observable<{ deletedCount: number }> {
        logger.info(`Deleting award with ID: ${id}`);
        return from(AwardModel.deleteOne({ _id: id })).pipe(
            switchMap((result) => {
                if (result.deletedCount > 0) {
                    logger.info(`Award with ID: ${id} deleted`);
                } else {
                    logger.warn(`No award found to delete with ID: ${id}`);
                }
                return [result];
            })
        );
    }

    static sendMessageToRabbitMQ(points: number, email: string): Observable<void> {
        return new Observable<void>((observer) => {
            const channel = getRabbitMQChannel();
            const message = JSON.stringify({ points, email });

            channel.assertQueue("awardUserQueue", { durable: true });
            channel.sendToQueue("awardUserQueue", Buffer.from(message), { persistent: true });

            logger.info(`Sent message for eligible awards to RabbitMQ for points: ${points}, email: ${email}`);

            observer.next();
            observer.complete();
            setTimeout(() => channel.close(), 500);
        });
    }
}