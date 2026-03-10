package org.cbg.entity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 客户端藏宝阁消息实体。
 */
public class Message
{
    private BigDecimal mesid;
    private BigDecimal roleid;
    private BigDecimal saleid;
    private String mescontent;
    private Date gettime;
    private String roleName;
    private int page;
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public BigDecimal getMesid() {
        return this.mesid;
    }

    /** 语义化别名：消息 ID。 */
    public BigDecimal getMessageId() {
        return this.mesid;
    }
    
    public void setMesid(BigDecimal mesid) {
        this.mesid = mesid;
    }

    public void setMessageId(BigDecimal messageId) {
        this.mesid = messageId;
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
    
    public BigDecimal getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(BigDecimal saleid) {
        this.saleid = saleid;
    }
    
    public String getMescontent() {
        return this.mescontent;
    }

    /** 语义化别名：消息内容。 */
    public String getMessageContent() {
        return this.mescontent;
    }
    
    public void setMescontent(String mescontent) {
        this.mescontent = ((mescontent == null) ? null : mescontent.trim());
    }

    public void setMessageContent(String messageContent) {
        this.mescontent = ((messageContent == null) ? null : messageContent.trim());
    }
    
    public Date getGettime() {
        return this.gettime;
    }

    /** 语义化别名：消息时间。 */
    public Date getMessageTime() {
        return this.gettime;
    }
    
    public void setGettime(Date gettime) {
        this.gettime = gettime;
    }

    public void setMessageTime(Date messageTime) {
        this.gettime = messageTime;
    }
}
