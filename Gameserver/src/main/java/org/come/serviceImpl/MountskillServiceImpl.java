package org.come.serviceImpl;

import org.come.entity.MountSkill;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.MountskillMapper;
import org.come.service.IMountskillService;

public class MountskillServiceImpl implements IMountskillService
{
    private MountskillMapper mountskillMapper;
    
    public MountskillServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.mountskillMapper = (MountskillMapper)ctx.getBean("mountskillMapper");
    }
    
    @Override
    public List<MountSkill> selectMountskillsByMountid(BigDecimal mid) {
        List<MountSkill> mountskill = this.mountskillMapper.selectMountskillsByMountid(mid);
        return mountskill;
    }
    
    @Override
    public void deleteMountskills(BigDecimal mid) {
        this.mountskillMapper.deleteMountskills(mid);
    }
    
    @Override
    public void insertMountskills(MountSkill mountSkill) {
        this.mountskillMapper.insertMountskills(mountSkill);
    }
    
    @Override
    public List<MountSkill> selectAllMountskills() {
        return this.mountskillMapper.selectAllMountskills();
    }
}
