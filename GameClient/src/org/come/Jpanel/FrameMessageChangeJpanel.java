package org.come.Jpanel;

import org.come.Frame.ChatHistoryJframe;
import org.come.bean.LoginResult;
import com.tool.tcpimg.UIUtils;
import org.come.until.GsonUtil;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import com.tool.tcpimg.InputBean;
import java.math.BigDecimal;
import java.awt.Graphics;
import java.util.HashMap;
import java.awt.Dimension;
import org.come.until.ScrenceUntil;
import com.updateNew.MyIsif;
import com.tool.tcpimg.ChatBox;
import java.util.Map;
import javax.swing.ImageIcon;
import com.tool.btn.SmallIconBtn;
import javax.swing.JPanel;

public class FrameMessageChangeJpanel extends JPanel
{
    public static boolean styles;
    private SmallIconBtn lableft;
    private SmallIconBtn labbottom;
    private SmallIconBtn labtop;
    private SmallIconBtn clear;
    private SmallIconBtn stop;
    private SmallIconBtn lableft2;
    private SmallIconBtn labbottom2;
    private SmallIconBtn labtop2;
    private SmallIconBtn clear2;
    private SmallIconBtn stop2;
    private SmallIconBtn history;
    private SmallIconBtn chatSwitchBtn;
    final ImageIcon imageIcon;
    private static Map<String, String> chatTypes;
    public static ChatBox chatbox;
    public static ChatBox chatbox1;
    public static ChatBox chatbox2;
    private static ImageIcon icon;
    private ImageIcon icon1;
    private static boolean[] chatSwitch;
    public static int i;
    
