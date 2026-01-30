package org.come.serviceImpl;

import java.util.List;
import org.come.until.TimeUntil;
import org.come.entity.Record;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.RecordMapper;
import org.come.service.RecordService;

public class RecordServiceImpl implements RecordService
{
    private RecordMapper recordMapper;
    
    public RecordServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.recordMapper = (RecordMapper)ctx.getBean("recordMapper", RecordMapper.class);
    }
    
    @Override
    public int insert(Record record) {
        record.setRecordTime(TimeUntil.getPastDate());
        return this.recordMapper.insert(record);
    }
    
    @Override
    public List<Record> selectRecordByType(int recordType, int count) {
        return this.recordMapper.selectRecordByType(recordType, count);
    }
}
