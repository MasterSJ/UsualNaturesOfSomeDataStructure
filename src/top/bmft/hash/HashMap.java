package top.bmft.hash;

public class HashMap {
    private static final float adaptRate = 0.75f;
    private int size = 8;
    private int capacity;
    private Node[] box;
    
    public HashMap(int size){
        if(size <= 0){
            throw new RuntimeException("初始大小必须大于0");
        }
        this.size = size;
        box = new Node[size];
    }
    
    public HashMap(){
        box = new Node[this.size];
    }
    
    private int hash(Object key){
        int addr = key.hashCode();
        //System.out.println(key + "----" + addr + "----" + (addr % size));
        return addr % size;
    }
    
    public void put(String key, Object value){
        int index = hash(key);
        Node node = box[index];
        if(node == null){
            box[index] = new Node(key, value);
        } else {
            box[index] = node.add(key, value);
        }
        capacity++;
        adaptCapacity();
    }
    
    public int size(){
        return capacity;
    }
    
    private void adaptCapacity(){
        float limit = size * adaptRate;
        if(capacity < limit){
            return;
        }
        int oldSize = size;
        size = oldSize << 1;
        Node[] newBox = new Node[size];
        for(int i = 0; i < oldSize; i++){
            Node node = box[i];
            while(node != null){
                int index = hash(node.k);
                Node newNode = newBox[index];
                if(newNode == null){
                    newBox[index] = node;
                } else {
                    newBox[index] = newNode.add(node.k, node.v);
                }
                Node temp = node.next;
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
        Node node = box[index];
        while(node != null){
            if(key.equals(node.k)){
                return node.v;
            } else {
                node = node.next;
            }
        }
        return null;
    }
    
    class Node {
        String k;
        Object v;
        Node next;
        public Node(String key, Object obj){
            k = key;
            v = obj;
            next = null;
        }
        public Node add(String key, Object obj){
            Node node = new Node(key, obj);
            node.next = this;
            return node;
        }
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("{");
        for(int i = 0; i < size; i++){
            Node node = box[i];
            sb.append("bucket" + i + "[");
            while(node != null){
                if(sb.length() > 9){
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
