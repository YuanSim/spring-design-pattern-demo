package yuansim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
@RestController
public class DemoController {

    @PostMapping(value = "/rest/ful")
    public List<Integer> pay() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        return list;

    }

    @PostMapping(value = "/rest/ful/fail")
    public String fail() {

        return "错误演示";

    }

}
