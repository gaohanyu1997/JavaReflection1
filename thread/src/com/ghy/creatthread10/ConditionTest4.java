package com.ghy.creatthread10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 使用Condition实现生产者/消费者设计模式，多对多，既有多个线程打印---，有多个线程打印***，实现--与**的交替打印
* */
public class ConditionTest4 {
    static class MyService{
        private Lock lock = new ReentrantLock();    //创建锁对象
        private Condition condition1 = lock.newCondition(); //创建condition对象
        private boolean flag = true;    //定义交替打印标志
        //定义方法打印------横线
        public void printOne(){
            try {
                lock.lock();    //锁定
                while(flag){   //当flag为true等待
                    System.out.println(Thread.currentThread().getName()+",waiting...");
                    condition1.await();
                }
                //flag为false时，打印
                System.out.println(Thread.currentThread().getName()+"----------");
                flag = true;      //修改交替打印标志
                //condition1.signal();    //通知另外的线程打印
                condition1.signalAll();    //通知另外所有等待的线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  //释放锁对象
            }
        }
        //定义方法打印******星号
        public void printTwo(){
            try {
                lock.lock();    //锁定
                while(!flag){   //当flag为false等待
                    System.out.println(Thread.currentThread().getName()+",waiting...");
                    condition1.await();
                }
                //flag为false时，打印
                System.out.println(Thread.currentThread().getName()+"***********");
                flag = false;      //修改交替打印标志
                //condition1.signal();    //通知另外的线程打印
                condition1.signalAll();    //通知另外所有等待的线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  //释放锁对象
            }
        }
    }
    public static void main(String[] args) {
        MyService myService = new MyService();
        for (int i =0;i<10;i++) {
            //创建线程打印--
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <100 ; i++) {
                        myService.printOne();
                    }
                }
            }).start();
            //创建线程打印**
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <100 ; i++) {
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
