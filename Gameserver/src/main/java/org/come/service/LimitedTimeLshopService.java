package org.come.service;

import org.come.model.Lshop;
import java.math.BigDecimal;

/**
 * 限时商城 Redis 服务接口。
 */
public interface LimitedTimeLshopService
{
    void addReidsLimit(BigDecimal roleId, String saleTime, Lshop lshop, int count);
    
    Lshop selectByID(BigDecimal roleId, String saleTime, int count);
}
