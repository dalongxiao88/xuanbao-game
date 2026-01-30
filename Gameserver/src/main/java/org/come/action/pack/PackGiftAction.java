package org.come.action.pack;

import org.come.protocol.ParamTool;
import org.come.until.GsonUtil;
import org.come.bean.PackGiftBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PackGiftAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        PackGiftBean packGiftBean = (PackGiftBean)GsonUtil.getGsonUtil().getgson().fromJson(message, PackGiftBean.class);
        ((IAction)ParamTool.ACTION_MAP.get("user")).action(ctx, packGiftBean.getGoodstable().getRgid().toString());
    }
}
