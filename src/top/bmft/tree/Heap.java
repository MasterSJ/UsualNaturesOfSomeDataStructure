package top.bmft.tree;

public class Heap<D extends HeapElement<?>> {
    private static int CAP = 16;
    private int size = 0;
    private Object[] data;
    
    public Heap(int capacity, D[] entrys){
        
        if(capacity < CAP){
            capacity = CAP;
        }
        while(capacity < entrys.length + 1){
            capacity = capacity << 1;
        }
        data = new Object[capacity];
        for(int i=0;i<entrys.length;i++){
            data[i+1] = entrys[i];
            size ++;
        }
        for(int i = size / 2; i > 0; i--){
            percolateDown(i);
        }
    }
    
    //删除最小
    public D delVertex(){
        D ret = getData(1);
        data[1] = getData(size);
        size--;
        percolateDown(1);
        return ret;
    }
    
    //下渗
    private void percolateDown(int index){
        D tmp = getData(index);
        int youngChildIndex;
        while((index << 1) <= size){
            youngChildIndex = index << 1;
            D youngChild = getData(youngChildIndex);
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
    
    private D getData(int index){
        return (D)data[index];
    }
    
    public Heap(D[] entrys){
        this(entrys.length + 1, entrys);
    }
    
    public String toStringDetail(){
        StringBuilder sb = new StringBuilder("");
        int line = 1;
        for(int i=0;i<size;){
            for(int j=0;j<line && i<size;j++){
                sb.append(((D)data[++i]).getLevel() + "    ");
            }
            sb.append("\n");
            line = line << 1;
        }
        return sb.toString();
    }
}
