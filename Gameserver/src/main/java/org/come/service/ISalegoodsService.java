package org.come.service;

import java.util.List;
import org.come.entity.Salegoods;
import java.math.BigDecimal;
import org.come.entity.SalegoodsExample;

public interface ISalegoodsService
{
    int countByExample(SalegoodsExample salegoodsExample);
    
    int deleteByExample(SalegoodsExample salegoodsExample);
    
    int deleteByPrimaryKey(BigDecimal saleId);
    
    int insert(Salegoods salegoods);
    
    int insertSelective(Salegoods salegoods);
    
    List<Salegoods> selectByExample(SalegoodsExample salegoodsExample);
    
    Salegoods selectByPrimaryKey(BigDecimal saleId);
    
    List<Salegoods> selectByAll();
    
    int updateByExampleSelective(Salegoods salegoods, SalegoodsExample salegoodsExample);
    
    int updateByExample(Salegoods salegoods, SalegoodsExample salegoodsExample);
    
    int updateByPrimaryKeySelective(Salegoods salegoods);
    
    int updateByPrimaryKey(Salegoods salegoods);
    
    void updateFlag(BigDecimal saleId, Integer flag);
    
    void deleteFlag(BigDecimal saleId);
    
    Integer selectFlag(BigDecimal saleId);
    
    Salegoods selectSaleGoodsByRoleid(String roleId);
}
