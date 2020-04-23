package com.sim.exchange.direct;

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
 *
 */
public class Producer {

    /**
     * 设置生产消息的路由key值，这个是direct模式中最重要的东西
     */
    private static  final String SMS_ROUTING_KEY = "sim.sms";
    /**
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {

        direct();
    }

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "direct-sim";
    /**
     * 交换机 fanout模式
     */
    public static void direct() throws IOException, TimeoutException{

        System.out.println("生产者启动...");
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 参数解释
         *  交换机名称
         *  交换机类型
         *  是否持久化 *** 这个比较坑，mq管理平台创建的交换机默认是持久化的，java默认创建的是非持久化 不指定的话 会保存
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);



        String msg = "我是生产者通过交换机direct类型过来的" + SMS_ROUTING_KEY;
        channel.basicPublish(EXCHANGE_NAME, SMS_ROUTING_KEY,null,msg.getBytes());
        channel.close();
        connection.close();
    }



}
