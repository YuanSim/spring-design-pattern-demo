package yuansim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yuansim.factory.StrategyFactory;
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

    @PostMapping(value = "/strategy/factory/pay")
    public String pay(@RequestParam String payType) {

        StrategyFactory strategyFactory = StrategyFactory.getInstance();

        Pay pay = strategyFactory.get(payType);

        String call = pay.call();

        System.out.println(call);

         return pay.call();

    }



}
