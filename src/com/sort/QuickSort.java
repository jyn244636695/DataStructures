package com.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr,0,arr.length-1);
        System.out.println("arr="+ Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        //中轴值
        int pivot = arr[(left+right)/2];
        int temp = 0;
        //while循环的目的是让比pivot小的值 放到左边，比他大的放到右边

        while (l<r){
            while (arr[l]<pivot){
                l += 1;
            }

            while (arr[r] >pivot){
                r -= 1;
            }
            //如果 l>=r 说明 pivot左右两边的值，已经按照左边全部是小于等于Pivot的值
            if(l>=r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换玩发现arr[l]==pivot，前移
            if(arr[l]==pivot){
                r -= 1;
            }

            if(arr[r]== pivot){
                l +=1;
            }
        }

        //如果l==r,则必须l++,r--
        if(l==r){
            l +=1;
            r -=1;
        }
        //向左递归
        if(left<r){
            quickSort(arr,left,r);
        }

        if(right>l){
            quickSort(arr,l,right);
        }

    }
}
