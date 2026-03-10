package org.come.service;

import org.come.entity.Car;
import org.come.entity.CarRoleUser;

import java.math.BigDecimal;
import java.util.List;

public interface ICarService
{
    List<Car> selectAllMounts();
    
    List<Car> selectMountsByRoleID(BigDecimal roleId);

    Car selectMountByRoleIDAndMountID(Car car);

    Car selectMountsByMID(BigDecimal carId);
    
    void deleteMountsByMid(BigDecimal carId);
    
    void updateMount(Car car);
    
    int updateMountForRid(Car car);
    
    void updateMountRedis(Car car);
    
    void insertMount(Car car);
    
    void deleteMountsByMidsql(BigDecimal carId);
    
    void updateMountsql(Car car);
    
    void insertMountsql(Car car);
    
    BigDecimal selectMaxID();
    
    List<CarRoleUser> selectMount(CarRoleUser carRoleUser);
    
    Integer selectMountCount(CarRoleUser carRoleUser);
    
    void deleteMountsByMidList(List<BigDecimal> carIds);
    
    void updateMountList(List<Car> carList);
    
    void insertMountList(List<Car> carList);
    
    void insertMountSingle(Car car);
}
