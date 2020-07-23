package sim.com;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/7/23
 */
public class ConsumerAndSupplierDemo {


    /**
     * @see java.util.function.Supplier  供给型接口  返回值类型就是参数类型
     * @see java.util.function.Consumer  消费型接口  传入参数消费，无返回值
     *
     * Supplier 与 Consumer 是对应的，一起使用效果杠杠的
     *
     * @param args
     */
    public static void main(String[] args) {

//        get();

//        accept();

//        andThan();

        consumerAndSupplier();

    }

    /**
     * 结合 Consumer与Supplier一起使用
     */
    public static void consumerAndSupplier() {

        Map<String,Object> userMap = new HashMap<>();
        userMap.put("id","1");
        userMap.put("name","echo");


        Consumer<Map<String,Object>> consumer = x -> {
            if(Predicate.isEqual(x.get("id")).test("1")) {
                x.put("name","hell");
            }
        };
        // 消费
        consumer.accept(userMap);
        Supplier<Map<String,Object>> supplier = () -> userMap;
        // 获取消费之后的数据
        supplier.get();

        System.out.println(userMap);

    }

    /**
     * Consumer的andThan是它的默认函数
     * 接受一个Consumer结构
     * 返回一个并行的Consumer实现结构
     * 执行的时候先执行调用者的 在执行参数的
     */
    public static void andThan() {

        Consumer<String> c = x -> System.out.println(x + 1);
        Consumer<String> c1 = x -> System.out.println(x + 2);
        c.andThen(c1).accept("hello");

    }

    /**
     * Consumer的accept是它的抽象函数，参数泛型，无返回值
     */
    public static void accept() {

        Consumer<String> c = x ->
                System.out.println(String.format("call accept result is %s",x.toUpperCase(Locale.CHINA)));
        c.accept("hello ");
    }

    /**
     * Supplier 只有一个抽象函数 get 没有参数
     * 返回T 泛型
     */
    public static void get(){

        Supplier<String> s = () -> "hello";

        String get = s.get();

        System.out.println(String.format("call get result is %s",get));
    }
}
