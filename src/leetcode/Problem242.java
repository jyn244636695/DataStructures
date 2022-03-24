package leetcode;

import java.util.HashMap;

public class Problem242 {
    public static void main(String[] args) {
        String s="anagram";
        String t="nagaram";
        if(s.length()!=t.length()){
            System.out.println("false");
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }

        for(int i = 0;i<t.length();i++){
            if(map.containsKey(t.charAt(i))){
                map.put(t.charAt(i),map.get(t.charAt(i))-1);
                if(map.get(t.charAt(i))==0){

                }

            }else{

            }
        }
        HashMap<Object,Object> map1 = new HashMap<>();
        map1.put("aaa","aaa");
        map1.remove("aaa");
        System.out.println(map1);
        System.out.println(map.isEmpty());
    }
}
