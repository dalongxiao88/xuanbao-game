package org.come.action.sale;

import org.come.entity.Salegoods;
import org.come.entity.CollectionExample;
import org.come.until.GsonUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import org.come.bean.BuyBtnGetBean;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsBuyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal saleid = new BigDecimal(message);
        BuyBtnGetBean bean = new BuyBtnGetBean();
        Salegoods sale = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(saleid);
        LoginResult role = AllServiceUtil.getRoleTableService().selectRoleID(sale.getRoleid());
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (sale.getBuyrole() != null && sale.getBuyrole().compareTo(new BigDecimal(0)) != 0 && roleInfo.getRole_id().compareTo(sale.getBuyrole()) != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("非指定角色！！"));
            bean.setFlag(false);
            String msg = Agreement.getAgreement().CBGBuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToSlef(ctx, msg);
            return;
        }
        CollectionExample example = new CollectionExample();
        CollectionExample.Criteria c = example.createCriteria();
        c.andSaleidEqualTo(saleid);
        int count = AllServiceUtil.getCollectionService().countByExample(example);
        bean.setGoodsRoleName(role.getRolename());
        bean.setInGetPeoSum(count + "");
        bean.setFlag(true);
        String msg2 = Agreement.getAgreement().CBGBuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToSlef(ctx, msg2);
    }
}
