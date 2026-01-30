package org.come.entity;

import org.come.until.AllServiceUtil;

public class BuyCount
{
    private long Bid;
    private int Ptype;
    private int shopId;
    private int shopType;
    private long totalNum;
    private long totalPrice;
    private long weekNum;
    private long weekPrice;
    private long dayNum;
    private long dayPrice;
    private Boolean isUP;
    
    public BuyCount() {
        this.isUP = Boolean.valueOf(false);
    }
    
    public void addCount(int sum, long price) {
        synchronized (this.isUP) {
            this.totalNum += (long)sum;
            this.weekNum += (long)sum;
            this.dayNum += (long)sum;
            this.totalPrice += price;
            this.weekPrice += price;
            this.dayPrice += price;
            this.isUP = Boolean.valueOf(true);
        }
    }
    
    public void Reset(int day) {
        synchronized (this.isUP) {
            if (this.dayNum != 0L || (day == 2 && this.weekNum != 0L)) {
                this.isUP = Boolean.valueOf(true);
            }
            this.dayNum = 0L;
            this.dayPrice = 0L;
            if (day == 2) {
                this.weekNum = 0L;
                this.weekPrice = 0L;
            }
        }
    }
    
    public void upData() {
        synchronized (this.isUP) {
            if ((boolean)this.isUP) {
                AllServiceUtil.getBuyCountServeice().updateBuyCount(this);
                this.isUP = Boolean.valueOf(false);
            }
        }
    }
    
    public long countPrice(int num, int priceNum, long shopPrice, boolean isAdd) {
        synchronized (this.isUP) {
            long price = 0L;
            int i = 0;
            while (i < num) {
                if ((this.weekNum + (long)i) % (long)priceNum == 0L) {
                    int size = num - i;
                    if (size > priceNum) {
                        size = priceNum;
                    }
                    if (isAdd) {
                        price = (long)((double)price + (double)shopPrice * (1.0 + (double)((this.weekNum + (long)i) / (long)priceNum) * 0.05) * (double)size);
                    }
                    else {
                        price = (long)((double)price - (double)shopPrice * (1.0 + (double)((this.weekNum + (long)i) / (long)priceNum) * 0.05) * (double)size);
                    }
                    i += size;
                }
                else {
                    if (isAdd) {
                        price = (long)((double)price + (double)shopPrice * (1.0 + (double)((this.weekNum + (long)i) / (long)priceNum) * 0.05));
                    }
                    else {
                        price = (long)((double)price - (double)shopPrice * (1.0 + (double)((this.weekNum + (long)i) / (long)priceNum) * 0.05));
                    }
                    ++i;
                }
            }
            return price;
        }
    }
    
    public long getBid() {
        return this.Bid;
    }
    
    public void setBid(long bid) {
        this.Bid = bid;
    }
    
    public int getPtype() {
        return this.Ptype;
    }
    
    public void setPtype(int ptype) {
        this.Ptype = ptype;
    }
    
    public int getShopId() {
        return this.shopId;
    }
    
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
    
    public int getShopType() {
        return this.shopType;
    }
    
    public void setShopType(int shopType) {
        this.shopType = shopType;
    }
    
    public long getTotalNum() {
        return this.totalNum;
    }
    
    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
    
    public long getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public long getWeekNum() {
        return this.weekNum;
    }
    
    public void setWeekNum(long weekNum) {
        this.weekNum = weekNum;
    }
    
    public long getWeekPrice() {
        return this.weekPrice;
    }
    
    public void setWeekPrice(long weekPrice) {
        this.weekPrice = weekPrice;
    }
    
    public long getDayNum() {
        return this.dayNum;
    }
    
    public void setDayNum(long dayNum) {
        this.dayNum = dayNum;
    }
    
    public long getDayPrice() {
        return this.dayPrice;
    }
    
    public void setDayPrice(long dayPrice) {
        this.dayPrice = dayPrice;
    }
}
