package com.atghy.reflection2Test;

import com.atghy.reflection2.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/*
 * 获取当前运行类的所有的属性结构
 */
public class FieldTest {

    @Test
    public void test1(){
        Class clazz = Person.class;
        //获取属性结构
        //getFields():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] f1 = clazz.getFields();
        for(Field f : f1){
            System.out.println(f);
        }
        System.out.println();
        //getDeclaredFields()：获取当前运行时类中声明的所有属性(不包含父类中声明的属性)
        Field[] f2 = clazz.getDeclaredFields();
        for (Field f : f2){
            System.out.println(f);
        }
    }

    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){
        Class clazz = Person.class;
        Field[] df = clazz.getDeclaredFields();
        for(Field f : df){
            //获取权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(modifiers + "\t");
            System.out.print(Modifier.toString(modifiers) + "\t");
            //数据类型
            Class type = f.getType();
            System.out.print(type.getName() +"\t");
            //变量名
            String name = f.getName();
            System.out.println(name);
        }
    }
}
