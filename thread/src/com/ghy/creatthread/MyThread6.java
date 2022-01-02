package com.ghy.creatthread;
/*
* 线程的活动状态
* */
public class MyThread6 extends Thread {
    @Override
    public void run() {
        System.out.println("run():"+this.isAlive());    //运行状态：true
    }
    public static void main(String[] args){
        MyThread6 thread6 = new MyThread6();
        System.out.println("t6 begin():"+thread6.isAlive());    //false:在启动线程之前
        thread6.start();
        //结果不一定，打印这一行时，如果t6线程还没结束就返回true,如果已结束，返回false
        System.out.println("t6 end():"+thread6.isAlive());
    }
}
