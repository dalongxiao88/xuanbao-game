package org.come.serviceImpl;

import java.util.List;
import org.come.entity.PackRecord;
import java.math.BigDecimal;
import org.come.entity.PackRecordExample;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.PackRecordMapper;
import org.come.service.IPackRecordService;

public class PackRecordServiceImpl implements IPackRecordService
{
    private PackRecordMapper packRecordMapper;
    
    public PackRecordServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.packRecordMapper = (PackRecordMapper)ctx.getBean("packRecordMapper", PackRecordMapper.class);
    }
    
    @Override
    public int countByExample(PackRecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByExample(PackRecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal roleId) {
        this.packRecordMapper.deleteByPrimaryKey(roleId);
        return 0;
    }
    
    @Override
    public int insert(PackRecord record) {
        this.packRecordMapper.insert(record);
        return 0;
    }
    
    @Override
    public int insertSelective(PackRecord record) {
        return 0;
    }
    
    @Override
    public List<PackRecord> selectByExample(PackRecordExample example) {
        return this.packRecordMapper.selectByExample(example);
    }
    
    @Override
    public PackRecord selectByPrimaryKey(BigDecimal roleId) {
        return this.packRecordMapper.selectByPrimaryKey(roleId);
    }
    
    @Override
    public int updateByExampleSelective(PackRecord record, PackRecordExample example) {
        return this.packRecordMapper.updateByExampleSelective(record, example);
    }
    
    @Override
    public int updateByExample(PackRecord record, PackRecordExample example) {
        return this.packRecordMapper.updateByExample(record, example);
    }
    
    @Override
    public int updateByPrimaryKeySelective(PackRecord record) {
        return this.packRecordMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(PackRecord record) {
        return this.packRecordMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public void addSLDH(BigDecimal roleid, int add) {
        this.packRecordMapper.addSLDH(roleid, add);
    }
    
    @Override
    public void emptySLDH() {
        this.packRecordMapper.emptySLDH();
    }
}
