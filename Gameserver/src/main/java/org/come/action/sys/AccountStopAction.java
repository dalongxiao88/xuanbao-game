package org.come.action.sys;

import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import org.come.entity.UserTable;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AccountStopAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        GameServer.userDown(ctx);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().serverstopAgreement());
        UserTable table = new UserTable();
        table.setUsername(message);
        table.setActivity(Short.valueOf((short)1));
        AllServiceUtil.getUserTableService().updateUser(table);
        AllServiceUtil.getRecordService().insert(new Record(5, message));
    }
}
