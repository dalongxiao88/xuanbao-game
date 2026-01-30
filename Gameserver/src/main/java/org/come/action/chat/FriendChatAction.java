package org.come.action.chat;

import java.text.DateFormat;
import org.come.entity.RoleTable;
import java.text.SimpleDateFormat;
import org.come.entity.Wechatrecord;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.util.Date;
import org.come.until.SpiritChat;
import org.come.until.GsonUtil;
import org.come.servlet.UserControlServlet;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.ChatBean;
import org.come.action.IAction;

public class FriendChatAction implements IAction
{
    private static ChatBean chatBean;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (UserControlServlet.isNoTalk(ctx)) {
            return;
        }
        ChatBean chatBean = (ChatBean)GsonUtil.getGsonUtil().getgson().fromJson(message, ChatBean.class);
        if (chatBean.getFriendName().equals("大话精灵")) {
            ChatBean chatBean2 = new ChatBean();
            chatBean2.setFriendName(chatBean.getRolename());
            chatBean2.setRolename(chatBean.getFriendName());
            chatBean2.setMessage(SpiritChat.getAnswering(chatBean.getMessage()));
            chatBean2.setTime(new Date().getTime());
            String msg = Agreement.getAgreement().friendchatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean2));
            if (GameServer.getRoleNameMap().get(chatBean2.getFriendName()) != null && chatBean2.getFriendName() != null) {
                SendMessage.sendMessageByRoleName(chatBean2.getFriendName(), msg);
            }
        }
        else {
            String msg2 = Agreement.getAgreement().friendchatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
            RoleTable friend = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(chatBean.getFriendName());
            LoginResult friend2 = null;
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(chatBean.getFriendName());
            if (ctx2 != null) {
                friend2 = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
            }
            else {
                friend = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(chatBean.getFriendName());
            }
            if ((int)roleInfo.getGrade() <= 102 && roleInfo.getPaysum().intValue() < 100) {
                SendMessage.sendMessageToSlef(ctx, ChatAction.MSG);
                return;
            }
            Wechatrecord wechatrecord = new Wechatrecord();
            wechatrecord.setChatGetid((friend2 != null) ? friend2.getRole_id() : friend.getRole_id());
            wechatrecord.setChatMes(chatBean.getMessage());
            wechatrecord.setChatSendid(roleInfo.getRole_id());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdayTime = dateFormat.format(new Date());
            wechatrecord.setTime(nowdayTime);
            AllServiceUtil.getWechatrecordService().insert(wechatrecord);
            if (GameServer.getRoleNameMap().get(chatBean.getFriendName()) != null && chatBean.getFriendName() != null) {
                SendMessage.sendMessageByRoleName(chatBean.getFriendName(), msg2);
            }
        }
    }
    
    public static void useServerFriendToRoleForClient(ChatBean chatBean) {
        String msg = Agreement.getAgreement().friendchatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
        SendMessage.sendMessageByRoleName(chatBean.getFriendName(), msg);
    }
    
    public static void createChatBeanForServer(String mes, String rolename) {
        FriendChatAction.chatBean.setRolename("系统");
        FriendChatAction.chatBean.setFriendName(rolename);
        FriendChatAction.chatBean.setMessage(mes);
        FriendChatAction.chatBean.setTime(System.currentTimeMillis());
        useServerFriendToRoleForClient(FriendChatAction.chatBean);
    }
    
    static {
        FriendChatAction.chatBean = new ChatBean();
    }
}
