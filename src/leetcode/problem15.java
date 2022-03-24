package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problem15 {
    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        if (nums == null || nums.length <= 2) {
            return res;
        }
        for(int i =0;i<nums.length;i++){
            int j=i+1,k=nums.length-1;
            if(nums[i]>0){
                return res;
            }
            while(j<k){
                if(nums[i]+nums[j]+nums[k]==0){

                    while (k > j && nums[k] == nums[k - 1]) {
                        k--;}
                    while (k > j && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    k--;
                    j++;
                }else if (nums[i]+nums[j]+nums[k]>0){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return res;

    }
}
