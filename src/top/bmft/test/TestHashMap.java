package top.bmft.test;

import java.util.ArrayList;

import top.bmft.hash.HashMap;

public class TestHashMap {
    public static void main(String[] args){
        testJdkMap();
        testMyMap();
    }
    
    public static void testMyMap(){
        HashMap map = new HashMap();
        ArrayList pay = new ArrayList(50);
        long start = System.nanoTime();
        long interval = 0;
        for(int i=0; i < 50; i++){
            map.put(String.valueOf(i), "2000" + i);
            long time = System.nanoTime() - start;
            pay.add(time - interval);
            interval = time;
        }
        System.out.println(interval);
        System.out.println(pay);
        for(int i=0; i < 50; i++){
            map.get(String.valueOf(20000 - 1 - i));
        }
    }
    

    public static void testJdkMap(){
        java.util.HashMap map = new java.util.HashMap();
        ArrayList pay = new ArrayList(50);
        long start = System.nanoTime();
        long interval = 0;
        for(int i=0; i < 50; i++){
            map.put(String.valueOf(i), "2000" + i);
            long time = System.nanoTime() - start;
            pay.add(time - interval);
            interval = time;
        }
        System.out.println(interval);
        System.out.println(pay);
        for(int i=0; i < 50; i++){
            map.get(String.valueOf(20000 - 1 - i));
        }
    }
}
