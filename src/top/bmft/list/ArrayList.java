package top.bmft.list;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements Iterable<E>{
    private static final int DEFAULT_CAPACITY = 8;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;
    
    public ArrayList(){
        elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    
    public ArrayList(int initSize){
        if(initSize > 0){
            elementData = new Object[initSize];
        } else if(initSize == 0){
            elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        } else {
            throw new RuntimeException("初始大小不能小于0");
        }
    }
    
    public boolean add(E e){
        ensureCapacity(size + 1);
        elementData[size] = e;
        size ++;
        return true;
    }
    
    public E get(int index){
        checkRange(index);
        return elementData(index);
    }
    
    @SuppressWarnings("unchecked")
    public E elementData(int index){
        return (E) elementData[index];
    }
    
    public int size(){
        return size;
    }
    
    public E remove(int index){
        checkRange(index);
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; 

        return oldValue;
    }
    
    private void checkRange(int index){
        if(index >= size){
            throw new RuntimeException(index+"下标超出了界限");
        }
    }
    
    private void ensureCapacity(int capacity){
        int newCapacity;
        if(capacity > elementData.length){
            if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
                newCapacity = DEFAULT_CAPACITY;
            } else {
                newCapacity = elementData.length << 1;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Ite();
    }
    
    class Ite implements Iterator<E>{
        int corsor = 0;
        @Override
        public boolean hasNext() {
            return corsor < size;
        }

        @Override
        public E next() {
            return  elementData(corsor++);
        }
    }
}
