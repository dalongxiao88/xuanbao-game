package org.come.bean;

import org.come.until.JmSum;

public class LxPointBean
{
    private Integer lv;
    private Integer tableId;
    private Integer maxPoint;
    private Integer nowPoint;
    
    public LxPointBean(int lv, int id, int max, int now) {
        this.lv = Integer.valueOf(lv);
        this.setTableId(Integer.valueOf(id));
        this.setMaxPoint(Integer.valueOf(max));
        this.setNowPoint(Integer.valueOf(now));
    }
    
    public Integer getLv() {
        return this.lv;
    }
    
    public void setLv(Integer lv) {
        this.lv = lv;
    }
    
    public Integer getTableId() {
        if (this.tableId == null) {
            this.setTableId(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.tableId));
    }
    
    public void setTableId(Integer tableId) {
        this.tableId = Integer.valueOf((int)JmSum.ZM((long)(int)tableId));
    }
    
    public Integer getMaxPoint() {
        if (this.maxPoint == null) {
            this.setMaxPoint(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.maxPoint));
    }
    
    public void setMaxPoint(Integer maxPoint) {
        this.maxPoint = Integer.valueOf((int)JmSum.ZM((long)(int)maxPoint));
    }
    
    public Integer getNowPoint() {
        if (this.nowPoint == null) {
            this.setNowPoint(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.nowPoint));
    }
    
    public void setNowPoint(Integer nowPoint) {
        this.nowPoint = Integer.valueOf((int)JmSum.ZM((long)(int)nowPoint));
    }
    
    public String setPoint(Integer nowPoint) {
        this.setNowPoint(nowPoint);
        if ((int)this.getNowPoint() > (int)this.getMaxPoint()) {
            nowPoint = this.maxPoint;
            return this.getMaxPoint().toString();
        }
        return this.getNowPoint() + "/" + this.getMaxPoint();
    }
}
