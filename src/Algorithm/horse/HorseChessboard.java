package Algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {

    private static int X;//列数
    private static int Y;//行数
    //标记棋盘的各个位置是否被访问过
    private static boolean[][] visited;
    //使用一个属性标记是否期盼的所有位置都被访问
    private static boolean finished;

    public static void main(String[] args) {
        //测试
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        visited = new boolean[X][Y];
        int[][] chessboard = new int[X][Y];
        //测试一下
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时"+(end - start)+"ms");

        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row        马儿当前行
     * @param column     马儿当前列
     * @param step       第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row][column] = true;//标记当前位置已经被访问
        //获取当前位置可以走的下一个位置
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断是否已经被访问
            if (!visited[p.y][p.x]) {//说明还没有访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断是否完成
        //没有达成数量，则表示没有完成任务，将棋盘置0

        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row][column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前的位置，计算马还能走哪些位置，并放入集合中，最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        //创建Point
        Point p1 = new Point();
        //表示马可以走的位置5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走的位置4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这一步的所有的下一步的选择位置进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2) {
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
