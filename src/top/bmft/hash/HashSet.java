package top.bmft.hash;

public class HashSet {
    private static final Object PRESENT = new Object();
    private HashMap map;
    
    public HashSet(){
        map = new HashMap();
    }
    
    public void add(String str){
        map.put(str, PRESENT);
    }
    
    public boolean contain(String str){
        return map.get(str) == PRESENT;
    }
    
    public boolean remove(String str){
        return map.remove(str) == PRESENT;
    }
    
    public String toString(){
        return map.toString();
    }
    
    public String toStringDetail(){
        return map.toStringDetail();
    }
}
