package leetcode;

public class problem151 {
    public static void main(String[] args) {
        String s = reverseWords("a good   example");
        System.out.println(s);
    }

    static String reverseWords(String s) {
        StringBuilder str = new StringBuilder(s);

        while(str.indexOf(" ")==0){
            str.delete(0,1);
        }
        while(str.lastIndexOf(" ")==str.length()-1){
            str.delete(str.length()-1,str.length());
        }

        int left = 0;
        while(left!=-1&&left<str.length()){
            //删除相邻两个空格
            int cur = str.indexOf(" ",left);
            while(str.charAt(left)==' '){
                str.delete(left,left+1);
            }
            int right = 0;
            if(str.indexOf(" ",left)==-1){
                 if(right == str.length()-1){
                     break;
                }else{
                     right = str.length()-1;
                }

            }else {
                 right = str.indexOf(" ",left)-1;
            }

            int temp = right+2;
            while(left<right){
                char a = str.charAt(right);
                str.setCharAt(right,str.charAt(left));
                str.setCharAt(left,a);
                left++;
                right--;
            }
            left = temp;
        }
        str.reverse();
        while(str.indexOf(" ")==0){
            str.delete(0,1);
        }
        while(str.lastIndexOf(" ")==str.length()){
            str.delete(str.length()-1,str.length());
        }

        return str.toString();
    }
}
