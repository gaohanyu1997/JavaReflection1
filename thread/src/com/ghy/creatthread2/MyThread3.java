package com.ghy.creatthread2;
/*
* synchronized同步代码块
* this锁对象
* */
public class MyThread3 {

    public static void main(String[] args) {
        //创建两个线程，分别调用mm方法
        //先创建MyThread3对象，通过对象名调用mm方法
        MyThread3 obj = new MyThread3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();       //使用锁对象this就是obj对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();       //使用锁对象this就是obj对象
            }
        }).start();
    }
    //打印100行字符串
    public void mm(){
        synchronized (this){    //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName()+"-->" + i);
            }
        }
    }
}
