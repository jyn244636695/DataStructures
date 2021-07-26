package com.sort;

import java.util.Arrays;

public class Selectsort {


    public static void main(String[] args) {

        int arr[] = {3 , 9, -1 , 10 , 20};
        selectsort(arr);


    }

    public static void selectsort(int arr[]){
        int temp = 0;
        for (int j = 0; j < arr.length-1; j++) {
            for (int i = j; i < arr.length; i++) {
                if(arr[j]>arr[i]){
                    temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }

        }
            System.out.println(Arrays.toString(arr));
        }
    }
}
