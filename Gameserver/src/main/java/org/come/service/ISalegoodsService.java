package org.come.service;

import java.util.List;
import org.come.entity.Salegoods;
import java.math.BigDecimal;
import org.come.entity.SalegoodsExample;

public interface ISalegoodsService
{
    int countByExample(SalegoodsExample p0);
    
    int deleteByExample(SalegoodsExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Salegoods p0);
    
    int insertSelective(Salegoods p0);
    
    List<Salegoods> selectByExample(SalegoodsExample p0);
    
    Salegoods selectByPrimaryKey(BigDecimal p0);
    
    List<Salegoods> selectByAll();
    
    int updateByExampleSelective(Salegoods p0, SalegoodsExample p1);
    
    int updateByExample(Salegoods p0, SalegoodsExample p1);
    
    int updateByPrimaryKeySelective(Salegoods p0);
    
    int updateByPrimaryKey(Salegoods p0);
    
    void updateFlag(BigDecimal p0, Integer p1);
    
    void deleteFlag(BigDecimal p0);
    
    Integer selectFlag(BigDecimal p0);
    
    Salegoods selectSaleGoodsByRoleid(String p0);
}
