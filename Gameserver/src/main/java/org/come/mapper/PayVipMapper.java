package org.come.mapper;

import org.come.entity.PayvipBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface PayVipMapper
{
    List<PayvipBean> selectAllVip();
}
