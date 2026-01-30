package org.come.serviceImpl;

import java.math.BigDecimal;
import org.come.entity.GangBattle;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GangBattleMapper;
import org.come.service.GangBattleService;

public class GangBattleServiceImpl implements GangBattleService
{
    private GangBattleMapper gangBattleMapper;
    
    public GangBattleServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.gangBattleMapper = (GangBattleMapper)ctx.getBean("gangBattleMapper");
    }
    
    @Override
    public int addGangBattle(GangBattle gangBattle) {
        return this.gangBattleMapper.addGangBattle(gangBattle);
    }
    
    @Override
    public void updataGangBattle(GangBattle gangBattle) {
        this.gangBattleMapper.updataGangBattle(gangBattle);
    }
    
    @Override
    public GangBattle selectGangBattle(BigDecimal week) {
        return this.gangBattleMapper.selectGangBattle(week);
    }
}
