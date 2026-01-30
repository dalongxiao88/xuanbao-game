package org.come.socket;

import org.come.action.MapAction;
import org.come.bean.Account;
import io.netty.channel.ChannelHandlerContext;

public class SendMessageUntil
{
    public static boolean CanDoConnectOrNo;
    public static ChannelHandlerContext SendMesToServer;
    static String FB;
    public static String gameServerKey;
    public static String accServerKey;
    public static String project;
    public static Account account;
    
    public static void toServer(String sendmes) {
        if (MapAction.flagAction.get(SendMessageUntil.gameServerKey) != null) {
            ((ChannelHandlerContext)MapAction.nettyAction.get(SendMessageUntil.gameServerKey)).writeAndFlush(sendmes + SendMessageUntil.FB);
        }
    }
    
    public static void loginToServer(String sendmes) {
        if ((boolean)MapAction.flagAction.get(SendMessageUntil.accServerKey)) {
            ((ChannelHandlerContext)MapAction.nettyAction.get(SendMessageUntil.accServerKey)).writeAndFlush(sendmes + SendMessageUntil.FB);
        }
    }
    
    public static void getClientUntil() throws Exception {
        if (SendMessageUntil.SendMesToServer == null) {
            GameClient client = new GameClient();
            client.start();
        }
    }
    
    public static void getClientUntil(String ip, int port) throws Exception {
        if (SendMessageUntil.SendMesToServer == null) {
            GameClient client = new GameClient(ip, port);
            client.start();
        }
    }
    
    static {
        SendMessageUntil.CanDoConnectOrNo = true;
        SendMessageUntil.FB = "\n";
        SendMessageUntil.gameServerKey = "";
        SendMessageUntil.accServerKey = "";
        SendMessageUntil.project = "";
        SendMessageUntil.account = null;
    }
}
