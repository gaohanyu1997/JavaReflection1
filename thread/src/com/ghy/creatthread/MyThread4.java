package com.ghy.creatthread;
/**
 * Thread.currentThread();//可以获得当前线程
 * 分别在构造方法中和run方法中打印当前线程
 */
public class MyThread4 extends Thread{
    public MyThread4(){
        System.out.println("构造方法打印当前线程名称："+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程名称："+Thread.currentThread().getName());
    }

    public static void main(String[] args){
        System.out.println("main方法打印当前线程名称："+Thread.currentThread().getName());
        //创建子线程，调用MyThread4()的构造方法
        //在main线程中调用构造方法，所以构造方法中当前线程就是main线程
        MyThread4 thread4 = new MyThread4();
        //启动子线程，子线程会调用run()方法，所以run()方法中的当前线程就是Thread-0
        /*thread4.start();*/
        //在main方法中直接调用run()方法，没有开启新的线程，所以在run方法中的当前线程就是main线程
        thread4.run();
    }
}
