package org.come.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.Record;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RecordMapper
{
    int insert(Record p0);
    
    List<Record> selectRecordByType(@Param("recordType") int p0, @Param("count") int p1);
}
