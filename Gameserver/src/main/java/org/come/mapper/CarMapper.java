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
    
    List<Car> selectMountsByRoleID(BigDecimal roleId);

    Car selectMountByRoleIDAndMountID(Car car);

    Car selectMountsByMID(BigDecimal carId);
    
    void deleteMountsByMid(BigDecimal carId);
    
    void updateMount(Car car);
    
    int updateMountForRid(Car car);
    
    int selectMountRole(Car car);
    
    void insertMount(Car car);
    
    BigDecimal selectMaxID();
    
    List<CarRoleUser> selectMount(@Param("mru") CarRoleUser carRoleUser);
    
    Integer selectMountCount(@Param("mru") CarRoleUser carRoleUser);
    
    void deleteMountsByMidList(List<BigDecimal> carIds);
    
    void updateMountList(List<Car> carList);
    
    void insertMountList(List<Car> carList);
    
    void insertMountSingle(Car car);
}
