package sim.com;

import java.util.function.Function;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/7/23
 */
public class FunctionDemo extends Object{


    /**
     * @see java.util.function.Function
     *
     * 所有使用  {@FunctionInterface} 注解的都是函数接口(该注解不是必须的), 如果一个接口只有一个方法，则编译器会认为这就是一个函数式接口
     * interface Function<T, R>
     *     function有两个泛型参数 T表示入参     <T> the type of the input to the function
     *                         R表示返回值   <R> the type of the result of the function
     *  Function 包括2个default方法和2个static类方法，已经一个需要实现的方法
     */
    public static void main(String[] args) {

//        apply();

//        compose();

//        andThen();

//        identity();
    }

    /**
     * Function的identity方法 是类方法(静态方法)
     * 没有参数，返回的就是当前执行的自己
     *
     */
    public static void identity() {

        Function<Integer,Integer> a = x -> x * 5;

        /**
         *  compose 优先执行参数，
         *  1. Function.identity().apply(1) 的返回值是1
         *  2. a.apply(1)
         *  返回值5
         */
        Integer apply = a.compose(Function.identity()).apply(1);

        /**
         *  Function.identity().apply(1)  返回值1
         *  这种不能单独自己使用，否则会实例化Function的内部类，默认的apply 就是返回参数
         *  一般在stream中使用，来找当前执行的 this
         */

        System.out.println(String.format("call identity result is %s",apply));

    }

    /**
     * Function 的andThan方法 是一个默认函数，不需要我们实现，可以直接使用
     * 它跟compose类似， 接受一个Function的匿名类，返回一个Function的匿名类
     * 区别是他们返回Function 匿名类的结构顺序是相反的
     * 执行的时候调用者会先执行，然后将调用者的返回值传递给参数进行执行,可以理解为并行执行
     */
    public static void andThen() {

        Function<Integer,Integer> a1 = x -> x * 5;
        Function<Integer,Integer> param = x -> x + 10;
        Function<Integer, Integer> andThen = a1.andThen(param);
        Integer apply = andThen.apply(1);
        System.out.println(String.format("call andThen result is %s",apply));
    }


    /**
     * Function 的compose方法，是一个默认的函数，不需要我们实现，可以直接使用
     * 这个函数参数是一个Function实现,{@param Function<? super V, ? extends T> before}，也可以是复合函数
     * 返回的是一个Function的匿名实现类对象 default <V> Function<V, R> 对应的是 (V v) -> apply(before.apply(v))
     * 执行的时候参数的结构会先执行，然后参数的返回值传递给调用者进行执行
     */
    public static void compose() {


        /**
         *  等同于匿名内部类
         *  Function<Integer,Integer> a1 = new Function<Integer, Integer>() {
         *             @Override
         *             public Integer apply(Integer integer) {
         *                 return integer * 5;
         *             }
         *         }
         */


        /**
         *  相对于：
         *  Function<Integer, Integer> compose = new Function<Integer, Integer>() {
         *                       @Override
         *                       public Integer apply(Integer integer) {
         *                          return integer * 5;
         *                       }
         *                   }.compose(new Function<Integer, Integer>() {
         *                       @Override
         *                       public Integer apply(Integer num) {
         *                           return num + 10;
         *                       }
         *                   });
         */
        Function<Integer,Integer> a1 = x -> x * 5;
        Function<Integer,Integer> param = x -> x + 10;
        /**
         * 执行的时候参数的结构会先执行，然后参数的返回值传递给调用者进行执行
         */
        Function<Integer, Integer> compose = a1.compose(param);
        Integer apply = compose.apply(1);

        System.out.println(String.format("call compose result is %s",apply));

    }

    /**
     * Function 真正需要实现的函数只有 apply,
     * 此函数需要传入一个参数T，返回R,当然实际使用
     * 中我们也可以参数与返回值使用同类型
     */
    public static void apply() {

        // 示例1
        Function<Integer,String> function1 = x -> x + "#";
        String apply1 = function1.apply(3);
        System.out.println(apply1);


        // 示例2
        Function<Integer,Integer> function2 = x->x;
        Integer apply2 = function2.apply(3);
        System.out.println(String.format("call apply result is %s",apply2));

    }
}
