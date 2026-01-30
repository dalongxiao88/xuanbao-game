package org.come.mapper;

import java.util.List;
import org.come.entity.Ipaddressmac;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface IpaddressmacMapper
{
    int insert(Ipaddressmac p0);
    
    int insertSelective(Ipaddressmac p0);
    
    List<Ipaddressmac> selectIpaddressmac(String p0);
}
