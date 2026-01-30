package org.come.serviceImpl;

import org.come.entity.BuyCount;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.BuyCountMapper;
import org.come.service.BuyCountServeice;

public class BuyCountServiceImpl implements BuyCountServeice
{
    private BuyCountMapper buyCountMapper;
    
    public BuyCountServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.buyCountMapper = (BuyCountMapper)ctx.getBean("buyCountMapper");
    }
    
    @Override
    public void insertBuyCount(BuyCount buyCount) {
        this.buyCountMapper.insertBuyCount(buyCount);
    }
    
    @Override
    public void updateBuyCount(BuyCount buyCount) {
        this.buyCountMapper.updateBuyCount(buyCount);
    }
    
    @Override
    public BuyCount selectBuyCount(long Bid) {
        return this.buyCountMapper.selectBuyCount(Bid);
    }
}
