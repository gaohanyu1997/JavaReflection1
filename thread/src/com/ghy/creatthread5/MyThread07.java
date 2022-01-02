package com.ghy.creatthread5;

/*
* wait(long)
* */
public class MyThread07 {
    public static void main(String[] args) throws Exception {
        final Object obj = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try{
                        System.out.println("begin wait");
                        obj.wait(5000);     //如果5秒内没有被唤醒，会自动唤醒
                        System.out.println("end wait");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }
}
