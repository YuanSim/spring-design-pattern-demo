package sim.com;

import java.util.function.Predicate;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/7/23
 */
public class PredicateDemo {

    /**
     * @see java.util.function.Predicate 断言
     * 包括3个default函数  一个static函数  一个抽象函数
     *
     * @param args
     */
    public static void main(String[] args) {


//        test();

//        and();

//        negate();

//        or();

        isEqual();
    }

    /**
     * Predicate 的isEqual,是Predicate的静态函数
     * 比较运算,返回一个Predicate的实现
     */
    public static void isEqual() {

        /**
         * 两个对象比较
         */
        boolean test = Predicate.isEqual("1").test(null);

        System.out.println(String.format("call PredicateDemo isEqual %s",test));

    }

    /**
     * Predicate 的or,是端两个断言(Predicate的实现)的实现 逻辑|(或者)运算
     * 返回的是Predicate的实现结构体
     */
    public static void or() {

        Predicate<String> p1 = x -> x.equals("1");

        Predicate<String> p2 = x -> x.equals("");

        boolean test = p1.or(p2).test("1");
        System.out.println(String.format("call PredicateDemo or %s",test));

    }



    /**
     * Predicate 的negate是对断言(Predicate的实现)的取反操作
     * 返回值是 断言(Predicate的实现)
     */
    public static void negate() {

        Predicate<String> p1 = x -> x.equals("1");
        boolean test = p1.negate().test("2");
        System.out.println(String.format("call PredicateDemo negate %s",test));
    }


    /**
     * Predicate 的and函数 参数是Predicate实现，返回一个复合实现结构
     * 会先执行调用者的实现 使用逻辑运算符(&&) 参数的实现
     * 简单说就是执行两个Predicate实现结果集的 &&运算
     */
    public static void and() {

        Predicate<String> p1 = x -> x.equals("1");

        Predicate<String> p2 = x -> x.equals("1");

        boolean test = p1.and(p2).test("1");

        System.out.println(String.format("call PredicateDemo and %s",test));
    }

    /**
     * Predicate 的test是我们需要实现的函数，接受任意类型参数，返回boolean
     * 一般在stream的filter中使用
     */
    public static void test() {

        Predicate<Integer> p = x -> x == 0;

        boolean test = p.test(1);

        System.out.println(String.format("call PredicateDemo test %s",test));
    }
}
