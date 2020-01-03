package yuansim.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
@RestController
public class DemoController {



    /**
     *  LocalDateTime 数据自定义绑定测试
     * @param date
     * @return
     */
    @GetMapping(value = "/bind/{date}")
    public LocalDateTime getLocalDateTime(@PathVariable(value = "date") LocalDateTime date) {

        System.out.println("测试");
        return date;
    }
}
