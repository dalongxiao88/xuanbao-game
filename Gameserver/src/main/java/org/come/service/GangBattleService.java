package org.come.service;

import java.math.BigDecimal;
import org.come.entity.GangBattle;

/**
 * 帮战服务接口。
 */
public interface GangBattleService
{
    int addGangBattle(GangBattle gangBattle);
    
    void updataGangBattle(GangBattle gangBattle);
    
    GangBattle selectGangBattle(BigDecimal gangId);
}
