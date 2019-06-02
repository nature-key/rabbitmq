package com.jiepi.rabbitmq.limit;

import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args)throws  Exception {
        Connection connection = ConnectRabbitMQ.getConnection();

        Channel channel = connection.createChannel();
        String exchangeName="test_qos_exchange";
        String queueName="test_qos_queue";
        String routingKey ="qos.#";
        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);
            //0 不限 1 每次处理1条   false 建立在consumer
        channel.basicQos(0,1,false);
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("=============   QOS    ===================");
                System.out.println(consumerTag);
                //手动确认机制
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume(queueName, false, consumer);
    }
}
