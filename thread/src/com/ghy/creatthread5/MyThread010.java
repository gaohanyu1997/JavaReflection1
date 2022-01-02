package com.ghy.creatthread5;

import java.util.ArrayList;
import java.util.List;

/*
*  wait条件发生变化
*   定义一个集合
*   定义一个线程向集合中添加数据，添加完数据后通知另外的线程从集合中去数据
*   定义一个线程从集合中取数据，如果集合中没有数据就等待
* */
public class MyThread010 {
    public static void main(String[] args) throws Exception {
        //定义添加数据的线程对象
        ThreadAdd threadAdd = new ThreadAdd();
        //定义取数据的线程对象
        ThreadSubTract threadSubTract = new ThreadSubTract();
        threadSubTract.setName("subtract1 ");
        //测试1：先开启添加数据的线程，在开启一个取数据的线程，大多数情况下会正常执行
        /*threadAdd.start();
        threadSubTract.start();*/
        /*显示：
        *   subtract 1从集合中取了data后，集合中数据的数量0
        * */
        //测试2:先开启取数据的线程，在开启添加数据的线程,取数据的线程会等待，等到添加数据之后，再取数据
       /* threadSubTract.start();
        threadAdd.start();*/
        /*显示         subtract1 begin wait...
                      subtract1 end wait...
                      subtract1 从集合中取了data后，集合中数据的数量0 */
        //测试3:开启两个 取数据的线程，在开启添加数据的线程
        ThreadSubTract threadSubTract2 = new ThreadSubTract();
        threadSubTract2.setName("subtract2 ");
        threadSubTract.start();
        threadSubTract2.start();
        threadAdd.start();
        /*某一次执行结果如下：
        *   subtract1 begin wait...
            subtract2 从集合中取了data后，集合中数据的数量0
            subtract1 end wait...
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
*       分析可能的执行顺序：
*               threadSubTract线程先启动，取数据时，集合中没有数据，wait()等待
*               threadAdd线程获得CPU执行权，添加数据，把threadSubTract线程唤醒
*               threadSubTract2线程开启后获得CPU执行权，正常取数据
*               threadSubTract线程获得CPU执行权，打印end wait...,然后在执行list.remove(0);取数据时，
*           现在list集合中已经没有数据了，这时会产生 下标越界 异常
*        出现异常的原因是：向list集合中添加了一个数据，remove()了两次
*        如果解决？
*           当等待的线程被唤醒后，再判断一次集合中是否有数据可取，即需要把subTract()方法中的if判断改为while
        * */
    }
    //1) 定义一个集合
    static List list = new ArrayList<>();
    //2) 定义方法从集合中取数据
    public static void subTract() throws InterruptedException {
        synchronized (list){
            /*if(list.size() == 0){*/
            while(list.size() == 0){
                System.out.println(Thread.currentThread().getName()+"begin wait...");
                list.wait();    //等待
                System.out.println(Thread.currentThread().getName()+"end wait...");
            }
            Object data = list.remove(0);    //从集合中取出一个数据
            System.out.println(Thread.currentThread().getName()+"从集合中取了"+data+"后，集合中数据的数量"+list.size());
        }
    }
    //3)定义方法向集合中添加数据,通知等待的线程去数据
    public static void add(){
        synchronized (list){
            list.add("data");
            System.out.println(Thread.currentThread().getName()+"存储了一个数据");
            list.notifyAll();
        }
    }
    //4)定义一个线程类调用subTract()取数据的方法
    static class ThreadAdd extends Thread{
        @Override
        public void run() {
            add();
        }
    }
    //5)定义一个线程类调用subTract()取数据的方法
    static class ThreadSubTract extends Thread{
        @Override
        public void run() {
            try {
                subTract();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
