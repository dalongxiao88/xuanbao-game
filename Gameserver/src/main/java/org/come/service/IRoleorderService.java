package org.come.service;

import org.come.entity.RoleorderExample;
import org.come.entity.Roleorder;
import java.util.List;
import java.math.BigDecimal;

public interface IRoleorderService
{
    List<Roleorder> selectRoleOrders(BigDecimal p0);
    
    int countByExample(RoleorderExample p0);
    
    int deleteByExample(RoleorderExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Roleorder p0);
    
    int insertSelective(Roleorder p0);
    
    List<Roleorder> selectByExample(RoleorderExample p0);
    
    Roleorder selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(Roleorder p0, RoleorderExample p1);
    
    int updateByExample(Roleorder p0, RoleorderExample p1);
    
    int updateByPrimaryKeySelective(Roleorder p0);
    
    int updateByPrimaryKey(Roleorder p0);
}
