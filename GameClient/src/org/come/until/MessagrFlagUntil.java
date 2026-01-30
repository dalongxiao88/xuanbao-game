package org.come.until;

import java.util.HashMap;
import java.util.ArrayList;
import org.come.Frame.TestfriendlistJframe;
import org.come.socket.SendMessageUntil;
import com.tool.image.ImageMixDeal;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.entity.Friendtable;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JScrollPane;
import org.come.Frame.FriendChatMessageJframe;
import org.come.bean.ChatBean;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import com.tool.tcp.Sprite;
import com.tool.tcpimg.RichLabel;
import java.util.Map;
import java.util.List;

public class MessagrFlagUntil
{
    public static List<String> NotReads;
    public static Map<String, RichLabel> chatMap;
    public static String ImgFlagImg;
    public static String MOUSE1;
    public static String MOUSE2;
    public static String MOUSE3;
    public static String MOUSE4;
    public static String MOUSE5;
    public static String MOUSE6;
    public static String MOUSE7;
    public static String MOUSE8;
    public static String MOUSE9;
    public static String MOUSE10;
    public static String MOUSE11;
    public static String MOUSE12;
    public static String MOUSE13;
    private static Sprite mouse;
    
    public static void setMouse(String mouseImg) {
        MessagrFlagUntil.ImgFlagImg = mouseImg;
        MessagrFlagUntil.mouse = null;
    }
    
    public static Sprite getMouse() {
        if (MessagrFlagUntil.mouse == null) {
            MessagrFlagUntil.mouse = SpriteFactory.Prepare(GetTcpPath.getMouseTcp(MessagrFlagUntil.ImgFlagImg));
        }
        return MessagrFlagUntil.mouse;
    }
    
    public static RichLabel getRichLabel(String rolename) {
        RichLabel richLabel = (RichLabel)MessagrFlagUntil.chatMap.get(rolename);
        if (richLabel == null) {
            richLabel = new RichLabel();
            richLabel.setSize(280, 16);
            MessagrFlagUntil.chatMap.put(rolename, richLabel);
        }
        return richLabel;
    }
    
    public static void ReceiveMessage(ChatBean bean, String rolename) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#r#R ");
        buffer.append(bean.getRolename());
        buffer.append(" ");
        buffer.append(timeStamp2Date(bean.getTime(), "yyyy-MM-dd HH:mm:ss"));
        buffer.append("#r#W");
        buffer.append(bean.getMessage());
        RichLabel richLabel = getRichLabel(rolename);
        richLabel.addText(buffer.toString());
        richLabel.setPreferredSize(richLabel.computeSize(richLabel.getWidth()));
        if (FormsManagement.getframe(56).isVisible()) {
            JScrollPane js = FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().getJs();
            js.getViewport().setViewSize(richLabel.getPreferredSize());
            js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
        }
        else if (!MessagrFlagUntil.NotReads.contains(bean.getRolename())) {
            MessagrFlagUntil.NotReads.add(bean.getRolename());
        }
    }
    
    public static String timeStamp2Date(long seconds, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(seconds));
    }
    
    public static void ShowMessage(String rolename) {
        for (int i = 0; i < UserMessUntil.getFriendtables().size(); ++i) {
            Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
            if (friend.getRolename().equals(rolename)) {
                FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, getRichLabel(friend.getRolename()));
                FormsManagement.showForm(56);
                return;
            }
        }
        Friendtable friend2 = new Friendtable();
        friend2.setRolename(rolename);
        friend2.setRace_name("非好友");
        friend2.setGrade(new BigDecimal(0));
        friend2.setRole_id(new BigDecimal(0));
        FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend2, getRichLabel(rolename));
        FormsManagement.showForm(56);
    }
    
    public static void ReceiveFriend() {
        if (MessagrFlagUntil.NotReads.size() != 0) {
            ShowMessage((String)MessagrFlagUntil.NotReads.get(MessagrFlagUntil.NotReads.size() - 1));
            MessagrFlagUntil.NotReads.remove(MessagrFlagUntil.NotReads.size() - 1);
        }
        else if (!FormsManagement.getframe(4).isVisible()) {
            String mes = Agreement.getAgreement().friendAgreement(ImageMixDeal.userimg.getRoleShow().getRole_id().toString());
            SendMessageUntil.toServer(mes);
            JTreeData.ShowFriendMsg(TestfriendlistJframe.getTestfriendlistJframe().getJflist().getTop(), TestfriendlistJframe.getTestfriendlistJframe().getJflist().getjTree());
            FormsManagement.showForm(4);
        }
        else {
            FormsManagement.HideForm(4);
        }
    }
    
    static {
        MessagrFlagUntil.NotReads = new ArrayList<>();
        MessagrFlagUntil.chatMap = new HashMap<>();
        MessagrFlagUntil.ImgFlagImg = "鼠标";
        MessagrFlagUntil.MOUSE1 = "鼠标";
        MessagrFlagUntil.MOUSE2 = "组队";
        MessagrFlagUntil.MOUSE3 = "给予";
        MessagrFlagUntil.MOUSE4 = "好友";
        MessagrFlagUntil.MOUSE5 = "交易";
        MessagrFlagUntil.MOUSE6 = "切磋";
        MessagrFlagUntil.MOUSE7 = "捕捉";
        MessagrFlagUntil.MOUSE8 = "施法状态";
        MessagrFlagUntil.MOUSE9 = "药品使用";
        MessagrFlagUntil.MOUSE10 = "加锁";
        MessagrFlagUntil.MOUSE11 = "解锁";
        MessagrFlagUntil.MOUSE12 = "手指";
        MessagrFlagUntil.MOUSE13 = "NCP";
    }
}
