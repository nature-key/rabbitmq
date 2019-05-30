package com.jiepi.rabbitmq.returnlistener;

import com.jiepi.rabbitmq.util.ProductUtil;

public class Producter {

    public static void main(String[] args) {
        String exchangeName="test_return_change";
        String routingKey1="topic'save";
        String routingKey2="topic,insert";
        String routingKey3="topic。update";
        ProductUtil.product(exchangeName,routingKey1,routingKey2,routingKey3);
    }
}
