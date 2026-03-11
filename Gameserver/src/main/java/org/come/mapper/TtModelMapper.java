package org.come.mapper;

import org.come.bean.TtModel;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 天梯配置 Mapper。
 */
public interface TtModelMapper
{
    List<TtModel> getTtConfig();
    
    void updateTtConfig(TtModel ttModel);
}
