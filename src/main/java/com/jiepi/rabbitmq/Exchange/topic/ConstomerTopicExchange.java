package com.jiepi.rabbitmq.Exchange.topic;

import com.jiepi.rabbitmq.util.ConstomerUtil;

public class ConstomerTopicExchange {

    public static void main(String[] args) {

        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        //String routingKey = "user.*";
        String routingKey = "user.#";
        ConstomerUtil.consumer(exchangeName,exchangeType,queueName,routingKey);

    }
}
