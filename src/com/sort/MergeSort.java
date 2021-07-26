package com.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];//归并排序需要一个额外空间
        mergeSort(arr,0, arr.length - 1, temp);

        System.out.println("归并排序后=" + Arrays.toString(arr));
    }


    //分+合的方法
    public static void mergeSort(int[] arr,int left , int right,int[] temp){
        if(left < right){
            int mid = (left+right)/2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归
            mergeSort(arr, mid+1, right, temp);
            //到合并
            merge(arr,left,mid,right,temp);
        }
    }
    //合并的方法
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中专索引
     */
    public static void merge(int[] arr,int left,int mid, int right,int[] temp){
        System.out.println("xxx");
        int i = left;
        int j = mid+1;
        int t = 0;//temp的当前索引

        //先把左右两边的数据按照规则拷贝到temp数组
        //知道左右两边有一边结束
        while (i<=mid&&j<=right){
            if (arr[i]>arr[j]){
                temp[t]=arr[j];
                j++;
                t++;
            }else {
                temp[t]=arr[i];
                i++;
                t++;
            }
        }

        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }

        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }

        //将temp拷贝到arr
        t = 0;
        int templeft = left;

        System.out.println("templft=" + templeft +"right=" + right);
        while (templeft<=right){//第一次合并是 templeft = 0;right=1;
            arr[templeft] = temp[t];
            t+=1;
            templeft +=1;
        }
    }
}
