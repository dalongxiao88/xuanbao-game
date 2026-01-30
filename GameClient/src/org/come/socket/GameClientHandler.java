package org.come.socket;

import org.come.action.MapAction;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GameClientHandler extends SimpleChannelInboundHandler<String>
{
    private GameClient client;
    
    public GameClientHandler(GameClient client) {
        this.client = client;
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
    
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                SendMessageUntil.toServer("bibi");
            }
        }
        super.userEventTriggered(ctx, evt);
    }
    
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        try {
            if (msg == null) {
                return;
            }
            MapAction.mesControl.controlMessFromServer(msg, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StringBuffer key = new StringBuffer();
        key.append(this.client.getIp());
        key.append("|");
        key.append(this.client.getPort());
        MapAction.nettyAction.put(key.toString(), ctx);
        MapAction.flagAction.put(key.toString(), Boolean.valueOf(true));
        super.channelActive(ctx);
    }
    
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        StringBuffer key = new StringBuffer();
        key.append(this.client.getIp());
        key.append("|");
        key.append(this.client.getPort());
        System.out.println("断线");
        if ((boolean)MapAction.flagAction.get(key.toString())) {
            MapAction.flagAction.put(key.toString(), Boolean.valueOf(false));
            this.client.doConnect();
        }
        super.channelInactive(ctx);
    }
}
