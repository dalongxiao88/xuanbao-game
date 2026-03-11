package org.come.mapper;

import java.util.List;
import org.come.bean.managerTable;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface managerTableMapper
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
