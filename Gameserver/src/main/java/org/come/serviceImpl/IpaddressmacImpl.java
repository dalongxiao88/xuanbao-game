package org.come.serviceImpl;

import java.util.List;
import org.come.entity.Ipaddressmac;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.IpaddressmacMapper;
import org.come.service.IpaddressmacService;

public class IpaddressmacImpl implements IpaddressmacService
{
    private IpaddressmacMapper ipaddressmacMapper;
    
    public IpaddressmacImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.ipaddressmacMapper = (IpaddressmacMapper)ctx.getBean("ipaddressmacMapper");
    }
    
    @Override
    public int insert(Ipaddressmac record) {
        return this.ipaddressmacMapper.insert(record);
    }
    
    @Override
    public int insertSelective(Ipaddressmac record) {
        return this.ipaddressmacMapper.insertSelective(record);
    }
    
    @Override
    public List<Ipaddressmac> selectIpaddressmac(String addresskey) {
        return this.ipaddressmacMapper.selectIpaddressmac(addresskey);
    }
}
