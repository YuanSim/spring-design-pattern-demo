package com.yuansim.controller;

import com.yuansim.config.ProjectConfig;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {

    private final ProjectConfig config;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string){

        return "Hello Nacos Discovery " + string;
    }


    @GetMapping(value = "/order/{id}")
    public String getUser(@PathVariable String id){

        return "{orderId: "+ id +",orderCode: "+ System.currentTimeMillis()/1000 +",price: 512}";
    }

    @GetMapping(value = "/project")
    public String projectMessage(){

        return "服务信息【" +config.getServerName() +":"+ config.getServerPort() +"】;项目信息【隶属于-"+ config.getParent() +":创始人-"+ config.getFounder() +":创建于-"+ config.getCreatedOn() +"】";
    }
}
