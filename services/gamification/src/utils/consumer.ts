import { from } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { getRabbitMQChannel } from './rabbitmq';

import AwardModel from "../models/Award.model";

const QUEUE_NAME = 'awardUserQueue';



const startConsumer = () => {
    const channel = getRabbitMQChannel();

    if (!channel) {
        console.error('Channel is not initialized');
        return;
    }

    channel.consume(QUEUE_NAME, (msg) => {
        if (msg) {
            const messageContent = msg.content.toString();
            console.log('Message received:', messageContent);

            processAwardUserMessage(messageContent).subscribe({
                next: (v) => {
                    console.log('Message processed successfully - ' + v);
                    channel!.ack(msg);
                },
                error: (e) => {
                    console.error('Error processing message:', e);
                    channel!.nack(msg, false, true);
                }
            });
        }
    }, { noAck: false });
};

const processAwardUserMessage = (messageContent: string) => {
    return from(new Promise<string>((resolve, reject) => {
        console.log(`Processing message: ${messageContent}`);

        try {
            const { points, email } = JSON.parse(messageContent);

            if (!points || !email) {
                reject("Points and email must be provided");
                return;
            }

            const awardUser$ = from(AwardModel.find({ pointsNeeded: { $lte: points }, userEmails: { $ne: email } }).exec()).pipe(
                switchMap((eligibleAwards) => {
                    if (eligibleAwards.length === 0) {
                        resolve("No eligible awards found");
                        return [];
                    }

                    return from(
                        AwardModel.updateMany(
                            {
                                pointsNeeded: { $lte: points },
                                userEmails: { $ne: email }
                            },
                            {
                                $addToSet: { userEmails: email }
                            }
                        ).exec()
                    ).pipe(
                        switchMap(() => {
                            resolve(`User ${email} awarded successfully`);
                            return [];
                        })
                    );
                }),
                catchError((error) => {
                    console.error(error);
                    reject("Internal server error");
                    return [];
                })
            );

            awardUser$.subscribe();

        } catch (e) {
            reject('Invalid JSON format');
        }
    }));
};

export { startConsumer };