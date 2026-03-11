package org.come.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.Record;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 记录 Mapper。
 */
public interface RecordMapper
{
    int insert(Record record);
    
    List<Record> selectRecordByType(@Param("recordType") int recordType, @Param("count") int count);
}
