package org.come.action.sys;

import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Good.ExpUtil;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class expAddAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.split("=")[0].equals("expAddJ")) {
            AssetUpdate assetUpdate = null;
            StringBuffer buffer = new StringBuffer();
            Long exp = Long.valueOf(Long.parseLong(message.split("=")[1]));
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(25);
            }
            ExpUtil.RoleExp(loginResult, (long)exp);
            Long zjy = Long.valueOf(Long.parseLong(loginResult.getExperience().toString()) + (long)exp);
            assetUpdate.updata("R" + loginResult.getGrade() + "=" + zjy + "=" + loginResult.getHp() + "=" + loginResult.getMp());
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("你获得经验#G");
            buffer.append(exp);
            assetUpdate.setMsg(buffer.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (message.split("=")[0].equals("expAddY")) {
            long xh = Long.parseLong(message.split("=")[2]);
            if (xh > loginResult.getCodecard().longValue()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你仙玉不足" + xh));
                return;
            }
            AssetUpdate assetUpdate2 = null;
            StringBuffer buffer2 = new StringBuffer();
            Long exp2 = Long.valueOf(Long.parseLong(message.split("=")[1]));
            if (assetUpdate2 == null) {
                assetUpdate2 = new AssetUpdate(25);
            }
            ExpUtil.RoleExp(loginResult, (long)exp2);
            assetUpdate2.setData("X=" + -xh);
            Long zjy2 = Long.valueOf(Long.parseLong(loginResult.getExperience().toString()) + (long)exp2);
            assetUpdate2.updata("R" + loginResult.getGrade() + "=" + zjy2 + "=" + loginResult.getHp() + "=" + loginResult.getMp());
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - xh));
            buffer2.append("花费【#G" + xh + "#Y】仙玉，获得经验#G");
            buffer2.append(exp2);
            assetUpdate2.setMsg(buffer2.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        else if (message.split("=")[0].equals("expAddM")) {
            AssetUpdate assetUpdate = null;
            StringBuffer buffer = new StringBuffer();
            Long exp = Long.valueOf(Long.parseLong(message.split("=")[1]));
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(25);
            }
            ExpUtil.RoleExp(loginResult, (long)exp);
            Long zjy = Long.valueOf(Long.parseLong(loginResult.getExperience().toString()) + (long)exp);
            assetUpdate.updata("R" + loginResult.getGrade() + "=" + zjy + "=" + loginResult.getHp() + "=" + loginResult.getMp());
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("你获得经验#G");
            buffer.append(exp);
            assetUpdate.setMsg(buffer.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
}
