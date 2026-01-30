package org.come.service;

import org.come.bean.TtModel;
import java.util.List;

public interface TtModelService
{
    List<TtModel> getTtConfig();
    
    void updateTtConfig(TtModel p0);
}
