package com.ghy.creatthread10;

import java.util.concurrent.locks.ReentrantLock;

/*
* int getQueueLength()：返回正等待获得锁的线程预估数
* */
public class ConditionTest7 {
    static ReentrantLock lock = new ReentrantLock();
    public static void sm() throws InterruptedException {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得锁，执行方法，估计等待获得锁的线程数：" + lock.getQueueLength());
            Thread.sleep(1000); //睡眠1s，模拟执行时间
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sm();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //开启10个线程，执行sm()方法
        for (int i = 0; i <10 ; i++) {
            new Thread(r).start();
        }
    }
}
