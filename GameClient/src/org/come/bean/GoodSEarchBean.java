package org.come.bean;

import java.util.List;

public class GoodSEarchBean
{
    private int type;
    private String goodname;
    private List returnListBean;
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getGoodname() {
        return this.goodname;
    }
    
    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }
    
    public List getReturnListBean() {
        return this.returnListBean;
    }
    
    public void setReturnListBean(List returnListBean) {
        this.returnListBean = returnListBean;
    }
}
