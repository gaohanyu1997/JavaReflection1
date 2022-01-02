package com.ghy.creatthread5;

/*
* notify()通知过早
* */
public class MyThread08 {
    public static void main(String[] args) throws Exception {
        final Object Lock = new Object();   //定义对象作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock){
                    try{
                        System.out.println("begin wait");
                        Lock.wait();
                        System.out.println("end wait");
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock){
                    try{
                        System.out.println("begin notify");
                        Lock.notify();
                        System.out.println("end notify");
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }
        });
        //如果先开启t1,在开启t2线程，大多数情况下，t1先等待，t1在把t1唤醒
        //t1.start();       //运行结果： begin wait begin notify end notify end wait
        //t2.start();
        //如果先开启t2通知线程,在开启t1等待线程，可能会出现t1线程等待没有收到通知的情况
        t2.start();
        t1.start();
    }
}
