package org.come.action.sys;

import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.UserTable;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class BindingMobileAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        UserTable table = (UserTable)GsonUtil.getGsonUtil().getgson().fromJson(message, UserTable.class);
        AllServiceUtil.getUserTableService().updateUser(table);
    }
}
