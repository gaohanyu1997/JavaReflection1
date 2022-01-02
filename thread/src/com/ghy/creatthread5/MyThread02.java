package com.ghy.creatthread5;
/*
* wait()会使线程等待
* 需要放在同步代码块中，通过锁对象调用
* */
public class MyThread02 {
    public static void main(String[] args) throws Exception {
        String test = "helloworld";
        String test1 = "hi";
        System.out.println("同步前的代码");
        synchronized (test){
            System.out.println("同步代码块开始。。。。");
            test.wait();    //调用wait后，当前线程就会等待，释放锁对象，当前线程需要被唤醒，如果没有唤醒就会一直等待
  //        test1.wait();   //不是锁对象调用会产生IllegalMonitorStateException异常异常
            System.out.println("wait后面的代码");
        }
        System.out.println("同步后的代码");
        System.out.println("main后面其他的代码");
    }
}
