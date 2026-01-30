package org.come.entity;

public class SellLiangHaoBase
{
    private int id;
    private String lianghao;
    private int type;
    private int price;
    private int aucPrice;
    private String aucEndTime;
    private String aucStartTime;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLianghao() {
        return this.lianghao;
    }
    
    public void setLianghao(String lianghao) {
        this.lianghao = lianghao;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getAucPrice() {
        return this.aucPrice;
    }
    
    public void setAucPrice(int aucPrice) {
        this.aucPrice = aucPrice;
    }
    
    public String getAucEndTime() {
        return this.aucEndTime;
    }
    
    public void setAucEndTime(String aucEndTime) {
        this.aucEndTime = aucEndTime;
    }
    
    public String getAucStartTime() {
        return this.aucStartTime;
    }
    
    public void setAucStartTime(String aucStartTime) {
        this.aucStartTime = aucStartTime;
    }
}
