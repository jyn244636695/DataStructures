package com.search;

import java.util.ArrayList;
import java.util.List;

//二分查找的前提是数组有序
public class BinarySearch {
    public static void main(String[] args) {
        int arr[]={1,8,10,89,100,100,1234};

        List<Integer> res = binarySearch1(arr,0,arr.length,100);
        System.out.println(res);
    }


    public static int binarySearch(int[] arr,int left, int right, int findval){
        if(left>right){
            return -1;
        }
        int mid =(left+right)/2;
        int midVal = arr[mid];
        if(findval>midVal){
            //向右递归
           return binarySearch(arr,mid+1,right,findval);
        }else if(findval<midVal){
            return binarySearch(arr,left,mid - 1,findval);
        }else {
            return mid;
        }
    }
    //完成一个课后思考题:
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */

    public static List<Integer> binarySearch1(int[] arr, int left, int right, int findval){
        if(left>right){
            return new ArrayList<>();
        }
        int mid =(left+right)/2;
        int midVal = arr[mid];
        if(findval>midVal){
            //向右递归
            return binarySearch1(arr,mid+1,right,findval);
        }else if(findval<midVal){
            return binarySearch1(arr,left,mid - 1,findval);
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

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }

    }

    public static List<Integer> binarySearch3(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch3(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch3(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid的左边扫描，将满足的元素下标加入集合
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp -=1;
            }
            resIndexlist.add( mid);

            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp +=1;
            }

            return resIndexlist;
        }

    }


}
