package org.come.model;

import org.come.bean.AutoActiveBase;

public class AutoTaskBase
{
    private int id;
    private String aName;
    private int sid;
    private String guide;
    private int autoNum;
    
    public AutoTaskBase(AutoActiveBase activeBase, int autoNum) {
        this.id = activeBase.getId();
        this.aName = activeBase.getaName();
        this.sid = activeBase.getTasksetId();
        this.guide = activeBase.getGuide();
        this.autoNum = autoNum;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getaName() {
        return this.aName;
    }
    
    public void setaName(String aName) {
        this.aName = aName;
    }
    
    public int getSid() {
        return this.sid;
    }
    
    public void setSid(int sid) {
        this.sid = sid;
    }
    
    public String getGuide() {
        return this.guide;
    }
    
    public void setGuide(String guide) {
        this.guide = guide;
    }
    
    public int getAutoNum() {
        return this.autoNum;
    }
    
    public void setAutoNum(int autoNum) {
        this.autoNum = autoNum;
    }
}
