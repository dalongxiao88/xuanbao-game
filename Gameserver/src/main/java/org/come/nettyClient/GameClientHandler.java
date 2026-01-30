package org.come.nettyClient;

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
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                StringBuffer key = new StringBuffer();
                key.append(this.client.getIp());
                key.append("|");
                key.append(this.client.getPort());
                ClientSendMessage.toServer(key.toString(), "bibi");
            }
        }
        super.userEventTriggered(ctx, evt);
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        try {
            if (msg != null) {
                ((FromServerAction)ClientMapAction.serverAction.get(MessageProcessUntil.ServerMes)).controlMessFromServer(msg);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StringBuffer key = new StringBuffer();
        key.append(this.client.getIp());
        key.append("|");
        key.append(this.client.getPort());
        ClientMapAction.nettyAction.put(key.toString(), ctx);
        ClientMapAction.flagAction.put(key.toString(), Boolean.valueOf(true));
        super.channelActive(ctx);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        StringBuffer key = new StringBuffer();
        key.append(this.client.getIp());
        key.append("|");
        key.append(this.client.getPort());
        System.out.println("断线");
        if ((boolean)ClientMapAction.flagAction.get(key.toString())) {
            ClientMapAction.flagAction.put(key.toString(), Boolean.valueOf(false));
            this.client.doConnect();
        }
        super.channelInactive(ctx);
    }
}
