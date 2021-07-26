package com.Stack;

public class Calculator {
    public static void main(String[] args) {
        //根据思路
        String experssion ="70+20*6-20";
        ArrayStack2 numstack = new ArrayStack2(10);
        ArrayStack2 operstack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepnum = "";

        while(true){
            ch = experssion.substring(index,index+1).charAt(0);
            if(operstack.isOper(ch)){
                //判断是否为空
                if(!operstack.isEmpty()){
                    //处理
                    if (operstack.priority(ch)>operstack.priority(operstack.pick())){
                        operstack.push(ch);
                    }else{
                        num1 = numstack.pop();
                        num2 = numstack.pop();
                        oper = operstack.pop();
                        res = numstack.cal(num1,num2,oper);
                        numstack.push(res);
                        operstack.push(ch);
                    }
                }else {
                    operstack.push(ch);
                }
            } else {

                //numstack.push(ch-48);
                //当处理多位数时，不能一看见数就入栈
                //需要向expression的表达式index后再看一位，如果是数就进行扫描，是符号就入栈
                keepnum += ch;

                //如果ch是expression的最后一位就直接入栈
                if (index == experssion.length() - 1) {
                    numstack.push(Integer.parseInt(keepnum));
                } else {
                    //判断下一个字符是不是数字如果是数字则继续扫描如果是运算符则入栈
                    if (operstack.isOper(experssion.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符则入栈
                        numstack.push(Integer.parseInt(keepnum));
                        keepnum = "";
                    }
                }
            }
            index++;
            if(index >= experssion.length()){
                break;
            }

        }

         while (true){
            if(operstack.isEmpty()){
                break;
            }else {
                num1 = numstack.pop();
                num2 = numstack.pop();
                oper = operstack.pop();
                res = numstack.cal(num1,num2,oper);
                numstack.push(res);
            }
        }
        System.out.printf("表达式%s=%d",experssion,numstack.pop());
    }
}

class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] =value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;

    }
    //遍历
    public void list(){
        if(isEmpty()){
            System.out.println("没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);

        }
    }

    //返回运算符的优先级,返回的数字越大 优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '-' || oper == '+' ){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val){
        return val=='+' || val=='-' || val=='/' || val == '*';
    }

    //计算方法
    public int cal(int num1,int num2 , int oper){
        int val = 0;
        switch (oper){
            case '+':
                val = num1 +num2;
                break;
            case '-':
                val = num2 -num1;
                break;
            case '/':
                val = num2/num1;
                break;
            case '*':
                val = num2*num1;
                break;
            default:
                break;
        }
        return val;
    }
    public int pick(){
        return stack[top];
    }
}

