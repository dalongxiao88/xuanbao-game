package org.come.service;

import org.come.entity.ChongjipackBean;
import java.util.List;

public interface ChongjipackServeice
{
    List<ChongjipackBean> selectAllPack();
    
    List<ChongjipackBean> selectChongjipack(int p0, int p1);
    
    int deleteChongjipack(Integer p0);
    
    int insertChongjipack(ChongjipackBean p0);
    
    int updateChongjipack(ChongjipackBean p0);
}
