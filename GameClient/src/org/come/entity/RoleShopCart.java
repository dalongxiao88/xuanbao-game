package org.come.entity;

import java.math.BigDecimal;

public class RoleShopCart
{
    private BigDecimal S_cart_Id;
    private BigDecimal Goods_id;
    private String Goods_name;
    private String Skin;
    private String Price;
    private Integer Gnumber;
    private BigDecimal Role_id;
    private Integer Type;
    private String InTime;
    
    public BigDecimal getS_cart_Id() {
        return this.S_cart_Id;
    }
    
    public void setS_cart_Id(BigDecimal s_cart_Id) {
        this.S_cart_Id = s_cart_Id;
    }
    
    public BigDecimal getGoods_id() {
        return this.Goods_id;
    }
    
    public void setGoods_id(BigDecimal goods_id) {
        this.Goods_id = goods_id;
    }
    
    public String getGoods_name() {
        return this.Goods_name;
    }
    
    public void setGoods_name(String goods_name) {
        this.Goods_name = goods_name;
    }
    
    public String getSkin() {
        return this.Skin;
    }
    
    public void setSkin(String skin) {
        this.Skin = skin;
    }
    
    public String getPrice() {
        return this.Price;
    }
    
    public void setPrice(String price) {
        this.Price = price;
    }
    
    public Integer getGnumber() {
        return this.Gnumber;
    }
    
    public void setGnumber(Integer gnumber) {
        this.Gnumber = gnumber;
    }
    
    public BigDecimal getRole_id() {
        return this.Role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.Role_id = role_id;
    }
    
    public String getInTime() {
        return this.InTime;
    }
    
    public void setInTime(String inTime) {
        this.InTime = inTime;
    }
    
    public Integer getType() {
        return this.Type;
    }
    
    public void setType(Integer type) {
        this.Type = type;
    }
}
