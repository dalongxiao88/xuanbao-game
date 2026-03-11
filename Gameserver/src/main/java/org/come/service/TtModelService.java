package org.come.service;

import org.come.bean.TtModel;
import java.util.List;

/**
 * 天梯配置服务接口。
 */
public interface TtModelService
{
    List<TtModel> getTtConfig();
    
    void updateTtConfig(TtModel ttModel);
}
