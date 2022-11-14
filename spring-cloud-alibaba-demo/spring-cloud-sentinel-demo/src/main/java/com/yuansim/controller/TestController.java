package com.yuansim.controller;

import com.yuansim.server.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/sentinel/{name}")
    public String sayHello(@PathVariable String name){

        String hello = testService.sayHello(name);

        return hello;
    }
}
