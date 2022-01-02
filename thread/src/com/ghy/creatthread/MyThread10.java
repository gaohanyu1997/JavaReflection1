package com.ghy.creatthread;

public class MyThread10  extends Thread{
    @Override
    public void run() {
        long sumRun = 0;
        long beginRun = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            sumRun += i;
            Thread.yield(); //线程让步，放弃CPU执行权
        }
        long endRun = System.currentTimeMillis();
        System.out.println("run()用时 = " + (endRun-beginRun));
    }

    public static void main(String[] args) throws Exception {
        //开启子线程计算累加和
        MyThread10 t10 = new MyThread10();
        t10.start();
        //main线程计算累加和
        long sumMain = 0;
        long beginMain = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            sumMain += i;
        }
        long endMain = System.currentTimeMillis();
        System.out.println("main()用时 = " + (endMain-beginMain));
    }
}
