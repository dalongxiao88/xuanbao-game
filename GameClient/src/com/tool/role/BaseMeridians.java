package com.tool.role;

import org.come.until.UserMessUntil;

public class BaseMeridians
{
    private int id;
    private int exp;
    private int quality;
    private String key;
    private double value;
    private int stage;
    
    public BaseMeridians(int id, int exp, int quality, String key, double value, int stage) {
        this.id = id;
        this.exp = exp;
        this.quality = quality;
        this.key = key;
        this.value = value;
        this.stage = stage;
    }
    
    public double getKeyValue() {
        String[] ss = UserMessUntil.getAllMeridians().getByQuality(this.id, this.quality).getStages().split("_");
        String s = ss[this.stage];
        return this.value * (1.0 + Double.parseDouble(s));
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public void setStage(int stage) {
        this.stage = stage;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public void setQuality(int quality) {
        this.quality = quality;
    }
}
