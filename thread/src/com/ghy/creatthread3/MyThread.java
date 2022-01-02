package com.ghy.creatthread3;

/*
* 演示线程的原子性问题
*
* */
public class MyThread extends Thread {

    public static void main(String[] args) throws Exception {
        //创建对象
        PrintString ps = new PrintString();
        //调用方法打印字符串
        ps.printStringMethod();
        //main线程睡眠1000ms
        Thread.sleep(1000);
        System.out.println("main线程中修改打印标志");
        ps.setContinuePrint(false);
        //修改完打印标志后，运行程序，查看程序运行结果
        //程序根本不会停止，因为printStringMethod()方法，调用后，该方法一直处于死循环状态，程序根本就执行不到ps.setContinuePrint(false);语句
        //解决方法：可以使用多线程技术
    }
    //定义类打印字符串
    static class PrintString{
        private boolean continuePrint = true;
        public PrintString setContinuePrint(boolean continuePrint){
            this.continuePrint = continuePrint;
            return this;
        }
        public void printStringMethod() throws Exception {
            while(continuePrint){
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(500);
            }
        }
    }
}
