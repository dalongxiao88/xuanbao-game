package org.come.entity;

import org.come.model.Lingbao;
import java.math.BigDecimal;

public class Hang
{
    private BigDecimal id;
    private BigDecimal mid;
    
    public Hang(Lingbao lingbao, int is) {
        this.id = lingbao.getBaoid();
    }
    
    public Hang(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
}
