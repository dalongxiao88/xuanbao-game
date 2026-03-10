package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 服务端出售商品实体。
 */
public class Salegoods
{
    private BigDecimal saleid;
    private String salename;
    private Integer saletype;
    private BigDecimal otherid;
    private String contiontype;
    private Integer flag;
    private Date uptime;
    private BigDecimal roleid;
    private BigDecimal buyrole;
    private BigDecimal saleprice;
    private String saleskin;
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public String getSalename() {
        return this.salename;
    }
    
    public void setSalename(String salename) {
        this.salename = ((salename == null) ? null : salename.trim());
    }
    
    public Integer getSaletype() {
        return this.saletype;
    }
    
    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
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
    
    public String getContiontype() {
        return this.contiontype;
    }

    /** 语义化别名：条件类型。 */
    public String getConditionType() {
        return this.contiontype;
    }
    
    public void setContiontype(String contiontype) {
        this.contiontype = ((contiontype == null) ? null : contiontype.trim());
    }

    public void setConditionType(String conditionType) {
        this.contiontype = ((conditionType == null) ? null : conditionType.trim());
    }
    
    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    
    public Date getUptime() {
        return this.uptime;
    }
    
    public void setUptime(Date uptime) {
        this.uptime = uptime;
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
    
    public BigDecimal getSaleprice() {
        return this.saleprice;
    }
    
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }
    
    public String getSaleskin() {
        return this.saleskin;
    }
    
    public void setSaleskin(String saleskin) {
        this.saleskin = ((saleskin == null) ? null : saleskin.trim());
    }
}
