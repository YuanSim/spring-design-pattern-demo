package com.sim.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sim.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/23
 *
 * 主体模式
 */
public class Producer {

    private static  final String SMS_ROUTING_KEY = "sim.sms";
    /**
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {

        topic();
    }

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "topic-sim";
    /**
     * 交换机 topic模式
     */
    public static void topic() throws IOException, TimeoutException{

        System.out.println("生产者启动...");
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 参数解释
         *  交换机名称
         *  交换机类型
         *  是否持久化 *** 这个比较坑，mq管理平台创建的交换机默认是持久化的，java默认创建的是非持久化 不指定的话 会保存
         *
         *  topic支持模糊匹配的 * 表示匹配一个词 #表示匹配多个
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true);



        String msg = "我是生产者通过交换机direct类型过来的" + SMS_ROUTING_KEY;
        channel.basicPublish(EXCHANGE_NAME, SMS_ROUTING_KEY,null,msg.getBytes());
        channel.close();
        connection.close();
    }



}
