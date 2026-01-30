package org.come.action.pawn;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RetrieveAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(message));
        if (goodstable == null) {
            return;
        }
        if (loginResult.getRole_id().compareTo(goodstable.getRole_id()) != 0) {
            return;
        }
        if ((int)goodstable.getUsetime() <= 0 || (int)goodstable.getStatus() != 2) {
            return;
        }
        int sum = (int)goodstable.getUsetime();
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, goodstable.getUsetime(), Integer.valueOf(11));
        if (EquipTool.canSuper(goodstable.getType())) {
            List<Goodstable> lists = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsIDAndState(goodstable.getRole_id(), goodstable.getGoodsid(), 0);
            if (lists.size() != 0) {
                AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(goodstable.getRgid());
                long v = (long)goodstable.getQuality();
                goodstable = (Goodstable)lists.get(0);
                if (v % 2L == 1L) {
                    goodstable.setQuality(Long.valueOf(v));
                }
                goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() + sum));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            }
            else {
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, null, null, Integer.valueOf(0));
            }
        }
        else {
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, null, null, Integer.valueOf(0));
        }
        String pawn = Agreement.getAgreement().pawnAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
        SendMessage.sendMessageToSlef(ctx, pawn);
    }
}
