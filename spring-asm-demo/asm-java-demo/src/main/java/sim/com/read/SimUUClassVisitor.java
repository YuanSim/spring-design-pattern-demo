package sim.com.read;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;


import static org.objectweb.asm.Opcodes.ASM7;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/22
 */
public class SimUUClassVisitor extends ClassVisitor {

    public SimUUClassVisitor() {
        /**
         * 版本号
          */
        super(ASM7);
    }

    @Override
    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {

        System.out.println(String.format("SimUUClassVisitor 获取方法名称：%s",name));
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {

        System.out.println(String.format("SimUUClassVisitor 获取注解名称：%s",descriptor));
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

        System.out.println(String.format("SimUUClassVisitor 获取属性名称：%s",name));

        return super.visitField(access, name, descriptor, signature, value);
    }

}
