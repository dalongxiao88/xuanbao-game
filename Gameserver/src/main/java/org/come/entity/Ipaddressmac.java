package org.come.entity;

import java.math.BigDecimal;

public class Ipaddressmac
{
    private BigDecimal ipid;
    private String addresskey;
    private String ctime;
    
    public BigDecimal getIpid() {
        return this.ipid;
    }
    
    public void setIpid(BigDecimal ipid) {
        this.ipid = ipid;
    }
    
    public String getAddresskey() {
        return this.addresskey;
    }
    
    public void setAddresskey(String addresskey) {
        this.addresskey = ((addresskey == null) ? null : addresskey.trim());
    }
    
    public String getCtime() {
        return this.ctime;
    }
    
    public void setCtime(String ctime) {
        this.ctime = ((ctime == null) ? null : ctime.trim());
    }
}
