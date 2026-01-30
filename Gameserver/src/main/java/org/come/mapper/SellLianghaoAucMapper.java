package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.SellLianghaoAuc;
import java.math.BigDecimal;
import org.come.entity.SellLianghaoAucExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SellLianghaoAucMapper
{
    int countByExample(SellLianghaoAucExample p0);
    
    int deleteByExample(SellLianghaoAucExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(SellLianghaoAuc p0);
    
    int insertSelective(SellLianghaoAuc p0);
    
    List<SellLianghaoAuc> selectByExample(SellLianghaoAucExample p0);
    
    SellLianghaoAuc selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") SellLianghaoAuc p0, @Param("example") SellLianghaoAucExample p1);
    
    int updateByExample(@Param("record") SellLianghaoAuc p0, @Param("example") SellLianghaoAucExample p1);
    
    int updateByPrimaryKeySelective(SellLianghaoAuc p0);
    
    int updateByPrimaryKey(SellLianghaoAuc p0);
    
    List<SellLianghaoAuc> selectByPrice(SellLianghaoAuc p0);
}