    public FrameMessageChangeJpanel() {
        this.imageIcon = new ImageIcon("img/123_副本.png");
        this.icon1 = new ImageIcon("img/xy2uiimg/chatjpanel_tmt.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X, ScrenceUntil.ChatFram_y));
            this.setLayout(null);
            (this.lableft = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.lableft);
            (this.labbottom = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下1", null)).setBounds(17, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labbottom);
            (this.labtop = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上1", null)).setBounds(36, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labtop);
            (this.clear = new SmallIconBtn("inkImg/button/147.png", 1, 2, "清屏1", null)).setBounds(55, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.clear);
            (this.lableft2 = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.lableft2);
            (this.labbottom2 = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下2", null)).setBounds(17, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labbottom2);
            (this.labtop2 = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上2", null)).setBounds(36, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labtop2);
            (this.clear2 = new SmallIconBtn("inkImg/button/147.png", 1, 2, "清屏2", null)).setBounds(55, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.clear2);
            (this.stop2 = new SmallIconBtn("inkImg/button/56.png", 1, 8, "停止", null)).setBounds(74, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.stop2);
            (this.history = new SmallIconBtn("inkImg/button/149.png", 1, 0, "记录", null)).setBounds(93, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.history);
            (this.chatSwitchBtn = new SmallIconBtn("inkImg/button/B38.png", 1, 0, "开关", null)).setBounds(112, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.chatSwitchBtn);
        }
        else {
            this.setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X, ScrenceUntil.ChatFram_y));
            this.setLayout(null);
            (this.lableft = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.lableft);
            (this.labbottom = new SmallIconBtn("inkImg/hongmu/25.png", 1, 1, "向下1", null)).setBounds(21, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labbottom);
            (this.labtop = new SmallIconBtn("inkImg/hongmu/24.png", 1, 0, "向上1", null)).setBounds(40, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labtop);
            (this.clear = new SmallIconBtn("inkImg/hongmu/26.png", 1, 2, "清屏1", null)).setBounds(59, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.clear);
            (this.lableft2 = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.lableft2);
            (this.labbottom2 = new SmallIconBtn("inkImg/hongmu/25.png", 1, 1, "向下2", null)).setBounds(21, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labbottom2);
            (this.labtop2 = new SmallIconBtn("inkImg/hongmu/24.png", 1, 0, "向上2", null)).setBounds(40, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labtop2);
            (this.clear2 = new SmallIconBtn("inkImg/hongmu/qh1.png", 1, 2, "清屏2", null)).setBounds(59, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.clear2);
            (this.stop2 = new SmallIconBtn("inkImg/hongmu/a8.png", 1, 8, "停止", null)).setBounds(78, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.stop2);
            (this.history = new SmallIconBtn("inkImg/hongmu/27.png", 1, 0, "记录", null)).setBounds(97, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.history);
            (this.chatSwitchBtn = new SmallIconBtn("inkImg/hongmu/26.png", 1, 0, "开关", null)).setBounds(116, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.chatSwitchBtn);
        }
        (FrameMessageChangeJpanel.chatTypes = new HashMap<>()).put("#Q", "当前");
        FrameMessageChangeJpanel.chatTypes.put("#J", "世界");
        FrameMessageChangeJpanel.chatTypes.put("#D", "队伍");
        FrameMessageChangeJpanel.chatTypes.put("#P", "帮派");
        FrameMessageChangeJpanel.chatTypes.put("#Z", "战斗");
        FrameMessageChangeJpanel.chatTypes.put("#T", "系统");
        FrameMessageChangeJpanel.chatTypes.put("#X", "信息");
        FrameMessageChangeJpanel.chatTypes.put("#S", "传音");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(FrameMessageChangeJpanel.icon.getImage(), 0, 0, ScrenceUntil.ChatFram_X - 3, ScrenceUntil.ChatFram_y, this);
        g.drawImage(this.icon1.getImage(), 0, ScrenceUntil.ChatFram_y / 10 * 5 - 20, ScrenceUntil.ChatFram_X - 3, 20, this);
        g.drawImage(this.icon1.getImage(), 0, ScrenceUntil.ChatFram_y - 20, ScrenceUntil.ChatFram_X - 3, 20, this);
        Graphics g2 = g.create(5, 0, ScrenceUntil.ChatFram_X - 5, ScrenceUntil.ChatFram_y / 10 * 5 - 20);
        g.translate(3, 0);
        FrameMessageChangeJpanel.chatbox1.paint(g2);
        g2.dispose();
        Graphics g3 = g.create(5, ScrenceUntil.ChatFram_y / 10 * 5, ScrenceUntil.ChatFram_X - 5, ScrenceUntil.ChatFram_y / 10 * 5 - 20);
        FrameMessageChangeJpanel.chatbox2.paint(g3);
        g3.dispose();
        if (FrameMessageChangeJpanel.styles) {
            FrameMessageChangeJpanel.styles = false;
            this.styleFG();
        }
    }
    
    public static boolean setChatSwitch(int index) {
        return FrameMessageChangeJpanel.chatSwitch[index] = !FrameMessageChangeJpanel.chatSwitch[index];
    }
    
    public static boolean getChatSwitch(int index) {
        return FrameMessageChangeJpanel.chatSwitch[index];
    }
    
    public static void setChatSwitch(int index, boolean is) {
        FrameMessageChangeJpanel.chatSwitch[index] = is;
    }
    
    public static boolean[] getChatSwitch() {
        return FrameMessageChangeJpanel.chatSwitch;
    }
    
    public static void addtext(int type, String text, BigDecimal id, String name) {
        int switchType = type;
        switch (switchType) {
            case 4: {
                switchType = -1;
                break;
            }
            case 7:
            case 8:
            case 9:
            case 10: {
                switchType = 5;
                break;
            }
            case 11: {
                switchType = 4;
                break;
            }
        }
        StringBuffer buffer = new StringBuffer();
        if (type == 4) {
            if (id != null) {
                InputBean inputBean = new InputBean();
                inputBean.setType(1);
                inputBean.setId(id);
                buffer.append("#S#G");
                LoginResult login = RoleData.getRoleData().getLoginResult();
                if (StringUtils.isNotBlank(login.getLiangHao())) {
                    inputBean.setGoodNum(login.getLiangHao());
                    inputBean.setGoodNumType(String.valueOf(login.getLianghaotype()));
                }
                if (StringUtils.isNotBlank(inputBean.getGoodNum())) {
                    inputBean.setName("[" + name);
                }
                else {
                    inputBean.setName("[" + name + "]");
                }
                inputBean.setColor("cFFFF00");
                buffer.append("#V");
                buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                buffer.append("#L#Y");
            }
            buffer.append(text);
            GameJpanel.getGameJpanel().addNotice(buffer.toString());
        }
        else {
            if (type == 7) {
                GameJpanel.getGameJpanel().addBox("#T" + text, 8, null);
            }
            if (type == 0) {
                buffer.append("#Q");
            }
            else if (type == 1) {
                buffer.append("#D");
            }
            else if (type == 2) {
                buffer.append("#P");
            }
            else if (type == 3) {
                buffer.append("#J");
            }
            else if (type == 5 || type == 7) {
                buffer.append("#T");
            }
            else if (type == 6) {
                buffer.append("#X");
            }
            else if (type == 11) {
                buffer.append("#Z");
            }
            if (type == 5 || type == 7) {
                buffer.append("#Y");
                buffer.append(text);
            }
            else if (type == 6) {
                buffer.append(text);
            }
            else {
                if (id != null) {
                    InputBean inputBean = new InputBean();
                    inputBean.setType(1);
                    inputBean.setId(id);
                    LoginResult login = RoleData.getRoleData().getLoginResult();
                    if (StringUtils.isNotBlank(login.getLiangHao())) {
                        inputBean.setGoodNum(login.getLiangHao());
                        inputBean.setGoodNumType(String.valueOf(login.getLianghaotype()));
                    }
                    if (StringUtils.isNotBlank(inputBean.getGoodNum())) {
                        inputBean.setName("[" + name);
                    }
                    else {
                        inputBean.setName("[" + name + "]");
                    }
                    inputBean.setColor("G");
                    buffer.append("#V");
                    buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                    buffer.append("#L");
                }
                else if (name != null) {
                    buffer.append("[" + name + "]");
                }
                buffer.append(text);
            }
        }
        if (switchType == -1 || FrameMessageChangeJpanel.chatSwitch[switchType]) {
            if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
                FrameMessageChangeJpanel.chatbox.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
            else if (type == 6 || type == 4 || type == 5) {
                FrameMessageChangeJpanel.chatbox1.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
            else {
                FrameMessageChangeJpanel.chatbox2.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
        }
        addChatHistory(buffer.toString());
    }
    
    public static void addtext(int type, String text, BigDecimal id, String name, String goodNum, Integer goodNumType) {
        int switchType = type;
        switch (switchType) {
            case 4: {
                switchType = -1;
                break;
            }
            case 7:
            case 8:
            case 9:
            case 10: {
                switchType = 5;
                break;
            }
            case 11: {
                switchType = 4;
                break;
            }
        }
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        if (type == 4) {
            if (id != null) {
                InputBean inputBean = new InputBean();
                inputBean.setType(1);
                inputBean.setId(id);
                buffer.append("#S#G");
                if (StringUtils.isNotBlank(goodNum)) {
                    inputBean.setGoodNum(goodNum);
                    inputBean.setGoodNumType(String.valueOf(goodNumType));
                }
                if (StringUtils.isNotBlank(inputBean.getGoodNum())) {
                    inputBean.setName("[" + name);
                }
                else {
                    inputBean.setName("[" + name + "]");
                }
                inputBean.setColor("cFFFF00");
                buffer.append("#V");
                buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                buffer.append("#L#Y");
                inputBean.setColor("cFFFF00");
                inputBean.setName("");
                buffer2.append("#V");
                buffer2.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                buffer2.append("#L#Y");
            }
            buffer.append(text);
            buffer2.append(text);
            GameJpanel.getGameJpanel().addNotice(buffer2.toString().replace("#S", ""));
        }
        else {
            if (type == 7) {
                GameJpanel.getGameJpanel().addBox("#T" + text, 8, null);
            }
            if (type == 0) {
                buffer.append("#Q");
            }
            else if (type == 1) {
                buffer.append("#D");
            }
            else if (type == 2) {
                buffer.append("#P");
            }
            else if (type == 3) {
                buffer.append("#J");
            }
            else if (type == 5 || type == 7) {
                buffer.append("#T");
            }
            else if (type == 6) {
                buffer.append("#X");
            }
            if (type == 5 || type == 7) {
                buffer.append("#Y");
                buffer.append(text);
            }
            else if (type == 6) {
                buffer.append(text);
            }
            else {
                if (id != null) {
                    InputBean inputBean = new InputBean();
                    inputBean.setType(1);
                    inputBean.setId(id);
                    LoginResult login = RoleData.getRoleData().getLoginResult();
                    if (StringUtils.isNotBlank(goodNum)) {
                        inputBean.setGoodNum(goodNum);
                        inputBean.setGoodNumType(String.valueOf(goodNumType));
                    }
                    if (StringUtils.isNotBlank(inputBean.getGoodNum())) {
                        inputBean.setName("[" + name);
                    }
                    else {
                        inputBean.setName("[" + name + "]");
                    }
                    inputBean.setColor("G");
                    buffer.append("#V");
                    buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                    buffer.append("#L");
                }
                else if (name != null) {
                    buffer.append("[" + name + "]");
                }
                buffer.append(text);
            }
        }
        if (switchType == -1 || FrameMessageChangeJpanel.chatSwitch[switchType]) {
            if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
                FrameMessageChangeJpanel.chatbox.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
            else if (type == 6 || type == 4 || type == 5) {
                FrameMessageChangeJpanel.chatbox1.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
            else {
                FrameMessageChangeJpanel.chatbox2.addtext(buffer.toString(), ScrenceUntil.ChatFram_X - 20, UIUtils.TEXT_FONT1);
            }
        }
        addChatHistory(buffer.toString());
    }
    
    private static synchronized void addChatHistory(String msg) {
        String chatType = msg.substring(0, 2);
        ChatHistoryJframe.getChatHistoryJframe().getChatHistoryJpanel().addChatHistory((String)FrameMessageChangeJpanel.chatTypes.get(chatType), msg);
    }
    
    public void BtnChange() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.lableft.setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.labbottom.setBounds(21, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.labtop.setBounds(40, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.clear.setBounds(59, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            if (!FrameMessageChangeJpanel.chatbox1.isDisplay()) {
                FrameMessageChangeJpanel.chatbox1.setW(ScrenceUntil.ChatFram_X - 10);
                FrameMessageChangeJpanel.chatbox1.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
            }
            this.lableft2.setBounds(2, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.labbottom2.setBounds(21, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.labtop2.setBounds(40, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.clear2.setBounds(59, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.stop2.setBounds(78, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.history.setBounds(97, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.chatSwitchBtn.setBounds(116, ScrenceUntil.ChatFram_y - 20, 20, 20);
            if (!FrameMessageChangeJpanel.chatbox2.isDisplay()) {
                FrameMessageChangeJpanel.chatbox2.setW(ScrenceUntil.ChatFram_X - 10);
                FrameMessageChangeJpanel.chatbox2.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
            }
        }
        else {
            this.lableft.setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.labbottom.setBounds(23, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.labtop.setBounds(44, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.clear.setBounds(65, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            if (!FrameMessageChangeJpanel.chatbox1.isDisplay()) {
                FrameMessageChangeJpanel.chatbox1.setW(ScrenceUntil.ChatFram_X - 10);
                FrameMessageChangeJpanel.chatbox1.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
            }
            this.lableft2.setBounds(2, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.labbottom2.setBounds(23, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.labtop2.setBounds(44, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.clear2.setBounds(65, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.stop2.setBounds(86, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.history.setBounds(107, ScrenceUntil.ChatFram_y - 21, 20, 20);
            this.chatSwitchBtn.setBounds(128, ScrenceUntil.ChatFram_y - 21, 20, 20);
            if (!FrameMessageChangeJpanel.chatbox2.isDisplay()) {
                FrameMessageChangeJpanel.chatbox2.setW(ScrenceUntil.ChatFram_X - 10);
                FrameMessageChangeJpanel.chatbox2.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
            }
        }
    }
    
    public SmallIconBtn getLableft() {
        return this.lableft;
    }
    
    public void setLableft(SmallIconBtn lableft) {
        this.lableft = lableft;
    }
    
    public SmallIconBtn getLabbottom() {
        return this.labbottom;
    }
    
    public void setLabbottom(SmallIconBtn labbottom) {
        this.labbottom = labbottom;
    }
    
    public SmallIconBtn getLabtop() {
        return this.labtop;
    }
    
    public void setLabtop(SmallIconBtn labtop) {
        this.labtop = labtop;
    }
    
    public SmallIconBtn getLableft2() {
        return this.lableft2;
    }
    
    public void setLableft2(SmallIconBtn lableft2) {
        this.lableft2 = lableft2;
    }
    
    public SmallIconBtn getLabbottom2() {
        return this.labbottom2;
    }
    
    public void setLabbottom2(SmallIconBtn labbottom2) {
        this.labbottom2 = labbottom2;
    }
    
    public SmallIconBtn getLabtop2() {
        return this.labtop2;
    }
    
    public void setLabtop2(SmallIconBtn labtop2) {
        this.labtop2 = labtop2;
    }
    
    public void styleFG() {
        this.remove(this.labtop2);
        this.remove(this.labbottom2);
        this.remove(this.lableft2);
        this.remove(this.labtop);
        this.remove(this.labbottom);
        this.remove(this.lableft);
        this.remove(this.clear);
        this.remove(this.clear2);
        this.remove(this.stop2);
        this.remove(this.history);
        this.remove(this.chatSwitchBtn);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X, ScrenceUntil.ChatFram_y));
            this.setLayout(null);
            (this.lableft = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.lableft);
            (this.labbottom = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下1", null)).setBounds(21, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labbottom);
            (this.labtop = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上1", null)).setBounds(40, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labtop);
            (this.clear = new SmallIconBtn("inkImg/button/147.png", 1, 2, "清屏1", null)).setBounds(59, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.clear);
            (this.lableft2 = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.lableft2);
            (this.labbottom2 = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下2", null)).setBounds(21, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labbottom2);
            (this.labtop2 = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上2", null)).setBounds(40, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labtop2);
            (this.clear2 = new SmallIconBtn("inkImg/button/147.png", 1, 2, "清屏2", null)).setBounds(59, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.clear2);
            (this.stop2 = new SmallIconBtn("inkImg/button/56.png", 1, 8, "停止", null)).setBounds(78, ScrenceUntil.ChatFram_y - 19, 20, 20);
            this.add(this.stop2);
            (this.history = new SmallIconBtn("inkImg/button/149.png", 1, 0, "记录", null)).setBounds(97, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.history);
            (this.chatSwitchBtn = new SmallIconBtn("inkImg/button/B38.png", 1, 0, "开关", null)).setBounds(116, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.chatSwitchBtn);
        }
        else {
            this.setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X, ScrenceUntil.ChatFram_y));
            this.setLayout(null);
            (this.lableft = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.lableft);
            (this.labbottom = new SmallIconBtn("inkImg/hongmu/25.png", 1, 1, "向下1", null)).setBounds(23, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labbottom);
            (this.labtop = new SmallIconBtn("inkImg/hongmu/24.png", 1, 0, "向上1", null)).setBounds(44, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.labtop);
            (this.clear = new SmallIconBtn("inkImg/hongmu/26.png", 1, 2, "清屏1", null)).setBounds(65, ScrenceUntil.ChatFram_y / 10 * 5 - 20, 20, 20);
            this.add(this.clear);
            (this.lableft2 = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", null)).setBounds(2, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.lableft2);
            (this.labbottom2 = new SmallIconBtn("inkImg/hongmu/25.png", 1, 1, "向下2", null)).setBounds(23, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labbottom2);
            (this.labtop2 = new SmallIconBtn("inkImg/hongmu/24.png", 1, 0, "向上2", null)).setBounds(44, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.labtop2);
            (this.clear2 = new SmallIconBtn("inkImg/hongmu/qh1.png", 1, 2, "清屏2", null)).setBounds(65, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.clear2);
            (this.stop2 = new SmallIconBtn("inkImg/hongmu/a8.png", 1, 8, "停止", null)).setBounds(86, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.stop2);
            (this.history = new SmallIconBtn("inkImg/hongmu/27.png", 1, 0, "记录", null)).setBounds(107, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.history);
            (this.chatSwitchBtn = new SmallIconBtn("inkImg/hongmu/26.png", 1, 0, "开关", null)).setBounds(128, ScrenceUntil.ChatFram_y - 20, 20, 20);
            this.add(this.chatSwitchBtn);
        }
    }


    //聊天界面背景
    public static void background() {
        ++FrameMessageChangeJpanel.i;
        FrameMessageChangeJpanel.icon = new ImageIcon("img/chat/BEIJING" + FrameMessageChangeJpanel.i + ".png");
        if (FrameMessageChangeJpanel.i == 8) {
            FrameMessageChangeJpanel.i = 0;
        }
    }
    
    static {
        FrameMessageChangeJpanel.styles = false;
        (FrameMessageChangeJpanel.chatbox = new ChatBox()).setW(ScrenceUntil.ChatFram_X - 10);
        FrameMessageChangeJpanel.chatbox.setH(ScrenceUntil.ChatFram_y - 20);
        (FrameMessageChangeJpanel.chatbox1 = new ChatBox()).setW(ScrenceUntil.ChatFram_X - 10);
        FrameMessageChangeJpanel.chatbox1.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
        (FrameMessageChangeJpanel.chatbox2 = new ChatBox()).setW(ScrenceUntil.ChatFram_X - 10);
        FrameMessageChangeJpanel.chatbox2.setH(ScrenceUntil.ChatFram_y / 10 * 5 - 20);
        FrameMessageChangeJpanel.icon = new ImageIcon("img/chat/BEIJING8.png");
        FrameMessageChangeJpanel.chatSwitch = new boolean[8];
        FrameMessageChangeJpanel.i = 0;
    }
}
