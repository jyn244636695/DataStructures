package com.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //现需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "mike");
        HeroNode node2 = new HeroNode(2, "james");
        HeroNode node3 = new HeroNode(3, "kobe");
        HeroNode node4 = new HeroNode(4, "durant");
        HeroNode node5 = new HeroNode(5, "sun");

        //先手动创建二叉树

        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);
//        System.out.println("前序");//12354
//        binaryTree.preOrder();
//
//        System.out.println("中");//21534
//        binaryTree.infixOrder();
//
//        System.out.println("后");//25431
//        binaryTree.postOrder();

        binaryTree.preOrder();

        binaryTree.delNOde(5);
        binaryTree.preOrder();


    }
}
//定义一个二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void delNOde(int no){
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }


    //秦绪查找
    public HeroNode prefind(int no){

        if(this.root != null){
            return this.root.prefind(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //中序查找
    public HeroNode infixfind(int no){

        if(this.root != null){
            return this.root.infixFind(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //中序查找
    public HeroNode postfind(int no){
        if(this.root != null){
            return this.root.postFind(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //前序遍历
    public void preOrder(){

        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}


class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    //如果删除的节点是叶子节点 就删除该系欸但
    //如果删除的是非叶子节点，就删除该子树
    public void delNode(int no){
        if( this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if( this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode1(int no) {

        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if(this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if(this.left != null) {
            this.left.delNode1(no);
        }
        //5.则应当向右子树进行递归删除
        if(this.right != null) {
            this.right.delNode1(no);
        }
    }
    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);
        //向左子树遍历
        if(this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后续遍历
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }

        if (this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode prefind(int no){
        System.out.println("find");
        if(this.no==no){
            return this;
        }
        HeroNode resNode = null;
        if(this.getLeft()!=null){
            resNode = this.getLeft().prefind(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.getRight()!=null){
            resNode = this.getRight().prefind(no);
        }

        return resNode;
    }

    public HeroNode infixFind(int no){

        HeroNode resNode = null;
        if(this.getLeft()!=null){
            resNode = this.getLeft().infixFind(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("find");
        if(this.no==no){
            return this;
        }

        if(this.getRight()!=null){
            resNode = this.getRight().infixFind(no);
        }
        return resNode;
    }

    public HeroNode postFind(int no){

        HeroNode resNode = null;
        if(this.getLeft()!=null){
            resNode = this.getLeft().postFind(no);
        }
        if(resNode!=null){
            return resNode;
        }

        if(this.getRight()!=null){
            resNode = this.getRight().postFind(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("find");
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}

