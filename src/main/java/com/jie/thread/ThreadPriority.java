package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：ThreadPriority
 * 类 描 述：线程的优先级
 * 创建时间：2019/7/19 20:21
 * 创 建 人：杰哥
 */
public class ThreadPriority {
    public static void main(String[] args) {
        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority,"高优先级1");
        Thread thread2 = new Thread(myPriority,"高优先级2");
        Thread thread3 = new Thread(myPriority,"高优先级3");
        Thread thread4 = new Thread(myPriority,"中优先级1");
        Thread thread5 = new Thread(myPriority,"中优先级2");
        Thread thread6 = new Thread(myPriority,"中优先级3");
        Thread thread7 = new Thread(myPriority,"低优先级1");
        Thread thread8 = new Thread(myPriority,"低优先级2");
        Thread thread9 = new Thread(myPriority,"低优先级3");

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);
        thread4.setPriority(Thread.NORM_PRIORITY);
        thread5.setPriority(Thread.NORM_PRIORITY);
        thread6.setPriority(Thread.NORM_PRIORITY);
        thread7.setPriority(Thread.MIN_PRIORITY);
        thread8.setPriority(Thread.MIN_PRIORITY);
        thread9.setPriority(Thread.MIN_PRIORITY);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
    }
}

class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"->"+Thread.currentThread().getPriority());
        Thread.yield();
    }
}