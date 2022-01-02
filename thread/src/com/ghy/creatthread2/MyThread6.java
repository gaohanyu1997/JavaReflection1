package com.ghy.creatthread2;
/*
* synchronized同步代码块
* 使用一个常量作为锁对象
* */
public class MyThread6 {

    public static void main(String[] args) {
        //创建两个线程，分别调用mm方法
        //先创建MyThread3对象，通过对象名调用mm方法
        MyThread6 obj = new MyThread6();
        MyThread6 obj2 = new MyThread6();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();       //使用锁对象obj常量
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.mm();       //使用锁对象obj常量
            }
        }).start();
        //第三个线程调用静态方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();       //使用锁对象obj常量
            }
        }).start();
    }
    public static final Object obj = new Object();  //定义一个常量

    //打印100行字符串
    public void mm(){
        synchronized (obj){    //经常一个常量对象作为锁对象
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName()+"-->" + i);
            }
        }
    }
    public static void sm(){
        synchronized (obj){    //经常一个常量对象作为锁对象
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName()+"-->" + i);
            }
        }
    }
}
