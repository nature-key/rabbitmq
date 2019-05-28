package com.jiepi.rabbitmq.Exchange.direct;


import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ProductDirectExchange {

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectRabbitMQ.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName="test_direct_exchange";
        String routingKey="key.direct";
        for (int i = 0; i < 100; i++) {

            String  msg="hello word jiepi";

            channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
