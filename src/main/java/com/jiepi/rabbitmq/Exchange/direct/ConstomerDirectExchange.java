package com.jiepi.rabbitmq.Exchange.direct;

import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConstomerDirectExchange {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectRabbitMQ.getConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_direct_exchange";
        String exchangeType = "direct";
        String queueName = "test_direct_queue";
        String routingKey = "key.direct";

        channel.exchangeDeclare(exchangeName, exchangeType,true, false, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName,exchangeName,routingKey);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body,"UTF-8");
                System.out.println(message);
            }
        };

        channel.basicConsume(queueName,true,consumer);
    }
}
