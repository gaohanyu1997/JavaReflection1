package com.ghy.creatthread4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
*演示AtomicReference可能会出现CAS的ABA问题
* */
public class MyThread07 {
    private static AtomicReference<String> reference = new AtomicReference<>("abc");
    public static void main(String[] args) throws Exception {
        //创建第一个线程，先把abc字符串改为def,在把字符串还原为abc
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reference.compareAndSet("abc","def");
                System.out.println(Thread.currentThread().getName()+"--"+reference.get());
                reference.compareAndSet("def","abc");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(reference.compareAndSet("abc","ghg"));
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(reference.get());
    }
}
