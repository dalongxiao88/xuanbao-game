package org.come.model;

import org.come.entity.BuyCount;
import java.math.BigDecimal;

public class Eshop
{
    private String eshopid;
    private BigDecimal eshopiid;
    private String eshopname;
    private String eshoptype;
    private long eshopprice;
    private String eshopskin;
    private String eshoptext;
    private transient String lvl;
    private transient int num;
    private transient int[] lvls;
    private transient BuyCount buyCount;
    
    public boolean addPrice(int num, long jg) {
        this.buyCount.addCount(num, jg);
        return false;
    }
    
    public String getEshopid() {
        return this.eshopid;
    }
    
    public void setEshopid(String eshopid) {
        this.eshopid = eshopid;
    }
    
    public BigDecimal getEshopiid() {
        return this.eshopiid;
    }
    
    public void setEshopiid(BigDecimal eshopiid) {
        this.eshopiid = eshopiid;
    }
    
    public String getEshopname() {
        return this.eshopname;
    }
    
    public void setEshopname(String eshopname) {
        this.eshopname = eshopname;
    }
    
    public String getEshoptype() {
        return this.eshoptype;
    }
    
    public void setEshoptype(String eshoptype) {
        this.eshoptype = eshoptype;
    }
    
    public long getEshopprice() {
        return this.eshopprice;
    }
    
    public void setEshopprice(long eshopprice) {
        this.eshopprice = eshopprice;
    }
    
    public String getEshopskin() {
        return this.eshopskin;
    }
    
    public void setEshopskin(String eshopskin) {
        this.eshopskin = eshopskin;
    }
    
    public String getEshoptext() {
        return this.eshoptext;
    }
    
    public void setEshoptext(String eshoptext) {
        this.eshoptext = eshoptext;
    }
    
    public String getLvl() {
        return this.lvl;
    }
    
    public void setLvl(String lvl) {
        this.lvl = lvl;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public int[] getLvls() {
        return this.lvls;
    }
    
    public void setLvls(int[] lvls) {
        this.lvls = lvls;
    }
    
    public BuyCount getBuyCount() {
        return this.buyCount;
    }
    
    public void setBuyCount(BuyCount buyCount) {
        this.buyCount = buyCount;
    }
}
