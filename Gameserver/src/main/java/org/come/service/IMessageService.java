package org.come.service;

import java.util.List;
import org.come.entity.Message;
import java.math.BigDecimal;
import org.come.entity.MessageExample;

public interface IMessageService
{
    int countByExample(MessageExample messageExample);
    
    int deleteByExample(MessageExample messageExample);
    
    int deleteByPrimaryKey(BigDecimal messageId);
    
    int insert(Message message);
    
    int insertSelective(Message message);
    
    List<Message> selectByExample(MessageExample messageExample);
    
    Message selectByPrimaryKey(BigDecimal messageId);
    
    int updateByExampleSelective(Message message, MessageExample messageExample);
    
    int updateByExample(Message message, MessageExample messageExample);
    
    int updateByPrimaryKeySelective(Message message);
    
    int updateByPrimaryKey(Message message);
}
