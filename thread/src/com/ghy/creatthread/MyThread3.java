package com.ghy.creatthread;
/**
 * 实现Runnable接口的形式创建线程
 * 当线程类已经有父类了，就不能用继承Thread类的形式创建线程，可以使用实现Runnable接口的形式
 * 1 定义类实现Runnable接口
 */
public class MyThread3 implements Runnable{
    //2 重写Runnable接口中的抽象方法run()，run()方法就是子线程要执行的代码
    @Override
    public void run() {
        for(int i=1;i<=100;i++){
            System.out.println("thread1:" + i);
        }
    }

    public static void main(String[] args) {
        //3 创建Runnable接口对象的实现类对象
        MyThread3 runnable = new MyThread3();
        //4 创建线程对象
        Thread t1 = new Thread(runnable);
        t1.start();
        //当前是main线程
        for(int i=1;i<=10;i++){
            System.out.println("main" + i);
        }

        //有时调用Thread(runnable)构造方法时，实参也会传递匿名内部类对象
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println("thread2:" + i);
                }
            }
        });
        t2.start();

        Thread t3 = new Thread(() ->{
                for(int i=1;i<=100;i++){
                    System.out.println("thread3:" + i);
                }
        });
        t3.start();
    }
}
