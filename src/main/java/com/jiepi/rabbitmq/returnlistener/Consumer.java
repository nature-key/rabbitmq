package com.jiepi.rabbitmq.returnlistener;

import com.jiepi.rabbitmq.util.ConstomerUtil;

public class Consumer {

    public static void main(String[] args) {
        String exchangeName = "test_return_change";
        String queueName = "test_return_queue";
        String exchangeType = "topic";
        String routingKey = "topic.#";
        ConstomerUtil.consumer(exchangeName, exchangeType, queueName, routingKey);
    }
}
