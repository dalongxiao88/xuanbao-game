package org.come.service;

import org.apache.ibatis.annotations.Param;
import org.come.entity.Titletable;
import java.util.List;
import java.math.BigDecimal;

/**
 * 称谓服务接口。
 */
public interface ITitletableService
{
    List<Titletable> selectRoleAllTitle(BigDecimal roleId);
    
    void createRoleTitle(Titletable title);
    
    void updateByPrimaryKey(Titletable title);
    
    Titletable selectRoleTitle(@Param("roleid") BigDecimal roleId, @Param("titlename") String titleName);
}
