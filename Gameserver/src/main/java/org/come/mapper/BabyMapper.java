package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface BabyMapper
{
    List<Baby> selectAllBaby();
    
    void createBaby(Baby p0);
    
    List<Baby> selectBabyByRolename(BigDecimal p0);
    
    void updateBaby(Baby p0);
    
    BigDecimal selectMaxID();
    
    void deleteBaby(Baby p0);
    
    void deleteBabyList(List<BigDecimal> p0);
    
    void createBabyList(List<Baby> p0);
    
    void updateBabyList(List<Baby> p0);
    
    void createBabySingle(Baby p0);
    
    void deleteBabySingle(BigDecimal p0);
}
