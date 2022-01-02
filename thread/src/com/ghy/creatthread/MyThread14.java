package com.ghy.creatthread;

public class MyThread14 extends Thread {
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            //判断线程的中断标志，线程有isInterrupted()方法，该方法返回线程的中断标志
            if(this.isInterrupted()){
                System.out.println("run()方法执行完毕，我退出了");
               /* break;*/  //中断循环，run()方法体执行完毕，子线程运行完毕
                return; //直接结束当前run()方法的执行
            }
            System.out.println("run() = " +i);
        }
    }

    public static void main(String[] args) {
        MyThread14 thread14 = new MyThread14();
        thread14.start();
        for(int i=1;i<=5;i++){
            System.out.println("main()" + i);
        }
        thread14.interrupt();
    }
}
