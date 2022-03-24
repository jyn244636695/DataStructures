public class test {
    public static void main(String[] args) {
        int[] nums = {3,4,5,6,7,9};
        int target = 6;
        int[] ints = plusOne(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }


    }
    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if(target<=nums[0]){
            return 0;
        }
        if(target>nums[len-1]){
            return len;
        }
        return Insert(0,len-1,nums,target);

    }
    public static int Insert(int begin,int end,int[] nums,int target){
        int temp = (begin+end)/2;
        if(end - begin<=2){
            if(target<=nums[temp]){
                return temp;
            }else{
                return temp+1;
            }

        }

        if(target<nums[temp]){
            return Insert(begin,temp,nums,target);
        }else{
            return Insert(temp,end,nums,target);
        }
    }

    public static int[] plusOne(int[] digits) {
        for(int i = digits.length-1;i>=0;i--){
            digits[i]++;
            digits[i]=digits[i]%10;
            if(digits[i]%10!=0){
                return digits;
            }
        }
        digits= new int[digits.length+1];
        digits[0] = 1;
        return digits;
    }
}
