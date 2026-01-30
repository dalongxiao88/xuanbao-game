package org.come.service;

import java.util.List;
import org.come.bean.managerTable;
import java.math.BigDecimal;

public interface managerTableService
{
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(managerTable p0);
    
    int insertSelective(managerTable p0);
    
    managerTable selectByPrimaryKey(BigDecimal p0);
    
    int updateByPrimaryKeySelective(managerTable p0);
    
    int updateByPrimaryKey(managerTable p0);
    
    managerTable selectByUsername(managerTable p0);
    
    List<managerTable> selectManageForPage(int p0);
}
