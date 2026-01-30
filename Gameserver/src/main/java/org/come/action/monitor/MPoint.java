package org.come.action.monitor;

public class MPoint
{
    private long key;
    private long value;
    private Object object;
    
    public MPoint() {
        this.object = new Object();
    }
    
    public void add(long value) {
        synchronized (this.object) {
            ++this.key;
            this.value += value;
        }
    }
    
    public long getKey() {
        return this.key;
    }
    
    public void setKey(long key) {
        this.key = key;
    }
    
    public long getValue() {
        return this.value;
    }
    
    public void setValue(long value) {
        this.value = value;
    }
}
