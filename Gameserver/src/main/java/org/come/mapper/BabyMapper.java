package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface BabyMapper
{
    /** 查询全部宝宝记录。 */
    List<Baby> selectAllBaby();
    
    void createBaby(Baby baby);
    
    List<Baby> selectBabyByRolename(BigDecimal roleId);
    
    void updateBaby(Baby baby);
    
    BigDecimal selectMaxID();
    
    void deleteBaby(Baby baby);
    
    void deleteBabyList(List<BigDecimal> babyIds);
    
    void createBabyList(List<Baby> babyList);
    
    void updateBabyList(List<Baby> babyList);
    
    void createBabySingle(Baby baby);
    
    void deleteBabySingle(BigDecimal babyId);
}
