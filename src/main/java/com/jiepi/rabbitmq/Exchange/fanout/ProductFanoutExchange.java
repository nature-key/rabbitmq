package com.jiepi.rabbitmq.Exchange.fanout;

import com.jiepi.rabbitmq.util.ProductUtil;

public class ProductFanoutExchange {

    public static void main(String[] args) {
        String exchangeName = "test_fanout_exchange";
        ProductUtil.product(exchangeName,"","","");

    }
}
