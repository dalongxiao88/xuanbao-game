package org.come.entity;

import java.math.BigDecimal;

/**
 * 服务端聊天记录实体。
 */
public class Wechatrecord
{
    private BigDecimal chatId;
    private String chatMes;
    private BigDecimal chatSendid;
    private BigDecimal chatGetid;
    private String time;
    
    public BigDecimal getChatId() {
        return this.chatId;
    }
    
    public void setChatId(BigDecimal chatId) {
        this.chatId = chatId;
    }
    
    public String getChatMes() {
        return this.chatMes;
    }

    /** 语义化别名：聊天内容。 */
    public String getChatMessage() {
        return this.chatMes;
    }
    
    public void setChatMes(String chatMes) {
        this.chatMes = ((chatMes == null) ? null : chatMes.trim());
    }

    public void setChatMessage(String chatMessage) {
        this.chatMes = ((chatMessage == null) ? null : chatMessage.trim());
    }
    
    public BigDecimal getChatSendid() {
        return this.chatSendid;
    }

    /** 语义化别名：发送方角色 ID。 */
    public BigDecimal getSenderRoleId() {
        return this.chatSendid;
    }
    
    public void setChatSendid(BigDecimal chatSendid) {
        this.chatSendid = chatSendid;
    }

    public void setSenderRoleId(BigDecimal senderRoleId) {
        this.chatSendid = senderRoleId;
    }
    
    public BigDecimal getChatGetid() {
        return this.chatGetid;
    }

    /** 语义化别名：接收方角色 ID。 */
    public BigDecimal getReceiverRoleId() {
        return this.chatGetid;
    }
    
    public void setChatGetid(BigDecimal chatGetid) {
        this.chatGetid = chatGetid;
    }

    public void setReceiverRoleId(BigDecimal receiverRoleId) {
        this.chatGetid = receiverRoleId;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = ((time == null) ? null : time.trim());
    }
}
