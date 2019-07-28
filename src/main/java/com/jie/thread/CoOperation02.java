package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：CoOperation02
 * 类 描 述：协作并发模式：生产者消费者模式：信号灯法，使用标志位判断
 * 创建时间：2019/7/28 11:48
 * 创 建 人：杰哥
 */
public class CoOperation02 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Thread(new Player(tv)).start();
        new Thread(new Watcher(tv)).start();
    }
}

class Watcher implements Runnable {

    private Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        this.watch();
    }

    private void watch() {
        for (int i = 0; i < 10; i++) {
            tv.watch();
        }
    }
}

class Player implements Runnable {

    private Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        this.perform();
    }

    private void perform() {
        for (int i = 0; i < 10; i++) {
            if (i%2==0){
                tv.play("唱歌");
            }else {
                tv.play("跳舞");
            }
        }
    }
}

class Tv {
    String voice;
    boolean flag = true;

    public synchronized void play(String voice){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("正在表演:"+voice);
        this.voice = voice;
        this.notifyAll();
        flag = !flag;
    }

    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众正在观看："+voice);
        this.notifyAll();
        flag = !flag;
    }
}
