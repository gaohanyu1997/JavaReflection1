package com.ghy.creatthread12;
/*
* 演示设置守护线程组
* */
public class ThreadGroupTest5 {
    public static void main(String[] args) throws InterruptedException {
        //先定义线程组
        ThreadGroup group = new ThreadGroup("group");
        //设置线程组为守护线程组
        group.setDaemon(true);
        //向group组中添加3个线程
        for (int i = 0; i <3 ; i++) {
            new Thread(group, new Runnable() {
                @Override
                public void run() {
                    for(int j = 0;j<20;j++){
                        System.out.println(Thread.currentThread().getName()+"------"+j);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        //main线程睡眠5s
        Thread.sleep(5000);
        System.out.println("main....end......");
    }
}
