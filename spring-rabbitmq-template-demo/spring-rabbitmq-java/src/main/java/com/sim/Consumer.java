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

    public static void main(String[] args) throws IOException, TimeoutException {

        //1:创建连接
        Connection connection = RabbitMQConnection.getConnection();

        //2:创建通道
        Channel channel = connection.createChannel();

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
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        //3: 监听消息
        /**
         *    *** 重点 第二个参数是消息签收机制，true自动签收，等于说mq服务端只要发送成功，那么它就任务OK了 会将发送的消息从队列删除
         *    false,手动签收  channel.basicAck(envelope.getDeliveryTag(),false); 设置签收
         */
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

        channel.close();
        connection.close();
    }
}
