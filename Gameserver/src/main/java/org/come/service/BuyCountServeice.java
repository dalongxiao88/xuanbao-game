package org.come.service;

import org.come.entity.BuyCount;

public interface BuyCountServeice
{
    void insertBuyCount(BuyCount p0);
    
    void updateBuyCount(BuyCount p0);
    
    BuyCount selectBuyCount(long p0);
}
