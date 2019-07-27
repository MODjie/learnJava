package com.jie.thread;

/**
 * 电影院买票，座位随机
 */
public class SynCinemaTest01 {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(9,"杰哥影院");
        Customer customer1 = new Customer(3,cinema);
        Customer customer2 = new Customer(3,cinema);
        Customer customer3 = new Customer(3,cinema);
        Customer customer4 = new Customer(3,cinema);

        new Thread(customer1).start();
        new Thread(customer2).start();
        new Thread(customer3).start();
        new Thread(customer4).start();
    }
}

class Cinema{
    private int remainingSeats;
    private String name;

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cinema(int remainingSeats, String name) {
        this.remainingSeats = remainingSeats;
        this.name = name;
    }
}

class Customer implements Runnable{
    private int bookSeatNum;
    private Cinema cinema;

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getBookSeatNum() {
        return bookSeatNum;
    }

    public void setBookSeatNum(int bookSeatNum) {
        this.bookSeatNum = bookSeatNum;
    }

    public Customer(int bookSeatNum, Cinema cinema) {
        this.bookSeatNum = bookSeatNum;
        this.cinema = cinema;
    }

    @Override
    public void run() {
        if (this.cinema.getRemainingSeats()<=0){
            return;
        }
        synchronized (cinema){
            if (this.cinema.getRemainingSeats()<=0){
                return;
            }
            this.cinema.setRemainingSeats(this.cinema.getRemainingSeats()-bookSeatNum);
            System.out.println("剩余座位为:"+this.cinema.getRemainingSeats());
        }
    }
}