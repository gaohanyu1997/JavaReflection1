package com.ghy.creatthread2;
/*
 * 同步方法与同步代码块如何选择
 * 同步代码块
 * */
public class MyThread9 {

    public static void main(String[] args) {
        MyThread9 thread9 = new MyThread9();
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread9.doLoneTimeTask();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread9.doLoneTimeTask();
            }
        }).start();
    }
    //同步代码快，锁的粒度细，执行效率高
    public void doLoneTimeTask(){
        try{
            System.out.println("Task Begain");
            Thread.sleep(3000);     //模拟任务需要准备三秒钟
            synchronized (this){
                System.out.println("开始同步");
                for (int i = 0; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"-->"+i);
                }
            }
            System.out.println("Task End");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
