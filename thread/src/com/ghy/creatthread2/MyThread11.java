package com.ghy.creatthread2;
/*
 * 藏读
 *  出现读取属性值出现了一些意外，读取的是中间值，而不是修改之后的值
 *  出现脏度的原因是 对共享数据的修改 与 对共享数据的读取不同步，可能会出现脏读
 * 解决方法：
 *  不仅对修改数据的代码块进行同步，还要对读取数据的代码块进行同步
 * */
public class MyThread11 {
    public static void main(String[] args) throws Exception {
        PublicValue pValue = new PublicValue();
        SubThread t1 = new SubThread(pValue);
        t1.start();
        //为了确定设置成功
        Thread.sleep(100);
        //在main线程中读取用户名和密码
        pValue.getValue();
    }
    //定义线程，设置用户名和密码
    static class SubThread extends Thread{
        public PublicValue pValue;
        public SubThread(PublicValue pValue){
            this.pValue = pValue;
        }
        @Override
        public void run() {
            try {
                pValue.setValue("abc","abc");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class PublicValue{
        private String name = "ghy";
        private String pwd = "123";
        public synchronized void getValue(){
            System.out.println(Thread.currentThread().getName()+",get操作"+",name = "+name +",pwd = " +pwd);
        }
        public synchronized void setValue(String name,String pwd) throws Exception {
            this.name = name;
            Thread.sleep(1000); //模拟操作name属性需要一定的时间
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName()+",set操作"+",name = "+name+",pwd="+pwd);
        }
    }
}
