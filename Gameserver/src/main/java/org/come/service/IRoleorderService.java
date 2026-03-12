package org.come.service;

import org.come.entity.RoleorderExample;
import org.come.entity.Roleorder;
import java.util.List;
import java.math.BigDecimal;
/**
 * IRoleorderService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

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

