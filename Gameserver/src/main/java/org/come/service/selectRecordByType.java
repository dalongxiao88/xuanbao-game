package org.come.service;

import java.util.List;
import org.come.entity.Record;

/**
 * 记录按类型查询服务接口。
 */
public interface selectRecordByType
{
    int insert(Record record);
    
    List<Record> selectRecordByType(int recordType, int count);
}
