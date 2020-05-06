package com.sim.exchange.fanout;

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
 * 扇形交换机
 */
public class Producer {


    /**
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        fanout();
    }

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "fanout-sim";
    /**
     * 交换机 fanout模式
     */
    public static void fanout() throws IOException, TimeoutException, InterruptedException{

        System.out.println("生产者启动...");
        //1：创建链接
        Connection connection = RabbitMQConnection.getConnection();

        //2：创建通道
        Channel channel = connection.createChannel();

        /**
         * 参数解释
         *  交换机名称
         *  交换机类型
         *  是否持久化 *** 这个比较坑，mq管理平台创建的交换机默认是持久化的，java默认创建的是非持久化 不指定的话 会保存
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout",true);

        String msg = "我是生产者通过交换机fanout类型过来的";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        channel.close();
        connection.close();
    }



}
