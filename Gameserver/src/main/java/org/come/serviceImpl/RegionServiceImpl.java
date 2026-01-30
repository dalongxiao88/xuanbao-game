package org.come.serviceImpl;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.RegionMapper;
import org.come.service.RegionService;

public class RegionServiceImpl implements RegionService
{
    private RegionMapper regionMapper;
    
    public RegionServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.regionMapper = (RegionMapper)ctx.getBean("regionMapper");
    }
    
    @Override
    public List<Region> selectRegion(BigDecimal quId, String raName) {
        return this.regionMapper.selectRegion(quId, raName);
    }
    
    @Override
    public List<RoleTableNew> selectRole(BigDecimal userId, Integer quid) {
        return this.regionMapper.selectRole(userId, quid);
    }
    
    @Override
    public List<String> selectRegionAll() {
        return this.regionMapper.selectRegionAll();
    }
}
