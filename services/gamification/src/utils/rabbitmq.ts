// rabbitmq.ts
import amqp from 'amqplib';
import { Observable, from } from 'rxjs';

let amqpConnection: amqp.Connection | null = null;
let amqpChannel: amqp.Channel | null = null;

export async function initRabbitMQ() {
    try {
        amqpConnection = await amqp.connect('amqp://localhost');
        amqpChannel = await amqpConnection.createChannel();
        console.log('RabbitMQ connected and channel created.');
    } catch (error) {
        console.error('Error initializing RabbitMQ:', error);
        throw error;
    }
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