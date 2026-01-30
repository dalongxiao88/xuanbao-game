package org.come.serviceImpl;

import org.come.redis.RedisCacheUtil;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.entity.Pal;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.PalMapper;
import org.come.service.PalService;

public class PalServiceImpl implements PalService
{
    private PalMapper palMapper;
    
    public PalServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.palMapper = (PalMapper)ctx.getBean("palMapper");
    }
    
    @Override
    public List<Pal> selectPalByRoleID(BigDecimal roleid) {
        List<Pal> pals = RedisControl.getS(RedisParameterUtil.PAL, roleid.toString(), Pal.class);
        return pals;
    }
    
    @Override
    public Pal selectPalByID(BigDecimal id) {
        return (Pal)RedisControl.getV(RedisParameterUtil.PAL, id.toString(), Pal.class);
    }
    
    @Override
    public void deletePal(BigDecimal id) {
        Pal pal = (Pal)RedisControl.getV(RedisParameterUtil.PAL, id.toString(), Pal.class);
        if (pal != null) {
            RedisControl.deletrValue(RedisParameterUtil.PAL, pal.getRoleId().toString(), id.toString());
        }
        RedisControl.delForKey(RedisParameterUtil.PAL, id.toString());
        RedisControl.insertController(RedisParameterUtil.PAL, id.toString(), "3");
    }
    
    @Override
    public void updatePal(Pal pal) {
        RedisControl.insertKeyT(RedisParameterUtil.PAL, pal.getId().toString(), pal);
        RedisControl.insertController(RedisParameterUtil.PAL, pal.getId().toString(), "2");
    }
    
    @Override
    public void insertPal(Pal pal) {
        pal.setId(RedisCacheUtil.getPal_pk());
        pal.setLvl(70);
        pal.setExp(0L);
        pal.setGrow(1.0);
        RedisControl.insertKeyT(RedisParameterUtil.PAL, pal.getId().toString(), pal);
        RedisControl.insertListRedis(RedisParameterUtil.PAL, pal.getRoleId().toString(), pal.getId().toString());
        RedisControl.insertController(RedisParameterUtil.PAL, pal.getId().toString(), "1");
    }
    
    @Override
    public List<Pal> selectAllPalSql() {
        return this.palMapper.selectAllPal();
    }
    
    @Override
    public List<Pal> selectPalByRoleIDSql(BigDecimal roleid) {
        return this.palMapper.selectPalByRoleID(roleid);
    }
    
    @Override
    public void deletePalSql(BigDecimal id) {
        this.palMapper.deletePal(id);
    }
    
    @Override
    public void updatePalSql(Pal pal) {
        this.palMapper.updatePal(pal);
    }
    
    @Override
    public void insertPalSql(Pal pal) {
        this.palMapper.insertPal(pal);
    }
    
    @Override
    public void deletePalList(List<BigDecimal> list) {
        this.palMapper.deletePalList(list);
    }
    
    @Override
    public void updatePalList(List<Pal> list) {
        this.palMapper.updatePalList(list);
    }
    
    @Override
    public void insertPalList(List<Pal> list) {
        this.palMapper.insertPalList(list);
    }
}
