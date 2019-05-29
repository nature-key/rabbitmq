package com.jiepi.rabbitmq.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;

public class Procuder {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("test1","test1");
        headers.put("test2","test2");
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .deliveryMode(2)//是否持久化  2  否1
                .contentEncoding("UTF-8")//编码
                .expiration("10000")//过期时间
                .headers(headers)
                .build();
        for (int i = 0; i < 5; i++) {
            String msg = "hello word";
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }


        channel.close();
        connection.close();

    }
}
