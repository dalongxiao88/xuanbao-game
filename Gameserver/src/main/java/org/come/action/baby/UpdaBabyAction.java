package org.come.action.baby;

import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Baby;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UpdaBabyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        try {
            Baby baby = (Baby)GsonUtil.getGsonUtil().getgson().fromJson(message, Baby.class);
            AllServiceUtil.getBabyService().updateBaby(baby);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
