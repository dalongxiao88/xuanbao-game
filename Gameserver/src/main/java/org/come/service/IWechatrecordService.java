package org.come.service;

import com.gl.model.Param;
import java.util.List;
import org.come.entity.Wechatrecord;
import java.math.BigDecimal;
import org.come.entity.WechatrecordExample;

public interface IWechatrecordService
{
    int countByExample(WechatrecordExample wechatrecordExample);
    
    int deleteByExample(WechatrecordExample wechatrecordExample);
    
    int deleteByPrimaryKey(BigDecimal chatId);
    
    int insert(Wechatrecord wechatrecord);
    
    int insertSelective(Wechatrecord wechatrecord);
    
    List<Wechatrecord> selectByExample(WechatrecordExample wechatrecordExample);
    
    Wechatrecord selectByPrimaryKey(BigDecimal chatId);
    
    int updateByExampleSelective(Wechatrecord wechatrecord, WechatrecordExample wechatrecordExample);
    
    int updateByExample(Wechatrecord wechatrecord, WechatrecordExample wechatrecordExample);
    
    int updateByPrimaryKeySelective(Wechatrecord wechatrecord);
    
    int updateByPrimaryKey(Wechatrecord wechatrecord);
    
    List<Wechatrecord> selectAll(Param queryParam);
}
