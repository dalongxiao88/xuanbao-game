package org.come.login;

import org.come.until.CutButtonImage;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.tool.image.Creepsskin;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.Util;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.Music;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.come.view.View;

public class CreateView extends View
{
    public static int Nbh;
    private SpriteBtn[] leibtns;
    private SpriteBtn[] rolebtns;
    private SpriteBtn creat;
    private SpriteBtn[] creatbtns;
    private SpriteBtn fanghui;
    private SpriteBtn shurumb;
    private SpriteBtn trait;
    private SpriteBtn roleimg;
    private SpriteBtn roletext;
    private String[] Weapons;
    private final String[] Actions;
    private final String[] roleren;
    private final String[] rolemo;
    private final String[] rolexian;
    private final String[] rolegui;
    private final String[] rolelong;
    private final String[] Mounts;
    private final String[] Fashions;
    private final String[] dirs;
    public static JTextField textAccount;
    private JLabel Weapon;
    private JLabel Action;
    private JLabel Mount;
    private JLabel Fashion;
    private SpriteBtn suiji;
    public JTextArea textArea;
    private ImageIcon beijing;
    private ImageIcon beijing2;
    private ImageIcon bejing3;
    private final ImageIcon buttonBg;
    private String msg;
    private NewPart part;
    private long time;
    private int dir;
    public String s;
    public static String zzs;
    private final JLabel renzu;
    private final JLabel mozu;
    private final JLabel xianzu;
    private final JLabel guizu;
    private final JLabel longzu;
    private ImageIcon nameBG;
    private SpriteBtn icon;
    private String checked;
    private final Sprite tcp1;
    
    public CreateView(LoginJpanel loginJpanel) {
        this(loginJpanel, 0);
    }
    
