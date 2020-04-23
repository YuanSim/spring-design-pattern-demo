package com.sim;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/23
 */
public class Consumer {

    private static final String QUEUE_NAME = "demo-java";

    /**
     * 测试多消费不同性能耗时
     */
    private static final int time = 1000;

    public static void main(String[] args) throws IOException, TimeoutException {

        ptp();
    }

    /**
     * 点对点
     * @throws IOException
     * @throws TimeoutException
     */
    public static void ptp() throws IOException, TimeoutException {
        System.out.println("我是消费端1号");
        //1:创建连接
        Connection connection = RabbitMQConnection.getConnection();

        //2:创建通道
        Channel channel = connection.createChannel();

        //5: 使用手动签收机制，设置为工作队列  公平队列
        /**
         * 设置为每次只消费1条消息，并且结合手动签收机制
         * 如果未签收那么不会在传递消息过来
         * 已达到能者多劳的效果
         */
        channel.basicQos(1);

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

                try {
                    Thread.sleep(time);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //4: 设置手动签收
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        //3: 监听消息
        /**
         *    *** 重点 第二个参数是消息签收机制，true自动签收，等于说mq服务端只要发送成功，那么它就任务OK了 会将发送的消息从队列删除
         *    false,手动签收  channel.basicAck(envelope.getDeliveryTag(),false); 设置签收
         */
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

      /*  channel.close();
        connection.close();*/
    }
}
