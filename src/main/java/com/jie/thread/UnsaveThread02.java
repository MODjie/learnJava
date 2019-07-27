package com.jie.thread;

public class UnsaveThread02 {
    public static void main(String[] args) {
        Account boyAccount = new Account("工商银行卡",100);
        People boy = new People(boyAccount,100,0,"男1");
        People girl = new People(boyAccount,90,0,"女1");
        People girl2 = new People(boyAccount,90,0,"女2");
        People girl3 = new People(boyAccount,90,0,"女3");
        People girl4 = new People(boyAccount,90,0,"女4");
        People girl5 = new People(boyAccount,90,0,"女5");

        Thread boyThread = new Thread(boy);
        Thread grilThread = new Thread(girl);
        Thread gril2Thread = new Thread(girl2);
        Thread gril3Thread = new Thread(girl3);
        Thread gril4Thread = new Thread(girl4);
        Thread gril5Thread = new Thread(girl5);

        boyThread.start();
        grilThread.start();
        gril2Thread.start();
        gril3Thread.start();
        gril4Thread.start();
        gril5Thread.start();

    }
}

/**
 * 账户
 */
class Account {
    /**
     * 名字
     */
    private String name;
    /**
     * 余额
     */
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
}

/**
 * 取钱的人
 */
class People implements Runnable{
    private Account account;
    /**
     * 取出来的钱
     */
    private int takeMoneyFromAccount;
    /**
     * 口袋剩下的钱
     */
    private int packetMoney;
    /**
     * 取钱的人
     */
    private String name;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getTakeMoneyFromAccount() {
        return takeMoneyFromAccount;
    }

    public void setTakeMoneyFromAccount(int takeMoneyFromAccount) {
        this.takeMoneyFromAccount = takeMoneyFromAccount;
    }

    public int getPacketMoney() {
        return packetMoney;
    }

    public void setPacketMoney(int packetMoney) {
        this.packetMoney = packetMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public People(Account account, int takeMoneyFromAccount, int packetMoney,String name) {
        this.account = account;
        this.takeMoneyFromAccount = takeMoneyFromAccount;
        this.packetMoney = packetMoney;
        this.name = name;
    }

    public void run() {
        drawingMoney();
    }

    public void drawingMoney(){
        //当账户余额不足时就直接退出，不用进入同步块去判断了，提高性能
        if (this.account.getBalance()<=0){
            System.out.println("账户余额不足");
            return;
        }
        synchronized (this.account){
            if (this.account.getBalance()-this.takeMoneyFromAccount<0){
                System.out.println(this.getName()+"取钱时账户余额不足");
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.account.setBalance(this.account.getBalance() - this.takeMoneyFromAccount);
            this.packetMoney += this.takeMoneyFromAccount;
            System.out.println("账户余额为"+account.getBalance());
            System.out.println(this.getName()+"口袋剩下的钱"+this.packetMoney);
        }
    }
}