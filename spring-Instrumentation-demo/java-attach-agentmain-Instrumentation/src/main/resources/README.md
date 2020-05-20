
### 1. 配置代理
```
 public static void agentmain(String agentArgs, Instrumentation inst){

        inst.addTransformer(new SimClassFileTransformer(),true);

        try {
            inst.retransformClasses(Class.forName("sim.com.AgentTargetSample"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

 <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <archive>
                        <manifestEntries>
                            <!--<Premain-Class>com.sim.PremainAgent</Premain-Class>-->
                             <Agent-Class>sim.com.AgentmainAgent</Agent-Class>
                            <Can-Redefine-Classes>true</Can-Redefine-Classes>
                            <Can-Retransform-Classes>true</Can-Retransform-Classes>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
        </plugins>

    </build>
```


### 2.配置被代理

```
   public void sayHello(String name) {
        System.out.println(String.format("%s 我是被代理程序 通过agent模式代理!", name));
    }

    public static void main(String[] args) throws InterruptedException {
        AgentTargetSample targetSample = new AgentTargetSample();
        for (; ; ) {
            Thread.sleep(1000);
            targetSample.sayHello(Thread.currentThread().getName());
        }
    }
```


### 3.配置操作
``` public static void main(String[] args) throws Exception {
   
           /**
            * 获取所有VM实例
            */
           List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
   
   
           vmds.stream().filter(x-> x.displayName().endsWith("AgentTargetSample")).forEach(x-> {
               try {
                   /**
                    * 目标VM
                    */
                   VirtualMachine virtualMachine = VirtualMachine.attach(x.id());
   
                   /**
                    * 目标VM加载Agent
                    * 参数1 代理Jar路径
                    * 参数2 命令参数
                    */
                   virtualMachine.loadAgent("/Users/mac/Documents/Gitee/spring-design-pattern-demo/spring-Instrumentation-demo/java-agentmain-Instrumentation/target/java-agentmain-Instrumentation-1.0-SNAPSHOT.jar","arg1");
   
                   virtualMachine.detach();
               } catch (Exception e) {
                   e.printStackTrace();
               }
   
           });
       }
```

### 4. 启动顺序
> 将代理程序VM打包 virtualMachine.loadAgent(位置)
> 启动被代理VM程序
> 启动操作VM程序

### 5. 执行结果，成功注入
[](resurce.png)



