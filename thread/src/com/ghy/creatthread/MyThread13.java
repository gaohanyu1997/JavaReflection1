package com.ghy.creatthread;

public class MyThread13 extends Thread{
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.println("thread = "+i);
        }
    }

    public static void main(String[] args) throws Exception {
        MyThread13 thread13 = new MyThread13();
        thread13.start();
        //当前线程是main线程
        for(int i=1;i<=5;i++){
            System.out.println("main = "+i);
        }
        //中断子线程
        thread13.interrupt(); //仅仅是给子线程标记中断，子线程没有真正的中断
    }
}
