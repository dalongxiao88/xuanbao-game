package org.come.service;

import org.come.model.Lshop;
import java.math.BigDecimal;

public interface LimitedTimeLshopService
{
    void addReidsLimit(BigDecimal p0, String p1, Lshop p2, int p3);
    
    Lshop selectByID(BigDecimal p0, String p1, int p2);
}
