package com.sort;

import java.util.Arrays;

public class RedisSort {
    public static void main(String[] args) {
        int[] arr={53, 3, 542,748, 14, 214};
        int arr1[]={53, 3, 542,748, 14, 214};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        redisSrot(arr);
        for (int i = 0; i < 3; i++) {

            System.out.println();
        }

    }

    public static void redisSrot(int[] arr){


        //根据前面的推到得到最终的代码
        //1.得到数组中最大数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大位数是几位数
        int maxLength = (max + "").length();


        //定义一个一维数组
        //定义一个二维数组
        //1.二维数组包含10个一维数组
        //2.为了防止溢出，则每个一维数组大小定位arr.length
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组计数
        int[] bucketElementCount = new int[10];

        for (int k = 0,n=1; k < maxLength; k++,n*=10) {
            for (int i = 0; i < arr.length; i++) {
                //去除每个元素的各位
                int digitOfElement = arr[i]/(n)%10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
                bucketElementCount[digitOfElement]++;
            }
            //取数据
            int index=0;
            for (int i = 0; i < bucketElementCount.length; i++) {
                if(bucketElementCount[i] != 0){
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }bucketElementCount[i]=0;//将计数桶清0
                }

            }
            System.out.println("第"+k+"轮"+Arrays.toString(arr));
        }

        /*//定义一个一维数组
        //定义一个二维数组
        //1.二维数组包含10个一维数组
        //2.为了防止溢出，则每个一维数组大小定位arr.length
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组计数
        int[] bucketElementCount = new int[10];
        //First round
        for (int i = 0; i < arr.length; i++) {
            //去除每个元素的各位
            int digitOfElement = arr[i]%10;
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
            bucketElementCount[digitOfElement]++;
        }
        //取数据
        int index=0;
        for (int i = 0; i < bucketElementCount.length; i++) {
            if(bucketElementCount[i] != 0){
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
                bucketElementCount[i]=0;//将计数桶清0
            }
        }

        System.out.println("第一轮"+Arrays.toString(arr));


        //2 round 十位
        for (int i = 0; i < arr.length; i++) {
            //去除每个元素的各位
            int digitOfElement = arr[i]/10%10;
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
            bucketElementCount[digitOfElement]++;
        }

        //取数据
        index=0;
        for (int i = 0; i < bucketElementCount.length; i++) {
            if(bucketElementCount[i] != 0){
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];

                }
                bucketElementCount[i]=0;//将计数桶清0
            }
        }

        System.out.println("第2轮"+Arrays.toString(arr));

        //3 round 百位
        for (int i = 0; i < arr.length; i++) {
            //去除每个元素的各位
            int digitOfElement = arr[i]/100 %10;
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
            bucketElementCount[digitOfElement]++;
        }

        //取数据
        index=0;
        for (int i = 0; i < bucketElementCount.length; i++) {
            if(bucketElementCount[i] != 0){
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];

                }
                bucketElementCount[i]=0;//将计数桶清0
            }
        }

        System.out.println("第3轮"+Arrays.toString(arr));*/

    }
}
