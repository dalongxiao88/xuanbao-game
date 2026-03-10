package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.RoleorderExample;
import org.come.entity.Roleorder;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RoleorderMapper
{
    List<Roleorder> selectRoleOrders(BigDecimal roleId);
    
    int countByExample(RoleorderExample roleorderExample);
    
    int deleteByExample(RoleorderExample roleorderExample);
    
    int deleteByPrimaryKey(BigDecimal orderId);
    
    int insert(Roleorder roleorder);
    
    int insertSelective(Roleorder roleorder);
    
    List<Roleorder> selectByExample(RoleorderExample roleorderExample);
    
    Roleorder selectByPrimaryKey(BigDecimal orderId);
    
    int updateByExampleSelective(@Param("record") Roleorder roleorder, @Param("example") RoleorderExample roleorderExample);
    
    int updateByExample(@Param("record") Roleorder roleorder, @Param("example") RoleorderExample roleorderExample);
    
    int updateByPrimaryKeySelective(Roleorder roleorder);
    
    int updateByPrimaryKey(Roleorder roleorder);
}
