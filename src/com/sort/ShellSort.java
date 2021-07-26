package com.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSory(arr);
        int[] arr = new int[200000];
        for (int i = 0; i < 200000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("插入排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        shellSory2(arr); //调用插入排序算法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //使用逐步推到的方式来编写
    public static void shellSory(int[] arr) {
        int temp = 0;
        //交换的希尔排序
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length ; i++) {
                for (int j = i-gap; j >=0 ; j-= gap) {
                    if (arr[j] > arr[j+gap]) {
                        temp = arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void shellSory2(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length ; i++) {
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j-gap>=0&& temp <arr[j - gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
