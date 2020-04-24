package com.sim.exchange.topic;

import com.rabbitmq.client.*;
import com.sim.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/23
 */
public class EmailConsumer {

    private static final String QUEUE_NAME = "topic-email-sim";

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "topic-sim";

    /**
     * 使用 sim.* 匹配到sim.sms路由，这样就不需要设置多次
     */
    private static  final String ROUTING_KEY = "sim.*";


    public static void main(String[] args) throws IOException, TimeoutException {

        topic();
    }

    /**
     * 点对点
    * @throws IOException
    * @throws TimeoutException
    */
    public static void topic() throws IOException, TimeoutException {
        System.out.println("我是消费端email");
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        //3: 将队列绑定到交换机, 并且设置路由规则
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, ROUTING_KEY);


        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException
            {
                String msg = new String(body,"UTF-8");
                System.out.println("消费消息：" + msg);

            }
        };

        //4: 监听消息
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);

      /*  channel.close();
        connection.close();*/
    }
}
