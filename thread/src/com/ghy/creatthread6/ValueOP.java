package com.ghy.creatthread6;
/*
* 定义一个操作数据的类
* */
public class ValueOP {
    private String value = "";
    //定义方法修改value字段的值
    public void setValue() throws InterruptedException {
        synchronized (this){
            //如果value不是""空串就等待
            /*if(!value.equalsIgnoreCase("")){*/
            while(!value.equalsIgnoreCase("")){
                this.wait();
            }
            //如果value字段值是空串,就设置value字段的值
            String value = System.currentTimeMillis()+"-"+System.nanoTime();
            System.out.println("set设置的值是："+value);
            this.value = value;
            /*this.notify();*/  //在多生产多消费者环境中，notify()不能保证是生产者唤醒消费者，
                                // 如果生产者唤醒的还是生产者可能会出现假死的情况
            this.notifyAll();
        }
    }

    //定义方法读取字段值
    public void getValue() throws InterruptedException {
        synchronized (this){
            //如果value是空串就等待
            /*if(value.equalsIgnoreCase("")){*/
            while(value.equalsIgnoreCase("")){
                this.wait();
            }
            //不是空串，读取字段值
            System.out.println("get的值是："+this.value);
            this.value = "";
            /*this.notify();*/
            this.notifyAll();
        }
    }
}
