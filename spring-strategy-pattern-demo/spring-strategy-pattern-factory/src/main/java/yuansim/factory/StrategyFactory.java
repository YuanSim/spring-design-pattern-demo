package yuansim.factory;

import yuansim.constant.PayConstant;
import yuansim.payment.Pay;
import yuansim.payment.impl.AliPay;
import yuansim.payment.impl.UnionPay;
import yuansim.payment.impl.WxPay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
public class StrategyFactory {

    private Map<String, Pay> map;

    public StrategyFactory() {

        List<Pay> pays = new ArrayList<>();
        pays.add(new AliPay());
        pays.add(new WxPay());
        pays.add(new UnionPay());

        map = pays.stream().collect(Collectors.toMap(Pay::getType,strategy-> strategy));
    }

    public static StrategyFactory getInstance() {
        return PayStrategyFactory.instance;
    }

    public Pay get(String type) {

        return map.get(type);
    }

    public static class PayStrategyFactory{

        public static StrategyFactory instance = new StrategyFactory();

    }


}
