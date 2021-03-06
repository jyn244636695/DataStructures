package Algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex;
    private int[][] dis;//保存从各个顶点出发到其他顶点的距离
    private int[][] pre;//保存到达目标定点的前去顶点

    /**
     * @param length 长度
     * @param matrix 领结矩阵
     * @param vertex 定点矩阵
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.dis = matrix;
        this.pre = new int[length][length];
        this.vertex = vertex;
        //pre初始化，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    //显示dis和pre数组
    public void show(){
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]]+" ");
            }
            System.out.println();

            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"->"+vertex[i]+"的最短路径是"+dis[k][i]+") ");
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;
        //对中间顶点遍历
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发
            for (int i = 0; i < dis.length; i++) {
                //到达j顶点
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k]+dis[j][k];//求出从i出发经过k到达j的距离
                    if(len<dis[i][j]){//如果len小于dis[i][j]则更新距离
                        dis[i][j]=len;
                        pre[i][j]=pre[k][j];//更新前驱节点
                    }
                }
            }
        }
    }
}
