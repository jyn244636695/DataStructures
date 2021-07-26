package com.queue;

import java.util.Scanner;

/**
 * @author Yinan Jiang
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
	// write your code here
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key =' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.listQueue();
                    break;

                case 'a':
                    System.out.println("输出一个数");
                    int value =scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d/n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = arrayQueue.headQueue();
                        System.out.printf("取出的数据是%d/n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:

                    return;
            }
        }
        System.out.println("程序退出");
    }
}

/**使用数组模拟队列-编写一个ArryaQueue类*/
class ArrayQueue {
    /**表示数组最大容量*/
    private int maxSize;
    /**队列头*/
    private int front;
    /**队列尾*/
    private int rear;
    /**用于存放数据，模拟队列*/
    private int[] arr;

    /**创建队列的构造器*/
    public ArrayQueue(int arrMaxsize) {

        maxSize = arrMaxsize;
        arr = new int[arrMaxsize];

        //只想队列头部，front只想队列头的前一个数据
        front = -1;
        //指向队列尾，指向队列尾的数据
        rear = -1;
    }

    /**判断队列是否满*/
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**判断队列是否为空*/
    public boolean isEmpty() {
        return rear == front;
    }

    /**添加数据到队列*/
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满了不能添加数据");
            return;
        } else {
            rear++;
            arr[rear] = n;
        }
    }

    /**获取队列的数据*/
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    /**显示队列的所有数据*/
    public void listQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
        }
        return arr[front + 1];

    }

}
