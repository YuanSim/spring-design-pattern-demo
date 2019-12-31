
### 设计模式与spring容器集成 - 数据库模式


### 详情
[](./src/main/resources/readme.md)


```aidl
 @PostMapping(value = "/strategy/normal/pay")
    public BaseResource pay(@RequestParam String code) {

        if(StringUtils.isEmpty(code)) {

            throw new ViewParamException();
        }

        /**
         * 从数据库获取beanId
         */
        PaymentChannel paymentChannel = paymentChannelMapper.getPaymentChannel(code);

        if(null == paymentChannel) {

            throw new DataException();
        }

        String strategyBeanId = paymentChannel.getStrategyBeanId();
        if(StringUtils.isEmpty(strategyBeanId)) {

            throw new DataException("beanId不存在");
        }

        /**
         * 使用spring容器通过的工具从容器中获取bean
         */
        Pay pay = SpringUtils.getBean(strategyBeanId, Pay.class);

        String call = pay.call();

        return BaseResource.fromSuccess(call);

    }
```
