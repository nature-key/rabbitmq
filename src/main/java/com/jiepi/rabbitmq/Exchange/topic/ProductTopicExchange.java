package com.jiepi.rabbitmq.Exchange.topic;

import com.jiepi.rabbitmq.util.ProductUtil;

public class ProductTopicExchange {

    public static void main(String[] args) {
        String exchangeName = "test_topic_exchange";
        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete.abc";
        ProductUtil.product(exchangeName,routingKey1,routingKey2,routingKey3);
    }
}
