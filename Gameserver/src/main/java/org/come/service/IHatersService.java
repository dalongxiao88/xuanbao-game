package org.come.service;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Haters;
import java.math.BigDecimal;
import org.come.entity.HatersExample;

public interface IHatersService
{
    int countByExample(HatersExample p0);
    
    int deleteByExample(HatersExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Haters p0);
    
    int insertSelective(Haters p0);
    
    List<Haters> selectByExample(HatersExample p0);
    
    Haters selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") Haters p0, @Param("example") HatersExample p1);
    
    int updateByExample(@Param("record") Haters p0, @Param("example") HatersExample p1);
    
    int updateByPrimaryKeySelective(Haters p0);
    
    int updateByPrimaryKey(Haters p0);
}
