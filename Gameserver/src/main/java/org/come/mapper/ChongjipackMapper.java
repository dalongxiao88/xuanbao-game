package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.ChongjipackBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface ChongjipackMapper
{
    List<ChongjipackBean> selectAllPack();
    
    List<ChongjipackBean> selectChongjipack(int p0);
    
    int updateChongjipack(ChongjipackBean p0);
    
    int deleteChongjipack(@Param("id") Integer p0);
    
    int insertChongjipack(ChongjipackBean p0);
}
