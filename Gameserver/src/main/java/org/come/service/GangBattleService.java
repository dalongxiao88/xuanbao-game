package org.come.service;

import java.math.BigDecimal;
import org.come.entity.GangBattle;

public interface GangBattleService
{
    int addGangBattle(GangBattle p0);
    
    void updataGangBattle(GangBattle p0);
    
    GangBattle selectGangBattle(BigDecimal p0);
}
