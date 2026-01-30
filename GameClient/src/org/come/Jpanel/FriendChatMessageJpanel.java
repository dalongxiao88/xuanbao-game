package org.come.Jpanel;

import org.come.until.MessagrFlagUntil;
import org.come.entity.TeamRole;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.AnalysisString;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.tool.tcpimg.RichLabel;
import org.come.until.ScrollUI;
import java.awt.Graphics;
import javax.swing.GrayFilter;
import java.awt.Image;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.Date;
import com.tool.image.ImageMixDeal;
import org.come.bean.ChatBean;
import org.come.until.Util;
import org.come.entity.Friendtable;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import com.tool.btn.RoleCaoZuoBtn;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FriendChatMessageJpanel extends JPanel
{
    private JLabel labNoC;
    private JLabel labTx;
    private JLabel labTxD;
    private static String ID;
    private static String HyName;
    private static String Grade;
    private JTextArea setwords;
    private Color fontcolor;
    private RoleCaoZuoBtn btnfasong;
    private JScrollPane js;
    private JScrollPane jscrollPane;
    final ImageIcon imageIcon;
    private Friendtable friendtable;
    public static boolean closeCk;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public FriendChatMessageJpanel() throws Exception {
        this.imageIcon = new ImageIcon("img/123_副本.png");
        if (!Util.dhjl) {
            Util.dhjl = true;
            ChatBean chatBean = new ChatBean();
            chatBean.setFriendName("大话精灵");
            chatBean.setMessage("#Y#38您要查询什么呢？如果有我解决不了的问题可以找老G哦！#r#G查询#RNPC#G请发送N=加NPC名字，#Y例如：N=玉皇大帝。#r#G查询#R掉落#G请发送D=物品名字，#Y例如：D=补天神石。#r#G查询#R技能#G请发送J=技能名称，#Y例如：J=夺命勾魂。");
            chatBean.setRolename(ImageMixDeal.userimg.getRoleShow().getRolename());
            chatBean.setSendRoleId(ImageMixDeal.userimg.getRoleShow().getRole_id());
            chatBean.setTime(new Date().getTime());
            ReceiveMessage1(chatBean, chatBean.getFriendName());
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(584, 404));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 56);
            offBtn.setBounds(552, 6, 25, 25);
            this.add(offBtn);
            (this.labTx = new JLabel("", 0)).setBounds(22, 17, 40, 40);
            this.add(this.labTx);
            (this.labTxD = new JLabel("", 0)).setBounds(344, 68, 218, 298);
            this.add(this.labTxD);
            (this.labNoC = new JLabel("", 0)).setBounds(344, 68, 218, 298);
            this.labNoC.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    FriendChatMessageJpanel.closeCk = !FriendChatMessageJpanel.closeCk;
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(this.labNoC);
            this.js = new JScrollPane(22, 31);
            this.js.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.js.getViewport().setOpaque(false);
            this.js.setOpaque(false);
            this.js.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
            this.js.setViewportView(null);
            this.js.setBounds(19, 85, 312, 208);
            this.add(this.js);
            (this.setwords = new JTextArea() {
                Image image = FriendChatMessageJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setCaretColor(Color.yellow);
            this.setwords.setLineWrap(true);
            this.setwords.setForeground(Color.white);
            this.setwords.setBackground(UIUtils.Color_BACK);
            this.setwords.setBorder(BorderFactory.createEmptyBorder());
            this.setwords.setFont(UIUtils.TEXT_FONT1);
            (this.jscrollPane = new JScrollPane(this.setwords)).setVerticalScrollBarPolicy(21);
            this.jscrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jscrollPane.getViewport().setOpaque(false);
            this.jscrollPane.setOpaque(false);
            this.jscrollPane.setBounds(24, 323, 304, 35);
            this.jscrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jscrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jscrollPane);
            (this.btnfasong = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "发送", 5, UIUtils.COLOR_BLACK)).setBounds(245, 365, 59, 24);
            this.add(this.btnfasong);
        }
        else {
            this.setPreferredSize(new Dimension(356, 410));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 56);
            offBtn.setBounds(320, 7, 23, 23);
            this.add(offBtn);
            (this.labTx = new JLabel("", 0)).setBounds(21, 19, 42, 42);
            this.add(this.labTx);
            (this.labNoC = new JLabel("", 0)).setBounds(20, 299, 17, 17);
            this.labNoC.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    FriendChatMessageJpanel.closeCk = !FriendChatMessageJpanel.closeCk;
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(this.labNoC);
            this.js = new JScrollPane(22, 31);
            this.js.getVerticalScrollBar().setUI(new ScrollUI());
            this.js.getViewport().setOpaque(false);
            this.js.setOpaque(false);
            this.js.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
            this.js.setViewportView(null);
            this.js.setBounds(22, 85, 311, 207);
            this.add(this.js);
            (this.setwords = new JTextArea() {
                Image image = FriendChatMessageJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setCaretColor(Color.yellow);
            this.setwords.setLineWrap(true);
            this.setwords.setForeground(Color.white);
            this.setwords.setBackground(UIUtils.Color_BACK);
            this.setwords.setBorder(BorderFactory.createEmptyBorder());
            this.setwords.setFont(UIUtils.TEXT_FONT1);
            (this.jscrollPane = new JScrollPane(this.setwords)).setVerticalScrollBarPolicy(22);
            this.jscrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jscrollPane.getViewport().setOpaque(false);
            this.jscrollPane.setOpaque(false);
            this.jscrollPane.setBounds(24, 321, 306, 36);
            this.jscrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jscrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jscrollPane);
            (this.btnfasong = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "发送", 5, UIUtils.COLOR_BTNPUTONG)).setBounds(245, 365, 60, 26);
            this.add(this.btnfasong);
        }
    }
    
    public void showFriend(Friendtable friend, RichLabel label) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (MyIsif.getStyle().equals("水墨")) {
            if (nao.equals("新")) {
                if ("非好友".equals(friend.getRace_name())) {
                    this.labTx.setIcon(CutButtonImage.getImage("img/icon/ico.png", 40, 40));
                    this.labTxD.setIcon(CutButtonImage.getImage("img/icon/ico.png", 218, 298));
                    FriendChatMessageJpanel.ID = "陌生人";
                }
                else {
                    this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + friend.getSpecies_id() + "-1.png", 40, 40));
                    this.labTxD.setIcon(CutButtonImage.getImage("img/head/Ds.png", 218, 298));
                    FriendChatMessageJpanel.ID = friend.getRole_id().toString();
                }
                FriendChatMessageJpanel.HyName = friend.getRolename();
                FriendChatMessageJpanel.Grade = AnalysisString.lvl(friend.getGrade().intValue()) + "级";
                this.js.setViewportView(label);
            }
            else {
                if ("非好友".equals(friend.getRace_name())) {
                    this.labTx.setIcon(CutButtonImage.getImage("img/icon/ico.png", 40, 40));
                    this.labTxD.setIcon(CutButtonImage.getImage("img/icon/ico.png", 218, 298));
                    FriendChatMessageJpanel.ID = "陌生人";
                }
                else {
                    this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + friend.getSpecies_id() + ".png", 40, 40));
                    this.labTxD.setIcon(CutButtonImage.getImage("img/head/Ds.png", 218, 298));
                    FriendChatMessageJpanel.ID = friend.getRole_id().toString();
                }
                FriendChatMessageJpanel.HyName = friend.getRolename();
                FriendChatMessageJpanel.Grade = AnalysisString.lvl(friend.getGrade().intValue()) + "级";
                this.js.setViewportView(label);
            }
        }
        else if (nao.equals("新")) {
            if ("非好友".equals(friend.getRace_name())) {
                this.labTx.setIcon(CutButtonImage.getImage("img/icon/ico.png", 40, 40));
                FriendChatMessageJpanel.ID = "陌生人";
            }
            else {
                this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + friend.getSpecies_id() + "-1.png", 40, 40));
                FriendChatMessageJpanel.ID = friend.getRole_id().toString();
            }
            FriendChatMessageJpanel.HyName = friend.getRolename();
            FriendChatMessageJpanel.Grade = AnalysisString.lvl(friend.getGrade().intValue()) + "级";
            this.js.setViewportView(label);
        }
        else {
            if ("非好友".equals(friend.getRace_name())) {
                this.labTx.setIcon(CutButtonImage.getImage("img/icon/ico.png", 40, 40));
                FriendChatMessageJpanel.ID = "陌生人";
            }
            else {
                this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + friend.getSpecies_id() + ".png", 40, 40));
                FriendChatMessageJpanel.ID = friend.getRole_id().toString();
            }
            FriendChatMessageJpanel.HyName = friend.getRolename();
            FriendChatMessageJpanel.Grade = AnalysisString.lvl(friend.getGrade().intValue()) + "级";
            this.js.setViewportView(label);
        }
    }
    
    public void showFriend(TeamRole teamRole, RichLabel label) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + "-1.png", 40, 40));
            this.labTxD.setIcon(CutButtonImage.getImage("sc/head/Ds.png", 218, 298));
            FriendChatMessageJpanel.HyName = teamRole.getName();
            FriendChatMessageJpanel.Grade = AnalysisString.lvl(teamRole.getGrade()) + "级";
            FriendChatMessageJpanel.ID = teamRole.getRoleId().toString();
            this.js.setViewportView(label);
        }
        else {
            this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + ".png", 40, 40));
            this.labTxD.setIcon(CutButtonImage.getImage("sc/head/Ds.png", 218, 298));
            FriendChatMessageJpanel.HyName = teamRole.getName();
            FriendChatMessageJpanel.Grade = AnalysisString.lvl(teamRole.getGrade()) + "级";
            FriendChatMessageJpanel.ID = teamRole.getRoleId().toString();
            this.js.setViewportView(label);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B328.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 584, 404, this);
            if (FriendChatMessageJpanel.HyName != null) {
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(UIUtils.COLOR_NAME8);
                g.drawString(FriendChatMessageJpanel.HyName, 73, 36);
                g.drawString(FriendChatMessageJpanel.Grade, 73, 57);
                g.setColor(Color.RED);
                g.drawString("ID:" + FriendChatMessageJpanel.ID, 78 + FriendChatMessageJpanel.HyName.length() * 16, 37);
                if (FriendChatMessageJpanel.closeCk) {
                    if (this.icon1 == null) {
                        this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
                    }
                    g.drawImage(this.icon1.getImage(), 20, 299, 15, 15, this);
                }
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/65_png.xy2uiimg.friend_bg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 356, 410, this);
            if (FriendChatMessageJpanel.HyName != null) {
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(new Color(185, 165, 75));
                g.drawString(FriendChatMessageJpanel.HyName, 73, 42);
                g.drawString(FriendChatMessageJpanel.Grade, 73, 60);
                g.setColor(Color.WHITE);
                g.drawString("ID: " + FriendChatMessageJpanel.ID, 78 + FriendChatMessageJpanel.HyName.length() * 16, 43);
                if (FriendChatMessageJpanel.closeCk) {
                    if (this.icon1 == null) {
                        this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
                    }
                    g.drawImage(this.icon1.getImage(), 20, 299, 15, 15, this);
                }
            }
        }
    }
    
    public static void ReceiveMessage1(ChatBean bean, String rolename) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#r#R ");
        buffer.append(bean.getRolename());
        buffer.append(" ");
        buffer.append(MessagrFlagUntil.timeStamp2Date(bean.getTime(), "yyyy-MM-dd HH:mm:ss"));
        buffer.append("#r#W");
        buffer.append(bean.getMessage());
        RichLabel richLabel = MessagrFlagUntil.getRichLabel(rolename);
        richLabel.addText(buffer.toString());
        richLabel.setPreferredSize(richLabel.computeSize(richLabel.getWidth()));
    }
    
    public JTextArea getSetwords() {
        return this.setwords;
    }
    
    public void setSetwords(JTextArea setwords) {
        this.setwords = setwords;
    }
    
    public RoleCaoZuoBtn getBtnfasong() {
        return this.btnfasong;
    }
    
    public void setBtnfasong(RoleCaoZuoBtn btnfasong) {
        this.btnfasong = btnfasong;
    }
    
    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public Friendtable getFriendtable() {
        return this.friendtable;
    }
    
    public void setFriendtable(Friendtable friendtable) {
        this.friendtable = friendtable;
    }
    
    public JScrollPane getJs() {
        return this.js;
    }
    
    public void setJs(JScrollPane js) {
        this.js = js;
    }
    
    public static String getID() {
        return FriendChatMessageJpanel.ID;
    }
    
    public static void setID(String iD) {
        FriendChatMessageJpanel.ID = iD;
    }
    
    public static String getHyName() {
        return FriendChatMessageJpanel.HyName;
    }
    
    public static void setHyName(String hyName) {
        FriendChatMessageJpanel.HyName = hyName;
    }
    
    public static String getGrade() {
        return FriendChatMessageJpanel.Grade;
    }
    
    public static void setGrade(String grade) {
        FriendChatMessageJpanel.Grade = grade;
    }
    
    public JScrollPane getJscrollPane() {
        return this.jscrollPane;
    }
    
    public void setJscrollPane(JScrollPane jscrollPane) {
        this.jscrollPane = jscrollPane;
    }
    
    static {
        FriendChatMessageJpanel.closeCk = true;
    }
}
