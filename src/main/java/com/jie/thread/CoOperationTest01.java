package com.jie.thread;

import org.apache.commons.lang.ArrayUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：learnJava
 * 类 名 称：CoOperationTest01
 * 类 描 述：并发协作模式：生产者消费者模式实现一：管程法（生产者与消费者之间通过一个并发容器作为缓冲区来解耦）
 * 创建时间：2019/7/28 1:02
 * 创 建 人：杰哥
 */
public class CoOperationTest01 {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        Producer producer = new Producer(synContainer);
        Consumer consumer = new Consumer(synContainer);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

/**
 * 项目名称：learnJava
 * 类 名 称：Producer
 * 类 描 述：生产者
 * 创建时间：2019/7/28 1:02
 * 创 建 人：杰哥
 */
class Producer implements Runnable {
    private SynContainer synContainer;
    private int goodsNum;

    public Producer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    public void run() {
       while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           goodsNum++;
            synContainer.push(new Goods(goodsNum));
            System.out.println("生产的第" + goodsNum+"个商品");
        }
    }
}

/**
 * 项目名称：learnJava
 * 类 名 称：Consumer
 * 类 描 述：消费者
 * 创建时间：2019/7/28 1:02
 * 创 建 人：杰哥
 */
class Consumer implements Runnable {
    private SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    public SynContainer getSynContainer() {
        return synContainer;
    }

    public void setSynContainer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    public void run() {
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println("消费的第" + synContainer.pop().getNum()+"个商品");
        }
    }

}

/**
 * 项目名称：learnJava
 * 类 名 称：SynContainer
 * 类 描 述：并发容器（缓冲区）
 * 创建时间：2019/7/28 1:02
 * 创 建 人：杰哥
 */
class SynContainer {
    Goods[] goods = new Goods[10];
    private int count;

    /**
     * 功能描述 : 存储商品
     *
     * @param
     * @return void
     * @author: 杰哥
     * @date 2019/7/28
     */
    public synchronized void push(Goods prouceGoods) {
        if (count == goods.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        goods[count] = prouceGoods;
        count++;
        this.notifyAll();
    }

    /**
     * 功能描述 : 获取商品
     *
     * @param
     * @return void
     * @author: 杰哥
     * @date 2019/7/28
     */
    public synchronized Goods pop() {
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Goods returnGoods = goods[count];
        ArrayUtils.removeElement(goods, count);
        this.notifyAll();
        return returnGoods;
    }
}

/**
 * 项目名称：learnJava
 * 类 名 称：Goods
 * 类 描 述：商品
 * 创建时间：2019/7/28 1:02
 * 创 建 人：杰哥
 */
class Goods {
    private int num;

    public Goods(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Goods() {
    }
}