package org.come.bean;

public class ColorScheme
{
    private int id;
    private String name;
    private String value;
    private int min;
    private int max;
    private int zid;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public int getZid() {
        return this.zid;
    }
    
    public void setZid(int zid) {
        this.zid = zid;
    }
}
