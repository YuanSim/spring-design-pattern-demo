package com.yuansim.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="spring-cloud-order-demo",
        fallback = OrderApiFallback.class,
        configuration = FeignConfiguration.class)
public interface OrderApi {


    @GetMapping(value = "/order/{id}")
    String getUser(@PathVariable String id);
}


class FeignConfiguration{

    @Bean
    public OrderApiFallback orderApiFallback(){
        return new OrderApiFallback();
    }
}

@Component
class OrderApiFallback implements OrderApi{

    @Override
    public String getUser(String id) {
        return "fall back";
    }
}