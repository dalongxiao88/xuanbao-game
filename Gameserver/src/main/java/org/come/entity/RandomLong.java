package org.come.entity;

public class RandomLong
{
    private int probability;
    private long min;
    private long max;
    
    public int getProbability() {
        return this.probability;
    }
    
    public void setProbability(int probability) {
        this.probability = probability;
    }
    
    public long getMin() {
        return this.min;
    }
    
    public void setMin(long min) {
        this.min = min;
    }
    
    public long getMax() {
        return this.max;
    }
    
    public void setMax(long max) {
        this.max = max;
    }
}
