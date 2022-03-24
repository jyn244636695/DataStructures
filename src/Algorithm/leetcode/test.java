package Algorithm.leetcode;

import java.util.HashMap;

public class test {
    public static void main(String[] args) {
//       int a = reverse(1534236469);

//        String s1 = "";
//        s1.concat("1");
//        System.out.println(a);
//        System.out.println(9/2);

        //boolean b = isPalindrome(121);
        int s = romanToInt("MCMXCIV");
        System.out.println(s);
    }
    public static int reverse(int x) {
            String num = String.valueOf(x);
            String s = new String();
            String s1 = new String();
            int j = num.length();
            int i =0;
            if( num.charAt(0) == '-'){
                s1 = s1.concat("-");
                i=1;
            }
            for(;j-1>=i;j--){
               s = num.substring(j-1,j);
                s1 = s1.concat(s) ;
            }
            int result = 0;
            try {
                result = Integer.parseInt(s1);
            }catch (Exception e){

            }
            return result;
    }

    public static boolean isPalindrome(int x) {
        String num = String.valueOf(x);
        int count = num.length();
        boolean flag = true;
        int i =0;
        int j = count;
        while(i<count/2){
            if(num.substring(i,i+1).equals(num.substring(j-1,j))){
                i++;
                j--;
            }else{
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static int romanToInt(String s) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",10000);
        int count = 0;
        int a =0;
        for (int i = 0; i+1 <= s.length(); i++) {
            a = map.get(s.substring(i,i+1));
            if(i>=1){
                if(a>map.get(s.substring(i-1,i))){
                    a = a-2*map.get(s.substring(i-1,i));
                }
            }
            count = count+a;
        }
        return count;
    }

//    public int numberOfArithmeticSlices(int[] nums) {
//        int count = nums.length;
//        if (count<3){
//            return 0;
//        }
//        if(nums[1]-nums[0]==nums[2]-nums[1]){
//
//        }
////        for (int i = 3; i+1 < ; i++) {
//
////        }
//    }

}
