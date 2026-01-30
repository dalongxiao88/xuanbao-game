package org.come.action;

import io.netty.channel.ChannelHandlerContext;

public interface IAction
{
    void action(ChannelHandlerContext p0, String p1);
}
