package org.come.extInterface;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.ReadTxtUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class Readtxt implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String txtName) {
        String TXTpath = GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\" + txtName;
        String result = ReadTxtUtil.readFile1(TXTpath);
        String msg = Agreement.getAgreement().readtxtAgreement(result);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
