package com.sim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import javax.sound.midi.Soundbank;
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

    public static final String QUEUE_NAME = "demo-java";

    /**
     * 点对点模式
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("生产者启动...");
        //1：创建链接
        Connection connection = RabbitMQConnection.getConnection();

        //2：创建通道
        Channel channel = connection.createChannel();

        for(int i = 0 ; i < 10 ; i ++){

            String msg = "我是生产者发送的第" + i + "消息";

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("生产者发送消息成功:" + msg);
        }

        channel.close();
        connection.close();
    }

}
