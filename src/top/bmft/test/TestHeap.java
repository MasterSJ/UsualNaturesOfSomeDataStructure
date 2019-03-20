package top.bmft.test;

import top.bmft.tree.Heap;
import top.bmft.tree.HeapElement;

public class TestHeap {
    public static void main(String[] args){
        test1();
    }
    
    public static void test1(){
        HeapElement<?>[] hs = new HeapElement[10];
        hs[0] = new HeapElement(4, null);
        hs[1] = new HeapElement(1, null);
        hs[2] = new HeapElement(3, null);
        hs[3] = new HeapElement(2, null);
        hs[4] = new HeapElement(5, null);
        hs[5] = new HeapElement(8, null);
        hs[6] = new HeapElement(7, null);
        hs[7] = new HeapElement(6, null);
        hs[8] = new HeapElement(9, null);
        hs[9] = new HeapElement(0, null);
        Heap heap = new Heap(hs);
        System.out.println(heap.toStringDetail());
        HeapElement ele = heap.delVertex();
        System.out.println(heap.toStringDetail());
    }
}
