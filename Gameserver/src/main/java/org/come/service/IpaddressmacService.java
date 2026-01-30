package org.come.service;

import java.util.List;
import org.come.entity.Ipaddressmac;

public interface IpaddressmacService
{
    int insert(Ipaddressmac p0);
    
    int insertSelective(Ipaddressmac p0);
    
    List<Ipaddressmac> selectIpaddressmac(String p0);
}
