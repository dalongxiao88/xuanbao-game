package org.come.model;

import org.come.entity.BuyCount;
import java.math.BigDecimal;

public class Shop
{
    private String shopid;
    private BigDecimal shopiid;
    private String shopname;
    private int shoptype;
    private long shopprice;
    private String shopskin;
    private String shoptext;
    private transient String lvl;
    private transient int num;
    private transient int priceNum;
    private transient int[] lvls;
    private Boolean isPrice;
    private transient BuyCount buyCount;
    private String limitTime;
    private Integer nid;
    
    public long getPrice() {
        if (this.priceNum == 0) {
            return this.shopprice;
        }
        boolean isAdd = this.priceNum >= 0;
        return this.buyCount.countPrice(1, Math.abs(this.priceNum), this.shopprice, isAdd);
    }
    
    public long getPrice(int num) {
        if (this.priceNum == 0) {
            return this.shopprice * (long)num;
        }
        boolean isAdd = this.priceNum >= 0;
        return this.buyCount.countPrice(num, Math.abs(this.priceNum), this.shopprice, isAdd);
    }
    
    public boolean addPrice(int num, long jg) {
        if (this.priceNum == 0) {
            this.buyCount.addCount(num, jg);
            return false;
        }
        int size = (int)(this.buyCount.getWeekNum() / (long)this.priceNum);
        this.buyCount.addCount(num, jg);
        return this.buyCount.getWeekNum() / (long)this.priceNum != (long)size;
    }
    
    public String getShopid() {
        return this.shopid;
    }
    
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
    
    public BigDecimal getShopiid() {
        return this.shopiid;
    }
    
    public void setShopiid(BigDecimal shopiid) {
        this.shopiid = shopiid;
    }
    
    public String getShopname() {
        return this.shopname;
    }
    
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    
    public int getShoptype() {
        return this.shoptype;
    }
    
    public void setShoptype(int shoptype) {
        this.shoptype = shoptype;
    }
    
    public long getShopprice() {
        return this.shopprice;
    }
    
    public void setShopprice(long shopprice) {
        this.shopprice = shopprice;
    }
    
    public String getShopskin() {
        return this.shopskin;
    }
    
    public void setShopskin(String shopskin) {
        this.shopskin = shopskin;
    }
    
    public String getShoptext() {
        return this.shoptext;
    }
    
    public void setShoptext(String shoptext) {
        this.shoptext = shoptext;
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
    
    public int getPriceNum() {
        return this.priceNum;
    }
    
    public void setPriceNum(int priceNum) {
        this.priceNum = priceNum;
    }
    
    public BuyCount getBuyCount() {
        return this.buyCount;
    }
    
    public void setBuyCount(BuyCount buyCount) {
        this.buyCount = buyCount;
    }
    
    public Boolean getIsPrice() {
        return this.isPrice;
    }
    
    public void setIsPrice(Boolean isPrice) {
        this.isPrice = isPrice;
    }
    
    public String getLimitTime() {
        return this.limitTime;
    }
    
    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }
    
    public Integer getNid() {
        return this.nid;
    }
    
    public void setNid(Integer nid) {
        this.nid = nid;
    }
}
