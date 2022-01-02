package com.ghy.creatthread10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
* int getWaitQueueLength(Condition condition)：返回与Condition条件相关的等待的线程预估数
* */
public class ConditionTest8 {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();   //定义锁对象
        private Condition condition = lock.newCondition();  //返回锁给定的condition
        public void waitMethod(){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" 进入等待前，现在该condition条件上等待的线程预估数："+lock.getWaitQueueLength(condition));
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void notifyMethod(){
            try{
                lock.lock();
                condition.signalAll(); //唤醒所有的等待
                System.out.println("唤醒所有的等待后，condition条件上等待的线程预估数："+lock.getWaitQueueLength(condition));
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        //创建10个线程调用waitMethod()
        for (int i = 0; i <10 ; i++) {
            new Thread(r1).start();
        }
        Thread.sleep(1000);
        service.notifyMethod(); //唤醒所有的等待
    }
}
