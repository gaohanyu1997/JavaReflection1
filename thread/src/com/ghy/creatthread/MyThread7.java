package com.ghy.creatthread;
/*
* 子线程休眠
* */
public class MyThread7 extends  Thread{
    @Override
    public void run() {
        try {
            System.out.println("run():"+Thread.currentThread().getName()+",begin:"+System.currentTimeMillis());
            Thread.sleep(3000); //当前线程睡眠3000毫秒 1s = 1000ms
            System.out.println("run():"+Thread.currentThread().getName()+",end:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        MyThread7 thread7 = new MyThread7();
        System.out.println("main_begin = "+System.currentTimeMillis());
        /*thread7.start();*/   //开启新的线程
        thread7.run();      //在main线程中调用实例方法run()，没有开启新的线程
        System.out.println("main_end = "+System.currentTimeMillis());
    }
}
