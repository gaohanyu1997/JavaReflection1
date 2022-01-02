package com.ghy.creatthread;
/*
* 使用线程休眠Thread.sleep完成一个简易的计时器
* */
public class MyThread8 {

    public static void main(String[] args) throws InterruptedException {
        int time = 10; //从10秒开始
        while(true){
            System.out.println("计时器"+time);
            time-- ;
            if(time < 0){
                break;
            }
            Thread.sleep(1000);     //线程休眠
        }
        System.out.println("计时器完成");
    }
}
