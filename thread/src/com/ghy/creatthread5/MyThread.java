package com.ghy.creatthread5;

/*
*  演示wait()/notify()方法需要放在同步代码块中,否则会产生java.lang.IllegalMonitorStateException异常
* 任何对象都可以调用wait()和notify(),这两个方法都是在Object类继承来的
* */
public class MyThread{
    public static void main(String[] args) throws Exception {
        String test="helloworld";
        try{
            test.wait();    //java.lang.IllegalMonitorStateException异常
        }catch (IllegalMonitorStateException e){
            e.printStackTrace();
        }
    }
}
