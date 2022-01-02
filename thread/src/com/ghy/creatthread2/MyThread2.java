package com.ghy.creatthread2;

import java.util.Random;

/*
* 测试线程的可见性
* */
public class MyThread2 {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        new Thread(myTask).start();
        Thread.sleep(1000);
        //主线程1ms后取消子线程
        myTask.cancel();
        /*
        *   可能会出现以下情况：
        *       在main线程中调用了task.cancel()方法，把task对象的toCancel变量修改为true
        *       可能存在子线程看不到main线程对象对toCancel做的修改，在子线程toCancel变量一直为false
        *       导致子线程看不到main线程对toCancel变量更新的原因，可能:
        *           1）JIT即时编译器可能会对run()中while循环进行优化为：
        *               if(!toCancel){
        *                   while(true){
        *                        if(doSomething()){
        *                        }
        *                   }
        *               }
        *           2)可能与计算机的储存系统有关，假设分别有两个cpu内核运行main线程与子线程
        *              运行子线程的cpu内核无法立即读取运行main线程Cpu中的数据
        * */
    }

    static class MyTask implements Runnable{
        private boolean toCancel = false;

        @Override
        public void run() {
            while(!toCancel){
                if(doSomething()){
                }
            }
            if(toCancel){
                System.out.println("任务被取消");
            }else{
                System.out.println("任务正常结束");
            }
        };
        private boolean doSomething(){
            System.out.println("执行某个任务...");
            try{
                Thread.sleep(new Random().nextInt(1000)); //模拟执行任务的时间
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        public void  cancel(){
            toCancel = true;
            System.out.println("收到取消线程的消息");
        }
    }
}
