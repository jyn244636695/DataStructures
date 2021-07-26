package com.Recursion;

public class Queue8 {
    //定义max表示有多少个皇后
    int max = 8;
    //定义数组array，保存皇后防止的魏国
    int[] array = new int[max];
    static int count = 0;
    static int jdcount = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("总共有"+count);
        System.out.println("总共判断有"+jdcount);
    }

    //输出位置
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    //当防止第n个皇后是，就检测是否和前面已经摆放的皇后冲突。
    /**
     *
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n){
        jdcount++;
        for (int i = 0; i < n; i++) {
            if(array[n]==array[i]+n-i||array[n]==array[i]||array[n]==array[i]+i-n){
                return false;
            }
        }
        return true;
    }

    //放第n个皇后
    //特别注意：check是每一次递归式，进入check中都有for (int i = 0; i < max; i++)
    private void check(int n){
        if (n == max){
            print();
            count++;
            return;
        }else {
            for (int i = 0; i < max; i++) {
                array[n]=i;
                if(judge(n)){//判断是否冲突，若不冲突则放第n+1个
                    check(n+1);
                }
            }
        }
    }
}
