package com.sim;


import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/20
 */
public class SimClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println(String.format("Premain Process by SimClassFileTransformer,target class = %s",className));

        return classfileBuffer;
    }

    private static final Pattern INSTRUMENT_CLASS_PATTERN = Pattern.compile("com/jfc/core/.*");
    public static void main(String[] args) {


        Matcher matcher = INSTRUMENT_CLASS_PATTERN.matcher("com/jfc/coupon/response");

        System.out.println( 0 & 0x100);

        Pattern pattern = compile("com/jfc/((?!config).)*");

        matcher = pattern.matcher("com/jfc/order/ttt");

        System.out.println(matcher.matches() );
        //
    }
}
