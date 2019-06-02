package com.jiepi.rabbitmq.ack;

import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

public class Producer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectRabbitMQ.getConnection();
        Channel channel = connection.createChannel();
        String exChange = "test_ack_echange";
        String routingName = "ack.save";
        String msg = "hello ack rabbitmq";

        Map<String, Object> headers = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            headers.put("num", i);
            Thread.sleep(20000);
            AMQP.BasicProperties ampq = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("utf-8")
                    .headers(headers)
                    .build();
            channel.basicPublish(exChange, routingName, true, ampq, msg.getBytes());
        }


    }
}
