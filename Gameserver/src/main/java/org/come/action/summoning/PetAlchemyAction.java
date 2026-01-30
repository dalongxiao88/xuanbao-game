package org.come.action.summoning;

import org.come.entity.RoleSummoning;
import org.come.until.AllServiceUtil;
import org.come.action.sys.enterGameAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Mixdeal.AnalysisString;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.bean.PetAlchemyBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetAlchemyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        PetAlchemyBean petAlchemyBean = (PetAlchemyBean)GsonUtil.getGsonUtil().getgson().fromJson(message, PetAlchemyBean.class);
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleSummoning roleSummoning = petAlchemyBean.getRoleSummoning();
        int lvl = AnalysisString.petLvlint((int)roleSummoning.getGrade());
        int Turn = AnalysisString.petTurnRount((int)roleSummoning.getGrade());
        if (Turn < 4) {
            if ((int)roleSummoning.getBone() < lvl || (int)roleSummoning.getSpir() < lvl || (int)roleSummoning.getPower() < lvl || (int)roleSummoning.getSpeed() < lvl) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning.getSummoningname() + "]属性点异常，请尽快使用混元丹，20秒后退出游戏！！！"));
                enterGameAction.ExiteGame(loginResult);
            }
        }
        else if ((int)roleSummoning.getBone() < lvl || (int)roleSummoning.getSpir() < lvl || (int)roleSummoning.getPower() < lvl || (int)roleSummoning.getSpeed() < lvl || (int)roleSummoning.getCalm() < lvl) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning.getSummoningname() + "]属性点异常，请尽快使用混元丹，20秒后退出游戏！！！"));
            enterGameAction.ExiteGame(loginResult);
        }
        if (roleSummoning.getCanpoint() < 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning.getSummoningname() + "]属性点异常，请尽快使用混元丹，20秒后退出游戏！！！"));
            enterGameAction.ExiteGame(loginResult);
        }
        if (petAlchemyBean.getGoodstable().getType() != 701L) {
            AllServiceUtil.getRoleSummoningService().updateRoleSummoning(petAlchemyBean.getRoleSummoning());
        }
        AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(petAlchemyBean.getGoodstable().getRgid());
        AllServiceUtil.getGoodsrecordService().insert(petAlchemyBean.getGoodstable(), null, Integer.valueOf(1), Integer.valueOf(12));
    }
}
