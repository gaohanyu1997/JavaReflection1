package com.ghy.creatthread3;

import java.util.concurrent.atomic.AtomicInteger;

/*
* 使用原子类进行自增
* */
public class MyThread04 extends Thread {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <10 ; i++) {
            new MyThread().start();
        }
        Thread.sleep(1000);
        System.out.println(MyThread.count.get());
    }
    //定义类打印字符串
    static class MyThread extends Thread{
        //使用AtomicInteger对象
        private static AtomicInteger count = new AtomicInteger();
        public static void addCount(){
            for (int i = 0; i <100 ; i++) {
                //自增的后缀形式
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName()+"count = "+count.get());
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
