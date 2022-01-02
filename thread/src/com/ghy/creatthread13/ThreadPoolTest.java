package com.ghy.creatthread13;

import java.util.concurrent.*;

/*
* 扩展线程池
* */
public class ThreadPoolTest {
    //定义任务类
    private static class MyTask implements Runnable{
        private String name;
        public MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name+"任务正在执行 "+Thread.currentThread().getId()+" 执行");
            try {
                Thread.sleep(1000); //模拟任务执行时长
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //定义扩展线程池,可以定义线程池类继承ThreadPoolExecutor，在子类中重写beforeExecutor()/afterExecute()方法
        //也可以直接使用ThreadPoolExecutor的内部类
        ExecutorService service = new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()){
            //在内部类中重写任务开始方法
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId()+"线程准备执行任务："+((MyTask)r).name);
            }
            //在内部类中重写任务结束方法
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).name+"任务执行完毕");
            }
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        //向线程池中添加任务
        for (int i = 0; i <5 ; i++) {
            MyTask task = new MyTask("task-"+i);
            service.execute(task);
        };
        //关闭线程池
        service.shutdown(); //关闭线程池,仅仅是说线程池不在接收新的任务,线程池中已接收的任务正常执行完毕
    }
}
