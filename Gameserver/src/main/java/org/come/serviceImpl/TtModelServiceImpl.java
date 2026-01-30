package org.come.serviceImpl;

import org.come.bean.TtModel;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.TtModelMapper;
import org.come.service.TtModelService;

public class TtModelServiceImpl implements TtModelService
{
    private TtModelMapper ttModelMapper;
    
    public TtModelServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.ttModelMapper = (TtModelMapper)ctx.getBean("ttModelMapper");
    }
    
    @Override
    public List<TtModel> getTtConfig() {
        return this.ttModelMapper.getTtConfig();
    }
    
    @Override
    public void updateTtConfig(TtModel ttModel) {
        this.ttModelMapper.updateTtConfig(ttModel);
    }
}
