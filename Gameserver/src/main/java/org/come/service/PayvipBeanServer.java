package org.come.service;

import org.come.entity.PayvipBean;
import java.util.List;

public interface PayvipBeanServer
{
    List<PayvipBean> selectAllVip();
    
    List<PayvipBean> selectVipPage(int p0);
    
    int deletePayvioBean(Integer p0);
    
    int insertPayvioBean(PayvipBean p0);
    
    int updatePayvioBean(PayvipBean p0);
}
