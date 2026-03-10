package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Message;
import java.math.BigDecimal;
import org.come.entity.MessageExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface MessageMapper
{
    int countByExample(MessageExample messageExample);
    
    int deleteByExample(MessageExample messageExample);
    
    int deleteByPrimaryKey(BigDecimal messageId);
    
    int insert(Message message);
    
    int insertSelective(Message message);
    
    List<Message> selectByExample(MessageExample messageExample);
    
    Message selectByPrimaryKey(BigDecimal messageId);
    
    int updateByExampleSelective(@Param("record") Message message, @Param("example") MessageExample messageExample);
    
    int updateByExample(@Param("record") Message message, @Param("example") MessageExample messageExample);
    
    int updateByPrimaryKeySelective(Message message);
    
    int updateByPrimaryKey(Message message);
}
