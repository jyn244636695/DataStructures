package leetcode;

public class problem29 {
    public static void main(String[] args) {
        System.out.println(2147483647*2);
        System.out.println(divide(2147483647,
                2));
    }
    public static int divide(int dividend, int divisor) {
        long count = 1;
        long res=0;
        boolean neg = false;
        long dived = (long) dividend;
        long div = divisor;
        if(div*dived<0){
            neg=true;
        }


        dived = Math.abs(dived);
        div = Math.abs(div);
        if(div==dived){
            res  = 1;
            return neg?-(int)res:(int)res;
        }
        while(div<dived){
            while(div<dived){
                div<<=1;
                count<<=1;
            }

                dived-=(div>>1) ;
                res+=count>>1;
                count = 1;
                div = Math.abs(divisor);


        }
        if(div==dived) {
            res = res + 1;
        }

        return neg?-(int)res:(int)res;

    }
}
