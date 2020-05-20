package sim.com;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/20
 *  负责attach工作的程序
 */
public class AgentmainAttachMain {

    public static void main(String[] args) throws Exception {

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
}
