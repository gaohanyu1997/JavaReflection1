package com.atghy.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    @Test
    public void test1(){
        // 1 创建Person类对象
        Person p1 =new Person("王五",15);
        // 2 通过对象调用其内部的属性和方法
        p1.setName("李松");
        System.out.print(p1.toString());
        p1.show();
        //在Person类的外部，不可以通过Person类的对象调用其内部私有结构  showNation
    }

    @Test
    public void test2() throws Exception {
        //反射之后
        // 1 通过反射创建Person类的对象
        Class clazz = Person.class;
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("tom",12);
        Person p = (Person) obj;
        System.out.print(p.toString());

        // 2 通过反射，调用对象指定的属性和方法
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.print(p.toString());
        // 调用方法
        Method showMethod = clazz.getDeclaredMethod("show");
        showMethod.invoke(p);

        System.out.print("*****调用Person内部私有属性。方法******");
        //通过反射，可以调用Person的私有结构
        Constructor cs = clazz.getDeclaredConstructor(String.class);
        cs.setAccessible(true);
        Object lorry = cs.newInstance("Lorry");
        Person p1 = (Person) lorry;
        System.out.print(lorry);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"tom");
        System.out.print(p1);

        //调用私用的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);   // 调用私有的方法
        // showNation.invoke 的返回值就是该方法的返回值 String
        String nation = (String) showNation.invoke(p1,"中国"); //相当于 String nation = p1.ShowNation("中国");
        System.out.print(nation);

        /*
        关于java.lang.Class类的理解
        1 类的加载过程：
            程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)，接着我们使用
            java.exe命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中，此
            过程就成为类的加载。加载到内存中的类就成为运行时类，此运行类就作为一个Class的实例
        2  换句话说 Class的实例就对应这一个运行时类
        3  加载到内存中的运行时类，会缓存一段时间，在此时间之内，可以通过不同的方式来获取此运行时类
        */
    }

    //获取Class的实例的方式(前三种方式需要掌握)
    @Test
    public void test3() throws Exception {
        //获取方式一：调用运行时类的属性 .class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //获取方式二：通过运行时类的对象 .getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);
        //获取方式三：调用Class的静态方法Class.forName(String classPath) 类的全路径
        Class clazz3 = Class.forName("com.atghy.reflection.Person");
        System.out.println(clazz3);
        //获取方式四：使用类加载器ClassLoader(了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atghy.reflection.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz2);   //true
        System.out.println(clazz1 == clazz3);   //true
        System.out.println(clazz1 == clazz4);   //true
    }
}
