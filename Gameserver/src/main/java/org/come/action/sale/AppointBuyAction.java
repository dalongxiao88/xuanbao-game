package org.come.action.sale;

import org.come.entity.Salegoods;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.SearchGoodsResultBean;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.entity.SalegoodsExample;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AppointBuyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        SalegoodsExample example = new SalegoodsExample();
        SalegoodsExample.Criteria c = example.createCriteria();
        c.andBuyroleEqualTo(roleInfo.getRole_id());
        int pageNum = 1;
        if (message != null) {
            pageNum = Integer.parseInt(message);
        }
        BasePageHelper.startPage(pageNum, 15);
        List<Salegoods> list = AllServiceUtil.getSalegoodsService().selectByExample(example);
        PageInfo<Salegoods> pageInfo = new PageInfo(list);
        SearchGoodsResultBean resultBean = new SearchGoodsResultBean();
        resultBean.setSalegoods(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().CBGSearch4Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
