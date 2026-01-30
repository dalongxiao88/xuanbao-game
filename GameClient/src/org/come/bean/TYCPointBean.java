package org.come.bean;

import org.come.until.JmSum;

public class TYCPointBean
{
    private Integer row;
    private Integer rank;
    private Integer tableId;
    private Integer maxPoint;
    private Integer nowPoint;
    private TYCTypeBean tycTypeBean;
    
    public Integer getRow() {
        if (this.row == null) {
            this.setRow(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.row));
    }
    
    public void setRow(Integer row) {
        this.row = Integer.valueOf((int)JmSum.ZM((long)(int)row));
    }
    
    public Integer getRank() {
        if (this.rank == null) {
            this.setRank(Integer.valueOf(0));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.rank));
    }
    
    public void setRank(Integer rank) {
        this.rank = Integer.valueOf((int)JmSum.ZM((long)(int)rank));
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
    
    public TYCTypeBean getTycTypeBean() {
        return this.tycTypeBean;
    }
    
    public void setTycTypeBean(TYCTypeBean tycTypeBean) {
        this.tycTypeBean = tycTypeBean;
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
