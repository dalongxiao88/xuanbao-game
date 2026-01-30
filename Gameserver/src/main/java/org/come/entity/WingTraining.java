package org.come.entity;

public class WingTraining
{
    private int id;
    private String text;
    private String type;
    private String base;
    private long value;
    private int un;
    private double[] bases;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getBase() {
        return this.base;
    }
    
    public void setBase(String base) {
        this.base = base;
    }
    
    public long getValue() {
        return this.value;
    }
    
    public void setValue(long value) {
        this.value = value;
    }
    
    public int getUn() {
        return this.un;
    }
    
    public void setUn(int un) {
        this.un = un;
    }
    
    public double[] getBases() {
        return this.bases;
    }
    
    public void setBases(double[] bases) {
        this.bases = bases;
    }
}
