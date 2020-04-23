package com.sim;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/23
 */
public class RabbitMQConnection {

    /**
     * 获取链接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //1.创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("118.31.123.232");

        connectionFactory.setPort(5672);

        connectionFactory.setUsername("sim");
        connectionFactory.setPassword("123456");

        connectionFactory.setVirtualHost("mq-sim");
        return connectionFactory.newConnection();
    }
}
