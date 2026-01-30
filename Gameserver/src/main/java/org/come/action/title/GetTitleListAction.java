package org.come.action.title;

import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Titletable;
import org.come.bean.TitleBean;
import come.tool.Title.TitleUtil;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GetTitleListAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal roleID = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getRole_id();
        List<Titletable> titletables = AllServiceUtil.getTitletableService().selectRoleAllTitle(roleID);
        titletables = TitleUtil.getTitles(roleID, titletables);
        TitleBean titleBean = new TitleBean();
        titleBean.setTitletables(titletables);
        for (Titletable titletable : titletables) {
            titletable.setColor(GameServer.getTitleColor(titletable.getColor()));
        }
        String msg = Agreement.getAgreement().TitleListAgreement(GsonUtil.getGsonUtil().getgson().toJson(titleBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
