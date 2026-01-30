package org.come.action.sale;

import java.util.Calendar;
import org.come.entity.Salegoods;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.SearchOrderResultBean;
import org.come.entity.Roleorder;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.entity.RoleorderExample;
import org.come.until.GsonUtil;
import org.come.bean.SearchOrderBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MyOrderSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        SearchOrderBean bean = (SearchOrderBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SearchOrderBean.class);
        RoleorderExample example = new RoleorderExample();
        RoleorderExample.Criteria c = example.createCriteria();
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        c.andRoleidEqualTo(roleInfo.getRole_id());
        if (bean.getStatus() != null && !"".equals(bean.getStatus())) {
            c.andStatusEqualTo(bean.getStatus());
        }
        if (bean.getTime() != null && !"".equals(bean.getTime())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int day = 0;
            if ((int)bean.getTime() == 1) {
                day = 7;
            }
            else {
                day = 30;
            }
            String nowdayTime = dateFormat.format(getDate(Integer.valueOf(day)));
            Date nowDate = null;
            try {
                nowDate = dateFormat.parse(nowdayTime);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            if ((int)bean.getTime() == 3) {
                c.andBuytimeLessThan(nowDate);
            }
            else {
                c.andBuytimeGreaterThan(nowDate);
            }
        }
        BasePageHelper.startPage((int)bean.getPageNum(), 15);
        List<Roleorder> list = AllServiceUtil.getRoleorderService().selectByExample(example);
        PageInfo<Roleorder> pageInfo = new PageInfo(list);
        for (Roleorder roleorder : pageInfo.getList()) {
            Salegoods salegoods = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(roleorder.getSaleid());
            if (salegoods == null) {
                continue;
            }
            else {
                roleorder.setSalename(salegoods.getSalename());
                roleorder.setSaleskin(salegoods.getSaleskin());
                roleorder.setSaleprice(salegoods.getSaleprice());
                roleorder.setSaletype(salegoods.getSaletype());
                roleorder.setOtherid(salegoods.getOtherid());
            }
        }
        SearchOrderResultBean resultBean = new SearchOrderResultBean();
        resultBean.setRoleorders(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        String msg = Agreement.getAgreement().CBGSearch3Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public static Date getDate(Integer day) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(5, date.get(5) - (int)day);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }
}
