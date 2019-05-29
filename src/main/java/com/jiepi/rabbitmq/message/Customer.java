package com.jiepi.rabbitmq.message;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;

public class Customer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" Consumer have received '" + message + "'");
                Map<String, Object> headers = properties.getHeaders();
                System.out.println(headers.get("test1"));
                System.out.println(headers.get("test2"));
            }
        };
        channel.basicConsume(queueName, true, consumer);


    }
}
