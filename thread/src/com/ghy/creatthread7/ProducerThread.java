package com.ghy.creatthread7;

public class ProducerThread extends Thread{
    private MyStack stack;
    public ProducerThread(MyStack stack){
        this.stack = stack;
    }
    @Override
    public void run() {
        while(true){
            try {
                stack.push();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
