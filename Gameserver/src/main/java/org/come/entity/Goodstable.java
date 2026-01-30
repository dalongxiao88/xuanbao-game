package org.come.entity;

import org.come.tool.JmSum;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import java.util.List;
import come.tool.Calculation.BaseEquip;
import java.math.BigDecimal;

public class Goodstable implements Cloneable
{
    // 物品IDgoodsid
    private BigDecimal goodsid;
    // 物品名称
    private String goodsname;
    private String skin;
    // 物品类型
    private long type;
    // 贵重
    private Long quality;
    // 物品功能
    private String value;
    // 物品说明
    private String instruction;

    private Integer minute;
    // 物品状态（0：未使用   1：使用   2:典当）
    private Integer status;
    // 使用次数
    private Integer usetime;
    // 表ID
    private BigDecimal rgid;
    // 角色ID
    private BigDecimal role_id;
    //物品是否有加锁
    private Long createTime;
    private int goodlock;
    private Integer qhv;
    private Integer qht;
    private Integer qhb;
    private String equipShow;
    private transient BaseEquip baseEquip;
    private List<String> otherInfo;
    private BigDecimal commodityId;
    private String rolename;
    static String[] vs;
    
    public List<String> getOtherInfo() {
        return this.otherInfo;
    }
    
    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public void goodxh(int i) {
        this.setUsetime(Integer.valueOf((int)this.getUsetime() - i));
    }
    
    public BigDecimal getRgid() {
        return this.rgid;
    }//1
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public BigDecimal getRole_id() {
    //    LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(this.role_id);
    //    this.rolename = loginResult.getRolename();
        return this.role_id;
    }

    public void setRole_id(BigDecimal role_id) {
     //   LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(role_id);
    //    this.rolename = loginResult.getRolename();
        this.role_id = role_id;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public String getGoodsname() {
        return this.goodsname;
    }
    
    public void setGoodsname(String goodsname) {
        this.goodsname = ((goodsname == null) ? null : goodsname.trim());
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public long getType() {
        return this.type;
    }
    
    public void setType(Long type) {
        this.type = (long)type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = ((value == null) ? null : value.trim());
        this.baseEquip = null;
    }
    
    public BaseEquip getEquip() {
        if (this.baseEquip == null && this.value != null && !this.value.equals("")) {
            this.baseEquip = new BaseEquip(this.value, this.type);
        }
        return this.baseEquip;
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = ((instruction == null) ? null : instruction.trim());
    }
    
    public Long getQuality() {
        return this.quality;
    }
    
    public void setQuality(Long quality) {
        this.quality = quality;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getUsetime() {
        if (this.usetime == null) {
            this.setUsetime(1);
        }
        return (int) JmSum.MZ((int) this.usetime);
    }
    
    public void setUsetime(Integer usetime) {
        if (this.goodsid != null && this.goodsid.longValue() == 80050L && (int)usetime > 20) {
            usetime = 20;
        }
        this.usetime = (int) JmSum.ZM((int) usetime);
    }
    
    public Integer getQhv() {
        return this.qhv;
    }
    
    public void setQhv(Integer qhv) {
        this.qhv = qhv;
    }
    
    public Integer getQht() {
        return this.qht;
    }
    
    public void setQht(Integer qht) {
        this.qht = qht;
    }
    
    public Integer getQhb() {
        return this.qhb;
    }
    
    public void setQhb(Integer qhb) {
        this.qhb = qhb;
    }
    
    public boolean isQh(Goodstable good) {
        if (this.qhv == null && this.qht == null && this.qhb == null && good.qhv == null && good.qht == null && good.qhb == null) {
            return true;
        }
        int v1 = (this.qhv != null) ? ((int)this.qhv) : 0;
        int v2 = (good.qhv != null) ? ((int)good.qhv) : 0;
        if (v1 != v2) {
            return false;
        }
        v1 = ((this.qht != null) ? ((int)this.qht) : 0);
        v2 = ((good.qht != null) ? ((int)good.qht) : 0);
        if (v1 != v2) {
            return false;
        }
        v1 = ((this.qhb != null) ? ((int)this.qhb) : 0);
        v2 = ((good.qhb != null) ? ((int)good.qhb) : 0);
        return v1 == v2;
    }
    
    public Goodstable clone() {
        try {
            return (Goodstable)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getGoodlock() {
        return this.goodlock;
    }
    
    public void setGoodlock(int goodlock) {
        this.goodlock = goodlock;
    }
    
    public void setType(long type) {
        this.type = type;
    }
    
    public static String toStringTwo(BigDecimal roleID, BigDecimal id, int type, String name, String value) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Goodstable.vs[1]);
        buffer.append(id);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[2]);
        buffer.append("999999");
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[3]);
        buffer.append(name);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[4]);
        buffer.append(roleID);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[5]);
        buffer.append("1");
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[6]);
        buffer.append(value);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[7]);
        buffer.append(type);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[8]);
        buffer.append("0");
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[9]);
        buffer.append("0");
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[10]);
        buffer.append("0");
        return buffer.toString();
    }
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Goodstable.vs[1]);
        buffer.append(this.rgid);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[2]);
        buffer.append(this.goodsid);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[3]);
        buffer.append(this.goodsname);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[4]);
        buffer.append(this.role_id);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[5]);
        buffer.append(this.getUsetime());
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[6]);
        buffer.append(this.value);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[7]);
        buffer.append(this.type);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[8]);
        buffer.append(this.quality);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[9]);
        buffer.append(this.status);
        buffer.append(Goodstable.vs[0]);
        buffer.append(Goodstable.vs[10]);
        buffer.append(this.instruction);
        return buffer.toString();
    }
    
    public String getEquipShow() {
        return this.equipShow;
    }
    
    public void setEquipShow(String equipShow) {
        this.equipShow = equipShow;
    }
    
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    public Integer getMinute() {
        return this.minute;
    }
    
    public void setMinute(Integer minute) {
        this.minute = minute;
    }
    
    public Long getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    
    static {
        Goodstable.vs = new String[] { "  ", "rgid:", "goodsid:", "goodsname:", "role_id:", "usetime:", "value:", "type:", "quality:", "status:", "DEFINEPRICE:" };
    }
}
