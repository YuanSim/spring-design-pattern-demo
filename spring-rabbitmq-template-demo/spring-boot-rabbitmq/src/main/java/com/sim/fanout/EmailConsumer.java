package com.sim.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/29
 */
@Component
@RabbitListener(queues = "sim-email-queue")
public class EmailConsumer {

    /**
     * 回调注解
     * @param msg
     */
    @RabbitHandler
    public void process(String msg) {
        System.out.println("EmailConsumer我是通过springboot发送的fanout消息=="+ msg);
    }

}
