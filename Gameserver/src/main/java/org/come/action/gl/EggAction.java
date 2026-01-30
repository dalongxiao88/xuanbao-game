package org.come.action.gl;

import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import org.come.action.buy.AddGoodUtil;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import com.gl.util.EggUtil;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class EggAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BigDecimal roleid = loginResult.getRole_id();
        List<Goodstable> goodsList = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(roleid, EggUtil.EGG_ID);
        if (goodsList.size() > 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经有一个了，带着它去战斗先把它孵化出来吧"));
            return;
        }
        if (RolePool.getRoleData(roleid).isGoodFull()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的物品栏已经满了，请留出至少一个空位"));
            return;
        }
        Goodstable goodstable = GameServer.getGood(EggUtil.EGG_ID);
        goodstable.setRole_id(roleid);
        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
        AddGoodUtil.addGood(ctx, goodstable);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你获得了一个元气蛋，快带着它去参加战斗吸收天地精华吧"));
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(0));
    }
}
