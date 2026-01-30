package org.come.model;

import java.math.BigDecimal;

public class GemBase
{
    private String type;
    private double value;
    
    public GemBase(String type, double value) {
        this.type = type;
        this.value = value;
    }
    
    public String getGemValue(int lvl, int G) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("等级=");
        buffer.append(lvl);
        buffer.append("|");
        buffer.append(this.type);
        buffer.append("=");
        BigDecimal big = new BigDecimal((double)(lvl * G) / 100.0 * this.value);
        if (big.doubleValue() == (double)big.longValue()) {
            buffer.append(big.longValue());
        }
        else {
            buffer.append(big.setScale(1, 4).doubleValue());
        }
        buffer.append("|价值=");
        buffer.append(G);
        return buffer.toString();
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
}
