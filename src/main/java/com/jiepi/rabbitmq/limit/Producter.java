package com.jiepi.rabbitmq.limit;

import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producter {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectRabbitMQ.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.#";
        String msg = "qos message";
        for (int i = 0; i < 5; i++) {

            channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
        }


    }
}
