package org.come.service;

import org.come.entity.BuyCount;

/**
 * 限购计数服务接口。
 */
public interface BuyCountServeice
{
    void insertBuyCount(BuyCount buyCount);
    
    void updateBuyCount(BuyCount buyCount);
    
    BuyCount selectBuyCount(long roleId);
}
