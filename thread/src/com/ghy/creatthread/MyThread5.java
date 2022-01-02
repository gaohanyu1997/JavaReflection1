package com.ghy.creatthread;

public class MyThread5 extends  Thread{
    public MyThread5(){
        System.out.println("构造方法中,Thread.currentThread().getName()："+Thread.currentThread().getName());
        System.out.println("构造方法中,this.getName()："+this.getName());
    }

    @Override
    public void run() {
        System.out.println("run()中,Thread.currentThread().getName()："+Thread.currentThread().getName());
        System.out.println("run()中,this.getName()："+this.getName());
    }

    public static void main(String[] args) throws Exception {
        //创建子线程对象
        MyThread5 thread5 = new MyThread5();
        thread5.setName("t5");  //设置线程的名称;
        thread5.start();
        Thread.sleep (500);  //main线程睡眠500毫秒

        //Thread(Runnable)构造方法形参是Runnable接口，调用时传递的实参是接口的实现类对象
        Thread t2 = new Thread(thread5);
        t2.start();
    }
}
