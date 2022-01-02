package com.ghy.creatthread9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* ReentrantLock锁的可重入性
* */
public class LockTest4 {
    static class SubThread extends Thread{
        //静态变量整个类所有对象所共有的
        //这样多个线程对象用的是同一个锁
        //如果为实例变量的话，是锁不住的，因为每一个线程对象都有它自己的实例变量
        private static Lock lock = new ReentrantLock();    //定义锁对象
        public static int num = 0;  //定义变量
        @Override
        public void run() {
            for (int i = 0; i <10000 ; i++) {
                try{
                    //可重入锁指可以反复获得该锁
                    lock.lock();
                    lock.lock();
                    num++;
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(SubThread.num);
    }
}
