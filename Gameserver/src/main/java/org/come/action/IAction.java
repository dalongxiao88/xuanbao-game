package org.come.action;

import io.netty.channel.ChannelHandlerContext;

/**
 * 通用协议动作接口。
 */
public interface IAction
{
    void action(ChannelHandlerContext ctx, String message);
}
