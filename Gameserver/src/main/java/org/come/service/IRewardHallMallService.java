package org.come.service;

import org.apache.ibatis.annotations.Param;
import java.util.concurrent.CopyOnWriteArrayList;
import org.come.entity.RewardHall;
import java.math.BigDecimal;
import org.come.entity.RewardHallExample;

public interface IRewardHallMallService
{
    int countByExample(RewardHallExample rewardHallExample);
    
    int deleteByExample(RewardHallExample rewardHallExample);
    
    int deleteByPrimaryKey(BigDecimal rewardId);
    
    int insert(RewardHall rewardHall);
    
    int insertSelective(RewardHall rewardHall);
    
    CopyOnWriteArrayList<RewardHall> selectByExample(RewardHallExample rewardHallExample);
    
    RewardHall selectByPrimaryKey(BigDecimal rewardId);
    
    int updateByExampleSelective(@Param("record") RewardHall rewardHall, @Param("example") RewardHallExample rewardHallExample);
    
    int updateByExample(@Param("record") RewardHall rewardHall, @Param("example") RewardHallExample rewardHallExample);
    
    int updateByPrimaryKeySelective(RewardHall rewardHall);
    
    int updateByPrimaryKey(RewardHall rewardHall);
}
