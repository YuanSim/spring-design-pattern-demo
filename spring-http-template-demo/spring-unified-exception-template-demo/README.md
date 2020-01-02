


### 异常全局demo

> controller
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

> service
```aidl
 /**
     * 测试service 抛出异常 并查看 @ResponseBody的情况
     * @param id
     * @return
     */
    @GetMapping("/service/{id}")
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