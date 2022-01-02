package com.ghy.creatthread10;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
* boolean hasWaiters(Condition condition)：查询是否有线程正在等待指定的Condition条件
* */
public class ConditionTest10 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static void sm(){
        try{
            lock.lock();
            System.out.println("是否有线程正在等待当前Condition条件? "+lock.hasWaiters(condition)+" --  waitqueuelenth: "+lock.getWaitQueueLength(condition));
            System.out.println(Thread.currentThread().getName()+" wait........");
            condition.await(new Random().nextInt(1000), TimeUnit.MILLISECONDS); //超时后，会自动唤醒
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"--超时唤醒，是否有线程正在等待当前Condition条件? "+lock.hasWaiters(condition)+" --  waitqueuelenth: "+lock.getWaitQueueLength(condition));
            //System.out.println(Thread.currentThread().getName()+"unlock........");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        //开启10个线程，调用sm()方法
        for (int i = 0; i <10 ; i++) {
            new Thread(r).start();
        }
    }
}
