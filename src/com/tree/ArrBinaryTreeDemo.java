package com.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4 , 5 , 6 , 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.infixOrder(0);
    }
}

class ArrBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public void preOrder(){{
        this.preOrder(0);
    }}
    //编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     *编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 表示数组的下标
     */
    public void preOrder(int index){
        //如果数组为空，或者arr.length=0
        if(arr == null&&arr.length == 0){
            System.out.println("数组为空，不能遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if(2*index +1<arr.length) {
            preOrder(2 * index + 1);
        }
        if(2*index +2<arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(int index){
        if(arr == null&&arr.length == 0){
            System.out.println("数组为空，不能遍历");
        }

        //向左递归遍历
        if(2*index +1<arr.length) {
            infixOrder(2 * index + 1);
        }

        //输出当前元素
        System.out.println(arr[index]);

        if(2*index +2<arr.length) {
            infixOrder(2 * index + 2);
        }

    }
}
