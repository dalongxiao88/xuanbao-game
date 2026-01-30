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
    int countByExample(WechatrecordExample p0);
    
    int deleteByExample(WechatrecordExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Wechatrecord p0);
    
    int insertSelective(Wechatrecord p0);
    
    List<Wechatrecord> selectByExample(WechatrecordExample p0);
    
    Wechatrecord selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") Wechatrecord p0, @Param("example") WechatrecordExample p1);
    
    int updateByExample(@Param("record") Wechatrecord p0, @Param("example") WechatrecordExample p1);
    
    int updateByPrimaryKeySelective(Wechatrecord p0);
    
    int updateByPrimaryKey(Wechatrecord p0);
    
    List<Wechatrecord> selectAll(com.gl.model.Param p0);
}
