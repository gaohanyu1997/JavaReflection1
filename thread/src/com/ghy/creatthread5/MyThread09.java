package com.ghy.creatthread5;

/*
* notify()通知过早，就不让线程等待了
* */
public class MyThread09 {
    static boolean isFirst = true;      //定义静态变量作为是否第一个运行的线程标志
    public static void main(String[] args) throws Exception {
        final Object Lock = new Object();   //定义对象作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock){
                    while(isFirst){     //当线程是第一个开启的线程就等待
                        try{
                            System.out.println("begin wait");
                            Lock.wait();
                            System.out.println("end wait");
                        }catch (Exception e ){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock){
                        System.out.println("begin notify");
                        Lock.notify();
                        System.out.println("end notify");
                        isFirst = false;    //通知后，就把第一个线程标志修改为false
                }
            }
        });
        //如果先开启t1,在开启t2线程，大多数情况下，t1先等待，t1在把t1唤醒
        /*t1.start();
        t2.start();*/
        //如果先开启t2通知线程,在开启t1等待线程，可能会出现t1线程等待没有收到通知的情况
        t2.start();
        t1.start();
        //实际上，调用start()就是告诉线程调度器，当前线程准备就绪，
        //线程调度器在什么时候开启这个线程不确定，即在调用start()开启线程的顺序，并不一定就是线程实际开启的顺序
        //在当前示例中，t1等待后让t2线程唤醒，如果t2线程先唤醒了，就不让t1线程等待了
    }
}
