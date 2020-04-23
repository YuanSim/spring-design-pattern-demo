package com.sim.exchange.direct;

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

    private static final String QUEUE_NAME = "direct-sms-sim";

    /**
     * 交换机名称
     */
    private static final String EXCHANGE_NAME = "direct-sim";

    /**
     * 设置生产消息的路由key值，这个是direct模式中最重要的东西
     */
    private static  final String SMS_ROUTING_KEY = "sim.sms";

    public static void main(String[] args) throws IOException, TimeoutException {

        direct();
    }

    /**
     * 点对点
    * @throws IOException
    * @throws TimeoutException
    */
    public static void direct() throws IOException, TimeoutException {
        System.out.println("我是消费端sms");
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        //3: 将队列绑定到交换机, 并且设置路由规则
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, SMS_ROUTING_KEY);

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
