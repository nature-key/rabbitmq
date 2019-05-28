package com.jiepi.rabbitmq.Exchange.fanout;

import com.jiepi.rabbitmq.util.ConstomerUtil;

public class ConstomerFanoutExchange {

    public static void main(String[] args) {
        String exchangeName = "test_fanout_exchange";
        String exchangeType = "fanout";
        String queueName = "test_fanout_queue";
        String routingKey = "";	//不设置路由键
        ConstomerUtil.consumer(exchangeName,exchangeType,queueName,routingKey);
    }
}
