package org.come.service;

import com.gl.model.Param;
import java.util.List;
import org.come.entity.Wechatrecord;
import java.math.BigDecimal;
import org.come.entity.WechatrecordExample;

public interface IWechatrecordService
{
    int countByExample(WechatrecordExample p0);
    
    int deleteByExample(WechatrecordExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Wechatrecord p0);
    
    int insertSelective(Wechatrecord p0);
    
    List<Wechatrecord> selectByExample(WechatrecordExample p0);
    
    Wechatrecord selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(Wechatrecord p0, WechatrecordExample p1);
    
    int updateByExample(Wechatrecord p0, WechatrecordExample p1);
    
    int updateByPrimaryKeySelective(Wechatrecord p0);
    
    int updateByPrimaryKey(Wechatrecord p0);
    
    List<Wechatrecord> selectAll(Param p0);
}
