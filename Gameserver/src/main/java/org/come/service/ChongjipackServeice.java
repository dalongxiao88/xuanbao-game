package org.come.service;

import org.come.entity.ChongjipackBean;
import java.util.List;

/**
 * 冲级礼包配置服务接口。
 */
public interface ChongjipackServeice
{
    List<ChongjipackBean> selectAllPack();
    
    List<ChongjipackBean> selectChongjipack(int type, int page);
    
    int deleteChongjipack(Integer id);
    
    int insertChongjipack(ChongjipackBean chongjipackBean);
    
    int updateChongjipack(ChongjipackBean chongjipackBean);
}
