package org.come.mapper;

import come.tool.oneArena.OneArenaNotes;
import java.util.List;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface OneArenaNotesMapper
{
    BigDecimal selectMaxID(@Param("time") String p0);
    
    List<OneArenaNotes> selectRole(@Param("roleId") BigDecimal p0, @Param("min") BigDecimal p1);
    
    int insertOneArenaNotes(@Param("notes") OneArenaNotes p0);
}
