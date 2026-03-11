package org.come.mapper;

import java.util.List;
import org.come.entity.Gang;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 帮派 Mapper。
 */
public interface GangMapper
{
    Gang findRoleGangByGangID(BigDecimal gangId);
    
    Gang findGangByGangName(String gangName);
    
    List<Gang> findAllGang();
    
    boolean createGang(Gang gang);
    
    void updateGang(Gang gang);
}
