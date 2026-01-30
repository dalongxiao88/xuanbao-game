package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.Titletable;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface TitletableMapper
{
    List<Titletable> selectRoleAllTitle(BigDecimal p0);
    
    void createRoleTitle(Titletable p0);
    
    void updateByPrimaryKey(Titletable p0);
    
    Titletable selectRoleTitle(@Param("roleid") BigDecimal p0, @Param("titlename") String p1);
}
