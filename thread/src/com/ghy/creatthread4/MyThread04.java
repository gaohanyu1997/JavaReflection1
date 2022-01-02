package com.ghy.creatthread4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/*
* 在多线程中使用AtomicIntegerArray原子数组
* */
public class MyThread04 extends Thread {
    //定义原子数组
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static void main(String[] args) throws Exception {
        //定义一个线程数组
        Thread[] threads = new Thread[10];
        //给线程数组元素赋值
        for (int i = 0; i < threads.length ; i++) {
            threads[i] = new AddThread();
        }
        //开启子线程
        for (Thread t:threads){
            t.start();
        }
        //在主线程中查看自增完后原子数组中各个元素的值，在主线程中需要在所有子线程都执行完在查看
        //把所有的子线程合并到当前主线程中
        for(Thread t:threads){
            t.join();
        }
        System.out.println(arr);
    }
    //定义一个线程类，在线程类中修改原子数组
    static class AddThread extends Thread{
        @Override
        public void run() {
            //把原子数组的每个元素自增1000次
            for (int j = 0;j<1000;j++) {
                for (int i = 0; i < arr.length() ; i++) {
                    arr.getAndIncrement(i % arr.length());
                }
            }
        }
    }
}
