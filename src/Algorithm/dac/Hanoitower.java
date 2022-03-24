package Algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoitowe(5,'A','B', 'C');
    }

    //汉诺塔的移动方法
    //分治算法

    /**
     *
     * @param num 盘子数量
     * @param a 目前为止
     * @param b 借用的位置
     * @param c 最后位置
     */
    public static void hanoitowe(int num,char a , char b,char c){
        if(num == 1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else {
            //n>=2 看成两个盘，最下面的一个盘和上面的所有盘
            //1.先把a->b
            hanoitowe(num-1,a,c,b);
            //2.把下面的盘A->c.移动过程使用c
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3.把b塔的所有盘从B->C，移动过程使用A
            hanoitowe(num-1,b,a,c);
        }
    }
}
