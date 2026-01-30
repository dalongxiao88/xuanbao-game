package org.come.agent;

import java.math.BigDecimal;
import java.util.Date;

public class Agent
{
    private Integer agentId;
    private String userName;
    private String password;
    private String qid;
    private String tel;
    private Date createTime;
    private String jurisdiction;
    private BigDecimal xianyu;
    private BigDecimal jf;
    
    public Integer getAgentId() {
        return this.agentId;
    }
    
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getQid() {
        return this.qid;
    }
    
    public void setQid(String qid) {
        this.qid = qid;
    }
    
    public Date getCreatTime() {
        return this.createTime;
    }
    
    public void setCreatTime(Date creatTime) {
        this.createTime = creatTime;
    }
    
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getJurisdiction() {
        return this.jurisdiction;
    }
    
    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    
    public BigDecimal getXianyu() {
        return this.xianyu;
    }
    
    public void setXianyu(BigDecimal xianyu) {
        this.xianyu = xianyu;
    }
    
    public BigDecimal getJf() {
        return this.jf;
    }
    
    public void setJf(BigDecimal jf) {
        this.jf = jf;
    }
}
