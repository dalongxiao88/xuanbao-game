package org.come.serviceImpl;

import java.util.Date;
import org.come.entity.Titletable;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.TitletableMapper;
import org.come.service.ITitletableService;

public class TitleServiceImpl implements ITitletableService
{
    private TitletableMapper titletableMapper;
    
    public TitleServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.titletableMapper = (TitletableMapper)ctx.getBean("titletableMapper");
    }
    
    @Override
    public List<Titletable> selectRoleAllTitle(BigDecimal roleid) {
        List<Titletable> titletables = this.titletableMapper.selectRoleAllTitle(roleid);
        return titletables;
    }
    
    @Override
    public void createRoleTitle(Titletable titletable) {
        titletable.setRecordtime(new Date());
        if (titletable.getLimittime() == 0) {
            titletable.setLimittime(-1);
        }
        this.titletableMapper.createRoleTitle(titletable);
    }
    
    @Override
    public void updateByPrimaryKey(Titletable titletable) {
        this.titletableMapper.updateByPrimaryKey(titletable);
    }
    
    @Override
    public Titletable selectRoleTitle(BigDecimal roleid, String titlename) {
        return this.titletableMapper.selectRoleTitle(roleid, titlename);
    }
}
