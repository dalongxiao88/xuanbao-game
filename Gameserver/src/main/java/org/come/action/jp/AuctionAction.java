package org.come.action.jp;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Scene.JP.AuctionSceneInfo;
import come.tool.Scene.SceneUtil;
import come.tool.Scene.JP.JPScene;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AuctionAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        JPScene scene = (JPScene)SceneUtil.getScene(1015);
        if (message.startsWith("Find")) {
            AuctionSceneInfo auctionSceneInfo = (AuctionSceneInfo)scene.getAuctionSceneInfoMap().get(Integer.valueOf(Integer.parseInt(message.substring(4))));
            String mes = Agreement.getAgreement().auctionGoods(GsonUtil.getGsonUtil().getgson().toJson(auctionSceneInfo));
            SendMessage.sendMessageToSlef(ctx, mes);
            return;
        }
        if (scene != null) {
            scene.biddingProcessController(ctx, message);
        }
    }
}
