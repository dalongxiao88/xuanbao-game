package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.GangBattle;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 帮战 Mapper。
 */
public interface GangBattleMapper
{
    int addGangBattle(GangBattle gangBattle);
    
    void updataGangBattle(GangBattle gangBattle);
    
    GangBattle selectGangBattle(BigDecimal gangId);
}
