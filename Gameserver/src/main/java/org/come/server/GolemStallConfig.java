package org.come.server;

import java.math.BigDecimal;

public class GolemStallConfig
{
    private BigDecimal prices;
    private int maxSum;
    
    public BigDecimal getPrices() {
        return this.prices;
    }
    
    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }
    
    public int getMaxSum() {
        return this.maxSum;
    }
    
    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }
}
