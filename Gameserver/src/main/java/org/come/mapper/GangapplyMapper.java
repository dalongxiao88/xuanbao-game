package org.come.mapper;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.entity.Gangapply;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GangapplyMapper
{
    void insertIntGangapple(Gangapply p0);
    
    Gangapply selectGangApply(@Param("roleid") BigDecimal p0, @Param("gangid") BigDecimal p1);
    
    void deleteGangappleAll(BigDecimal p0);
    
    void deleteGangapple(@Param("roleid") BigDecimal p0, @Param("gangid") BigDecimal p1);
    
    void deleteGangappleGang(BigDecimal p0);
    
    List<Gangapplytable> getGangapplytables(BigDecimal p0);
}
