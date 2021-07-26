package com.search;

import java.util.Arrays;

public class FibonacciSearch {
    
    
    public static int maxSize = 20;
    public static void main(String[] args) {
        int arr[]={1,8,10,89,100,123};

        System.out.println(fibSearch(arr,189));
    }

    //因为需要使用斐波那契数列，因此需要获取一个
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写算法
    /**
     *
     * @param a  数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length-1;
        int k = 0;//表示斐波那契数列分割数值的下标；
        int mid =0;
        int f[] = fib();//获取斐波那契数列
        //获取到斐波那契分割数值的下表
        while (high>f[k]-1){
            k++;
        }
        //因为f[k]值可能大于a的长度，需要使用Arrays类构造一个新的数组，并指向A
        int[] temp = Arrays.copyOf(a,f[k]);
        //使用a数组最后一个属填充temp
        for (int i = high+1; i <temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while循环处理找到key
        while (low <=high){
            mid = low + f[k-1] -1;
            if(key<temp[mid]){//说明应该继续向数组左边查找
                high = mid -1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            }else if(key>temp[mid]){//往右查找
                low = mid+1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k-=2;

            }else {
                //需要确定返回的是哪个小标
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
