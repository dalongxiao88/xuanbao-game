package org.come.action.Fly;

import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FlyReleaseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        AllServiceUtil.getFlyService().deleteFlysByMid(new BigDecimal(message));
        AllServiceUtil.getMountskillService().deleteMountskills(new BigDecimal(message));
    }
}
