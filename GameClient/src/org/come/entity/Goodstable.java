package org.come.entity;

import org.come.until.JmSum;
import java.util.List;
import java.math.BigDecimal;
import org.come.Jpanel.spot.Commodity;
/**
 * 客户端背包物品实体。
 *
 * 当前类既服务于背包、装备、摆摊，也服务于翅膀、灵宝等面板的物品展示。
 * 历史方法 `goodxh` 实际表示“扣减使用次数/叠加数量”，
 * `TH` 实际表示“从另一个物品对象复制数据”。
 */
public class Goodstable implements Cloneable, Commodity
{
    private BigDecimal rgid;
    private BigDecimal role_id;
    private BigDecimal goodsid;
    private String goodsname;
    private String skin;
    private Long type;
    private Long quality;
    private String value;
    private String instruction;
    private Integer minute;
    private Integer status;
    private Integer usetime;
    private int goodlock;
    private BigDecimal definePrice;
    private BigDecimal commodityId;
    private Integer qhv;
    private Integer qht;
    private List<String> otherInfo;
    private Long createTime;
    
    public List<String> getOtherInfo() {
        return this.otherInfo;
    }
    
    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public int getTypeOrder() {
        int typeOrder = 999;
        switch (this.type.intValue()) {
            case 0:
            case 1: {
                typeOrder = this.type.intValue();
                break;
            }
            case 112: {
                typeOrder = 2;
                break;
            }
            case 494: {
                typeOrder = 2;
                break;
            }
            case 921: {
                typeOrder = 3;
                break;
            }
            case 922: {
                typeOrder = 3;
                break;
            }
        }
        return typeOrder;
    }
    
    /**
     * 扣减物品使用次数或叠加数量。
     */
    public void consumeUseTime(int amount) {
        this.setUsetime(Integer.valueOf((int)this.getUsetime() - amount));
    }

    /** 兼容旧命名：扣减使用次数。 */
    public void goodxh(int i) {
        this.consumeUseTime(i);
    }
    
    /**
     * 从另一个物品对象复制主要展示与状态字段。
     */
    public void copyFrom(Goodstable good) {
        this.rgid = good.rgid;
        this.role_id = good.role_id;
        this.goodsid = good.goodsid;
        this.goodsname = good.goodsname;
        this.skin = good.skin;
        this.type = good.type;
        this.quality = good.quality;
        this.value = good.value;
        this.instruction = good.instruction;
        this.status = good.status;
        this.usetime = good.usetime;
        this.goodlock = good.goodlock;
    }

    /** 兼容旧命名：复制物品对象。 */
    public void TH(Goodstable good) {
        this.copyFrom(good);
    }
    
    public Goodstable(BigDecimal rgid, BigDecimal role_id, BigDecimal goodsid, String goodsname, String skin, Long type, Long quality, String value, String instruction, Integer status, Integer usetime) {
        this.rgid = rgid;
        this.role_id = role_id;
        this.goodsid = goodsid;
        this.goodsname = goodsname;
        this.skin = skin;
        this.type = type;
        this.quality = quality;
        this.value = value;
        this.instruction = instruction;
        this.status = status;
        this.setUsetime(usetime);
    }
    
    public Goodstable() {
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }

    /** 语义化别名：所属角色 ID。 */
    public BigDecimal getRoleId() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }

    public void setRoleId(BigDecimal roleId) {
        this.role_id = roleId;
    }
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }

    /** 语义化别名：物品模板 ID。 */
    public BigDecimal getGoodsId() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }

    public void setGoodsId(BigDecimal goodsId) {
        this.goodsid = goodsId;
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
    
    public Long getType() {
        if (this.type == null) {
            this.type = Long.valueOf(-1L);
        }
        return this.type;
    }
    
    public void setType(Long type) {
        this.type = type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = ((value == null) ? null : value.trim());
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = ((instruction == null) ? null : instruction.trim());
    }
    
    public Long getQuality() {
        if (this.quality == null) {
            this.quality = new Long(0L);
        }
        return this.quality;
    }
    
    public void setQuality(Long quality) {
        this.quality = quality;
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
    
    public Integer getUsetime() {
        if (this.usetime == null) {
            this.setUsetime(Integer.valueOf(1));
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.usetime));
    }
    
    public void setUsetime(Integer usetime) {
        this.usetime = Integer.valueOf((int)JmSum.ZM((long)(int)usetime));
    }
    
    public BigDecimal getRgid() {
        if (this.rgid != null) {
            return this.rgid;
        }
        return new BigDecimal(0);
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public int getGoodlock() {
        return this.goodlock;
    }
    
    public void setGoodlock(int goodlock) {
        this.goodlock = goodlock;
    }
    
    public Object clone() {
        Goodstable goods = null;
        try {
            goods = (Goodstable)super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Object)goods;
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
    
    public BigDecimal getDefinePrice() {
        return this.definePrice;
    }
    
    public void setDefinePrice(BigDecimal definePrice) {
        this.definePrice = definePrice;
    }
    
    @Override
    public String toString() {
        return "Goodstable{rgid=" + this.rgid + ", role_id=" + this.role_id + ", goodsid=" + this.goodsid + ", goodsname='" + this.goodsname + '\'' + ", skin='" + this.skin + '\'' + ", type=" + this.type + ", quality=" + this.quality + ", value='" + this.value + '\'' + ", instruction='" + this.instruction + '\'' + ", status=" + this.status + ", usetime=" + this.usetime + ", goodlock=" + this.goodlock + ", definePrice=" + this.definePrice + ", qhv=" + this.qhv + ", qht=" + this.qht + '}';
    }
    
    @Override
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    @Override
    public BigDecimal getCommodityId() {
        return this.commodityId;
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
}
