package com.search;

import java.util.ArrayList;
import java.util.List;

public class InsertValueSearch {
    public static void main(String[] args) {

        int arr[] = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }

        int i = insertValueSearch1(arr, 0, 99, 5);
        System.out.println(i);

        List<Integer> res = insetValueSearch(arr,0,arr.length-1,5);
        System.out.println(res);

    }

    public static List<Integer> insetValueSearch(int[] arr, int left, int right, int findval){
        System.out.println("插值查找次数~~");
        if(left>right|| findval<arr[0]||findval>arr[arr.length-1]){
            return new ArrayList<>();
        }
        int mid =left + (right - left) * (findval - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findval>midVal){
            //向右递归
            return insetValueSearch(arr,mid+1,right,findval);
        }else if(findval<midVal){
            return insetValueSearch(arr,left,mid - 1,findval);
        }else {
            List<Integer> resindexlist = new ArrayList<Integer>();
            //向mid的左边扫描，将满足的元素下标加入集合
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findval){
                    break;
                }
                resindexlist.add(temp);
                temp -=1;
            }
            resindexlist.add( mid);

            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1 || arr[temp] != findval){
                    break;
                }
                resindexlist.add(temp);
                temp +=1;
            }
            return resindexlist;
        }

    }

    public static int insertValueSearch1(int[] arr, int left, int right, int findVal) {

        System.out.println("插值查找次数~~");

        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        // 求出mid, 自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch1(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch1(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }
}
