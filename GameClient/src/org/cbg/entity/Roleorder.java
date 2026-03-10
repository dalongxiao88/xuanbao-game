package org.cbg.entity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 客户端角色订单实体。
 */
public class Roleorder
{
    private BigDecimal orderid;
    private BigDecimal saleid;
    private Date buytime;
    private Integer status;
    private BigDecimal roleid;
    private String ordernumber;
    private String salename;
    private String saleskin;
    private BigDecimal saleprice;
    private Integer saletype;
    private BigDecimal otherid;
    
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
    
    public BigDecimal getOrderid() {
        return this.orderid;
    }

    /** 语义化别名：订单 ID。 */
    public BigDecimal getOrderId() {
        return this.orderid;
    }
    
    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public void setOrderId(BigDecimal orderId) {
        this.orderid = orderId;
    }
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public Date getBuytime() {
        return this.buytime;
    }

    /** 语义化别名：购买时间。 */
    public Date getBuyTime() {
        return this.buytime;
    }
    
    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public void setBuyTime(Date buyTime) {
        this.buytime = buyTime;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
    
    public String getOrdernumber() {
        return this.ordernumber;
    }

    /** 语义化别名：订单号。 */
    public String getOrderNumber() {
        return this.ordernumber;
    }
    
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ((ordernumber == null) ? null : ordernumber.trim());
    }

    public void setOrderNumber(String orderNumber) {
        this.ordernumber = ((orderNumber == null) ? null : orderNumber.trim());
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
