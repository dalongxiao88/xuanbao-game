package org.come.service;

import org.come.entity.FlyRoleUser;
import java.math.BigDecimal;
import org.come.entity.Fly;
import java.util.List;

public interface IFlyService
{
    List<Fly> selectAllFlys();
    
    List<Fly> selectFlysByRoleID(BigDecimal roleId);
    
    Fly selectFlysByMID(BigDecimal flyId);
    
    void deleteFlysByMid(BigDecimal flyId);
    
    void updateFly(Fly fly);
    
    void updateFlyRedis(Fly fly);
    
    void insertFly(Fly fly);
    
    void deleteFlysByMidsql(BigDecimal flyId);
    
    void updateFlysql(Fly fly);
    
    void insertFlysql(Fly fly);
    
    BigDecimal selectMaxID();
    
    List<FlyRoleUser> selectFly(FlyRoleUser flyRoleUser);
    
    Integer selectFlyCount(FlyRoleUser flyRoleUser);
    
    void deleteFlysByMidList(List<BigDecimal> flyIds);
    
    void updateFlyList(List<Fly> flyList);
    
    void insertFlyList(List<Fly> flyList);
    
    void insertFlySingle(Fly fly);
}
