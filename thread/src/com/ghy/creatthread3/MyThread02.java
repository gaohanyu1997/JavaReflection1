package com.ghy.creatthread3;

/*
* 演示线程的原子性问题
*
* */
public class MyThread02 extends Thread {

    public static void main(String[] args) throws Exception {
        //创建对象
        PrintString ps = new PrintString();
        //开启子线程，让子线程执行ps.printStringMethod();方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ps.printStringMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //main线程睡眠1000ms
        Thread.sleep(1000);
        System.out.println("main线程中修改打印标志");
        ps.setContinuePrint(false);
        //程序运行，查看在main线程中修改了打印标志后，子线程打印是否可以结束
        //程序运行后，可能会出现死循环的情况
        //分析原因：main线程修改了printString对象的打印标志后，子线程读不到
        //解决办法：使用volatile关键字修饰printString对象的打印标志
        //使用volatile作用：可以强制线程从公共内存中读取变量的值，而不是从工作内存中
    }
    //定义类打印字符串
    static class PrintString{
        private volatile boolean continuePrint = true;
        public PrintString setContinuePrint(boolean continuePrint){
            this.continuePrint = continuePrint;
            return this;
        }
        public void printStringMethod() throws Exception {
            System.out.println(Thread.currentThread().getName()+"开始");
            while(continuePrint){
            }
            System.out.println(Thread.currentThread().getName()+"结束");
        }
    }
}
