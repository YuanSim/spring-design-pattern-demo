package yuansim.controller;

import org.springframework.web.bind.annotation.*;
import yuansim.SpringUtils;
import yuansim.payment.Pay;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@RestController
public class DemoController {

    @PostMapping(value = "/strategy/normal/pay")
    public String pay(@RequestParam String beanId) {


         Pay pay = SpringUtils.getBean(beanId, Pay.class);

         String call = pay.call();

         return call;

    }



}
