package com.atghy.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/*
* Lambda表达式的使用
* 1 举例：(o1,o2) -> Integer.compare(o1,o2);
* 2 格式：
*       -> ：lambda操作符 或 箭头操作符
*       ->左边 : lambda形参列表(其实就是接口中的抽象方法的形参列表)
*       ->右边 : lambda体 （其实就是重写的抽象方法的方法体）
* 3 Lambda表达式的使用：(分为6中情况介绍)
*       总结：
*          ->左边 : lambda形参列表的参数列表可以省略(类型推断 )；如果lambda形参列表只有一个参数，其一对()也可以省略；
*           ->右边 : lambda体应该使用一对{}包裹，如果lambda体只有一条执行语句(可能是return语句),省略这一对{}和return关键字
*
* 4 Lambda表达式的本质：作为函数式接口的实例
* 5 如果接口中，只声明了一个抽象方法，则此接口就成为函数式接口
* 所以以前用匿名实现类表示的现在都可以用Lambda表达式来写。
* */
public class LambdaTest1 {
    @Test
    public void test1(){
        //格式一：无参，无返回值
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("========");
        Runnable r2 = () -> {
            System.out.println("我爱北京股东");
        };
        r2.run();
    }

    @Test
    public void test2(){
        //格式二：Lambda需要一个参数，但是没有返回值
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么");
        System.out.println("*****************");
        Consumer<String> con2 = (String s) -> {
            System.out.println(s);
        };
        con2.accept("篮球明星都有谁？");
    }

    @Test
    public void test3() {
        //格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
        Consumer<String> con = (String s) -> {
            System.out.println(s);
        };
        con.accept("篮球明星都有谁？");
        System.out.println("*****************");
        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("篮球明星都有谁？");
    }

    @Test
    public void test4() {
        ArrayList<String> list = new ArrayList<>();  //类型推断
        int[] arr = new int[]{1,2,3};
        int[] arr2 = {1,2,3}; //类型推断
    }

    @Test
    public void test5() {
        //格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
        Consumer<String> con = (s) -> {
            System.out.println(s);
        };
        con.accept("篮球明星都有谁？");
        System.out.println("*****************");
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("篮球明星都有谁？");
    }
    @Test
    public void test6() {
        //格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,21));
        System.out.println("*****************");
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(12,6));
    }

    @Test
    public void test7() {
        //格式六：当Lambda 体只有一条语句时，return 与大括号若有，都可以省略
        Comparator<Integer> com = (o1,o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(com.compare(12,6));
        System.out.println("*****************");
        Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(12,21));
    }
    @Test
    public void test8() {
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("篮球明星都有谁？");
        System.out.println("*****************");
        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("篮球明星都有谁？");
    }
}
