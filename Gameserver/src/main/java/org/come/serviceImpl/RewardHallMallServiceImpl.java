package org.come.serviceImpl;

import java.util.concurrent.CopyOnWriteArrayList;
import org.come.entity.RewardHall;
import java.math.BigDecimal;
import org.come.entity.RewardHallExample;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.RewardHallMapper;
import org.come.service.IRewardHallMallService;

public class RewardHallMallServiceImpl implements IRewardHallMallService
{
    private RewardHallMapper rewardHallMapper;
    
    public RewardHallMallServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.rewardHallMapper = (RewardHallMapper)ctx.getBean("rewardHallMapper", RewardHallMapper.class);
    }
    
    @Override
    public int countByExample(RewardHallExample example) {
        return 0;
    }
    
    @Override
    public int deleteByExample(RewardHallExample example) {
        return 0;
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        this.rewardHallMapper.deleteByPrimaryKey(id);
        return 0;
    }
    
    @Override
    public int insert(RewardHall record) {
        this.rewardHallMapper.insert(record);
        return 0;
    }
    
    @Override
    public int insertSelective(RewardHall record) {
        return 0;
    }
    
    @Override
    public CopyOnWriteArrayList<RewardHall> selectByExample(RewardHallExample example) {
        return this.rewardHallMapper.selectByExample(example);
    }
    
    @Override
    public RewardHall selectByPrimaryKey(BigDecimal id) {
        return null;
    }
    
    @Override
    public int updateByExampleSelective(RewardHall record, RewardHallExample example) {
        return 0;
    }
    
    @Override
    public int updateByExample(RewardHall record, RewardHallExample example) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKeySelective(RewardHall record) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKey(RewardHall record) {
        return 0;
    }
}
