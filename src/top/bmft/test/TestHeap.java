package top.bmft.test;

import top.bmft.tree.Heap;
import top.bmft.tree.Heap.HeapElement;

public class TestHeap {
    public static void main(String[] args){
        test1();
    }
    
    @SuppressWarnings("unchecked")
    public static void test1(){
        HeapElement<Object>[] hs = new HeapElement[10];
        hs[0] = new HeapElement<Object>(43, "第1个");
        hs[1] = new HeapElement<Object>(14, "第2个");
        hs[2] = new HeapElement<Object>(36, "第3个");
        hs[3] = new HeapElement<Object>(28, "第4个");
        hs[4] = new HeapElement<Object>(59, "第5个");
        hs[5] = new HeapElement<Object>(8, "第6个");
        hs[6] = new HeapElement<Object>(755, "第7个");
        hs[7] = new HeapElement<Object>(6, "第8个");
        hs[8] = new HeapElement<Object>(94, "第9个");
        hs[9] = new HeapElement<Object>(0, "第10个");
        Heap<HeapElement<Object>> heap = new Heap<>(hs);
        System.out.println(heap.toTreeString(3));
        heap.decreaseLevel(9, 9);
        System.out.println(heap.toTreeString(3));
        heap.delVertex();
        System.out.println(heap.toTreeString(3));
        heap.delVertex1(3);
        System.out.println(heap.toTreeString(3));
        heap.insert(new HeapElement<Object>(1, new Object()));
        heap.insert(new HeapElement<Object>(866, new Object()));
        heap.insert(new HeapElement<Object>(0, new Object()));
        heap.insert(new HeapElement<Object>(41, new Object()));
        heap.insert(new HeapElement<Object>(8, new Object()));
        heap.insert(new HeapElement<Object>(0, new Object()));
        heap.insert(new HeapElement<Object>(1, new Object()));
        System.out.println(heap.toTreeString(3));
    }
}
