package com.sim.fanout;

import com.sim.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/4/29
 */
@RestController
public class FanoutProduct {

    @Autowired
    private AmqpTemplate template;

    @GetMapping("/send/fan-out/msg")
    public String sendMsg(String msg) {

        // 参数1 交换机名称 、参数2路由key  参数3 消息
        template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME_FANOUT,"",msg);

        return "success";
    }

}
