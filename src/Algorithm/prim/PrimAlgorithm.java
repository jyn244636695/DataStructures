package Algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F','G'};
        int vertex = data.length;
        //领结矩阵用二维数组表示 10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph mGraph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.creatGrapg(mGraph, vertex, data, weight);
        //输出
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);
    }
}

//创建最小生成树->村庄的图
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void creatGrapg(MGraph graph, int verxs, char data[], int[][] weight) {

        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写一个Prim算法，得到一个最小生成树

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成'A'->0 'B'->1...
     */
    public void prim(MGraph graph, int v) {
        //标记顶点是否被访问过
        boolean[] visited = new boolean[graph.verx];
        for (int i = 0; i < graph.verx; i++) {
            visited[i] = false;
        }
        visited[v] = true;
        //记录两个顶点的小标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将其初始化为一个大数
        for (int k = 1; k < graph.verx; k++) {//因为有 graph.verxs顶点，普利姆算法结束后，有 graph.verxs-1边
            //确定每一次生成的子图
            for (int i = 0; i < graph.verx; i++) {//i结点表示北方问过的结点
                for (int j = 0; j < graph.verx; j++) {//表示还未访问过的结点
                    if (visited[i] == true && visited[j] == false && graph.weight[i][j] <= minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边最小
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值"+minWeight );
            //将两个结点表示为已经访问
            visited[h2]= true;
            //minWeight为最大值
            minWeight = 10000;
        }
    }

}

class MGraph {
    int verx;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是领结矩阵

    public MGraph(int verxs) {
        this.verx = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
