package com.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {

        int arr[]={13,7,8,3,29,6,1};
        Node node = creatHuffmanTree(arr);
        node.preOrder();
    }

    //编写前序遍历方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("节点为空，无法遍历");
        }
    }

    public static Node creatHuffmanTree(int[] arr){
        //第一步为了操作方便，遍历array
        //将arr的每个元素构成一个node
        //将node放入到arrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size()>1) {
            Collections.sort(nodes);

            System.out.println("node = " + nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);

            parent.left = left;
            parent.right = right;

            //从arraylist删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            //将parent加入到nodes
            nodes.add(parent);
            System.out.println("第一次处理后" + nodes);
        }
        return nodes.get(0);
    }
}

//创建节点类
//为了让node对象持续排序，collectuins集合排序
//让node实现Compareble接口
class Node implements Comparable<Node>{
    int value;//权值
    char c;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
