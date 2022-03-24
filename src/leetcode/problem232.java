package leetcode;

import java.util.Stack;

public class problem232 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }



}


class MyQueue {

    private Stack<Integer> temp1;


    public MyQueue() {
        this.temp1 = new Stack<>();


    }

    public void push(int x) {
        this.temp1.push(x);

    }

    public int pop() {
        Stack<Integer> temp2= new Stack<>();
        while(!this.temp1.empty()){
            temp2.push(this.temp1.pop());
        }
        int res = temp2.pop();
        while(!this.temp1.empty()){
            this.temp1.push(temp2.pop());
        }
        return res;
    }

    public int peek() {
        Stack<Integer> temp2= new Stack<>();
        while(!this.temp1.empty()){
            temp2.push(this.temp1.pop());
        }
        return temp2.peek();
    }

    public boolean empty() {
        return this.temp1.empty();
    }
}
