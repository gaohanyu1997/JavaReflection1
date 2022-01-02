package com.ghy.creatthread;

public class MyThread9 extends Thread{

    @Override
    public void run() {
        System.out.println("name = "+Thread.currentThread().getName()+",id = " + this.getId());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread()+",id = " + Thread.currentThread().getId());
       for(int i=1;i<=5;i++){
           new MyThread9().start();
           Thread.sleep(1000);
       }
    }
}
