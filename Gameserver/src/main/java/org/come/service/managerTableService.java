package org.come.service;

import java.util.List;
import org.come.bean.managerTable;
import java.math.BigDecimal;
/**
 * managerTableService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

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

