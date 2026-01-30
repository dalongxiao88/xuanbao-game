package org.come.serviceImpl;

import org.come.entity.Gangapplytable;
import java.util.List;
import java.math.BigDecimal;
import org.come.entity.Gangapply;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GangapplyMapper;
import org.come.service.IGangapplyService;

public class GangapplyServiceImpl implements IGangapplyService
{
    private GangapplyMapper gangapplyMapper;
    
    public GangapplyServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.gangapplyMapper = (GangapplyMapper)ctx.getBean("gangapplyMapper");
    }
    
    @Override
    public void insertIntGangapple(Gangapply gangapply) {
        this.gangapplyMapper.insertIntGangapple(gangapply);
    }
    
    @Override
    public Gangapply selectGangApply(BigDecimal roleid, BigDecimal gangid) {
        return this.gangapplyMapper.selectGangApply(roleid, gangid);
    }
    
    @Override
    public void deleteGangappleAll(BigDecimal roleid) {
        this.gangapplyMapper.deleteGangappleAll(roleid);
    }
    
    @Override
    public void deleteGangapple(BigDecimal roleid, BigDecimal gangid) {
        this.gangapplyMapper.deleteGangapple(roleid, gangid);
    }
    
    @Override
    public List<Gangapplytable> getGangapplytables(BigDecimal gangid) {
        return this.gangapplyMapper.getGangapplytables(gangid);
    }
    
    @Override
    public void deleteGangappleGang(BigDecimal gangid) {
        this.gangapplyMapper.deleteGangappleGang(gangid);
    }
}
