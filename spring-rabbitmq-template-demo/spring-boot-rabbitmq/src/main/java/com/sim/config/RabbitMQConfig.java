package com.sim.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/29
 *
 *  扇形模式
 */
@Component
public class RabbitMQConfig {

    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME_FANOUT = "sim-exchange-fanout";

    /**
     * sms队列名称
     */
    public static final String SMS_QUEUE_NAME = "sim-sms-queue";

    /**
     * email队列名称
     */
    public static final String EMAIL_QUEUE_NAME = "sim-email-queue";


    @Bean
    public Queue smsQueue() {

        return new Queue(SMS_QUEUE_NAME);
    }

    @Bean
    public Queue emailQueue() {

        return new Queue(EMAIL_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME_FANOUT);
    }

    /**
     * 绑定队列到交换机
     */
    @Bean
    public Binding  smsBindingExchange(Queue smsQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

    /**
     * 绑定队列到交换机
     */
    @Bean
    public Binding  emailBindingExchange(Queue emailQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }
}
