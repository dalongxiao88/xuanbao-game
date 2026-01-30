package org.come.serviceImpl;

import org.come.bean.DayForOneAreaServiceMonthBean;
import org.come.bean.OneAreaServiceMonthBean;
import java.util.List;
import org.come.entity.ExpensesReceipts;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.ExpensesReceiptsMapper;
import org.come.service.ExpensesReceiptsService;

public class ExpensesReceiptsServiceImpl implements ExpensesReceiptsService
{
    private ExpensesReceiptsMapper expensesReceiptsMapper;
    
    public ExpensesReceiptsServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.expensesReceiptsMapper = (ExpensesReceiptsMapper)ctx.getBean("expensesReceiptsMapper");
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal erid) {
        return 0;
    }
    
    @Override
    public int insert(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.insert(record);
    }
    
    @Override
    public int insertSelective(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.insertSelective(record);
    }
    
    @Override
    public int insert1(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.insert1(record);
    }
    
    @Override
    public ExpensesReceipts selectByPrimaryKey(BigDecimal erid) {
        return this.expensesReceiptsMapper.selectByPrimaryKey(erid);
    }
    
    @Override
    public int updateByPrimaryKeySelective(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<ExpensesReceipts> selectTimeAll(String start, String end) {
        return this.expensesReceiptsMapper.selectTimeAll(start, end);
    }
    
    @Override
    public List<ExpensesReceipts> selectAllForAreaId(ExpensesReceipts record) {
        return this.expensesReceiptsMapper.selectAllForAreaId(record);
    }
    
    @Override
    public OneAreaServiceMonthBean selectChartForMoneth(OneAreaServiceMonthBean areaServiceMonthBean) {
        return this.expensesReceiptsMapper.selectChartForMoneth(areaServiceMonthBean);
    }
    
    @Override
    public DayForOneAreaServiceMonthBean selectChartForDayWithSid(DayForOneAreaServiceMonthBean areaServiceMonthBean) {
        return this.expensesReceiptsMapper.selectChartForDayWithSid(areaServiceMonthBean);
    }
    
    @Override
    public List<Integer> selectAllfyId(Integer sid, String start, String end) {
        return this.expensesReceiptsMapper.selectAllfyId(sid, start, end);
    }
    
    @Override
    public int selectAllTotal(ExpensesReceipts expensesReceipts) {
        return this.expensesReceiptsMapper.selectAllTotal(expensesReceipts);
    }
    
    @Override
    public List<ExpensesReceipts> selectAll(ExpensesReceipts expensesReceipts) {
        return this.expensesReceiptsMapper.selectAll(expensesReceipts);
    }
}
