package org.come.service;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.PackRecord;
import java.math.BigDecimal;
import org.come.entity.PackRecordExample;

public interface IPackRecordService
{
    int countByExample(PackRecordExample p0);
    
    int deleteByExample(PackRecordExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(PackRecord p0);
    
    int insertSelective(PackRecord p0);
    
    List<PackRecord> selectByExample(PackRecordExample p0);
    
    PackRecord selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") PackRecord p0, @Param("example") PackRecordExample p1);
    
    int updateByExample(@Param("record") PackRecord p0, @Param("example") PackRecordExample p1);
    
    int updateByPrimaryKeySelective(PackRecord p0);
    
    int updateByPrimaryKey(PackRecord p0);
    
    void addSLDH(@Param("roleid") BigDecimal p0, @Param("add") int p1);
    
    void emptySLDH();
}
