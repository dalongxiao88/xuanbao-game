package org.come.bean;

import java.math.BigDecimal;

public class UserTable
{
    private BigDecimal user_id;
    private String username;
    private String userpwd;
    private Short activity;
    private Long vip;
    private BigDecimal frient_id;
    private String safety;
    private Long idcard;
    private BigDecimal qid;
    private String phonenumber;
    private String phonetime;
    private String tuiji;
    
    public BigDecimal getQid() {
        return this.qid;
    }
    
    public void setQid(BigDecimal qid) {
        this.qid = qid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = ((username == null) ? null : username.trim());
    }
    
    public String getUserpwd() {
        return this.userpwd;
    }
    
    public void setUserpwd(String userpwd) {
        this.userpwd = ((userpwd == null) ? null : userpwd.trim());
    }
    
    public Short getActivity() {
        return this.activity;
    }
    
    public void setActivity(Short activity) {
        this.activity = activity;
    }
    
    public Long getVip() {
        return this.vip;
    }
    
    public void setVip(Long vip) {
        this.vip = vip;
    }
    
    public BigDecimal getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }
    
    public BigDecimal getFrient_id() {
        return this.frient_id;
    }
    
    public void setFrient_id(BigDecimal frient_id) {
        this.frient_id = frient_id;
    }
    
    public String getSafety() {
        return this.safety;
    }
    
    public void setSafety(String safety) {
        this.safety = ((safety == null) ? null : safety.trim());
    }
    
    public Long getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(Long idcard) {
        this.idcard = idcard;
    }
    
    public String getPhonenumber() {
        return this.phonenumber;
    }
    
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    public String getPhonetime() {
        return this.phonetime;
    }
    
    public void setPhonetime(String phonetime) {
        this.phonetime = phonetime;
    }
    
    public String getTuiji() {
        return this.tuiji;
    }
    
    public void setTuiji(String tuiji) {
        this.tuiji = tuiji;
    }
}
