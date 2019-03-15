package top.bmft.test;

import top.bmft.list.ArrayList;

public class TestArrayList {
    public static void main(String[] args){
        test1();
    }
    
    public static void test1(){
        ArrayList<Integer> list = new ArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for(int i=0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        list.remove(1);
        list.remove(3);
        for(int i=list.size(); i > 0; i--){
            System.out.println("--"+list.get(i-1));
        }
        
        for(Integer i : list){
            System.out.println("----"+i);
        }
    }
}
