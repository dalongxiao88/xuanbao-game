package org.come.mapper;

import org.come.bean.TtModel;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface TtModelMapper
{
    List<TtModel> getTtConfig();
    
    void updateTtConfig(TtModel p0);
}
