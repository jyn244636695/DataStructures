package com.queue;

import java.util.Scanner;

/**
 * @Author Yinan Jiang
 * @create 2021/5/22 21:42
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
// write your code here
        CircleArray arrayQueue = new CircleArray(4);
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
class CircleArray {
    /**表示数组最大容量*/
    private int maxSize;
    /**队列头,front指向队列的第一个元素*/
    private int front;
    /**队列尾，rear只想队列的最后一个元素的后一位*/
    private int rear;
    /**用于存放数据，模拟队列*/
    private int[] arr;

    /**创建队列的构造器*/
    public CircleArray(int arrMaxsize) {

        maxSize = arrMaxsize;
        arr = new int[arrMaxsize];

        //只想队列头部，front只想队列头的第一个数据
        front = 0;
        //指向队列尾，rear只想队列的最后一个元素的后一位
        rear = 0;
    }

    /**判断队列是否满*/
    public boolean isFull() {
        return (rear+1)%maxSize == front;
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

            arr[rear] = n;
            rear = ( rear + 1 ) % maxSize;
        }
    }

    /**获取队列的数据*/
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        //需要分析出front是只想队列的第一个元素
        //2.吧front的值保存
        //2.把front后裔，取模
        //3.将临时保存变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**显示队列的所有数据*/
    public void listQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
        }
        //从front开始遍历，便利多少个元素

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**求出当前数列的有效数据*/
    public int size(){
        return (rear + maxSize -front) % maxSize;
    }
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
        }
        return arr[front];

    }

}
