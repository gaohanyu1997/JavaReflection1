package com.ghy.creatthread7;

public class ConsumerThread extends Thread {
    private MyStack stack;
    public ConsumerThread(MyStack stack){
        this.stack = stack;
    }
    @Override
    public void run() {
        while (true){
            try {
                stack.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
