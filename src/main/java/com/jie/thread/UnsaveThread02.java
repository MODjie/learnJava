package com.jie.thread;

public class UnsaveThread02 {
    public static void main(String[] args) {
        Account boyAccount = new Account("工商银行卡",100);
        People boy = new People(boyAccount,70,0,"杰");
        People girl = new People(boyAccount,90,0,"云");

        Thread boyThread = new Thread(boy);
        Thread grilThread = new Thread(girl);

        boyThread.start();
        grilThread.start();
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
    private Account account = new Account("聘礼",100);
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