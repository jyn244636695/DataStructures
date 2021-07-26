package com.LInkedList;

public class Josepfu {
    public static void main(String[] args) {
        //测试构建和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试出圈是否正确
        circleSingleLinkedList.exitBoy(1,2,5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList{
    //创建一个first节点
    private Boy first = new Boy(-1);
    public void addBoy(int nums){
        //nums 做数据校验
        if (nums <1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        //for来创建
        for (int i = 1; i <= nums; i++) {
            //更具标号，创建节点

            Boy boy = new Boy(i);
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy = first;
            }
            else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy(){
        //判断是否为空
        if(first==null){
            System.out.println("empty");
            return;
        }
        //因为first不能动
        Boy curboy = first;
        while(true){
            System.out.printf("小孩的标号%d\n",curboy.getNo());
            if(curboy.getNext()==first){
                break;
            }
            curboy = curboy.getNext();
        }
    }

    /**
     * 计算出拳小孩
     * @param stratnum
     * @param countnum
     * @param nums
     */
    public void exitBoy(int stratnum,int countnum,int nums){
        //判断是否为空
        if(first==null|| stratnum <1 || stratnum>1){
            System.out.println("参数输入有误");
            return;
        }
        Boy helper = first;
        while (true){
            if(helper.getNext()==first){
                break;
            }
            helper = helper.getNext();
        }


        for (int i =0; i < stratnum -1; i++) {
            first = first.getNext();
            helper = helper.getNext();
            }
        //当小孩报数是，让first和help同时移动m-1次
        while (true){
            if(helper == first){//说明权重只有一人
                break;
            }
            //让first和helper 同时移动countnum-1次
            for (int i = 0; i < countnum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在权重的编号小号%d\n",helper.getNo());
    }
}

//创建一个boy类
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
