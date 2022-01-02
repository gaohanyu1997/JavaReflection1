package com.ghy.creatthread4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/*
* AtomicIntegerArray的基本操作
* */
public class MyThread03 extends Thread {
    public static void main(String[] args) throws Exception {
        //1 创建一个指定长度的原子数组
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        System.out.println(array);  //[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //2 返回指定位置的元素
        System.out.println(array.get(0));   //0
        System.out.println(array.get(1));   //0
        //3 设置指定位置的元素
        array.set(0,10);
        //在设置数组元素的新值时，同时返回数组元素的旧值
        System.out.println(array.getAndSet(1,11));  //0
        System.out.println(array);  // [10, 11, 0, 0, 0, 0, 0, 0, 0, 0]
        //4 修改数组元素的值，把数组元素加上某个值
        System.out.println(array.addAndGet(0,22));  //32
        System.out.println(array.getAndAdd(1,33));  //11
        System.out.println(array);  //[32, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        //5 CAS操作
        //如果数组中索引值为0的元素的值是22，就修改为222
        System.out.println(array.compareAndSet(0,32,222));  //true
        System.out.println(array);  //[222, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(array.compareAndSet(1,11,333));  //false
        System.out.println(array);  //[222, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        //6自增和自减
        System.out.println(array.incrementAndGet(0));   //223 相当于前缀
        System.out.println(array.getAndIncrement(1));   //44    相当于后缀
        System.out.println(array);  //[223, 45, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(array.decrementAndGet(2));   //-1
        System.out.println(array);  //[223, 45, -1, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(array.getAndDecrement(3));     //0
        System.out.println(array);     //[223, 45, -1, -1, 0, 0, 0, 0, 0, 0]
    }
}
