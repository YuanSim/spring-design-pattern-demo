package com.yuansim.controller;

import com.yuansim.remote.OrderApi;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final OrderApi orderApi;

    @GetMapping("/user/order/{id}")
    public String order(@PathVariable String id){

        return orderApi.getUser(id);
    }
}
