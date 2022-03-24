package Algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                        /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        //创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));//未排序
        kruskalCase.sortEdge(edges);
        System.out.println("排序后="+Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化定点数和边的个数
        int vlen = vertexs.length;

        //初始化vertexs,复制拷贝
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index = 0;
        int[] ends = new int[edgeNum];//用于保存最小生成树中的每个顶点是否在最小生成树的终点
        //创建结果数组
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合，一共12边
        EData[] edges = getEdges();
        System.out.println("获取图的边的集合="+Arrays.toString(edges)+"共"+edges.length+"条");
        //按照边的权值大小排序（从小到大）
        sortEdge(edges);

        //遍历edges数组，将边添加到最小生成树中，判断是准备加入到的边是否形成了回路，如果美欧，就加入rets
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第2个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有的最小生成树中的终点
            int m =getEnd(ends,p1);
            //获取p2这个顶点在最小生成树中的终点
            int n = getEnd(ends,p2);
            if(m!=n){
                ends[m]=n;//设置m在已有最小生成树中的终点
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为=");

        //统计并打印最小生成树,输出rets
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    //打印领结矩阵
    public void print(){
        System.out.println("领结矩阵为");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%15d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }
    //对边进行排序

    /**
     * 对边进行排序
     * @param edges 边的集合
     */
    private void sortEdge(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length -1-i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值，比如“A”
     * @return 返回定点对应的下表，找不到返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边放到EData[] 中，后面需要遍历该数组
     * 通过matrix 邻接矩阵来获取
     * EData[] [[A,B,12]]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    edges[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j] );
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点，ends实在遍历过程中逐步形成
     * @param i 表示传入顶点的下标
     * @return 返回下表为i的点的终点下标，一会回头还要理解
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}

//创建一个类EData，对象实例就表示一条边
class EData{
    char start;//边的一个点
    char end;//另外一个点
    int weight;//边的权值

    //构造器
    public EData(char start,char end,int weight){
        this.start =start;
        this.end=end;
        this.weight=weight;
    }
    //重写toString方法

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}