package com.ghy.creatthread;

public class MyThread11 extends Thread {
    @Override
    public void run() {
        long sumA = 0;
        long beginA = System.currentTimeMillis();
        for(int i=1;i<=1000000000;i++){
            sumA += i;
        }
        long endA = System.currentTimeMillis();
        System.out.println("A用时:" + (endA - beginA ));
    }

    public static void main(String[] args) throws Exception {
        MyThread11 thread11 = new MyThread11();
        thread11.setPriority(1);
        thread11.start();
        MyThread12 thread12 = new MyThread12();
        thread12.setPriority(10);
        thread12.start();
    }
}

class MyThread12 extends Thread {
    @Override
    public void run() {
        long sumB = 0;
        long beginB = System.currentTimeMillis();
        for (int i = 1; i <= 10000000; i++) {
            sumB += i;
        }
        long endB = System.currentTimeMillis();
        System.out.println("B用时:" + (endB - beginB));
    }
}
