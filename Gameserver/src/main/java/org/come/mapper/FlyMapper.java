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
    
    List<Fly> selectFlysByRoleID(BigDecimal p0);
    
    Fly selectFlysByMID(BigDecimal p0);
    
    void deleteFlysByMid(BigDecimal p0);
    
    void updateFly(Fly p0);
    
    void insertFly(Fly p0);
    
    BigDecimal selectMaxID();
    
    List<FlyRoleUser> selectFly(@Param("mru") FlyRoleUser p0);
    
    Integer selectFlyCount(@Param("mru") FlyRoleUser p0);
    
    void deleteFlysByMidList(List<BigDecimal> p0);
    
    void updateFlyList(List<Fly> p0);
    
    void insertFlyList(List<Fly> p0);
    
    void insertFlySingle(Fly p0);
}
