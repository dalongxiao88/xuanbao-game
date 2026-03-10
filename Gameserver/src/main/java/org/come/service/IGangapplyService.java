package org.come.service;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.entity.Gangapply;

public interface IGangapplyService
{
    void insertIntGangapple(Gangapply gangapply);
    
    Gangapply selectGangApply(@Param("roleid") BigDecimal roleId, @Param("gangid") BigDecimal gangId);
    
    void deleteGangappleAll(BigDecimal roleId);
    
    void deleteGangapple(@Param("roleid") BigDecimal roleId, @Param("gangid") BigDecimal gangId);
    
    void deleteGangappleGang(BigDecimal gangId);
    
    List<Gangapplytable> getGangapplytables(BigDecimal gangId);
}
