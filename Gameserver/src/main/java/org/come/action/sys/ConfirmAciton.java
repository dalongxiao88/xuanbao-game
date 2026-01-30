package org.come.action.sys;

import come.tool.newGang.GangDomain;
import come.tool.hjsl.HjslAction;
import org.come.action.suit.GemIntensify;
import java.math.BigDecimal;
import come.tool.oneArena.OneArenaAction;
import come.tool.newGang.GangUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.ConfirmBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ConfirmAciton implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        ConfirmBean confirmBean = (ConfirmBean)GsonUtil.getGsonUtil().getgson().fromJson(message, ConfirmBean.class);
        int type = confirmBean.getType();
        if (type == 47 || type == 48 || type == 49) {
            if (!roleInfo.getGangpost().equals("帮主")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮主才有权限"));
                return;
            }
            GangDomain gangDomain = GangUtil.getGangDomain(roleInfo.getGang_id());
            if (gangDomain == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有帮派"));
                return;
            }
            String msg = gangDomain.upLvl(type);
            if (msg != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
                return;
            }
        }
        else if (type == 101) {
            OneArenaAction.addNumOneArena(ctx, roleInfo, Integer.valueOf(type));
        }
        else if (type == 102) {
            GemIntensify.type102(roleInfo, ctx, null, new BigDecimal(confirmBean.getValue()));
        }
        else if (type == 103) {
            HjslAction.addNumHjsl(ctx, roleInfo, Integer.valueOf(type));
        }
    }
}
