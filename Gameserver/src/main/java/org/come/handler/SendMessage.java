package org.come.handler;

import org.come.bean.LoginResult;
import come.tool.newGang.GangDomain;
import come.tool.newGang.GangUtil;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.Map;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;

public class SendMessage
{
    static String FB;
    
    public static void sendMessageToSlef(ChannelHandlerContext ctx, String msg) {
        if (ctx == null) {
            return;
        }
        ctx.writeAndFlush(msg);
    }
    
    public static void sendMessageByRoleName(String roleName, String msg) {
        sendMessageToSlef((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName), msg);
    }
    
    public static void sendMessageByUserName(String userName, String msg) {
        sendMessageToSlef((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(userName), msg);
    }
    
    public static void sendMessageToAllRoles(String msg) {
        for (Map.Entry<String, ChannelHandlerContext> entrys : GameServer.getRoleNameMap().entrySet()) {
            sendMessageToSlef((ChannelHandlerContext)entrys.getValue(), msg);
        }
    }
    
    public static void sendMessageToMapRoles(Long mapID, String msg) {
        for (Map.Entry<String, ChannelHandlerContext> entrys : ((ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(mapID)).entrySet()) {
            sendMessageToSlef((ChannelHandlerContext)entrys.getValue(), msg);
        }
    }
    
    public static void sendMessageToMapRoles(ChannelHandlerContext ctx, Long mapID, String msg) {
        for (Map.Entry<String, ChannelHandlerContext> entrys : ((ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(mapID)).entrySet()) {
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)entrys.getValue();
            if (ctx != ctx2) {
                sendMessageToSlef(ctx2, msg);
            }
        }
    }
    
    public static void sendMessageToGangRoles(BigDecimal gangID, String msg) {
        GangDomain gangDomain = GangUtil.getGangDomain(gangID);
        if (gangDomain != null) {
            for (Map.Entry<BigDecimal, ChannelHandlerContext> entrys : gangDomain.getRoleMap().entrySet()) {
                sendMessageToSlef(entrys.getValue(), msg);
            }
        }
    }
    
    public static void sendMessageToGangMap(BigDecimal gangID, long mapID, String msg) {
        GangDomain gangDomain = GangUtil.getGangDomain(gangID);
        if (gangDomain != null) {
            for (Map.Entry<BigDecimal, ChannelHandlerContext> entrys : gangDomain.getRoleMap().entrySet()) {
                ChannelHandlerContext ctx = entrys.getValue();
                LoginResult roleInfo = GameServer.getAllLoginRole().get(ctx);
                if (roleInfo != null && roleInfo.getMapid() == mapID) {
                    sendMessageToSlef(ctx, msg);
                }
            }
        }
    }
    
    static {
        SendMessage.FB = "\n";
    }
    public static void sendMessageToAllRolesUpdateText(String msg) {
        Iterator<Map.Entry<String, ChannelHandlerContext>> entries = GameServer.getRoleNameMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String,ChannelHandlerContext> entrys = entries.next();
            sendMessageToSlef(entrys.getValue(), msg);
        }
    }
}
