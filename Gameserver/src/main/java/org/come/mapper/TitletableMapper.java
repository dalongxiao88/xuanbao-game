package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.Titletable;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 称谓 Mapper。
 */
public interface TitletableMapper
{
    List<Titletable> selectRoleAllTitle(BigDecimal roleId);
    
    void createRoleTitle(Titletable title);
    
    void updateByPrimaryKey(Titletable title);
    
    Titletable selectRoleTitle(@Param("roleid") BigDecimal roleId, @Param("titlename") String titleName);
}
