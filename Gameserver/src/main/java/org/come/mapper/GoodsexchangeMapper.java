package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.Goodsexchange;
import org.come.entity.GoodsexchangeExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsexchangeMapper
{
    int countByExample(GoodsexchangeExample p0);
    
    int deleteByExample(GoodsexchangeExample p0);
    
    int deleteByPrimaryKey(String p0);
    
    int insert(Goodsexchange p0);
    
    int insertSelective(Goodsexchange p0);
    
    List<Goodsexchange> selectByExample(GoodsexchangeExample p0);
    
    Goodsexchange selectByPrimaryKey(String p0);
    
    int updateByExampleSelective(@Param("record") Goodsexchange p0, @Param("example") GoodsexchangeExample p1);
    
    int updateByExample(@Param("record") Goodsexchange p0, @Param("example") GoodsexchangeExample p1);
    
    int updateByPrimaryKeySelective(Goodsexchange p0);
    
    int updateByPrimaryKey(Goodsexchange p0);
    
    List<Goodsexchange> selectListAll();
}
