package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.PayvipBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 充值 VIP 配置 Mapper。
 */
public interface PayvipBeanServerMapper
{
    List<PayvipBean> selectAllVip();
    
    int deletePayvipBean(@Param("id") Integer id);
    
    int insertPayvioBean(PayvipBean payvipBean);
    
    int updatePayvioBean(PayvipBean payvipBean);
}
