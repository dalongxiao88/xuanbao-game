package org.come.action.pack;

import java.util.Iterator;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.protocol.ParamTool;
import org.come.tool.WriteOut;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import org.come.bean.GoodsResultArrBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PackChangeAction implements IAction
{
    public static int size;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        GoodsResultArrBean goodsResultArrBean = (GoodsResultArrBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodsResultArrBean.class);
        List<Goodstable> goods = goodsResultArrBean.getList();
        if (goods == null) {
            return;
        }
        for (Goodstable good : goods) {
            if (good != null) {
                if (loginResult.getRole_id().compareTo(good.getRole_id()) != 0) {
                    WriteOut.addtxt("使用物品角色ID改变:" + loginResult.getRole_id() + ":" + message, 9999L);
                    ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(loginResult.getUserName()), loginResult.getUserName());
                    return;
                }
                String value = AllServiceUtil.getGoodsTableService().updateGoodsNum(good, goodsResultArrBean.getI());
                if (value != null) {
                    WriteOut.addtxt("物品突变协议头数据:" + message, 9999L);
                    ++PackChangeAction.size;
                    if (PackChangeAction.size % 5 == 0) {
                        ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(loginResult.getUserName()), loginResult.getUserName());
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().serverstopAgreement());
                    }
                    return;
                }
                else if (good.getType() != 750L && good.getType() != 0L && good.getType() != 50L && good.getType() != 49L && good.getType() != 2012L && good.getType() != 1L) {
                    AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(9));
                }
                else {
                    continue;
                }
            }
        }
    }
}
