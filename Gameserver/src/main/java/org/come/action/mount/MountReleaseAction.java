package org.come.action.mount;

import org.come.until.AllServiceUtil;
import org.come.action.summoning.SummonPetAction;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountReleaseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal mountId = new BigDecimal(message);
        if (!SummonPetAction.validateMount(ctx, mountId)) {
            return;
        }
        AllServiceUtil.getMountService().deleteMountsByMid(new BigDecimal(message));
        AllServiceUtil.getMountskillService().deleteMountskills(new BigDecimal(message));
    }
}
