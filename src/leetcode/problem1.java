package leetcode;

import java.util.HashMap;
import java.util.Map;

public class problem1 {
    public static void main(String[] args) {
        int res = fourSumCount(new int[]{-1,-1},new int[]{1,-1},new int[]{-1,1},new int[]{1,-1});
        System.out.println(res);
    }


    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Map<Integer,Integer> temp1 = new HashMap<>();
        Map<Integer,Integer> temp2 = new HashMap<>();
        for(int i =0;i<n;i++){
            for(int j = 0;j<n;j++){
                int cur = nums1[i]+nums2[j];
                int cur1=nums3[i]+nums4[j];
                if(!temp1.containsKey(cur)){
                    temp1.put(cur,1);
                }else{
                    temp1.put(cur,temp1.get(cur)+1);
                }
                if(!temp2.containsKey(cur1)){
                    temp2.put(cur1,1);
                }else{
                    temp2.put(cur1,temp2.get(cur1)+1);
                }
            }
        }
        int res =0;
        for(Integer key:temp1.keySet()){
            if(temp2.containsKey(-key)){
                res+=temp2.get(-key);
            }
        }
//        Character
        return res;
    }
}
