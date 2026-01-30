package org.come.action.lianghao;

import org.come.until.AllServiceUtil;
import org.come.entity.RoleTable;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LiangHaoAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.startsWith("LHTYPE")) {
            String[] result = message.split("=");
            if (result[1] != null) {
                if (loginResult.getLianghaotype().compareTo(Integer.valueOf(result[1])) == 0) {
                    return;
                }
                long jg = 200000000L;
                if (jg <= 0L) {
                    return;
                }
                AssetUpdate assetUpdate2 = new AssetUpdate();
                long gold = loginResult.getGold().longValue() - jg;
                if (gold < 0L) {
                    assetUpdate2.setMsg("切换失败，切换靓号需扣除" + String.valueOf(jg) + "，但是你没有足够的大话币");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else {
                    loginResult.setGold(new BigDecimal(gold));
                    MonitorUtil.getMoney().useD(jg);
                    assetUpdate2.setMsg("切换靓号样式扣除大话币：" + String.valueOf(jg));
                    assetUpdate2.setData("D=" + -jg);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    RoleTable roleTable = new RoleTable();
                    roleTable.setRole_id(loginResult.getRole_id());
                    roleTable.setLianghaotype(Integer.valueOf(result[1]));
                    AllServiceUtil.getRoleTableService().updateRoleLiangHaoType(roleTable);
                    loginResult.setLianghaotype(roleTable.getLianghaotype());
                }
            }
        }
    }
}
