package Algorithm.kmp;

public class kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";
        int[] next = kmpNext("ABCDABD");
        for (int i : next) {
            System.out.println(i);
        }

        int index = kmpSearch(str1,str2,next);
        System.out.println("index="+index);
    }

    //写出kmp搜索算法

    /**
     *
     * @param str1 str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，是字串对应部分匹配表
     * @return 如果-1则没匹配找
     */
    public  static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j=0; i < str1.length(); i++) {

            //需要考虑不同的情况下
            while (j>0&&str1.charAt(i)!=str2.charAt(j)){
                j = next[j-1];
            }

            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取到字符串字串的部分匹配之
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1，则第一个字符部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i)！=dest.charAt(j)满足时 需要从next[j-1]获取新的j
            //直到dest.charAt(i)==dest.charAt(j)推出
            //这是kmp算法的核心
            while (j>0 && dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }

            //当dest.charAt(i)==dest.charAt(j)满足时 部分匹配值就是+1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
