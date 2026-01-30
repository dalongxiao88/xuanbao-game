package org.come.extInterBean;

import java.math.BigDecimal;

public class Goodsrecord2
{
    private Long grid;
    private Integer recordtype;
    private BigDecimal roleid;
    private BigDecimal otherrole;
    private String goods;
    private String recordtime;
    private Integer goodsnum;
    private Integer sid;
    private String goodsname;
    private String value;
    private String usetime;
    private BigDecimal goodsid;
    private String skin;
    private Long type;
    private Long quality;
    private String instruction;
    private BigDecimal rgid;
    private Integer status;
    private BigDecimal defineprice;
    private Integer goodlock;
    private String roleName;
    private String otherRole;
    
    public Goodsrecord2() {
        this.grid = Long.valueOf(0L);
        this.recordtype = Integer.valueOf(0);
        this.roleid = new BigDecimal(0);
        this.otherrole = new BigDecimal(0);
        this.goods = "0";
        this.goodsnum = Integer.valueOf(0);
        this.sid = Integer.valueOf(0);
        this.goodsname = "0";
        this.value = "0";
        this.usetime = "0";
        this.goodsid = new BigDecimal(0);
        this.skin = "0";
        this.type = Long.valueOf(0L);
        this.quality = Long.valueOf(0L);
        this.rgid = new BigDecimal(0);
        this.status = Integer.valueOf(0);
        this.defineprice = new BigDecimal(0);
        this.goodlock = Integer.valueOf(0);
        this.roleName = "0";
        this.otherRole = "0";
    }
    
    public Long getGrid() {
        if (this.grid == null) {
            this.grid = Long.valueOf(0L);
        }
        return this.grid;
    }
    
    public void setGrid(Long grid) {
        this.grid = grid;
    }
    
    public Integer getRecordtype() {
        if (this.recordtype == null) {
            this.recordtype = Integer.valueOf(0);
        }
        return this.recordtype;
    }
    
    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }
    
    public BigDecimal getRoleid() {
        if (this.roleid == null) {
            this.roleid = new BigDecimal(0);
        }
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getOtherrole() {
        if (this.otherrole == null) {
            this.otherrole = new BigDecimal(0);
        }
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
    
    public String getRecordtime() {
        return this.recordtime;
    }
    
    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }
    
    public Integer getGoodsnum() {
        if (this.goodsnum == null) {
            this.goodsnum = Integer.valueOf(0);
        }
        return this.goodsnum;
    }
    
    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }
    
    public Integer getSid() {
        if (this.sid == null) {
            this.sid = Integer.valueOf(0);
        }
        return this.sid;
    }
    
    public void setSid(Integer sid) {
        this.sid = sid;
    }
    
    public String getGoodsname() {
        if (this.goodsname == null) {
            this.goodsname = "0";
        }
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = ((goodsname == null) ? null : goodsname.trim());
    }
    
    public String getValue() {
        if (this.value == null) {
            this.value = "0";
        }
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = ((value == null) ? null : value.trim());
    }
    
    public String getUsetime() {
        if (this.usetime == null) {
            this.usetime = "0";
        }
        return this.usetime;
    }
    
    public void setUsetime(String usetime) {
        this.usetime = ((usetime == null) ? null : usetime.trim());
    }
    
    public BigDecimal getGoodsid() {
        if (this.goodsid == null) {
            this.goodsid = new BigDecimal(0);
        }
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = ((skin == null) ? null : skin.trim());
    }
    
    public Long getType() {
        if (this.type == null) {
            this.type = Long.valueOf(0L);
        }
        return this.type;
    }
    
    public void setType(Long type) {
        this.type = type;
    }
    
    public Long getQuality() {
        if (this.quality == null) {
            this.quality = Long.valueOf(0L);
        }
        return this.quality;
    }
    
    public void setQuality(Long quality) {
        this.quality = quality;
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = ((instruction == null) ? null : instruction.trim());
    }
    
    public BigDecimal getRgid() {
        if (this.rgid == null) {
            this.rgid = new BigDecimal(0);
        }
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public Integer getStatus() {
        if (this.status == null) {
            this.status = Integer.valueOf(0);
        }
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public BigDecimal getDefineprice() {
        return this.defineprice;
    }
    
    public void setDefineprice(BigDecimal defineprice) {
        this.defineprice = defineprice;
    }
    
    public Integer getGoodlock() {
        if (this.goodlock == null) {
            this.goodlock = Integer.valueOf(0);
        }
        return this.goodlock;
    }
    
    public void setGoodlock(Integer goodlock) {
        this.goodlock = goodlock;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getOtherRole() {
        return this.otherRole;
    }
    
    public void setOtherRole(String otherRole) {
        this.otherRole = otherRole;
    }
}
