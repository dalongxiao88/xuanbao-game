package org.come.mapper;

import org.come.bean.DayForOneAreaServiceMonthBean;
import org.come.bean.OneAreaServiceMonthBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.ExpensesReceipts;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface ExpensesReceiptsMapper
{
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(ExpensesReceipts p0);
    
    int insertSelective(ExpensesReceipts p0);
    
    int insert1(ExpensesReceipts p0);
    
    ExpensesReceipts selectByPrimaryKey(BigDecimal p0);
    
    int updateByPrimaryKeySelective(ExpensesReceipts p0);
    
    int updateByPrimaryKey(ExpensesReceipts p0);
    
    List<ExpensesReceipts> selectTimeAll(@Param("start") String p0, @Param("end") String p1);
    
    List<ExpensesReceipts> selectAllForAreaId(ExpensesReceipts p0);
    
    OneAreaServiceMonthBean selectChartForMoneth(OneAreaServiceMonthBean p0);
    
    DayForOneAreaServiceMonthBean selectChartForDayWithSid(DayForOneAreaServiceMonthBean p0);
    
    List<Integer> selectAllfyId(@Param("sid") Integer p0, @Param("start") String p1, @Param("end") String p2);
    
    int selectAllTotal(ExpensesReceipts p0);
    
    List<ExpensesReceipts> selectAll(ExpensesReceipts p0);
}
