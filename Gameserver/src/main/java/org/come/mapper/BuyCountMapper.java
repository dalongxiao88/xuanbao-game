package org.come.mapper;

import org.come.entity.BuyCount;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface BuyCountMapper
{
    void insertBuyCount(BuyCount p0);
    
    void updateBuyCount(BuyCount p0);
    
    BuyCount selectBuyCount(long p0);
}
