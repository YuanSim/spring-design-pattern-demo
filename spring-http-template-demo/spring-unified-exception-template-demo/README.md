


### 异常全局demo

### 非自定义异常
```aidl
 /**
     * 测试非自定义异常
     */
    @GetMapping("/demo/number/format/exception")
    public List<String> demo() {

        throw new NumberFormatException();
    }

```


### 自定义异常

> yuansim.controller
```aidl
  /**
     * 测试controller 抛出异常
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public List<String> getUserById(@PathVariable Long id){
        throw new BusinessException("1001", "根据ID查询用户异常");
    }
```

> yuansim.service
```aidl
 /**
     * 测试service 抛出异常 并查看 @ResponseBody的情况
     * @param id
     * @return
     */
    @GetMapping("/yuansim.service/{id}")
    @ResponseBody
    public List<String> findUsers(@PathVariable Long id){

        return demoService.demo();
    }

@Service
public class DemoService {

    public List<String> demo() {

        throw new BusinessException("1003", "因为处理异常");
    }
}

```
