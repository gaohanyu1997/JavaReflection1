package com.ghy.creatthread10;

import java.util.concurrent.locks.ReentrantLock;

/*
* boolean hasQueuedThread(Thread thread)：查询参数指定的线程是否在等待获得锁
* boolean hasQueuedThreads()：查询是否还有线程在等待获得该锁
* */
public class ConditionTest9 {
    static ReentrantLock lock = new ReentrantLock();
    public static void waitMethod(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得了锁");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放了锁对象");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ConditionTest9.waitMethod();
            }
        };
        Thread [] threads = new Thread[5];  //定义一个线程数组
        //给线程数组的元素赋值，每个线程都调用waitMethod()方法，并启动线程
        for (int i = 0; i <threads.length ; i++) {
            threads[i] = new Thread(r);
            threads[i].setName("thread - "+i);
            threads[i].start();
        }
        Thread.sleep(3000);
        //判断数组中的每个线程对象是否正在等待获得锁
        System.out.println(lock.hasQueuedThread(threads[0]));
        System.out.println(lock.hasQueuedThread(threads[1]));
        System.out.println(lock.hasQueuedThread(threads[2]));
        System.out.println(lock.hasQueuedThread(threads[3]));
        System.out.println(lock.hasQueuedThread(threads[4]));
        Thread.sleep(2000);
        //判断是否还有线程在等待获得锁
        System.out.println(lock.hasQueuedThreads());
    }
}
