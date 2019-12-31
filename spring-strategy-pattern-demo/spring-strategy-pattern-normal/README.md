
### 设计模式与spring容器集成 - 普通模式 

```aidl
    @PostMapping(value = "/strategy/normal/pay")
    public String pay(@RequestParam String beanId) {

         
         Pay pay = SpringUtils.getBean(beanId, Pay.class);

         String call = pay.call();

         return call;

    }

```