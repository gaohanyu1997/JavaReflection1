package com.ghy.creatthread8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 在多线程环境中，把字符串转换为日期对象，多个线程使用同一个SimpleDateFormat对象可能会产生线程安全问题，有异常
* 为每个线程指定自己的SimpleDateFormat对象，使用ThreadLocal
* */
public class Test02 {
    //定义SimpleDateFormat对象，该对象可以把字符串转换为日期
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
    //定义Runnable接口的实现类
    static class PerseDate implements Runnable{
        private int  i = 0;
        public PerseDate(int i){
            this.i = i;
        }
        @Override
        public void run() {
            try {
                String text = "2068年11月22日 08:28:" + i%60;    //构建一个日期字符串
                //Date date = sdf.parse(text);        //把字符串转换为日期
                //System.out.println(i+" -- "+date);
                //先判断当前线程是否有SimpleDateFormat对象，如果当前线程没有SimpleDateFormat对象就创建一个，有就直接使用
                if(threadLocal.get() == null){
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date date = threadLocal.get().parse(text);
                System.out.println(i+" -- "+date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new PerseDate(i)).start();
        }
    }
}
