package org.come.service;

import java.util.List;
import org.come.entity.Record;

/**
 * 记录服务接口。
 */
public interface RecordService
{
    int insert(Record record);
    
    List<Record> selectRecordByType(int recordType, int count);
}
