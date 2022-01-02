package com.ghy.creatthread13;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
* 线程池可能会吃掉程序中的异常
* */
public class ThreadPoolTest6 {
    //定义类实现Runnable接口,用于计算两个数相除
    private static class  DivideTask implements Runnable{
        private int x;
        private int y;
        public  DivideTask(int x,int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"计算:"+x+"/"+y+"="+(x/y));
        }
    }
    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        //向线程池中添加两个数相除的任务
        for (int i = 0; i <5 ; i++) {
            /*executor.submit(new DivideTask(10,i));*/
            executor.execute(new DivideTask(10,i));
        }
        /*
        *   运行程序,只有四条计算结果,我们实际上向线程池提交了5个计算任务
        *   分析结果发现:
        *       当i=0时,提交的任务会产生算数异常，线程池把该异常给吃掉了,导致我们对该异常一无所知
        *   解决方法:
        *       一是把submit()提交方法改为execute()
        *       二是对线程池进行扩展,对submit()方法进行包装
        * */
    }
}
