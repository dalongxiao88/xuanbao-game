package org.come.action.sale;

import java.util.Iterator;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.SearchGoodsResultBean;
import java.util.Date;
import org.come.entity.Salegoods;
import java.util.Calendar;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.entity.SalegoodsExample;
import org.come.until.GsonUtil;
import org.come.bean.SearchMyGoodsBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MyWaresSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        SearchMyGoodsBean bean = (SearchMyGoodsBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SearchMyGoodsBean.class);
        SalegoodsExample example = new SalegoodsExample();
        SalegoodsExample.Criteria c = example.createCriteria();
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        c.andRoleidEqualTo(roleInfo.getRole_id());
        if (bean.getFlag() != null && !"".equals(bean.getFlag())) {
            c.andFlagEqualTo(bean.getFlag());
        }
        example.setOrderByClause("uptime desc");
        BasePageHelper.startPage((int)bean.getPageNum(), 15);
        List<Salegoods> list = AllServiceUtil.getSalegoodsService().selectByExample(example);
        PageInfo<Salegoods> pageInfo = new PageInfo(list);
        Calendar calendar = Calendar.getInstance();
        for (Salegoods sale : pageInfo.getList()) {
            if ((int)sale.getFlag() == 2) {
                calendar.setTime(sale.getUptime());
                calendar.add(11, 168);
                if (new Date().getTime() > calendar.getTime().getTime()) {
                    sale.setFlag(Integer.valueOf(1));
                }
                else {
                    continue;
                }
            }
        }
        SearchGoodsResultBean resultBean = new SearchGoodsResultBean();
        resultBean.setSalegoods(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().CBGSearch2Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
