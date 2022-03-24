package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//储存顶点集合
    private int[][] edges;
    private int numOfEdges;//表示边的数目

    //定义boolean【】，记录某个节点是否被访问
    private boolean[] isVisited ;
    public static void main(String[] args) {
        int n = 8;
//        String Vertex[] = {"A","B","c","D","E"};
        String Vertex[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加项目
        for (String vertex : Vertex) {
            graph.insertVertes(vertex);
        }

//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();

        //测试一把
        System.out.println("深度遍历");
        graph.dfs();

        System.out.println("广度优先");
//        graph.bfs();
    }
    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //返回第一个领接结点的下表w

    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的小标来获取下一个邻接结点的
    // v1是访问过的，v2是找到的节点需要访问其下一个节点
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    //广度优先
    private void bfs(boolean[] isVisited,int i){
        int u;//表示对应的头节点的下表
        int w;//邻接节点的下表

        //队列
        LinkedList<Integer> queue = new LinkedList<Integer>();
        System.out.print(getValueByIndex(i)+"=>");
        //标记为已访问
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()){
            //去除队列的头节点下表
            u = queue.removeFirst();
            //得到第一个临界点的下标w
            w = getFirstNeighbor(u);
            while (w!=-1) {
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w)+"=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前一个结点，找w后面的下一个邻结点
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }
    //遍历所有结点都进行广度优先
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //深度优先遍历
    private void dfs(boolean[] isVisited,int i){
        //首先访问该节点
        System.out.print(getValueByIndex(i)+"->");
        //将该节点设置为已经访问过
        isVisited[i]  = true;
        //查找节点i的邻接节点w
        int w = getFirstNeighbor(i);
        while (w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }

            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载,遍历所有的节点，并进行遍历
    public void dfs(){
        //遍历所有的节点进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    //常用方法
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回节点对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1，v2的权值
    public int getweight(int v1,int v2){
        return edges[v1][v2];
    }

    //插入顶点
    public void insertVertes(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    /**
     *
     * @param v1 表示点的下标，即第几个顶点
     * @param v2 第二个顶点的下标
     * @param weight 表示
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
