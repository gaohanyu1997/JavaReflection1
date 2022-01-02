package com.atghy.lambda2;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
* 方法引用的使用
* 1 使用情景：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
* 2 方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以方法引用，也是函数式接口的实例
* 3 使用格式 类(或对象) :: 方法名
* 4 具体分为三种情况：
*   情况1 对象 :: 实例方法名(非静态方法)
*   情况2 类 :: 静态方法名
*   情况3 类 :: 实例方法名(非静态方法)
* 5 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型
*   与方法引用的方法的形参列表和返回值类型相同！(针对于情况1 和 情况2)
* */
public class MethodRefTest {

    //情况一：对象 :: 实例方法
    //Consumer 中的 void accept(T t)
    //PrintStream 中的 void println(T t)
    @Test
    public void test1(){
        Consumer<String> con = str -> System.out.println(str);
        con.accept("北京");
        System.out.println("===================");
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("西安");
    }

    //Supplier 中的 T get()
    //Employee 中的 String getName()
    @Test
    public void test2(){
        Employee em = new Employee(1001,"Tom",23,999);
        Supplier<String> sup1 =() -> em.getName();
        System.out.println(sup1.get());
        System.out.println("===================");
        Supplier<String> sup2 =em::getName;
        System.out.println(sup2.get());
    }

    //情况二： 类::静态方法
    //Comparator 中的 int Compare(T t1,T t2)
    //Integer 中的 int compare(T t1,T t2)
    @Test
    public void test3(){
        Comparator<Integer> com1 = (t1,t2) ->Integer.compare(t1,t2);
        int compare = com1.compare(12, 21);
        System.out.println(compare);
        System.out.println("===================");
        Comparator<Integer> com2 = Integer::compare;
        int compare2 = com2.compare(35, 21);
        System.out.println(compare2);
    }

    //Function 中的 R apply(T t)
    //Math 中的 Long round(Double d)
    @Test
    public void test4(){
        Function<Double, Long> func1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };
        Long apply1 = func1.apply(14.5);
        System.out.println(apply1);
        System.out.println("===================");
        Function<Double, Long> func2 = d -> Math.round(d);  //Lambda表达式
        Long apply2 = func2.apply(12.3);
        System.out.println(apply2);
        System.out.println("===================");
        Function<Double, Long> func3 = Math::round; //方法引用
        Long apply3 = func3.apply(16.8);
        System.out.println(apply3);
    }

    //情况三： 类::实例方法(有难度)
    //Comparator 中的 int comapre(T t1,T t2)
    //String 中的 int t1.compareTo(t2);
    @Test
    public void test5(){
        Comparator<String> com1 = (s1, s2) ->s1.compareTo(s2);
        int c1 = com1.compare("abc", "abd");
        System.out.println(c1);
        System.out.println("===================");
        Comparator<String> com2 = String::compareTo;
        int c2 = com2.compare("acd", "acd");
        System.out.println(c2);
    }

    //BiPredicate 中的 boolean test(T t1,T t2)
    //String 中的 boolean t1.equals(t2);
    @Test
    public void test6(){
        BiPredicate<String,String> pre1 = (t1,t2) -> t1.equals(t2);
        System.out.println(pre1.test("abc","abc"));
        System.out.println("===================");
        BiPredicate<String,String> pre2 = String::equals;
        System.out.println(pre2.test("abc","abd"));
    }

    //Function 中的R apply(T t)
    //Employee 中的 String getName();
    @Test
    public void test7(){
        Employee e1 = new Employee(1001,"jerry",23,999);
        Function<Employee,String> f1 = e -> e.getName();
        String apply = f1.apply(e1);
        System.out.println(apply);
        System.out.println("===================");
        Function<Employee,String> f2 = Employee::getName;
        String apply2 = f2.apply(e1);
        System.out.println(apply2);
    }
}
