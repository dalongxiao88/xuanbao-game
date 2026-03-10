package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.PackRecord;
import java.math.BigDecimal;
import org.come.entity.PackRecordExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 背包记录数据访问接口。
 */
public interface PackRecordMapper
{
    int countByExample(PackRecordExample packRecordExample);
    
    int deleteByExample(PackRecordExample packRecordExample);
    
    int deleteByPrimaryKey(BigDecimal roleId);
    
    int insert(PackRecord packRecord);
    
    int insertSelective(PackRecord packRecord);
    
    List<PackRecord> selectByExample(PackRecordExample packRecordExample);
    
    PackRecord selectByPrimaryKey(BigDecimal roleId);
    
    int updateByExampleSelective(@Param("record") PackRecord packRecord, @Param("example") PackRecordExample packRecordExample);
    
    int updateByExample(@Param("record") PackRecord packRecord, @Param("example") PackRecordExample packRecordExample);
    
    int updateByPrimaryKeySelective(PackRecord packRecord);
    
    int updateByPrimaryKey(PackRecord packRecord);
    
    void addSLDH(@Param("roleid") BigDecimal roleId, @Param("add") int addValue);
    
    void emptySLDH();
}
