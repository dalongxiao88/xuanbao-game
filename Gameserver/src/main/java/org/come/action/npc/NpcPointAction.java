package org.come.action.npc;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NPCDialog;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class NpcPointAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        NPCDialog npcDialog = new NPCDialog();
        String result = Agreement.getAgreement().npcAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcDialog));
        SendMessage.sendMessageToSlef(ctx, result);
    }
}
