package com.ghy.creatthread3;

/*
* volatile不具备原子性
* */
public class MyThread03 extends Thread {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <10 ; i++) {
            new MyThread().start();
        }
    }
    //定义类打印字符串
    static class MyThread extends Thread{
        //volatile关键字仅仅是表示所有线程从住内存读取count变量的值
        //synchronized不仅可以保证原子性，还可以保证可见性
        /*volatile public static int count;*/
        public static int count;
        /*  这段代码运行后不是线程安全的，想要线程安全，需要使用synchronized进行同步
            如果使用需要使用synchronized同步，也就不需要volatile
            public static void addCount(){
            for (int i = 0; i <1000 ; i++) {
                //count++不是原子操作
                count += i;
            }
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }*/
        public synchronized static void addCount(){
            for (int i = 0; i <1000 ; i++) {
                //count++不是原子操作
                count += i;
            }
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
