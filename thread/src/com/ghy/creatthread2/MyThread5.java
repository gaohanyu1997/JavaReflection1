package com.ghy.creatthread2;
/*
* synchronized同步代码块
* 使用一个常量作为锁对象
* */
public class MyThread5 {

    public static void main(String[] args) {
        //创建两个线程，分别调用mm方法
        //先创建MyThread3对象，通过对象名调用mm方法
        MyThread5 obj = new MyThread5();
        MyThread5 obj2 = new MyThread5();
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
}
