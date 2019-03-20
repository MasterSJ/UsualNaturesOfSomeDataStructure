package top.bmft.test;

import java.util.ArrayList;

import top.bmft.hash.HashMap;

public class TestHashMap {
    public static void main(String[] args){
//        testJdkMap();
//        testMyMap();
        testRemove();
    }
    
    public static void testRemove(){
        HashMap<Integer, String> map = new HashMap<>();
        for(int i=0; i < 40; i++){
            map.put(i, "00" + i*(i+1));
        }
        System.out.println(map.toString());
        map.remove(11);
        map.remove(13);
        map.remove(32);
        map.remove(34);
        System.out.println("---------------------");
        System.out.println(map.toStringDetail());
    }
    
    public static void testMyMap(){
        System.out.println();
        HashMap<String, String> map = new HashMap<>();
        long[] pay = new long[50];
        long start = System.nanoTime();
        long interval = 0;
        for(int i=0; i < 50; i++){
            map.put(String.valueOf(i), "2000" + i);
            long time = System.nanoTime() - start;
            pay[i] = time - interval;
            interval = time;
        }
        System.out.println(interval);
        for(int i=0; i < 50; i++){
            System.out.print(pay[i] + ", ");
        }
        for(int i=0; i < 50; i++){
            map.get(String.valueOf(20000 - 1 - i));
        }
    }
    

    public static void testJdkMap(){
        System.out.println();
        java.util.HashMap<String, String> map = new java.util.HashMap<>();
        long[] pay = new long[50];
        long start = System.nanoTime();
        long interval = 0;
        for(int i=0; i < 50; i++){
            map.put(String.valueOf(i), "2000" + i);
            long time = System.nanoTime() - start;
            pay[i] = time - interval;
            interval = time;
        }
        System.out.println(interval);
        for(int i=0; i < 50; i++){
            System.out.print(pay[i] + ", ");
        }
        for(int i=0; i < 50; i++){
            map.get(String.valueOf(20000 - 1 - i));
        }
    }
}
