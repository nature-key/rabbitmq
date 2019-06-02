package com.jiepi.rabbitmq.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Customer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("127.0.0.1");

        Connection connection = connectionFactory.newConnection();


        Channel channel = connection.createChannel();


//        String exchangeName = "test_dlx_exchange";
//        String routingKey = "dlx.#";
//        String queueName = "test_dlx_queue";
//        channel.exchangeDeclare(exchangeName, "topic", true);
//
//        Map<String ,Object> agruments = new HashMap<>() ;
//        agruments.put("x-dead-letter-exchange", "dlx.exchange");
//        channel.queueDeclare(queueName, true, false, false, agruments);
//        channel.queueBind(queueName, exchangeName, routingKey);

        //要进行死信队列的声明:
        channel.exchangeDeclare("dlx.exchange", "topic", true, false, null);
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "#");

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };

        channel.basicConsume("dlx.queue", true, consumer);

    }
}
