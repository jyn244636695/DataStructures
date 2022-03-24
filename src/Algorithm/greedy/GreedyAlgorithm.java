package Algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最多未覆盖地区对应的电台的key
        //如果maxKey部位null，加入selects
        String maxKey = null;
        while (allAreas.size()!=0){
            //每次进行一次while是maxkey为空
            maxKey=null;
            for (String key : broadcasts.keySet()) {
                //每进行一次for
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempset和allAreas的交集，交际会赋值给tempset
                tempSet.retainAll(allAreas);

                //如果当前集合包含单位覆盖地区的数量，比maxkey指向的集合未覆盖的地区还要多
                //就需要重置maxkey
                //tempSet.size()>broadcasts.get(maxKey).size()体现出贪心算法的特点
                if (tempSet.size()>0&&
                        (maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;

                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                //将max指向的广播电台包含的地区从allAreas清除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
