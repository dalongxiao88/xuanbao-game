package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Goodsrecord
{
    private Integer grid;
    private Integer recordtype;
    private BigDecimal roleid;
    private BigDecimal otherrole;
    private String goods;
    private Date recordtime;
    private String recordtimeString;
    private Integer goodsnum;
    private String rolename;
    private String othername;
    
    public String getRecordtimeString() {
        return this.recordtimeString;
    }
    
    public void setRecordtimeString(String recordtimeString) {
        this.recordtimeString = recordtimeString;
    }
    
    public Integer getGrid() {
        return this.grid;
    }
    
    public void setGrid(Integer grid) {
        this.grid = grid;
    }
    
    public Integer getRecordtype() {
        return this.recordtype;
    }
    
    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getOtherrole() {
        return this.otherrole;
    }
    
    public void setOtherrole(BigDecimal otherrole) {
        this.otherrole = otherrole;
    }
    
    public String getGoods() {
        return this.goods;
    }
    
    public void setGoods(String goods) {
        this.goods = ((goods == null) ? null : goods.trim());
    }
    
    public Date getRecordtime() {
        return this.recordtime;
    }
    
    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }
    
    public Integer getGoodsnum() {
        return this.goodsnum;
    }
    
    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getOthername() {
        return this.othername;
    }
    
    public void setOthername(String othername) {
        this.othername = othername;
    }
}
