

### 策略+工厂

> 解决大量if 判断不同code,进行创建不同对象
> 采用工厂+ stream Api 处理

```aidl
  //测试调用
  public static void main(String[] args) {

        StrategyFactory strategyFactory = StrategyFactory.getInstance();

        Pay pay = strategyFactory.get(PayConstant.ali_pay.name());

        System.out.println(pay.call());
    }

```