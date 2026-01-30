package org.come.service;

import java.math.BigDecimal;
import org.come.entity.MountSkill;
import java.util.List;

public interface IMountskillService
{
    List<MountSkill> selectAllMountskills();
    
    List<MountSkill> selectMountskillsByMountid(BigDecimal p0);
    
    void deleteMountskills(BigDecimal p0);
    
    void insertMountskills(MountSkill p0);
}
