package org.come.entity;

import org.come.model.Shop;
import java.util.List;
import org.come.model.Lshop;
import java.util.Map;

public class ChongjipackBean
{
    private Integer id;
    private Integer packtype;
    private Integer packgradetype;
    private String packgrade;
    private String packgoods;
    private Integer getnumber;
    private String datetime;
    private Integer canpaymoney;
    private String huitime;
    private String endtime;
    private Map<Integer, Lshop> limitedTimeShop;
    private List<Shop> shops;
    private static final long serialVersionUID = 203745024714917095L;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPacktype() {
        return this.packtype;
    }
    
    public void setPacktype(Integer packtype) {
        this.packtype = packtype;
    }
    
    public Integer getPackgradetype() {
        return this.packgradetype;
    }
    
    public void setPackgradetype(Integer packgradetype) {
        this.packgradetype = packgradetype;
    }
    
    public String getPackgrade() {
        return this.packgrade;
    }
    
    public void setPackgrade(String packgrade) {
        this.packgrade = packgrade;
    }
    
    public String getPackgoods() {
        return this.packgoods;
    }
    
    public void setPackgoods(String packgoods) {
        this.packgoods = packgoods;
    }
    
    public Integer getGetnumber() {
        return this.getnumber;
    }
    
    public void setGetnumber(Integer getnumber) {
        this.getnumber = getnumber;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public Integer getCanpaymoney() {
        return this.canpaymoney;
    }
    
    public void setCanpaymoney(Integer canpaymoney) {
        this.canpaymoney = canpaymoney;
    }
    
    public String getHuitime() {
        return this.huitime;
    }
    
    public void setHuitime(String huitime) {
        this.huitime = huitime;
    }
    
    public String getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    
    public Map<Integer, Lshop> getLimitedTimeShop() {
        return this.limitedTimeShop;
    }
    
    public void setLimitedTimeShop(Map<Integer, Lshop> limitedTimeShop) {
        this.limitedTimeShop = limitedTimeShop;
    }
    
    public List<Shop> getShops() {
        return this.shops;
    }
    
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
