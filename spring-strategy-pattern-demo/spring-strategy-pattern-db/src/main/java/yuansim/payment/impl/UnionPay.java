package yuansim.payment.impl;

import org.springframework.stereotype.Component;
import yuansim.payment.Pay;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Component
public class UnionPay implements Pay {

    @Override
    public String call() {
        System.out.println("调用银联");
        return "union";
    }
}
