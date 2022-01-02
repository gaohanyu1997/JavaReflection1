package com.ghy.creatthread2;
/*
* synchronized同步静态方法
* 把整个方法体作为同步代码快
* 默认的锁对象是当前类的运行时类对象MyThread8.class,有人称它为类锁
* */
public class MyThread8 {

    public static void main(String[] args) {
        //先创建MyThread3对象，通过对象名调用mm方法
        MyThread8 obj = new MyThread8();
        //一个线程调用m1()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.m1();       //使用的锁对象是MyThread8.class
            }
        }).start();
        //一个线程调用sm2()方法
        new Thread(new Runnable() {
            @Override
            public void run() {MyThread8.sm2();       //使用锁对象是MyThread8.class
            }
        }).start();
    }

    public  void m1(){
        //使用当前类的运行时类对象作为锁对象，可以简单的理解为把MyThread8类的字节码文件作为锁对象
        synchronized(MyThread8.class){
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName()+"-->" + i);
            }
        }

    }

    //使用synchronized修饰静态方法，同步静态方法，默认运行时类MyThread8.class作为锁对象
    public synchronized static void sm2(){
        for (int i = 1; i <= 100 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-->" + i);
        }
    }
}
