package com.ghy.creatthread;

public class MyThread15 extends Thread {

    @Override
    public void run() {
        while(true){
            System.out.println("run()....");
        }
    }

    public static void main(String[] args) {
        MyThread15 thread15 = new MyThread15();
        //设置线程为守护线程
        //设置守护线程的代码应该在线程启动前
        thread15.setDaemon(true);
        thread15.start();
        //main线程
        for(int i=1;i<=5;i++){
            System.out.println("main" + i);
        }
        //当main线程结束，守护线程thread也销毁了
    }
}
