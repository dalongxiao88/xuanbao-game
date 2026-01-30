package org.come.serviceImpl;

import org.come.entity.PayvipBean;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.PayVipMapper;
import org.come.service.PayVipService;

public class PayVipServiceImpl implements PayVipService
{
    private PayVipMapper payVipMapper;
    
    public PayVipServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.payVipMapper = (PayVipMapper)ctx.getBean("payVipMapper", PayVipMapper.class);
    }
    
    @Override
    public List<PayvipBean> selectAllVip() {
        return this.payVipMapper.selectAllVip();
    }
}
