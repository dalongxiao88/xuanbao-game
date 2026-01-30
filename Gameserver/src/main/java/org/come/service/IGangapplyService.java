package org.come.service;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.entity.Gangapply;

public interface IGangapplyService
{
    void insertIntGangapple(Gangapply p0);
    
    Gangapply selectGangApply(@Param("roleid") BigDecimal p0, @Param("gangid") BigDecimal p1);
    
    void deleteGangappleAll(BigDecimal p0);
    
    void deleteGangapple(@Param("roleid") BigDecimal p0, @Param("gangid") BigDecimal p1);
    
    void deleteGangappleGang(BigDecimal p0);
    
    List<Gangapplytable> getGangapplytables(BigDecimal p0);
}
