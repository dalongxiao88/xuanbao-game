package org.come.bean;

public class BuyBtnGetBean
{
    private String inGetPeoSum;
    private String goodsRoleName;
    private boolean flag;
    
    public boolean isFlag() {
        return this.flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public String getInGetPeoSum() {
        return this.inGetPeoSum;
    }
    
    public void setInGetPeoSum(String inGetPeoSum) {
        this.inGetPeoSum = inGetPeoSum;
    }
    
    public String getGoodsRoleName() {
        return this.goodsRoleName;
    }
    
    public void setGoodsRoleName(String goodsRoleName) {
        this.goodsRoleName = goodsRoleName;
    }
}
