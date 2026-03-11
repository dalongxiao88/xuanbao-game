package org.come.service;

import java.util.List;
import org.come.entity.Ipaddressmac;

/**
 * IP/MAC 记录服务接口。
 */
public interface IpaddressmacService
{
    int insert(Ipaddressmac ipaddressmac);
    
    int insertSelective(Ipaddressmac ipaddressmac);
    
    List<Ipaddressmac> selectIpaddressmac(String roleName);
}
