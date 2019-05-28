package com.jiepi.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class ProductUtil {

    public static void product(String exchangeName, String routingKey1, String routingKey2, String routingKey3) {


        try {
            Connection connection = ConnectRabbitMQ.getConnection();
            Channel channel = connection.createChannel();
            String msg = "hello word jiepi";
            channel.basicPublish(exchangeName, routingKey1, null, msg.getBytes());
            channel.basicPublish(exchangeName, routingKey2, null, msg.getBytes());
            channel.basicPublish(exchangeName, routingKey3, null, msg.getBytes());
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
