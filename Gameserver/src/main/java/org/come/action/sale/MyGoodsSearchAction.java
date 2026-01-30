package org.come.action.sale;

import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.entity.Roleorder;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.SearchOrderResultBean;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MyGoodsSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BasePageHelper.startPage(Integer.parseInt(message), 15);
        List<Roleorder> list = AllServiceUtil.getRoleorderService().selectRoleOrders(roleInfo.getRole_id());
        PageInfo<Roleorder> pageInfo = new PageInfo(list);
        SearchOrderResultBean resultBean = new SearchOrderResultBean();
        resultBean.setRoleorders(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        String msg = Agreement.getAgreement().CBGSearch5Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
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
