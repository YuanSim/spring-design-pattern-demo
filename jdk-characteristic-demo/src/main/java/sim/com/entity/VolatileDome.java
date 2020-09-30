package sim.com.entity;

import java.util.concurrent.TimeUnit;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/29
 */
public class VolatileDome {

     int numberInt = 0;

     volatile int numberVolatile = 0;

    public void setNumberIntTo100() {
        this.numberInt = 100;
    }

    public void setNumberVolatileTo100(){
        this.numberVolatile = 100;
    }

    public static void main(String[] args) {

        VolatileDome dome = new VolatileDome();
//        domeNumberInt(dome);


        domeNumberVolatile(dome);
    }

    /**
     * 使用volatile 修饰过的int 修改之后的值对其他线程是可见的，所以主线程知道值被修改了
     * @param dome
     */
    private static void domeNumberVolatile(VolatileDome dome) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dome.setNumberVolatileTo100();
            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update numberInt value:" + dome.numberVolatile);
        },"子线程").start();

        while(dome.numberVolatile == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 如果查询执行到这里说明修改的属性是被主线程察觉的
        System.out.println(Thread.currentThread().getName() + "\t 主线程感知到了 numberVolatile 不等于 0");
    }

    /**
     * w未被修饰的int 对于不同线程之间修改之后的值是不可见的
     * @param dome
     */
    private static void domeNumberInt(VolatileDome dome) {
        // 使用子线程修改 numberInt ，查看修改之后的值对主线程是否可见
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dome.setNumberIntTo100();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update numberInt value:" + dome.numberInt);
        },"子线程").start();

        while(dome.numberInt == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 如果查询执行到这里说明修改的属性是被主线程察觉的
        System.out.println(Thread.currentThread().getName() + "\t 主线程感知到了 numberInt 不等于 0");
    }
}
