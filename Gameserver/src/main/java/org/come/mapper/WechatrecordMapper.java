package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Wechatrecord;
import java.math.BigDecimal;
import org.come.entity.WechatrecordExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface WechatrecordMapper
{
    int countByExample(WechatrecordExample wechatrecordExample);
    
    int deleteByExample(WechatrecordExample wechatrecordExample);
    
    int deleteByPrimaryKey(BigDecimal chatId);
    
    int insert(Wechatrecord wechatrecord);
    
    int insertSelective(Wechatrecord wechatrecord);
    
    List<Wechatrecord> selectByExample(WechatrecordExample wechatrecordExample);
    
    Wechatrecord selectByPrimaryKey(BigDecimal chatId);
    
    int updateByExampleSelective(@Param("record") Wechatrecord wechatrecord, @Param("example") WechatrecordExample wechatrecordExample);
    
    int updateByExample(@Param("record") Wechatrecord wechatrecord, @Param("example") WechatrecordExample wechatrecordExample);
    
    int updateByPrimaryKeySelective(Wechatrecord wechatrecord);
    
    int updateByPrimaryKey(Wechatrecord wechatrecord);
    
    List<Wechatrecord> selectAll(com.gl.model.Param queryParam);
}
