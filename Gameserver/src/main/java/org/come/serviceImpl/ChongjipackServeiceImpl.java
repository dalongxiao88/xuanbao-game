package org.come.serviceImpl;

import com.github.pagehelper.BasePageHelper;
import org.come.entity.ChongjipackBean;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.ChongjipackMapper;
import org.come.service.ChongjipackServeice;

public class ChongjipackServeiceImpl implements ChongjipackServeice
{
    private ChongjipackMapper chongjipackMapper;
    public static Integer pageNum;
    
    public ChongjipackServeiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.chongjipackMapper = (ChongjipackMapper)ctx.getBean("chongjipackMapper", ChongjipackMapper.class);
    }
    
    @Override
    public List<ChongjipackBean> selectChongjipack(int type, int page) {
        BasePageHelper.startPage(page, (int)ChongjipackServeiceImpl.pageNum);
        return this.chongjipackMapper.selectChongjipack(type);
    }
    
    @Override
    public int deleteChongjipack(Integer id) {
        return this.chongjipackMapper.deleteChongjipack(id);
    }
    
    @Override
    public int insertChongjipack(ChongjipackBean chongjipackBean) {
        return this.chongjipackMapper.insertChongjipack(chongjipackBean);
    }
    
    @Override
    public int updateChongjipack(ChongjipackBean chongjipackBean) {
        return this.chongjipackMapper.updateChongjipack(chongjipackBean);
    }
    
    @Override
    public List<ChongjipackBean> selectAllPack() {
        return this.chongjipackMapper.selectAllPack();
    }
    
    static {
        ChongjipackServeiceImpl.pageNum = Integer.valueOf(10);
    }
}
