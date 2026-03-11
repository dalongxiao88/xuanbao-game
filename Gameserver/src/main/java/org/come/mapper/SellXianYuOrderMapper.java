package org.come.mapper;

import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SellXianYuOrderMapper
{
    int deleteByPrimaryKey(BigDecimal orderId);
    
    int insert(SellXianYuOrder sellXianYuOrder);
    
    int insertSelective(SellXianYuOrder sellXianYuOrder);
    
    SellXianYuOrder selectByPrimaryKey(BigDecimal orderId);
    
    int updateByPrimaryKeySelective(SellXianYuOrder sellXianYuOrder);
    
    int updateByPrimaryKey(SellXianYuOrder sellXianYuOrder);
    
    List<SellXianYuOrder> selectAllNotDeposit();
    
    List<SellXianYuOrder> selectAllByRoleId(BigDecimal roleId);
    
    List<SellXianYuOrder> selectAllBySellRoleIdAndStatus(SellXianYuOrder sellXianYuOrder);
}
