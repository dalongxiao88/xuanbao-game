package org.come.serviceImpl;

import java.math.BigDecimal;
import org.come.entity.Openareatable;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.OpenareatableMapper;
import org.come.service.OpenareatableService;

public class OpenareatableServiceImpl implements OpenareatableService
{
    private OpenareatableMapper openareatableMapper;
    
    public OpenareatableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.openareatableMapper = (OpenareatableMapper)ctx.getBean("openareatableMapper");
    }
    
    @Override
    public List<Openareatable> selectAllOpenareatable() {
        return this.openareatableMapper.selectAllOpenareatable();
    }
    
    @Override
    public Integer insertOpenareatable(Openareatable openareatable) {
        return this.openareatableMapper.insertOpenareatable(openareatable);
    }
    
    @Override
    public Integer updateOpenareatable(Openareatable openareatable) {
        return this.openareatableMapper.updateOpenareatable(openareatable);
    }
    
    @Override
    public Integer deleteOpenareatable(BigDecimal tb_id) {
        return this.openareatableMapper.deleteOpenareatable(tb_id);
    }
    
    @Override
    public List<BigDecimal> selectTuijiNum(String tuiji) {
        return this.openareatableMapper.selectTuijiNum(tuiji);
    }
    
    @Override
    public List<Openareatable> selectAllArea(BigDecimal userid) {
        return this.openareatableMapper.selectAllArea(userid);
    }
    
    @Override
    public String selectBelong(String qid) {
        return this.openareatableMapper.selectBelong(qid);
    }
    
    @Override
    public String selectAtid(String qid) {
        return this.openareatableMapper.selectAtid(qid);
    }
    
    @Override
    public Openareatable selectOpenareatable(String qid) {
        return this.openareatableMapper.selectOpenareatable(qid);
    }
}
