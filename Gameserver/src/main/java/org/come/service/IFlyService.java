package org.come.service;

import org.come.entity.FlyRoleUser;
import java.math.BigDecimal;
import org.come.entity.Fly;
import java.util.List;

public interface IFlyService
{
    List<Fly> selectAllFlys();
    
    List<Fly> selectFlysByRoleID(BigDecimal p0);
    
    Fly selectFlysByMID(BigDecimal p0);
    
    void deleteFlysByMid(BigDecimal p0);
    
    void updateFly(Fly p0);
    
    void updateFlyRedis(Fly p0);
    
    void insertFly(Fly p0);
    
    void deleteFlysByMidsql(BigDecimal p0);
    
    void updateFlysql(Fly p0);
    
    void insertFlysql(Fly p0);
    
    BigDecimal selectMaxID();
    
    List<FlyRoleUser> selectFly(FlyRoleUser p0);
    
    Integer selectFlyCount(FlyRoleUser p0);
    
    void deleteFlysByMidList(List<BigDecimal> p0);
    
    void updateFlyList(List<Fly> p0);
    
    void insertFlyList(List<Fly> p0);
    
    void insertFlySingle(Fly p0);
}
