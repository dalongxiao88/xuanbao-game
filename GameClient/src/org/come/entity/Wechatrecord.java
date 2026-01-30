package org.come.entity;

import java.math.BigDecimal;

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
    
    public void setChatMes(String chatMes) {
        this.chatMes = ((chatMes == null) ? null : chatMes.trim());
    }
    
    public BigDecimal getChatSendid() {
        return this.chatSendid;
    }
    
    public void setChatSendid(BigDecimal chatSendid) {
        this.chatSendid = chatSendid;
    }
    
    public BigDecimal getChatGetid() {
        return this.chatGetid;
    }
    
    public void setChatGetid(BigDecimal chatGetid) {
        this.chatGetid = chatGetid;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = ((time == null) ? null : time.trim());
    }
}
