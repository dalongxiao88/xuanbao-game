package org.come.service;

import java.util.List;
import org.come.entity.Message;
import java.math.BigDecimal;
import org.come.entity.MessageExample;

public interface IMessageService
{
    int countByExample(MessageExample p0);
    
    int deleteByExample(MessageExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Message p0);
    
    int insertSelective(Message p0);
    
    List<Message> selectByExample(MessageExample p0);
    
    Message selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(Message p0, MessageExample p1);
    
    int updateByExample(Message p0, MessageExample p1);
    
    int updateByPrimaryKeySelective(Message p0);
    
    int updateByPrimaryKey(Message p0);
}
