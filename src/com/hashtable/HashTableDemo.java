package com.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();

                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入查到的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTable
class HashTab{
    private EmpLinkedList[] empLinkedListsArray;
    private int size;//表示有多少个链表

    public HashTab(int size) {
        //初始化empLinkedListsArray
        empLinkedListsArray = new EmpLinkedList[size];
        this.size =size;
        //坑，需要分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据id得到该员工应该加入的链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListsArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表，遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i);
        }
    }

    public void findEmpById(int id){
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findEmpById(id);
        if(emp!=null){
            System.out.println("在第"+(empLinkedListNO+1)+"条链表中找到雇员id=" + id);
        }else {
            System.out.println("未找到该雇员");
        }
    }

    //编写散列函数，使用取模法
    public int hashFun(int id){
        return id % size;
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EMplinkedLIst
class EmpLinkedList{
    //头指针，执行第一个emp，英雌这个链表的head指向第一个Emp
    private Emp head;

    //添加雇员到链表
    //说明
    //1.假定，当添加雇员时，id自增长，即id的分配总是从小到大
    //因此 将该雇员直接加入到本链表的最后
    public void add(Emp emp){
        //添加第一个雇员
        if(head==null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用辅助指针帮助定位到最后
        Emp cur = head;
        while (true){
            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }

        cur.next = emp;
    }

    public void list(int no){
        if(head==null){
            System.out.println("第"+no+"条链表为空");
            return;
        }
        System.out.println("第"+no+"条链表的信息为");
        Emp cur =head;
        while (true){
            System.out.println("id:" + cur.id +";"+"姓名为："+ cur.name);
            if(cur.next==null){
                return;
            }
            cur = cur.next;
        }
    }

    //根据id查找雇员
    //找到就返回Emp没找到就返回null
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true){
            if(curEmp.id == id){
                break;
            }

            if(curEmp.next==null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;

        }
        return curEmp;
    }
}
