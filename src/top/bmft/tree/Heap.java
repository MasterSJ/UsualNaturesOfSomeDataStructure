package top.bmft.tree;

import java.util.Arrays;

/**  
* @ClassName: Heap  
* @Description: 优先队列，也叫二叉堆，或者直接叫堆，队列中的元素都有一定的优先级，按照优先级出队。 
* @author songjun 
* @date 2019年3月21日  
*  
* @param <D>  
*/
public class Heap<HeapElement> {
    private static int DEFAULT_CAP = 16;
    private int size = 0;
    private HeapElement[] data;
    
    public Heap(int capacity, HeapElement[] entrys){
        
        if(capacity < DEFAULT_CAP){
            capacity = DEFAULT_CAP;
        }
        while(capacity < entrys.length + 1){
            capacity = capacity << 1;
        }
        data = new HeapElement[capacity];
        for(int i=0;i<entrys.length;i++){
            data[i+1] = entrys[i];
            size ++;
        }
        for(int i = size / 2; i > 0; i--){
            percolateDown(i);
        }
    }
    
    public Heap(HeapElement[] entrys){
        this(entrys.length + 1, entrys);
    }
    
    /**    
    * @Description 删除顶点. 
    */ 
    public HeapElement delVertex(){
        if(size > 0){
            HeapElement ret = getData(1);
            data[1] = getData(size);
            data[size] = null;
            size--;
            percolateDown(1);
            return ret;
        }
        return null;
    }
    
    /**    
     * @Description 删除节点. 
     */ 
    public HeapElement delVertex(int index){
        if(index < 1 || index > size){
            return null;
        }
        HeapElement ele = getData(index);
        increaseLevel(index, Integer.MIN_VALUE);
        delVertex();
        return ele;
    }
    
    /**    
     * @Description 删除节点另一种实现. 
     */ 
    public HeapElement delVertex1(int index){
        if(index < 1 || index > size){
            return null;
        }
        HeapElement ele = getData(index);
        if(index == 1){
            delVertex(); 
        } else {
            HeapElement tail = getData(size);
            data[size--] = null;
            data[index] = tail;
            HeapElement parent = getData(index/2);
            if(tail.level > parent.level){
                percolateDown(index);
            } else if(tail.level < parent.level){
                percolateUp(index);
            }
        }
        return ele;
    }
    
    /**    
     * @Description 入队. 
     */ 
    public void insert(HeapElement ele){
        ensureCapacity(size + 1);
        data[size + 1] = ele;
        percolateUp(++size);
    }
    
    /**    
     * @Description 加大level. 
     */ 
    public HeapElement increaseLevel(int index, int increment){
        if(size <= 0 || index > size || index < 1){
            return null;
        }
        HeapElement ele = getData(index);
        int oldLevel = ele.getLevel();
        ele.setLevel(oldLevel + increment);
        if(ele.getLevel() > oldLevel){
            percolateDown(index);
        } else if (ele.getLevel() < oldLevel){
            percolateUp(index);
        }
        return ele;
    }
    
    /**    
     * @Description 减小level. 
     */ 
    public HeapElement decreaseLevel(int index, int decrement){
        return increaseLevel(index, 0 - decrement);
    }
    
    //上滤
    private void percolateUp(int index){
        int child = index;
        HeapElement ele = getData(child);
        for(int parent = child / 2;parent > 0;parent /= 2){
            if(getData(parent).getLevel() > ele.getLevel()){
                data[child] = data[parent];
                child = parent;
            } else {
                break;
            }
        }
        data[child] = ele;
    }
    
    //下滤
    private void percolateDown(int index){
        HeapElement tmp = getData(index);
        int youngChildIndex;
        while((index << 1) <= size){
            youngChildIndex = index << 1;
            HeapElement youngChild = getData(youngChildIndex);
            if(youngChildIndex + 1 < size &&
                    youngChild.getLevel() > getData(youngChildIndex + 1).getLevel()){
                youngChild = getData(++youngChildIndex);
            }
            if(tmp.getLevel() > youngChild.getLevel()){
                data[index] = youngChild;
                index = youngChildIndex;
                data[index] = tmp;
            } else {
                break;
            }
        }
    }
    
    //容量保证
    private void ensureCapacity(int capacity){
        if(capacity >= data.length){
            data = Arrays.copyOf(data, data.length << 1);
        }
    }
    
    private HeapElement getData(int index){
        return data[index];
    }
    
    public String toTreeString(int maxSateWidth){
        Double s = Math.log(size) / Math.log(2);
        int deep = s.intValue() + 1;
        int maxWidth = (1 << deep) * (maxSateWidth + 2);
        StringBuilder sb = new StringBuilder("");
        int line = 1;
        int interval = maxWidth/2;
        for(int i=0;i<size;){
            int thisSeatWidth = 0;
            for(int j=0;j<line && i<size;j++){
                if(j==0){
                    appendSpaces(sb, interval - thisSeatWidth);
                } else {
                    appendSpaces(sb, interval * 2 - thisSeatWidth);
                }
                sb.append(data[++i].getLevel());
                thisSeatWidth = getSeatWidth(data[i].getLevel());
            }
            sb.append("\n\n");
            line = line << 1;
            interval = interval >> 1;
        }
        return sb.toString();
    }
    
    private void appendSpaces(StringBuilder sb, int num){
        for(int i=0; i< num; i++){
            sb.append(" ");
        }
    }
    
    private int getSeatWidth(int seat){
        return String.valueOf(seat).length();
    }
    
    
    public String toStringDetail(){
        StringBuilder sb = new StringBuilder("");
        int line = 1;
        for(int i=0;i<size;){
            for(int j=0;j<line && i<size;j++){
                sb.append(data[++i].getLevel() + "    ");
            }
            sb.append("\n");
            line = line << 1;
        }
        return sb.toString();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i=0;i<size;){
            if(sb.length() > 1){
                sb.append(", ");
            }
            sb.append(data[++i].getLevel());
        }
        sb.append("]");
        return sb.toString();
    }
    
    
    public static class HeapElement<E> {
        private int level;
        private E ele;
        
        public HeapElement(int level, E e){
            if(level < 0){
                throw new IllegalArgumentException("level不能小于0");
            }
            setLevel(level);
            setEle(e);
        }
        
        public int getLevel() {
            return level;
        }
        private void setLevel(int level) {
            this.level = level;
        }
        public E getEle() {
            return ele;
        }
        public void setEle(E ele) {
            this.ele = ele;
        }
    }
}
