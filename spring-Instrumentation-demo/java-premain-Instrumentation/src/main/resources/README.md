
### 设计模式与spring容器集成 - 普通模式 

### 详情

> 打包，记住位置
[](pg.png)


```

 
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println(String.format("Process by ClassFileTransformer,target class = %s",className));

        return classfileBuffer;
    }

     /**
         * JVM在指定代理的方式下启动，此时Instrumentation实例会传递到代理类的premain方法
         * premain方式依赖独立的javaagent，也就是单独建立一个项目编写好代码之后打成jar包供另一个使用程序通过代理形式引入.
         * premain方式回调到ClassFileTransformer中的类是虚拟机加载的所有类，这个是由于代理加载的顺序比较靠前决定的，
         * 在开发者逻辑看来就是：所有类首次加载并且进入程序main()方法之前，premain方法会被激活，
         * 然后所有被加载的类都会执行ClassFileTransformer列表中的回调
         *
         * @param agentArgs
         * @param inst
         */
        public static void premain(String agentArgs, Instrumentation inst){
    
            inst.addTransformer(new SimClassFileTransformer());
    
        }

```

> permain打包注意事项
```
   <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <archive>
                        <manifestEntries>
                            <Premain-Class>com.sim.PremainAgent</Premain-Class>
                            <!-- <Agent-Class>com.sim.JFCAgent</Agent-Class>-->
                            <Can-Redefine-Classes>true</Can-Redefine-Classes>
                            <Can-Retransform-Classes>true</Can-Retransform-Classes>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
        </plugins>

    </build>

```
