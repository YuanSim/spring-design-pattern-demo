package com.yuansim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string){

        return "Hello Nacos Discovery " + string;
    }


    @GetMapping(value = "/order/{id}")
    public String getUser(@PathVariable String id){

        return "{orderId: "+ id +",orderCode: "+ System.currentTimeMillis()/1000 +",price: 512}";
    }
}
