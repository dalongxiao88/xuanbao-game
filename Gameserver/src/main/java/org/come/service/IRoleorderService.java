package org.come.service;

import org.come.entity.RoleorderExample;
import org.come.entity.Roleorder;
import java.util.List;
import java.math.BigDecimal;

public interface IRoleorderService
{
    List<Roleorder> selectRoleOrders(BigDecimal roleId);
    
    int countByExample(RoleorderExample roleorderExample);
    
    int deleteByExample(RoleorderExample roleorderExample);
    
    int deleteByPrimaryKey(BigDecimal orderId);
    
    int insert(Roleorder roleorder);
    
    int insertSelective(Roleorder roleorder);
    
    List<Roleorder> selectByExample(RoleorderExample roleorderExample);
    
    Roleorder selectByPrimaryKey(BigDecimal orderId);
    
    int updateByExampleSelective(Roleorder roleorder, RoleorderExample roleorderExample);
    
    int updateByExample(Roleorder roleorder, RoleorderExample roleorderExample);
    
    int updateByPrimaryKeySelective(Roleorder roleorder);
    
    int updateByPrimaryKey(Roleorder roleorder);
}
