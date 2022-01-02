package com.ghy.creatthread13;

import java.util.concurrent.*;

/*
* 监控线程池
* */
public class ThreadPoolTest5 {
    public static void main(String[] args) throws InterruptedException {
        //先定义任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+" 编号的线程开始执行："+System.currentTimeMillis());
                try {
                    Thread.sleep(10000);    //线程睡眠10秒,模拟任务执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //定义线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        //向线程池提交30个任务
        for (int i = 0; i <30 ; i++) {
            poolExecutor.submit(r);
            System.out.println("当前线程池核心线程数："+poolExecutor.getCorePoolSize()+"，最大线程数："+poolExecutor.getMaximumPoolSize()
                    +"，当前线程池大小："+poolExecutor.getPoolSize()+"，活动线程数量："+poolExecutor.getActiveCount()
                    +"，收到任务数量："+poolExecutor.getTaskCount()+"，完成任务数量："+poolExecutor.getCompletedTaskCount()
                    +"，等待任务数量："+poolExecutor.getQueue().size());
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("---------------");
        while(poolExecutor.getActiveCount()>0){
            System.out.println("当前线程池核心线程数："+poolExecutor.getCorePoolSize()+"，最大线程数："+poolExecutor.getMaximumPoolSize()
                    +"，当前线程池大小："+poolExecutor.getPoolSize()+"，活动线程数量："+poolExecutor.getActiveCount()
                    +"，收到任务数量："+poolExecutor.getTaskCount()+"，完成任务数量："+poolExecutor.getCompletedTaskCount()
                    +"，等待任务数量："+poolExecutor.getQueue().size());
            Thread.sleep(1000);
        }
    }
}
