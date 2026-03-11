package org.come.service;

import java.util.List;
import org.come.bean.managerTable;
import java.math.BigDecimal;

public interface managerTableService
{
    int deleteByPrimaryKey(BigDecimal managerId);
    
    int insert(managerTable managerTable);
    
    int insertSelective(managerTable managerTable);
    
    managerTable selectByPrimaryKey(BigDecimal managerId);
    
    int updateByPrimaryKeySelective(managerTable managerTable);
    
    int updateByPrimaryKey(managerTable managerTable);
    
    managerTable selectByUsername(managerTable managerTable);
    
    List<managerTable> selectManageForPage(int pageNum);
}
