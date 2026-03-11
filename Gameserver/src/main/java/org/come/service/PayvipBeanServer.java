package org.come.service;

import org.come.entity.PayvipBean;
import java.util.List;

/**
 * 充值 VIP 配置服务接口。
 */
public interface PayvipBeanServer
{
    List<PayvipBean> selectAllVip();
    
    List<PayvipBean> selectVipPage(int page);
    
    int deletePayvioBean(Integer id);
    
    int insertPayvioBean(PayvipBean payvipBean);
    
    int updatePayvioBean(PayvipBean payvipBean);
}
