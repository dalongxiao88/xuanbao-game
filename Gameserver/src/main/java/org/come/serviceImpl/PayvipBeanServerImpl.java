package org.come.serviceImpl;

import com.github.pagehelper.BasePageHelper;
import org.come.entity.PayvipBean;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.PayvipBeanServerMapper;
import org.come.service.PayvipBeanServer;

public class PayvipBeanServerImpl implements PayvipBeanServer
{
    private PayvipBeanServerMapper payvipBeanServerMapper;
    public static Integer pageNum;
    
    public PayvipBeanServerImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.payvipBeanServerMapper = (PayvipBeanServerMapper)ctx.getBean("payvipBeanServerMapper", PayvipBeanServerMapper.class);
    }
    
    @Override
    public List<PayvipBean> selectAllVip() {
        return this.payvipBeanServerMapper.selectAllVip();
    }
    
    @Override
    public List<PayvipBean> selectVipPage(int page) {
        BasePageHelper.startPage(page, (int)PayvipBeanServerImpl.pageNum);
        return this.payvipBeanServerMapper.selectAllVip();
    }
    
    @Override
    public int deletePayvioBean(Integer id) {
        return this.payvipBeanServerMapper.deletePayvipBean(id);
    }
    
    @Override
    public int insertPayvioBean(PayvipBean payvipBean) {
        return this.payvipBeanServerMapper.insertPayvioBean(payvipBean);
    }
    
    @Override
    public int updatePayvioBean(PayvipBean payvipBean) {
        return this.payvipBeanServerMapper.updatePayvioBean(payvipBean);
    }
    
    static {
        PayvipBeanServerImpl.pageNum = Integer.valueOf(10);
    }
}
