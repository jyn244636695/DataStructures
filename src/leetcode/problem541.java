package leetcode;

public class problem541 {
    public static void main(String[] args) {
        String res = reverseStr(new String("abcdefg"), 2);
        System.out.println(res);
    }

    static String reverseStr(String s, int k) {
        char[] res = s.toCharArray() ;
        int left = 0;
        while(left<res.length){
            /** 方法一 */
            int right =0;
            if(left+k-1<res.length){
                right = left+k-1;
            }else{
                right = res.length -1;
            }

            while(left<right){

                res[right]^= res[left];//先把r和l中，不相同的位保存到r，现在r中置1的位，代表原始的r和l不相同的位，而0，就是r和l相同的位。
                res[left]^= res[right];//。
                res[right]^= res[left];

                right--;
                left++;
            }
            left=left+2*k;
        }

        return String.valueOf(res);

    }
}
