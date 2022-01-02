package com.ghy.creatthread10;

import java.util.concurrent.locks.ReentrantLock;

/*
* boolean isLocked():判断锁是否被线程持有
* */
public class ConditionTest12 {
    static ReentrantLock lock = new ReentrantLock();
    static void sm(){
        try{
            System.out.println("before lock() -- "+lock.isLocked());    //false
            lock.lock();
            System.out.println("after lock() == "+lock.isLocked());     //true
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1 -- "+lock.isLocked());    //false
        //开启线程调用sm()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        }).start();
        Thread.sleep(3000); //确保子线程执行结束
        System.out.println("2 -- "+lock.isLocked());    //false(已经释放了)
    }
}
