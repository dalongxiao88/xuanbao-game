package org.come.serviceImpl;

import org.come.entity.FlyRoleUser;
import org.come.redis.RedisCacheUtil;
import come.tool.Role.RolePool;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.entity.Fly;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.FlyMapper;
import org.come.service.IFlyService;

public class FlyServiceImpl implements IFlyService
{
    private FlyMapper flyMapper;
    private final Integer limit;
    
    public FlyServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.flyMapper = (FlyMapper)ctx.getBean("flyMapper");
    }
    
    @Override
    public List<Fly> selectFlysByRoleID(BigDecimal roleID) {
        List<Fly> flys = RedisControl.getS(RedisParameterUtil.FLY, roleID.toString(), Fly.class);
        return flys;
    }
    
    @Override
    public Fly selectFlysByMID(BigDecimal mid) {
        Fly fly = (Fly)RedisControl.getV(RedisParameterUtil.FLY, mid.toString(), Fly.class);
        return fly;
    }
    
    @Override
    public void deleteFlysByMid(BigDecimal roleID) {
        Fly fly = (Fly)RedisControl.getV(RedisParameterUtil.FLY, roleID.toString(), Fly.class);
        if (fly != null) {
            RedisControl.deletrValue(RedisParameterUtil.FLY, fly.getRoleid().toString(), fly.getMid().toString());
            RedisControl.delForKey(RedisParameterUtil.FLY, fly.getMid().toString());
            RedisControl.insertController(RedisParameterUtil.FLY, fly.getMid().toString(), "3");
            RolePool.getRoleData(fly.getRoleid());
        }
    }
    
    @Override
    public void updateFly(Fly fly) {
        Fly fly2 = (Fly)RedisControl.getV(RedisParameterUtil.FLY, fly.getMid().toString(), Fly.class);
        if (fly2 != null) {
            RedisControl.insertKeyT(RedisParameterUtil.FLY, fly2.getMid().toString(), fly2);
            RedisControl.insertController(RedisParameterUtil.FLY, fly2.getMid().toString(), "2");
        }
    }
    
    @Override
    public void updateFlyRedis(Fly fly) {
        RedisControl.insertKeyT(RedisParameterUtil.FLY, fly.getMid().toString(), fly);
        RedisControl.insertController(RedisParameterUtil.FLY, fly.getMid().toString(), "2");
    }
    
    @Override
    public void insertFly(Fly fly) {
        fly.setMid(RedisCacheUtil.getFly_pk());
        fly.setExp(Integer.valueOf(0));
        RedisControl.insertKeyT(RedisParameterUtil.FLY, fly.getMid().toString(), fly);
        RedisControl.insertListRedis(RedisParameterUtil.FLY, fly.getRoleid().toString(), fly.getMid().toString());
        RedisControl.insertController(RedisParameterUtil.FLY, fly.getMid().toString(), "1");
    }
    
    @Override
    public List<Fly> selectAllFlys() {
        return this.flyMapper.selectAllFlys();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.flyMapper.selectMaxID();
    }
    
    @Override
    public void deleteFlysByMidsql(BigDecimal roleID) {
        this.flyMapper.deleteFlysByMid(roleID);
    }
    
    @Override
    public void updateFlysql(Fly fly) {
        this.flyMapper.updateFly(fly);
    }
    
    @Override
    public void insertFlysql(Fly fly) {
        this.flyMapper.insertFly(fly);
    }
    
    @Override
    public List<FlyRoleUser> selectFly(FlyRoleUser mru) {
        Integer start = Integer.valueOf(((int)mru.getPageNum() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        mru.setStart(start);
        mru.setEnd(end);
        List<FlyRoleUser> flyList = this.flyMapper.selectFly(mru);
        return flyList;
    }
    
    @Override
    public Integer selectFlyCount(FlyRoleUser mru) {
        Integer flyCount = this.flyMapper.selectFlyCount(mru);
        return flyCount;
    }
    
    @Override
    public void deleteFlysByMidList(List<BigDecimal> roleID) {
        this.flyMapper.deleteFlysByMidList(roleID);
    }
    
    @Override
    public void updateFlyList(List<Fly> fly) {
        this.flyMapper.updateFlyList(fly);
    }
    
    @Override
    public void insertFlyList(List<Fly> fly) {
        this.flyMapper.insertFlyList(fly);
    }
    
    @Override
    public void insertFlySingle(Fly fly) {
        this.flyMapper.insertFlySingle(fly);
    }
}
