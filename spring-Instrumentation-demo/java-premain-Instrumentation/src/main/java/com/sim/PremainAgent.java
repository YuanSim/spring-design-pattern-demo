package com.sim;

import java.lang.instrument.Instrumentation;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/20
 */
public class PremainAgent {

    /**
     * JVM在指定代理的方式下启动，此时Instrumentation实例会传递到代理类的premain方法
     * premain方式依赖独立的javaagent，也就是单独建立一个项目编写好代码之后打成jar包供另一个使用程序通过代理形式引入.
     * premain方式回调到ClassFileTransformer中的类是虚拟机加载的所有类，这个是由于代理加载的顺序比较靠前决定的，
     * 在开发者逻辑看来就是：所有类首次加载并且进入程序main()方法之前，premain方法会被激活，
     * 然后所有被加载的类都会执行ClassFileTransformer列表中的回调
     *
     * @param agentArgs agentArgs是premain函数得到的程序参数，通过– javaagent命令行参数传入。
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){

        inst.addTransformer(new SimClassFileTransformer());

    }
}
