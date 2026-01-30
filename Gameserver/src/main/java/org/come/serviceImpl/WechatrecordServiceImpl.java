package org.come.serviceImpl;

import com.gl.model.Param;
import java.util.List;
import org.come.entity.Wechatrecord;
import java.math.BigDecimal;
import org.come.entity.WechatrecordExample;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.WechatrecordMapper;
import org.come.service.IWechatrecordService;

public class WechatrecordServiceImpl implements IWechatrecordService
{
    private WechatrecordMapper wechatrecordMapper;
    
    public WechatrecordServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.wechatrecordMapper = (WechatrecordMapper)ctx.getBean("wechatrecordMapper", WechatrecordMapper.class);
    }
    
    @Override
    public int countByExample(WechatrecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByExample(WechatrecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal chatId) {
        this.wechatrecordMapper.deleteByPrimaryKey(chatId);
        return 0;
    }
    
    @Override
    public int insert(Wechatrecord record) {
        this.wechatrecordMapper.insert(record);
        return 0;
    }
    
    @Override
    public int insertSelective(Wechatrecord record) {
        return 0;
    }
    
    @Override
    public List<Wechatrecord> selectByExample(WechatrecordExample example) {
        return this.wechatrecordMapper.selectByExample(example);
    }
    
    @Override
    public Wechatrecord selectByPrimaryKey(BigDecimal chatId) {
        return null;
    }
    
    @Override
    public int updateByExampleSelective(Wechatrecord record, WechatrecordExample example) {
        return 0;
    }
    
    @Override
    public int updateByExample(Wechatrecord record, WechatrecordExample example) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKeySelective(Wechatrecord record) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKey(Wechatrecord record) {
        return 0;
    }
    
    @Override
    public List<Wechatrecord> selectAll(Param param) {
        return this.wechatrecordMapper.selectAll(param);
    }
}
