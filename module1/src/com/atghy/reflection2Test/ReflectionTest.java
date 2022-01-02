package com.atghy.reflection2Test;

import com.atghy.reflection2.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
* 调用运行时类中指定的结构：属性，方法，构造器
* */
public class ReflectionTest {

    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;
        //newInstance()：获取运行时类的对象
        Person p = (Person) clazz.newInstance();
        //getField()：获取指定的属性：要求运行时类中属性声明为public
        //通常不采用此方式
        Field  id = clazz.getField("id");
        //set()：参数1：指明设置那个对象的属性  参数2：将此属性设置为多少
        id.set(p,1001);
        //获取当前属性的指 get()：参数1:获取哪个对象的属性值
        int pId = (int) id.get(p);
        System.out.println(pId);
    }

    /*
    * 如果获取运行时类中的指定属性 -- 需要掌握
    * */
    @Test
    public void testField2() throws Exception {
        Class clazz = Person.class;
        //获取运行时类的对象
        Person p = (Person) clazz.newInstance();
        //1 getDeclaredField(String fieldName)：获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        //2 保证当前属性是可访问的
        name.setAccessible(true);
        //3 获取，设置指定对象的此属性值
        name.set(p,"tom");
        System.out.println(name.get(p));
    }

    /*
     * 如果获取运行时类中的指定方法 -- 需要掌握
     * */
    @Test
    public void testMethod() throws Exception {
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        //1 getDeclaredMethod(String methodName)：获取运行时类中指定的某个方法
        //参数1：指明获取方法的名称   参数2:指明获取方法的形参列表
        Method show = clazz.getDeclaredMethod("show",String.class);
        //2 保证当前方法是可访问的
        show.setAccessible(true);
        //invoke()   参数1:方法的调用者(哪个对象调用)  参数2：给方法形参赋值的实参
        //invoke()方法的返回值：为对应类中调用的方法的返回值
        Object returnValue  = show.invoke(p,"CHN");   //String nation = p.show("CHN");
        System.out.println(returnValue);

        System.out.println("----如何调用静态方法------");
        // private static void showDesc(){}
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
        Object returnStaticValue = showDesc.invoke(Person.class);
        /*Object returnStaticValue = showDesc.invoke(null);*/
        System.out.println(returnStaticValue);  // null
    }

    /*
     * 如果调用运行时类中的指定的构造器
     * */
    @Test
    public void testConstructor() throws Exception {
        Class clazz = Person.class;
        //private Person(String name)
        //getDeclaredConstructor():获取指定的构造器
        //1 getDeclaredConstructor():参数：指明构造器的参数列表
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class);
        //2 保证此构造器是可访问的
        declaredConstructor.setAccessible(true);
        //3 调用此构造器创建运行时类的对象
        Person tom = (Person) declaredConstructor.newInstance("Tom");
        System.out.println(tom);
    }


}
