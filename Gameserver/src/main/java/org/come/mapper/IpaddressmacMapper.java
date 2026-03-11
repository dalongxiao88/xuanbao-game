package org.come.mapper;

import java.util.List;
import org.come.entity.Ipaddressmac;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * IP/MAC 记录 Mapper。
 */
public interface IpaddressmacMapper
{
    int insert(Ipaddressmac ipaddressmac);
    
    int insertSelective(Ipaddressmac ipaddressmac);
    
    List<Ipaddressmac> selectIpaddressmac(String roleName);
}
