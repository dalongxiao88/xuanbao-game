package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.PayvipBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface PayvipBeanServerMapper
{
    List<PayvipBean> selectAllVip();
    
    int deletePayvipBean(@Param("id") Integer p0);
    
    int insertPayvioBean(PayvipBean p0);
    
    int updatePayvioBean(PayvipBean p0);
}
