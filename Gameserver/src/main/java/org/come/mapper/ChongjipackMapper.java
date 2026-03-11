package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.ChongjipackBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 冲级礼包配置 Mapper。
 */
public interface ChongjipackMapper
{
    List<ChongjipackBean> selectAllPack();
    
    List<ChongjipackBean> selectChongjipack(int type);
    
    int updateChongjipack(ChongjipackBean chongjipackBean);
    
    int deleteChongjipack(@Param("id") Integer id);
    
    int insertChongjipack(ChongjipackBean chongjipackBean);
}
