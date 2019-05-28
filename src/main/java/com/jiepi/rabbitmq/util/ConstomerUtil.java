package com.jiepi.rabbitmq.util;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConstomerUtil {


    public static void consumer(String exchangeName, String exchangeType, String queueName, String routingKey) {
        try {
            Connection connection = ConnectRabbitMQ.getConnection();
            Channel channel = connection.createChannel();
            //1.声明交换机
            channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
            // 2 声明队列
            channel.queueDeclare(queueName, false, false, false, null);
            // 3 建立交换机和队列的绑定关系:
            channel.queueBind(queueName, exchangeName, routingKey);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String message = new String(body, "UTF-8");
                    System.out.println(message);
                }
            };

            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
