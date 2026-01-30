package org.come.bean;

import java.math.BigDecimal;

public class Goodsbuyrecordsumbean
{
    private BigDecimal gid;
    private String goodsname;
    private BigDecimal salesum;
    private int saleprice;
    private BigDecimal salesumprice;
    private String datetime;
    private int page;
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public BigDecimal getSalesum() {
        return this.salesum;
    }
    
    public void setSalesum(BigDecimal salesum) {
        this.salesum = salesum;
    }
    
    public int getSaleprice() {
        return this.saleprice;
    }
    
    public void setSaleprice(int saleprice) {
        this.saleprice = saleprice;
    }
    
    public BigDecimal getSalesumprice() {
        return this.salesumprice;
    }
    
    public void setSalesumprice(BigDecimal salesumprice) {
        this.salesumprice = salesumprice;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public String getGoodsname() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
}
