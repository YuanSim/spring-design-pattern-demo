package sim.com;

import java.lang.instrument.Instrumentation;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/20
 */
public class AgentmainAgent {

    /**
     * agentmain的使用方式和permain十分相似，包括编写MANIFEST.MF和生成代理Jar包。
     * 但是，它并不需要通过-javaagent命令行形式引入代理Jar，而是在运行时通过attach工具激活指定代理即可。
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst){

        inst.addTransformer(new SimClassFileTransformer(),true);

        try {
            inst.retransformClasses(Class.forName("sim.com.AgentTargetSample"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
