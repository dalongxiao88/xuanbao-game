package org.come.mapper;

import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SellXianYuOrderMapper
{
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(SellXianYuOrder p0);
    
    int insertSelective(SellXianYuOrder p0);
    
    SellXianYuOrder selectByPrimaryKey(BigDecimal p0);
    
    int updateByPrimaryKeySelective(SellXianYuOrder p0);
    
    int updateByPrimaryKey(SellXianYuOrder p0);
    
    List<SellXianYuOrder> selectAllNotDeposit();
    
    List<SellXianYuOrder> selectAllByRoleId(BigDecimal p0);
    
    List<SellXianYuOrder> selectAllBySellRoleIdAndStatus(SellXianYuOrder p0);
}
