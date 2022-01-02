package com.ghy.creatthread;
/*
*多线程运行结果是随机的
* */
class MyThread2 extends Thread{
    @Override
    public void run() {
        try{
            for (int i=1;i<=10;i++){
                System.out.println("Thread" + i);
                int time = (int) (Math.random()*1000);
                Thread.sleep(time);     //线程休眠，单位是毫秒    1秒 = 1000 ms
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        MyThread2 thread2 = new MyThread2();
        thread2.start();    //开启子线程
        try{
            for (int i=1;i<=10;i++){
                System.out.println("main" + i);
                int time = (int) (Math.random()*1000);
                Thread.sleep(time);     //线程休眠，单位是毫秒    1秒 = 1000 ms
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
