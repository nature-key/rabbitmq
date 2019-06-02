package com.jiepi.rabbitmq.ack;

import com.jiepi.rabbitmq.util.ConnectRabbitMQ;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args)throws Exception {
        Connection connection = ConnectRabbitMQ.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test_ack_echange";
        String routingKey = "ack.save";
        String queueName="test_ack_queue";
        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("=======ack=========");
//                channel.basicAck(envelope.getDeliveryTag(),false);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ((Integer)properties.getHeaders().get("num")==0) {
                    channel.basicNack(envelope.getDeliveryTag(),false,true);
                }else {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
                System.out.println(body+"num:"+(Integer)properties.getHeaders().get("num"));
            }
        };

        channel.basicConsume(queueName,false,consumer);
    }
}
