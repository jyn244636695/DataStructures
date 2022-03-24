package Algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {

        int[] w = {1,4,3};//物品重量
        int[] val = {1500,3000,2000};//物品价值，v[i]
        int m = 4;
        int n = val.length;

        //v[i][j]表示在前i个物品中装入容量为j的背包中的最大容量
        int[][] v = new int[n+1][m+1];

        int[][] path = new  int[n+1][m+1];

        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;//将第一行设置为0
        }

        //动态规划
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[1].length; j++) {//不处理第一列
                if(w[i-1]>j){//公式要减1
                    v[i][j]=v[i-1][j];
                }else {
                    //i从1开始的因此公式要从i-1
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放在背包的情况 不能直接使用上面的公式，需要使用if else
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //把当前记录到path
                        path[i][j]=1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        //输出最后放入哪些商品
        //遍历path，有冗余情况
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if(path[i][j]==1) {
//                    System.out.printf("第%d个商品放入背包\n", i);
//                }
//            }
//        }

        int i = path.length-1;//行的最大下表
        int j = path[0].length-1;//列的最大下标
        while (i>0&&j>0){//从后往前
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入背包\n", i);
                j-=w[i-1];//w[i-1]
            }
            i--;
        }
    }
}
