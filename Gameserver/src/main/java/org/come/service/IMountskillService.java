package org.come.service;

import java.math.BigDecimal;
import org.come.entity.MountSkill;
import java.util.List;

public interface IMountskillService
{
    List<MountSkill> selectAllMountskills();
    
    List<MountSkill> selectMountskillsByMountid(BigDecimal mountId);
    
    void deleteMountskills(BigDecimal mountId);
    
    void insertMountskills(MountSkill mountSkill);
}
