package com.ghy.creatthread4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/*
* 模拟服务器的请求总数，处理成功数，处理失败数
* */
public class MyThread02 extends Thread {
    public static void main(String[] args) throws Exception {
        //通过线程模拟请求,在实际应用中可以在ServletFilter中调用Indicator计数器相关方法
        for (int i = 1; i <= 100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程就是一个请求，请求总数要加1
                    Indicator.getInstance().newRequest();
                    int num = new Random().nextInt();
                    if(num % 2 ==0){    //偶数模拟成功
                        Indicator.getInstance().requestSuccess();
                    }else{  //处理失败
                        Indicator.getInstance().requestFialure();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(Indicator.getInstance().getRequestCount());
        System.out.println(Indicator.getInstance().getRequestSuccess());
        System.out.println(Indicator.getInstance().getRequestFialure());
    }
}
/*
* 使用原子变量类定义一个计数器
* 该计数器，在整个程序中都能使用，并且所有的地方都使用这一个计数器，这个计数器可以设计为单例
* */
class Indicator{
    //1 构造方法私有化
    private Indicator(){}
    //2 定义一个私有的本类静态的对象
    private static final Indicator INSTANCE = new Indicator();
    //3 提供一个公共静态方法返回该类的唯一实例
    public static Indicator getInstance(){
        return INSTANCE;
    }
    //使用原子变量类保存请求总数，成功数，失败数
    private final AtomicLong requestCount = new AtomicLong(0); //记录请求总数
    private final AtomicLong successCount = new AtomicLong(0); //处理成功总数
    private final AtomicLong fialureCount = new AtomicLong(0); //处理失败总数
    //有新的请求
    public void newRequest(){
        requestCount.incrementAndGet();
    }
    //处理成功
    public void requestSuccess(){
        successCount.incrementAndGet();
    }
    //有新的请求
    public void requestFialure(){
        fialureCount.incrementAndGet();
    }
    //查看总数，成功数，失败数
    public Long getRequestCount(){
        return requestCount.get();
    }
    public Long getRequestSuccess(){
        return successCount.get();
    }
    public Long getRequestFialure(){
        return fialureCount.get();
    }
}