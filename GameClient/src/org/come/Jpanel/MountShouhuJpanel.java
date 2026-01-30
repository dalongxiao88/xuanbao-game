package org.come.Jpanel;

import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import com.tool.image.ImageMixDeal;
import org.come.bean.LoginResult;
import org.come.until.Util;
import javax.swing.Icon;
import java.awt.Graphics;
import org.come.MountShouHu.ShouhuPackGoodsMouslisten;
import java.awt.BorderLayout;
import org.come.MountShouHu.TxtMoulisten;
import org.come.MountShouHu.shuxingRenderer;
import javax.swing.BorderFactory;
import org.come.MountShouHu.ZonglanRenderer;
import javax.swing.ListModel;
import org.come.Frame.ZhuFrame;
import org.come.entity.Mount;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import com.tool.tcp.SpriteFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.util.ArrayList;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.MountPanelBtn;
import org.come.login.SpriteBtn;
import javax.swing.JTextPane;
import com.tool.btn.MountShouhuBtn;
import javax.swing.JLabel;
import org.come.MountShouHu.ShouHu;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.List;
import javax.swing.JPanel;

public class MountShouhuJpanel extends JPanel
{
    public static int zonglanidx;
    public static int shuxingidx;
    public static List<String> zonglan;
    public static List<String> shuxing;
    public static Map<String, List<String>> shuxingmap;
    static Map<String, ImageIcon> mount;
    private static JList<String> zonglanlsit;
    private static final char[] CN_UPPER_NUMBER;
    public static ImageIcon[] imageIcons1;
    public static ImageIcon[] imageIcons2;
    private final ImageIcon hengtiao;
    private final ImageIcon xuanze;
    private final ImageIcon hengtiao1;
    private final ImageIcon xuanze1;
    private final JScrollPane jScrollPane_shuxing;
    private final JScrollPane jScrollPane_zonglan;
    private final JList<String> shuxinglsit;
    private final DefaultListModel<String> shuxingmodel;
    public ShouHu shouHu;
    public JLabel sixiang1zuoqi;
    public MountShouhuBtn shengji;
    public MountShouhuBtn fuling;
    public int xuanzhong;
    public MountShouhuBtn[] sixiang;
    public MountShouhuBtn[] hengtiaoxianshi;
    public JTextPane ZTlvl;
    public JTextPane QLlvl;
    public JTextPane BHlvl;
    public JTextPane ZQlvl;
    public JTextPane XUlvl;
    public JTextPane BHname;
    public JTextPane QLname;
    public JTextPane XUname;
    public JTextPane ZQname;
    public SpriteBtn tcp;
    public SpriteBtn tcp1;
    public MountShouhuBtn shouhushi;
    private ImageIcon ico;
    private List<MountPanelBtn> shouhu;
    private MountShouhuBtn qinglong;
    private MountShouhuBtn baihu;
    private MountShouhuBtn xuanwu;
    private MountShouhuBtn zhuque;
    private MountShouhuBtn zhongtian;
    private JLabel sixiang1;
    private JLabel sixiang2;
    private DefaultListModel<String> zonglanmode;
    public MountShouhuBtn[] shouhuBtn;
    public int zuoqi;
    public int SX;
    public boolean s;
    public int l;
    public int fenjie;
    private FormsOnOffBtn offBtn;
    
