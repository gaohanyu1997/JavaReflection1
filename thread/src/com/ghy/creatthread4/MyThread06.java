package com.ghy.creatthread4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/*
* AtomicReference对象 可以原子读写一个对象
* */
public class MyThread06{
    //创建一个AtomicReference对象
    static AtomicReference<String> reference = new AtomicReference<>("abc");

    public static void main(String[] args) throws Exception {
        //创建100个线程修改字符串
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (reference.compareAndSet("abc","def")){
                        System.out.println(Thread.currentThread().getName()+"把字符串更改为def");
                    }
                }
            }).start();
        }
        //在创建100个线程
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(reference.compareAndSet("def","abc")){
                        System.out.println(Thread.currentThread().getName()+"把字符串还原为abc");
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(reference.get());
    }
}
