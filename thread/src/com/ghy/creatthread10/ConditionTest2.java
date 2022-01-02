package com.ghy.creatthread10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
* 多个Condition实现通知部分线程，使用更加灵活
* */
public class ConditionTest2 {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();   //定义锁对象
        //定义两个Condition对象
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();
        //定义方法，使用ConditionA等待
        public void waitMethodA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"begin wait" + System.currentTimeMillis());
                conditionA.await();
                System.out.println(Thread.currentThread().getName()+"end wait" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        //定义方法，使用ConditionB等待
        public void waitMethodB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"begin wait" + System.currentTimeMillis());
                conditionB.await();
                System.out.println(Thread.currentThread().getName()+"end wait" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        //定义方法唤醒conditionA对象上的等待
        public void signalA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"signal A time = " + System.currentTimeMillis());
                conditionA.signal();
                System.out.println(Thread.currentThread().getName()+"signal A time = " + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }
        //定义方法唤醒conditionB对象上的等待
        public void signalB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"signal B time = " + System.currentTimeMillis());
                conditionB.signal();
                System.out.println(Thread.currentThread().getName()+"signal B time = " + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        //开启两个线程，分别调用waitMethodA()，waitMethodB()
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodB();
            }
        }).start();
        Thread.sleep(3000); //main线程睡眠3s
        //service.signalA();  //唤醒 conditionA对象上的等待，conditionB上的等待依然继续等待
        service.signalB();      //唤醒 conditionB对象上的等待，conditionA上的等待依然继续等待
    }
}
