package org.come.action.summoning;

import org.come.action.sys.enterGameAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Mixdeal.AnalysisString;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.entity.RoleSummoning;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetChangeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        RoleSummoning roleSummoning = (RoleSummoning)GsonUtil.getGsonUtil().getgson().fromJson(message, RoleSummoning.class);
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleSummoning dbRoleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(roleSummoning.getSid());
        if (roleSummoning.getRoleid().compareTo(dbRoleSummoning.getRoleid()) != 0) {
            return;
        }
        String mes = roleSummoning.getLyk();
        String[] lianyao = null;
        if (mes != null) {
            lianyao = mes.split("\\|");
        }
        if (lianyao != null) {
            for (int i = 0; i <= lianyao.length - 1; ++i) {
                String[] lianyao2 = lianyao[i].split("=");
                for (int k = 0; k <= lianyao2.length - 1; ++k) {
                    if (lianyao2[k].equals("忽视防御几率") || lianyao2[k].equals("忽视防御程度") || lianyao2[k].equals("致命率") || lianyao2[k].equals("命中率") || lianyao2[k].equals("狂暴率") || lianyao2[k].equals("连击次数") || lianyao2[k].equals("加成攻击") || lianyao2[k].equals("攻击") || lianyao2[k].equals("力量") || lianyao2[k].equals("敏捷") || lianyao2[k].equals("根骨") || lianyao2[k].equals("灵性") || lianyao2[k].equals("强仙法") || lianyao2[k].equals("忽视仙法") || lianyao2[k].equals("仙法狂暴") || lianyao2[k].equals("仙法狂暴程度") || lianyao2[k].equals("火法伤害") || lianyao2[k].equals("水法伤害") || lianyao2[k].equals("风法伤害") || lianyao2[k].equals("雷法伤害") || lianyao2[k].equals("伤害") || lianyao2[k].equals("狂暴") || lianyao2[k].equals("忽视") || lianyao2[k].equals("强") || lianyao2[k].equals("物理躲闪") || lianyao2[k].equals("法术躲闪") || lianyao2[k].equals("躲闪")) {
                        roleSummoning.setLyk(null);
                    }
                }
            }
        }
        roleSummoning.setSkill(dbRoleSummoning.getSkill());
        roleSummoning.setLingxi(dbRoleSummoning.getLingxi());
        roleSummoning.setPetSkills(dbRoleSummoning.getPetSkills());
        roleSummoning.setBeastSkills(dbRoleSummoning.getBeastSkills());
        roleSummoning.setPetQlSkills(dbRoleSummoning.getPetQlSkills());
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
        AllServiceUtil.getRoleSummoningService().updateRoleSummoning(roleSummoning);
    }
}
