package com.ghy.creatthread;
/*
* 1 定义类继承Thread
*
* */
public class MyThread extends Thread {
    //2 重写Thread父类中的run()
    //run()方法体中的代码就是子线程要执行的任务
    @Override
    public void run() {
        System.out.println("这是子线程打印的内容");
    }

    public static void main(String[] args){
        System.out.println("JVM启动main线程，main线程执行main方法");
        //3 创建子线程对象
        MyThread thread = new MyThread();
        //4 启动线程
        thread.start();
        System.out.println("main方法后面的代码");
        /*
        *调用线程的start()方法来启动线程，启动线程的实质就是请求JVM运行相应的线程，
        * 这个线程具体什么时候运行由线程调度器(Scheduler)决定
        *注意：  start()方法调用结束并不意味着子线程开始运行
        *       新开启的线程会执行run()方法
        *       如果开启了多个线程，start()调用的顺序并不一定就是线程启动的顺序
        *       多线程运行结果与代码执行顺序或调用顺序无关
        * */
    }
}
