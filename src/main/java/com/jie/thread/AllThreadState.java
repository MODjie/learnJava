package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：AllThreadState
 * 类 描 述：线程的所有状态
 * 创建时间：2019/7/18 23:10
 * 创 建 人：杰哥
 */
public class AllThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("....");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread secondThread = new Thread(new SecondThread());
            secondThread.start();
            try {
                secondThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.State state = thread.getState();
        System.out.println(state);
        thread.start();
        state = thread.getState();
        System.out.println(state);
        while(state!=Thread.State.TERMINATED){
            state = thread.getState();
            System.out.println(state);
        }
    }
}

class SecondThread implements Runnable{

    @Override
    public void run() {
        System.out.println("我是第二个线程");
    }
}