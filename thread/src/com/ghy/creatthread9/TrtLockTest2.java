package com.ghy.creatthread9;

import java.util.concurrent.locks.ReentrantLock;

/*
* tryLock()
* 当锁对象没有被其他线程持有的情况下才会获得该锁定
* */
public class TrtLockTest2 {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        public void serviewMethod(){
            try{
                if(lock.tryLock()){
                    System.out.println(Thread.currentThread().getName()+"获得锁定");
                    Thread.sleep(3000);     //模拟执行任务的时长
                }else{
                    System.out.println(Thread.currentThread().getName()+"没有获得锁定");
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Service s = new Service();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                s.serviewMethod();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(50);   //睡眠50毫秒，确保t1线程锁定
        Thread t2 = new Thread(r);
        t2.start();
    }
}
