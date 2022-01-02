package com.ghy.creatthread9;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
/*
* 通过ReentrantLock锁的lockInterruptibly()：可以避免死锁的产生
* */
public class Test6 {
    static class IntLock implements Runnable{
        //创建两个ReentrantLock 锁对象
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        int lockNum;    //定义整数变量，决定使用那个锁
        public IntLock(int lockNum){
            this.lockNum = lockNum;
        }
        @Override
        public void run() {
            try{
                if (lockNum % 2 == 1) {  //奇数，先锁1在锁2
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，获得锁1，还需要获得锁2");
                    Thread.sleep(new Random().nextInt(500));
                    /*lock2.lock();*/
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，同时获得了锁1与锁2....");
                } else {  //偶数，先锁2在锁1
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，获得锁2，还需要获得锁1");
                    Thread.sleep(new Random().nextInt(500));
                    /*lock1.lock();*/
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，同时获得了锁2与锁1....");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //如果当前线程持有这个锁，我就释放
                if(lock1.isHeldByCurrentThread()){  //判断当前线程是否持有该锁
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "线程退出了");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(12);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.start();
        t2.start();
        //在main线程，等待3000毫秒，如果还有线程没有结束就中断该线程
        Thread.sleep(3000);
        //可以中断任何一个线程来解决死锁，t2线程会放弃对锁1的申请，同时释放锁2，  t1线程会完成它的任务
        if(t2.isAlive()){
            t2.interrupt();
        }
    }
}
