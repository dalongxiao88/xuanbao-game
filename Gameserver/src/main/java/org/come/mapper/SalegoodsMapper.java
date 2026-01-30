package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Salegoods;
import java.math.BigDecimal;
import org.come.entity.SalegoodsExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SalegoodsMapper
{
    int countByExample(SalegoodsExample p0);
    
    int deleteByExample(SalegoodsExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Salegoods p0);
    
    int insertSelective(Salegoods p0);
    
    List<Salegoods> selectByExample(SalegoodsExample p0);
    
    Salegoods selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") Salegoods p0, @Param("example") SalegoodsExample p1);
    
    int updateByExample(@Param("record") Salegoods p0, @Param("example") SalegoodsExample p1);
    
    int updateByPrimaryKeySelective(Salegoods p0);
    
    int updateByPrimaryKey(Salegoods p0);
    
    List<Salegoods> selectByAll();
    
    Salegoods selectSaleGoodsByRoleid(@Param("roleid") String p0);
}
