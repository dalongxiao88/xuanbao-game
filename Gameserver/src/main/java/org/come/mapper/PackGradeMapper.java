package org.come.mapper;

import org.come.entity.ChongjipackBean;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface PackGradeMapper
{
    List<ChongjipackBean> selectAllPack();
}
