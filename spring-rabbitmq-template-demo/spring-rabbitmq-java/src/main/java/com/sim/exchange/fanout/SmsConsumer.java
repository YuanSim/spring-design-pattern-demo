package com.sim.exchange.fanout;

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
public class SmsConsumer {

    private static final String QUEUE_NAME = "sms-sim";

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "fanout-sim";

    public static void main(String[] args) throws IOException, TimeoutException {

        fanout();
    }

    /**
     * 点对点
    * @throws IOException
    * @throws TimeoutException
    */
    public static void fanout() throws IOException, TimeoutException {
        System.out.println("我是消费端sms");
        //1:创建连接
        Connection connection = RabbitMQConnection.getConnection();

        //2:创建通道
        Channel channel = connection.createChannel();

        //3: 将队列绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");


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
