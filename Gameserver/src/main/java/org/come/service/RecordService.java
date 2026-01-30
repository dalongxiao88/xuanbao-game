package org.come.service;

import java.util.List;
import org.come.entity.Record;

public interface RecordService
{
    int insert(Record p0);
    
    List<Record> selectRecordByType(int p0, int p1);
}
