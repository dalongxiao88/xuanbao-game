package org.come.service;

import java.util.List;
import org.come.entity.Gang;
import java.math.BigDecimal;

/**
 * 帮派服务接口。
 */
public interface IGangService
{
    Gang findRoleGangByGangID(BigDecimal gangId);
    
    Gang findGangByGangName(String gangName);
    
    boolean createGang(Gang gang);
    
    List<Gang> findAllGang();
    
    void updateGang(Gang gang);
}
