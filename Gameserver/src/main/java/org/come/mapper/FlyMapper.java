package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.FlyRoleUser;
import java.math.BigDecimal;
import org.come.entity.Fly;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface FlyMapper
{
    List<Fly> selectAllFlys();
    
    List<Fly> selectFlysByRoleID(BigDecimal roleId);
    
    Fly selectFlysByMID(BigDecimal flyId);
    
    void deleteFlysByMid(BigDecimal flyId);
    
    void updateFly(Fly fly);
    
    void insertFly(Fly fly);
    
    BigDecimal selectMaxID();
    
    List<FlyRoleUser> selectFly(@Param("mru") FlyRoleUser flyRoleUser);
    
    Integer selectFlyCount(@Param("mru") FlyRoleUser flyRoleUser);
    
    void deleteFlysByMidList(List<BigDecimal> flyIds);
    
    void updateFlyList(List<Fly> flyList);
    
    void insertFlyList(List<Fly> flyList);
    
    void insertFlySingle(Fly fly);
}
