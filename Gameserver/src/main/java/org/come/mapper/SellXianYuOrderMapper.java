package org.come.mapper;

import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;
/**
 * SellXianYuOrderMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

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

