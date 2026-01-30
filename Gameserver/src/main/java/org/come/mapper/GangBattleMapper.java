package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.GangBattle;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GangBattleMapper
{
    int addGangBattle(GangBattle p0);
    
    void updataGangBattle(GangBattle p0);
    
    GangBattle selectGangBattle(BigDecimal p0);
}
