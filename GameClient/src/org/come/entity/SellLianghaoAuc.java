package org.come.entity;

import java.math.BigDecimal;

public class SellLianghaoAuc
{
    private BigDecimal id;
    private BigDecimal buyRoleId;
    private BigDecimal aucPoint;
    private String lianghao;
    private String buyTime;
    private String aucEndTime;
    private Short status;
    private Integer originalprice;
    
    public Integer getOriginalprice() {
        return this.originalprice;
    }
    
    public void setOriginalprice(Integer originalprice) {
        this.originalprice = originalprice;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getBuyRoleId() {
        return this.buyRoleId;
    }
    
    public void setBuyRoleId(BigDecimal buyRoleId) {
        this.buyRoleId = buyRoleId;
    }
    
    public BigDecimal getAucPoint() {
        return this.aucPoint;
    }
    
    public void setAucPoint(BigDecimal aucPoint) {
        this.aucPoint = aucPoint;
    }
    
    public String getLianghao() {
        return this.lianghao;
    }
    
    public void setLianghao(String lianghao) {
        this.lianghao = ((lianghao == null) ? null : lianghao.trim());
    }
    
    public String getBuyTime() {
        return this.buyTime;
    }
    
    public void setBuyTime(String buyTime) {
        this.buyTime = ((buyTime == null) ? null : buyTime.trim());
    }
    
    public String getAucEndTime() {
        return this.aucEndTime;
    }
    
    public void setAucEndTime(String aucEndTime) {
        this.aucEndTime = ((aucEndTime == null) ? null : aucEndTime.trim());
    }
    
    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }
}
