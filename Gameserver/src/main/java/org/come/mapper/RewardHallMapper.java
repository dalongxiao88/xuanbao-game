package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.concurrent.CopyOnWriteArrayList;
import org.come.entity.RewardHall;
import java.math.BigDecimal;
import org.come.entity.RewardHallExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RewardHallMapper
{
    int countByExample(RewardHallExample p0);
    
    int deleteByExample(RewardHallExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(RewardHall p0);
    
    int insertSelective(RewardHall p0);
    
    CopyOnWriteArrayList<RewardHall> selectByExample(RewardHallExample p0);
    
    RewardHall selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") RewardHall p0, @Param("example") RewardHallExample p1);
    
    int updateByExample(@Param("record") RewardHall p0, @Param("example") RewardHallExample p1);
    
    int updateByPrimaryKeySelective(RewardHall p0);
    
    int updateByPrimaryKey(RewardHall p0);
}
