package top.bmft.hash;

public class HashMap<K,V> {
    private static final float adaptRate = 0.75f;
    private static final int defaultSize = 16;
    private int size;//数组长度
    private int capacity;//元素总数
    private Node<K,V>[] box;//具体value存放数组
    private int threshold;//扩容临界值
    
    public HashMap(int size){
        if(size <= 0){
            throw new RuntimeException("初始大小必须大于0");
        }
        this.size = size;
        box = new Node[size];
        threshold = (int) (size * adaptRate);
    }
    
    public HashMap(){
        this(defaultSize);
    }
    
    private int hash(Object key){
        int addr = key.hashCode();
        //System.out.println(key + "----" + addr + "----" + (addr % size));
        return addr % size;
    }
    
    public void put(K key, V value){
        int index = hash(key);
        Node<K,V> node = box[index];
        if(node == null){
            box[index] = new Node(key, value);
            capacity++;
        } else {
            box[index] = node.merge(key, value);
        }
        adaptCapacity();
    }
    
    public Object remove(K key){
        if(key == null){
            return null;
        }
        int index = hash(key);
        Node<K,V> node = box[index];
        if(node == null){
            return null;
        } else if(key.equals(node.k)){
            box[index] = node.next;
            capacity--;
        } else {
            Node<K,V> p = node;
            node = node.next;
            while(node != null){
                if(key.equals(node.k)){
                    p.next = node.next;
                    capacity--;
                    return node.v;
                }
                p = node;
                node = node.next;
            }
        }
        return null;
    }
    
    public int size(){
        return capacity;
    }
    
    private void adaptCapacity(){
        if(capacity < threshold){
            return;
        }
        int oldSize = size;
        size = oldSize << 1;
        threshold = threshold << 1;
        Node<K,V>[] newBox = new Node[size];
        for(int i = 0; i < oldSize; i++){
            Node<K,V> node = box[i];
            while(node != null){
                int index = hash(node.k);
                Node<K,V> newNode = newBox[index];
                if(newNode == null){
                    newBox[index] = node;
                } else {
                    newBox[index] = newNode.add(node.k, node.v);
                }
                Node<K,V> temp = node.next;
                node.next = null;
                node = temp;
            } 
        }
        box = newBox;
    }
    
    public Object get(String key){
        if(key == null){
            return null;
        }
        int index = hash(key);
        Node<K,V> node = box[index];
        while(node != null){
            if(key.equals(node.k)){
                return node.v;
            } else {
                node = node.next;
            }
        }
        return null;
    }
    
    class Node<K,V> {
        K k;
        V v;
        Node<K,V> next;
        public Node(K key, V obj){
            k = key;
            v = obj;
            next = null;
        }
        
        public Node<K,V> merge(K key, V obj){
            Node<K,V> node = this;
            while(node != null){
                if(key.equals(node.k)){
                    node.v = obj;
                    break;
                }
                node = node.next;
            }
            if(node == null){
                node = add(key, obj);
                capacity++;
                return node;
            } else {
                return node;
            }
        }
        
        public Node<K,V> add(K key, V obj){
            Node<K,V> node = new Node<>(key, obj);
            node.next = this;
            return node;
        }
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("{");
        for(int i = 0; i < size; i++){
            Node<K,V> node = box[i];
            while(node != null){
                if(sb.length() > 1){
                    sb.append(", ");
                }
                sb.append(node.k).append(":").append(node.v);
                node = node.next;
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public String toStringDetail(){
        StringBuilder sb = new StringBuilder("{\n");
        for(int i = 0; i < size; i++){
            Node<K,V> node = box[i];
            sb.append("bucket" + i + "[");
            int innerCount = 0;
            while(node != null){
                innerCount++;
                if(innerCount > 1){
                    sb.append(", ");
                }
                sb.append(node.k).append(":").append(node.v);
                node = node.next;
            }
            sb.append("]\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
