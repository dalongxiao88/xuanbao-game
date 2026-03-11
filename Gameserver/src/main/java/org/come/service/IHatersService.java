package org.come.service;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Haters;
import java.math.BigDecimal;
import org.come.entity.HatersExample;

/**
 * 仇人/黑名单记录服务接口。
 */
public interface IHatersService
{
    int countByExample(HatersExample hatersExample);
    
    int deleteByExample(HatersExample hatersExample);
    
    int deleteByPrimaryKey(BigDecimal roleId);
    
    int insert(Haters haters);
    
    int insertSelective(Haters haters);
    
    List<Haters> selectByExample(HatersExample hatersExample);
    
    Haters selectByPrimaryKey(BigDecimal roleId);
    
    int updateByExampleSelective(@Param("record") Haters haters, @Param("example") HatersExample hatersExample);
    
    int updateByExample(@Param("record") Haters haters, @Param("example") HatersExample hatersExample);
    
    int updateByPrimaryKeySelective(Haters haters);
    
    int updateByPrimaryKey(Haters haters);
}
