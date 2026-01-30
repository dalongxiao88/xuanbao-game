package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;
import org.come.entity.Car;
import org.come.entity.CarRoleUser;

import java.math.BigDecimal;
import java.util.List;

@MyBatisAnnotation
public interface CarMapper
{
    List<Car> selectAllMounts();
    
    List<Car> selectMountsByRoleID(BigDecimal p0);

    Car selectMountByRoleIDAndMountID(Car p0);

    Car selectMountsByMID(BigDecimal p0);
    
    void deleteMountsByMid(BigDecimal p0);
    
    void updateMount(Car p0);
    
    int updateMountForRid(Car p0);
    
    int selectMountRole(Car p0);
    
    void insertMount(Car p0);
    
    BigDecimal selectMaxID();
    
    List<CarRoleUser> selectMount(@Param("mru") CarRoleUser p0);
    
    Integer selectMountCount(@Param("mru") CarRoleUser p0);
    
    void deleteMountsByMidList(List<BigDecimal> p0);
    
    void updateMountList(List<Car> p0);
    
    void insertMountList(List<Car> p0);
    
    void insertMountSingle(Car p0);
}
