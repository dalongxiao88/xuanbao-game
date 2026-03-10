package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.GoodsrecordExample;
import java.util.List;
import org.come.entity.Goodsrecord;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsrecordMapper
{
    List<Goodsrecord> selectGoodsRecord(Goodsrecord goodsrecord);
    
    int countByExample(GoodsrecordExample goodsrecordExample);
    
    int insertGoodsrecordRoel(Goodsrecord goodsrecord);
    
    int deleteByExample(GoodsrecordExample goodsrecordExample);
    
    int deleteByPrimaryKey(Integer recordId);
    
    int insert(Goodsrecord goodsrecord);
    
    int insertSelective(Goodsrecord goodsrecord);
    
    List<Goodsrecord> selectByExample(GoodsrecordExample goodsrecordExample);
    
    Goodsrecord selectByPrimaryKey(Integer recordId);
    
    int updateByExampleSelective(@Param("record") Goodsrecord goodsrecord, @Param("example") GoodsrecordExample goodsrecordExample);
    
    int updateByExample(@Param("record") Goodsrecord goodsrecord, @Param("example") GoodsrecordExample goodsrecordExample);
    
    int updateByPrimaryKeySelective(Goodsrecord goodsrecord);
    
    int updateByPrimaryKey(Goodsrecord goodsrecord);
    List<Goodsrecord> selectGoodsRecordByType(@Param("type") Integer type);
}
