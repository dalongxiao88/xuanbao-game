package org.come.serviceImpl;

import java.util.List;
import org.come.entity.Gang;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GangMapper;
import org.come.service.IGangService;

public class GangServiceImpl implements IGangService
{
    private GangMapper gangMapper;
    
    public GangServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.gangMapper = (GangMapper)ctx.getBean("gangMapper");
    }
    
    @Override
    public Gang findRoleGangByGangID(BigDecimal gangid) {
        Gang gang = this.gangMapper.findRoleGangByGangID(gangid);
        return gang;
    }
    
    @Override
    public boolean createGang(Gang gang) {
        boolean isSuccess = this.gangMapper.createGang(gang);
        return isSuccess;
    }
    
    @Override
    public Gang findGangByGangName(String gangname) {
        Gang gang = this.gangMapper.findGangByGangName(gangname);
        return gang;
    }
    
    @Override
    public List<Gang> findAllGang() {
        List<Gang> gangs = this.gangMapper.findAllGang();
        return gangs;
    }
    
    @Override
    public void updateGang(Gang gang) {
        this.gangMapper.updateGang(gang);
    }
}
