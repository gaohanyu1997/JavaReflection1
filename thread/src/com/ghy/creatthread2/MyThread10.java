package com.ghy.creatthread2;
/*
 * 同步方法与同步代码块如何选择
 * 同步方法锁的粒度粗，并发效率低
 * 同步方法
 * */
public class MyThread10 {

    public static void main(String[] args) {
        MyThread10 thread10 = new MyThread10();
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread10.doLoneTimeTask();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread10.doLoneTimeTask();
            }
        }).start();
    }
    //同步方法，执行效率低//同步方法，执行效率低
    public synchronized void doLoneTimeTask(){
        try{
            System.out.println("Task Begain");
            Thread.sleep(3000);     //模拟任务需要准备三秒钟
            System.out.println("开始同步");
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName()+"-->"+i);
            }
            System.out.println("Task End");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
