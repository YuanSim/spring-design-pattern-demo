package yuansim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yuansim.PayType;

import java.time.LocalDateTime;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
@Controller
public class DemoController {

    /**
     * 数据自定义绑定测试-- 枚举
     */
    @GetMapping(value = "/bind/enum/{str}")
    public int getEnum(@PathVariable(value = "str") PayType pay) {

        System.out.println("测试");

        return pay.getCode();
    }
    /**
     *  LocalDateTime 数据自定义绑定测试
     * @param date
     * @return
     */
    @GetMapping(value = "/bind/{date}")
    @ResponseBody
    public LocalDateTime getLocalDateTime(@PathVariable(value = "date") LocalDateTime date) {

        System.out.println("测试");
        return date;
    }
}
