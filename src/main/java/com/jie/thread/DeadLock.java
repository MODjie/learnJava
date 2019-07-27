package com.jie.thread;

/**
 * 项目名称：learnJava
 * 类 名 称：DeadLock
 * 类 描 述：死锁
 * 创建时间：2019/7/27 23:56
 * 创 建 人：杰哥
 */
public class DeadLock {
    public static void main(String[] args) {
        Girl girl1 = new Girl("张柏芝",1);
        Girl girl2 = new Girl("刘亦菲",2);

        new Thread(girl1).start();
        new Thread(girl2).start();
    }
}


class Lipstick {

}

class Mirror {

}

class Girl implements Runnable{

    private static Lipstick lipstick = new Lipstick();
    private static Mirror mirror = new Mirror();
    private String name;
    private int choice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Girl(String name, int choice) {
        this.name = name;
        this.choice = choice;
    }

    public void run() {
        this.makeUp2();
    }

    /**
     * 功能描述 : 化妆（嵌套同步导致死锁）
     * @author: 杰哥
     * @date 2019/7/28
     * @param
     * @return void
     */
    private void makeUp() {
        if (choice == 1){
            synchronized (mirror){
                System.out.println(name+"照镜子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lipstick){
                    System.out.println(name+"女生1涂口红");
                }
            }
        }else{
            synchronized (lipstick){
                System.out.println(name+"女生2涂口红");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mirror){
                    System.out.println(name+"女生2照镜子");
                }
            }
        }

    }

    /**
     * 功能描述 : 化妆（并列同步防止死锁）
     * @author: 杰哥
     * @date 2019/7/28
     * @param
     * @return void
     */
    private void makeUp2() {
        if (choice == 1){
            synchronized (mirror){
                System.out.println(name+"照镜子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lipstick){
                System.out.println(name+"涂口红");
            }
        }else{
            synchronized (lipstick){
                System.out.println(name+"涂口红");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (mirror){
                System.out.println(name+"照镜子");
            }
        }

    }
}