package org.come.service;

import java.util.List;
import org.come.entity.Gang;
import java.math.BigDecimal;

public interface IGangService
{
    Gang findRoleGangByGangID(BigDecimal p0);
    
    Gang findGangByGangName(String p0);
    
    boolean createGang(Gang p0);
    
    List<Gang> findAllGang();
    
    void updateGang(Gang p0);
}
