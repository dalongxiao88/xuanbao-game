package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Goodsexchange;
import org.come.entity.GoodsexchangeExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsexchangeMapper
{
    int countByExample(GoodsexchangeExample goodsExchangeExample);
    
    int deleteByExample(GoodsexchangeExample goodsExchangeExample);
    
    int deleteByPrimaryKey(String goodsGuid);
    
    int insert(Goodsexchange goodsExchange);
    
    int insertSelective(Goodsexchange goodsExchange);
    
    List<Goodsexchange> selectByExample(GoodsexchangeExample goodsExchangeExample);
    
    Goodsexchange selectByPrimaryKey(String goodsGuid);
    
    int updateByExampleSelective(@Param("record") Goodsexchange goodsExchange, @Param("example") GoodsexchangeExample goodsExchangeExample);
    
    int updateByExample(@Param("record") Goodsexchange goodsExchange, @Param("example") GoodsexchangeExample goodsExchangeExample);
    
    int updateByPrimaryKeySelective(Goodsexchange goodsExchange);
    
    int updateByPrimaryKey(Goodsexchange goodsExchange);
    
    List<Goodsexchange> selectListAll();
}
