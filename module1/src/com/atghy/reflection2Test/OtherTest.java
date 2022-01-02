package com.atghy.reflection2Test;

import com.atghy.reflection2.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
* 获取构造器结构
* */
public class OtherTest {
    @Test
    public void test1(){
        Class clazz = Person.class;
        //getConstructors():获取当前运行时类中声明public的构造器
        Constructor[] constructors = clazz.getConstructors();
        for(Constructor c : constructors){
            System.out.println(c);
        }
        System.out.println();
        //getDeclaredConstructors():获取当前运行时类中声明都所有构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for(Constructor c : declaredConstructors){
            System.out.println(c);
        }
    }

    @Test
    public void test2(){
        Class clazz = Person.class;
        //获取运行时类的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    @Test
    public void test3(){
        Class clazz = Person.class;
        //获取运行时类的带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    @Test
    public void test4(){
        Class clazz = Person.class;
        //获取运行时类的带泛型父类的泛型
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class)actualTypeArguments[0]).getName());
    }

    @Test
    public void test5(){
        //获取运行时类实现的接口
        Class clazz = Person.class;
        //getInterfaces():获取运行时类实现的接口
        Class[] interfaces = clazz.getInterfaces();
        for(Class i : interfaces){
            System.out.println(i);
        }
        System.out.println();
        //getSuperclass().getInterfaces():获取运行时类继承父类实现的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for(Class i : interfaces1){
            System.out.println(i);
        }
        System.out.println();
    }

    @Test
    public void test6(){
        Class clazz = Person.class;
        //getPackage()：获取运行时类所在的包
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    @Test
    public void test7(){
        Class clazz = Person.class;
        //getPackage()：获取运行时类声明的注解
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation a : annotations){
            System.out.println(a);
        }

    }
}
