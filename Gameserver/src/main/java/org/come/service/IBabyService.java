package org.come.service;

import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.List;

public interface IBabyService
{
    List<Baby> selectAllBaby();
    
    void createBaby(Baby p0);
    
    List<Baby> selectBabyByRolename(BigDecimal p0);
    
    Baby selectBabyById(BigDecimal p0);
    
    void updateBaby(Baby p0);
    
    void updateBabyRedis(Baby p0);
    
    BigDecimal selectMaxID();
    
    void deleteBaby(Baby p0);
    
    void createBabysql(Baby p0);
    
    void updateBabysql(Baby p0);
    
    void deleteBabysql(Baby p0);
    
    void deleteBabyList(List<BigDecimal> p0);
    
    void createBabyList(List<Baby> p0);
    
    void updateBabyList(List<Baby> p0);
    
    void createBabySingle(Baby p0);
    
    void deleteBabySingle(BigDecimal p0);
}
