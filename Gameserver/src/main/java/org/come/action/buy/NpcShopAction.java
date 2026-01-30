package org.come.action.buy;

import come.tool.Scene.DNTG.DNTGRole;
import come.tool.Scene.Scene;
import org.come.bean.PathPoint;
import come.tool.Scene.ZZS.ZZSRole;
import come.tool.Scene.ZZS.ZZSScene;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.LTS.LTSUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Scene.SceneUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class NpcShopAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        if (message.equals("1106")) {
            long meney = 0L;
            ZZSScene zzsScene = SceneUtil.getZZS(roleInfo);
            if (zzsScene != null) {
                ZZSRole zzsRole = zzsScene.getRole(roleInfo);
                if (zzsRole != null) {
                    meney = (long)zzsRole.getJf();
                }
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().BuyNPCGoodsAgreement(message + "|" + meney));
        }
        else if (message.equals("515")) {
            long meney = 0L;
            PathPoint point = LTSUtil.getLtsUtil().getJF(roleInfo.getRole_id());
            if (point != null) {
                meney = (long)point.getY();
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().BuyNPCGoodsAgreement(message + "|" + meney));
        }
        else if (message.equals("605")) {
            long meney = 0L;
            Scene scene = SceneUtil.getScene(1011);
            if (scene != null) {
                DNTGScene dntgScene = (DNTGScene)scene;
                DNTGRole role = dntgScene.getRole(roleInfo.getRole_id());
                if (role != null) {
                    meney = (long)role.getUseDNJF();
                }
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().BuyNPCGoodsAgreement(message + "|" + meney));
        }
    }
}
