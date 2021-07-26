package com.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubleSort {
    public static void main(String[] args) {
//       int arr[] = {3 , 9, -1 , 10 , 20};
//        int temp = 0;
//        boolean ex = false;
//        for (int j = 1; j < arr.length; j++) {
//
//                for (int i = 0; i < arr.length - j; i++) {
//                    if (arr[i] > arr[i + 1]) {
//                        temp = arr[i + 1];
//                        arr[i + 1] = arr[i];
//                        arr[i] = temp;
//                        ex = true;
//                    }
//            }
//            System.out.printf("第%d趟排序后的数组\n", j);
//            System.out.println(Arrays.toString(arr));
//
//            if(!ex){
//                break;
//            }else {
//                ex=false;
//            }
//        }
        //测试一下冒泡排序的速度
        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = simpleFormatter.format(date);
        System.out.println("排序前的时间："+date1);

//        System.out.println(Arrays.toString(arr));
        bublesort( arr);
        Date date2 = new Date();
        String date3 = simpleFormatter.format(date2);
        System.out.println("排序前的时间："+date3);
    }

    //冒泡排序封装成一个方法
    public static void bublesort(int arr[]){

        int temp = 0;
        boolean ex = false;
        for (int j = 1; j < arr.length; j++) {

            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    ex = true;
                }
            }
//            System.out.printf("第%d趟排序后的数组\n", j);
//            System.out.println(Arrays.toString(arr));

            if(!ex){
                break;
            }else {
                ex=false;
            }
        }
    }
}
