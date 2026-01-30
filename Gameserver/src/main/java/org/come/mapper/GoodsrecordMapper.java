package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.GoodsrecordExample;
import java.util.List;
import org.come.entity.Goodsrecord;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsrecordMapper
{
    List<Goodsrecord> selectGoodsRecord(Goodsrecord p0);
    
    int countByExample(GoodsrecordExample p0);
    
    int insertGoodsrecordRoel(Goodsrecord p0);
    
    int deleteByExample(GoodsrecordExample p0);
    
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Goodsrecord p0);
    
    int insertSelective(Goodsrecord p0);
    
    List<Goodsrecord> selectByExample(GoodsrecordExample p0);
    
    Goodsrecord selectByPrimaryKey(Integer p0);
    
    int updateByExampleSelective(@Param("record") Goodsrecord p0, @Param("example") GoodsrecordExample p1);
    
    int updateByExample(@Param("record") Goodsrecord p0, @Param("example") GoodsrecordExample p1);
    
    int updateByPrimaryKeySelective(Goodsrecord p0);
    
    int updateByPrimaryKey(Goodsrecord p0);
    List<Goodsrecord> selectGoodsRecordByType(@Param("type") Integer type);
}
