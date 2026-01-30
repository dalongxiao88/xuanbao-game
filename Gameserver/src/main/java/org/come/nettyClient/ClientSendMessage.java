package org.come.nettyClient;

import io.netty.channel.ChannelHandlerContext;

public class ClientSendMessage
{
    static long x;
    static String FB;
    
    public static void toServer(String key, String sendmes) {
        if ((boolean)ClientMapAction.flagAction.get(key)) {
            ((ChannelHandlerContext)ClientMapAction.nettyAction.get(key)).writeAndFlush(sendmes + ClientSendMessage.FB);
        }
    }
    
    public static void getClientUntil(String ip, int port) throws Exception {
        ChannelHandlerContext SendMesToServer = (ChannelHandlerContext)ClientMapAction.nettyAction.get(ip + "|" + port);
        if (SendMesToServer == null) {
            GameClient client = new GameClient(ip, port);
            client.start();
        }
    }
    
    static {
        ClientSendMessage.x = System.currentTimeMillis();
        ClientSendMessage.FB = "\n";
    }
}
