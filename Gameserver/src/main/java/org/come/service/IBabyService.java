package org.come.service;

import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.List;

public interface IBabyService
{
    /** 查询全部宝宝记录。 */
    List<Baby> selectAllBaby();
    
    void createBaby(Baby baby);
    
    List<Baby> selectBabyByRolename(BigDecimal roleId);
    
    Baby selectBabyById(BigDecimal babyId);
    
    void updateBaby(Baby baby);
    
    void updateBabyRedis(Baby baby);
    
    BigDecimal selectMaxID();
    
    void deleteBaby(Baby baby);
    
    void createBabysql(Baby baby);
    
    void updateBabysql(Baby baby);
    
    void deleteBabysql(Baby baby);
    
    void deleteBabyList(List<BigDecimal> babyIds);
    
    void createBabyList(List<Baby> babyList);
    
    void updateBabyList(List<Baby> babyList);
    
    void createBabySingle(Baby baby);
    
    void deleteBabySingle(BigDecimal babyId);
}
