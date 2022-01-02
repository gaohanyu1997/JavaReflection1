package com.ghy.creatthread4;

/*
* 使用CAS实现一个线程安全的计数器
*
* */
public class MyThread extends Thread {

    public static void main(String[] args) throws Exception {
        CASCount cc = new CASCount();
        for (int i = 1; i <= 100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(cc.incrementAndGet());
                }
            }).start();
        }
    }
}
class CASCount{
    //使用volatile修饰value值，使线程可见
    volatile private long value;

    public long getValue(){
        return value;
    }
    //定义comare and swap方法
    private boolean CompareAndSwap(long expectedValue,long newValue){
        //如果当前value的值与期望的expectedValue值一样，就把当前的value字段替换为newValue值
        synchronized (this){
            if(value == expectedValue){
                value = newValue;
                return true;
            }else{
                return false;
            }
        }
    }
    //定义自增的方法
    public long incrementAndGet(){
        long oldValue;
        long newValue;
        do{
            oldValue = value;
            newValue = oldValue + 1;
        }while(! CompareAndSwap(oldValue,newValue));
        return newValue;
    }
}