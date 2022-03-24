package com.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = {10, 11,7, 6 ,8, 9 };

        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("未平衡处理前");
        System.out.println(avlTree.getRoot().height());
        System.out.println("左子树高度"+avlTree.getRoot().leftHeight());
        System.out.println("右子树高度"+avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

class AVLTree{
     private Node root;


    //查找要删除的节点
    public Node search(int value){
        if(root==null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //编写方法
    //1.返回以Node为根节点的二叉排序树的最小节点的值
    //2.删除以node为根节点的二叉排序树的最小节点
    /**
     *
     * @param node 传入结点 当作二叉排序树的根节点
     * @return 返回以Node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.left!=null){
            target = target.left;
        }
        //这时target就指向最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }
    //删除节点
    public void delNode(int value){
        if(root==null){
            return;
        }else {
            Node targetNode = search(value);
            if(targetNode==null){
                return;
            }
            //如果发现targetNode没有父节点，或者只有一个节点
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }

            //找到父节点
            Node parent = searchParent(value);
            //如果删除的是叶子节点
            if(targetNode.left==null&&targetNode.right==null){
                if(parent.left!=null&&value==parent.left.value){
                    parent.left=null;
                }else if(parent.right!=null&&value == parent.right.value){
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){//删除两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else {//删除只有一棵子树的节点
                if(targetNode.left!=null){//如果targetNode右左子节点
                    if(parent!=null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {
                    if(parent!=null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
    //添加节点
    public void add(Node node){
        if(root==null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
    }


    //遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
}
//创建节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right  == null){
            return 0;
        }
        return right.height();
    }

    //返回以该节点的高度，以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null? 0 : left.height() ,right == null?0:right.height())+1;
    }

    //左旋转的方法
    private void leftRotate(){
        //创建新节点
        Node newnode = new Node(value);

        newnode.left = left;
        newnode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树，设置成右子树的右子树
        right=right.right;
        //把当前节点的左子节点，设置成新节点
        left = newnode;
    }

    //右旋转的方法
    private void rightRotate(){
        //创建新节点
        Node newnode = new Node(value);

        newnode.right = right;
        newnode.left = left.right;
        value = left.value;
        left = left.left;
        right = newnode;
    }

    //查找要删除的节点
    /**
     *
     * @param value 希望删除的结点值
     * @return 如果找到就返回该节点，否则就返回null
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;
            }
           return this.left.search(value);
        }else {
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除的父节点

    /**
     *
     * @param Value 要查找结点的值
     * @return 返回要删除的节点的父节点，如果没有就返回null
     */
    public Node searchParent(int Value){
        if(this.left!=null&&this.left.value==Value||this.right!=null&&this.right.value==Value){
            return this;
        }else {
            //如果要查找的这个值小于当前的结点值，并且当前的左子节点不为空
            if(Value <this.value&&this.left!=null){
                return this.left.searchParent(Value);
            }else if(Value >=this.value&&this.right!=null){
                return this.right.searchParent(Value);
            }else {
                return null;//没找到父节点
            }
        }
    }

    //递归添加节点，满足二叉排序树
    public void add(Node node){
        if(node==null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的关系
        if(node.value < this.value){
            if(this.left==null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {//添加节点的值大于当前节点的值
            if(this.right==null){

                    this.right=node;
                }else {
                    //向右递归
                    this.right.add(node);
                }
            }

            //当添加完一个节点后，如果有字数的高度大于左子树高度+1
        if (rightHeight() - leftHeight() > 1) {
            //如果右子树的左子树的高度大于它的右子树的高度
                if(right != null && right.rightHeight()<right.leftHeight()){
                    //先对当前节点的右节点进行右旋转
                    right.rightRotate();
                    //对当前节点进行左旋转
                    leftRotate();
                }else {
                    leftRotate();
                }
                return;
        }
        if (leftHeight() - rightHeight() > 1) {
            //如果左子树的右子树的高度大于它的左子树的高度
                if(left != null && left.rightHeight()>left.leftHeight()){
                    //先对当前节点的左节点进行左旋转
                    left.leftRotate();
                    //在对当前节点进行右旋转
                    rightRotate();
                }else {
                    rightRotate();
                }
        }
    }



    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right!=null){
            this.right.infixOrder();
        }
    }
}