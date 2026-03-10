package org.cbg.entity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 客户端收藏记录实体。
 */
public class Collection
{
    private BigDecimal colid;
    private BigDecimal saleid;
    private BigDecimal roleid;
    private String salename;
    private String saleskin;
    private BigDecimal saleprice;
    private Integer saletype;
    private Date uptime;
    private BigDecimal otherid;
    private BigDecimal buyrole;
    
    public BigDecimal getBuyrole() {
        return this.buyrole;
    }

    /** 语义化别名：购买角色 ID。 */
    public BigDecimal getBuyRoleId() {
        return this.buyrole;
    }
    
    public void setBuyrole(BigDecimal buyrole) {
        this.buyrole = buyrole;
    }

    public void setBuyRoleId(BigDecimal buyRoleId) {
        this.buyrole = buyRoleId;
    }
    
    public BigDecimal getOtherid() {
        return this.otherid;
    }

    /** 语义化别名：对方角色 ID。 */
    public BigDecimal getOtherRoleId() {
        return this.otherid;
    }
    
    public void setOtherid(BigDecimal otherid) {
        this.otherid = otherid;
    }

    public void setOtherRoleId(BigDecimal otherRoleId) {
        this.otherid = otherRoleId;
    }
    
    public Integer getSaletype() {
        return this.saletype;
    }

    /** 语义化别名：出售类型。 */
    public Integer getSaleType() {
        return this.saletype;
    }
    
    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }

    public void setSaleType(Integer saleType) {
        this.saletype = saleType;
    }
    
    public Date getUptime() {
        return this.uptime;
    }
    
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    
    public BigDecimal getColid() {
        return this.colid;
    }
    
    public void setColid(BigDecimal colid) {
        this.colid = colid;
    }
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }

    /** 语义化别名：角色 ID。 */
    public BigDecimal getRoleId() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleid = roleId;
    }
    
    public String getSalename() {
        return this.salename;
    }
    
    public void setSalename(String salename) {
        this.salename = salename;
    }
    
    public String getSaleskin() {
        return this.saleskin;
    }
    
    public void setSaleskin(String saleskin) {
        this.saleskin = saleskin;
    }
    
    public BigDecimal getSaleprice() {
        return this.saleprice;
    }
    
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }
}
