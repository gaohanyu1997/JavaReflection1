package com.atghy.optional;

import org.junit.Test;

import java.util.Optional;

/*
* Optional.of(T t):创建一个Optional实例,t必须非空；
* Optional.empty():创建一个空的Optional实例
* Optional.ofNullable(T t):t可以为null
* */
public class OptionTest {
    @Test
    public void test1(){
        Girl girl = new Girl();
        /*girl = null;
        * of(T t):保证t是非空的*/
        Optional<Girl> girl1 = Optional.of(girl);
    }
    @Test
    public void test2(){
        Girl girl = new Girl();
        girl = null;
        //ofNullable(T t):t可以为null
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);
        //orElse(T t):如果当前的Optional内部封装的t是非空的，则返回内部的t
        //如果内部的t是空的,则返回orElse()方法中的参数t1
        Girl girl2 = girl1.orElse(new Girl("赵丽颖"));
        System.out.println(girl2);
    }
    public String getGrilName(Boy boy){
        return boy.getGirl().getName();
    }
    @Test
    public void test3(){
        Boy boy = new Boy();
        boy = null;
        String grilName = getGrilName(boy);
        System.out.println(grilName);
    }
    //优化以后的getGrilName()
    public String getGrilName1(Boy boy){
        if(boy != null){
            Girl girl = boy.getGirl();
            if(girl != null){
                return girl.getName();
            }
        }
        return null;
    }
    @Test
    public void test4(){
        Boy boy = new Boy();
        boy = null;
        String grilName = getGrilName1(boy);
        System.out.println(grilName);
    }
    public String getGrilName2(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //gir1一定非空
        Girl gir1 = girlOptional.orElse(new Girl("古力娜扎"));
        return gir1.getName();
    }
    @Test
    public void test5(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("苍老师"));
        String grilName = getGrilName2(boy);
        System.out.println(grilName);
    }
}
