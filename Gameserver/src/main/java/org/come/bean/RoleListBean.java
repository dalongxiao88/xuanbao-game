package org.come.bean;

import java.math.BigDecimal;
import java.util.List;

public class RoleListBean
{
    private List<Role_bean> roleList;
    private int statues;
    private BigDecimal userId;
    private double usermoney;
    private String qid;
    private String phone;
    
    public double getUsermoney() {
        return this.usermoney;
    }
    
    public void setUsermoney(double usermoney) {
        this.usermoney = usermoney;
    }
    
    public List<Role_bean> getRoleList() {
        return this.roleList;
    }
    
    public void setRoleList(List<Role_bean> roleList) {
        this.roleList = roleList;
    }
    
    public int getStatues() {
        return this.statues;
    }
    
    public void setStatues(int statues) {
        this.statues = statues;
    }
    
    public BigDecimal getUserId() {
        return this.userId;
    }
    
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }
    
    public String getQid() {
        return this.qid;
    }
    
    public void setQid(String qid) {
        this.qid = qid;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
