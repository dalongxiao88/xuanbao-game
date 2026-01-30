package org.come.bean;

import java.math.BigDecimal;

public class LingBaoEquipment
{
    private BigDecimal lingbaoid;
    private BigDecimal fabaoid1;
    private BigDecimal fabaoid2;
    
    public BigDecimal getLingbaoid() {
        return this.lingbaoid;
    }
    
    public void setLingbaoid(BigDecimal lingbaoid) {
        this.lingbaoid = lingbaoid;
    }
    
    public BigDecimal getFabaoid1() {
        return this.fabaoid1;
    }
    
    public void setFabaoid1(BigDecimal fabaoid1) {
        this.fabaoid1 = fabaoid1;
    }
    
    public BigDecimal getFabaoid2() {
        return this.fabaoid2;
    }
    
    public void setFabaoid2(BigDecimal fabaoid2) {
        this.fabaoid2 = fabaoid2;
    }
}
