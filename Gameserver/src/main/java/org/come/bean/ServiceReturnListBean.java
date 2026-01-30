package org.come.bean;

import org.come.entity.ExpensesReceipts;
import java.util.List;

public class ServiceReturnListBean
{
    private OneAreaServiceMonthBean areaServiceMonthBean;
    private DayForOneAreaServiceMonthBean dayForOneAreaServiceMonthBean;
    private List<ExpensesReceipts> expensesReceiptsList;
    
    public OneAreaServiceMonthBean getAreaServiceMonthBean() {
        return this.areaServiceMonthBean;
    }
    
    public void setAreaServiceMonthBean(OneAreaServiceMonthBean areaServiceMonthBean) {
        this.areaServiceMonthBean = areaServiceMonthBean;
    }
    
    public DayForOneAreaServiceMonthBean getDayForOneAreaServiceMonthBean() {
        return this.dayForOneAreaServiceMonthBean;
    }
    
    public void setDayForOneAreaServiceMonthBean(DayForOneAreaServiceMonthBean dayForOneAreaServiceMonthBean) {
        this.dayForOneAreaServiceMonthBean = dayForOneAreaServiceMonthBean;
    }
    
    public List<ExpensesReceipts> getExpensesReceiptsList() {
        return this.expensesReceiptsList;
    }
    
    public void setExpensesReceiptsList(List<ExpensesReceipts> expensesReceiptsList) {
        this.expensesReceiptsList = expensesReceiptsList;
    }
}
