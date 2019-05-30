package com.jiepi.rabbitmq.util;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

public class ProductUtil {

    public static void product(String exchangeName, String routingKey1, String routingKey2, String routingKey3) {


        try {
            Connection connection = ConnectRabbitMQ.getConnection();
            Channel channel = connection.createChannel();


            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange,
                                         String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("---------return listener----------");
                    System.err.println("replyCode: " + replyCode);
                    System.err.println("replyText: " + replyText);
                    System.err.println("exchange: " + exchange);
                    System.err.println("routingKey: " + routingKey);
                    System.err.println("properties: " + properties);
                    System.err.println("body: " + new String(body));
                }
            });


            String msg = "hello word jiepi";
            channel.basicPublish(exchangeName, routingKey1,true, null, msg.getBytes());
            channel.basicPublish(exchangeName, routingKey2, true,null, msg.getBytes());
            channel.basicPublish(exchangeName,routingKey3, true,null, msg.getBytes());
//            channel.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
