package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class UserTable
{
    private BigDecimal user_id;
    private String username;
    private String userpwd;
    private Short activity;
    private Integer type;
    private Long vip;
    private BigDecimal frient_id;
    private String safety;
    private Long idcard;
    private BigDecimal codecard;
    private BigDecimal savegold;
    private Integer money;
    private BigDecimal qid;
    private Double usermoney;
    private int start;
    private int end;
    private Integer payintegration;
    private String Userregidtsertime;
    private String USERLASTLOGIN;
    private String loginip;
    private String registerip;
    private String phonenumber;
    private String phonetime;
    private String useString;
    private String tuiji;
    private String flag;
    
    public UserTable() {
        this.payintegration = null;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(new Date(1567177597619L));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getLoginip() {
        return this.loginip;
    }
    
    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }
    
    public String getRegisterip() {
        return this.registerip;
    }
    
    public void setRegisterip(String registerip) {
        this.registerip = registerip;
    }
    
    public String getUseString() {
        return this.useString;
    }
    
    public void setUseString(String useString) {
        this.useString = useString;
    }
    
    public int getStart() {
        return this.start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return this.end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    public Double getUsermoney() {
        return this.usermoney;
    }
    
    public void setUsermoney(Double usermoney) {
        this.usermoney = usermoney;
    }
    
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
    
    public BigDecimal getCodecard() {
        return this.codecard;
    }
    
    public void setCodecard(BigDecimal codecard) {
        this.codecard = codecard;
    }
    
    public BigDecimal getSavegold() {
        return this.savegold;
    }
    
    public void setSavegold(BigDecimal savegold) {
        this.savegold = savegold;
    }
    
    public Integer getMoney() {
        return this.money;
    }
    
    public void setMoney(Integer money) {
        this.money = money;
    }
    
    public Integer getPayintegration() {
        if (this.payintegration == null) {
            this.payintegration = Integer.valueOf(0);
        }
        return this.payintegration;
    }
    
    public void setPayintegration(Integer payintegration) {
        this.payintegration = payintegration;
    }
    
    public String getUserregidtsertime() {
        return this.Userregidtsertime;
    }
    
    public void setUserregidtsertime(String userregidtsertime) {
        this.Userregidtsertime = userregidtsertime;
    }
    
    public String getUSERLASTLOGIN() {
        return this.USERLASTLOGIN;
    }
    
    public void setUSERLASTLOGIN(String uSERLASTLOGIN) {
        this.USERLASTLOGIN = uSERLASTLOGIN;
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
    
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "UserTable [user_id=" + this.user_id + ", username=" + this.username + ", userpwd=" + this.userpwd + ", activity=" + this.activity + ", vip=" + this.vip + ", frient_id=" + this.frient_id + ", safety=" + this.safety + ", idcard=" + this.idcard + ", codecard=" + this.codecard + ", money=" + this.money + ", qid=" + this.qid + ", usermoney=" + this.usermoney + ", start=" + this.start + ", end=" + this.end + ", payintegration=" + this.payintegration + ", Userregidtsertime=" + this.Userregidtsertime + ", USERLASTLOGIN=" + this.USERLASTLOGIN + ", loginip=" + this.loginip + ", registerip=" + this.registerip + ", phonenumber=" + this.phonenumber + ", phonetime=" + this.phonetime + ", useString=" + this.useString + "]";
    }
}
