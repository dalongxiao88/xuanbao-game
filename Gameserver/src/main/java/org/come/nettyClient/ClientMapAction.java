package org.come.nettyClient;

import java.util.HashMap;
import io.netty.channel.ChannelHandlerContext;
import java.util.Map;

public class ClientMapAction
{
    public static Map<String, FromServerAction> serverAction;
    public static Map<String, ChannelHandlerContext> nettyAction;
    public static Map<String, Boolean> flagAction;
    
    public ClientMapAction() {
        ClientMapAction.serverAction.put(MessageProcessUntil.ServerMes, new ServerToClientMesControl());
        ClientMapAction.serverAction.put("LOGINVERSION", new VersionControl());
    }
    
    static {
        ClientMapAction.serverAction = new HashMap<>();
        ClientMapAction.nettyAction = new HashMap<>();
        ClientMapAction.flagAction = new HashMap<>();
    }
}
