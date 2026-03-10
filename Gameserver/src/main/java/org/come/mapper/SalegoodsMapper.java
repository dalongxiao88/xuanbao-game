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
    int countByExample(SalegoodsExample salegoodsExample);
    
    int deleteByExample(SalegoodsExample salegoodsExample);
    
    int deleteByPrimaryKey(BigDecimal saleId);
    
    int insert(Salegoods salegoods);
    
    int insertSelective(Salegoods salegoods);
    
    List<Salegoods> selectByExample(SalegoodsExample salegoodsExample);
    
    Salegoods selectByPrimaryKey(BigDecimal saleId);
    
    int updateByExampleSelective(@Param("record") Salegoods salegoods, @Param("example") SalegoodsExample salegoodsExample);
    
    int updateByExample(@Param("record") Salegoods salegoods, @Param("example") SalegoodsExample salegoodsExample);
    
    int updateByPrimaryKeySelective(Salegoods salegoods);
    
    int updateByPrimaryKey(Salegoods salegoods);
    
    List<Salegoods> selectByAll();
    
    Salegoods selectSaleGoodsByRoleid(@Param("roleid") String roleId);
}
