package com.tool.role;

public class BaseXingpans
{
    private int bh;
    private String exp;
    private int xs;
    private String key;
    private double value;
    private String key1;
    private double value1;
    
    public BaseXingpans(int bh, String exp, int xs, String key, double value, String key1, double value1) {
        this.bh = bh;
        this.exp = exp;
        this.init(xs, key, value, key1, value1);
    }
    
    public void init(int xs, String key, double value, String key1, double value1) {
        this.xs = xs;
        this.key = key;
        this.value = value;
        this.key1 = key1;
        this.value1 = value1;
    }
    
    public int getBh() {
        return this.bh;
    }
    
    public int getXs() {
        return this.xs;
    }
    
    public void setXs(int xs) {
        this.xs = xs;
    }
    
    public String getExp() {
        return this.exp;
    }
    
    public void setExp(String exp) {
        this.exp = exp;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getKey1() {
        return this.key1;
    }
    
    public double getKeyValue() {
        return this.value * 0.4 + this.value * (double)this.xs * 0.04;
    }
    
    public double getKeyValue1() {
        return this.value1 * 0.4 + this.value1 * (double)this.xs * 0.04;
    }
}
