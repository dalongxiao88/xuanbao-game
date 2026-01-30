package org.come.action.exchange;

import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;

public class Compensation
{
    private String CCDK;
    private BigDecimal goodId;
    private long Cmin;
    private long Cmax;
    private ConcurrentHashMap<BigDecimal, Integer> Cmap;
    
    public Compensation() {
        this.Cmap = new ConcurrentHashMap<>();
    }
    
    public boolean contain(long time) {
        return time > this.Cmin && time < this.Cmax;
    }
    
    public void addMap(BigDecimal ID) {
        if (this.Cmap.get(ID) == null) {
            this.Cmap.put(ID, Integer.valueOf(0));
        }
    }
    
    public int receive(BigDecimal ID) {
        Integer id = (Integer)this.Cmap.get(ID);
        if (id == null) {
            return 0;
        }
        if ((int)id == 0) {
            this.Cmap.put(ID, Integer.valueOf(1));
            return 1;
        }
        return 2;
    }
    
    public String getCCDK() {
        return this.CCDK;
    }
    
    public void setCCDK(String cCDK) {
        this.CCDK = cCDK;
    }
    
    public long getCmin() {
        return this.Cmin;
    }
    
    public void setCmin(long cmin) {
        this.Cmin = cmin;
    }
    
    public long getCmax() {
        return this.Cmax;
    }
    
    public void setCmax(long cmax) {
        this.Cmax = cmax;
    }
    
    public BigDecimal getGoodId() {
        return this.goodId;
    }
    
    public void setGoodId(BigDecimal goodId) {
        this.goodId = goodId;
    }
}
