package org.come.extInterBean;

import java.util.Date;
import java.math.BigDecimal;

public class AreaNumRecord
{
    private String area_name;
    private String atid;
    private BigDecimal maxonline;
    private Date recortime;
    private String memo;
    private BigDecimal maxnum;
    
    public String getArea_name() {
        return this.area_name;
    }
    
    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
    
    public BigDecimal getMaxonline() {
        return this.maxonline;
    }
    
    public void setMaxonline(BigDecimal maxonline) {
        this.maxonline = maxonline;
    }
    
    public Date getRecortime() {
        return this.recortime;
    }
    
    public void setRecortime(Date recortime) {
        this.recortime = recortime;
    }
    
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getAtid() {
        return this.atid;
    }
    
    public void setAtid(String atid) {
        this.atid = atid;
    }
    
    public BigDecimal getMaxnum() {
        return this.maxnum;
    }
    
    public void setMaxnum(BigDecimal maxnum) {
        this.maxnum = maxnum;
    }
}
