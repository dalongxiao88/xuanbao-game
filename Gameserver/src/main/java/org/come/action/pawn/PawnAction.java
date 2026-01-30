package org.come.action.pawn;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.bean.PawnGoodsMessBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PawnAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        PawnGoodsMessBean pawnGoods = (PawnGoodsMessBean)GsonUtil.getGsonUtil().getgson().fromJson(message, PawnGoodsMessBean.class);
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(pawnGoods.getRgid());
        if (goodstable == null) {
            return;
        }
        int sum1 = (int)goodstable.getUsetime();
        int sum2 = pawnGoods.getNumber();
        if (sum1 <= 0 || sum2 <= 0 || sum2 > sum1) {
            return;
        }
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(sum2), Integer.valueOf(10));
        int userTime = sum1 - sum2;
        if (!EquipTool.canSuper(goodstable.getType())) {
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, null, null, Integer.valueOf(2));
        }
        else {
            List<Goodstable> lists = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsIDAndState(goodstable.getRole_id(), goodstable.getGoodsid(), 2);
            if (lists.size() != 0) {
                goodstable.setUsetime(Integer.valueOf(userTime));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
                goodstable = (Goodstable)lists.get(0);
                goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() + sum2));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            }
            else if (userTime > 0) {
                goodstable.setUsetime(Integer.valueOf(userTime));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
                goodstable = goodstable.clone();
                goodstable.setStatus(Integer.valueOf(2));
                goodstable.setUsetime(Integer.valueOf(sum2));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
            }
            else {
                goodstable.setUsetime(Integer.valueOf(sum2));
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, null, null, Integer.valueOf(2));
            }
        }
        String pawn = Agreement.getAgreement().pawnAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
        SendMessage.sendMessageToSlef(ctx, pawn);
    }
}
