package org.come.mapper;

import org.come.entity.BuyCount;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 限购计数 Mapper。
 */
public interface BuyCountMapper
{
    void insertBuyCount(BuyCount buyCount);
    
    void updateBuyCount(BuyCount buyCount);
    
    BuyCount selectBuyCount(long roleId);
}
