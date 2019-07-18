package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：YieldDemo
 * 类 描 述：yield方法的使用:yield礼让，进入就绪状态，不会阻塞线程
 * 创建时间：2019/7/17 22:45
 * 创 建 人：杰哥
 */
public class YieldDemo {
    public static void main(String[] args) {
       Yield yield = new Yield();
        Thread thread1 = new Thread(yield,"a");
        Thread thread2 = new Thread(yield,"b");
        thread1.start();
        thread2.start();
    }
}

class Yield implements Runnable{

    public void run() {
        System.out.println(Thread.currentThread().getName()+"Start");
        Thread.currentThread().yield();
        System.out.println(Thread.currentThread().getName()+"End");
    }
}