    public CreateView(LoginJpanel loginJpanel, int v) {
        this.Actions = new String[] { "站立=stand", "跑=run", "走=walk", "防御=defend", "物理攻击=attack", "法术攻击=magic", "被攻击=hit", "死亡=die", "攻击跑=attackrun", "进入战斗=guard" };
        this.roleren = new String[] { "逍遥生", "剑侠客", "猛壮士", "俏千金", "飞燕女", "英女侠", "神秀生", "玲珑女" };
        this.rolemo = new String[] { "巨魔王", "夺命妖", "虎头怪", "狐美人", "骨精灵", "小蛮妖", "绝影魔", "霜月灵" };
        this.rolexian = new String[] { "神天兵", "龙战将", "智圣仙", "舞天姬", "精灵仙", "玄剑娥", "青阳使", "云中君" };
        this.rolegui = new String[] { "猎魂引", "无崖子", "祭剑魂", "夜溪灵", "幽梦影", "墨衣行", "南冠客", "镜花影" };
        this.rolelong = new String[] { "沧浪君", "龙渊客", "忘忧子", "骊珠儿", "木兰行", "莫解语", "游无极", "临九渊" };
        this.Mounts = new String[] { "无", "一坐骑", "二坐骑", "三坐骑", "四坐骑", "五坐骑", "六坐骑", "七坐骑" };
        this.Fashions = new String[] { "无" };
        this.dirs = new String[] { "4", "0", "7", "3", "6", "2", "5", "1" };
        this.time = 0L;
        this.dir = 4;
        this.tcp1 = SpriteFactory.VloadSprite("resource/xinUI/xin/jsxq", null);
        Music.beijing(false);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String lzkg = configure.getLzjskg();
        this.time = 0L;
        this.dir = 4;
        this.setBounds(0, 0, 1027, 720);
        this.buttonBg = new ImageIcon("resource/xinUI/xin/buttonBg.png");
        (this.fanghui = new SpriteBtn("resource/xinUI/xin/返回按钮", 5, 680, false)).setBounds(5, 680, 60, 30);
        this.fanghui.addMouseListener(new RoleMouslisten(11, this.fanghui, loginJpanel));
        this.add(this.fanghui);
        (this.suiji = new SpriteBtn("resource/xinUI/骰子2", 420, 569, false)).setBounds(420, 569, 24, 31);
        this.suiji.addMouseListener(new RoleMouslisten(15, this.suiji, loginJpanel));
        this.add(this.suiji);
        this.creat = new SpriteBtn("resource/xinUI/xin/展示面板", 919, 550, false);
        (this.Weapon = new JLabel()).setText("无");
        this.Weapon.setHorizontalAlignment(0);
        this.Weapon.setFont(UIUtils.TEXT_FONT);
        this.Weapon.setForeground(new Color(91, 79, 64));
        this.Weapon.setBounds(941, 586, 70, 25);
        this.add(this.Weapon);
        (this.Mount = new JLabel()).setText("坐骑一");
        this.Mount.setHorizontalAlignment(0);
        this.Mount.setFont(UIUtils.TEXT_FONT);
        this.Mount.setForeground(new Color(91, 79, 64));
        this.Mount.setBounds(941, 606, 70, 25);
        this.add(this.Mount);
        (this.Action = new JLabel()).setText("无");
        this.Action.setHorizontalAlignment(0);
        this.Action.setFont(UIUtils.TEXT_FONT);
        this.Action.setForeground(new Color(91, 79, 64));
        this.Action.setBounds(941, 566, 70, 25);
        this.add(this.Action);
        (this.Fashion = new JLabel()).setText("无");
        this.Fashion.setHorizontalAlignment(0);
        this.Fashion.setFont(UIUtils.TEXT_FONT);
        this.Fashion.setForeground(new Color(91, 79, 64));
        this.Fashion.setBounds(941, 546, 70, 25);
        this.add(this.Fashion);
        if (lzkg.equals("5")) {
            this.leibtns = new SpriteBtn[5];
            (this.renzu = new JLabel()).setBounds(-7, 223, 80, 26);
            this.renzu.setForeground(new Color(255, 255, 255));
            this.renzu.setFont(UIUtils.HYXLSJ15);
            this.renzu.setHorizontalAlignment(0);
            this.renzu.setText("人族");
            this.add(this.renzu);
            (this.mozu = new JLabel()).setBounds(-17, 295, 100, 26);
            this.mozu.setForeground(new Color(255, 255, 255));
            this.mozu.setFont(UIUtils.HYXLSJ15);
            this.mozu.setHorizontalAlignment(0);
            this.mozu.setText("魔族");
            this.add(this.mozu);
            (this.xianzu = new JLabel()).setBounds(-17, 365, 100, 26);
            this.xianzu.setForeground(new Color(255, 255, 255));
            this.xianzu.setFont(UIUtils.HYXLSJ15);
            this.xianzu.setHorizontalAlignment(0);
            this.xianzu.setText("仙族");
            this.add(this.xianzu);
            (this.guizu = new JLabel()).setBounds(-17, 435, 100, 26);
            this.guizu.setForeground(new Color(255, 255, 255));
            this.guizu.setFont(UIUtils.HYXLSJ15);
            this.guizu.setHorizontalAlignment(0);
            this.guizu.setText("鬼族");
            this.add(this.guizu);
            (this.longzu = new JLabel()).setBounds(-17, 508, 100, 26);
            this.longzu.setForeground(new Color(255, 255, 255));
            this.longzu.setFont(UIUtils.HYXLSJ15);
            this.longzu.setHorizontalAlignment(0);
            this.longzu.setText("龙族");
            this.add(this.longzu);
            for (int i = 0; i < this.leibtns.length; ++i) {
                if (i == 0) {
                    (this.leibtns[0] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", -1, 169, true, 1, 70, 70)).setBounds(-1, 169, 65, 65);
                }
                if (i == 1) {
                    (this.leibtns[1] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 240, true, 1, 70, 70)).setBounds(0, 240, 65, 65);
                }
                if (i == 2) {
                    (this.leibtns[2] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 313, true, 1, 70, 70)).setBounds(0, 313, 65, 65);
                }
                if (i == 3) {
                    (this.leibtns[3] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 384, true, 1, 70, 70)).setBounds(0, 384, 65, 65);
                }
                if (i == 4) {
                    (this.leibtns[4] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 457, true, 1, 70, 70)).setBounds(0, 457, 65, 65);
                }
                this.leibtns[i].addMouseListener(new RaceMouslistenn(i, this.leibtns[i], loginJpanel, 1));
                this.add(this.leibtns[i]);
            }
        }
        else if (lzkg.equals("4")) {
            this.leibtns = new SpriteBtn[4];
            (this.renzu = new JLabel()).setBounds(-7, 223, 80, 26);
            this.renzu.setForeground(new Color(255, 255, 255));
            this.renzu.setFont(UIUtils.HYXLSJ15);
            this.renzu.setHorizontalAlignment(0);
            this.renzu.setText("人族");
            this.add(this.renzu);
            (this.mozu = new JLabel()).setBounds(-17, 295, 100, 26);
            this.mozu.setForeground(new Color(255, 255, 255));
            this.mozu.setFont(UIUtils.HYXLSJ15);
            this.mozu.setHorizontalAlignment(0);
            this.mozu.setText("魔族");
            this.add(this.mozu);
            (this.xianzu = new JLabel()).setBounds(-17, 365, 100, 26);
            this.xianzu.setForeground(new Color(255, 255, 255));
            this.xianzu.setFont(UIUtils.HYXLSJ15);
            this.xianzu.setHorizontalAlignment(0);
            this.xianzu.setText("仙族");
            this.add(this.xianzu);
            (this.guizu = new JLabel()).setBounds(-17, 435, 100, 26);
            this.guizu.setForeground(new Color(255, 255, 255));
            this.guizu.setFont(UIUtils.HYXLSJ15);
            this.guizu.setHorizontalAlignment(0);
            this.guizu.setText("鬼族");
            this.add(this.guizu);
            (this.longzu = new JLabel()).setBounds(-17, 508, 100, 26);
            this.longzu.setForeground(new Color(255, 255, 255));
            this.longzu.setFont(UIUtils.HYXLSJ15);
            this.longzu.setHorizontalAlignment(0);
            this.longzu.setText("龙族");
            for (int i = 0; i < this.leibtns.length; ++i) {
                if (i == 0) {
                    (this.leibtns[0] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", -1, 169, true, 1, 70, 70)).setBounds(-1, 169, 65, 65);
                }
                if (i == 1) {
                    (this.leibtns[1] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 240, true, 1, 70, 70)).setBounds(0, 240, 65, 65);
                }
                if (i == 2) {
                    (this.leibtns[2] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 313, true, 1, 70, 70)).setBounds(0, 313, 65, 65);
                }
                if (i == 3) {
                    (this.leibtns[3] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 384, true, 1, 70, 70)).setBounds(0, 384, 65, 65);
                }
                this.leibtns[i].addMouseListener(new RaceMouslistenn(i, this.leibtns[i], loginJpanel, 1));
                this.add(this.leibtns[i]);
            }
        }
        else {
            this.leibtns = new SpriteBtn[3];
            (this.renzu = new JLabel()).setBounds(-7, 223, 80, 26);
            this.renzu.setForeground(new Color(255, 255, 255));
            this.renzu.setFont(UIUtils.HYXLSJ15);
            this.renzu.setHorizontalAlignment(0);
            this.renzu.setText("人族");
            this.add(this.renzu);
            (this.mozu = new JLabel()).setBounds(-17, 295, 100, 26);
            this.mozu.setForeground(new Color(255, 255, 255));
            this.mozu.setFont(UIUtils.HYXLSJ15);
            this.mozu.setHorizontalAlignment(0);
            this.mozu.setText("魔族");
            this.add(this.mozu);
            (this.xianzu = new JLabel()).setBounds(-17, 365, 100, 26);
            this.xianzu.setForeground(new Color(255, 255, 255));
            this.xianzu.setFont(UIUtils.HYXLSJ15);
            this.xianzu.setHorizontalAlignment(0);
            this.xianzu.setText("仙族");
            this.add(this.xianzu);
            (this.guizu = new JLabel()).setBounds(-17, 435, 100, 26);
            this.guizu.setForeground(new Color(255, 255, 255));
            this.guizu.setFont(UIUtils.HYXLSJ15);
            this.guizu.setHorizontalAlignment(0);
            this.guizu.setText("鬼族");
            (this.longzu = new JLabel()).setBounds(-17, 508, 100, 26);
            this.longzu.setForeground(new Color(255, 255, 255));
            this.longzu.setFont(UIUtils.HYXLSJ15);
            this.longzu.setHorizontalAlignment(0);
            this.longzu.setText("龙族");
            for (int i = 0; i < this.leibtns.length; ++i) {
                if (i == 0) {
                    (this.leibtns[0] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", -1, 169, true, 1, 70, 70)).setBounds(-1, 169, 65, 65);
                }
                if (i == 1) {
                    (this.leibtns[1] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 240, true, 1, 70, 70)).setBounds(0, 240, 65, 65);
                }
                if (i == 2) {
                    (this.leibtns[2] = new SpriteBtn("resource/xinUI/xin/" + LoginJpanel.leipath(i) + "族按钮", 0, 313, true, 1, 70, 70)).setBounds(0, 313, 65, 65);
                }
                this.leibtns[i].addMouseListener(new RaceMouslistenn(i, this.leibtns[i], loginJpanel, 1));
                this.add(this.leibtns[i]);
            }
        }
        (CreateView.textAccount = new JTextField()).setBounds(460, 573, 156, 23);
        CreateView.textAccount.setOpaque(false);
        CreateView.textAccount.setBorder(BorderFactory.createEmptyBorder());
        CreateView.textAccount.setForeground(Color.WHITE);
        CreateView.textAccount.setFont(UIUtils.TEXT_FONT1);
        CreateView.textAccount.setCaretColor(Color.WHITE);
        CreateView.textAccount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (Util.special(CreateView.textAccount.getText())) {
                    if (CreateView.textAccount.getText().getBytes().length <= 18) {
                        CreateView.this.msg = null;
                    }
                    else {
                        CreateView.this.msg = "角色名字超长了!!！";
                    }
                }
                else {
                    CreateView.this.msg = "角色名字不能包含特殊字符!!";
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Util.special(CreateView.textAccount.getText())) {
                    if (CreateView.textAccount.getText().getBytes().length <= 18) {
                        CreateView.this.msg = null;
                    }
                    else {
                        CreateView.this.msg = "角色名字超长了!!！";
                    }
                }
                else {
                    CreateView.this.msg = "角色名字不能包含特殊字符!!";
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        CreateView.textAccount.setVisible(true);
        this.add(CreateView.textAccount);
        this.creatbtns = new SpriteBtn[11];
        for (int i = 0; i < this.creatbtns.length; ++i) {
            if (i < 8) {
                if (i % 2 == 0) {
                    if (i == 0) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向左", 901, 590, false)).setBounds(911, 590, 20, 20);
                    }
                    if (i == 2) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向左", 901, 610, false)).setBounds(911, 610, 20, 20);
                    }
                    if (i == 4) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向左", 901, 570, false)).setBounds(911, 570, 20, 20);
                    }
                    if (i == 6) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向左", 901, 550, false)).setBounds(911, 550, 20, 20);
                    }
                }
                else {
                    if (i == 1) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向右", 995, 589, false)).setBounds(1005, 589, 20, 20);
                    }
                    if (i == 3) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向右", 995, 609, false)).setBounds(1005, 609, 20, 20);
                    }
                    if (i == 5) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向右", 995, 569, false)).setBounds(1005, 569, 20, 20);
                    }
                    if (i == 7) {
                        (this.creatbtns[i] = new SpriteBtn("resource/xinUI/xin/向右", 995, 549, false)).setBounds(1005, 549, 20, 20);
                    }
                }
            }
            else if (i == 8) {
                (this.creatbtns[i] = new SpriteBtn("resource/xinUI/方向向左新", 430, 465, false)).setBounds(430, 465, 37, 32);
            }
            else if (i == 9) {
                (this.creatbtns[i] = new SpriteBtn("resource/xinUI/方向向右新", 609, 465, false)).setBounds(609, 465, 30, 32);
            }
            else if (i == 10) {
                (this.creatbtns[i] = new SpriteBtn("resource/xinUI/进入1", 619, 569, false)).setBounds(609, 570, 83, 30);
            }
            this.creatbtns[i].addMouseListener(new RoleMouslisten(i, this.creatbtns[i], loginJpanel));
            this.add(this.creatbtns[i]);
        }
        this.xzRace(v, loginJpanel);
        this.xzRole(0);
    }
    
    public void xzRace(int v, LoginJpanel loginJpanel) {
        LoginJpanel.quxiao(this.leibtns, v);
        String race = LoginJpanel.leipath(v);
        this.beijing = new ImageIcon("resource/xinUI/xin/" + race + ".png");
        this.beijing2 = new ImageIcon("resource/xinUI/xin/beijing2.png");
        this.bejing3 = new ImageIcon("resource/xinUI/xin/3BAF0C86.png");
        this.icon = new SpriteBtn("resource/xinUI/xin/" + race + "族图标", 725, 158, false);
        this.trait = new SpriteBtn("resource/xinUI/xin/" + race + "族特点", 350, 100, false);
        if (this.rolebtns == null) {
            this.rolebtns = new SpriteBtn[6];
        }
        for (int i = 0; i < this.rolebtns.length; ++i) {
            if (this.rolebtns[i] == null) {
                (this.rolebtns[i] = new SpriteBtn("resource/xinUI/xin/角色按钮_" + this.rolename(race, i), 63, 147 + i * 67, true, 1, 120, 120)).setBounds(63, 147 + i * 67, 92, 91);
                this.rolebtns[i].addMouseListener(new CreateMouslisten(i, this.rolebtns[i], loginJpanel));
                this.add(this.rolebtns[i]);
            }
            else {
                this.rolebtns[i].setSprite("resource/xinUI/xin/角色按钮_" + this.rolename(race, i));
            }
        }
    }
    
    public void xzRole(int zhi) {
        LoginJpanel.quxiao(this.rolebtns, zhi);
        this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 655, 0, false);
        if (!this.SelectedLei().equals("鬼")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 655, 0, false);
        }
        if (!this.SelectedLei().equals("龙")) {
            this.roleimg = new SpriteBtn("resource/xinUI/xin/角色图片_" + this.rolename(this.SelectedLei(), zhi), 460, 0, false);
        }
        else if (this.roleimg == null || this.roleimg.getSx() != 0) {
            this.roleimg = new SpriteBtn("resource/xinUI/xin/0", 0, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("沧浪君")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 600, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("夺命妖")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 630, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("狐美人") || this.rolename(this.SelectedLei(), zhi).equals("骨精灵")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 595, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("猎魂引") || this.rolename(this.SelectedLei(), zhi).equals("墨衣行")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 605, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("幽梦影")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 600, 0, false);
        }
        if (this.rolename(this.SelectedLei(), zhi).equals("无崖子") || this.rolename(this.SelectedLei(), zhi).equals("祭剑魂") || this.rolename(this.SelectedLei(), zhi).equals("夜溪灵")) {
            this.roletext = new SpriteBtn("resource/xinUI/xin/角色描述_" + this.rolename(this.SelectedLei(), zhi), 595, 0, false);
        }
        this.GetWeapons(this.SelectedRole());
        this.ModifyAction(0, -1);
        this.ModifyMount(0, -1);
        this.ModifyWeapon(0, -1);
        this.ModifyFashion(0, -1);
        this.roletcp();
    }
    
    public String rolename(String lei, int i) {
        if (lei.equals("人")) {
            return this.roleren[i];
        }
        if (lei.equals("魔")) {
            return this.rolemo[i];
        }
        if (lei.equals("仙")) {
            return this.rolexian[i];
        }
        if (lei.equals("鬼")) {
            return this.rolegui[i];
        }
        return this.rolelong[i];
    }
    
    public void roletcp() {
        if (!this.GetdqMount().equals("无")) {
            BigDecimal se = new BigDecimal(this.SelectedRoleID());
            int mountId = 1;
            if (this.GetdqMount().startsWith("二")) {
                mountId = 2;
            }
            else if (this.GetdqMount().startsWith("三")) {
                mountId = 3;
            }
            else if (this.GetdqMount().startsWith("四")) {
                mountId = 4;
            }
            else if (this.GetdqMount().startsWith("五")) {
                mountId = 5;
            }
            else if (this.GetdqMount().startsWith("六")) {
                mountId = 6;
            }
            else if (this.GetdqMount().startsWith("七")) {
                mountId = 7;
            }
            this.part = SpriteFactory.createPart((long)mountId << 40 | se.longValue(), this.GetdqActionIntTwo(), 1, (String)null);
        }
        else {
            String weapon = this.GetdqWeapon();
            long type = 0L;
            if (weapon.equals("剑")) {
                type = 1L;
            }
            else if (weapon.equals("扇")) {
                type = 2L;
            }
            else if (weapon.equals("锤")) {
                type = 3L;
            }
            else if (weapon.equals("斧头")) {
                type = 4L;
            }
            else if (weapon.equals("拳套")) {
                type = 5L;
            }
            else if (weapon.equals("书")) {
                type = 6L;
            }
            else if (weapon.equals("棍")) {
                type = 7L;
            }
            else if (weapon.equals("鞭")) {
                type = 8L;
            }
            else if (weapon.equals("钩")) {
                type = 9L;
            }
            else if (weapon.equals("刀")) {
                type = 10L;
            }
            else if (weapon.equals("双环")) {
                type = 11L;
            }
            else if (weapon.equals("枪")) {
                type = 12L;
            }
            else if (weapon.equals("幡")) {
                type = 13L;
            }
            else if (weapon.equals("爪")) {
                type = 14L;
            }
            else if (weapon.equals("浮尘")) {
                type = 15L;
            }
            else if (weapon.equals("飘带")) {
                type = 16L;
            }
            else if (weapon.equals("灯笼")) {
                type = 17L;
            }
            else if (weapon.equals("弓")) {
                type = 18L;
            }
            this.part = SpriteFactory.createPart(type << 32 | (long)this.SelectedRoleID(), this.GetdqActionIntTwo(), 1, (String)null);
        }
    }
    
    public void ModifyAction(int zhi, int sx) {
        if (zhi != -1) {
            this.Action.setText(this.Actions[zhi].split("=")[0]);
        }
        else if (this.GetdqMount().equals("无")) {
            this.Action.setText(this.getshuzu(this.Actions, this.Actions[this.GetdqActionInt()], sx).split("=")[0]);
        }
        else {
            String[] zhu = { "站立=stand", "跑=run" };
            this.Action.setText(this.getshuzu(zhu, this.Actions[this.GetdqActionInt()], sx).split("=")[0]);
        }
    }
    
    public void ModifyMount(int zhi, int sx) {
        if (zhi != -1) {
            this.Mount.setText(this.Mounts[zhi]);
            if (!this.Mount.getText().equals("无")) {
                this.ModifyAction(0, -1);
            }
        }
        else {
            this.Mount.setText(this.getshuzu(this.Mounts, this.Mount.getText(), sx));
            if (!this.Mount.getText().equals("无")) {
                this.ModifyAction(0, -1);
            }
        }
    }
    
    public void ModifyWeapon(int zhi, int sx) {
        if (zhi != -1) {
            this.Weapon.setText(this.Weapons[zhi]);
        }
        else {
            this.Weapon.setText(this.getshuzu(this.Weapons, this.Weapon.getText(), sx));
        }
    }
    
    public void ModifyFashion(int zhi, int sx) {
    }
    
    public void ModifyDir(int zhi, int sx) {
        if (zhi != -1) {
            this.dir = Integer.parseInt(this.dirs[zhi]);
        }
        else {
            this.dir = Integer.parseInt(this.getshuzu(this.dirs, this.getdir(), sx));
        }
    }
    
    public String getshuzu(String[] zhu, String zhi, int sx) {
        int weizhi = 0;
        int i = 0;
        while (i < zhu.length) {
            if (zhu[i].equals(zhi)) {
                weizhi = i;
                break;
            }
            else {
                ++i;
            }
        }
        if (sx == 0) {
            --weizhi;
        }
        else {
            ++weizhi;
        }
        if (weizhi < 0) {
            weizhi = zhu.length - 1;
        }
        else if (weizhi > zhu.length - 1) {
            weizhi = 0;
        }
        return zhu[weizhi];
    }
    
    public void GetWeapons(String rolename) {
        if (rolename.equals("纯阳子")) {
            this.Weapons = new String[] { "扇", "书" };
        }
        else if (rolename.equals("夺命妖")) {
            this.Weapons = new String[] { "刀", "幡" };
        }
        else if (rolename.equals("飞剑侠")) {
            this.Weapons = new String[] { "剑", "拳套" };
        }
        else if (rolename.equals("飞燕女")) {
            this.Weapons = new String[] { "鞭", "钩" };
        }
        else if (rolename.equals("骨精灵")) {
            this.Weapons = new String[] { "剑", "棍" };
        }
        else if (rolename.equals("红拂女")) {
            this.Weapons = new String[] { "剑", "鞭" };
        }
        else if (rolename.equals("狐美人")) {
            this.Weapons = new String[] { "爪", "鞭" };
        }
        else if (rolename.equals("虎头怪")) {
            this.Weapons = new String[] { "棍", "枪" };
        }
        else if (rolename.equals("混天魔")) {
            this.Weapons = new String[] { "刀", "斧头" };
        }
        else if (rolename.equals("祭剑魂")) {
            this.Weapons = new String[] { "刀", "剑" };
        }
        else if (rolename.equals("剑侠客")) {
            this.Weapons = new String[] { "锤", "剑" };
        }
        else if (rolename.equals("精灵仙")) {
            this.Weapons = new String[] { "刀", "拳套" };
        }
        else if (rolename.equals("镜花影")) {
            this.Weapons = new String[] { "剑", "飘带" };
        }
        else if (rolename.equals("九尾狐")) {
            this.Weapons = new String[] { "钩", "爪" };
        }
        else if (rolename.equals("巨魔王")) {
            this.Weapons = new String[] { "刀", "枪" };
        }
        else if (rolename.equals("绝影魔")) {
            this.Weapons = new String[] { "刀", "枪" };
        }
        else if (rolename.equals("猎魂引")) {
            this.Weapons = new String[] { "枪", "拳套" };
        }
        else if (rolename.equals("玲珑女")) {
            this.Weapons = new String[] { "鞭", "刀" };
        }
        else if (rolename.equals("龙战将")) {
            this.Weapons = new String[] { "棍", "爪" };
        }
        else if (rolename.equals("媚灵狐")) {
            this.Weapons = new String[] { "刀", "双环" };
        }
        else if (rolename.equals("猛壮士")) {
            this.Weapons = new String[] { "斧头", "拳套" };
        }
        else if (rolename.equals("墨衣行")) {
            this.Weapons = new String[] { "鞭", "钩" };
        }
        else if (rolename.equals("南冠客")) {
            this.Weapons = new String[] { "刀", "剑" };
        }
        else if (rolename.equals("霓裳仙")) {
            this.Weapons = new String[] { "灯笼", "飘带" };
        }
        else if (rolename.equals("逆天魔")) {
            this.Weapons = new String[] { "斧头", "棍" };
        }
        else if (rolename.equals("俏千金")) {
            this.Weapons = new String[] { "刀", "枪" };
        }
        else if (rolename.equals("青阳使")) {
            this.Weapons = new String[] { "枪", "棍" };
        }
        else if (rolename.equals("神天兵")) {
            this.Weapons = new String[] { "锤", "枪" };
        }
        else if (rolename.equals("神秀生")) {
            this.Weapons = new String[] { "棍", "剑" };
        }
        else if (rolename.equals("霜月灵")) {
            this.Weapons = new String[] { "刀", "钩" };
        }
        else if (rolename.equals("无崖子")) {
            this.Weapons = new String[] { "幡", "书" };
        }
        else if (rolename.equals("武尊神")) {
            this.Weapons = new String[] { "枪", "爪" };
        }
        else if (rolename.equals("舞天姬")) {
            this.Weapons = new String[] { "棍", "飘带" };
        }
        else if (rolename.equals("逍遥生")) {
            this.Weapons = new String[] { "剑", "扇" };
        }
        else if (rolename.equals("小蛮妖")) {
            this.Weapons = new String[] { "刀", "钩" };
        }
        else if (rolename.equals("玄剑娥")) {
            this.Weapons = new String[] { "剑", "枪" };
        }
        else if (rolename.equals("玄天姬")) {
            this.Weapons = new String[] { "双环", "飘带" };
        }
        else if (rolename.equals("燕山雪")) {
            this.Weapons = new String[] { "刀", "剑" };
        }
        else if (rolename.equals("夜溪灵")) {
            this.Weapons = new String[] { "灯笼", "双环" };
        }
        else if (rolename.equals("英女侠")) {
            this.Weapons = new String[] { "刀", "棍" };
        }
        else if (rolename.equals("幽梦影")) {
            this.Weapons = new String[] { "双环", "飘带" };
        }
        else if (rolename.equals("云中君")) {
            this.Weapons = new String[] { "双环", "飘带" };
        }
        else if (rolename.equals("智圣仙")) {
            this.Weapons = new String[] { "浮尘", "剑" };
        }
        else if (rolename.equals("紫薇神")) {
            this.Weapons = new String[] { "幡", "剑" };
        }
        else if (rolename.equals("沧浪君")) {
            this.Weapons = new String[] { "书", "剑" };
        }
        else if (rolename.equals("莫解语")) {
            this.Weapons = new String[] { "灯笼", "剑" };
        }
        else if (rolename.equals("龙渊客")) {
            this.Weapons = new String[] { "枪", "刀" };
        }
        else if (rolename.equals("忘忧子")) {
            this.Weapons = new String[] { "弓", "双环" };
        }
        else if (rolename.equals("骊珠儿")) {
            this.Weapons = new String[] { "锤", "钩" };
        }
        else if (rolename.equals("木兰行")) {
            this.Weapons = new String[] { "弓", "枪" };
        }
    }
    
    public String GetdqAction() {
        return this.Action.getText();
    }
    
    public int GetdqActionIntTwo() {
        String dq = this.GetdqAction() + "=";
        int i = 0;
        while (i < this.Actions.length) {
            if (this.Actions[i].startsWith(dq)) {
                dq = this.Actions[i].substring(dq.length());
                break;
            }
            else {
                ++i;
            }
        }
        for (i = 0; i < SpriteFactory.ActionType.length; ++i) {
            if (dq.equals(SpriteFactory.ActionType[i])) {
                return i;
            }
        }
        return 0;
    }
    
    public int GetdqActionInt() {
        String dq = this.GetdqAction();
        for (int i = 0; i < this.Actions.length; ++i) {
            if (this.Actions[i].split("=")[0].equals(dq)) {
                return i;
            }
        }
        return 0;
    }
    
    public String GetdqWeapon() {
        return this.Weapon.getText();
    }
    
    public String GetdqMount() {
        return this.Mount.getText();
    }
    
    public String getdir() {
        return String.valueOf(this.dir);
    }
    
    public int SelectedRoleID() {
        String role = this.SelectedRole();
        return Creepsskin.getLocalID(role);
    }
    
    public String SelectedRole() {
        for (int i = 0; i < this.rolebtns.length; ++i) {
            if (this.rolebtns[i].getZhen() == 2) {
                return this.rolename(this.SelectedLei(), i);
            }
        }
        return "逍遥生";
    }
    
    public String SelectedLei() {
        for (int i = 0; i < this.leibtns.length; ++i) {
            if (this.leibtns[i].getZhen() == 2) {
                return LoginJpanel.leipath(i);
            }
        }
        return "人";
    }
    
    public void btnAbout(int i) {
        switch (i) {
            case 0: {
                this.ModifyWeapon(-1, 0);
                break;
            }
            case 1: {
                this.ModifyWeapon(-1, 1);
                break;
            }
            case 2: {
                this.ModifyMount(-1, 0);
                break;
            }
            case 3: {
                this.ModifyMount(-1, 1);
                break;
            }
            case 4: {
                this.ModifyAction(-1, 0);
                break;
            }
            case 5: {
                this.ModifyAction(-1, 1);
                break;
            }
            case 6: {
                this.ModifyFashion(-1, 0);
                break;
            }
            case 7: {
                this.ModifyFashion(-1, 1);
                break;
            }
        }
        this.roletcp();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long l = System.currentTimeMillis();
        g.drawImage(this.bejing3.getImage(), 0, 0, 1027, 720, null);
        g.drawImage(this.beijing2.getImage(), 0, 0, 1027, 656, null);
        g.drawImage(this.beijing.getImage(), 0, 73, 1027, 575, (ImageObserver)null);
        if (this.nameBG == null) {
            this.nameBG = CutButtonImage.getImage("resource/xinUI/xin/188.png", -1, -1);
        }
        g.drawImage(this.nameBG.getImage(), 397, 565, 320, 40, this);
        g.drawImage(this.buttonBg.getImage(), 0, 78, 85, 500, this);
        this.roletext.draw(g);
        this.trait.draw(g);
        this.icon.draw(g);
        for (int i = 0; i < this.leibtns.length; ++i) {
            this.leibtns[i].draw(g);
        }
        for (int i = 0; i < this.rolebtns.length; ++i) {
            this.rolebtns[i].draw(g);
        }
        this.creat.draw(g);
        for (int i = 0; i < this.creatbtns.length; ++i) {
            this.creatbtns[i].draw(g);
        }
        this.fanghui.draw(g);
        this.suiji.draw(g);
        if (this.part != null) {
            this.time += 30L;
            this.part.draw(g, 540, 460, this.dir, this.time);
        }
        if (this.msg != null) {
            g.setFont(UIUtils.TEXT_BOLD_FONT);
            g.setColor(Color.red);
            g.drawString(this.msg, 460, 560);
        }
        this.tcp1.updateToTime(l, 0);
        this.tcp1.draw(g, 0, -9);
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public JTextField getTextAccount() {
        return CreateView.textAccount;
    }
    
    public void setTextAccount(JTextField textAccount) {
        CreateView.textAccount = textAccount;
    }
    
    public void clearCheckRole() {
    }
    
    public void setChecked(String text) {
    }
    
    public JTextArea getTextArea() {
        return this.textArea;
    }
    
    public String getChecked() {
        return this.checked;
    }
    
    static {
        CreateView.zzs = "5";
    }
}
