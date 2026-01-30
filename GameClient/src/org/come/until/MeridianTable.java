package org.come.until;

import java.util.Objects;

public class MeridianTable
{
    private int id;
    private String attribute;
    private double min;
    private double max;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAttribute() {
        return this.attribute;
    }
    
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public void setMin(double min) {
        this.min = min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public void setMax(double max) {
        this.max = max;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        MeridianTable that = (MeridianTable)o;
        return this.id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(new Object[] { Integer.valueOf(this.id) });
    }
}
