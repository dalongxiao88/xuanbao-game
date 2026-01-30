package org.come.until;

import java.util.Iterator;
import java.util.LinkedList;

public class FixSizeLinkedList<T> extends LinkedList<T>
{
    private int capacity;
    
    public FixSizeLinkedList(int capacity) {
        this.capacity = capacity;
    }
    
    @Override
    public void add(int index, T element) {
        super.add(index, element);
        if (this.size() > this.capacity) {
            super.removeFirst();
        }
    }
    
    @Override
    public boolean add(T t) {
        if (this.size() + 1 > this.capacity) {
            super.removeFirst();
        }
        return super.add(t);
    }
    
    public static void main(String[] args) {
        FixSizeLinkedList<String> list = new FixSizeLinkedList(4);
        list.add(0, "123456");
        list.add(0, "12345");
        list.add(0, "1234");
        list.add(0, "123");
        list.add(0, "12");
        list.add(0, "1");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
