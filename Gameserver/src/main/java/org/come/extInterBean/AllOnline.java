package org.come.extInterBean;

import java.util.Date;

public class AllOnline
{
    private String areaName;
    private String nowOnline;
    private String maxOnline;
    private Date recordTime;
    
    public String getAreaName() {
        return this.areaName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public String getNowOnline() {
        return this.nowOnline;
    }
    
    public void setNowOnline(String nowOnline) {
        this.nowOnline = nowOnline;
    }
    
    public String getMaxOnline() {
        return this.maxOnline;
    }
    
    public void setMaxOnline(String maxOnline) {
        this.maxOnline = maxOnline;
    }
    
    public Date getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
