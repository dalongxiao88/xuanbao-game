package org.come.mapper;

import org.come.bean.DayForOneAreaServiceMonthBean;
import org.come.bean.OneAreaServiceMonthBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.ExpensesReceipts;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;
/**
 * ExpensesReceiptsMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

@MyBatisAnnotation
public interface ExpensesReceiptsMapper
{
    int deleteByPrimaryKey(BigDecimal receiptsId);
    
    int insert(ExpensesReceipts expensesReceipts);
    
    int insertSelective(ExpensesReceipts expensesReceipts);
    
    int insert1(ExpensesReceipts expensesReceipts);
    
    ExpensesReceipts selectByPrimaryKey(BigDecimal receiptsId);
    
    int updateByPrimaryKeySelective(ExpensesReceipts expensesReceipts);
    
    int updateByPrimaryKey(ExpensesReceipts expensesReceipts);
    
    List<ExpensesReceipts> selectTimeAll(@Param("start") String start, @Param("end") String end);
    
    List<ExpensesReceipts> selectAllForAreaId(ExpensesReceipts expensesReceipts);
    
    OneAreaServiceMonthBean selectChartForMoneth(OneAreaServiceMonthBean monthBean);
    
    DayForOneAreaServiceMonthBean selectChartForDayWithSid(DayForOneAreaServiceMonthBean dayBean);
    
    List<Integer> selectAllfyId(@Param("sid") Integer sid, @Param("start") String start, @Param("end") String end);
    
    int selectAllTotal(ExpensesReceipts expensesReceipts);
    
    List<ExpensesReceipts> selectAll(ExpensesReceipts expensesReceipts);
}