    public MountShouhuJpanel() {
        this.hengtiao = new ImageIcon("inkImg/Client/待升级后解锁.png");
        this.xuanze = new ImageIcon("inkImg/Client/选择坐骑界面.png");
        this.hengtiao1 = new ImageIcon("inkImg/Client/可解锁.png");
        this.xuanze1 = new ImageIcon("inkImg/Client/可解锁不带所图标格.png");
        this.xuanzhong = 0;
        this.sixiang = new MountShouhuBtn[7];
        this.hengtiaoxianshi = new MountShouhuBtn[7];
        this.ico = new ImageIcon("inkImg/Client/守护背景.png");
        this.shouhu = new ArrayList<>();
        this.shouhuBtn = new MountShouhuBtn[7];
        this.zuoqi = -1;
        this.SX = -1;
        this.s = false;
        this.l = -1;
        this.fenjie = 0;
        this.setPreferredSize(new Dimension(740, 516));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 559)).setBounds(715, 0, 25, 25);
        this.add(this.offBtn);
        MountPanelBtn btn = new MountPanelBtn("inkImg/Client/未选中按钮.png", 1, "坐骑", this, 9, UIUtils.Black);
        btn.setBounds(50, 15, 100, 33);
        this.add(btn);
        this.shouhu.add(btn);
        MountPanelBtn btn2 = new MountPanelBtn("inkImg/Client/未选中按钮.png", 1, "守护", this, 10, UIUtils.Black);
        btn2.setBounds(160, 15, 100, 33);
        this.add(btn2);
        this.shouhu.add(btn2);
        (this.ZTlvl = new JTextPane()).setFont(UIUtils.TEXT_FONT1);
        this.ZTlvl.setBounds(124, 255, 15, 20);
        this.ZTlvl.setOpaque(false);
        this.ZTlvl.setForeground(Color.white);
        this.ZTlvl.setText("0");
        this.ZTlvl.setEnabled(false);
        this.add(this.ZTlvl);
        (this.QLlvl = new JTextPane()).setFont(UIUtils.TEXT_FONT1);
        this.QLlvl.setBounds(60, 305, 15, 20);
        this.QLlvl.setOpaque(false);
        this.QLlvl.setForeground(Color.white);
        this.QLlvl.setText("0");
        this.QLlvl.setEnabled(false);
        this.add(this.QLlvl);
        (this.BHlvl = new JTextPane()).setFont(UIUtils.TEXT_FONT1);
        this.BHlvl.setBounds(199, 170, 15, 20);
        this.BHlvl.setOpaque(false);
        this.BHlvl.setForeground(Color.white);
        this.BHlvl.setText("0");
        this.BHlvl.setEnabled(false);
        this.add(this.BHlvl);
        (this.XUlvl = new JTextPane()).setFont(UIUtils.TEXT_FONT1);
        this.XUlvl.setBounds(160, 402, 15, 20);
        this.XUlvl.setOpaque(false);
        this.XUlvl.setForeground(Color.white);
        this.XUlvl.setText("0");
        this.XUlvl.setEnabled(false);
        this.add(this.XUlvl);
        (this.ZQlvl = new JTextPane()).setFont(UIUtils.TEXT_FONT1);
        this.ZQlvl.setBounds(86, 81, 15, 20);
        this.ZQlvl.setOpaque(false);
        this.ZQlvl.setForeground(Color.white);
        this.ZQlvl.setText("0");
        this.ZQlvl.setEnabled(false);
        this.add(this.ZQlvl);
        (this.BHname = new JTextPane()).setOpaque(false);
        this.BHname.setBounds(198, 200, 50, 50);
        this.BHname.setForeground(new Color(9803157));
        this.BHname.setText("白\n虎");
        this.BHname.setEnabled(false);
        this.BHname.setFont(UIUtils.TEXT_FONT42.deriveFont(1));
        this.add(this.BHname);
        (this.QLname = new JTextPane()).setOpaque(false);
        this.QLname.setBounds(60, 330, 50, 50);
        this.QLname.setForeground(new Color(9803157));
        this.QLname.setText("青\n龙");
        this.QLname.setFont(UIUtils.TEXT_FONT42.deriveFont(1));
        this.QLname.setEnabled(false);
        this.add(this.QLname);
        (this.ZQname = new JTextPane()).setOpaque(false);
        this.ZQname.setBounds(83, 104, 50, 50);
        this.ZQname.setForeground(new Color(9803157));
        this.ZQname.setText("朱\n雀");
        this.ZQname.setFont(UIUtils.TEXT_FONT42.deriveFont(1));
        this.ZQname.setEnabled(false);
        this.add(this.ZQname);
        (this.XUname = new JTextPane()).setOpaque(false);
        this.XUname.setBounds(157, 425, 50, 50);
        this.XUname.setForeground(new Color(9803157));
        this.XUname.setText("玄\n武");
        this.XUname.setFont(UIUtils.TEXT_FONT42.deriveFont(1));
        this.XUname.setEnabled(false);
        this.add(this.XUname);
        (this.zhongtian = new MountShouhuBtn("inkImg/Client/中天_未选中.png", 1, 4, this, SpriteFactory.VloadSprite("inkImg/Client/动效_中天.tcp", null), 62, 70, false)).setBounds(70, 179, 136, 142);
        this.add(this.zhongtian);
        (this.qinglong = new MountShouhuBtn("inkImg/Client/青龙_未选中.png", 1, 0, this, SpriteFactory.VloadSprite("inkImg/Client/动效_青龙白虎朱雀玄龟.tcp", null), 23, 163, false)).setBounds(46, 195, 74, 235);
        this.add(this.qinglong);
        (this.xuanwu = new MountShouhuBtn("inkImg/Client/玄武_未选中.png", 1, 1, this, SpriteFactory.VloadSprite("inkImg/Client/动效_青龙白虎朱雀玄龟.tcp", null), 122, 62, false)).setBounds(48, 394, 138, 103);
        this.add(this.xuanwu);
        (this.zhuque = new MountShouhuBtn("inkImg/Client/朱雀_未选中.png", 1, 2, this, SpriteFactory.VloadSprite("inkImg/Client/动效_青龙白虎朱雀玄龟.tcp", null), 40, 82, false)).setBounds(55, 50, 184, 134);
        this.add(this.zhuque);
        (this.baihu = new MountShouhuBtn("inkImg/Client/白虎_未选中.png", 1, 3, this, SpriteFactory.VloadSprite("inkImg/Client/动效_青龙白虎朱雀玄龟.tcp", null), 63, 63, false)).setBounds(145, 158, 104, 206);
        this.add(this.baihu);
        (this.sixiang1 = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/Client/可添加坐骑格.png", -1, -1));
        this.sixiang1.setBounds(355, 142, 60, 60);
        this.sixiang1.setVisible(true);
        this.sixiang1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 1) {
                    FormsManagement.showForm(2255);
                    MountShouhuJpanel.this.SX = 1;
                }
                else {
                    MountShouhuJpanel.this.sixiang1.setBounds(355, 142, 60, 60);
                    MountShouhuJpanel.this.sixiang1.setIcon(CutButtonImage.getImage("inkImg/Client/可添加坐骑格.png", -1, -1));
                    String mes = RoleData.getRoleData().getLoginResult().getZhongtian();
                    if (mes != null) {
                        String[] mes2 = mes.split("\\|");
                        mes2[2] = "0*" + mes.split("\\|")[2].split("\\*")[1];
                        RoleData.getRoleData().getLoginResult().setZhongtian(mes2[0] + "|" + mes2[1] + "|" + mes2[2]);
                    }
                    else {
                        RoleData.getRoleData().getLoginResult().setZhongtian("-1|-1|0*-1");
                    }
                    MountShouhuJpanel.this.shouHu.setZhongtianmount(RoleData.getRoleData().getLoginResult().getZhongtian().split("\\|")[2]);
                    SendMessageUntil.toServer(Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(MountShouhuJpanel.this.shouHu)));
                    String[] m = RoleData.getRoleData().getLoginResult().getSh().split("\\|");
                    m[MountShouhuJpanel.this.getXuanzhong() - 1] = m[MountShouhuJpanel.this.getXuanzhong() - 1].split("-")[0] + "-0-" + m[MountShouhuJpanel.this.getXuanzhong() - 1].split("-")[2];
                    RoleData.getRoleData().getLoginResult().setSh(m[0] + "|" + m[1] + "|" + m[2] + "|" + m[3]);
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("SSH&" + RoleData.getRoleData().getLoginResult().getSh()));
                    List<Mount> mountList = UserMessUntil.getMountlsit();
                    for (int i = 0; i <= mountList.size() - 1; ++i) {
                        if (((Mount)mountList.get(i)).getSh() != 0 && ((Mount)mountList.get(i)).getSh() == MountShouhuJpanel.this.xuanzhong) {
                            ((Mount)mountList.get(i)).setSh(0);
                            String sendmes1 = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(mountList.get(i)));
                            SendMessageUntil.toServer(sendmes1);
                        }
                    }
                }
            }
        });
        this.add(this.sixiang1);
        (this.sixiang2 = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/Client/待解锁坐骑格.png", -1, -1));
        this.sixiang2.setBounds(575, 142, 60, 60);
        this.sixiang2.setVisible(true);
        this.sixiang2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (MountShouhuJpanel.this.xuanzhong) {
                    case 1: {
                        if (MountShouhuJpanel.this.shouHu.getQinglonglvl() < 16) {
                            ZhuFrame.getZhuJpanel().addPrompt2("需要青龙等级达到16级");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        if (MountShouhuJpanel.this.shouHu.getBaihulvl() < 16) {
                            ZhuFrame.getZhuJpanel().addPrompt2("需要白虎等级达到16级");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        if (MountShouhuJpanel.this.shouHu.getXuanwulvl() < 16) {
                            ZhuFrame.getZhuJpanel().addPrompt2("需要玄武等级达到16级");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        if (MountShouhuJpanel.this.shouHu.getZhuquelvl() < 16) {
                            ZhuFrame.getZhuJpanel().addPrompt2("需要朱雀等级达到16级");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                }
                if (e.getButton() == 1) {
                    FormsManagement.showForm(2255);
                    MountShouhuJpanel.this.SX = 2;
                }
                else {
                    MountShouhuJpanel.this.sixiang2.setBounds(580, 142, 60, 60);
                    MountShouhuJpanel.this.sixiang2.setIcon(CutButtonImage.getImage("inkImg/Client/可添加坐骑格.png", -1, -1));
                    String mes = RoleData.getRoleData().getLoginResult().getZhongtian();
                    if (mes != null) {
                        String[] mes2 = mes.split("\\|");
                        mes2[2] = mes.split("\\|")[2].split("\\*")[0] + "*0";
                        RoleData.getRoleData().getLoginResult().setZhongtian(mes2[0] + "|" + mes2[1] + "|" + mes2[2]);
                    }
                    else {
                        RoleData.getRoleData().getLoginResult().setZhongtian("-1|-1|-1*0");
                    }
                    MountShouhuJpanel.this.shouHu.setZhongtianmount(RoleData.getRoleData().getLoginResult().getZhongtian().split("\\|")[2]);
                    SendMessageUntil.toServer(Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(MountShouhuJpanel.this.shouHu)));
                    List<Mount> mountList = UserMessUntil.getMountlsit();
                    String[] m = RoleData.getRoleData().getLoginResult().getSh().split("\\|");
                    m[MountShouhuJpanel.this.getXuanzhong() - 1] = m[MountShouhuJpanel.this.getXuanzhong() - 1].split("-")[0] + "-" + m[MountShouhuJpanel.this.getXuanzhong() - 1].split("-")[1] + "-0";
                    RoleData.getRoleData().getLoginResult().setSh(m[0] + "|" + m[1] + "|" + m[2] + "|" + m[3]);
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("SSH&" + RoleData.getRoleData().getLoginResult().getSh()));
                    for (int i = 0; i <= mountList.size() - 1; ++i) {
                        if (((Mount)mountList.get(i)).getSh() != 0 && ((Mount)mountList.get(i)).getSh() == MountShouhuJpanel.this.xuanzhong) {
                            ((Mount)mountList.get(i)).setSh(0);
                            String sendmes1 = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(mountList.get(i)));
                            SendMessageUntil.toServer(sendmes1);
                        }
                    }
                }
            }
        });
        this.add(this.sixiang2);
        this.zonglanmode = new DefaultListModel<>();
        (MountShouhuJpanel.zonglanlsit = new JList<String>(this.zonglanmode) {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        MountShouhuJpanel.zonglanlsit.setSelectionMode(0);
        MountShouhuJpanel.zonglanlsit.setBackground(UIUtils.Color_BACK);
        MountShouhuJpanel.zonglanlsit.setCellRenderer(new ZonglanRenderer());
        (this.jScrollPane_zonglan = new JScrollPane(MountShouhuJpanel.zonglanlsit)).setVerticalScrollBarPolicy(22);
        this.jScrollPane_zonglan.getViewport().setOpaque(false);
        this.jScrollPane_zonglan.setOpaque(false);
        this.jScrollPane_zonglan.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane_zonglan.setHorizontalScrollBarPolicy(31);
        this.jScrollPane_zonglan.setBounds(515, 344, 190, 120);
        this.jScrollPane_zonglan.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        this.add(this.jScrollPane_zonglan);
        this.shuxingmodel = new DefaultListModel<>();
        (this.shuxinglsit = new JList<String>(this.shuxingmodel) {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.shuxinglsit.setSelectionMode(0);
        this.shuxinglsit.setBackground(UIUtils.Color_BACK);
        this.shuxinglsit.setCellRenderer(new shuxingRenderer());
        (this.jScrollPane_shuxing = new JScrollPane(this.shuxinglsit)).setVerticalScrollBarPolicy(22);
        this.jScrollPane_shuxing.getViewport().setOpaque(false);
        this.jScrollPane_shuxing.setOpaque(false);
        this.jScrollPane_shuxing.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane_shuxing.setHorizontalScrollBarPolicy(31);
        this.jScrollPane_shuxing.setBounds(280, 344, 210, 120);
        this.jScrollPane_shuxing.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        this.add(this.jScrollPane_shuxing);
        (this.shengji = new MountShouhuBtn("inkImg/Client/升级按钮.png", 1, 6, "升 级", UIUtils.Black, UIUtils.TEXT_HY17, this)).setBounds(450, 470, 96, 28);
        this.add(this.shengji);
        (this.fuling = new MountShouhuBtn("inkImg/Client/附灵按钮.png", 1, 7, "赋灵", UIUtils.Black, UIUtils.TEXT_HY17, this)).setBounds(250, 270, 74, 36);
        this.add(this.fuling);
        for (int i = 0; i < 7; ++i) {
            (this.sixiang[i] = new MountShouhuBtn("inkImg/Client/无加号待解锁格.png", 1, 50 + i, this)).addMouseListener(new TxtMoulisten(i));
            this.shouhuBtn[i] = new MountShouhuBtn("inkImg/Client/守护石圆圈带未点亮守护石.png", 1, 60 + i, this);
            this.hengtiaoxianshi[i] = new MountShouhuBtn("inkImg/Client/待升级后解锁.png", -1);
            switch (i) {
                case 0: {
                    this.sixiang[i].setBounds(450, 65, 60, 60);
                    this.shouhuBtn[i].setBounds(490, 100, 32, 32);
                    break;
                }
                case 1: {
                    this.sixiang[i].setBounds(345, 80, 60, 60);
                    this.shouhuBtn[i].setBounds(390, 115, 32, 32);
                    break;
                }
                case 2: {
                    this.sixiang[i].setBounds(265, 135, 60, 60);
                    this.shouhuBtn[i].setBounds(310, 170, 32, 32);
                    break;
                }
                case 3: {
                    this.sixiang[i].setBounds(390, 200, 60, 60);
                    this.shouhuBtn[i].setBounds(430, 235, 32, 32);
                    break;
                }
                case 4: {
                    this.sixiang[i].setBounds(510, 200, 60, 60);
                    this.shouhuBtn[i].setBounds(550, 235, 32, 32);
                    break;
                }
                case 5: {
                    this.sixiang[i].setBounds(640, 135, 60, 60);
                    this.shouhuBtn[i].setBounds(680, 170, 32, 32);
                    break;
                }
                case 6: {
                    this.sixiang[i].setBounds(550, 80, 60, 60);
                    this.shouhuBtn[i].setBounds(590, 115, 32, 32);
                    break;
                }
            }
            this.hengtiaoxianshi[i].setVisible(false);
            this.hengtiaoxianshi[i].setBounds(this.sixiang[i].getX() + 10, this.sixiang[i].getY() + 20, 37, 23);
            this.sixiang[i].setVisible(false);
            this.sixiang[i].setOpaque(false);
            this.sixiang[i].setBackground(new Color(0, 0, 0, 0));
            this.sixiang[i].setLayout(new BorderLayout());
            this.shouhuBtn[i].setVisible(false);
            this.shouhuBtn[i].setBackground(new Color(0, 0, 0, 0));
            this.shouhuBtn[i].addMouseListener(new ShouhuPackGoodsMouslisten(i + 60));
            this.add(this.shouhuBtn[i]);
            this.add(this.hengtiaoxianshi[i]);
            this.add(this.sixiang[i]);
        }
    }
    
    public static Object[] calculateTotalAttackValue(DefaultListModel<String> shuxingList, int lvl, String value) {
        int fixedAttackValue = 0;
        double percentageAttackValue = 0.0;
        if (lvl >= shuxingList.size()) {
            lvl = shuxingList.getSize();
        }
        for (int i = 0; i < lvl; ++i) {
            String attribute = (String)shuxingList.get(i);
            if (attribute.contains(value)) {
                String[] parts = attribute.split(value);
                if (parts.length >= 2) {
                    String valuePart = parts[1].trim();
                    if (valuePart.endsWith("+")) {
                        valuePart = valuePart.substring(1);
                    }
                    if (valuePart.endsWith("%")) {
                        double percentageValue = Double.parseDouble(valuePart.substring(0, valuePart.length() - 1));
                        percentageAttackValue += percentageValue;
                    }
                    else {
                        try {
                            int fixedValue = Integer.parseInt(valuePart);
                            fixedAttackValue += fixedValue;
                        }
                        catch (NumberFormatException ex) {}
                    }
                }
            }
        }
        return new Object[] { Integer.valueOf(fixedAttackValue), Double.valueOf(percentageAttackValue) };
    }
    
    public void shou() {
        MountShouhuJpanel.zonglan.clear();
        this.zonglanmode.clear();
        this.shuxingmodel.clear();
        MountShouhuJpanel.shuxing.clear();
        this.ZTlvl.setText(String.valueOf((this.shouHu.getZhongtianlvl() == -1) ? 0 : this.shouHu.getZhongtianlvl()));
        this.QLlvl.setText(String.valueOf((this.shouHu.getQinglonglvl() == -1) ? 0 : this.shouHu.getQinglonglvl()));
        this.BHlvl.setText(String.valueOf((this.shouHu.getBaihulvl() == -1) ? 0 : this.shouHu.getBaihulvl()));
        this.ZQlvl.setText(String.valueOf((this.shouHu.getZhuquelvl() == -1) ? 0 : this.shouHu.getZhuquelvl()));
        this.XUlvl.setText(String.valueOf((this.shouHu.getXuanwulvl() == -1) ? 0 : this.shouHu.getXuanwulvl()));
        switch (this.xuanzhong) {
            case 0: {
                this.shuxingmodel.addElement("1级 青龙守护属性提升10%");
                this.shuxingmodel.addElement("2级 白虎守护属性提升10%");
                this.shuxingmodel.addElement("3级 朱雀守护属性提升10%");
                this.shuxingmodel.addElement("4级 玄武守护属性提升10%");
                this.shuxingmodel.addElement("5级 青龙守护属性提升15%");
                this.shuxingmodel.addElement("6级 白虎守护属性提升15%");
                this.shuxingmodel.addElement("7级 朱雀守护属性提升15%");
                this.shuxingmodel.addElement("8级 玄武守护属性提升15%");
                this.shuxingmodel.addElement("9级 青龙守护属性提升15%");
                this.shuxingmodel.addElement("10级 白虎守护属性提升15%");
                this.shuxingmodel.addElement("11级 朱雀守护属性提升15%");
                this.shuxingmodel.addElement("12级 玄武守护属性提升15%");
                this.shuxingmodel.addElement("13级 青龙守护属性提升20%");
                this.shuxingmodel.addElement("14级 白虎守护属性提升20%");
                this.shuxingmodel.addElement("15级 朱雀守护属性提升20%");
                this.shuxingmodel.addElement("16级 玄武守护属性提升20%");
                this.shuxingmodel.addElement("17级 青龙守护属性提升20%");
                this.shuxingmodel.addElement("18级 白虎守护属性提升20%");
                this.shuxingmodel.addElement("19级 朱雀守护属性提升20%");
                this.shuxingmodel.addElement("20级 玄武守护属性提升20%");
                this.shuxingmodel.addElement("21级 青龙守护属性提升20%");
                this.shuxingmodel.addElement("22级 白虎守护属性提升20%");
                this.shuxingmodel.addElement("23级 朱雀守护属性提升20%");
                this.shuxingmodel.addElement("24级 玄武守护属性提升20%");
                int k = Math.min(this.shouHu.zhongtianlvl, 24);
                int q = 0;
                int b = 0;
                int x = 0;
                int z = 0;
                for (int i = 0; i <= k - 1; ++i) {
                    if (((String)this.shuxingmodel.elementAt(i)).contains("青龙")) {
                        char secondLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 3);
                        char thirdLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 2);
                        q = q + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                    }
                    else if (((String)this.shuxingmodel.elementAt(i)).contains("白虎")) {
                        char secondLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 3);
                        char thirdLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 2);
                        b = b + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                    }
                    if (((String)this.shuxingmodel.elementAt(i)).contains("朱雀")) {
                        char secondLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 3);
                        char thirdLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 2);
                        z = z + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                    }
                    if (((String)this.shuxingmodel.elementAt(i)).contains("玄武")) {
                        char secondLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 3);
                        char thirdLastChar = ((String)this.shuxingmodel.elementAt(i)).charAt(((String)this.shuxingmodel.elementAt(i)).length() - 2);
                        x = x + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                    }
                }
                if (q != 0) {
                    this.zonglanmode.addElement("青龙守护属性提升" + q + "%");
                    MountShouhuJpanel.zonglan.add("青龙守护属性提升" + q + "%");
                }
                if (b != 0) {
                    this.zonglanmode.addElement("白虎守护属性提升" + b + "%");
                    MountShouhuJpanel.zonglan.add("白虎守护属性提升" + b + "%");
                }
                if (z != 0) {
                    this.zonglanmode.addElement("朱雀守护属性提升" + z + "%");
                    MountShouhuJpanel.zonglan.add("朱雀守护属性提升" + z + "%");
                }
                if (x != 0) {
                    this.zonglanmode.addElement("玄武守护属性提升" + x + "%");
                    MountShouhuJpanel.zonglan.add("玄武守护属性提升" + x + "%");
                    break;
                }
                else {
                    break;
                }
            }
            case 1: {
                for (int i = 0; i <= ((List<String>)MountShouhuJpanel.shuxingmap.get("青龙")).size() - 1; ++i) {
                    this.shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("青龙")).get(i));
                }
                Object[] attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.qinglonglvl, "速度");
                int fixedAttackValue = (int)attackValues[0];
                double percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("速度 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("速度 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.qinglonglvl, "气血");
                fixedAttackValue = (int)attackValues[0];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("气血 +" + fixedAttackValue);
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.qinglonglvl, "法力");
                fixedAttackValue = (int)attackValues[0];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("法力 +" + fixedAttackValue);
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.qinglonglvl, "物理吸收率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("物理吸收率 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.qinglonglvl, "法术躲闪几率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("法术躲闪几率 +" + percentageAttackValue + "%");
                }
                for (int p = 0; p <= this.zonglanmode.size() - 1; ++p) {
                    MountShouhuJpanel.zonglan.add(this.zonglanmode.get(p));
                }
                break;
            }
            case 2: {
                for (int j = 0; j <= ((List<String>)MountShouhuJpanel.shuxingmap.get("白虎")).size() - 1; ++j) {
                    this.shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("白虎")).get(j));
                }
                Object[] attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.baihulvl, "攻击");
                int fixedAttackValue = (int)attackValues[0];
                double percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("攻击 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("攻击 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.baihulvl, "气血");
                fixedAttackValue = (int)attackValues[0];
                percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("气血 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("气血 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.baihulvl, "狂暴率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("狂暴率 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.baihulvl, "命中率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("命中率 +" + percentageAttackValue + "%");
                }
                for (int p = 0; p <= this.zonglanmode.size() - 1; ++p) {
                    MountShouhuJpanel.zonglan.add(this.zonglanmode.get(p));
                }
                break;
            }
            case 3: {
                for (int j = 0; j <= ((List<String>)MountShouhuJpanel.shuxingmap.get("玄武")).size() - 1; ++j) {
                    this.shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("玄武")).get(j));
                }
                Object[] attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.xuanwulvl, "气血");
                int fixedAttackValue = (int)attackValues[0];
                double percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("气血 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("气血 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.xuanwulvl, "法力");
                fixedAttackValue = (int)attackValues[0];
                percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("法力 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("法力 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.xuanwulvl, " 抗致命率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("抗致命率 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.xuanwulvl, "抗仙法鬼火狂暴率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("抗仙法鬼火狂暴率 +" + percentageAttackValue + "%");
                }
                for (int p = 0; p <= this.zonglanmode.size() - 1; ++p) {
                    MountShouhuJpanel.zonglan.add(this.zonglanmode.get(p));
                }
                break;
            }
            case 4: {
                for (int j = 0; j <= ((List<String>)MountShouhuJpanel.shuxingmap.get("朱雀")).size() - 1; ++j) {
                    this.shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("朱雀")).get(j));
                }
                Object[] attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.zhuquelvl, "法力");
                int fixedAttackValue = (int)attackValues[0];
                double percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("法力 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("法力 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.zhuquelvl, "气血");
                fixedAttackValue = (int)attackValues[0];
                percentageAttackValue = (double)attackValues[1];
                if (fixedAttackValue != 0) {
                    this.zonglanmode.addElement("气血 +" + fixedAttackValue);
                }
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("气血 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.zhuquelvl, " 忽视仙法鬼火");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("忽视仙法鬼火 +" + percentageAttackValue + "%");
                }
                attackValues = calculateTotalAttackValue(this.shuxingmodel, this.shouHu.zhuquelvl, "仙法鬼火命中率");
                percentageAttackValue = (double)attackValues[1];
                if (percentageAttackValue != 0.0) {
                    this.zonglanmode.addElement("仙法鬼火命中率 +" + percentageAttackValue + "%");
                }
                for (int p = 0; p <= this.zonglanmode.size() - 1; ++p) {
                    MountShouhuJpanel.zonglan.add(this.zonglanmode.get(p));
                }
                break;
            }
        }
        for (int l = 0; l <= this.shuxingmodel.size() - 1; ++l) {
            MountShouhuJpanel.shuxing.add(this.shuxingmodel.get(l));
        }
        if (this.xuanzhong == 0) {
            this.sixiang1.setVisible(false);
            this.sixiang2.setVisible(false);
            this.setIco(new ImageIcon("inkImg/Client/守护背景.png"));
            this.zhongtian.setIschoose(true);
        }
        else {
            this.sixiang1.setVisible(true);
            this.sixiang2.setVisible(true);
            this.setIco(new ImageIcon("inkImg/Client/四象背景.png"));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.ico != null) {
            g.drawImage(this.ico.getImage(), 0, 0, 740, 516, null);
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        g.setFont(UIUtils.TEXT_MSGX.deriveFont(16));
        g.setColor(new Color(9803157));
        if (this.xuanzhong != 0) {
            for (int i = 0; i < 7; ++i) {
                this.sixiang[i].setVisible(false);
                this.hengtiaoxianshi[i].setVisible(false);
                this.shouhuBtn[i].setVisible(false);
            }
            if (loginResult.getSh() != null) {
                String[] mes = loginResult.getSh().split("\\|")[this.xuanzhong - 1].split("-");
                List<Mount> mountList = UserMessUntil.getMountlsit();
                if (mes[1].equals("1") || mes[1].equals("2") || mes[1].equals("3") || mes[1].equals("4") || mes[1].equals("5") || mes[1].equals("6") || mes[1].equals("7")) {
                    mountList.forEach(e/* org.come.entity.Mount, */ -> {
                        if ((int)e.getMountid() == Integer.parseInt(mes[1])) {
                            g.setColor(new Color(13221254));
                            g.drawString(" " + MountShouhuJpanel.CN_UPPER_NUMBER[(int)e.getMountid()] + "·" + e.getMountname().split(" ")[0], 350, 230);
                        }
                        return;
                    });
                }
                else if (mes[1].equals("0")) {
                    g.setColor(new Color(13221254));
                    g.drawString("待放入坐骑", 350, 230);
                }
                else if (mes[1].equals("-1")) {
                    g.setColor(new Color(13221254));
                    g.drawString("待解锁", 350, 230);
                }
                if (mes[2].equals("1") || mes[2].equals("2") || mes[2].equals("3") || mes[2].equals("4") || mes[2].equals("5") || mes[2].equals("6") || mes[2].equals("7")) {
                    mountList.forEach(e/* org.come.entity.Mount, */ -> {
                        if ((int)e.getMountid() == Integer.parseInt(mes[2])) {
                            g.setColor(new Color(13221254));
                            g.drawString(" " + MountShouhuJpanel.CN_UPPER_NUMBER[(int)e.getMountid()] + "·" + e.getMountname().split(" ")[0], 580, 230);
                        }
                        return;
                    });
                }
                else if (mes[2].equals("0")) {
                    g.setColor(new Color(13221254));
                    switch (this.xuanzhong) {
                        case 1: {
                            if (this.shouHu.getQinglonglvl() < 16) {
                                g.drawString("16级可添加", 580, 230);
                                break;
                            }
                            else {
                                g.drawString("待放入坐骑", 580, 230);
                                break;
                            }
                        }
                        case 2: {
                            if (this.shouHu.getBaihulvl() < 16) {
                                g.drawString("16级可添加", 580, 230);
                                break;
                            }
                            else {
                                g.drawString("待放入坐骑", 580, 230);
                                break;
                            }
                        }
                        case 3: {
                            if (this.shouHu.getXuanwulvl() < 16) {
                                g.drawString("16级可添加", 580, 230);
                                break;
                            }
                            else {
                                g.drawString("待放入坐骑", 580, 230);
                                break;
                            }
                        }
                        case 4: {
                            if (this.shouHu.getZhuquelvl() < 16) {
                                g.drawString("16级可添加", 580, 230);
                                break;
                            }
                            else {
                                g.drawString("待放入坐骑", 580, 230);
                                break;
                            }
                        }
                    }
                }
            }
            else {
                g.setColor(new Color(13221254));
                g.drawString("待解锁", 350, 230);
                g.drawString("16级可添加", 580, 230);
            }
        }
        else {
            for (int i = 0; i < 7; ++i) {
                this.sixiang[i].setVisible(true);
                this.hengtiaoxianshi[i].setVisible(true);
                this.hengtiaoxianshi[i].setType(2);
                if (i < 4) {
                    if (this.shouHu.zhongtianlvl >= i * 3 + 3) {
                        g.setColor(new Color(13221254));
                        if (loginResult.getJiesuo() != null && loginResult.getJiesuo().split("\\|").length == 7) {
                            String[] mes2 = loginResult.getJiesuo().split("\\|");
                            List<Mount> mountList2 = UserMessUntil.getMountlsit();
                            if (mes2[i].equals("0")) {
                                g.drawString(" 坐骑待选择", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                                this.sixiang[i].setIcon(this.xuanze1);
                                this.hengtiaoxianshi[i].setVisible(false);
                                this.shouhuBtn[i].setVisible(true);
                            }
                            else if (mes2[i].equals("-1")) {
                                g.drawString("  可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                                this.sixiang[i].setIcon(this.xuanze1);
                                this.hengtiaoxianshi[i].setIcon(this.hengtiao1);
                            }
                            else if (mes2[i].equals("1") || mes2[i].equals("2") || mes2[i].equals("3") || mes2[i].equals("4") || mes2[i].equals("5") || mes2[i].equals("6") || mes2[i].equals("7")) {
                                int finalI = i;
                                mountList2.forEach(element/* org.come.entity.Mount, */ -> {
                                    if (Integer.parseInt(mes2[finalI]) == (int)element.getMountid()) {
                                        g.drawString(" " + MountShouhuJpanel.CN_UPPER_NUMBER[Integer.parseInt(mes2[finalI])] + "·" + element.getMountname().split(" ")[0], this.sixiang[finalI].getX() - 10, this.sixiang[finalI].getY() + 90);
                                        if (element.getShouhu() != 0) {
                                            try {
                                                this.shouhuBtn[finalI].setIcons(MountShouhuJpanel.imageIcons1);
                                            }
                                            catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                        else {
                                            try {
                                                this.shouhuBtn[finalI].setIcons(MountShouhuJpanel.imageIcons2);
                                            }
                                            catch (Exception e3) {
                                                e3.printStackTrace();
                                            }
                                        }
                                    }
                                    return;
                                });
                                this.sixiang[i].setIcon(MountShouhuBtn.getimage(Integer.parseInt(mes2[i])));
                                this.hengtiaoxianshi[i].setVisible(false);
                                this.shouhuBtn[i].setVisible(true);
                            }
                            else {
                                g.drawString("  已解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                                this.sixiang[i].setIcon((Icon)MountShouhuJpanel.mount.get("50x50_" + Util.getRaceSting(loginResult.getSpecies_id()) + mes2[i]));
                                this.hengtiaoxianshi[i].setVisible(false);
                            }
                        }
                        else {
                            g.drawString("  可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                            this.sixiang[i].setIcon(this.xuanze1);
                            this.hengtiaoxianshi[i].setIcon(this.hengtiao1);
                        }
                    }
                    else {
                        g.setColor(new Color(9803157));
                        g.drawString(i * 3 + 3 + "级可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                    }
                }
                else if (this.shouHu.zhongtianlvl >= i * 4) {
                    g.setColor(new Color(13221254));
                    if (loginResult.getJiesuo() != null && loginResult.getJiesuo().split("\\|").length == 7) {
                        String[] mes2 = loginResult.getJiesuo().split("\\|");
                        List<Mount> mountList2 = UserMessUntil.getMountlsit();
                        if (mes2[i].equals("0")) {
                            g.drawString(" 坐骑待选择", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                            this.sixiang[i].setIcon(this.xuanze1);
                            this.hengtiaoxianshi[i].setVisible(false);
                            this.shouhuBtn[i].setVisible(true);
                        }
                        else if (mes2[i].equals("-1")) {
                            g.drawString("  可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                            this.sixiang[i].setIcon(this.xuanze1);
                            this.hengtiaoxianshi[i].setIcon(this.hengtiao1);
                        }
                        else if (mes2[i].equals("1") || mes2[i].equals("2") || mes2[i].equals("3") || mes2[i].equals("4") || mes2[i].equals("5") || mes2[i].equals("6") || mes2[i].equals("7")) {
                            int finalI = i;
                            mountList2.forEach(element/* org.come.entity.Mount, */ -> {
                                if (Integer.parseInt(mes2[finalI]) == (int)element.getMountid()) {
                                    g.drawString(" " + MountShouhuJpanel.CN_UPPER_NUMBER[Integer.parseInt(mes2[finalI])] + "·" + element.getMountname().split(" ")[0], this.sixiang[finalI].getX() - 10, this.sixiang[finalI].getY() + 90);
                                    if (element.getShouhu() != 0) {
                                        try {
                                            this.shouhuBtn[finalI].setIcons(MountShouhuJpanel.imageIcons1);
                                        }
                                        catch (Exception e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    else {
                                        try {
                                            this.shouhuBtn[finalI].setIcons(MountShouhuJpanel.imageIcons2);
                                        }
                                        catch (Exception e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                }
                                return;
                            });
                            this.sixiang[i].setIcon(MountShouhuBtn.getimage(Integer.parseInt(mes2[i])));
                            this.hengtiaoxianshi[i].setVisible(false);
                            this.shouhuBtn[i].setVisible(true);
                        }
                        else {
                            g.drawString("  已解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                            this.sixiang[i].setIcon((Icon)MountShouhuJpanel.mount.get("50x50_" + Util.getRaceSting(loginResult.getSpecies_id()) + mes2[i]));
                            this.hengtiaoxianshi[i].setVisible(false);
                        }
                    }
                    else {
                        g.drawString("  可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                        this.sixiang[i].setIcon(this.xuanze1);
                        this.hengtiaoxianshi[i].setIcon(this.hengtiao1);
                    }
                }
                else {
                    g.setColor(new Color(9803157));
                    g.drawString(i * 4 + "级可解锁", this.sixiang[i].getX() - 10, this.sixiang[i].getY() + 90);
                }
            }
            g.setColor(Color.black);
            g.drawString(loginResult.getShouhu() + "", 630, 38);
        }
    }
    
    public List<MountPanelBtn> getShouhu() {
        return this.shouhu;
    }
    
    public void setShouhu(List<MountPanelBtn> shouhu) {
        this.shouhu = shouhu;
    }
    
    public MountShouhuBtn getQinglong() {
        return this.qinglong;
    }
    
    public void setQinglong(MountShouhuBtn qinglong) {
        this.qinglong = qinglong;
    }
    
    public MountShouhuBtn getBaihu() {
        return this.baihu;
    }
    
    public void setBaihu(MountShouhuBtn baihu) {
        this.baihu = baihu;
    }
    
    public MountShouhuBtn getXuanwu() {
        return this.xuanwu;
    }
    
    public void setXuanwu(MountShouhuBtn xuanwu) {
        this.xuanwu = xuanwu;
    }
    
    public MountShouhuBtn getZhuque() {
        return this.zhuque;
    }
    
    public void setZhuque(MountShouhuBtn zhuque) {
        this.zhuque = zhuque;
    }
    
    public MountShouhuBtn getZhongtian() {
        return this.zhongtian;
    }
    
    public void setZhongtian(MountShouhuBtn zhongtian) {
        this.zhongtian = zhongtian;
    }
    
    public ImageIcon getIco() {
        return this.ico;
    }
    
    public void setIco(ImageIcon ico) {
        this.ico = ico;
    }
    
    public JLabel getSixiang1() {
        return this.sixiang1;
    }
    
    public void setSixiang1(JLabel sixiang1) {
        this.sixiang1 = sixiang1;
    }
    
    public JLabel getSixiang2() {
        return this.sixiang2;
    }
    
    public void setSixiang2(JLabel sixiang2) {
        this.sixiang2 = sixiang2;
    }
    
    public void setzonglan() {
    }
    
    public DefaultListModel<String> getZonglanmode() {
        return this.zonglanmode;
    }
    
    public void setZonglanmode(DefaultListModel<String> zonglanmode) {
        this.zonglanmode = zonglanmode;
    }
    
    public int getXuanzhong() {
        return this.xuanzhong;
    }
    
    public void setXuanzhong(int xuanzhong) {
        this.sixiang1.setIcon(CutButtonImage.getImage("inkImg/Client/可添加坐骑格.png", -1, -1));
        this.sixiang2.setIcon(CutButtonImage.getImage("inkImg/Client/可添加坐骑格.png", -1, -1));
        if (xuanzhong > 0) {
            if (Integer.parseInt(RoleData.getRoleData().getLoginResult().getSh().split("\\|")[xuanzhong - 1].split("-")[1]) != 0) {
                this.sixiang1.setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + Integer.parseInt(RoleData.getRoleData().getLoginResult().getSh().split("\\|")[xuanzhong - 1].split("-")[1]) + ".png"));
            }
            if (Integer.parseInt(RoleData.getRoleData().getLoginResult().getSh().split("\\|")[xuanzhong - 1].split("-")[2]) != 0) {
                this.sixiang2.setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + Integer.parseInt(RoleData.getRoleData().getLoginResult().getSh().split("\\|")[xuanzhong - 1].split("-")[2]) + ".png"));
            }
        }
        this.xuanzhong = xuanzhong;
    }
    
    public static Image icochange(String path) {
        File imageFile = new File(path);
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(imageFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (originalImage == null) {
            return null;
        }
        int maxSize = Math.max(originalImage.getWidth(), originalImage.getHeight());
        int scaledWidth = originalImage.getWidth() * 45 / maxSize;
        int scaledHeight = originalImage.getHeight() * 45 / maxSize;
        return originalImage.getScaledInstance(scaledWidth, scaledHeight, 4);
    }
    
    static {
        MountShouhuJpanel.zonglanidx = -1;
        MountShouhuJpanel.shuxingidx = -1;
        MountShouhuJpanel.zonglan = new ArrayList<>();
        MountShouhuJpanel.shuxing = new ArrayList<>();
        MountShouhuJpanel.shuxingmap = new HashMap<>();
        MountShouhuJpanel.mount = new HashMap<>();
        CN_UPPER_NUMBER = new char[] { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
        List<String> zhuque = new ArrayList<>();
        zhuque.add("1级 法力 +500");
        zhuque.add("2级 法力 +0.5%");
        zhuque.add("3级 气血 +500");
        zhuque.add("4级 忽视仙法鬼火+1.0%");
        zhuque.add("5级 法力 +500");
        zhuque.add("6级 法力 +0.5%");
        zhuque.add("7级 气血 +500");
        zhuque.add("8级 仙法鬼火命中率+0.5%");
        zhuque.add("9级 法力 +500");
        zhuque.add("10级 法力 +0.5%");
        zhuque.add("11级 气血 +500");
        zhuque.add("12级 忽视抗仙法鬼火+1.0%");
        zhuque.add("13级 法力 +500");
        zhuque.add("14级 法力 +0.5%");
        zhuque.add("15级 气血 +500");
        zhuque.add("16级 仙法鬼火命中率+0.5%");
        zhuque.add("17级 法力 +500");
        zhuque.add("18级 法力 +0.5%");
        zhuque.add("19级 气血 +500");
        zhuque.add("20级 忽视仙法鬼火+1.0%");
        zhuque.add("21级 法力 +500");
        zhuque.add("22级 法力 +0.5%");
        zhuque.add("23级 气血 +500");
        zhuque.add("24级 仙法鬼火命中率+0.5%");
        MountShouhuJpanel.shuxingmap.put("朱雀", zhuque);
        List<String> qinglong = new ArrayList<>();
        qinglong.add("1级 速度 +2");
        qinglong.add("2级 物理吸收率+0.5%");
        qinglong.add("3级 气血 +500");
        qinglong.add("4级 速度 +0.2%");
        qinglong.add("5级 速度 +2");
        qinglong.add("6级 物理吸收率+0.5%");
        qinglong.add("7级 法力 +500");
        qinglong.add("8级 法术躲闪几率+0.5%");
        qinglong.add("9级 速度 +2");
        qinglong.add("10级 物理吸收率+0.5%");
        qinglong.add("11级 气血 +500");
        qinglong.add("12级 速度 +0.2%");
        qinglong.add("13级 速度 +2");
        qinglong.add("14级 物理吸收率+0.5%");
        qinglong.add("15级 法力 +500");
        qinglong.add("16级 法术躲闪几率+0.5%");
        qinglong.add("17级 速度 +2");
        qinglong.add("18级 物理吸收率+0.5%");
        qinglong.add("19级 气血 +500");
        qinglong.add("20级 速度 +0.2%");
        qinglong.add("21级 速度 +2");
        qinglong.add("22级 物理吸收率+0.5%");
        qinglong.add("23级 法力 +500");
        qinglong.add("24级 法术躲闪几率+0.5%");
        MountShouhuJpanel.shuxingmap.put("青龙", qinglong);
        List<String> xuanwu = new ArrayList<>();
        xuanwu.add("1级 气血 +500");
        xuanwu.add("2级 气血 +0.5%");
        xuanwu.add("3级 法力 +500");
        xuanwu.add("4级 抗致命率+1.0%");
        xuanwu.add("5级 气血 +500");
        xuanwu.add("6级 气血 +0.5%");
        xuanwu.add("7级 法力 +500");
        xuanwu.add("8级 抗仙法鬼火狂暴率+1.0%");
        xuanwu.add("9级 气血 +500");
        xuanwu.add("10级 攻击 +0.5%");
        xuanwu.add("11级 法力 +500");
        xuanwu.add("12级 抗致命率+1.0%");
        xuanwu.add("13级 气血 +500");
        xuanwu.add("14级 气血 +0.5%");
        xuanwu.add("15级 法力 +500");
        xuanwu.add("16级 抗仙法鬼火狂暴率+1.0%");
        xuanwu.add("17级 气血 +500");
        xuanwu.add("18级 气血 +0.5%");
        xuanwu.add("19级 法力 +500");
        xuanwu.add("20级 抗致命率+1.0%");
        xuanwu.add("21级 气血 +500");
        xuanwu.add("22级 气血 +0.5%");
        xuanwu.add("23级 法力 +500");
        xuanwu.add("24级 抗仙法鬼火狂暴率+1.0%");
        MountShouhuJpanel.shuxingmap.put("玄武", xuanwu);
        List<String> baihu = new ArrayList<>();
        baihu.add("1级 攻击 +200");
        baihu.add("2级 攻击 +0.5%");
        baihu.add("3级 气血 +500");
        baihu.add("4级 狂暴率+1.0%");
        baihu.add("5级 攻击 +200");
        baihu.add("6级 攻击 +0.5%");
        baihu.add("7级 气血 +500");
        baihu.add("8级 命中率+1.0%");
        baihu.add("9级 攻击 +200");
        baihu.add("10级 攻击 +0.5%");
        baihu.add("11级 气血 +500");
        baihu.add("12级 狂暴率+1.0%");
        baihu.add("13级 攻击 +200");
        baihu.add("14级 攻击 +0.5%");
        baihu.add("15级 气血 +500");
        baihu.add("16级 命中率+1.0%");
        baihu.add("17级 攻击 +200");
        baihu.add("18级 攻击 +0.5%");
        baihu.add("19级 气血 +500");
        baihu.add("20级 狂暴率+1.0%");
        baihu.add("21级 攻击 +200");
        baihu.add("22级 攻击 +0.5%");
        baihu.add("23级 气血 +500");
        baihu.add("24级 命中率+1.0%");
        MountShouhuJpanel.shuxingmap.put("白虎", baihu);
        for (int i = 1; i <= 7; ++i) {
            ImageIcon imageIcon = new ImageIcon("inkImg/Client/zqhead/50x50_鬼" + i + ".png");
            ImageIcon imageIcon2 = new ImageIcon("inkImg/Client/zqhead/50x50_龙" + i + ".png");
            ImageIcon imageIcon3 = new ImageIcon("inkImg/Client/zqhead/50x50_魔" + i + ".png");
            ImageIcon imageIcon4 = new ImageIcon("inkImg/Client/zqhead/50x50_仙" + i + ".png");
            ImageIcon imageIcon5 = new ImageIcon("inkImg/Client/zqhead/50x50_人" + i + ".png");
            MountShouhuJpanel.mount.put("50x50_鬼" + i, imageIcon);
            MountShouhuJpanel.mount.put("50x50_龙" + i, imageIcon2);
            MountShouhuJpanel.mount.put("50x50_魔" + i, imageIcon3);
            MountShouhuJpanel.mount.put("50x50_仙" + i, imageIcon4);
            MountShouhuJpanel.mount.put("50x50_人" + i, imageIcon5);
        }
        try {
            MountShouhuJpanel.imageIcons1 = CutButtonImage.cuts("inkImg/Client/守护石圆圈带守护石.png");
            MountShouhuJpanel.imageIcons2 = CutButtonImage.cuts("inkImg/Client/守护石圆圈带未点亮守护石.png");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static class CustomScrollBarUI extends BasicScrollBarUI
    {
        @Override
        protected void configureScrollBarColors() {
            this.trackColor = Color.black;
            this.setThumbBounds(0, 0, 3, 10);
        }
        
        @Override
        public Dimension getPreferredSize(JComponent c) {
            c.setPreferredSize(new Dimension(10, 0));
            return super.getPreferredSize(c);
        }
        
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(0, 0);
                }
            };
        }
        
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(0, 0);
                }
            };
        }
        
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D)g;
            GradientPaint gp = null;
            if (this.scrollbar.getOrientation() == 1) {
                gp = new GradientPaint(0.0f, 0.0f, new Color(0, 0, 0), (float)trackBounds.width, 0.0f, new Color(8351321));
            }
            g2.setPaint(gp);
            g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            if (this.trackHighlight == 1) {
                this.paintDecreaseHighlight(g);
            }
            if (this.trackHighlight == 2) {
                this.paintIncreaseHighlight(g);
            }
        }
        
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.translate(thumbBounds.x, thumbBounds.y);
            g.setColor(new Color(250, 250, 250));
            Graphics2D g2 = (Graphics2D)g;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.addRenderingHints(rh);
            g2.setComposite(AlphaComposite.getInstance(3, 0.1f));
            g2.fillRoundRect(0, 0, 20, thumbBounds.height - 1, 5, 5);
        }
    }
}
