package org.come.service;

import java.util.List;
import org.come.entity.Goodsexchange;
import org.come.entity.GoodsexchangeExample;

public interface IGoodsExchangeService
{
    int countByExample(GoodsexchangeExample p0);
    
    int deleteByExample(GoodsexchangeExample p0);
    
    int deleteByPrimaryKey(String p0);
    
    int insert(Goodsexchange p0);
    
    int insertSelective(Goodsexchange p0);
    
    List<Goodsexchange> selectByExample(GoodsexchangeExample p0);
    
    Goodsexchange selectByPrimaryKey(String p0);
    
    int updateByExampleSelective(Goodsexchange p0, GoodsexchangeExample p1);
    
    int updateByExample(Goodsexchange p0, GoodsexchangeExample p1);
    
    int updateByPrimaryKeySelective(Goodsexchange p0);
    
    int updateByPrimaryKey(Goodsexchange p0);
    
    List<Goodsexchange> selectListAll();
}
