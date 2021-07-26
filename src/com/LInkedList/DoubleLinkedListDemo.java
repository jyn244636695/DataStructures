package com.LInkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    private HeroNode2 head =  new HeroNode2(0,"","");
    public HeroNode2 getHead() {
        return head;
    }
    /**
     * 显示链表，遍历
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
    /**
     * 添加节点到双项列表
     * */
    public void add(HeroNode2 heroNode){

        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            if(temp.next==null){
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当推出循环时，就指向了链表最后
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    /**
     * 修改节点的内容
     * 与单向列表一样
     */
    public void update(HeroNode2 newHeroNode){
        //判断链表是否空
        if(head.next ==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据NO编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null){
                break;//到链表最后，遍历结束
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否要找到修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nikename = newHeroNode.nikename;
        }else {
            System.out.printf("没有找到编号%d 的节点，不能修改\n",newHeroNode.no);
        }
    }
    /**双向链表删除节点
     * 思路
     * 1.对于双向列表可以直接找到这个节点
     * 2.找到后自我删除
     * */
    public void del(int no){
        //判断当前链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null) {//已经到链表最后
                break;
            }
            if(temp.no==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if(flag){
            //可以删除
            temp.pre.next=temp.next;
            if(temp.next==null){
                return;
            }
            temp.next.pre=temp.pre;
        }else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nikename;
    public HeroNode2 next;//
    public HeroNode2 pre;

    public HeroNode2(int hNo, String name, String nikename) {
        this.no = hNo;
        this.name = name;
        this.nikename = nikename;
    }

    /**
     * 显示方便，重写toSTring
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }



}
