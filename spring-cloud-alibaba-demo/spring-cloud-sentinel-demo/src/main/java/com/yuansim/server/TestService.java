package com.yuansim.server;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    /**
     * SentinelResouce 注解用来标识资源是否被限流、降级。 value表示资源名称
     * 更多注解内容参考 @link https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81
     *
     * 若不配置 blockHandler、fallback 等函数，则被流控降级时方法会直接抛出对应的 BlockException；若方法未定义 throws BlockException 则会被 JVM 包装一层 UndeclaredThrowableException。
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello", blockHandler = "sayHelloBlockHandler")
    public String sayHello(String name){

        return "Hello, " + name;
    }

    public String sayHelloBlockHandler(BlockException blockException){

        return "simon custom Block Exception";
    }

}
