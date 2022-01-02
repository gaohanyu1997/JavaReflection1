package com.ghy.creatthread2;
/*
* synchronized同步实例方法
* 把整个方法体作为同步代码快
* 默认的锁对象是this对象
* */
public class MyThread7 {

    public static void main(String[] args) {
        //先创建MyThread3对象，通过对象名调用mm方法
        MyThread7 obj = new MyThread7();
        //一个线程调用mm()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();       //使用锁对象this就是obj对象,可以同步
                /*new MyThread7().mm();*/   //使用的锁对象this是刚刚创建的一个新对象，不是同一个锁对象，不能同步
            }
        }).start();
        //一个线程调用mm22()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm22();       //使用锁对象this就是obj对象
            }
        }).start();
    }

    public  void mm(){
        synchronized(this){     //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName()+"-->" + i);
            }
        }

    }

    //使用synchronized修饰实例方法，同步实例方法默认this作为锁对象
    public synchronized void mm22(){
        for (int i = 1; i <= 100 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-->" + i);
        }
    }
}
