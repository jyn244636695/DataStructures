package com.tree.ThreadBinaryTree;

public class ThreadeBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一下中序线索二叉树
        HeroNode root = new HeroNode(1, "mike");
        HeroNode node2 = new HeroNode(3, "james");
        HeroNode node3 = new HeroNode(6, "kobe");
        HeroNode node4 = new HeroNode(8, "durant");
        HeroNode node5 = new HeroNode(10, "sun");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树后面递归创建
        root.setRight(node3);
        root.setLeft(node2);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadeBinaryTree threadeBinaryTree = new ThreadeBinaryTree();
        threadeBinaryTree.setRoot(root);
        threadeBinaryTree.threadNodes1(root);

        //测试：以10好测试
//        HeroNode node5Left = node6.getLeft();
//        System.out.println(node5Left);
//        HeroNode node5Right = node6.getRight();
//        System.out.println(node5Right);

        System.out.println("使用线索化遍历");
        threadeBinaryTree.threadpostList(root);
    }
}

//定义实现了线索化功能的二叉树
class ThreadeBinaryTree{
    private HeroNode root;

    //为了使先线索化，需要创建一个指向前驱节点的指针
    //prenode保留前一个结点
    private HeroNode prenode=null;
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void threadNodes(){
        this.threadNodes(root);
    }
    //后序遍历线索化二叉树
    public void threadpostList(HeroNode node){

        if(node.getLeftType()!=1) {
            threadpostList(node.getLeft());
        }

        if(node.getRightType()!=1){
            threadpostList(node.getRight());
        }

        System.out.println(node);
        return;
    }

    //中序遍历线索化二叉树
    public void threadList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;

        while (node != null){
            //循环找到lefttype==1的节点，第一个找到的就是8节点
            //后面随着遍历而变化，当lefttype==1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);

            //如果当前节点指向的是后继节点，就一直输出
            while (node.getRightType() == 1){
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }

    }



    //编写对二叉树进行中序线索化的方法

    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadNodes(HeroNode node){
        if(node == null){
            return;
        }
        //1.先线索化左子树
        threadNodes(node.getLeft());
        //2.线索化当前节点[]
        //处理当前节点的前驱节点
        if(node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(prenode);
            node.setLeftType(1);
        }
        //处理节点的后继节点
        if(prenode != null&&prenode.getRight()==null){
            //让前驱节点的指针指向当前节点
            prenode.setRight(node);
            prenode.setRightType(1);
        }
        //没处理一个节点后，让当前节点是下一个节点的前驱节点
        prenode = node;
        //3.线索化右子树
        threadNodes(node.getRight());
    }
    //编写对二叉树进行后序线索化的方法

    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadNodes1(HeroNode node){
        if(node == null){
            return;
        }

        //1.先线索化左子树
        threadNodes1(node.getLeft());
        //2.线索化右子树
        threadNodes1(node.getRight());
        //2.线索化当前节点[]
        //处理当前节点的前驱节点
        if(node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(prenode);
            node.setLeftType(1);
        }
        //处理节点的后继节点
        if(prenode != null&&prenode.getRight()==null){
            //让前驱节点的指针指向当前节点
            prenode.setRight(node);
            prenode.setRightType(1);
        }
        prenode = node;

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
//创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    //1.如果leftType == 0 表示只想的是左子树，如果是1则表示只想前驱节点
    //2.如果rightType == 0 表示指向是右子树，如果1表示只想后继节点
    private  int leftType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    private  int rightType;

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
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
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
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode1(no);
        }
        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode1(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        //向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后续遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode prefind(int no) {
        System.out.println("find");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().prefind(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().prefind(no);
        }

        return resNode;
    }

    public HeroNode infixFind(int no) {

        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().infixFind(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("find");
        if (this.no == no) {
            return this;
        }

        if (this.getRight() != null) {
            resNode = this.getRight().infixFind(no);
        }
        return resNode;
    }

    public HeroNode postFind(int no) {

        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().postFind(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.getRight() != null) {
            resNode = this.getRight().postFind(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("find");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}