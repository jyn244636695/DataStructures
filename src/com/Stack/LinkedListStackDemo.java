package com.Stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        LinkedListStack Stack = new LinkedListStack();
        Stack.push(hero1);
        Stack.show();
        Stack.push(hero2);
        Stack.show();
        Stack.pop();
        Stack.show();
    }
}

class LinkedListStack{
    private SingleLinkedList sl = new SingleLinkedList();

    public void pop(){
        sl.del(sl.getHead().next.no);
    }
    public void push(HeroNode heroNode){
        sl.add(heroNode);
    }

    public void show(){
        sl.list();
    }
}



/**定义SinggleLinkedList管理我们的英雄*/
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动
    private HeroNode head =  new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**添加节点到单项列表*/
    public void add(HeroNode heroNode){

        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
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
    }

    /**方式二，再添加英雄是，根据排名将印象加入指定位置*/
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此需要辅助变量
        //因为单链表，因此我们找到temp是位于添加位置的前一个结点
        HeroNode temp = head;
        //flag标志添加编号是否存在
        boolean flag =false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                //位置找到了就在这里插入
                break;
            }
            else if(temp.next.no == heroNode.no){
                //说明标号存在
                flag = true;
                break;
            }
            //后移，
            temp = temp.next;
        }
        //判断flag的值
        if(flag){
            System.out.printf("编号%d已经存在，不能加入\n",heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    /**修改节点的信息，根据编号来修改，no编号不能改*/
    public void update(HeroNode newHeroNode){
        //判断链表是否空
        if(head.next ==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据NO编号
        //定义一个辅助变量
        HeroNode temp = head.next;
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
    /**删除节点
     * 思路
     * 1.head不能东，因此需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.在比较让temp.no = 需要删除节点的no
     * */
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null) {//已经到链表最后
                break;
            }
            if(temp.next.no==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if(flag){
            //可以删除
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }

    /**显示链表*/
    public void list(){
        //判断链表是否为空
        if(head.next ==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
/**定义HeroNode，每个HeroNode对象就是一个节点*/
class HeroNode{
    public int no;
    public String name;
    public String nikename;
    public HeroNode next;

    public HeroNode(int hNo,String name,String nikename){
        this.no=hNo;
        this.name= name;
        this.nikename = nikename;
    }

    /**显示方便，重写toSTring*/
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }

}