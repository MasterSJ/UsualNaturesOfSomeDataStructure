package top.bmft.test;

import top.bmft.hash.HashMap;
import top.bmft.hash.HashSet;

public class TestHashSet {
    public static void main(String[] args){
      testRemove();
  }
  
  public static void testRemove(){
      HashSet set = new HashSet();
      set.add(String.valueOf(1));
      set.add(String.valueOf(2));
      set.add(String.valueOf(3));
      set.add(String.valueOf(4));
      set.add(String.valueOf(5));
      set.add(String.valueOf(6));
      set.add(String.valueOf(7));
      set.add(String.valueOf(3));
      set.add(String.valueOf(4));
      set.add(String.valueOf(5));
      set.add(String.valueOf(8));
      set.add(String.valueOf(9));
      set.add(String.valueOf(10));
      set.add(String.valueOf(3));
      set.add(String.valueOf(4));
      set.add(String.valueOf(5));
      System.out.println(set.toString());
      set.remove("1");
      set.remove("3");
      set.remove("5");
      System.out.println("---------------------");
      System.out.println(set.toStringDetail());
  }
}
