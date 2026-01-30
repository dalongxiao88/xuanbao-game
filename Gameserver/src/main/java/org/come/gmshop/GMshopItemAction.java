package org.come.gmshop;

import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import com.gl.service.PlayerService;
import org.come.action.IAction;

public class GMshopItemAction implements IAction
{
    PlayerService playerService;
    static String CHECKTS1;
    static String CHECKTS2;
    
    @Override
    public synchronized void action(ChannelHandlerContext ctx, String message) {
    }
    
    static {
        GMshopItemAction.CHECKTS1 = Agreement.getAgreement().PromptAgreement("获得");
        GMshopItemAction.CHECKTS2 = Agreement.getAgreement().PromptAgreement("特权等级不够请联系群主升级！");
    }
}
