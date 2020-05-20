package sim.com;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/20
 */
public class AgentTargetSample {

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
}
