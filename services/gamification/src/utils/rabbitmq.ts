// rabbitmq.ts
import amqp from 'amqplib';
import { Observable, from } from 'rxjs';

let amqpConnection: amqp.Connection | null = null;
let amqpChannel: amqp.Channel | null = null;

const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));

export async function initRabbitMQ() {
    let retries = 5;

    while (retries > 0) {
        try {
            const host = process.env.RABBITMQ_HOST || 'localhost';

            amqpConnection = await amqp.connect('amqp://' + host);
            amqpChannel = await amqpConnection.createChannel();
            console.log('RabbitMQ connected and channel created.');

            await amqpChannel.assertQueue('awardUserQueue', {
                durable: true,
            });

            return;
        } catch (error) {
            console.error('Error initializing RabbitMQ:', error);
            retries--;
            await sleep(3000);
        }
    }

    throw new Error('Failed to connect to RabbitMQ');
}

export function sendMessageToQueue(queue: string, message: string): Observable<void> {
    return new Observable<void>((subscriber) => {
        const channel = getRabbitMQChannel();
        if (!channel) {
            subscriber.error('RabbitMQ channel is not available');
            return;
        }

        channel.assertQueue(queue, { durable: true });

        try {
            channel.sendToQueue(queue, Buffer.from(message), { persistent: true });
            console.log(' [x] Sent %s', message);
            subscriber.next();
            subscriber.complete();
        } catch (error) {
            subscriber.error(error);
        }
    });
}

export function getRabbitMQChannel(): amqp.Channel | null {
    return amqpChannel;
}

export function getRabbitMQConnection(): amqp.Connection | null {
    return amqpConnection;
}