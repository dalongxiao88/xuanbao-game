package org.come.action.summoning;

import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetReleaseFPAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal summoningId = new BigDecimal(message);
        if (!SummonPetAction.validateSummoning(ctx, summoningId)) {
            return;
        }
        AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySid(new BigDecimal(message));
    }
}
