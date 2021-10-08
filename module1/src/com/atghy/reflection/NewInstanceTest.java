package com.atghy.reflection;

import org.junit.Test;

import java.util.Random;

public class NewInstanceTest {

    @Test
    public void test1() throws Exception {
        //通过反射创建对应的运行时类的对象
        Class<Person> clazz = Person.class;
        /*
        * newInstance():创建对应的运行时类，内部调用了运行时类的空参构造器
        *要想此方法正常的创建运行时类的对象，要求：
        *    1 运行时类必须提供空参的构造器
        *    2 空参的构造器的访问权限的够，通常设置为public
        *
        * 在javaBean中要求提供一个public的空参构造器，原因：
        *    1 便于通过反射，创建运行时类的对象
        *    2 便于子类继承此运行类类时，默认调用super()时，保证父类有此构造器
        *
        * */
        Person o = clazz.newInstance();
        System.out.print(o);
    }

    //体会反射的动态性
    @Test
    public void test2() throws Exception {
        for(int i=0;i<100;i++){
            String classPath = "";
            int j = new Random().nextInt(3);// 0,1,2
           switch (j){
               case 0:
                   classPath = "java.util.Date";
                   break;
               case 1:
                   classPath = "com.atghy.reflection.Person";
                   break;
               case 2:
                   classPath = "java.lang.Object";
                   break;
           }
            Object instance = getInstance(classPath);
           System.out.print(instance);
        }
    }

    //创建一个指定类的对象 classPath:指定类的全路径
    public Object getInstance(String classPath) throws Exception {
        Class aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

}
