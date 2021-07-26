package com.tree;

import java.util.Arrays;

public class Heapsort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9,-1,90,-999,1000};
        heapSort(arr);
    }
    //编写一个堆排序方法
    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序");
        //分布完成
        for (int i = arr.length/2 - 1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }


        System.out.println("first"+ Arrays.toString(arr));
        for (int i = arr.length-1; i > 0 ; i--) {
            //交换
            temp = arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0, i);
        }
        System.out.println("2"+ Arrays.toString(arr));

    }

    //将一个二叉树调整成一个大顶堆

    /**
     * 功能： 完成将以i指向的非叶子节点的树调整成大顶堆
     * @param arr 待调整数组
     * @param i 表示非叶子节点的索引
     * @param lenghth    对多少个元素进行调整
     */
    public static void adjustHeap(int arr[],int i,int lenghth){

        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        for(int k = i * 2 + 1; k < lenghth; k = k * 2 + 1) {
            if(k+1 < lenghth && arr[k] < arr[k+1]) { //说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            if(arr[k] > temp) { //如果子结点大于父结点
                arr[i] = arr[k]; //把较大的值赋给当前结点
                i = k; //!!! i 指向 k,继续循环比较
            } else {
                break;//!
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
