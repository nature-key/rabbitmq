package com.jiepi.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producter {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("127.0.0.1");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";

        String msg = " hello rabbitmq wx";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("no ack------");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("ack------------");
            }
        });
    }
}
