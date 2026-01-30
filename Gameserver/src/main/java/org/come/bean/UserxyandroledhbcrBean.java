package org.come.bean;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class UserxyandroledhbcrBean
{
    private BigDecimal id;
    private String username;
    private BigDecimal userid;
    private List<BigDecimal> xsum;
    private List<BigDecimal> xnow;
    private List<BigDecimal> xdsum;
    private List<BigDecimal> dsum;
    private List<BigDecimal> sssum;
    private List<String> time;
    
    public UserxyandroledhbcrBean(String username, BigDecimal userid, BigDecimal xsum, BigDecimal xnow, BigDecimal xdsum, BigDecimal dsum, BigDecimal sssum, String time) {
        this.username = username;
        this.userid = userid;
        this.getXsum().add(xsum);
        this.getXdsum().add(xdsum);
        this.getXnow().add(xnow);
        this.getSssum().add(sssum);
        this.getTime().add(time);
        this.getDsum().add(dsum);
    }
    
    public UserxyandroledhbcrBean() {
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<BigDecimal> getDsum() {
        if (this.dsum == null) {
            this.dsum = new ArrayList<>();
        }
        return this.dsum;
    }
    
    public List<BigDecimal> getXsum() {
        if (this.xsum == null) {
            this.xsum = new ArrayList<>();
        }
        return this.xsum;
    }
    
    public void setXsum(List<BigDecimal> xsum) {
        this.xsum = xsum;
    }
    
    public void setDsum(List<BigDecimal> dsum) {
        this.dsum = dsum;
    }
    
    public List<BigDecimal> getXnow() {
        if (this.xnow == null) {
            this.xnow = new ArrayList<>();
        }
        return this.xnow;
    }
    
    public void setXnow(List<BigDecimal> xnow) {
        this.xnow = xnow;
    }
    
    public List<BigDecimal> getXdsum() {
        if (this.xdsum == null) {
            this.xdsum = new ArrayList<>();
        }
        return this.xdsum;
    }
    
    public void setXdsum(List<BigDecimal> xdsum) {
        this.xdsum = xdsum;
    }
    
    public List<BigDecimal> getSssum() {
        if (this.sssum == null) {
            this.sssum = new ArrayList<>();
        }
        return this.sssum;
    }
    
    public void setSssum(List<BigDecimal> sssum) {
        this.sssum = sssum;
    }
    
    public List<String> getTime() {
        if (this.time == null) {
            this.time = new ArrayList<>();
        }
        return this.time;
    }
    
    public void setTime(List<String> time) {
        this.time = time;
    }
    
    public BigDecimal getUserid() {
        return this.userid;
    }
    
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
    
    @Override
    public String toString() {
        return "UserxyandroledhbcrBean [id=" + this.id + ", username=" + this.username + ", userid=" + this.userid + ", xsum=" + this.xsum + ", xnow=" + this.xnow + ", xdsum=" + this.xdsum + ", dsum=" + this.dsum + ", sssum=" + this.sssum + ", time=" + this.time + "]";
    }
}
