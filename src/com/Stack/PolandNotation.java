package com.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //将中缀表达式转换为后缀表达式
        //说明将 1+(2+3)*4)-5 转换成 1 2 3 + 4 * + 5 -
        //2.因为直接扫描str不方便，因此将str转换为中缀的list
        String Expression = "1+(2+3)*4-5";
        List<String> list = toInfixExpressionList(Expression);
        System.out.println("中缀表达式"+ list);
        List<String> list1 = toSuffixExperssionList(list);
        System.out.println("转换后后缀表达式" + list1);
        /*
        //先创建一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 /
        String suffixExpression = "30 4 + 5 * 6 -";
        //1.先将suffixExpression放入一个ArrayList里
        //2.将ArrayList传递一个方法，遍历 配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);*/
        
       int res = calculate(list1);
       System.out.println(res);
    }
    //将中缀表达式转换为后缀表达式
    public static List<String > toSuffixExperssionList(List<String> list) {
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<>();
        for (String item : list) {
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将 （ 弹出 S1栈，消除 （
            } else {
                //当item运算符的优先级小于等于栈顶运算符的优先级,将s1栈顶运算符弹出并加入s2
                while (s1.size() != 0 && Operation.getValue(s1.peek())  >=  Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //需要将item压入栈
                s1.push(item);
            }
        }
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }


    //将中缀表达式转换为对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i =0;//指针，用来遍历中缀表达式字符串
        String str;
        char c;//每遍历到一个字符，就放入c
        do{
            //如果c是一个非数字,就需要加入到ls中
            if((c = s.charAt(i))<48||(c = s.charAt(i)) >= 57){
                ls.add(String.valueOf(c));
                i++;
            }else {
                str = "";
                while (i<s.length()&&(c = s.charAt(i))>=48&&(c = s.charAt(i))<=57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i <s.length());
        return ls;
    }
    //将逆波兰表达式，一次将数据和运算符放入ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : s) {
            list.add(ele);
        }
        return list;
    }
    
    public static int calculate(List<String > ls){

        Stack<String> stack = new Stack<String>();

        for (String item : ls) {
            if(item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //弹出两个数并运算,在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 +num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
//编写一个返回运算符对应优先级的类 Operation
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    public static int getValue(String oper){
        int result = 0;
        switch (oper){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("输入错误");
                break;
        }
        return result;
    }

}