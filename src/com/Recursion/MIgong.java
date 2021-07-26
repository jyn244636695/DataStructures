package com.Recursion;

public class MIgong {
    public static void main(String[] args) {
        //先创建一个二维数组
        //地图
        int[][] map =new int[8][7];
        //使用1表示墙
        //先把上下全部置为1
        for (int i = 0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右置为1
        for (int i = 0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        map[2][2]=1;
        //map[2][1]=1;
        //map[1][2]=1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setway(map,1,1);
        System.out.println("小球路线");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 2. i j 表示从地图的哪个位置开始出发
     * 3.如果小球能到map【6】【5】则表示道路找到
     * 当为0时表示走过，当为1是走过；为2表示通路
     * 策略：下->右->上->左
     * @param map 表示地图
     * @param i 从哪个位置找
     * @param j
     * @return 找到就返回true
     */
    public static boolean setway(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                if(setway(map,i+1,j)){
                    return true;
                }else if(setway(map,i,j+1)){
                    return true;
                }else if(setway(map,i-1,j)){
                    return true;
                }else if(setway(map,i,j-1)){
                    return true;
                }else {
                    //该点是断路
                    map[i][j]=3;
                    return false;
                }
            }else {//如果map[i][j] != 0
                return false;
            }
        }
    }
}
