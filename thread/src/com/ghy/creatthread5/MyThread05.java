package com.ghy.creatthread5;

/*
* interrupt方法会中断线程的wait等待
* */
public class MyThread05 {
    public static void main(String[] args) throws Exception {
        SubThread s1 = new SubThread();
        s1.start();
        Thread.sleep(2000);     //主线程睡眠2秒，确保子线程处于wait()等待状态
        s1.interrupt();
    }
    private static final Object LOCK = new Object();    //定义常量作为对象
    static class SubThread extends Thread{
        @Override
        public void run() {
            synchronized (LOCK){
                try{
                    System.out.println("begin wait...");
                    LOCK.wait();
                    System.out.println("end wait...");
                }catch(Exception e){
                    System.out.println("wait等待被中断了***********");
                }
            }
        }
    }
}
