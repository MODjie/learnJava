package com.jie.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影院买票，自选座位
 */
public class SynCinemaTest02 {
    public static void main(String[] args) {
        List<Integer> seats = new ArrayList<>();
        seats.add(1);
        seats.add(2);
        seats.add(5);
        seats.add(7);
        seats.add(9);

        Cinema02 cinema02 = new Cinema02(seats,"杰哥影院");
        List<Integer> bookSeats01 = new ArrayList<>();
        bookSeats01.add(1);
        bookSeats01.add(2);

        List<Integer> bookSeats02 = new ArrayList<>();
        bookSeats02.add(6);
        bookSeats02.add(7);
        bookSeats02.add(9);
        Customer02 customer1 = new Customer02(bookSeats01,cinema02);
        Customer02 customer2 = new Customer02(bookSeats02,cinema02);

        new Thread(customer1).start();
        new Thread(customer2).start();
    }
}

class Cinema02{
    private List<Integer> remainingSeats;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(List<Integer> remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public Cinema02(List<Integer> remainingSeats, String name) {
        this.remainingSeats = remainingSeats;
        this.name = name;
    }
}

class Customer02 implements Runnable{
    private List<Integer> bookSeatNum;
    private Cinema02 cinema02;


    public List<Integer> getBookSeatNum() {
        return bookSeatNum;
    }

    public void setBookSeatNum(List<Integer> bookSeatNum) {
        this.bookSeatNum = bookSeatNum;
    }

    public Cinema02 getCinema02() {
        return cinema02;
    }

    public void setCinema02(Cinema02 cinema02) {
        this.cinema02 = cinema02;
    }

    public Customer02(List<Integer> bookSeatNum, Cinema02 cinema02) {
        this.bookSeatNum = bookSeatNum;
        this.cinema02 = cinema02;
    }

    @Override
    public void run() {
        if (this.cinema02.getRemainingSeats().size()<=0){
            return;
        }
        synchronized (cinema02){
            if (this.cinema02.getRemainingSeats().size()<=0){
                return;
            }
            System.out.println("剩余座位："+this.cinema02.getRemainingSeats());
            List<Integer> copy = new ArrayList<>();
            copy.addAll(this.cinema02.getRemainingSeats());
            copy.removeAll(this.bookSeatNum);
            if (this.cinema02.getRemainingSeats().size()-copy.size()==this.bookSeatNum.size()){
                this.cinema02.setRemainingSeats(copy);
                System.out.println("抢到座位:"+this.bookSeatNum);
                return;
            }
            System.out.println("座位不够");
        }
    }
}