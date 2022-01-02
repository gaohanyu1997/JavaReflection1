package com.atghy.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
* Java内置的4大核心函数式接口
*
* 消费型接口 Consumer<T>     void accept(T t)
* 供给型接口 Supplier<T>     T get()
* 函数型接口 Function<T,R>   R apply(T t)
* 断定型接口 Predicate<T>    boolean test(T t)
*
* */
public class LambdaTest2 {
    @Test
    public void testFunction1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学费是" + aDouble);
            }
        });
        System.out.println("********************");
        happyTime(400,aDouble -> System.out.println("学费是" + aDouble));
    }
    public void happyTime(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public void testFunction2(){
        //“Arrays.asList的使用 Arrays.asList的作用是将数组转化为list,
        // 一般是用于在初始化的时候,设置几个值进去,简化代码,省去add的部分。”
        List<String> list = Arrays.asList("北京","东京","南京","西京");
        List<String> filterS = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("北");
            }
        });
        System.out.println(filterS);
        System.out.println("********************");
        List<String> filterS2 = filterString(list,s -> s.contains("西"));
        System.out.println(filterS2);
    }
    //根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }
}
