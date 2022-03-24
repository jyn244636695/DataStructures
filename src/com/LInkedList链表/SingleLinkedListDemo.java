package com.LInkedList链表;

import java.util.Stack;

/**
 * @Author Yinan Jiang
 * @create 2021/5/27 20:29
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //测试单链表反转
        System.out.println("原来链表");
        singleLinkedList.list();

        System.out.println("反转");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("逆序打印");
        reversePrint(singleLinkedList.getHead());

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();
        //测试修改节点
        HeroNode newHeroNOde = new HeroNode(2,"小卢","玉麒麟~~");
        singleLinkedList.update(newHeroNOde);
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.list();

        //测试一下 求单链表中有效节点的个数
        System.out.println(getLength(singleLinkedList.getHead()));

        HeroNode res = findLastNode(singleLinkedList.getHead(),1);
        System.out.println("res=" + res);


    }
    //使用对战打印反向输出
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;
        }
        //创建一个站，将节点压入
        Stack<Object> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入
        while (cur !=null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() >0){
            System.out.println(stack.pop());
        }

    }

    //将单链表反转
    public static void reversetList(HeroNode head){
        if(head.next == null || head.next.next==null){
            return;
        }

        //定义一个辅助指针，帮助遍历
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表
        //每遍历一个就将其写出
        while (cur != null){
            next = cur.next;
            cur.next=reverseHead.next;//将cur的下一个节点指向新的链表的头
            reverseHead.next=cur;//将cur链接到新的链表
            cur = next;//让cur指向下一个
        }
        //将head.next执行reverseHead.next
        head.next = reverseHead.next;
    }

    //查找单链表中倒数第k个节点
    //1.编写一个方法接收head节点，同时接受一个index
    //2.index表示是倒数第index节点
    //3.先遍历，得到链表的总长度
    //4.得到size后，从链表的第一个开始遍历（size-index）个
    public static HeroNode findLastNode(HeroNode head, int index){
        if(head.next == null){
            return null;
        }
        //遍历得到节点的长度
        int size = getLength(head);
        //第二次遍历 size-index位置
        //先做一个index的校验
        if(index <=0 || index>size){
            return  null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    //方法获取单链表的节点的个数（如果是带头结点的链表，需求不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){//空链表
            return 0;
        }
        int length=0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
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