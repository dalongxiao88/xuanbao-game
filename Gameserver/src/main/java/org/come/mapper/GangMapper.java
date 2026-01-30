package org.come.mapper;

import java.util.List;
import org.come.entity.Gang;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GangMapper
{
    Gang findRoleGangByGangID(BigDecimal p0);
    
    Gang findGangByGangName(String p0);
    
    List<Gang> findAllGang();
    
    boolean createGang(Gang p0);
    
    void updateGang(Gang p0);
}
