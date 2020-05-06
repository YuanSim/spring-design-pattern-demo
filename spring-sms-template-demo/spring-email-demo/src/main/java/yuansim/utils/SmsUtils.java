package yuansim.utils;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
public class SmsUtils {

    /**
     *
     */
    public static final String NUMBER_SEED = "123456789";

    /**
     *
     */
    public static final int VERIFICATION_LENGTH = 6;


    /**
     * @param length
     * @return
     */
    public static String generateVerification(String seed, int length) {
        int size = seed.length();
        long timeNow = currentTimeMillis();
        Random random = new Random(timeNow);
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(size);
            sb.append(seed.charAt(index));
        }

        return sb.toString();
    }
}
