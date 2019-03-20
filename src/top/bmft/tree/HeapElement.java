package top.bmft.tree;

public class HeapElement<E> {
    private int level;
    private E ele;
    
    public HeapElement(int level, E e){
        this.level = level;
        this.ele = e;
    }
    
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public E getEle() {
        return ele;
    }
    public void setEle(E ele) {
        this.ele = ele;
    }
}
