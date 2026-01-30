package org.come.serviceImpl;

import java.util.List;
import org.come.bean.ServiceArea;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.ServiceAreaMapper;
import org.springframework.stereotype.Service;
import org.come.service.ServiceAreaService;

@Service
public class ServiceAreaServiceImpl implements ServiceAreaService
{
    private ServiceAreaMapper serviceAreaMapper;
    
    public ServiceAreaServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.serviceAreaMapper = (ServiceAreaMapper)ctx.getBean("serviceAreaMapper");
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal sid) {
        return this.serviceAreaMapper.deleteByPrimaryKey(sid);
    }
    
    @Override
    public int insert(ServiceArea record) {
        return this.serviceAreaMapper.insert(record);
    }
    
    @Override
    public int insertSelective(ServiceArea record) {
        return this.serviceAreaMapper.insertSelective(record);
    }
    
    @Override
    public ServiceArea selectByPrimaryKey(BigDecimal sid) {
        return this.serviceAreaMapper.selectByPrimaryKey(sid);
    }
    
    @Override
    public int updateByPrimaryKeySelective(ServiceArea record) {
        return this.serviceAreaMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(ServiceArea record) {
        return this.serviceAreaMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<BigDecimal> selectServiceAreaid(ServiceArea record) {
        return this.serviceAreaMapper.selectServiceAreaid(record);
    }
    
    @Override
    public List<ServiceArea> selectAllService() {
        return this.serviceAreaMapper.selectAllService();
    }
    
    @Override
    public List<ServiceArea> selectListAreaForUid(BigDecimal manageid) {
        return this.serviceAreaMapper.selectListAreaForUid(manageid);
    }
    
    @Override
    public List<ServiceArea> selectServiceForPage(int pageNumber) {
        return this.serviceAreaMapper.selectServiceForPage(pageNumber);
    }
}
