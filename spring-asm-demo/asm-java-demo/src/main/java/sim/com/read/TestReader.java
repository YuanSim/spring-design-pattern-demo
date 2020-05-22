package sim.com.read;


import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/22
 */
public class TestReader {

    public static void main(String[] args) throws IOException {

        /**
         * 1 读取目标类
         * 注意事项: 采用org.objectweb.asm.ClassReader包
         */
        ClassReader reader = new ClassReader("sim.com.vo.User");

        /**
         * 2 创建对象
         */
        SimUUClassVisitor simUUClassVisitor = new SimUUClassVisitor();

        /**
         * 3 执行
         */
        reader.accept(simUUClassVisitor,ClassReader.SKIP_DEBUG);


    }
}
