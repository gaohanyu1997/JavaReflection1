package com.atghy.reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {

    @Test
    public void test1() throws Exception {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader2 = classLoader.getParent();
        System.out.println(classLoader2);

        //调用扩展类加载器的.getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);

        //说明String类是引导类加载器进行加载的
        ClassLoader StringClassLoader = String.class.getClassLoader();
        System.out.println(StringClassLoader);
    }

    @Test
    public void test2() throws Exception{
        // Properties: 用来读取配置文件
        Properties prop = new Properties();
        //此时的文件默认在当前的modulel下
        //读取配置文件的方式一
//      FileInputStream fis = new FileInputStream("jdbc.properties");
        FileInputStream fis = new FileInputStream("src/jdbc1.properties");
        prop.load(fis);

        //读取配置文件的方式二
        //配置文件默认识别为当前module的src下
//      ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//      InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
//      prop.load(resourceAsStream);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        System.out.print("username" + username+",password"+password);

    }
}
