package com.ghy.creatthread8;

/*
* ThreadLocal的基本使用
* */
public class Test {
    //定义ThreadLocal对象
    static ThreadLocal threadLocal = new ThreadLocal();
    //定义线程类
    static class SubThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                //设置线程关联的值
                threadLocal.set(Thread.currentThread().getName()+" - "+i);
                //调用get方法读取关联的值
                System.out.println(Thread.currentThread().getName()+",value="+threadLocal.get());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        Thread.sleep(10);
        t2.start();
    }
}
