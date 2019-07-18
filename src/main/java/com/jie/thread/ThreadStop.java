package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：ThreadStop
 * 类 描 述：线程的停止方式
 * 1、线程自动运行结束
 * 2、人为干扰（不建议使用stop、destroy方法）
 * 创建时间：2019/7/17 21:44
 * 创 建 人：杰哥
 */
public class ThreadStop implements Runnable{

    private boolean stopFlag = true;

    private String name;

    public void run() {
        int i = 0;
        while(stopFlag){
            System.out.println(name+"-->"+i++);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadStop(String name) {
        this.name = name;
    }

    public void terminate(){
        this.stopFlag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop("线程杰");
        new Thread(threadStop).start();

        for (int i = 0; i < 99; i++) {
            if (i==88){
                threadStop.terminate();
                System.out.println("线程终止");
            }
            System.out.println("main -->"+i);
        }
    }
}
