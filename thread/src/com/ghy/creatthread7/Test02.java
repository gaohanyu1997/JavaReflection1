package com.ghy.creatthread7;
/*
* 测试一生产多消费的情况
* */
public class Test02 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        ProducerThread p1 = new ProducerThread(stack);
        ConsumerThread c1 = new ConsumerThread(stack);
        ConsumerThread c2 = new ConsumerThread(stack);
        ConsumerThread c3 = new ConsumerThread(stack);
        c1.setName("消费者1");
        c2.setName("消费者2");
        c3.setName("消费者3");
        p1.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
