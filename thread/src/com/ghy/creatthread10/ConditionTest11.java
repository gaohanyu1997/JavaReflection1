package com.ghy.creatthread10;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/*
* Boolean isFair()：判断是否为公平锁
* Boolean isHeldByCurrentThread()：判断锁是否被当前线程持有
* */
public class ConditionTest11 {
    static class Service{
        static ReentrantLock lock;
        //通过构造方法接收布尔值，确定当前锁是否公平
        public Service(Boolean isFair){
            this.lock = new ReentrantLock(isFair);
        }
        public void serviceMethod(){
            try{
                System.out.println("是否公平锁："+lock.isFair()+" -- "+Thread.currentThread().getName()+"调用lock前是否持有锁？"+lock.isHeldByCurrentThread());
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"调用lock方法后是否持有锁？"+lock.isHeldByCurrentThread());
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                //如果锁对象被当前线程持有就释放锁
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt();
                new Service(num % 2 == 0 ? true:false).serviceMethod();
            }
        };
        for (int i = 0; i <3 ; i++) {
            new Thread(r,"Thread - "+i).start();
        }
    }
}
