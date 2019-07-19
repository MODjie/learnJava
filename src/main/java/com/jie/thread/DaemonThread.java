package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：DaemonThread
 * 类 描 述：守护线程：为用户线程服务的线程，当用户线程结束时，计算守护线程没运行结束也会停止运行
 * 创建时间：2019/7/19 20:50
 * 创 建 人：杰哥
 */
public class DaemonThread {
    public static void main(String[] args) {
        new Thread(new UserThread()).start();
        Thread daemonThread = new Thread(new MyDaemon());
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}

class MyDaemon implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("我是守护线程，我为用户线程做服务");
        }
    }
}

class UserThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <365*100 ; i++) {
            System.out.println("我是用户线程，我一停止，守护线程就得停止");
        }
    }
}