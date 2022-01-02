package com.ghy.creatthread4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/*
* 线程类
* */
public class MyThread05 extends Thread {
    private User user;  //要更新的user对象
    //创建AtomicIntegerFieldUpdater更新器
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");
    public MyThread05(User user) {
        this.user = user;
    }
    @Override
    public void run() {
        //在子线程中user对象的age字段自增10次
        for (int i = 0; i <10 ; i++) {
            System.out.println(updater.getAndIncrement(user));
        }
    }
    public static void main(String[] args) throws Exception {
        User user = new User(1234,10);
        //开启10个线程
        for (int i = 0; i <10 ; i++) {
                new MyThread05(user).start();
        }
        Thread.sleep(1000);
        System.out.println(user);
    }
}
/*
* 使用AtomicIntegerFieldUpdater更新的字段必须使用volatile修饰
* */
class User{
    int id;
    volatile int age;
    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
