package org.come.serviceImpl;

import java.util.List;
import org.come.bean.managerTable;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.managerTableMapper;
import org.springframework.stereotype.Service;
import org.come.service.managerTableService;

@Service
public class managerTableServiceImpl implements managerTableService
{
    private managerTableMapper managerTableMapper;
    
    public managerTableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.managerTableMapper = (managerTableMapper)ctx.getBean("managerTableMapper");
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal manaeid) {
        return this.managerTableMapper.deleteByPrimaryKey(manaeid);
    }
    
    @Override
    public int insert(managerTable record) {
        return this.managerTableMapper.insert(record);
    }
    
    @Override
    public int insertSelective(managerTable record) {
        return 0;
    }
    
    @Override
    public managerTable selectByPrimaryKey(BigDecimal manaeid) {
        return null;
    }
    
    @Override
    public int updateByPrimaryKeySelective(managerTable record) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKey(managerTable record) {
        return 0;
    }
    
    @Override
    public managerTable selectByUsername(managerTable record) {
        return this.managerTableMapper.selectByUsername(record);
    }
    
    @Override
    public List<managerTable> selectManageForPage(int pageNumber) {
        return this.managerTableMapper.selectManageForPage(pageNumber);
    }
}
