package com.ghy.creatthread6;

/*
* 测试一生产，一消费的情况
* */
public class Test {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();
        ProducerThread p = new ProducerThread(valueOP);
        ConsumerThread c = new ConsumerThread(valueOP);
        p.start();
        c.start();
        //生产与消费交易运行
    }
}
