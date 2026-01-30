package org.come.bean;

import org.come.entity.Message;
import java.util.List;

public class SearchMesResultBean
{
    private List<Message> messages;
    private Integer total;
    
    public List<Message> getMessages() {
        return this.messages;
    }
    
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
}
