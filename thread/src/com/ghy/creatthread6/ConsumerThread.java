package com.ghy.creatthread6;
/*
* 定义线程类模拟消费者
* */
public class ConsumerThread extends Thread {
    //消费者使用数据，就是使用ValueOP类的value字段值
    private ValueOP obj;
    public ConsumerThread(ValueOP obj){
        this.obj = obj;
    }
    @Override
    public void run() {
        while(true){
            try {
                obj.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
