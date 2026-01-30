package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.MountSkill;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface MountskillMapper
{
    List<MountSkill> selectAllMountskills();
    
    List<MountSkill> selectMountskillsByMountid(BigDecimal p0);
    
    void deleteMountskills(BigDecimal p0);
    
    void insertMountskills(MountSkill p0);
}
