package org.come.until;

import java.math.BigDecimal;
import java.util.Calendar;
import org.come.serviceImpl.ExpensesReceiptsServiceImpl;
import org.come.bean.ServiceArea;
import java.util.List;
import org.come.bean.managerTable;
import org.come.serviceImpl.ServiceAreaServiceImpl;
import org.come.serviceImpl.managerTableServiceImpl;
import org.come.entity.ExpensesReceipts;
import org.come.bean.DayForOneAreaServiceMonthBean;
import org.come.bean.OneAreaServiceMonthBean;
import org.come.bean.ServiceReturnListBean;
import org.come.bean.RequestReturnBean;

public class InterfaceControlUntil
{
    private static RequestReturnBean requestReturnBean;
    private static ServiceReturnListBean serviceReturnListBean;
    private static OneAreaServiceMonthBean areaServiceMonthBean;
    private static DayForOneAreaServiceMonthBean dayForOneAreaServiceMonthBean;
    private static ExpensesReceipts expensesReceipts;
    
    public static RequestReturnBean controlManagerControl(String mes, managerTableServiceImpl impl, ServiceAreaServiceImpl serimpl) {
        managerTable manege = (managerTable)GsonUtil.getGsonUtil().getgson().fromJson(mes, managerTable.class);
        if (manege != null) {
            if (manege.getControlStyle() == 6666) {
                managerTable returnManager = impl.selectByUsername(manege);
                if (returnManager.getPwd().equals(manege.getPwd())) {
                    List<ServiceArea> userSerbice = serimpl.selectListAreaForUid(returnManager.getManaeid());
                    if (userSerbice.size() != 0 || userSerbice != null) {
                        String content = GsonUtil.getGsonUtil().getgson().toJson(userSerbice);
                        InterfaceControlUntil.requestReturnBean.setContent(content);
                        InterfaceControlUntil.requestReturnBean.setStyle("success");
                        InterfaceControlUntil.requestReturnBean.setRequresHeader(returnManager.getFlag() + "");
                    }
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (manege.getControlStyle() == 10002) {
                int a = impl.deleteByPrimaryKey(manege.getManaeid());
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("删除成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("删除失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (manege.getControlStyle() == 10000) {
                int a = impl.insert(manege);
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("插入成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("插入失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (manege.getControlStyle() == 10001) {
                int a = impl.updateByPrimaryKey(manege);
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("修改成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("修改失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (manege.getControlStyle() == 10003) {
                List<managerTable> manegerlist = impl.selectManageForPage(manege.getPageNumber());
                String content2 = GsonUtil.getGsonUtil().getgson().toJson(manegerlist);
                InterfaceControlUntil.requestReturnBean.setContent(content2);
                InterfaceControlUntil.requestReturnBean.setStyle("success");
            }
        }
        else {
            InterfaceControlUntil.requestReturnBean.setContent("请求错误");
            InterfaceControlUntil.requestReturnBean.setStyle("error");
        }
        InterfaceControlUntil.requestReturnBean.setReturnDate(GetTime.getNowMinit());
        InterfaceControlUntil.requestReturnBean.setStyle("10006");
        return InterfaceControlUntil.requestReturnBean;
    }
    
    public static RequestReturnBean serviceMoneyControl(String mes, ServiceAreaServiceImpl serviceAreaServiceImpl, ExpensesReceiptsServiceImpl expensesReceiptsServiceImpl) {
        ServiceArea service = (ServiceArea)GsonUtil.getGsonUtil().getgson().fromJson(mes, ServiceArea.class);
        if (service != null) {
            if (service.getControlStyle() == 10000) {
                int a = serviceAreaServiceImpl.insert(service);
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("添加成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("添加失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (service.getControlStyle() == 10001) {
                int a = serviceAreaServiceImpl.updateByPrimaryKey(service);
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("修改成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("修改失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (service.getControlStyle() == 10002) {
                int a = serviceAreaServiceImpl.deleteByPrimaryKey(service.getSid());
                if (a == 1) {
                    InterfaceControlUntil.requestReturnBean.setContent("删除成功");
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    InterfaceControlUntil.requestReturnBean.setContent("删除失败");
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
            }
            else if (service.getControlStyle() == 10003) {
                InterfaceControlUntil.dayForOneAreaServiceMonthBean = new DayForOneAreaServiceMonthBean();
                InterfaceControlUntil.areaServiceMonthBean = new OneAreaServiceMonthBean();
                (InterfaceControlUntil.areaServiceMonthBean = new OneAreaServiceMonthBean()).setSid(service.getSid());
                InterfaceControlUntil.dayForOneAreaServiceMonthBean.setSid(service.getSid());
                InterfaceControlUntil.expensesReceipts.setSid(service.getSid());
                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(2) + 1;
                InterfaceControlUntil.dayForOneAreaServiceMonthBean.setMonth(month + "");
                InterfaceControlUntil.areaServiceMonthBean.setYear(calendar.get(1) + "");
                InterfaceControlUntil.dayForOneAreaServiceMonthBean = expensesReceiptsServiceImpl.selectChartForDayWithSid(InterfaceControlUntil.dayForOneAreaServiceMonthBean);
                InterfaceControlUntil.areaServiceMonthBean = expensesReceiptsServiceImpl.selectChartForMoneth(InterfaceControlUntil.areaServiceMonthBean);
                InterfaceControlUntil.expensesReceipts.setPageNumber(1);
                List<ExpensesReceipts> listExpen = expensesReceiptsServiceImpl.selectAllForAreaId(InterfaceControlUntil.expensesReceipts);
                InterfaceControlUntil.serviceReturnListBean.setAreaServiceMonthBean(InterfaceControlUntil.areaServiceMonthBean);
                InterfaceControlUntil.serviceReturnListBean.setDayForOneAreaServiceMonthBean(InterfaceControlUntil.dayForOneAreaServiceMonthBean);
                InterfaceControlUntil.serviceReturnListBean.setExpensesReceiptsList(listExpen);
                String content = GsonUtil.getGsonUtil().getgson().toJson(InterfaceControlUntil.serviceReturnListBean);
                InterfaceControlUntil.requestReturnBean.setContent(content);
                InterfaceControlUntil.requestReturnBean.setStyle("success");
            }
            else if (service.getControlStyle() == 10004) {
                InterfaceControlUntil.expensesReceipts.setPageNumber(service.getPageNum());
                InterfaceControlUntil.expensesReceipts.setSid(new BigDecimal(service.getSid() + ""));
                List<ExpensesReceipts> listExpen2 = expensesReceiptsServiceImpl.selectAllForAreaId(InterfaceControlUntil.expensesReceipts);
                String content2 = null;
                if (listExpen2 != null && listExpen2.size() != 0) {
                    InterfaceControlUntil.serviceReturnListBean.setAreaServiceMonthBean(null);
                    InterfaceControlUntil.serviceReturnListBean.setDayForOneAreaServiceMonthBean(null);
                    InterfaceControlUntil.serviceReturnListBean.setExpensesReceiptsList(listExpen2);
                    content2 = GsonUtil.getGsonUtil().getgson().toJson(InterfaceControlUntil.serviceReturnListBean);
                    InterfaceControlUntil.requestReturnBean.setStyle("success");
                }
                else {
                    content2 = "最新一页";
                    InterfaceControlUntil.requestReturnBean.setStyle("error");
                }
                InterfaceControlUntil.requestReturnBean.setContent(content2);
            }
            else if (service.getControlStyle() == 10005) {
                List<ServiceArea> serviceList = serviceAreaServiceImpl.selectServiceForPage(service.getPageNum());
                String content2 = GsonUtil.getGsonUtil().getgson().toJson(serviceList);
                InterfaceControlUntil.requestReturnBean.setContent(content2);
                InterfaceControlUntil.requestReturnBean.setStyle("success");
            }
        }
        else {
            InterfaceControlUntil.requestReturnBean.setContent("请求错误");
            InterfaceControlUntil.requestReturnBean.setStyle("error");
        }
        InterfaceControlUntil.requestReturnBean.setReturnDate(GetTime.getNowMinit());
        InterfaceControlUntil.requestReturnBean.setStyle("10007");
        return InterfaceControlUntil.requestReturnBean;
    }
    
    static {
        InterfaceControlUntil.requestReturnBean = new RequestReturnBean();
        InterfaceControlUntil.serviceReturnListBean = new ServiceReturnListBean();
        InterfaceControlUntil.areaServiceMonthBean = new OneAreaServiceMonthBean();
        InterfaceControlUntil.dayForOneAreaServiceMonthBean = new DayForOneAreaServiceMonthBean();
        InterfaceControlUntil.expensesReceipts = new ExpensesReceipts();
    }
}
