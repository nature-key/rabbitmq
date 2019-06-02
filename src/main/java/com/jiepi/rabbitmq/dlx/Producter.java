package com.jiepi.rabbitmq.dlx;

import com.rabbitmq.client.*;

public class Producter {
    /**
     * 死信队列
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("127.0.0.1");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.save";

        String msg = " hello rabbitmq wx";
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .build();
        channel.basicPublish(exchangeName,routingKey,properties,msg.getBytes());


    }
}
