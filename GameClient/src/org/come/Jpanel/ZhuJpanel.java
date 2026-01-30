package org.come.Jpanel;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.come.bean.*;
import org.come.entity.*;
import org.come.entity.Fly;
import org.come.until.GoodsListFromServerUntil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.come.until.Article;
import org.come.until.MessagrFlagUntil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.come.Frame.SummonJframe;
import org.come.Frame.RolePetResistanceJframe;
import com.tool.imagemonitor.FightingMonitor;
import org.come.until.Music;
import com.tool.role.RoleTX;
import com.tool.tcp.SpriteFactory;
import org.come.control.TestMain;
import org.come.Frame.RoleMsgJframe;
import org.come.Frame.PetsMsgJframe;
import org.come.Frame.LingMsgJframe;
import java.awt.Point;

import org.come.model.Eshop;
import org.come.model.Shop;
import org.come.Frame.GoodsMsgJframe;
import org.come.until.Util;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Graphics;
import java.util.Map;
import java.util.Iterator;
import org.come.Frame.LingHelpListJframe;
import java.util.Arrays;
import org.come.model.Lingbao;
import java.util.ArrayList;
import com.tool.role.RoleLingFa;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.SupportListJframe;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.lottery.frame.LotteryMainFrame;
import org.come.mouslisten.OnlineShopingOpenShopMouslisten;
import org.come.mouslisten.ZDCYMouslisten;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.mouslisten.WLLMouslisten;
import org.come.until.CutButtonImage;
import com.tool.Document.RichDocument;

import javax.swing.*;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import org.come.until.ScrenceUntil;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.mouslisten.HotKeyMouseListen;
import com.tool.tcp.Sprite;
import com.tool.btn.AircraftBtn;
import com.tool.btn.TeamPanelBtn;
import org.come.view.TaskGuideView;
import com.tool.btn.FightingBtn;
import org.come.mouslisten.ChoseNoticeMouslisten;
import org.come.mouslisten.ChoseWorldMouslisten;
import org.come.mouslisten.ChoseBangsMouslisten;
import org.come.mouslisten.ChoseGroupsMouslisten;
import org.come.mouslisten.ChoseNowMouslisten;
import org.come.mouslisten.ChoseDangQianMounslisten;
import com.tool.PanelDisplay.PetPanelShow;
import com.tool.PanelDisplay.RolePanelShow;

import java.util.List;
import com.tool.btn.WorkshopBtn;
import com.tool.btn.MouseStyleBtn;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.VipShopBtn;
import com.tool.btn.GoodPanelBtn;
import com.tool.btn.SmallIconBtn;

import java.util.Date;

public class ZhuJpanel extends JPanel
{
    private Date uptime;
    private JLabel rolehp;
    private JLabel rolemp;
    private JLabel roleexe;
    private JLabel bbhp;
    private JLabel bbmp;
    private JLabel toubbmp;
    private JLabel toubbhp;
    private JLabel toubbexe;
    private JLabel bbexe;
    private JLabel tx;
    private String cjkg;
    private String mrqdkg;
    private String zzs;
    private String jjckg;
    private String mrczkg;
    private static JLabel MapName;
    private static JLabel Timemiao;
    private static JLabel xAndY;
    private JLabel dangqian;
    private JLabel labnow;
    private JLabel labgroups;
    private JLabel labbangs;
    private JLabel labworld;
    private JLabel labnotice;
    private JLabel lowerRightImgBack;
    public static boolean battlePetZhiSwitch;
    public static boolean battleLingZhiSwitch;
    public static boolean isPetZhiyuan;
    public static boolean isPetShouFa;
    public static boolean isLingZhiyuan;
    private static JLabel currentBattlePetImg;
    private JLabel currentBattlePetLab;
    private JLabel currentBattlePetSwitch;
    private static JLabel currentBattlelingImg;
    private JLabel currentBattlelingLab;
    private JLabel currentBattlelingSwitch;
    private JLabel kjsfBtn;
    private JTextField SendMes;
    private JLabel jLabelSendMes;
    private JLabel jLabelLeftMes;
    private JLabel jLabelLeftMes1;
    private static SmallIconBtn lableft;
    private static SmallIconBtn labbottom;
    private static SmallIconBtn labtop;
    private static SmallIconBtn labstar;
    private static SmallIconBtn labadd;
    private static SmallIconBtn labminus;
    private GoodPanelBtn xiang;
    private static JLabel labbackground;
    private static JLabel labbackground1;
    private static JLabel labsounds;
    private static JLabel labsounds2;
    private static JLabel labsounds3;
    private static JLabel labsounds4;
    private static JLabel labrolehead;
    private static JLabel petlehead;
    private static JLabel labpetimg;
    private static JLabel labroleimg;
    private static Date date;
    private VipShopBtn recharge;
    private static FormsOnOffBtn palBtn;
    private static FormsOnOffBtn protectBtn;
    private static FormsOnOffBtn dtck;
    private static FormsOnOffBtn mapiconBtn;
    private static FormsOnOffBtn worldMap;
    private static FormsOnOffBtn qdBtn;
    private static FormsOnOffBtn dhBtn;
    private static FormsOnOffBtn CHA;
    private static FormsOnOffBtn chuan;
    private static FormsOnOffBtn duihuan;
    private static FormsOnOffBtn huanj;
    private static FormsOnOffBtn jdBtn;
    private static FormsOnOffBtn arenaBtn;
    private static FormsOnOffBtn zhuBtn;
    private static FormsOnOffBtn TJBtn1;
    private static FormsOnOffBtn zhuanshen;
    private static FormsOnOffBtn guideBtn;
    private static FormsOnOffBtn czlbBtn;
    private static FormsOnOffBtn pdBtn;
    private static FormsOnOffBtn CBGBtn;
    private static FormsOnOffBtn dsBtn;
    private static FormsOnOffBtn lsBtn;
    private static FormsOnOffBtn fuzhu;
    private static FormsOnOffBtn zaBtn;
    private static FormsOnOffBtn RanklistBtn;
    private static FormsOnOffBtn TJBtn;
    private static FormsOnOffBtn gangBtn;
    private static FormsOnOffBtn friendsBtn;
    private static FormsOnOffBtn taskBtn;
    private static FormsOnOffBtn petBtn1;
    private static FormsOnOffBtn childBtn;
    private static FormsOnOffBtn zdrwBtn;
    private static FormsOnOffBtn knapsackBtn;
    private static FormsOnOffBtn QianDaoMenu;
    private static FormsOnOffBtn GMshop;
    private static FormsOnOffBtn bjczBtn;
    private static FormsOnOffBtn xyzpBtn;
    private static FormsOnOffBtn hdrlBtn;
    private static FormsOnOffBtn hotKey;
    private static FormsOnOffBtn zhsbd;
    private static FormsOnOffBtn ttBtn;
//    private static FormsOnOffBtn gzBtn;
    private static FormsOnOffBtn GGBtn;
    private static MouseStyleBtn groupBtn;
    private static FormsOnOffBtn arenaBtn1;
    private static MouseStyleBtn pkBtn;
    private static MouseStyleBtn petBtn;
    private static WorkshopBtn getBtn;
    private static WorkshopBtn transactionBtn;
    private static MouseStyleBtn transactionBtn1;
    private static FormsOnOffBtn rwjnBtn;
    private static MouseStyleBtn systemBtn;
    private static List<Mount> listMount;



    private static List<Car> listCar;
    private static List<Fly> ListFly;
    private static Goodstable goodstableAl;
    private static int UseGoodsType;
    private static Goodstable Nedangoods;
    private RolePanelShow choseRoleStateMouslisten;
    private PetPanelShow chosePetMouslisten;
    private ChoseDangQianMounslisten choseDangQianMounslisten;
    private ChoseNowMouslisten choseNowMouslisten;
    private ChoseGroupsMouslisten choseGroupsMouslisten;
    private ChoseBangsMouslisten choseBangsMouslisten;
    private ChoseWorldMouslisten choseWorldMouslisten;
    private ChoseNoticeMouslisten choseNoticeMouslisten;
    private JLabel ShopingMenu;
    private JLabel DrawMenu;
    private FormsOnOffBtn Dbiaoqing;
    private JPanel biaoti;
    private JPanel setText;
    private int TeamState;
    public int mounState;
    private JLabel touxiang;
    private static FightingBtn zidong;
    private FightingBtn fashu;
    private FightingBtn daoju;
    private FightingBtn fangyu;
    private FightingBtn baohu;
    private FightingBtn zhaohuan;
    private FightingBtn zhaohui;
    private FightingBtn buzhua;
    private FightingBtn taopao;
    private FightingBtn chehui;
    private static FightingBtn qiangtui;
    private FightingBtn tcpdBtn;
    private VipShopBtn rechargeBtn;
    private VipShopBtn oddsBtn;
    private VipShopBtn limitShopBtn;
    private VipShopBtn showVipBtn;
    private static FormsOnOffBtn tournamentsBtn;
    private VipShopBtn showIconBtn;
    private VipShopBtn showIconBtnY;
    private VipShopBtn showcaidan1;
    private VipShopBtn chongjiBtn;
    private VipShopBtn continuousBtn;
    private VipShopBtn caidan;
    private static FormsOnOffBtn bsynBtn;
    private VipShopBtn caidan1;
    private List<VipShopBtn> btnListVicon;
    private TaskGuideView taskGuideView;
    private TeamPanelBtn btnOperation;
    private TeamPanelBtn btnPlatform;
    private TeamPanelBtn btnSpotCard;
    private TeamPanelBtn btnSystemSettings;
    private TeamPanelBtn slhjbtn;
    private TeamPanelBtn cpBtn;
    private TeamPanelBtn drjjBtn;
    private TeamPanelBtn zdlhBtn;
    private TeamPanelBtn jpBtn;
    private TeamPanelBtn gl;
    private TeamPanelBtn cj;
    private TeamPanelBtn xszy;
    private TeamPanelBtn kjcz;
    private TeamPanelBtn qiandao;
    private TeamPanelBtn yueka;
    private JLabel[] teamHeads;
    private AircraftBtn[] aircraftBtns;
    private TeamPanelBtn[] teamOperations;
    private JLabel startTime;
    private JLabel durationTime;
    private JLabel obtainExp;
    private JLabel spendSilver;
    private JLabel spendxy;
    private JLabel win;
    private JLabel pdexp;
    private JLabel mexp;
    private AircraftBtn btnzuoqi;
    private AircraftBtn btnfeixingqi;
    private AircraftBtn btnzuojia;
    private static TeamPanelBtn ladderPanelBtn;
    private int caozuo;
    private JLabel hotKeyBack;
    public static boolean styles;
    static Sprite MallX;
    static Sprite tcp2;
    static Sprite tcp1;
    static Sprite tcp;
    private ImageIcon icon;
    static Sprite Mall;
    static Sprite Draw;
    private int mallX;
    private ImageIcon IconSM;
    private ImageIcon IconHM;
    private static String txk;
    private static String txk1;
    private static String txk2;
    private static String txk3;
    private static String txk4;
    JLabel[] choseGoodsJLabel;
    JLabel[] hotKeyGoodsListLabel;
    JLabel[] hotKeyGoodsListLabelFID;
    HotKeyMouseListen[] goodsMouslistens;
    
    public ZhuJpanel() {
        initTimer();
        this.TeamState = 0;
        this.mounState = 0;
        this.icon = null;
        this.mallX = 210;
        this.IconSM = new ImageIcon("inkImg/background1/rwtxlsm.png");
        this.IconHM = new ImageIcon("inkImg/hongmu1/rwtxlhm.png");
        this.choseGoodsJLabel = new JLabel[8];
        this.hotKeyGoodsListLabel = new JLabel[8];
        this.hotKeyGoodsListLabelFID = new JLabel[8];
        this.goodsMouslistens = new HotKeyMouseListen[8];
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getCjgnkg().equals("开")) {
            this.cjkg = "开";
        }
        else {
            this.cjkg = "关";
        }
        this.jjckg = configure.getJjcgnkg();
        this.mrczkg = configure.getMrczkg();
        this.mrqdkg = configure.getMrqdgnkg();
        this.zzs = configure.getLzjskg();
//        gzBtn = new FormsOnOffBtn("inkImg/hongmu/gj.png", 1, 60888);//CHA
//        gzBtn.setBounds(215, 97, 67, 42);
//        gzBtn.setVisible(false);
//        this.add(gzBtn);
//        bsynBtn = new FormsOnOffBtn("inkImg/hongmu/bsyn.png", 1, 3079);//CHA
//        bsynBtn.setBounds(275, 97, 67, 42);
//        bsynBtn.setVisible(true);
//        this.add(bsynBtn);
        if (MyIsif.getStyle().equals("水墨")) {
            this.fightingbtn();
            this.fightingbtn();
            this.setPreferredSize(new Dimension(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
            this.setLayout(null);
            ImageIcon tips = new ImageIcon("inkImg/background/S33.png");
            (this.labnotice = new JLabel()).setIcon(tips);
            this.labnotice.setBounds(10, ScrenceUntil.Screen_y - 161, 51, 25);
            this.labnotice.setForeground(Color.WHITE);
            this.labnotice.setText("传音");
            this.labnotice.setHorizontalTextPosition(0);
            this.labnotice.setFont(UIUtils.TEXT_FONT);
            this.choseNoticeMouslisten = new ChoseNoticeMouslisten(this);
            this.labnotice.addMouseListener(this.choseNoticeMouslisten);
            this.labnotice.setVisible(false);
            (this.labworld = new JLabel()).setIcon(tips);
            this.labworld.setBounds(10, ScrenceUntil.Screen_y - 134, 51, 25);
            this.labworld.setForeground(Color.WHITE);
            this.labworld.setText("世界");
            this.labworld.setHorizontalTextPosition(0);
            this.labworld.setFont(UIUtils.TEXT_FONT);
            this.choseWorldMouslisten = new ChoseWorldMouslisten(this);
            this.labworld.addMouseListener(this.choseWorldMouslisten);
            this.labworld.setVisible(false);
            (this.labbangs = new JLabel()).setIcon(tips);
            this.labbangs.setBounds(10, ScrenceUntil.Screen_y - 107, 51, 25);
            this.labbangs.setForeground(Color.WHITE);
            this.labbangs.setText("帮派");
            this.labbangs.setHorizontalTextPosition(0);
            this.labbangs.setFont(UIUtils.TEXT_FONT);
            this.choseBangsMouslisten = new ChoseBangsMouslisten(this);
            this.labbangs.addMouseListener(this.choseBangsMouslisten);
            this.labbangs.setVisible(false);
            (this.labgroups = new JLabel()).setIcon(tips);
            this.labgroups.setBounds(10, ScrenceUntil.Screen_y - 80, 51, 25);
            this.labgroups.setForeground(Color.WHITE);
            this.labgroups.setText("队伍");
            this.labgroups.setHorizontalTextPosition(0);
            this.labgroups.setFont(UIUtils.TEXT_FONT);
            this.choseGroupsMouslisten = new ChoseGroupsMouslisten(this);
            this.labgroups.addMouseListener(this.choseGroupsMouslisten);
            this.labgroups.setVisible(false);
            (this.labnow = new JLabel()).setIcon(tips);
            this.labnow.setBounds(10, ScrenceUntil.Screen_y - 53, 51, 25);
            this.labnow.setForeground(Color.WHITE);
            this.labnow.setText("当前");
            this.labnow.setHorizontalTextPosition(0);
            this.labnow.setFont(UIUtils.TEXT_FONT);
            this.choseNowMouslisten = new ChoseNowMouslisten(this);
            this.labnow.addMouseListener(this.choseNowMouslisten);
            this.labnow.setVisible(false);
            (this.dangqian = new JLabel()).setBounds(10, ScrenceUntil.Screen_y - 26, 51, 25);
            this.dangqian.setForeground(Color.WHITE);
            this.dangqian.setText("当前");
            this.dangqian.setVerticalTextPosition(0);
            this.dangqian.setHorizontalTextPosition(0);
            this.dangqian.setFont(UIUtils.TEXT_HYK16);
            this.choseDangQianMounslisten = new ChoseDangQianMounslisten(this);
            this.dangqian.addMouseListener(this.choseDangQianMounslisten);
            (this.SendMes = new JTextField(40)).setBounds(75, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x, 25);
            this.SendMes.setForeground(Color.white);
            this.SendMes.setBackground(UIUtils.Color_BACK);
            this.SendMes.setBorder(BorderFactory.createEmptyBorder());
            this.SendMes.setCaretColor(Color.WHITE);
            this.SendMes.setFont(UIUtils.TEXT_MSG);
            this.SendMes.setDocument(new RichDocument());
            this.SendMes.setFocusable(true);
            this.add(this.SendMes);
            this.setVisible(true);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            (this.jLabelLeftMes = new JLabel()).setIcon(new ImageIcon("inkImg/background1/ltsrk.png"));
            this.jLabelLeftMes.setBounds(0, ScrenceUntil.Screen_y - 44, 160, 43);
            (this.jLabelLeftMes1 = new JLabel()).setIcon(new ImageIcon("inkImg/background1/ltsrkkz.png"));
            this.jLabelLeftMes1.setBounds(170, ScrenceUntil.Screen_y - 42, 70, 43);
            this.add(this.dangqian);
            this.add(this.labnow);
            this.add(this.labgroups);
            this.add(this.labbangs);
            this.add(this.labworld);
            this.add(this.labnotice);
            this.add(this.jLabelLeftMes);
            (this.Dbiaoqing = new FormsOnOffBtn("inkImg/background1/biaoqing.png", 1, 985)).setBounds(205, 5, 35, 35);
            this.add(this.Dbiaoqing);
            this.add(this.jLabelLeftMes1);
            (this.jLabelSendMes = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/background/S31.png", ScrenceUntil.SendMsg_x + 10, -1));
            this.jLabelSendMes.setBounds(70, ScrenceUntil.Screen_y - 42, ScrenceUntil.SendMsg_x + 10, 42);
            (ZhuJpanel.lableft = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", this)).setBounds(19, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.lableft);
            (ZhuJpanel.labbottom = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下", this)).setBounds(38, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labbottom);
            (ZhuJpanel.labtop = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上", this)).setBounds(57, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labtop);
            (ZhuJpanel.labstar = new SmallIconBtn("inkImg/button/B41.png", 1, 7, "星星", this)).setBounds(76, ScrenceUntil.Screen_y - 50, 18, 18);
            this.add(ZhuJpanel.labstar);
            (ZhuJpanel.labadd = new SmallIconBtn("inkImg/button/B35.png", 1, 5, "加号", this)).setBounds(95, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labadd);
            (ZhuJpanel.labminus = new SmallIconBtn("inkImg/button/B36.png", 1, 6, "减号", this)).setBounds(114, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labminus);
        //    (ZhuJpanel.zdrwBtn = new FormsOnOffBtn("inkImg/button1/qqcd.png", 1, 3100)).addMouseListener(new WLLMouslisten(214));
            ZhuJpanel.zdrwBtn = new FormsOnOffBtn("inkImg/button1/qqcd.png", 1, 3072);
            ZhuJpanel.zdrwBtn.setBounds(ScrenceUntil.Screen_x - 348, ScrenceUntil.Screen_y - 30, 25, 25);
          //  ZhuJpanel.zdrwBtn.setToolTipText("功绩千秋");
       //     ZhuJpanel.zdrwBtn.addMouseListener(new WLLMouslisten(214));
            this.add(ZhuJpanel.zdrwBtn);
            (ZhuJpanel.childBtn = new FormsOnOffBtn("inkImg/button1/xhd.png", 1, 1)).setBounds(ScrenceUntil.Screen_x - 319, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.childBtn.addMouseListener(new WLLMouslisten(43));
            this.add(ZhuJpanel.childBtn);
            (ZhuJpanel.knapsackBtn = new FormsOnOffBtn("inkImg/button1/djld.png", 1, 2)).setBounds(ScrenceUntil.Screen_x - 290, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.knapsackBtn.addMouseListener(new WLLMouslisten(44));
            this.add(ZhuJpanel.knapsackBtn);
            (ZhuJpanel.groupBtn = new MouseStyleBtn("inkImg/button1/dwd.png", 1, "组队")).setBounds(ScrenceUntil.Screen_x - 261, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.groupBtn.addMouseListener(new WLLMouslisten(45));
            this.add(ZhuJpanel.groupBtn);
            (ZhuJpanel.pkBtn = new MouseStyleBtn("inkImg/button1/pkd.png", 1, "切磋")).setBounds(ScrenceUntil.Screen_x - 232, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.pkBtn.addMouseListener(new WLLMouslisten(46));
            this.add(ZhuJpanel.pkBtn);
            (ZhuJpanel.transactionBtn1 = new MouseStyleBtn("inkImg/button1/jyd.png", 1, "交易菜单")).setBounds(ScrenceUntil.Screen_x - 174, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.transactionBtn1.addMouseListener(new WLLMouslisten(48));
            this.add(ZhuJpanel.transactionBtn1);
            (ZhuJpanel.rwjnBtn = new FormsOnOffBtn("inkImg/button1/jnd.png", 1, 8)).addMouseListener(new WLLMouslisten(47));
            this.add(ZhuJpanel.rwjnBtn);
            (ZhuJpanel.petBtn = new MouseStyleBtn("inkImg/button1/zqd.png", 1, "坐骑")).setBounds(ScrenceUntil.Screen_x - 145, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.petBtn.addMouseListener(new WLLMouslisten(49));
            this.add(ZhuJpanel.petBtn);
            (ZhuJpanel.taskBtn = new FormsOnOffBtn("inkImg/button1/rwd.png", 1, 3)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.taskBtn.addMouseListener(new WLLMouslisten(50));
            this.add(ZhuJpanel.taskBtn);
            (ZhuJpanel.friendsBtn = new FormsOnOffBtn("inkImg/button1/hyd.png", 1, 4)).setBounds(ScrenceUntil.Screen_x - 87, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.friendsBtn.addMouseListener(new WLLMouslisten(51));
            this.add(ZhuJpanel.friendsBtn);
            (ZhuJpanel.gangBtn = new FormsOnOffBtn("inkImg/button1/bpd.png", 1, 48)).setBounds(ScrenceUntil.Screen_x - 58, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.gangBtn.addMouseListener(new WLLMouslisten(52));
            this.add(ZhuJpanel.gangBtn);
            (ZhuJpanel.systemBtn = new MouseStyleBtn("inkImg/button1/szd.png", 1, "设置")).setBounds(ScrenceUntil.Screen_x - 26, ScrenceUntil.Screen_y - 26, 25, 25);
            ZhuJpanel.systemBtn.addMouseListener(new WLLMouslisten(53));
            this.add(ZhuJpanel.systemBtn);
            (this.lowerRightImgBack = new JLabel()).setIcon(new ImageIcon("inkImg/background1/keybarT.png"));
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            this.add(this.lowerRightImgBack);
            this.add(ZhuJpanel.currentBattlePetImg = new JLabel());
            (this.currentBattlePetSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlePetSwitch);
            (this.currentBattlePetLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlePetLab);
            this.add(ZhuJpanel.currentBattlelingImg = new JLabel());
            (this.currentBattlelingSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlelingSwitch);
            (this.currentBattlelingLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlelingLab);
            (ZhuJpanel.labpetimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
            ZhuJpanel.labpetimg.addMouseListener(new WLLMouslisten(21));
            this.chosePetMouslisten = new PetPanelShow();
            ZhuJpanel.labpetimg.addMouseListener(this.chosePetMouslisten);
            ZhuJpanel.labpetimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 308, 6, 48, 48);
                    super.mousePressed(e);
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
                    super.mouseReleased(e);
                }
            });
            this.add(ZhuJpanel.labpetimg);
            (ZhuJpanel.labroleimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
            ZhuJpanel.labroleimg.addMouseListener(new WLLMouslisten(20));
            this.choseRoleStateMouslisten = new RolePanelShow();
            ZhuJpanel.labroleimg.addMouseListener(this.choseRoleStateMouslisten);
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            if (nao.equals("新")) {
                if (TeststateJpanel.getQhnum().equals("1")) {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
                }
                else {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58));
                }
            }
            else if (TeststateJpanel.getQhnum().equals("1")) {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            else {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            ZhuJpanel.labroleimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 170, 9, 58, 58);
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
                }
            });
            this.add(ZhuJpanel.labroleimg);
            /**==========================召唤兽经验=====================================*/
            (this.bbexe = new JLabel()).addMouseListener(new WLLMouslisten(29));
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 101, 0, 138, 52);
            this.add(this.bbexe);
            /**==========================召唤兽血条=====================================*/
            (this.bbhp = new JLabel()).addMouseListener(new ZDCYMouslisten(2));
            this.add(this.bbhp);
            /**==========================召唤兽蓝条=====================================*/
            (this.bbmp = new JLabel()).addMouseListener(new ZDCYMouslisten(3));
            this.add(this.bbmp);
            /**==========================角色经验=====================================*/
            (this.roleexe = new JLabel()).setBounds(ScrenceUntil.Screen_x - 116, 2, 90, 10);
            this.roleexe.addMouseListener(new WLLMouslisten(28));
            this.add(this.roleexe);
            /**==========================角色血条=====================================*/
            (this.rolehp = new JLabel()).addMouseListener(new ZDCYMouslisten(0));
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 110, 16, 90, 10);
            this.add(this.rolehp);
            /**==========================角色蓝条=====================================*/
            (this.rolemp = new JLabel()).addMouseListener(new ZDCYMouslisten(1));
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 100, 30, 90, 10);
            this.add(this.rolemp);
            /**==========================人物头像显示=====================================*/
            (ZhuJpanel.labrolehead = new JLabel()).setIcon(new ImageIcon("inkImg/background1/rwtxl.png"));
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 100, 5, 133, 52);
            this.add(ZhuJpanel.labrolehead);
            /**==========================召唤兽头像=====================================*/
            (ZhuJpanel.petlehead = new JLabel()).setIcon(new ImageIcon("inkImg/background1/cwtxl.png"));
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 109, 41);
            this.add(ZhuJpanel.petlehead);
            /**==========================地图=====================================*/
            (ZhuJpanel.MapName = new JLabel()).setBounds(15, 6, 80, 20);
            ZhuJpanel.MapName.setForeground(Color.yellow);
            /**==========================时间显示实例=====================================*/
            //时辰
            (ZhuJpanel.Timemiao = new JLabel()).addMouseListener(new WLLMouslisten(0));
            ZhuJpanel.Timemiao.setBounds(106, 43, 50, 20);
            ZhuJpanel.Timemiao.setForeground(UIUtils.COLOR_WHITE1[0]);
            ZhuJpanel.Timemiao.setFont(UIUtils.TEXT_FONTZS);
            this.add(ZhuJpanel.Timemiao);
            /**==========================时间显示实例=====================================*/
            (ZhuJpanel.xAndY = new JLabel()).setBounds(45, 11, 180, 20);
            ZhuJpanel.xAndY.setForeground(Color.white);
            this.add(ZhuJpanel.xAndY);
            /**==========================坐标=====================================*/

            // 每日活动
            (ZhuJpanel.TJBtn = new FormsOnOffBtn("inkImg/button1/XTB1.png", 1, 114)).setBounds(2, 45, 20, 20);
            ZhuJpanel.TJBtn.addMouseListener(new WLLMouslisten(304));
            this.add(ZhuJpanel.TJBtn);
            // 排行榜
            (ZhuJpanel.RanklistBtn = new FormsOnOffBtn("inkImg/button1/XTB2.png", 1, UIUtils.COLOR_BTNTEXT, "", 60)).setBounds(24, 45, 20, 20);
            ZhuJpanel.RanklistBtn.addMouseListener(new WLLMouslisten(305));
            this.add(ZhuJpanel.RanklistBtn);
            // 藏宝阁
            (ZhuJpanel.CBGBtn = new FormsOnOffBtn("inkImg/button1/XTB3.png", 1, UIUtils.COLOR_BTNTEXT, "", 78)).setBounds(46, 45, 20, 20);
            ZhuJpanel.CBGBtn.addMouseListener(new WLLMouslisten(306));
            this.add(ZhuJpanel.CBGBtn);
            //活动日历
            (ZhuJpanel.hdrlBtn = new FormsOnOffBtn("inkImg/button1/XTB4.png", 1, UIUtils.COLOR_BTNTEXT, "", 40)).setBounds(68, 45, 20, 20);
            ZhuJpanel.hdrlBtn.addMouseListener(new WLLMouslisten(307));
            this.add(ZhuJpanel.hdrlBtn);
            //公告
            (ZhuJpanel.GGBtn = new FormsOnOffBtn("inkImg/button1/gg1.png", 1, 1001)).setBounds(2, 45, 45, 44);
            this.add(ZhuJpanel.GGBtn);

            (ZhuJpanel.zhuBtn = new FormsOnOffBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BTNTEXT, "伙", 105)).setBounds(41, 34, 18, 18);
            //左上角小地图图标
            (ZhuJpanel.mapiconBtn = new FormsOnOffBtn("inkImg/button1/B61.png", 1, 22)).setBounds(182, 23, 20, 20);
            ZhuJpanel.mapiconBtn.addMouseListener(new WLLMouslisten(302));
            this.add(ZhuJpanel.mapiconBtn);
            //左上角世界地图图标
            (ZhuJpanel.worldMap = new FormsOnOffBtn("inkImg/button1/B62.png", 1, 1102)).setBounds(182, 3, 20, 20);
            ZhuJpanel.worldMap.addMouseListener(new WLLMouslisten(303));
            this.add(ZhuJpanel.worldMap);
            //飞行器
            this.aircraftBtns = new AircraftBtn[2];
            for (int i = 0; i < this.aircraftBtns.length; ++i) {
                (this.aircraftBtns[i] = new AircraftBtn("img/xy2uiimg/btnCommon102.png", 1, (i == 0) ? "坐骑" : "飞行器", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, i + 10, this)).setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (67 - i * 17), 102, 17);
                this.aircraftBtns[i].setVisible(false);
                this.add(this.aircraftBtns[i]);
            }
            (this.btnzuoqi = new AircraftBtn("inkImg/button1/syaj.png", 1, "坐  骑", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 10, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnzuoqi.setVisible(false);
            this.add(this.btnzuoqi);
            (this.btnfeixingqi = new AircraftBtn("inkImg/button1/syaj.png", 1, "飞  行", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 11, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnfeixingqi.setVisible(false);
            this.add(this.btnfeixingqi);
            (this.btnzuojia = new AircraftBtn("inkImg/button1/syaj.png", 1, "坐  驾", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 12, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - (67+17), 102, 17);
            this.btnzuojia.setVisible(false);
            this.add(this.btnzuojia);

            (ZhuJpanel.transactionBtn = new WorkshopBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, "交  易", 203)).setVisible(false);
            this.add(ZhuJpanel.transactionBtn);
            (ZhuJpanel.getBtn = new WorkshopBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, "给  予", 204)).setVisible(false);
            this.add(ZhuJpanel.getBtn);
            if (this.cjkg.equals("开")) {
                (this.cj = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "幸运抽奖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
                this.cj.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            }
            (this.yueka = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "月卡特权", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.yueka.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.yueka);
            (this.qiandao = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "每日签到", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.qiandao);
            (this.gl = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "游戏攻略", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.gl.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.gl);
            (this.xszy = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "新手指引", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.xszy);
            (this.kjcz = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "快捷操作(Tab)", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.kjcz);
            (this.jpBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "聚宝阁拍卖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.jpBtn);
            (this.zdlhBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "自动炼化(F12)", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.zdlhBtn);
            (this.drjjBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "单人竞技", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.drjjBtn.setVisible(false);
            this.add(this.drjjBtn);
            (this.cpBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "全民竞猜", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.cpBtn);
            (ZhuJpanel.ladderPanelBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "天梯竞技", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            ZhuJpanel.ladderPanelBtn.setVisible(false);
            this.add(ZhuJpanel.ladderPanelBtn);
            (this.btnSpotCard = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "仙玉寄售", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 116, 24);
            this.btnSpotCard.setVisible(false);
            this.add(this.btnSpotCard);
            (this.btnSystemSettings = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "系统设置", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 116, 24);
            this.btnSystemSettings.setVisible(false);
            this.add(this.btnSystemSettings);
            if (configure.getMrczkg().equals("开")) {
                //礼包中心
                (this.chongjiBtn = new VipShopBtn("Inkimg/background1/SS70.png", 1, 50)).addMouseListener(new WLLMouslisten(8));
                this.chongjiBtn.setBounds(80, 90, 53, 53);
                this.add(this.chongjiBtn);
                (this.oddsBtn = new VipShopBtn("Inkimg/button/B23.png", 1, 53)).setBounds(20, 145, 53, 53);
           //     this.add(this.oddsBtn);
                //限时限购
                (this.limitShopBtn = new VipShopBtn("Inkimg/button1/xsxg.png", 1, 61)).setBounds(140, 90, 53, 53);
                this.add(this.limitShopBtn);
            }
            this.tournamentsBtn = new FormsOnOffBtn("inkImg/danxin/p/e12.png", 1, UIUtils.COLOR_BTNTEXT, "", 1146);
            this.tournamentsBtn.setBounds(285, 97, 53, 53);
//            if (configure.getZhsdjsx().equals("开")) {
                this.add(this.tournamentsBtn);
//            }
            (this.showVipBtn = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 54)).addMouseListener(new WLLMouslisten(7));
            this.showVipBtn.setBounds(0, 120, 14, 38);
            (this.showIconBtn = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 60)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtn);
            (this.showIconBtnY = new VipShopBtn("Inkimg/button1/sjajdk.png", 1, 62)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtnY);
            (ZhuJpanel.bjczBtn = new FormsOnOffBtn("inkImg/hongmu/shortcut-btn.png", 1, 3000)).setBounds(20, 90, 53, 53);
            if (configure.getBjczkg().equals("开")) {//快捷操作
                this.add(ZhuJpanel.bjczBtn);
            }
            (ZhuJpanel.guideBtn = new FormsOnOffBtn("inkImg/hongmu/xsyd.png", 1, 68)).addMouseListener(new WLLMouslisten(12));
            ZhuJpanel.guideBtn.setBounds(20, 60, 53, 53);//限时限购
            //开服狂欢
            (ZhuJpanel.czlbBtn = new FormsOnOffBtn("Inkimg/background1/SS70.png", 1, 3002)).setBounds(200, 78, 53, 53);
            if (configure.getLbzxkg().equals("开")) {}
            //便捷操作
//            gzBtn = new FormsOnOffBtn("inkImg/hongmu/gj.png", 1, 60888);//官职
//            gzBtn.setBounds(215, 97, 67, 42);
//            gzBtn.setVisible(true);
//            this.add(gzBtn);//大开关
            bsynBtn = new FormsOnOffBtn("inkImg/hongmu/bsyn.png", 1, 3079);//八十一难
            bsynBtn.setBounds(211, 94, 67, 42);
            bsynBtn.setVisible(true);
            this.add(bsynBtn);//大开关

            //泡点
            (ZhuJpanel.pdBtn = new FormsOnOffBtn("inkImg/button/pd.png", 1, 3003)).setBounds(198, 60, 53, 53);
            if (configure.getPdgnkg().equals("开")) {
                this.add(ZhuJpanel.pdBtn);
            }
            (ZhuJpanel.labbackground = new JLabel()).setIcon(new ImageIcon("inkImg/background1/S30.png"));
            ZhuJpanel.labbackground.setBounds(0, 0, 201, 67);
            this.add(ZhuJpanel.labbackground);
            // 打开商城
            (this.ShopingMenu = new JLabel()).setBounds(205, 5, 35, 35);
            this.ShopingMenu.addMouseListener(new OnlineShopingOpenShopMouslisten());
            this.ShopingMenu.addMouseListener(new WLLMouslisten(300));
            this.add(this.ShopingMenu);
            // 设置抽奖按钮

            if (configure.getCjgnkg().equals("开")) {
                (this.DrawMenu = new JLabel()).setBounds(250, 5, 35, 35);
                // 打开抽奖
                this.DrawMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                        FormsManagement.showForm(87);
                    }
                });
            }
            this.setBackground(UIUtils.Color_BACK);
            (this.btnOperation = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "组队操作", UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnOperation.setVisible(false);
            this.add(this.btnOperation);
            (this.btnPlatform = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "组队平台", UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnPlatform.setVisible(false);
            this.add(this.btnPlatform);
            this.add(this.taskGuideView = new TaskGuideView());
            //快捷操作栏
            ImageIcon hotKeyBackImg = new ImageIcon("inkImg/background1/xbbkjl.png");
            this.hotKeyMethod(hotKeyBackImg);
            (this.kjsfBtn = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/快捷_w250.png"));
            this.kjsfBtn.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            //左上角扩展快捷
            (this.caidan = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 56)).addMouseListener(new WLLMouslisten(7));
            this.caidan.setBounds(0, 632, 14, 38);
            this.add(this.caidan);
            (ZhuJpanel.zhsbd = new FormsOnOffBtn("inkImg/button1/zhsbd.png", 1, 30001)).setBounds(20, 90, 53, 53);
            (ZhuJpanel.ttBtn = new FormsOnOffBtn("inkImg/button/ttIco.png", 1, 604)).setBounds(80, 90, 53, 53);
        }
        else {
            this.fightingbtn();
            this.setPreferredSize(new Dimension(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
            this.setLayout(null);
            ImageIcon tips = new ImageIcon("img/xy2uiimg/11_png.xy2uiimg.tips_副本.png");
            (this.labnotice = new JLabel()).setIcon(tips);
            this.labnotice.setBounds(0, ScrenceUntil.Screen_y - 164, 70, 25);
            this.labnotice.setForeground(Color.WHITE);
            this.labnotice.setText("传音");
            this.labnotice.setHorizontalTextPosition(0);
            this.labnotice.setFont(UIUtils.TEXT_FONT);
            this.choseNoticeMouslisten = new ChoseNoticeMouslisten(this);
            this.labnotice.addMouseListener(this.choseNoticeMouslisten);
            this.labnotice.setVisible(false);
            (this.labworld = new JLabel()).setIcon(tips);
            this.labworld.setBounds(0, ScrenceUntil.Screen_y - 134, 70, 25);
            this.labworld.setForeground(Color.WHITE);
            this.labworld.setText("世界");
            this.labworld.setHorizontalTextPosition(0);
            this.labworld.setFont(UIUtils.TEXT_FONT);
            this.choseWorldMouslisten = new ChoseWorldMouslisten(this);
            this.labworld.addMouseListener(this.choseWorldMouslisten);
            this.labworld.setVisible(false);
            (this.labbangs = new JLabel()).setIcon(tips);
            this.labbangs.setBounds(0, ScrenceUntil.Screen_y - 107, 70, 25);
            this.labbangs.setForeground(Color.WHITE);
            this.labbangs.setText("帮派");
            this.labbangs.setHorizontalTextPosition(0);
            this.labbangs.setFont(UIUtils.TEXT_FONT);
            this.choseBangsMouslisten = new ChoseBangsMouslisten(this);
            this.labbangs.addMouseListener(this.choseBangsMouslisten);
            this.labbangs.setVisible(false);
            (this.labgroups = new JLabel()).setIcon(tips);
            this.labgroups.setBounds(0, ScrenceUntil.Screen_y - 80, 70, 25);
            this.labgroups.setForeground(Color.WHITE);
            this.labgroups.setText("队伍");
            this.labgroups.setHorizontalTextPosition(0);
            this.labgroups.setFont(UIUtils.TEXT_FONT);
            this.choseGroupsMouslisten = new ChoseGroupsMouslisten(this);
            this.labgroups.addMouseListener(this.choseGroupsMouslisten);
            this.labgroups.setVisible(false);
            (this.labnow = new JLabel()).setIcon(tips);
            this.labnow.setBounds(0, ScrenceUntil.Screen_y - 53, 70, 25);
            this.labnow.setForeground(Color.WHITE);
            this.labnow.setText("当前");
            this.labnow.setHorizontalTextPosition(0);
            this.labnow.setFont(UIUtils.TEXT_FONT);
            this.choseNowMouslisten = new ChoseNowMouslisten(this);
            this.labnow.addMouseListener(this.choseNowMouslisten);
            this.labnow.setVisible(false);
            (this.dangqian = new JLabel()).setBounds(0, ScrenceUntil.Screen_y - 26, 70, 25);
            this.dangqian.setForeground(Color.WHITE);
            this.dangqian.setText("当前");
            this.dangqian.setVerticalTextPosition(0);
            this.dangqian.setHorizontalTextPosition(0);
            this.dangqian.setFont(UIUtils.TEXT_HYK16);
            this.choseDangQianMounslisten = new ChoseDangQianMounslisten(this);
            this.dangqian.addMouseListener(this.choseDangQianMounslisten);
            (this.SendMes = new JTextField(40)).setBounds(75, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x, 25);
            this.SendMes.setForeground(Color.white);
            this.SendMes.setBackground(UIUtils.Color_BACK);
            this.SendMes.setBorder(BorderFactory.createEmptyBorder());
            this.SendMes.setCaretColor(Color.WHITE);
            this.SendMes.setFont(UIUtils.TEXT_MSG);
            this.SendMes.setDocument(new RichDocument());
            this.SendMes.setFocusable(true);
            this.add(this.SendMes);
            this.setVisible(true);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            (this.jLabelLeftMes = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/ltsrkh.png"));
            this.jLabelLeftMes.setBounds(70, ScrenceUntil.Screen_y - 21, 160, 25);
            (this.jLabelLeftMes1 = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/聊天框0000大1.png"));
            this.jLabelLeftMes1.setBounds(70, ScrenceUntil.Screen_y - 21, ScrenceUntil.Screen_x + 70, 25);
            this.add(this.dangqian);
            this.add(this.labnow);
            this.add(this.labgroups);
            this.add(this.labbangs);
            this.add(this.labworld);
            this.add(this.labnotice);
            this.add(this.jLabelLeftMes);
            (ZhuJpanel.zhsbd = new FormsOnOffBtn("inkImg/button1/zhsbd.png", 1, 30001)).setBounds(20, 90, 49, 44);
            (ZhuJpanel.ttBtn = new FormsOnOffBtn("inkImg/button/ttIco.png", 1, 604)).setBounds(80, 90, 49, 49);
            (this.Dbiaoqing = new FormsOnOffBtn("inkImg/background1/biaoqing.png", 1, 985)).setBounds(205, 5, 35, 35);
            this.add(this.Dbiaoqing);
            this.add(this.jLabelLeftMes1);
            (this.jLabelSendMes = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/hongmu/s258.png", ScrenceUntil.SendMsg_x + 50, -1));
            this.jLabelSendMes.setBounds(0, ScrenceUntil.Screen_y - 42, ScrenceUntil.SendMsg_x + 1110, 25);
            (ZhuJpanel.lableft = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", this)).setBounds(19, ScrenceUntil.Screen_y + 83, 17, 17);
            this.add(ZhuJpanel.lableft);
            (ZhuJpanel.labbottom = new SmallIconBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, 1, "向下", this)).setBounds(19, ScrenceUntil.Screen_y - 47, 19, 19);
            this.add(ZhuJpanel.labbottom);
            (ZhuJpanel.labtop = new SmallIconBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, 0, "向上", this)).setBounds(38, ScrenceUntil.Screen_y - 47, 19, 19);
            this.add(ZhuJpanel.labtop);
            (ZhuJpanel.labstar = new SmallIconBtn("img/xy2uiimg/56_png.button.btn_11.png", 1, 7, "星星", this)).setBounds(57, ScrenceUntil.Screen_y - 47, 18, 18);
            this.add(ZhuJpanel.labstar);
            (ZhuJpanel.labadd = new SmallIconBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 5, "加号", this)).setBounds(76, ScrenceUntil.Screen_y - 47, 17, 17);
            this.add(ZhuJpanel.labadd);
            (ZhuJpanel.labminus = new SmallIconBtn("img/xy2uiimg/33_png.button.btn_6.png", 1, 6, "减号", this)).setBounds(95, ScrenceUntil.Screen_y - 47, 17, 17);
            this.add(ZhuJpanel.labminus);
            (ZhuJpanel.zdrwBtn = new FormsOnOffBtn("inkImg/button1/qqcdh.png", 1, 3072)).addMouseListener(new WLLMouslisten(214));
            this.add(ZhuJpanel.zdrwBtn);
            (ZhuJpanel.childBtn = new FormsOnOffBtn("inkImg/button1/xhdh.png", 1, 1)).setBounds(ScrenceUntil.Screen_x - 319, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.childBtn.addMouseListener(new WLLMouslisten(43));
            this.add(ZhuJpanel.childBtn);
            (ZhuJpanel.knapsackBtn = new FormsOnOffBtn("inkImg/button1/djldh.png", 1, 2)).setBounds(ScrenceUntil.Screen_x - 290, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.knapsackBtn.addMouseListener(new WLLMouslisten(44));
            this.add(ZhuJpanel.knapsackBtn);
            (ZhuJpanel.groupBtn = new MouseStyleBtn("inkImg/button1/dwdh.png", 1, "组队")).setBounds(ScrenceUntil.Screen_x - 261, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.groupBtn.addMouseListener(new WLLMouslisten(45));
            this.add(ZhuJpanel.groupBtn);
            (ZhuJpanel.pkBtn = new MouseStyleBtn("inkImg/button1/pkdh.png", 1, "切磋")).setBounds(ScrenceUntil.Screen_x - 232, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.pkBtn.addMouseListener(new WLLMouslisten(46));
            this.add(ZhuJpanel.pkBtn);
            (ZhuJpanel.transactionBtn1 = new MouseStyleBtn("inkImg/button1/jydh.png", 1, "交易菜单")).setBounds(ScrenceUntil.Screen_x - 174, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.transactionBtn1.addMouseListener(new WLLMouslisten(48));
            this.add(ZhuJpanel.transactionBtn1);
            (ZhuJpanel.rwjnBtn = new FormsOnOffBtn("inkImg/button1/jndh.png", 1, 8)).addMouseListener(new WLLMouslisten(47));
            this.add(ZhuJpanel.rwjnBtn);
            (ZhuJpanel.petBtn = new MouseStyleBtn("inkImg/button1/zqdh.png", 1, "坐骑")).setBounds(ScrenceUntil.Screen_x - 145, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.petBtn.addMouseListener(new WLLMouslisten(49));
            this.add(ZhuJpanel.petBtn);
            (ZhuJpanel.taskBtn = new FormsOnOffBtn("inkImg/button1/rwdh.png", 1, 3)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.taskBtn.addMouseListener(new WLLMouslisten(50));
            this.add(ZhuJpanel.taskBtn);
            (ZhuJpanel.friendsBtn = new FormsOnOffBtn("inkImg/button1/hydh.png", 1, 4)).setBounds(ScrenceUntil.Screen_x - 87, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.friendsBtn.addMouseListener(new WLLMouslisten(51));
            this.add(ZhuJpanel.friendsBtn);
            (ZhuJpanel.gangBtn = new FormsOnOffBtn("inkImg/button1/bpdh.png", 1, 48)).setBounds(ScrenceUntil.Screen_x - 58, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.gangBtn.addMouseListener(new WLLMouslisten(52));
            this.add(ZhuJpanel.gangBtn);
            (ZhuJpanel.systemBtn = new MouseStyleBtn("inkImg/button1/szdh.png", 1, "设置")).setBounds(ScrenceUntil.Screen_x - 29, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.systemBtn.addMouseListener(new WLLMouslisten(53));
            this.add(ZhuJpanel.systemBtn);
//            gzBtn = new FormsOnOffBtn("inkImg/hongmu/gj.png", 1, 60888);//CHA
//            gzBtn.setBounds(215, 97, 67, 42);
//            gzBtn.setVisible(true);
//            this.add(gzBtn);//大开关
            bsynBtn = new FormsOnOffBtn("inkImg/hongmu/bsyn.png", 1, 3079);//CHA
            bsynBtn.setBounds(211, 94, 67, 42);
            bsynBtn.setVisible(true);
            this.add(bsynBtn);//大开关
            (this.lowerRightImgBack = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/keybarTh.png"));
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            this.add(this.lowerRightImgBack);
            this.add(ZhuJpanel.currentBattlePetImg = new JLabel());
            (this.currentBattlePetSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlePetSwitch);
            (this.currentBattlePetLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlePetLab);
            this.add(ZhuJpanel.currentBattlelingImg = new JLabel());
            (this.currentBattlelingSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlelingSwitch);
            (this.currentBattlelingLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlelingLab);
            (ZhuJpanel.labpetimg = new JLabel()).addMouseListener(new WLLMouslisten(21));
            this.chosePetMouslisten = new PetPanelShow();
            ZhuJpanel.labpetimg.addMouseListener(this.chosePetMouslisten);
            ZhuJpanel.labpetimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 308, 6, 48, 48);
                    super.mousePressed(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
                    super.mouseReleased(e);
                }
            });
            this.add(ZhuJpanel.labpetimg);
            (ZhuJpanel.labroleimg = new JLabel()).addMouseListener(new WLLMouslisten(20));
            this.choseRoleStateMouslisten = new RolePanelShow();
            ZhuJpanel.labroleimg.addMouseListener(this.choseRoleStateMouslisten);
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            if (nao.equals("新")) {
                if (TeststateJpanel.getQhnum().equals("1")) {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
                }
                else {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58));
                }
            }
            else if (TeststateJpanel.getQhnum().equals("1")) {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            else {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            ZhuJpanel.labroleimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 170, 9, 58, 58);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
                }
            });
            this.add(ZhuJpanel.labroleimg);
            (this.bbexe = new JLabel()).addMouseListener(new WLLMouslisten(29));
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 100, 0, 133, 52);
            this.add(this.bbexe);
            (this.bbhp = new JLabel()).addMouseListener(new ZDCYMouslisten(2));
            this.add(this.bbhp);
            (this.bbmp = new JLabel()).addMouseListener(new ZDCYMouslisten(3));
            this.add(this.bbmp);
            (this.roleexe = new JLabel()).setBounds(ScrenceUntil.Screen_x - 116, 2, 78, 10);
            this.roleexe.addMouseListener(new WLLMouslisten(28));
            this.add(this.roleexe);
            (this.rolehp = new JLabel()).addMouseListener(new ZDCYMouslisten(0));
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 116, 16, 78, 10);
            this.add(this.rolehp);
            (this.rolemp = new JLabel()).addMouseListener(new ZDCYMouslisten(1));
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 116, 30, 78, 10);
            this.add(this.rolemp);
            (ZhuJpanel.labrolehead = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/rwtxlh.png"));
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 126, 46);
            this.add(ZhuJpanel.labrolehead);
            (ZhuJpanel.petlehead = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/cwtxlh.png"));
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 108, 41);
            this.add(ZhuJpanel.petlehead);
            (ZhuJpanel.MapName = new JLabel()).setBounds(15, 6, 80, 20);
            ZhuJpanel.MapName.setForeground(Color.yellow);
            if (configure.getCjgnkg().equals("开")) {
                this.cjkg = "开";
            }
            else {
                this.cjkg = "关";
            }
            (ZhuJpanel.Timemiao = new JLabel()).addMouseListener(new WLLMouslisten(0));
            ZhuJpanel.Timemiao.setBounds(106, 43, 50, 20);
            ZhuJpanel.Timemiao.setForeground(UIUtils.COLOR_WHITE1[0]);
            ZhuJpanel.Timemiao.setFont(UIUtils.TEXT_FONTZS);
            this.add(ZhuJpanel.Timemiao);
            (ZhuJpanel.xAndY = new JLabel()).setBounds(45, 11, 180, 20);
            ZhuJpanel.xAndY.setForeground(Color.white);
            this.add(ZhuJpanel.xAndY);
            Color backgroundColor = new Color(0, 0, 0);
            this.setBackground(backgroundColor);
            (this.ShopingMenu = new JLabel()).setBounds(205, 5, 35, 35);
            this.ShopingMenu.addMouseListener(new OnlineShopingOpenShopMouslisten());
            this.ShopingMenu.addMouseListener(new WLLMouslisten(300));
            this.add(this.ShopingMenu);
            if (configure.getCjgnkg().equals("开")) {
                (this.DrawMenu = new JLabel()).setBounds(250, 5, 35, 35);
                this.DrawMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                        FormsManagement.showForm(87);
                    }
                });
            }
            (ZhuJpanel.TJBtn = new FormsOnOffBtn("inkImg/button1/XTB1h.png", 1, 114)).setBounds(2, 45, 20, 20);
            ZhuJpanel.TJBtn.addMouseListener(new WLLMouslisten(304));
            this.add(ZhuJpanel.TJBtn);
            (ZhuJpanel.RanklistBtn = new FormsOnOffBtn("inkImg/button1/XTB2h.png", 1, UIUtils.COLOR_BTNTEXT, "", 60)).setBounds(24, 45, 20, 20);
            ZhuJpanel.RanklistBtn.addMouseListener(new WLLMouslisten(305));
            this.add(ZhuJpanel.RanklistBtn);
            (ZhuJpanel.CBGBtn = new FormsOnOffBtn("inkImg/button1/XTB3h.png", 1, UIUtils.COLOR_BTNTEXT, "", 78)).setBounds(46, 45, 20, 20);
            ZhuJpanel.CBGBtn.addMouseListener(new WLLMouslisten(306));
            this.add(ZhuJpanel.CBGBtn);
            (ZhuJpanel.hdrlBtn = new FormsOnOffBtn("inkImg/button1/XTB4h.png", 1, UIUtils.COLOR_BTNTEXT, "", 40)).setBounds(68, 45, 20, 20);
            ZhuJpanel.hdrlBtn.addMouseListener(new WLLMouslisten(307));
            this.add(ZhuJpanel.hdrlBtn);
            (ZhuJpanel.GGBtn = new FormsOnOffBtn("inkImg/button1/gg1.png", 1, 1001)).setBounds(2, 45, 45, 44);
            this.add(ZhuJpanel.GGBtn);
            (ZhuJpanel.mapiconBtn = new FormsOnOffBtn("inkImg/button1/B61h.png", 1, 22)).setBounds(182, 23, 20, 20);
            ZhuJpanel.mapiconBtn.addMouseListener(new WLLMouslisten(302));
            this.add(ZhuJpanel.mapiconBtn);
            (ZhuJpanel.worldMap = new FormsOnOffBtn("inkImg/button1/B62h.png", 1, 1102)).setBounds(182, 3, 20, 20);
            ZhuJpanel.worldMap.addMouseListener(new WLLMouslisten(303));
            this.add(ZhuJpanel.worldMap);
            (ZhuJpanel.zhuBtn = new FormsOnOffBtn("img/xy2uiimg/伙_w17,h51.png", 1, UIUtils.COLOR_BTNTEXT, "", 105)).setBounds(39, 34, 17, 17);
            (this.btnOperation = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "组队操作", UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnOperation.setVisible(false);
            this.add(this.btnOperation);
            (this.btnPlatform = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "组队平台", UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnPlatform.setVisible(false);
            this.add(this.btnPlatform);
            (ZhuJpanel.transactionBtn = new WorkshopBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, "交  易", 203)).setVisible(false);
            this.add(ZhuJpanel.transactionBtn);
            (ZhuJpanel.getBtn = new WorkshopBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, "给  予", 204)).setVisible(false);
            this.add(ZhuJpanel.getBtn);
            if (this.cjkg.equals("开")) {
                (this.cj = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "幸运抽奖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
                this.cj.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 216, 116, 24);
            }
            (this.yueka = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "月卡特权", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.yueka.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.yueka);
            (this.qiandao = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "每日签到", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.qiandao);
            (this.gl = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "游戏攻略", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.gl.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 192, 116, 24);
            this.add(this.gl);
            (this.xszy = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "新手指引", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 168, 116, 24);
            this.add(this.xszy);
            (this.kjcz = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "快捷操作(Tab)", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 144, 116, 24);
            this.add(this.kjcz);
            (this.jpBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "聚宝阁拍卖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 120, 116, 24);
            this.add(this.jpBtn);
            (this.zdlhBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "自动炼化(F12)", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 120, 116, 24);
            this.add(this.zdlhBtn);
            (this.drjjBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "单人竞技", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 144, 116, 24);
            this.add(this.drjjBtn);
            (this.cpBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "全民竞猜", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 120, 116, 24);
            this.add(this.cpBtn);
            (ZhuJpanel.ladderPanelBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "天梯竞技", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 96, 116, 24);
            ZhuJpanel.ladderPanelBtn.setVisible(false);
            this.add(ZhuJpanel.ladderPanelBtn);
            (this.btnSpotCard = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "仙玉寄售", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 72, 116, 24);
            this.btnSpotCard.setVisible(false);
            this.add(this.btnSpotCard);
            (this.btnSystemSettings = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "系统设置", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 48, 116, 24);
            this.btnSystemSettings.setVisible(false);
            this.add(this.btnSystemSettings);
            if (configure.getMrczkg().equals("开")) {
                (this.chongjiBtn = new VipShopBtn("Inkimg/background1/SS70.png", 1, 50)).addMouseListener(new WLLMouslisten(8));
                this.chongjiBtn.setBounds(80, 90, 53, 53);
                this.add(this.chongjiBtn);
                (this.rechargeBtn = new VipShopBtn("Inkimg/button/B22.png", 1, 52)).setBounds(79, 132, 53, 53);
                (this.oddsBtn = new VipShopBtn("Inkimg/button/B23.png", 1, 53)).setBounds(139, 116, 53, 53);
                (this.limitShopBtn = new VipShopBtn("Inkimg/button1/xsxg.png", 1, 61)).setBounds(140, 90, 53, 53);
                this.add(this.limitShopBtn);
            }
            this.tournamentsBtn = new FormsOnOffBtn("inkImg/danxin/p/e12.png", 1, UIUtils.COLOR_BTNTEXT, "", 1146);
            this.tournamentsBtn.setBounds(285, 97, 53, 53);
//            if (configure.getZhsdjsx().equals("开")) {
                this.add(this.tournamentsBtn);
//            }
            (this.showVipBtn = new VipShopBtn("Inkimg/button1/sjajgbh.png", 1, 54)).addMouseListener(new WLLMouslisten(7));
            this.showVipBtn.setBounds(0, 120, 14, 38);
            (this.showIconBtn = new VipShopBtn("Inkimg/button1/sjajdkh.png", 1, 60)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtn);
            (this.showIconBtnY = new VipShopBtn("Inkimg/button1/sjajdkh.png", 1, 62)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtnY);
            (this.caidan = new VipShopBtn("Inkimg/button1/sjajgbh.png", 1, 56)).addMouseListener(new WLLMouslisten(7));
            this.caidan.setBounds(0, 632, 14, 38);
            this.add(this.caidan);
            (this.kjsfBtn = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/快捷_w250.png"));
            this.kjsfBtn.setBounds(ScrenceUntil.Screen_x - 190, ScrenceUntil.Screen_y - 76, 190, 38);
            (ZhuJpanel.czlbBtn = new FormsOnOffBtn("inkImg/button/gift-btn.png", 1, 3002)).setBounds(200, 78, 53, 53);
            if (configure.getLbzxkg().equals("开")) {}
            (ZhuJpanel.pdBtn = new FormsOnOffBtn("inkImg/button/pd.png", 1, 3003)).setBounds(198, 60, 53, 53);
            if (configure.getPdgnkg().equals("开")) {
                this.add(ZhuJpanel.pdBtn);
            }
            (ZhuJpanel.bjczBtn = new FormsOnOffBtn("inkImg/hongmu/shortcut-btn.png", 1, 3000)).addMouseListener(new WLLMouslisten(88));
            ZhuJpanel.bjczBtn.setBounds(20, 90, 53, 53);
            if (configure.getBjczkg().equals("开")) {
                this.add(ZhuJpanel.bjczBtn);
            }
            (ZhuJpanel.guideBtn = new FormsOnOffBtn("inkImg/hongmu/xsyd.png", 1, 68)).addMouseListener(new WLLMouslisten(12));
            ZhuJpanel.guideBtn.setBounds(20, 60, 53, 53);
            this.aircraftBtns = new AircraftBtn[2];
            for (int j = 0; j < this.aircraftBtns.length; ++j) {
                (this.aircraftBtns[j] = new AircraftBtn("img/xy2uiimg/btnCommon102.png", 1, (j == 0) ? "坐骑" : "飞行器", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, j + 10, this)).setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (67 - j * 17), 102, 17);
                this.aircraftBtns[j].setVisible(false);
                this.add(this.aircraftBtns[j]);
            }
            (this.btnzuoqi = new AircraftBtn("inkImg/button1/syajh.png", 1, "坐  骑", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 10, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnzuoqi.setVisible(false);
            this.add(this.btnzuoqi);
            (this.btnfeixingqi = new AircraftBtn("inkImg/button1/syajh.png", 1, "飞  行", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 11, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnfeixingqi.setVisible(false);
            this.add(this.btnfeixingqi);
            (this.btnzuojia = new AircraftBtn("inkImg/button1/syajh.png", 1, "坐  驾", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 12, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - (67+17), 102, 17);
            this.btnzuojia.setVisible(false);
            this.add(this.btnzuojia);


            ImageIcon hotKeyBackImg2 = new ImageIcon("inkImg/hongmu1/xbbkjlh.png");
            this.hotKeyMethod(hotKeyBackImg2);
            (ZhuJpanel.labbackground = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/S30h.png"));
            ZhuJpanel.labbackground.setBounds(0, 0, 201, 67);
            this.add(ZhuJpanel.labbackground);
            this.setBackground(UIUtils.Color_BACK);
            this.add(this.taskGuideView = new TaskGuideView());
        }
        (this.startTime = new JLabel()).setBounds(15, 5, 180, 20);
        this.startTime.setForeground(Color.YELLOW);
        (this.durationTime = new JLabel()).setBounds(15, 5, 180, 20);
        this.durationTime.setForeground(Color.YELLOW);
        (this.obtainExp = new JLabel()).setBounds(15, 5, 180, 20);
        this.obtainExp.setForeground(Color.YELLOW);
        (this.spendSilver = new JLabel()).setBounds(15, 5, 180, 20);
        this.spendSilver.setForeground(Color.YELLOW);
        (this.spendxy = new JLabel()).setBounds(15, 5, 180, 20);
        this.spendxy.setForeground(Color.YELLOW);
        (this.win = new JLabel()).setBounds(15, 5, 180, 20);
        this.win.setForeground(Color.YELLOW);
        (this.mexp = new JLabel()).setBounds(15, 5, 180, 20);
        this.mexp.setForeground(Color.YELLOW);
        (this.pdexp = new JLabel()).setIcon(new ImageIcon("inkImg/background/泡点背景.png"));
        this.pdexp.setBounds(0, 0, 205, 147);
        (this.tcpdBtn = new FightingBtn("inkImg/background/paodian-tc-btn.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "", 30034)).setBounds(468, 615, 69, 26);
        this.add(this.tcpdBtn);
        ZhuJpanel.currentBattlePetImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RoleData roleData = RoleData.getRoleData();
                roleData.addHelpBb(roleData.getHelpBbId());
                SupportListJframe.getSupportListJframe().getSupportListJpanel().init(roleData.getHelpBbName(roleData.getHelpBb()));
                FormsManagement.showForm(62);
            }
        });
        this.currentBattlePetSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.isPetZhiyuan = !ZhuJpanel.isPetZhiyuan;
                ZhuJpanel.this.uptatePetZhiyuan(true);
            }
        });
        ZhuJpanel.currentBattlelingImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RoleData roleData = RoleData.getRoleData();
                String helpLing = roleData.getPackRecord().getHelpLing();
                if (StringUtils.isBlank(helpLing)) {
                    Lingbao[] lingBaos = RoleLingFa.getRoleLingFa().getLingBaos();
                    List<Lingbao> s = new ArrayList<>();
                    for (Lingbao lingBao : lingBaos) {
                        if (lingBao != null) {
                            s.add(lingBao);
                        }
                    }
                    if (s.size() > 0) {
                        BigDecimal[] array = (BigDecimal[])s.<Lingbao, Lingbao>stream().map(Lingbao::getBaoid).toArray(BigDecimal[]::new);
                        String join = StringUtils.join(array, "|");
                        RoleData.getRoleData().sendHelpLingbao(join);
                    }
                }
                String helpLing2 = RoleData.getRoleData().getPackRecord().getHelpLing();
                List<Lingbao> lingbaoList = new ArrayList<>();
                List<String> delList = new ArrayList<>();
                if (StringUtils.isNotEmpty(helpLing2)) {
                    for (Lingbao lingBao2 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                        if (lingBao2 != null && !helpLing2.contains(lingBao2.getBaoid().toString())) {
                            helpLing2 = helpLing2 + "|" + lingBao2.getBaoid().toString();
                        }
                    }
                    String[] split = helpLing2.split("\\|");
                    for (String s2 : split) {
                        Boolean b = Boolean.valueOf(true);
                        try {
                            for (Lingbao lingBao3 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                                if (lingBao3 != null && lingBao3.getBaoid().toString().equals(s2)) {
                                    lingbaoList.add(lingBao3);
                                    b = Boolean.valueOf(false);
                                    break;
                                }
                            }
                        }
                        catch (Exception e2) {
                            continue;
                        }
                        if ((boolean)b) {
                            delList.add(s2);
                        }
                    }
                    List<String> collect = Arrays.asList(split);
                    List<String> collect2 = new ArrayList<>();
                    for (String s2 : collect) {
                        Boolean b = Boolean.valueOf(true);
                        Iterator<String> iterator2 = delList.iterator();
                        if (iterator2.hasNext()) {
                            String string = (String)iterator2.next();
                            b = Boolean.valueOf(false);
                        }
                        if ((boolean)b) {
                            collect2.add(s2);
                        }
                    }
                    if (collect2.size() > 0) {
                        lingbaoList.clear();
                        String[] lingIds = new String[collect2.size()];
                        collect2.toArray(lingIds);
                        String join2 = StringUtils.join(lingIds, "|");
                        RoleData.getRoleData().sendHelpLingbao(join2);
                        for (String s3 : lingIds) {
                            for (Lingbao lingBao4 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                                if (lingBao4 != null && lingBao4.getBaoid().toString().equals(s3.toString())) {
                                    lingbaoList.add(lingBao4);
                                    break;
                                }
                            }
                        }
                    }
                    LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
                }
                else {
                    LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
                }
                FormsManagement.showForm(622);
            }
        });
        this.currentBattlelingSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.isLingZhiyuan = !ZhuJpanel.isLingZhiyuan;
                ZhuJpanel.this.uptateLingZhiyuan(true);
            }
        });
        ZhuJpanel.currentBattlePetImg.setVisible(false);
        this.currentBattlePetSwitch.setVisible(false);
        this.currentBattlePetLab.setVisible(false);
        ZhuJpanel.currentBattlelingImg.setVisible(false);
        this.currentBattlelingSwitch.setVisible(false);
        this.currentBattlelingLab.setVisible(false);
    }

    public static FormsOnOffBtn getCBGBtn() {
        return ZhuJpanel.CBGBtn;
    }

    public static void setCBGBtn(FormsOnOffBtn CBGBtn) {
        ZhuJpanel.CBGBtn = CBGBtn;
    }

    public JLabel gettouxiang() {
        return this.touxiang;
    }

    public void settouxiang(JLabel touxiang) {
        this.touxiang = touxiang;
    }

    public static FormsOnOffBtn getTJBtn1() {
        return ZhuJpanel.TJBtn1;
    }

    public static void setTJBtn1(FormsOnOffBtn TJBtn1) {
        ZhuJpanel.TJBtn1 = TJBtn1;
    }

    public static FormsOnOffBtn getarenaBtn() {
        return ZhuJpanel.arenaBtn;
    }

    public static void setarenaBtn(FormsOnOffBtn arenaBtn) {
        ZhuJpanel.arenaBtn = arenaBtn;
    }

    public static FormsOnOffBtn getzhuanshen() {
        return ZhuJpanel.zhuanshen;
    }

    public static void setzhuanshen(FormsOnOffBtn zhuanshen) {
        ZhuJpanel.zhuanshen = zhuanshen;
    }

    public static FormsOnOffBtn getzaBtn() {
        return ZhuJpanel.zaBtn;
    }

    public static void setzaBtn(FormsOnOffBtn zaBtn) {
        ZhuJpanel.zaBtn = zaBtn;
    }

    public static FormsOnOffBtn getarenaBtn1() {
        return ZhuJpanel.arenaBtn1;
    }

    public static void setarenaBtn1(FormsOnOffBtn arenaBtn1) {
        ZhuJpanel.arenaBtn1 = arenaBtn1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.cjkg.equals("开")) {}
        this.chehui.setBounds(ScrenceUntil.Screen_x - 29, 264, 26, 26);
        if (FightingMixDeal.State == 2 || FightingMixDeal.State == 1 || FightingMixDeal.State == 3) {
            Graphics2D g2d = (Graphics2D)g;
            Color color = new Color(32, 38, 32, 200);
            Color color2 = new Color(52, 61, 52, 0);
            GradientPaint gradient = new GradientPaint(0.0f, 0.0f, color, 0.0f, (float)(this.getHeight() - 1), color2);
            g2d.setPaint(gradient);
            g2d.fillRoundRect(ScrenceUntil.Screen_x - 29, 290, 23, 100, 0, 0);
            g2d.fillRoundRect(ScrenceUntil.Screen_x - 29, 290, 23, 100, 0, 0);
            g2d.setColor(Color.white);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setFont(UIUtils.TEXT_HY19);
            g2d.drawString("撤", ScrenceUntil.Screen_x - 26, 305);
            g2d.drawString("撤", ScrenceUntil.Screen_x - 26, 305);
            g2d.drawString("回", ScrenceUntil.Screen_x - 26, 331);
            g2d.drawString("回", ScrenceUntil.Screen_x - 26, 331);
        }
        if (Util.ifpf) {
            this.mexp.setText("泡点信息");
            this.mexp.setFont(UIUtils.TEXT_FONTZS);
            this.startTime.setText("开始时间：" + Util.startTimeName);
            this.durationTime.setText("泡点时长：" + this.getPdTime());
            this.obtainExp.setText("获得经验：" + Util.hdzjy);
            this.spendSilver.setText("消费金钱：" + Util.xfzje);
            this.spendxy.setText("消费仙玉：" + Util.xfzxy);
            this.win.setText("胜利场次：" + ((long)Util.xfzxy - 1L));
            if (ScrenceUntil.Screen_x == 1024 && ScrenceUntil.Screen_y == 768) {
                this.startTime.setBounds(405, 530, 180, 20);
                this.durationTime.setBounds(405, 550, 180, 20);
                this.obtainExp.setBounds(405, 570, 180, 20);
                this.spendSilver.setBounds(405, 590, 180, 20);
                this.win.setBounds(405, 590, 180, 20);
                this.spendxy.setBounds(405, 590, 180, 20);
                this.mexp.setBounds(468, 505, 180, 20);
                this.pdexp.setBounds(400, 500, 205, 147);
                this.tcpdBtn.setBounds(468, 615, 69, 26);
            }
            else if (ScrenceUntil.Screen_x == 800 && ScrenceUntil.Screen_y == 600) {
                this.startTime.setBounds(320, 420, 180, 20);
                this.durationTime.setBounds(320, 440, 180, 20);
                this.obtainExp.setBounds(320, 460, 180, 20);
                this.spendSilver.setBounds(320, 480, 180, 20);
                this.win.setBounds(320, 480, 180, 20);
                this.spendxy.setBounds(320, 480, 180, 20);
                this.mexp.setBounds(380, 400, 180, 20);
                this.pdexp.setBounds(310, 400, 205, 147);
                this.tcpdBtn.setBounds(380, 515, 69, 26);
            }
            this.add(this.startTime);
            this.add(this.durationTime);
            this.add(this.obtainExp);
            if (Util.pdtype.equals("2")) {
                this.add(this.spendSilver);
                this.remove(this.spendxy);
            }
            else if (Util.pdtype.equals("3")) {
                this.add(this.spendxy);
                this.remove(this.spendSilver);
            }
            else if (Util.pdtype.equals("1")) {
                this.remove(this.spendSilver);
                this.remove(this.spendxy);
            }
            this.add(this.mexp);
            this.add(this.pdexp);
        }
        else {
            this.remove(this.startTime);
            this.remove(this.durationTime);
            this.remove(this.obtainExp);
            this.remove(this.spendSilver);
            this.remove(this.spendxy);
            this.remove(this.win);
            this.remove(this.mexp);
            this.remove(this.pdexp);
            this.tcpdBtn.setBounds(9968, 9615, 69, 26);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        if (dhb) {
            int newWidth = (int) (obtainGod.getIconWidth() * scale);
            int newHeight = (int) (obtainGod.getIconHeight() * scale);
            g2d.drawImage(obtainGod.getImage(), xPosition, yPosition, newWidth, newHeight, this);
            g2d.dispose();
        }
        for(int i=0;i<yhbztx.length;i++){
            if(yhbztx[i]!=null){
                yhtime[i] += 20;
                yhbztx[i].updateToTime(yhtime[i], 0);
                yhbztx[i].draw(g,ScrenceUntil.Screen_x / 2+yhx[i], ScrenceUntil.Screen_y / 3);
                if (yhbztx[i].getTime() < yhtime[i]) {
                    yhbztx[i] = null;
                }
            }
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            if (TeststateJpanel.getQhnum().equals("1")) {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            else {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58));
            }
        }
        else if (TeststateJpanel.getQhnum().equals("1")) {
            ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
        }
        else {
            ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
        }
    }

    public String getPdTime() {
        String timeName = "00:00:00";
        Long s = Util.startTime;
        Long d = Long.valueOf(new Date().getTime());
        Long c = Long.valueOf((long)d - (long)s);
        timeName = Util.formatTime(c);
        return timeName;
    }

    public void BtnChange() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (MyIsif.getStyle().equals("水墨")) {
            ZhuJpanel.zidong.setBounds(ScrenceUntil.Screen_x - 60, ScrenceUntil.Screen_y - 150, 60, 26);
        }
        else {
            ZhuJpanel.zidong.setBounds(ScrenceUntil.Screen_x - 60, ScrenceUntil.Screen_y - 150, 60, 26);
        }
        this.fashu.setBounds(ScrenceUntil.Screen_x - 90, 200, 60, 26);
        this.daoju.setBounds(ScrenceUntil.Screen_x - 90, 226, 60, 26);
        this.fangyu.setBounds(ScrenceUntil.Screen_x - 90, 252, 60, 26);
        this.baohu.setBounds(ScrenceUntil.Screen_x - 90, 278, 60, 26);
        this.zhaohuan.setBounds(ScrenceUntil.Screen_x - 90, 304, 60, 26);
        this.zhaohui.setBounds(ScrenceUntil.Screen_x - 90, 330, 60, 26);
        this.buzhua.setBounds(ScrenceUntil.Screen_x - 90, 356, 60, 26);
        this.taopao.setBounds(ScrenceUntil.Screen_x - 90, 382, 60, 26);
        ZhuJpanel.qiangtui.setBounds(142, 70, 18, 18);
        this.labnotice.setBounds(0, ScrenceUntil.Screen_y - 161, 70, 25);
        this.labworld.setBounds(0, ScrenceUntil.Screen_y - 134, 70, 25);
        this.labbangs.setBounds(0, ScrenceUntil.Screen_y - 107, 70, 25);
        this.labgroups.setBounds(0, ScrenceUntil.Screen_y - 80, 70, 25);
        this.labnow.setBounds(0, ScrenceUntil.Screen_y - 53, 70, 25);
        this.SendMes.setBounds(75, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x + 60, 25);
        this.jLabelLeftMes.setBounds(0, ScrenceUntil.Screen_y - 27, 2260, 25);
        if (MyIsif.getStyle().equals("水墨")) {
            this.btnzuoqi.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnfeixingqi.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - 63, 117, 24);
            this.btnzuojia.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - (87+24), 117, 24);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 135, 117, 24);
            if (this.cjkg.equals("开")) {
                this.cj.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 303, 117, 24);
            }
            this.yueka.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 279, 117, 24);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 255, 117, 24);
            this.gl.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 231, 117, 24);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 207, 117, 24);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 183, 117, 24);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 159, 117, 24);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 183, 117, 24);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 159, 117, 24);
            ZhuJpanel.ladderPanelBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 111, 117, 24);
            this.btnSpotCard.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnSystemSettings.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 63, 117, 24);
            this.btnOperation.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnPlatform.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 63, 117, 24);
        }
        else {
            this.btnzuoqi.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnfeixingqi.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - 63, 117, 24);
            this.btnzuojia.setBounds(ScrenceUntil.Screen_x - 233, ScrenceUntil.Screen_y - (87+24), 117, 24);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 159, 117, 24);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 135, 117, 24);
            if (this.cjkg.equals("开")) {
                this.cj.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 303, 117, 24);
            }
            this.yueka.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 279, 117, 24);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 255, 117, 24);
            this.gl.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 231, 117, 24);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 207, 117, 24);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 183, 117, 24);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 159, 117, 24);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 159, 117, 24);
            ZhuJpanel.ladderPanelBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 111, 117, 24);
            this.btnSpotCard.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnSystemSettings.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 63, 117, 24);
            this.btnOperation.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 87, 117, 24);
            this.btnPlatform.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 63, 117, 24);
        }
        if (MyIsif.getStyle().equals("水墨")) {
            if (ScrenceUntil.Screen_x == 1024) {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 43, ScrenceUntil.SendMsg_x, 43);
            }
            else if (ScrenceUntil.Screen_x == 800) {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 43, ScrenceUntil.SendMsg_x, 43);
            }
            else {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 43, ScrenceUntil.SendMsg_x, 43);
            }
            this.jLabelLeftMes.setBounds(0, ScrenceUntil.Screen_y - 43, 160, 43);
            ZhuJpanel.lableft.setBounds(9, ScrenceUntil.Screen_y - 50, 17, 17);
            ZhuJpanel.labbottom.setBounds(28, ScrenceUntil.Screen_y - 50, 17, 17);
            ZhuJpanel.labtop.setBounds(47, ScrenceUntil.Screen_y - 50, 17, 17);
            ZhuJpanel.labstar.setBounds(66, ScrenceUntil.Screen_y - 50, 18, 18);
            ZhuJpanel.labadd.setBounds(85, ScrenceUntil.Screen_y - 50, 17, 17);
            ZhuJpanel.labminus.setBounds(104, ScrenceUntil.Screen_y - 50, 17, 17);
        }
        else {
            this.jLabelLeftMes.setBounds(0, ScrenceUntil.Screen_y - 26, 2260, 25);
            if (ScrenceUntil.Screen_x == 1024) {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x, 25);
            }
            else if (ScrenceUntil.Screen_x == 800) {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x, 25);
            }
            else {
                this.jLabelLeftMes1.setBounds(160, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x, 25);
            }
            this.jLabelSendMes.setBounds(0, ScrenceUntil.Screen_y - 26, ScrenceUntil.SendMsg_x + 350, 25);
            ZhuJpanel.lableft.setBounds(9, ScrenceUntil.Screen_y - 47, 20, 20);
            ZhuJpanel.labbottom.setBounds(28, ScrenceUntil.Screen_y - 47, 20, 20);
            ZhuJpanel.labtop.setBounds(47, ScrenceUntil.Screen_y - 47, 20, 20);
            ZhuJpanel.labstar.setBounds(66, ScrenceUntil.Screen_y - 47, 18, 18);
            ZhuJpanel.labadd.setBounds(85, ScrenceUntil.Screen_y - 47, 20, 20);
            ZhuJpanel.labminus.setBounds(104, ScrenceUntil.Screen_y - 48, 20, 20);
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.Dbiaoqing.setBounds(ScrenceUntil.Screen_x - 497, ScrenceUntil.Screen_y - 32, 22, 36);
        }
        else {
            this.Dbiaoqing.setBounds(ScrenceUntil.Screen_x - 497, ScrenceUntil.Screen_y - 32, 22, 36);
        }
        ZhuJpanel.guideBtn.setBounds(20, 88, 53, 53);
        ZhuJpanel.bjczBtn.setBounds(20, 90, 53, 53);
        ZhuJpanel.czlbBtn.setBounds(200, 90, 53, 53);
        ZhuJpanel.pdBtn.setBounds(198, 60, 53, 53);
        ZhuJpanel.GGBtn.setBounds(ScrenceUntil.Screen_x - 65, 110, 53, 53);
        this.caidan.setBounds(0, 88, 13, 53);
        ZhuJpanel.zhsbd.setBounds(20, 90, 47, 49);
        ZhuJpanel.ttBtn.setBounds(80, 90, 49, 49);
        this.showVipBtn.setBounds(0, 142, 13, 53);
        this.showIconBtn.setBounds(ScrenceUntil.Screen_x - 14, 64, 13, 53);
        this.showIconBtnY.setBounds(ScrenceUntil.Screen_x - 13, 110, 13, 53);
        if (MyIsif.getStyle().equals("水墨")) {
            ZhuJpanel.zdrwBtn.setBounds(ScrenceUntil.Screen_x - 468, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.childBtn.setBounds(ScrenceUntil.Screen_x - 429, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.knapsackBtn.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.groupBtn.setBounds(ScrenceUntil.Screen_x - 351, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.pkBtn.setBounds(ScrenceUntil.Screen_x - 312, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.transactionBtn1.setBounds(ScrenceUntil.Screen_x - 273, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.rwjnBtn.setBounds(ScrenceUntil.Screen_x - 234, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.petBtn.setBounds(ScrenceUntil.Screen_x - 195, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.taskBtn.setBounds(ScrenceUntil.Screen_x - 156, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.friendsBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.gangBtn.setBounds(ScrenceUntil.Screen_x - 78, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.systemBtn.setBounds(ScrenceUntil.Screen_x - 39, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.getBtn.setBounds(ScrenceUntil.Screen_x - 311, ScrenceUntil.Screen_y - 63, 117, 24);
            ZhuJpanel.transactionBtn.setBounds(ScrenceUntil.Screen_x - 311, ScrenceUntil.Screen_y - 87, 117, 24);
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 468, ScrenceUntil.Screen_y - 39, 468, 39);
            ZhuJpanel.currentBattlePetImg.setBounds(207, 3, 25, 26);
            this.currentBattlePetSwitch.setBounds(207, 31, 27, 17);
            this.currentBattlePetLab.setBounds(205, 1, 29, 30);
            ZhuJpanel.currentBattlelingImg.setBounds(241, 2, 25, 26);
            this.currentBattlelingSwitch.setBounds(242, 31, 27, 17);
            this.currentBattlelingLab.setBounds(240, 1, 29, 30);
        }
        else {
            ZhuJpanel.zdrwBtn.setBounds(ScrenceUntil.Screen_x - 468, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.childBtn.setBounds(ScrenceUntil.Screen_x - 429, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.knapsackBtn.setBounds(ScrenceUntil.Screen_x - 390, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.groupBtn.setBounds(ScrenceUntil.Screen_x - 351, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.pkBtn.setBounds(ScrenceUntil.Screen_x - 312, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.transactionBtn1.setBounds(ScrenceUntil.Screen_x - 273, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.rwjnBtn.setBounds(ScrenceUntil.Screen_x - 234, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.petBtn.setBounds(ScrenceUntil.Screen_x - 195, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.taskBtn.setBounds(ScrenceUntil.Screen_x - 156, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.friendsBtn.setBounds(ScrenceUntil.Screen_x - 117, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.gangBtn.setBounds(ScrenceUntil.Screen_x - 78, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.systemBtn.setBounds(ScrenceUntil.Screen_x - 39, ScrenceUntil.Screen_y - 39, 39, 39);
            ZhuJpanel.getBtn.setBounds(ScrenceUntil.Screen_x - 311, ScrenceUntil.Screen_y - 63, 117, 24);
            ZhuJpanel.transactionBtn.setBounds(ScrenceUntil.Screen_x - 311, ScrenceUntil.Screen_y - 87, 117, 24);
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 468, ScrenceUntil.Screen_y - 39, 468, 39);
            ZhuJpanel.currentBattlePetImg.setBounds(207, 3, 25, 26);
            this.currentBattlePetSwitch.setBounds(207, 31, 27, 17);
            this.currentBattlePetLab.setBounds(205, 1, 29, 30);
            ZhuJpanel.currentBattlelingImg.setBounds(241, 2, 25, 26);
            this.currentBattlelingSwitch.setBounds(242, 31, 27, 17);
            this.currentBattlelingLab.setBounds(240, 1, 29, 30);
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 253, 6, 71, 12);
            this.bbhp.setBounds(ScrenceUntil.Screen_x - 253, 21, 71, 12);
            this.bbmp.setBounds(ScrenceUntil.Screen_x - 253, 36, 71, 12);
            this.roleexe.setBounds(ScrenceUntil.Screen_x - 104, 8, 99, 14);
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 104, 25, 99, 14);
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 104, 42, 99, 14);
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 178, 1, 178, 72);
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 314, 0, 136, 58);
            this.dangqian.setBounds(6, ScrenceUntil.Screen_y - 26, 51, 25);
            ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
            ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
        }
        else {
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 253, 7, 71, 12);
            this.bbhp.setBounds(ScrenceUntil.Screen_x - 253, 22, 71, 12);
            this.bbmp.setBounds(ScrenceUntil.Screen_x - 253, 37, 71, 12);
            this.roleexe.setBounds(ScrenceUntil.Screen_x - 104, 8, 99, 14);
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 104, 25, 99, 14);
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 104, 42, 99, 14);
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 178, 1, 178, 72);
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 314, 0, 136, 58);
            this.dangqian.setBounds(3, ScrenceUntil.Screen_y - 26, 51, 25);
            ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
            ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
        }
        this.hotKeyBack.setBounds(ScrenceUntil.Screen_x - 321, ScrenceUntil.Screen_y - 81, 321, 41);
        ZhuJpanel.hotKey.setBounds(ScrenceUntil.Screen_x - 333, ScrenceUntil.Screen_y - 79, 12, 37);
        if (this.teamHeads != null) {
            for (int i = 0; i < this.teamHeads.length; ++i) {
                if (this.teamHeads[i] != null) {
                    this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x + i * 52 + 70, 0, 52, 52);
                }
            }
        }
        for (int i = 0; i < this.aircraftBtns.length; ++i) {
            this.aircraftBtns[i].setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (64 - i * 17), 102, 17);
        }
        if (this.teamOperations != null) {
            for (int i = 0; i < this.teamOperations.length; ++i) {
                if (i != 0 && this.teamOperations[i] != null) {
                    this.teamOperations[i].setBounds(ScrenceUntil.TeamImg_x + 122 + i * 52, 34, 18, 18);
                }
            }
        }
    }

    public void creatgoodtext(Object object) {
        GoodsMsgJframe goodsMsgJframe = GoodsMsgJframe.getGoodsMsgJframe();
        int height = 0;
        if (object instanceof Goodstable) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showGood((Goodstable)object);
        }
        else if (object instanceof Shop) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showShop((Shop)object);
        }
        else if (object instanceof Eshop) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showEshop((Eshop)object);
        }
        else if (object instanceof PayvipBean) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showPayvipBean((PayvipBean)object);
        }
        else if (object instanceof RoleTxBean) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showRoleTxBean((RoleTxBean)object);
        }else if (object instanceof XuanBao) {
            height = goodsMsgJframe.getGoodsMsgJpanel().showXuanbao((XuanBao)object);
        }

        Point goodx = this.framepoint(GameJpanel.getGameJpanel().getMousePosition(), goodsMsgJframe.getSize());
        goodsMsgJframe.setBounds((int)goodx.getX(), (int)goodx.getY(), 500, height);
        FormsManagement.showForm(24);
    }

    public void creatlingtext(Lingbao lingbao) {
        Point goodx = this.framepoint(GameJpanel.getGameJpanel().getMousePosition(), LingMsgJframe.getLingMsgJpanel().getSize());
        LingMsgJframe.getLingMsgJpanel().setBounds((int)goodx.getX(), (int)goodx.getY(), LingMsgJframe.getLingMsgJpanel().getWidth(), LingMsgJframe.getLingMsgJpanel().getHeight());
        LingMsgJframe.getLingMsgJpanel().getJpanel().lingbaoshow(lingbao);
        FormsManagement.showForm(45);
    }

    public void creatpettext(RoleSummoning roleSummoning) {
        PetsMsgJframe petsMsgJframe = PetsMsgJframe.getPetsMsgJframe();
        petsMsgJframe.setSize(400, petsMsgJframe.getPetsMsgJpanel().petMsg(roleSummoning, null));
        Point goodx = this.framepoint(GameJpanel.getGameJpanel().getMousePosition(), petsMsgJframe.getSize());
        petsMsgJframe.setBounds((int)goodx.getX(), (int)goodx.getY(), 500, petsMsgJframe.getPetsMsgJpanel().getGheight());
        FormsManagement.showForm(42);
    }

    public void creatroletext(BigDecimal id, String name) {
        Point goodx = this.framepoint(GameJpanel.getGameJpanel().getMousePosition(), RoleMsgJframe.getRoleMsgJframe().getSize());
        RoleMsgJframe.getRoleMsgJframe().getJpanel().show(id, name);
        RoleMsgJframe.getRoleMsgJframe().setBounds(0, 134, 160, 185);
        FormsManagement.showForm(77);
    }

    public void creatgoodtext2(Goodstable goodstable) {
        int height = GoodsMsgJframe.getGoodsMsgJframe().getGoodsMsgJpanel().showGood(goodstable);
        Point goodx = this.framepoint(null, GoodsMsgJframe.getGoodsMsgJframe().getSize());
        GoodsMsgJframe.getGoodsMsgJframe().setBounds((int)goodx.getX(), (int)goodx.getY(), 500, height);
        FormsManagement.showForm(24);
    }

    public void creatlingtext2(Lingbao lingbao) {
        Point goodx = this.framepoint(null, LingMsgJframe.getLingMsgJpanel().getSize());
        LingMsgJframe.getLingMsgJpanel().setBounds((int)goodx.getX(), (int)goodx.getY(), LingMsgJframe.getLingMsgJpanel().getWidth(), LingMsgJframe.getLingMsgJpanel().getHeight());
        LingMsgJframe.getLingMsgJpanel().getJpanel().lingbaoshow(lingbao);
        FormsManagement.showForm(45);
    }

    public void creatpettext2(RoleSummoning roleSummoning) {
        PetsMsgJframe petsMsgJframe = PetsMsgJframe.getPetsMsgJframe();
        petsMsgJframe.setSize(400, petsMsgJframe.getPetsMsgJpanel().petMsg(roleSummoning, roleSummoning.getInnerGoodList()));
        Point goodx = this.framepoint(null, petsMsgJframe.getSize());
        petsMsgJframe.setBounds((int)goodx.getX(), (int)goodx.getY(), 500, petsMsgJframe.getPetsMsgJpanel().getGheight());
        FormsManagement.showForm(42);
    }

    public void cleargoodtext() {
        FormsManagement.HideForm(24);
    }

    public void clearlingtext() {
        FormsManagement.HideForm(45);
    }

    public void pettext() {
        FormsManagement.HideForm(42);
    }

    public void roletext() {
        FormsManagement.HideForm(77);
    }

    public Point framepoint(Point mouse, Dimension dimension) {
        int fw = TestMain.gameJframe.getWidth();
        int fh = TestMain.gameJframe.getHeight();
        if (mouse == null) {
            mouse = GameJpanel.getGameJpanel().getMousePosition();
            Point point = new Point((int)(((double)fw - dimension.getWidth()) / 2.0), (int)(((double)fh - dimension.getHeight()) / 2.0));
            if (mouse == null) {
                return point;
            }
            if (point.getX() > mouse.getX() || point.getY() > mouse.getY() || point.getX() + dimension.getWidth() < mouse.getX() || point.getY() + dimension.getHeight() < mouse.getY()) {
                return point;
            }
        }
        if (mouse.getX() + dimension.getWidth() + 10.0 <= (double)fw && mouse.getY() + dimension.getHeight() + 10.0 <= (double)fh) {
            return new Point((int)mouse.getX() + 10, (int)mouse.getY() + 10);
        }
        if (mouse.getX() + dimension.getWidth() + 10.0 <= (double)fw && mouse.getY() - dimension.getHeight() - 10.0 > 0.0) {
            return new Point((int)mouse.getX() + 10, (int)mouse.getY() - (int)dimension.getHeight() - 10);
        }
        if (mouse.getX() - dimension.getWidth() - 10.0 > 0.0 && mouse.getY() - dimension.getHeight() - 10.0 > 0.0) {
            return new Point((int)mouse.getX() - (int)dimension.getWidth() - 10, (int)mouse.getY() - (int)dimension.getHeight() - 10);
        }
        if (mouse.getX() - dimension.getWidth() - 10.0 > 0.0 && mouse.getY() + dimension.getHeight() + 10.0 <= (double)fh) {
            return new Point((int)mouse.getX() - (int)dimension.getWidth() - 10, (int)mouse.getY() + 10);
        }
        if (mouse.getX() + dimension.getWidth() + 10.0 <= (double)fw) {
            return new Point((int)mouse.getX() + 10, (int)mouse.getY() - fh / 2 + 10);
        }
        return new Point((int)mouse.getX() - (int)dimension.getWidth() - 10, (int)mouse.getY() - fh / 2 + 10);
    }

    public void uptatePetZhiyuan(boolean is) {
        if (ZhuJpanel.isPetZhiyuan) {
            this.currentBattlePetSwitch.setIcon(new ImageIcon("inkImg/button1/kqzy.png"));
        }
        else {
            this.currentBattlePetSwitch.setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
        }
        if (is) {
            SupportListJframe.getSupportListJframe().getSupportListJpanel().uptateZhiyuan();
        }
    }

    public void uptateLingZhiyuan(boolean is) {
        if (ZhuJpanel.isLingZhiyuan) {
            this.currentBattlelingSwitch.setIcon(new ImageIcon("inkImg/button1/kqzy.png"));
        }
        else {
            this.currentBattlelingSwitch.setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
        }
        if (is) {
            LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().uptateZhiyuan();
        }
    }

    public static void removerTXK() {
        ZhuJpanel.txk = "0";
        ZhuJpanel.txk1 = "0";
        ZhuJpanel.txk2 = "0";
        ZhuJpanel.txk3 = "0";
        ZhuJpanel.txk4 = "0";
    }

    public void setTXK(Graphics g) {
        if (FightingMixDeal.State == 0) {
            if (ScrenceUntil.Screen_x == 1366) {
                this.drawRole(g, ZhuJpanel.txk, ScrenceUntil.TeamImg_x + 105);
                this.drawRole(g, ZhuJpanel.txk1, ScrenceUntil.TeamImg_x + 175);
                this.drawRole(g, ZhuJpanel.txk2, ScrenceUntil.TeamImg_x + 245);
                this.drawRole(g, ZhuJpanel.txk3, ScrenceUntil.TeamImg_x + 315);
                this.drawRole(g, ZhuJpanel.txk4, ScrenceUntil.TeamImg_x + 385);
            }
            else if (ScrenceUntil.Screen_x == 1024) {
                this.drawRole(g, ZhuJpanel.txk, ScrenceUntil.TeamImg_x - 45);
                this.drawRole(g, ZhuJpanel.txk1, ScrenceUntil.TeamImg_x + 25);
                this.drawRole(g, ZhuJpanel.txk2, ScrenceUntil.TeamImg_x + 95);
                this.drawRole(g, ZhuJpanel.txk3, ScrenceUntil.TeamImg_x + 165);
                this.drawRole(g, ZhuJpanel.txk4, ScrenceUntil.TeamImg_x + 235);
            }
            else {
                this.drawRole(g, ZhuJpanel.txk, ScrenceUntil.TeamImg_x + 55);
                this.drawRole(g, ZhuJpanel.txk1, ScrenceUntil.TeamImg_x + 125);
                this.drawRole(g, ZhuJpanel.txk2, ScrenceUntil.TeamImg_x + 195);
                this.drawRole(g, ZhuJpanel.txk3, ScrenceUntil.TeamImg_x + 265);
                this.drawRole(g, ZhuJpanel.txk4, ScrenceUntil.TeamImg_x + 335);
            }
        }
    }

    private void drawRole(Graphics g, String txk, int x) {
        if (txk != null && !txk.equals("0")) {
            Sprite role1 = SpriteFactory.Prepare("inkImg/txk/tx" + txk + ".tcp");
            if (role1 != null) {
                role1.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                role1.draw(g, x, 35);
            }
        }
        else {
            g.clearRect(x, 35, 0, 0);
        }
    }

    @Override
    public void paint(Graphics g) {
        ZhuJpanel.Mall.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        ZhuJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        ZhuJpanel.Mall.draw(g, this.mallX, 5);
        ZhuJpanel.tcp.draw(g, this.mallX, 5);
        ZhuJpanel.tcp1.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        ZhuJpanel.tcp2.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        super.paint(g);
        Sprite role = SpriteFactory.Prepare(RoleTX.getLabroletxk());
        if (role != null) {
            role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
            role.draw(g, ScrenceUntil.Screen_x - 143, 35);
        }
        this.setTXK(g);
        if (this.cjkg == null || this.cjkg.equals("开")) {}
        if (ZhuJpanel.styles) {
            ZhuJpanel.styles = false;
            this.ssx();
        }
    }

    public void addPrompt(String text) {
        Music.addyinxiao("提示音2.mp3");
        GameJpanel.getGameJpanel().addPrompt("#Y" + text);
    }

    public void addPrompt2(String text) {
        Music.addyinxiao("提示音2.mp3");
        if ("退出战斗".equals(text)) {
            FightingMixDeal.FightingEnd();
            return;
        }
        FrameMessageChangeJpanel.addtext(6, text, null, null, null, null);
        if (text.contains("经验") || text.contains("获得")) {
            text = text.replace("#Y", "#R");
            text = text.replace("#W", "#Y");
        }
        GameJpanel.getGameJpanel().addPrompt("#Y" + text);
    }

    public void addPrompt3(String text) {
        FrameMessageChangeJpanel.addtext(6, text, null, null);
    }

    public void SXTeam(RoleShow roleShow, TeamBean teamBean) {
        if (teamBean == null) {
            if (this.teamHeads != null) {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setVisible(false);
                    }
                }
            }
            if (this.teamOperations != null) {
                for (int i = 0; i < this.teamOperations.length; ++i) {
                    if (this.teamOperations[i] != null) {
                        this.teamOperations[i].setVisible(false);
                    }
                }
            }
        }
        else {
            boolean isCaptian = ((TeamRole)teamBean.getTeams().get(0)).getRoleId().compareTo(roleShow.getRole_id()) == 0;
            int j = 0;
            int length = teamBean.getTeams().size();
            while (j < 5) {
                TeamRole teamRole = (j < length) ? ((TeamRole)teamBean.getTeams().get(j)) : null;
                if (teamRole == null) {
                    if (this.teamHeads != null && this.teamHeads[j] != null) {
                        this.teamHeads[j].setVisible(false);
                    }
                    if (this.teamOperations != null && j != 0 && this.teamOperations[j - 1] != null) {
                        this.teamOperations[j - 1].setVisible(false);
                    }
                    return;
                }
                else {
                    if (teamRole.getTxk() != 0) {
                        RoleTxBean txBean = UserMessUntil.getTxBean(((TeamRole)teamBean.getTeams().get(j)).getTxk());
                        if (j == 0) {
                            ZhuJpanel.txk = txBean.getRdId() + "";
                        }
                        if (j == 1) {
                            ZhuJpanel.txk1 = txBean.getRdId() + "";
                        }
                        if (j == 2) {
                            ZhuJpanel.txk2 = txBean.getRdId() + "";
                        }
                        if (j == 3) {
                            ZhuJpanel.txk3 = txBean.getRdId() + "";
                        }
                        if (j == 4) {
                            ZhuJpanel.txk4 = txBean.getRdId() + "";
                        }
                    }
                    if (this.teamHeads == null) {
                        this.teamHeads = new JLabel[5];
                    }
                    if (this.teamHeads[j] == null) {
                        this.teamHeads[j] = new JLabel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                if (MyIsif.getStyle().equals("水墨")) {
                                    ZhuJpanel.this.icon = ZhuJpanel.this.IconSM;
                                }
                                else {
                                    ZhuJpanel.this.icon = ZhuJpanel.this.IconHM;
                                }
                                g.drawImage(ZhuJpanel.this.icon.getImage(), 0, 0, null);
                                g.translate(6, 0);
                                super.paintComponent(g);
                                g.translate(-6, 0);
                            }
                        };
                        if (ScrenceUntil.Screen_x == 1366) {
                            this.teamHeads[j].setBounds(ScrenceUntil.TeamImg_x + j * 71 + 70, 0, 71, 72);
                        }
                        else if (ScrenceUntil.Screen_x == 1024) {
                            this.teamHeads[j].setBounds(ScrenceUntil.TeamImg_x - 150 + j * 71 + 70, 0, 71, 72);
                        }
                        else {
                            this.teamHeads[j].setBounds(ScrenceUntil.TeamImg_x - 50 + j * 71 + 70, 0, 71, 72);
                        }
                        this.add(this.teamHeads[j]);
                    }
                    ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                    Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                    Configure configure = (Configure)item.get(new BigDecimal(1));
                    String nao = "新";
                    if (configure.getNeworold() != null) {
                        nao = configure.getNeworold();
                    }
                    if (nao.equals("新")) {
                        if (TeststateJpanel.getQhnum().equals("1")) {
                            this.teamHeads[j].setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + teamRole.getSpeciesId() + ".png", 58, 58));
                        }
                        else {
                            this.teamHeads[j].setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + "-1.png", 58, 58));
                        }
                        this.teamHeads[j].setVisible(true);
                    }
                    else {
                        if (TeststateJpanel.getQhnum().equals("1")) {
                            this.teamHeads[j].setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + teamRole.getSpeciesId() + ".png", 58, 58));
                        }
                        else {
                            this.teamHeads[j].setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + ".png", 58, 58));
                        }
                        this.teamHeads[j].setVisible(true);
                    }
                    if (j != 0) {
                        String text = null;
                        if (isCaptian && teamRole.getState() < 0) {
                            text = "召";
                        }
                        else if (teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                            text = ((teamRole.getState() >= 0) ? "离" : "归");
                        }
                        if (text != null) {
                            if (this.teamOperations == null) {
                                this.teamOperations = new TeamPanelBtn[4];
                            }
                            if (this.teamOperations[j - 1] == null) {
                                if (MyIsif.getStyle().equals("水墨")) {
                                    this.teamOperations[j - 1] = new TeamPanelBtn("inkImg/button/19.png", 1, j, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT);
                                }
                                else {
                                    this.teamOperations[j - 1] = new TeamPanelBtn("inkImg/hongmu/B31h.png", 1, j, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT);
                                }
                                if (ScrenceUntil.Screen_x == 1366) {
                                    this.teamOperations[j - 1].setBounds(ScrenceUntil.TeamImg_x + j * 71 + 70, 54, 18, 18);
                                }
                                else if (ScrenceUntil.Screen_x == 1024) {
                                    this.teamOperations[j - 1].setBounds(ScrenceUntil.TeamImg_x - 150 + j * 71 + 70, 54, 18, 18);
                                }
                                else {
                                    this.teamOperations[j - 1].setBounds(ScrenceUntil.TeamImg_x - 50 + j * 71 + 70, 54, 18, 18);
                                }
                                this.add(this.teamOperations[j - 1], 0);
                            }
                            this.teamOperations[j - 1].setText(text);
                            this.teamOperations[j - 1].setVisible(true);
                        }
                        else if (this.teamOperations != null && this.teamOperations[j - 1] != null) {
                            this.teamOperations[j - 1].setVisible(false);
                        }
                    }
                    ++j;
                }
            }
        }
    }

    public void fightingbtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            (ZhuJpanel.zidong = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "自动", 0)).setBounds(ScrenceUntil.Screen_x - 135, 80, 59, 24);
            (this.fashu = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "法术", 1)).setBounds(ScrenceUntil.Screen_x - 70, 160, 59, 24);
            (this.daoju = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "道具", 2)).setBounds(ScrenceUntil.Screen_x - 70, 186, 59, 24);
            (this.fangyu = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "防御", 3)).setBounds(ScrenceUntil.Screen_x - 70, 212, 59, 24);
            (this.baohu = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "保护", 4)).setBounds(ScrenceUntil.Screen_x - 70, 238, 59, 24);
            (this.zhaohuan = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "召唤", 5)).setBounds(ScrenceUntil.Screen_x - 70, 264, 59, 24);
            (this.zhaohui = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "召回", 6)).setBounds(ScrenceUntil.Screen_x - 70, 290, 59, 24);
            (this.buzhua = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "捕抓", 7)).setBounds(ScrenceUntil.Screen_x - 70, 316, 59, 24);
            (this.taopao = new FightingBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "逃跑", 8)).setBounds(ScrenceUntil.Screen_x - 70, 342, 59, 24);
            (this.chehui = new FightingBtn("inkImg/button/zdchs.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT21, "", 9)).setBounds(ScrenceUntil.Screen_x - 30, 368, 26, 26);
            (ZhuJpanel.qiangtui = new FightingBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT2, "退", 11)).setBounds(142, 70, 18, 18);
            (this.xiang = new GoodPanelBtn(null, "inkImg/button1/B31.png", 1, "详")).setBounds(120, 70, 18, 18);
            this.xiang.setVisible(false);
            this.add(this.xiang);
        }
        else {
            (ZhuJpanel.zidong = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "自动", 0)).setBounds(ScrenceUntil.Screen_x - 70, 420, 60, 26);
            (this.fashu = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "法术", 1)).setBounds(ScrenceUntil.Screen_x - 70, 160, 60, 26);
            (this.daoju = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "道具", 2)).setBounds(ScrenceUntil.Screen_x - 70, 186, 60, 26);
            (this.fangyu = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "防御", 3)).setBounds(ScrenceUntil.Screen_x - 70, 212, 60, 26);
            (this.baohu = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "保护", 4)).setBounds(ScrenceUntil.Screen_x - 70, 238, 60, 26);
            (this.zhaohuan = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "召唤", 5)).setBounds(ScrenceUntil.Screen_x - 70, 264, 60, 26);
            (this.zhaohui = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "召回", 6)).setBounds(ScrenceUntil.Screen_x - 70, 290, 60, 26);
            (this.buzhua = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "捕捉", 7)).setBounds(ScrenceUntil.Screen_x - 70, 316, 60, 26);
            (this.taopao = new FightingBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "逃跑", 8)).setBounds(ScrenceUntil.Screen_x - 70, 342, 60, 26);
            (this.chehui = new FightingBtn("inkImg/hongmu/zdch.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT21, "", 9)).setBounds(ScrenceUntil.Screen_x - 30, 368, 26, 26);
            (ZhuJpanel.qiangtui = new FightingBtn("inkImg/hongmu/B31h.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT2, "退", 11)).setBounds(142, 70, 18, 18);
            (this.xiang = new GoodPanelBtn(null, "inkImg/hongmu/B31h.png", 1, "详")).setBounds(120, 70, 18, 18);
            this.xiang.setVisible(false);
            this.add(this.xiang);
        }
    }

    public void ShowManBtn(boolean is) {
        FightingMonitor.mousestate = 0;
        this.add(this.fashu);
        this.add(this.daoju);
        this.add(this.fangyu);
        this.add(this.baohu);
        this.add(this.zhaohuan);
        this.add(this.zhaohui);
        this.add(this.buzhua);
        this.add(this.taopao);
        this.add(this.chehui);
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (is) {
                    if (this.baohu.getBtn() != -1) {
                        this.baohu.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                        this.baohu.setBtn(-1);
                    }
                    if (this.zhaohui.getBtn() != -1) {
                        this.zhaohui.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                        this.zhaohui.setBtn(-1);
                    }
                    if (this.buzhua.getBtn() != -1) {
                        this.buzhua.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                        this.buzhua.setBtn(-1);
                    }
                }
                else {
                    if (this.fashu.getBtn() != 1) {
                        this.fashu.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                        this.fashu.setBtn(1);
                    }
                    if (this.daoju.getBtn() != 1) {
                        this.daoju.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                        this.daoju.setBtn(1);
                    }
                    if (this.baohu.getBtn() != 1) {
                        this.baohu.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                        this.baohu.setBtn(1);
                    }
                    if (this.zhaohui.getBtn() != 1) {
                        this.zhaohui.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                        this.zhaohui.setBtn(1);
                    }
                    if (this.buzhua.getBtn() != 1) {
                        this.buzhua.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                        this.buzhua.setBtn(1);
                    }
                }
            }
            else if (is) {
                if (this.baohu.getBtn() != -1) {
                    this.baohu.setIcon(CutButtonImage.getImage("inkImg/hongmu/w50.png", -1, -1));
                    this.baohu.setBtn(-1);
                }
                if (this.zhaohui.getBtn() != -1) {
                    this.zhaohui.setIcon(CutButtonImage.getImage("inkImg/hongmu/w50.png", -1, -1));
                    this.zhaohui.setBtn(-1);
                }
                if (this.buzhua.getBtn() != -1) {
                    this.buzhua.setIcon(CutButtonImage.getImage("inkImg/hongmu/w50.png", -1, -1));
                    this.buzhua.setBtn(-1);
                }
            }
            else {
                if (this.fashu.getBtn() != 1) {
                    this.fashu.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.fashu.setBtn(1);
                }
                if (this.daoju.getBtn() != 1) {
                    this.daoju.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.daoju.setBtn(1);
                }
                if (this.baohu.getBtn() != 1) {
                    this.baohu.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.baohu.setBtn(1);
                }
                if (this.zhaohui.getBtn() != 1) {
                    this.zhaohui.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.zhaohui.setBtn(1);
                }
                if (this.buzhua.getBtn() != 1) {
                    this.buzhua.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.buzhua.setBtn(1);
                }
            }
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public void HideBeastBtn() {
        this.remove(this.fashu);
        this.remove(this.daoju);
        this.remove(this.fangyu);
        this.remove(this.baohu);
        this.remove(this.zhaohuan);
        this.remove(this.zhaohui);
        this.remove(this.buzhua);
        this.remove(this.taopao);
    }

    public void ShowBeastBtn() {
        FightingMonitor.mousestate = 0;
        this.add(this.fashu);
        this.add(this.daoju);
        this.add(this.fangyu);
        this.add(this.baohu);
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.fashu.getBtn() != 1) {
                    this.fashu.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                    this.fashu.setBtn(1);
                }
                if (this.daoju.getBtn() != 1) {
                    this.daoju.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                    this.daoju.setBtn(1);
                }
                if (this.baohu.getBtn() != 1) {
                    this.baohu.setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                    this.baohu.setBtn(1);
                }
            }
            else {
                if (this.fashu.getBtn() != 1) {
                    this.fashu.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.fashu.setBtn(1);
                }
                if (this.daoju.getBtn() != 1) {
                    this.daoju.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.daoju.setBtn(1);
                }
                if (this.baohu.getBtn() != 1) {
                    this.baohu.setIcons(CutButtonImage.cuts("inkImg/hongmu/w50.png"));
                    this.baohu.setBtn(1);
                }
            }
        }
        catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public void outFighting() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (this.teamHeads != null) {
            if (ScrenceUntil.Screen_x == 1366) {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x + i * 71 + 70, 0, 71, 72);
                    }
                }
            }
            else if (ScrenceUntil.Screen_x == 1024) {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x - 150 + i * 71 + 70, 0, 71, 72);
                    }
                }
            }
            else {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x - 50 + i * 71 + 70, 0, 71, 72);
                    }
                }
            }
        }
        for (int i = 0; i < this.aircraftBtns.length; ++i) {
            this.aircraftBtns[i].setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (64 - i * 17), 102, 17);
        }
        if (this.teamOperations != null) {
            RoleData rl = RoleData.getRoleData();
            if (rl.getTeamBean() != null&&rl.getTeamBean().getTeams().size()>0) {
                if (ScrenceUntil.Screen_x == 1366) {
                    for (int i = 0; i < this.teamOperations.length; ++i) {
                        if (i != 0 && this.teamOperations[i - 1] != null&&rl.getTeamBean().getTeams().size()>i&&rl.getTeamBean().getTeams().get(i).getState()<0) {
                            this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x + 70 + i * 71, 54, 18, 18);
                            this.teamOperations[i - 1].setVisible(true);
                        }
                    }
                }
                else if (ScrenceUntil.Screen_x == 1024) {
                    for (int i = 0; i < this.teamOperations.length; ++i) {
                        if (i != 0 && this.teamOperations[i - 1] != null&&rl.getTeamBean().getTeams().size()>i&&rl.getTeamBean().getTeams().get(i).getState()<0) {
                            this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x - 150 + 70 + i * 71, 54, 18, 18);
                            this.teamOperations[i - 1].setVisible(true);
                        }
                    }
                }
                else {
                    for (int i = 0; i < this.teamOperations.length; ++i) {
                        if (i != 0 && this.teamOperations[i - 1] != null&&rl.getTeamBean().getTeams().size()>i&&rl.getTeamBean().getTeams().get(i).getState()<0) {
                            this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x - 50 + 70 + i * 71, 54, 18, 18);
                            this.teamOperations[i - 1].setVisible(true);
                        }
                    }
                }
            }

        }
        this.ShopingMenu.setBounds(205, 5, 35, 35);
        FightingMonitor.mousestate = 0;
        this.remove(ZhuJpanel.zidong);
        this.remove(this.fashu);
        this.remove(this.daoju);
        this.remove(this.fangyu);
        this.remove(this.baohu);
        this.remove(this.zhaohuan);
        this.remove(this.zhaohui);
        this.remove(this.buzhua);
        this.remove(this.taopao);
        this.remove(this.chehui);
        this.remove(ZhuJpanel.qiangtui);
        this.limitShopBtn.setVisible(true);
        this.taskGuideView.setVisible(this.taskGuideView.isV());
        ZhuJpanel.CBGBtn.setVisible(true);
        ZhuJpanel.hdrlBtn.setVisible(true);
        ZhuJpanel.TJBtn.setVisible(true);
        ZhuJpanel.mapiconBtn.setVisible(true);
        ZhuJpanel.worldMap.setVisible(true);
        this.chongjiBtn.setVisible(true);
        this.caidan.setVisible(true);
        ZhuJpanel.zhsbd.setVisible(true);
        ZhuJpanel.ttBtn.setVisible(true);
        ZhuJpanel.knapsackBtn.setVisible(true);
        ZhuJpanel.groupBtn.setVisible(true);
        ZhuJpanel.pkBtn.setVisible(true);
        ZhuJpanel.transactionBtn1.setVisible(true);
        ZhuJpanel.taskBtn.setVisible(true);
        this.lowerRightImgBack.setVisible(false);
        this.xiang.setVisible(false);
        ZhuJpanel.GGBtn.setVisible(true);
        this.showIconBtnY.setVisible(true);
        ZhuJpanel.currentBattlePetImg.setVisible(false);
        this.currentBattlePetSwitch.setVisible(false);
        this.currentBattlePetLab.setVisible(false);
        ZhuJpanel.currentBattlelingImg.setVisible(false);
        this.currentBattlelingSwitch.setVisible(false);
        this.currentBattlelingLab.setVisible(false);
        ZhuJpanel.pdBtn.setVisible(true);
        ZhuJpanel.bjczBtn.setVisible(true);
        ZhuJpanel.bsynBtn.setVisible(true);
//        ZhuJpanel.gzBtn.setVisible(true);
        ZhuJpanel.MapName.setVisible(true);
        ZhuJpanel.xAndY.setVisible(true);
        ZhuJpanel.Timemiao.setVisible(true);
        ZhuJpanel.RanklistBtn.setVisible(true);
        this.ShopingMenu.setVisible(true);
        if (configure.getCjgnkg().equals("开")) {
            this.DrawMenu.setVisible(true);
        }
        ZhuJpanel.labbackground.setVisible(true);
        ZhuJpanel.labpetimg.setVisible(true);
        ZhuJpanel.petlehead.setVisible(true);
        ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
        ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 314, 0, 136, 58);
        if (MyIsif.getStyle().equals("水墨")) {
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 253, 6, 71, 12);
            this.bbhp.setBounds(ScrenceUntil.Screen_x - 253, 21, 71, 12);
            this.bbmp.setBounds(ScrenceUntil.Screen_x - 253, 36, 71, 12);
        }
        else {
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 253, 7, 71, 12);
            this.bbhp.setBounds(ScrenceUntil.Screen_x - 253, 22, 71, 12);
            this.bbmp.setBounds(ScrenceUntil.Screen_x - 253, 37, 71, 12);
        }
        this.mallX = 210;
        if (RolePetResistanceJframe.getResistancejframe().isVisible()) {
            FormsManagement.HideForm(58);
        }
        if (SummonJframe.getSummonJframe().isVisible()) {
            FormsManagement.HideForm(710);
        }
        if (RolePetResistanceJframe.getBaoResistancejframe().isVisible()) {
            FormsManagement.HideForm(711);
        }
    }

    public void intoFighting() {
        if (this.teamHeads != null) {
            if (ScrenceUntil.Screen_x == 1366) {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x + i * 71 + 70, 0, 0, 0);
                    }
                }
            }
            else if (ScrenceUntil.Screen_x == 1024) {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x - 150 + i * 71 + 70, 0, 0, 0);
                    }
                }
            }
            else {
                for (int i = 0; i < this.teamHeads.length; ++i) {
                    if (this.teamHeads[i] != null) {
                        this.teamHeads[i].setBounds(ScrenceUntil.TeamImg_x - 50 + i * 71 + 70, 0, 0, 0);
                    }
                }
            }
        }
        for (int i = 0; i < this.aircraftBtns.length; ++i) {
            this.aircraftBtns[i].setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (64 - i * 17), 102, 17);
        }
        if (this.teamOperations != null) {
            if (ScrenceUntil.Screen_x == 1366) {
                for (int i = 0; i < this.teamOperations.length; ++i) {
                    if (i != 0 && this.teamOperations[i - 1] != null) {
                        this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x + 70 + i * 71, 54, 0, 0);
                        this.teamOperations[i - 1].setVisible(false);
                    }
                }
            }
            else if (ScrenceUntil.Screen_x == 1024) {
                for (int i = 0; i < this.teamOperations.length; ++i) {
                    if (i != 0 && this.teamOperations[i - 1] != null) {
                        this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x - 150 + 70 + i * 71, 54, 0, 0);
                        this.teamOperations[i - 1].setVisible(false);
                    }
                }
            }
            else {
                for (int i = 0; i < this.teamOperations.length; ++i) {
                    if (i != 0 && this.teamOperations[i - 1] != null) {
                        this.teamOperations[i - 1].setBounds(ScrenceUntil.TeamImg_x - 50 + 70 + i * 71, 54, 0, 0);
                        this.teamOperations[i - 1].setVisible(false);
                    }
                }
            }
        }
        FightingMonitor.mousestate = 0;
        this.remove(ZhuJpanel.zidong);
        this.remove(this.fashu);
        this.remove(this.daoju);
        this.remove(this.fangyu);
        this.remove(this.baohu);
        this.remove(this.zhaohuan);
        this.remove(this.zhaohui);
        this.remove(this.buzhua);
        this.remove(this.taopao);
        this.remove(ZhuJpanel.qiangtui);
        if (this.taskGuideView.isV()) {
            this.taskGuideView.setVisible(false);
        }
        this.limitShopBtn.setVisible(false);
        this.xiang.setVisible(true);
        ZhuJpanel.knapsackBtn.setVisible(false);
        ZhuJpanel.groupBtn.setVisible(false);
        ZhuJpanel.pkBtn.setVisible(false);
        ZhuJpanel.transactionBtn1.setVisible(false);
        ZhuJpanel.taskBtn.setVisible(false);
        this.lowerRightImgBack.setVisible(true);
        this.chongjiBtn.setVisible(false);
        this.caidan.setVisible(false);
        ZhuJpanel.zhsbd.setVisible(false);
        ZhuJpanel.ttBtn.setVisible(false);
        ZhuJpanel.bjczBtn.setVisible(false);
        ZhuJpanel.bsynBtn.setVisible(false);
//        ZhuJpanel.gzBtn.setVisible(false);
        ZhuJpanel.GGBtn.setVisible(false);
        this.showIconBtnY.setVisible(false);
        int x = 210;
        if (ZhuJpanel.battlePetZhiSwitch) {
            ZhuJpanel.currentBattlePetImg.setVisible(true);
            this.currentBattlePetSwitch.setVisible(true);
            this.currentBattlePetLab.setVisible(true);
            this.uptatePetZhiyuan(false);
            x += 35;
        }
        if (ZhuJpanel.battleLingZhiSwitch) {
            ZhuJpanel.currentBattlelingImg.setVisible(true);
            this.currentBattlelingSwitch.setVisible(true);
            this.currentBattlelingLab.setVisible(true);
            this.uptateLingZhiyuan(false);
            x += 35;
        }
        this.mallX = x;
        this.ShopingMenu.setBounds(this.mallX, 5, 35, 35);
        setLabLingImg((RoleLingFa.getRoleLingFa().equipBao[0] != null) ? RoleLingFa.getRoleLingFa().equipBao[0].getSkin() : null);
    }

    public void showIsTeamBtn(boolean type, int num) {
        if (num == 0) {
            this.btnOperation.setVisible(type);
            this.btnPlatform.setVisible(type);
        }
        else if (this.btnOperation.isVisible()) {
            this.btnOperation.setVisible(false);
            this.btnPlatform.setVisible(false);
        }
        else {
            this.btnOperation.setVisible(true);
            this.btnPlatform.setVisible(true);
        }
    }

    public void showIsSystemBtn(boolean type, int num) {
        if (num == 0) {
            this.btnSystemSettings.setVisible(type);
            this.btnSpotCard.setVisible(type);
            ZhuJpanel.ladderPanelBtn.setVisible(type);
            this.cpBtn.setVisible(type);
            this.drjjBtn.setVisible(type);
            this.zdlhBtn.setVisible(type);
            this.jpBtn.setVisible(type);
            if (this.cjkg.equals("开")) {
                this.cj.setVisible(type);
            }
            this.qiandao.setVisible(type);
            this.yueka.setVisible(type);
            this.gl.setVisible(type);
            this.xszy.setVisible(type);
            this.kjcz.setVisible(type);
        }
        else if (this.btnSystemSettings.isVisible()) {
            this.btnSystemSettings.setVisible(false);
            this.btnSpotCard.setVisible(false);
            ZhuJpanel.ladderPanelBtn.setVisible(false);
            this.cpBtn.setVisible(false);
            this.drjjBtn.setVisible(false);
            this.zdlhBtn.setVisible(false);
            this.jpBtn.setVisible(false);
            if (this.cjkg.equals("开")) {
                this.cj.setVisible(false);
            }
            this.qiandao.setVisible(false);
            this.yueka.setVisible(false);
            this.gl.setVisible(false);
            this.xszy.setVisible(false);
            this.kjcz.setVisible(false);
        }
        else {
            this.btnSystemSettings.setVisible(true);
            this.btnSpotCard.setVisible(true);
            ZhuJpanel.ladderPanelBtn.setVisible(true);
            this.cpBtn.setVisible(false);
            this.drjjBtn.setVisible(false);
            this.zdlhBtn.setVisible(true);
            this.jpBtn.setVisible(true);
            if (this.cjkg.equals("开")) {
                this.cj.setVisible(true);
            }
            this.qiandao.setVisible(true);
            this.yueka.setVisible(true);
            this.gl.setVisible(true);
            this.xszy.setVisible(true);
            this.kjcz.setVisible(true);
        }
    }

    public void tradingMenu(boolean type, int num) {
        if (num == 0) {
            ZhuJpanel.getBtn.setVisible(type);
            ZhuJpanel.transactionBtn.setVisible(type);
        }
        else if (ZhuJpanel.transactionBtn.isVisible()) {
            ZhuJpanel.getBtn.setVisible(false);
            ZhuJpanel.transactionBtn.setVisible(false);
        }
        else {
            ZhuJpanel.getBtn.setVisible(true);
            ZhuJpanel.transactionBtn.setVisible(true);
        }
    }

    public static JLabel getMapName() {
        return ZhuJpanel.MapName;
    }

    public static void setMapName(JLabel mapName) {
        ZhuJpanel.MapName = mapName;
    }

    public static JLabel getTimemiao() {
        return ZhuJpanel.Timemiao;
    }

    public static void setTimemiao(JLabel timemiao) {
        ZhuJpanel.Timemiao = timemiao;
    }

    public JLabel getDangqian() {
        return this.dangqian;
    }

    public void setDangqian(JLabel dangqian) {
        this.dangqian = dangqian;
    }

    public JLabel getLabnow() {
        return this.labnow;
    }

    public void setLabnow(JLabel labnow) {
        this.labnow = labnow;
    }

    public JLabel getLabgroups() {
        return this.labgroups;
    }

    public void setLabgroups(JLabel labgroups) {
        this.labgroups = labgroups;
    }

    public JLabel getLabbangs() {
        return this.labbangs;
    }

    public void setLabbangs(JLabel labbangs) {
        this.labbangs = labbangs;
    }

    public JLabel getLabworld() {
        return this.labworld;
    }

    public void setLabworld(JLabel labworld) {
        this.labworld = labworld;
    }

    public JTextField getSendMes() {
        return this.SendMes;
    }

    public void setSendMes(JTextField sendMes) {
        this.SendMes = sendMes;
    }

    public static SmallIconBtn getLableft() {
        return ZhuJpanel.lableft;
    }

    public static void setLableft(SmallIconBtn lableft) {
        ZhuJpanel.lableft = lableft;
    }

    public static JLabel getLabsounds() {
        return ZhuJpanel.labsounds;
    }

    public static void setLabsounds(JLabel labsounds) {
        ZhuJpanel.labsounds = labsounds;
    }

    public static JLabel getLabsounds2() {
        return ZhuJpanel.labsounds2;
    }

    public static void setLabsounds2(JLabel labsounds2) {
        ZhuJpanel.labsounds2 = labsounds2;
    }

    public static JLabel getLabsounds3() {
        return ZhuJpanel.labsounds3;
    }

    public static void setLabsounds3(JLabel labsounds3) {
        ZhuJpanel.labsounds3 = labsounds3;
    }

    public static JLabel getLabsounds4() {
        return ZhuJpanel.labsounds4;
    }

    public static void setLabsounds4(JLabel labsounds4) {
        ZhuJpanel.labsounds4 = labsounds4;
    }

    public static JLabel getLabrolehead() {
        return ZhuJpanel.labrolehead;
    }

    public static void setLabrolehead(JLabel labrolehead) {
        ZhuJpanel.labrolehead = labrolehead;
    }

    public static JLabel getLabpetimg() {
        return ZhuJpanel.labpetimg;
    }

    public static TeamPanelBtn getladderPanelBtn() {
        return ZhuJpanel.ladderPanelBtn;
    }

    public static FightingBtn getqiangtui() {
        return ZhuJpanel.qiangtui;
    }

    public void setqiangtui(FightingBtn qiangtui) {
        ZhuJpanel.qiangtui = qiangtui;
    }

    public static void setLabpetimg(String path) {
        if (path == null) {
            ImageIcon icon1 = CutButtonImage.getImage("resource/jiuUI/chongwudaiji.png", -1, -1);
            ZhuJpanel.labpetimg.setIcon(icon1);
            ZhuJpanel.currentBattlePetImg.setIcon(null);
        }
        else {
            File file = new File(path);
            if (file.exists()) {
                ImageIcon icon2 = CutButtonImage.getImage(path, -1, -1);
                ZhuJpanel.labpetimg.setIcon(icon2);
                ImageIcon icon3 = CutButtonImage.getImage(path, 25, 26);
                ZhuJpanel.currentBattlePetImg.setIcon(icon3);
            }
            else {
                ImageIcon icon2 = CutButtonImage.getImage("resource/jiuUI/chongwudaiji.png", -1, -1);
                ZhuJpanel.labpetimg.setIcon(icon2);
                ZhuJpanel.currentBattlePetImg.setIcon(null);
            }
        }
    }

    public static void setLabLingImg(String skin) {
        String path = "img/lingbao/zhao/s_" + skin + ".png";
        File file = new File(path);
        if (file.exists()) {
            ImageIcon icon = CutButtonImage.getImage(path, 25, 26);
            ZhuJpanel.currentBattlelingImg.setIcon(icon);
        }
    }

    public static JLabel getLabroleimg() {
        return ZhuJpanel.labroleimg;
    }

    public static void setLabroleimg(JLabel labroleimg) {
        ZhuJpanel.labroleimg = labroleimg;
    }

    public RolePanelShow getChoseRoleStateMouslisten() {
        return this.choseRoleStateMouslisten;
    }

    public void setChoseRoleStateMouslisten(RolePanelShow choseRoleStateMouslisten) {
        this.choseRoleStateMouslisten = choseRoleStateMouslisten;
    }

    public PetPanelShow getChosePetMouslisten() {
        return this.chosePetMouslisten;
    }

    public void setChosePetMouslisten(PetPanelShow chosePetMouslisten) {
        this.chosePetMouslisten = chosePetMouslisten;
    }

    public ChoseDangQianMounslisten getChoseDangQianMounslisten() {
        return this.choseDangQianMounslisten;
    }

    public void setChoseDangQianMounslisten(ChoseDangQianMounslisten choseDangQianMounslisten) {
        this.choseDangQianMounslisten = choseDangQianMounslisten;
    }

    public ChoseNowMouslisten getChoseNowMouslisten() {
        return this.choseNowMouslisten;
    }

    public void setChoseNowMouslisten(ChoseNowMouslisten choseNowMouslisten) {
        this.choseNowMouslisten = choseNowMouslisten;
    }

    public ChoseGroupsMouslisten getChoseGroupsMouslisten() {
        return this.choseGroupsMouslisten;
    }

    public void setChoseGroupsMouslisten(ChoseGroupsMouslisten choseGroupsMouslisten) {
        this.choseGroupsMouslisten = choseGroupsMouslisten;
    }

    public ChoseBangsMouslisten getChoseBangsMouslisten() {
        return this.choseBangsMouslisten;
    }

    public void setChoseBangsMouslisten(ChoseBangsMouslisten choseBangsMouslisten) {
        this.choseBangsMouslisten = choseBangsMouslisten;
    }

    public ChoseWorldMouslisten getChoseWorldMouslisten() {
        return this.choseWorldMouslisten;
    }

    public void setChoseWorldMouslisten(ChoseWorldMouslisten choseWorldMouslisten) {
        this.choseWorldMouslisten = choseWorldMouslisten;
    }

    public int getTeamState() {
        return this.TeamState;
    }

    public void setTeamState(int teamState) {
        this.TeamState = teamState;
    }

    public static FightingBtn getZidong() {
        return ZhuJpanel.zidong;
    }

    public void setZidong(FightingBtn zidong) {
        ZhuJpanel.zidong = zidong;
    }
    public static List<Car> getListCar() {
        return listCar;
    }

    public static void setListCar(List<Car> listCar) {
        ZhuJpanel.listCar = listCar;
    }
    public static List<Mount> getListMount() {
        return ZhuJpanel.listMount;
    }

    public static void setListMount(List<Mount> listMount) {
        ZhuJpanel.listMount = listMount;
    }

    public static Mount getPetMount(BigDecimal petId) {
        if (ZhuJpanel.listMount != null) {
            for (int i = 0; i < ZhuJpanel.listMount.size(); ++i) {
                Mount mount = (Mount)ZhuJpanel.listMount.get(i);
                if (mount.getSid() != null && mount.getSid().compareTo(petId) == 0) {
                    return mount;
                }
                if (mount.getOthrersid() != null && mount.getOthrersid().compareTo(petId) == 0) {
                    return mount;
                }
                if (mount.getSid4() != null && mount.getSid4().compareTo(petId) == 0) {
                    return mount;
                }
                if (mount.getSid5() != null && mount.getSid5().compareTo(petId) == 0) {
                    return mount;
                }
                if (mount.getSid3() != null && mount.getSid3().compareTo(petId) == 0) {
                    return mount;
                }
            }
        }
        return null;
    }

    public static Goodstable getGoodstableAl() {
        return ZhuJpanel.goodstableAl;
    }

    public static void setGoodstableAl(Goodstable goodstableAl) {
        ZhuJpanel.goodstableAl = goodstableAl;
    }

    public JLabel getRolehp() {
        return this.rolehp;
    }

    public void setRolehp(JLabel rolehp) {
        this.rolehp = rolehp;
    }

    public JLabel getRolemp() {
        return this.rolemp;
    }

    public void setRolemp(JLabel rolemp) {
        this.rolemp = rolemp;
    }

    public JLabel getRoleexe() {
        return this.roleexe;
    }

    public void setRoleexe(JLabel roleexe) {
        this.roleexe = roleexe;
    }

    public JLabel getBbhp() {
        return this.bbhp;
    }

    public void setBbhp(JLabel bbhp) {
        this.bbhp = bbhp;
    }

    public JLabel getBbmp() {
        return this.bbmp;
    }

    public void setBbmp(JLabel bbmp) {
        this.bbmp = bbmp;
    }

    public JLabel getBbexe() {
        return this.bbexe;
    }

    public void setBbexe(JLabel bbexe) {
        this.bbexe = bbexe;
    }

    public static int getUseGoodsType() {
        return ZhuJpanel.UseGoodsType;
    }

    public static void setUseGoodsType(int useGoodsType) {
        ZhuJpanel.UseGoodsType = useGoodsType;
    }

    public static Goodstable getNedangoods() {
        return ZhuJpanel.Nedangoods;
    }

    public static void setNedangoods(Goodstable nedangoods) {
        ZhuJpanel.Nedangoods = nedangoods;
    }

    public JLabel getjLabelSendMes() {
        return this.jLabelSendMes;
    }

    public void setjLabelSendMes(JLabel jLabelSendMes) {
        this.jLabelSendMes = jLabelSendMes;
    }

    public static FormsOnOffBtn getKnapsackBtn() {
        return ZhuJpanel.knapsackBtn;
    }

    public static void setKnapsackBtn(FormsOnOffBtn knapsackBtn) {
        ZhuJpanel.knapsackBtn = knapsackBtn;
    }

    public static MouseStyleBtn getGroupBtn() {
        return ZhuJpanel.groupBtn;
    }

    public static void setGroupBtn(MouseStyleBtn groupBtn) {
        ZhuJpanel.groupBtn = groupBtn;
    }

    public static WorkshopBtn getGetBtn() {
        return ZhuJpanel.getBtn;
    }

    public static void setGetBtn(WorkshopBtn getBtn) {
        ZhuJpanel.getBtn = getBtn;
    }

    public static FormsOnOffBtn getFriendsBtn() {
        return ZhuJpanel.friendsBtn;
    }

    public static void setFriendsBtn(FormsOnOffBtn friendsBtn) {
        ZhuJpanel.friendsBtn = friendsBtn;
    }

    public static FormsOnOffBtn getfuzhu() {
        return ZhuJpanel.fuzhu;
    }

    public static void setfuzhu(FormsOnOffBtn fuzhu) {
        ZhuJpanel.fuzhu = fuzhu;
    }

    public static FormsOnOffBtn getQianDaoMenu() {
        return ZhuJpanel.QianDaoMenu;
    }

    public static void setQianDaoMenu(FormsOnOffBtn QianDaoMenu) {
        ZhuJpanel.QianDaoMenu = QianDaoMenu;
    }

    public static FormsOnOffBtn getTJBtn() {
        return ZhuJpanel.TJBtn;
    }

    public static void setTJBtn(FormsOnOffBtn TJBtn) {
        ZhuJpanel.TJBtn = TJBtn;
    }

    public static FormsOnOffBtn getGangBtn() {
        return ZhuJpanel.gangBtn;
    }

    public static void setGangBtn(FormsOnOffBtn gangBtn) {
        ZhuJpanel.gangBtn = gangBtn;
    }

    public static SmallIconBtn getLabbottom() {
        return ZhuJpanel.labbottom;
    }

    public static void setLabbottom(SmallIconBtn labbottom) {
        ZhuJpanel.labbottom = labbottom;
    }

    public static SmallIconBtn getLabtop() {
        return ZhuJpanel.labtop;
    }

    public static void setLabtop(SmallIconBtn labtop) {
        ZhuJpanel.labtop = labtop;
    }

    public static SmallIconBtn getLabstar() {
        return ZhuJpanel.labstar;
    }

    public static void setLabstar(SmallIconBtn labstar) {
        ZhuJpanel.labstar = labstar;
    }

    public static SmallIconBtn getLabadd() {
        return ZhuJpanel.labadd;
    }

    public static void setLabadd(SmallIconBtn labadd) {
        ZhuJpanel.labadd = labadd;
    }

    public static SmallIconBtn getLabminus() {
        return ZhuJpanel.labminus;
    }

    public static void setLabminus(SmallIconBtn labminus) {
        ZhuJpanel.labminus = labminus;
    }

    public static JLabel getxAndY() {
        return ZhuJpanel.xAndY;
    }

    public static void setxAndY(JLabel xAndY) {
        ZhuJpanel.xAndY = xAndY;
    }

    public JLabel getLabnotice() {
        return this.labnotice;
    }

    public void setLabnotice(JLabel labnotice) {
        this.labnotice = labnotice;
    }

    public VipShopBtn getRechargeBtn() {
        return this.rechargeBtn;
    }

    public void setRechargeBtn(VipShopBtn rechargeBtn) {
        this.rechargeBtn = rechargeBtn;
    }

    public VipShopBtn getOddsBtn() {
        return this.oddsBtn;
    }

    public void setOddsBtn(VipShopBtn oddsBtn) {
        this.oddsBtn = oddsBtn;
    }

    public VipShopBtn getLimitShopBtn() {
        return this.limitShopBtn;
    }

    public void setLimitShopBtn(VipShopBtn limitShopBtn) {
        this.limitShopBtn = limitShopBtn;
    }

    public VipShopBtn getShowVipBtn() {
        return this.showVipBtn;
    }

    public void setShowVipBtn(VipShopBtn showVipBtn) {
        this.showVipBtn = showVipBtn;
    }

    public VipShopBtn getShowIconBtn() {
        return this.showIconBtn;
    }

    public void setShowIconBtn(VipShopBtn showIconBtn) {
        this.showIconBtn = showIconBtn;
    }

    public JLabel getToubbmp() {
        return this.toubbmp;
    }

    public void setToubbmp(JLabel toubbmp) {
        this.toubbmp = toubbmp;
    }

    public static FormsOnOffBtn getGGBtn() {
        return ZhuJpanel.GGBtn;
    }

    public static void setGGBtn(FormsOnOffBtn GGBtn) {
        ZhuJpanel.GGBtn = GGBtn;
    }

    public VipShopBtn getShowIconBtnY() {
        return this.showIconBtnY;
    }

    public void setShowIconBtnY(VipShopBtn showIconBtnY) {
        this.showIconBtnY = showIconBtnY;
    }

    public VipShopBtn getcaidan() {
        return this.caidan;
    }

    public void setcaidan(VipShopBtn caidan) {
        this.caidan = caidan;
    }

    public VipShopBtn getShowcaidan1() {
        return this.showcaidan1;
    }

    public void setShowcaidan1(VipShopBtn showcaidan1) {
        this.showcaidan1 = showcaidan1;
    }

    public TaskGuideView getTaskGuideView() {
        return this.taskGuideView;
    }

    public TeamPanelBtn getBtnOperation() {
        return this.btnOperation;
    }

    public void setBtnOperation(TeamPanelBtn btnOperation) {
        this.btnOperation = btnOperation;
    }

    public TeamPanelBtn getBtnPlatform() {
        return this.btnPlatform;
    }

    public void setBtnPlatform(TeamPanelBtn btnPlatform) {
        this.btnPlatform = btnPlatform;
    }

    public List<VipShopBtn> getBtnListVicon() {
        return this.btnListVicon;
    }

    public void setBtnListVicon(List<VipShopBtn> btnListVicon) {
        this.btnListVicon = btnListVicon;
    }

    public VipShopBtn getchongjiBtn() {
        return this.chongjiBtn;
    }

    public void setchongjiBtn(VipShopBtn chongjiBtn) {
        this.chongjiBtn = chongjiBtn;
    }

    public VipShopBtn getcontinuousBtn() {
        return this.continuousBtn;
    }

    public void setcontinuousBtn(VipShopBtn continuousBtn) {
        this.continuousBtn = continuousBtn;
    }

    public JLabel getShopingMenu() {
        return this.ShopingMenu;
    }

    public void setShopingMenu(JLabel shopingMenu) {
        this.ShopingMenu = shopingMenu;
    }

    public JLabel getDrawMenu() {
        return this.DrawMenu;
    }

    public void setDrawMenu(JLabel drawMenu) {
        this.DrawMenu = drawMenu;
    }

    public VipShopBtn getrecharge() {
        return this.recharge;
    }

    public void setrecharge(VipShopBtn recharge) {
        this.recharge = recharge;
    }

    public Date getUptime() {
        return this.uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public AircraftBtn[] getAircraftBtns() {
        return this.aircraftBtns;
    }

    public void setAircraftBtns(AircraftBtn[] aircraftBtns) {
        this.aircraftBtns = aircraftBtns;
    }

    public static FormsOnOffBtn getPdBtn() {
        return ZhuJpanel.pdBtn;
    }

    public static void setPdBtn(FormsOnOffBtn pdBtn) {
        ZhuJpanel.pdBtn = pdBtn;
    }

    public static FormsOnOffBtn getBjczBtn() {
        return ZhuJpanel.bjczBtn;
    }

    public static FormsOnOffBtn getGuideBtn() {
        return ZhuJpanel.guideBtn;
    }

    public static void setGuideBtn(FormsOnOffBtn guideBtn) {
        ZhuJpanel.guideBtn = guideBtn;
    }

    public static FormsOnOffBtn getCzlbBtn() {
        return ZhuJpanel.czlbBtn;
    }

    public static void setCzlbBtn(FormsOnOffBtn czlbBtn) {
        ZhuJpanel.czlbBtn = czlbBtn;
    }

    public static void setBjczBtn(FormsOnOffBtn bjczBtn) {
        ZhuJpanel.bjczBtn = bjczBtn;
    }

    public int getMounState() {
        return this.mounState;
    }

    public void setMounState(int mounState) {
        this.mounState = mounState;
    }

    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }

    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }

    public static List<Fly> getListFly() {
        return ZhuJpanel.ListFly;
    }

    public static void setListFly(List<Fly> ListFly) {
        ZhuJpanel.ListFly = ListFly;
    }

    public void showflybtn(boolean type, int num) {
        if (num == 0) {
            this.btnzuoqi.setVisible(type);
            this.btnfeixingqi.setVisible(type);
            this.btnzuojia.setVisible(type);
        }
        else if (this.btnzuoqi.isVisible()) {
            this.btnzuoqi.setVisible(false);
            this.btnfeixingqi.setVisible(false);
            this.btnzuojia.setVisible(false);
        }
        else {
            this.btnzuoqi.setVisible(true);
            this.btnfeixingqi.setVisible(true);
            this.btnzuojia.setVisible(true);
        }
    }

    public void ssx() {
        String zb = ZhuJpanel.xAndY.getText();
        this.remove(this.Dbiaoqing);
        this.remove(ZhuJpanel.lableft);
        this.remove(ZhuJpanel.labbottom);
        this.remove(ZhuJpanel.labtop);
        this.remove(ZhuJpanel.labstar);
        this.remove(ZhuJpanel.labadd);
        this.remove(ZhuJpanel.labminus);
        this.remove(ZhuJpanel.zdrwBtn);
        this.remove(ZhuJpanel.childBtn);
        this.remove(ZhuJpanel.knapsackBtn);
        this.remove(ZhuJpanel.groupBtn);
        this.remove(ZhuJpanel.pkBtn);
        this.remove(ZhuJpanel.rwjnBtn);
        this.remove(ZhuJpanel.getBtn);
        this.remove(ZhuJpanel.transactionBtn1);
        this.remove(ZhuJpanel.taskBtn);
        this.remove(ZhuJpanel.friendsBtn);
        this.remove(ZhuJpanel.gangBtn);
        this.remove(ZhuJpanel.systemBtn);
        if (ZhuJpanel.petBtn != null) {
            this.remove(ZhuJpanel.petBtn);
        }
        this.remove(this.lowerRightImgBack);
        this.remove(ZhuJpanel.labpetimg);
        this.remove(ZhuJpanel.labroleimg);
        this.remove(this.bbexe);
        this.remove(this.dangqian);
        this.remove(this.labnow);
        this.remove(this.labgroups);
        this.remove(this.labbangs);
        this.remove(this.labworld);
        this.remove(this.labnotice);
        this.remove(this.jLabelLeftMes);
        this.remove(this.jLabelLeftMes1);
        this.remove(ZhuJpanel.labrolehead);
        this.remove(ZhuJpanel.petlehead);
        this.remove(ZhuJpanel.MapName);
        this.remove(ZhuJpanel.Timemiao);
        this.remove(this.bbhp);
        this.remove(this.bbhp);
        this.remove(this.bbmp);
        this.remove(this.roleexe);
        this.remove(this.rolehp);
        this.remove(this.rolemp);
        this.remove(ZhuJpanel.xAndY);
        this.remove(ZhuJpanel.RanklistBtn);
        this.remove(ZhuJpanel.TJBtn);
        this.remove(ZhuJpanel.zhuBtn);
        if (ZhuJpanel.mapiconBtn != null) {
            this.remove(ZhuJpanel.mapiconBtn);
        }
        if (ZhuJpanel.dtck != null) {
            this.remove(ZhuJpanel.dtck);
        }
        this.remove(ZhuJpanel.worldMap);
        this.remove(this.btnOperation);
        this.remove(this.btnPlatform);
        this.remove(this.btnzuoqi);
        this.remove(this.btnfeixingqi);
        this.remove(this.btnzuojia);
        if (this.chongjiBtn != null) {
            this.remove(this.chongjiBtn);
        }
        if (this.rechargeBtn != null) {
            this.remove(this.rechargeBtn);
        }
        if (this.oddsBtn != null) {
            this.remove(this.oddsBtn);
        }
        if (this.showVipBtn != null) {
            this.remove(this.showVipBtn);
        }
        if (this.showIconBtnY != null) {
            this.remove(this.showIconBtnY);
        }
        if (this.showIconBtn != null) {
            this.remove(this.showIconBtn);
        }
        if (ZhuJpanel.bjczBtn != null) {
            this.remove(ZhuJpanel.bjczBtn);
        }
        if (ZhuJpanel.guideBtn != null) {
            this.remove(ZhuJpanel.guideBtn);
        }
        if (ZhuJpanel.czlbBtn != null) {
            this.remove(ZhuJpanel.czlbBtn);
        }
        if (ZhuJpanel.pdBtn != null) {
            this.remove(ZhuJpanel.pdBtn);
        }
        this.remove(ZhuJpanel.pdBtn);
        this.remove(ZhuJpanel.labbackground);
        this.remove(this.ShopingMenu);
        if (this.DrawMenu != null) {}
        this.remove(this.limitShopBtn);
        this.remove(this.taskGuideView);
        this.remove(this.kjsfBtn);
        this.remove(this.caidan);
        this.remove(this.btnSpotCard);
        this.remove(ZhuJpanel.ladderPanelBtn);
        this.remove(this.cpBtn);
        this.remove(this.drjjBtn);
        this.remove(this.zdlhBtn);
        this.remove(this.btnSystemSettings);
        this.remove(this.hotKeyBack);
        this.remove(ZhuJpanel.hotKey);
        this.remove(ZhuJpanel.GGBtn);
        for (int j = 0; j < 8; ++j) {
            this.hotKeyGoodsListLabelFID[j].setVisible(false);
            this.hotKeyGoodsListLabel[j].setVisible(false);
            this.remove(this.hotKeyGoodsListLabelFID[j]);
            this.remove(this.hotKeyGoodsListLabel[j]);
        }
        HotKeyMouseListen.isHide = Boolean.valueOf(false);
        this.remove(ZhuJpanel.CBGBtn);
        this.remove(ZhuJpanel.hdrlBtn);
        if (MyIsif.getStyle().equals("水墨")) {
            (Article.xuelans = new ImageIcon[4])[0] = new ImageIcon("inkImg/background/S2999.png");
            Article.xuelans[1] = new ImageIcon("inkImg/background1/S29.png");
            Article.xuelans[2] = new ImageIcon("inkImg/background1/S28.png");
            Article.xuelans[3] = new ImageIcon("inkImg/background1/S27.png");
        }
        else {
            (Article.xuelans = new ImageIcon[4])[0] = new ImageIcon("img/xy2uiimg/27_png.xy2uiimg.empty.png");
            Article.xuelans[1] = new ImageIcon("inkImg/hongmu/S29.png");
            Article.xuelans[2] = new ImageIcon("inkImg/hongmu/S28.png");
            Article.xuelans[3] = new ImageIcon("inkImg/hongmu/S27.png");
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getCjgnkg().equals("开")) {
            this.cjkg = "开";
        }
        else {
            this.cjkg = "关";
        }
        this.jjckg = configure.getJjcgnkg();
        this.mrczkg = configure.getMrczkg();
        this.mrqdkg = configure.getMrqdgnkg();
        if (MyIsif.getStyle().equals("水墨")) {
            this.fightingbtn();
            this.fightingbtn();
            this.setPreferredSize(new Dimension(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
            this.setLayout(null);
            ImageIcon tips = new ImageIcon("inkImg/background/S33.png");
            (this.labnotice = new JLabel()).setIcon(tips);
            this.labnotice.setBounds(10, ScrenceUntil.Screen_y - 161, 51, 25);
            this.labnotice.setForeground(Color.WHITE);
            this.labnotice.setText("传音");
            this.labnotice.setHorizontalTextPosition(0);
            this.labnotice.setFont(UIUtils.TEXT_FONT);
            this.choseNoticeMouslisten = new ChoseNoticeMouslisten(this);
            this.labnotice.addMouseListener(this.choseNoticeMouslisten);
            this.labnotice.setVisible(false);
            (this.labworld = new JLabel()).setIcon(tips);
            this.labworld.setBounds(10, ScrenceUntil.Screen_y - 134, 51, 25);
            this.labworld.setForeground(Color.WHITE);
            this.labworld.setText("世界");
            this.labworld.setHorizontalTextPosition(0);
            this.labworld.setFont(UIUtils.TEXT_FONT);
            this.choseWorldMouslisten = new ChoseWorldMouslisten(this);
            this.labworld.addMouseListener(this.choseWorldMouslisten);
            this.labworld.setVisible(false);
            (this.labbangs = new JLabel()).setIcon(tips);
            this.labbangs.setBounds(10, ScrenceUntil.Screen_y - 107, 51, 25);
            this.labbangs.setForeground(Color.WHITE);
            this.labbangs.setText("帮派");
            this.labbangs.setHorizontalTextPosition(0);
            this.labbangs.setFont(UIUtils.TEXT_FONT);
            this.choseBangsMouslisten = new ChoseBangsMouslisten(this);
            this.labbangs.addMouseListener(this.choseBangsMouslisten);
            this.labbangs.setVisible(false);
            (this.labgroups = new JLabel()).setIcon(tips);
            this.labgroups.setBounds(10, ScrenceUntil.Screen_y - 80, 51, 25);
            this.labgroups.setForeground(Color.WHITE);
            this.labgroups.setText("队伍");
            this.labgroups.setHorizontalTextPosition(0);
            this.labgroups.setFont(UIUtils.TEXT_FONT);
            this.choseGroupsMouslisten = new ChoseGroupsMouslisten(this);
            this.labgroups.addMouseListener(this.choseGroupsMouslisten);
            this.labgroups.setVisible(false);
            (this.labnow = new JLabel()).setIcon(tips);
            this.labnow.setBounds(10, ScrenceUntil.Screen_y - 53, 51, 25);
            this.labnow.setForeground(Color.WHITE);
            this.labnow.setText("当前");
            this.labnow.setHorizontalTextPosition(0);
            this.labnow.setFont(UIUtils.TEXT_FONT);
            this.choseNowMouslisten = new ChoseNowMouslisten(this);
            this.labnow.addMouseListener(this.choseNowMouslisten);
            this.labnow.setVisible(false);
            (this.dangqian = new JLabel()).setBounds(10, ScrenceUntil.Screen_y - 26, 51, 25);
            this.dangqian.setForeground(Color.WHITE);
            this.dangqian.setText("当前");
            this.dangqian.setVerticalTextPosition(0);
            this.dangqian.setHorizontalTextPosition(0);
            this.dangqian.setFont(UIUtils.TEXT_HYK16);
            this.choseDangQianMounslisten = new ChoseDangQianMounslisten(this);
            this.dangqian.addMouseListener(this.choseDangQianMounslisten);
            this.setVisible(true);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            (this.jLabelLeftMes = new JLabel()).setIcon(new ImageIcon("inkImg/background1/ltsrk.png"));
            this.jLabelLeftMes.setBounds(0, ScrenceUntil.Screen_y - 43, 160, 43);
            (this.jLabelLeftMes1 = new JLabel()).setIcon(new ImageIcon("inkImg/background1/ltsrkkz.png"));
            this.jLabelLeftMes1.setBounds(170, ScrenceUntil.Screen_y - 43, 70, 43);
            this.add(this.dangqian);
            this.add(this.labnow);
            this.add(this.labgroups);
            this.add(this.labbangs);
            this.add(this.labworld);
            this.add(this.labnotice);
            this.add(this.jLabelLeftMes);
            (this.Dbiaoqing = new FormsOnOffBtn("inkImg/background1/biaoqing.png", 1, 985)).setBounds(205, ScrenceUntil.Screen_y - 35, 35, 35);
            this.add(this.Dbiaoqing);
            this.add(this.jLabelLeftMes1);
            (this.jLabelSendMes = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/background/S31.png", ScrenceUntil.SendMsg_x + 10, -1));
            this.jLabelSendMes.setBounds(70, ScrenceUntil.Screen_y - 42, ScrenceUntil.SendMsg_x + 10, 42);
            (ZhuJpanel.lableft = new SmallIconBtn("inkImg/button/B44.png", 1, 4, "向左", this)).setBounds(19, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.lableft);
            (ZhuJpanel.labbottom = new SmallIconBtn("inkImg/button/B43.png", 1, 1, "向下", this)).setBounds(38, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labbottom);
            (ZhuJpanel.labtop = new SmallIconBtn("inkImg/button/B42.png", 1, 0, "向上", this)).setBounds(57, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labtop);
            (ZhuJpanel.labstar = new SmallIconBtn("inkImg/button/B41.png", 1, 7, "星星", this)).setBounds(76, ScrenceUntil.Screen_y - 50, 18, 18);
            this.add(ZhuJpanel.labstar);
            (ZhuJpanel.labadd = new SmallIconBtn("inkImg/button/B35.png", 1, 5, "加号", this)).setBounds(95, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labadd);
            (ZhuJpanel.labminus = new SmallIconBtn("inkImg/button/B36.png", 1, 6, "减号", this)).setBounds(114, ScrenceUntil.Screen_y - 50, 17, 17);
            this.add(ZhuJpanel.labminus);
            (ZhuJpanel.zdrwBtn = new FormsOnOffBtn("inkImg/button1/qqcd.png", 1, 3072)).addMouseListener(new WLLMouslisten(214));
            this.add(ZhuJpanel.zdrwBtn);
            (ZhuJpanel.childBtn = new FormsOnOffBtn("inkImg/button1/xhd.png", 1, 1)).setBounds(ScrenceUntil.Screen_x - 319, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.childBtn.addMouseListener(new WLLMouslisten(43));
            this.add(ZhuJpanel.childBtn);
            (ZhuJpanel.knapsackBtn = new FormsOnOffBtn("inkImg/button1/djld.png", 1, 2)).setBounds(ScrenceUntil.Screen_x - 290, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.knapsackBtn.addMouseListener(new WLLMouslisten(44));
            this.add(ZhuJpanel.knapsackBtn);
            (ZhuJpanel.groupBtn = new MouseStyleBtn("inkImg/button1/dwd.png", 1, "组队")).setBounds(ScrenceUntil.Screen_x - 261, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.groupBtn.addMouseListener(new WLLMouslisten(45));
            this.add(ZhuJpanel.groupBtn);
            (ZhuJpanel.pkBtn = new MouseStyleBtn("inkImg/button1/pkd.png", 1, "切磋")).setBounds(ScrenceUntil.Screen_x - 232, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.pkBtn.addMouseListener(new WLLMouslisten(46));
            this.add(ZhuJpanel.pkBtn);
            (ZhuJpanel.transactionBtn1 = new MouseStyleBtn("inkImg/button1/jyd.png", 1, "交易菜单")).setBounds(ScrenceUntil.Screen_x - 174, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.transactionBtn1.addMouseListener(new WLLMouslisten(48));
            this.add(ZhuJpanel.transactionBtn1);
            (ZhuJpanel.rwjnBtn = new FormsOnOffBtn("inkImg/button1/jnd.png", 1, 8)).addMouseListener(new WLLMouslisten(47));
            this.add(ZhuJpanel.rwjnBtn);
            (ZhuJpanel.petBtn = new MouseStyleBtn("inkImg/button1/zqd.png", 1, "坐骑")).setBounds(ScrenceUntil.Screen_x - 145, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.petBtn.addMouseListener(new WLLMouslisten(49));
            this.add(ZhuJpanel.petBtn);
            (ZhuJpanel.taskBtn = new FormsOnOffBtn("inkImg/button1/rwd.png", 1, 3)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.taskBtn.addMouseListener(new WLLMouslisten(50));
            this.add(ZhuJpanel.taskBtn);
            (ZhuJpanel.friendsBtn = new FormsOnOffBtn("inkImg/button1/hyd.png", 1, 4)).setBounds(ScrenceUntil.Screen_x - 87, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.friendsBtn.addMouseListener(new WLLMouslisten(51));
            this.add(ZhuJpanel.friendsBtn);
            (ZhuJpanel.gangBtn = new FormsOnOffBtn("inkImg/button1/bpd.png", 1, 48)).setBounds(ScrenceUntil.Screen_x - 58, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.gangBtn.addMouseListener(new WLLMouslisten(52));
            this.add(ZhuJpanel.gangBtn);
            (ZhuJpanel.systemBtn = new MouseStyleBtn("inkImg/button1/szd.png", 1, "设置")).setBounds(ScrenceUntil.Screen_x - 26, ScrenceUntil.Screen_y - 26, 25, 25);
            ZhuJpanel.systemBtn.addMouseListener(new WLLMouslisten(53));
            this.add(ZhuJpanel.systemBtn);
            (this.lowerRightImgBack = new JLabel()).setIcon(new ImageIcon("inkImg/background1/keybarT.png"));
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            this.add(this.lowerRightImgBack);
            this.add(ZhuJpanel.currentBattlePetImg = new JLabel());
            (this.currentBattlePetSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlePetSwitch);
            (this.currentBattlePetLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlePetLab);
            this.add(ZhuJpanel.currentBattlelingImg = new JLabel());
            (this.currentBattlelingSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlelingSwitch);
            (this.currentBattlelingLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlelingLab);
            (ZhuJpanel.labpetimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
            ZhuJpanel.labpetimg.addMouseListener(new WLLMouslisten(21));
            this.chosePetMouslisten = new PetPanelShow();
            ZhuJpanel.labpetimg.addMouseListener(this.chosePetMouslisten);
            ZhuJpanel.labpetimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 308, 6, 48, 48);
                    super.mousePressed(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
                    super.mouseReleased(e);
                }
            });
            this.add(ZhuJpanel.labpetimg);
            (ZhuJpanel.labroleimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
            ZhuJpanel.labroleimg.addMouseListener(new WLLMouslisten(20));
            this.choseRoleStateMouslisten = new RolePanelShow();
            ZhuJpanel.labroleimg.addMouseListener(this.choseRoleStateMouslisten);
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            if (nao.equals("新")) {
                if (TeststateJpanel.getQhnum().equals("1")) {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
                }
                else {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58));
                }
            }
            else if (TeststateJpanel.getQhnum().equals("1")) {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            else {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            ZhuJpanel.labroleimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 170, 9, 58, 58);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
                }
            });
            this.add(ZhuJpanel.labroleimg);
            (this.bbexe = new JLabel()).addMouseListener(new WLLMouslisten(29));
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 101, 0, 138, 52);
            this.add(this.bbexe);
            (this.bbhp = new JLabel()).addMouseListener(new ZDCYMouslisten(2));
            this.add(this.bbhp);
            (this.bbmp = new JLabel()).addMouseListener(new ZDCYMouslisten(3));
            this.add(this.bbmp);
            (this.roleexe = new JLabel()).setBounds(ScrenceUntil.Screen_x - 116, 2, 80, 10);
            this.roleexe.addMouseListener(new WLLMouslisten(28));
            this.add(this.roleexe);
            (this.rolehp = new JLabel()).addMouseListener(new ZDCYMouslisten(0));
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 110, 16, 80, 10);
            this.add(this.rolehp);
            (this.rolemp = new JLabel()).addMouseListener(new ZDCYMouslisten(1));
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 100, 30, 80, 10);
            this.add(this.rolemp);
            Article.manxie(RoleData.getRoleData().getLoginResult());
            List<RoleSummoning> petList = UserMessUntil.getPetListTable();
            if (petList != null && petList.size() > 0) {
                for (RoleSummoning summoning : petList) {
                    if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && summoning.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                        setLabpetimg("img/head/p" + summoning.getSummoningskin() + ".png");
                        Article.souxie(summoning);
                        break;
                    }
                }
            }
            (ZhuJpanel.labrolehead = new JLabel()).setIcon(new ImageIcon("inkImg/background1/rwtxl.png"));
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 100, 5, 133, 52);
            this.add(ZhuJpanel.labrolehead);
            (ZhuJpanel.petlehead = new JLabel()).setIcon(new ImageIcon("inkImg/background1/cwtxl.png"));
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 109, 41);
            this.add(ZhuJpanel.petlehead);
            (ZhuJpanel.MapName = new JLabel()).setBounds(15, 6, 80, 20);
            ZhuJpanel.MapName.setForeground(Color.yellow);
            (ZhuJpanel.Timemiao = new JLabel()).addMouseListener(new WLLMouslisten(0));
            ZhuJpanel.Timemiao.setBounds(106, 43, 50, 20);
            ZhuJpanel.Timemiao.setForeground(UIUtils.COLOR_WHITE1[0]);
            ZhuJpanel.Timemiao.setFont(UIUtils.TEXT_FONTZS);
            this.add(ZhuJpanel.Timemiao);
            (ZhuJpanel.xAndY = new JLabel()).setBounds(45, 11, 180, 20);
            ZhuJpanel.xAndY.setForeground(Color.white);
            this.add(ZhuJpanel.xAndY);
            (ZhuJpanel.TJBtn = new FormsOnOffBtn("inkImg/button1/XTB1.png", 1, 114)).setBounds(2, 45, 20, 20);
            ZhuJpanel.TJBtn.addMouseListener(new WLLMouslisten(304));
            this.add(ZhuJpanel.TJBtn);
            (ZhuJpanel.RanklistBtn = new FormsOnOffBtn("inkImg/button1/XTB2.png", 1, UIUtils.COLOR_BTNTEXT, "", 60)).setBounds(24, 45, 20, 20);
            ZhuJpanel.RanklistBtn.addMouseListener(new WLLMouslisten(305));
            this.add(ZhuJpanel.RanklistBtn);
            (ZhuJpanel.CBGBtn = new FormsOnOffBtn("inkImg/button1/XTB3.png", 1, UIUtils.COLOR_BTNTEXT, "", 78)).setBounds(46, 45, 20, 20);
            ZhuJpanel.CBGBtn.addMouseListener(new WLLMouslisten(306));
            this.add(ZhuJpanel.CBGBtn);
            (ZhuJpanel.hdrlBtn = new FormsOnOffBtn("inkImg/button1/XTB4.png", 1, UIUtils.COLOR_BTNTEXT, "", 40)).setBounds(68, 45, 20, 20);
            ZhuJpanel.hdrlBtn.addMouseListener(new WLLMouslisten(307));
            this.add(ZhuJpanel.hdrlBtn);
            (ZhuJpanel.GGBtn = new FormsOnOffBtn("inkImg/button1/gg1.png", 1, 1001)).setBounds(2, 45, 45, 44);
            this.add(ZhuJpanel.GGBtn);
            (ZhuJpanel.zhuBtn = new FormsOnOffBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BTNTEXT, "猜", 105)).setBounds(41, 34, 18, 18);
            (ZhuJpanel.mapiconBtn = new FormsOnOffBtn("inkImg/button1/B61.png", 1, 22)).setBounds(182, 23, 20, 20);
            ZhuJpanel.mapiconBtn.addMouseListener(new WLLMouslisten(302));
            this.add(ZhuJpanel.mapiconBtn);
            (ZhuJpanel.worldMap = new FormsOnOffBtn("inkImg/button1/B62.png", 1, 1102)).setBounds(182, 3, 20, 20);
            ZhuJpanel.worldMap.addMouseListener(new WLLMouslisten(303));
            this.add(ZhuJpanel.worldMap);
            this.aircraftBtns = new AircraftBtn[2];
            for (int i = 0; i < this.aircraftBtns.length; ++i) {
                (this.aircraftBtns[i] = new AircraftBtn("img/xy2uiimg/btnCommon102.png", 1, (i == 0) ? "坐骑" : "飞行器", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, i + 10, this)).setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (67 - i * 17), 102, 17);
                this.aircraftBtns[i].setVisible(false);
                this.add(this.aircraftBtns[i]);
            }
            (this.btnzuoqi = new AircraftBtn("inkImg/button1/syaj.png", 1, "坐  骑", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 10, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnzuoqi.setVisible(false);
            this.add(this.btnzuoqi);
            (this.btnfeixingqi = new AircraftBtn("inkImg/button1/syaj.png", 1, "飞  行", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 11, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnfeixingqi.setVisible(false);
            this.add(this.btnfeixingqi);

            (this.btnzuojia = new AircraftBtn("inkImg/button1/syaj.png", 1, "坐  驾", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE1, 12, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - (67+17), 102, 17);
            this.btnzuojia.setVisible(false);
            this.add(this.btnzuojia);

            (ZhuJpanel.transactionBtn = new WorkshopBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, "交  易", 203)).setVisible(false);
            this.add(ZhuJpanel.transactionBtn);
            (ZhuJpanel.getBtn = new WorkshopBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, "给  予", 204)).setVisible(false);
            this.add(ZhuJpanel.getBtn);
            if (this.cjkg.equals("开")) {
                (this.cj = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "幸运抽奖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
                this.cj.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            }
            (this.yueka = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "月卡特权", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.yueka.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.yueka);
            (this.qiandao = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "每日签到", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.qiandao);
            (this.gl = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "游戏攻略", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.gl.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.gl);
            (this.xszy = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "新手指引", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.xszy);
            (this.kjcz = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "快捷操作(Tab)", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.kjcz);
            (this.jpBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "聚宝阁拍卖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.jpBtn);
            (this.zdlhBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "自动炼化(F12)", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.zdlhBtn);
            (this.drjjBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "单人竞技", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.drjjBtn);
            (this.cpBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "全民竞猜", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.cpBtn);
            (ZhuJpanel.ladderPanelBtn = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "天梯竞技", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            ZhuJpanel.ladderPanelBtn.setVisible(false);
            this.add(ZhuJpanel.ladderPanelBtn);
            (this.btnSpotCard = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "仙玉寄售", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 38 - 72, 116, 24);
            this.btnSpotCard.setVisible(false);
            this.add(this.btnSpotCard);
            (this.btnSystemSettings = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "系统设置", UIUtils.COLOR_WHITE1, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 38 - 48, 116, 24);
            this.btnSystemSettings.setVisible(false);
            this.add(this.btnSystemSettings);
            if (configure.getMrczkg().equals("开")) {
                (this.chongjiBtn = new VipShopBtn("Inkimg/background1/SS70.png", 1, 50)).addMouseListener(new WLLMouslisten(8));
                this.chongjiBtn.setBounds(80, 90, 53, 53);
                this.add(this.chongjiBtn);
                (this.rechargeBtn = new VipShopBtn("Inkimg/button/B22.png", 1, 52)).setBounds(79, 132, 53, 53);
                (this.oddsBtn = new VipShopBtn("Inkimg/button/B23.png", 1, 53)).setBounds(20, 145, 53, 53);
                (this.limitShopBtn = new VipShopBtn("Inkimg/button1/xsxg.png", 1, 61)).setBounds(140, 90, 53, 53);
                this.add(this.limitShopBtn);
            }
            this.tournamentsBtn = new FormsOnOffBtn("inkImg/danxin/p/e12.png", 1, UIUtils.COLOR_BTNTEXT, "", 1146);
            this.tournamentsBtn.setBounds(285, 97, 53, 53);
//            if (configure.getZhsdjsx().equals("开")) {
                this.add(this.tournamentsBtn);
//            }
            (this.showVipBtn = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 54)).addMouseListener(new WLLMouslisten(7));
            this.showVipBtn.setBounds(0, 120, 14, 38);
            (this.showIconBtn = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 60)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtn);
            (this.showIconBtnY = new VipShopBtn("Inkimg/button1/sjajdk.png", 1, 62)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtnY);
            (ZhuJpanel.bjczBtn = new FormsOnOffBtn("inkImg/hongmu/shortcut-btn.png", 1, 3000)).setBounds(20, 90, 53, 53);
            if (configure.getBjczkg().equals("开")) {
                this.add(ZhuJpanel.bjczBtn);
            }
            (ZhuJpanel.guideBtn = new FormsOnOffBtn("inkImg/hongmu/xsyd.png", 1, 68)).addMouseListener(new WLLMouslisten(12));
            ZhuJpanel.guideBtn.setBounds(20, 60, 53, 53);
            (ZhuJpanel.czlbBtn = new FormsOnOffBtn("Inkimg/background1/SS70.png", 1, 3002)).setBounds(200, 78, 53, 53);
            if (configure.getLbzxkg().equals("开")) {}
            (ZhuJpanel.pdBtn = new FormsOnOffBtn("inkImg/button/pd.png", 1, 3003)).setBounds(198, 60, 53, 53);
            if (configure.getPdgnkg().equals("开")) {
                this.add(ZhuJpanel.pdBtn);
            }
            (ZhuJpanel.labbackground = new JLabel()).setIcon(new ImageIcon("inkImg/background1/S30.png"));
            ZhuJpanel.labbackground.setBounds(0, 0, 201, 67);
            this.add(ZhuJpanel.labbackground);
            (this.ShopingMenu = new JLabel()).setBounds(205, 5, 35, 35);
            this.ShopingMenu.addMouseListener(new OnlineShopingOpenShopMouslisten());
            this.ShopingMenu.addMouseListener(new WLLMouslisten(300));
            this.add(this.ShopingMenu);
            if (configure.getCjgnkg().equals("开")) {
                (this.DrawMenu = new JLabel()).setBounds(250, 5, 35, 35);
                this.DrawMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                        FormsManagement.showForm(87);
                    }
                });
            }
            this.setBackground(UIUtils.Color_BACK);
            (this.btnOperation = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "组队操作", UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnOperation.setVisible(false);
            this.add(this.btnOperation);
            (this.btnPlatform = new TeamPanelBtn("inkImg/button1/syaj.png", 1, "组队平台", UIUtils.COLOR_WHITE1, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnPlatform.setVisible(false);
            this.add(this.btnPlatform);
            this.add(this.taskGuideView = new TaskGuideView());
            ImageIcon hotKeyBackImg = new ImageIcon("inkImg/background1/xbbkjl.png");
            this.hotKeyMethod(hotKeyBackImg);
            (this.kjsfBtn = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/快捷_w250.png"));
            this.kjsfBtn.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            (this.caidan = new VipShopBtn("Inkimg/button1/sjajgb.png", 1, 56)).addMouseListener(new WLLMouslisten(7));
            this.caidan.setBounds(0, 632, 14, 38);
            this.add(this.caidan);
            (ZhuJpanel.ttBtn = new FormsOnOffBtn("inkImg/button/ttIco.png", 1, 604)).setBounds(80, 90, 49, 49);
        }
        else {
            this.fightingbtn();
            this.setPreferredSize(new Dimension(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
            this.setLayout(null);
            ImageIcon tips = new ImageIcon("img/xy2uiimg/11_png.xy2uiimg.tips_副本.png");
            (this.labnotice = new JLabel()).setIcon(tips);
            this.labnotice.setBounds(0, ScrenceUntil.Screen_y - 164, 70, 25);
            this.labnotice.setForeground(Color.WHITE);
            this.labnotice.setText("传音");
            this.labnotice.setHorizontalTextPosition(0);
            this.labnotice.setFont(UIUtils.TEXT_FONT);
            this.choseNoticeMouslisten = new ChoseNoticeMouslisten(this);
            this.labnotice.addMouseListener(this.choseNoticeMouslisten);
            this.labnotice.setVisible(false);
            (this.labworld = new JLabel()).setIcon(tips);
            this.labworld.setBounds(0, ScrenceUntil.Screen_y - 134, 70, 25);
            this.labworld.setForeground(Color.WHITE);
            this.labworld.setText("世界");
            this.labworld.setHorizontalTextPosition(0);
            this.labworld.setFont(UIUtils.TEXT_FONT);
            this.choseWorldMouslisten = new ChoseWorldMouslisten(this);
            this.labworld.addMouseListener(this.choseWorldMouslisten);
            this.labworld.setVisible(false);
            (this.labbangs = new JLabel()).setIcon(tips);
            this.labbangs.setBounds(0, ScrenceUntil.Screen_y - 107, 70, 25);
            this.labbangs.setForeground(Color.WHITE);
            this.labbangs.setText("帮派");
            this.labbangs.setHorizontalTextPosition(0);
            this.labbangs.setFont(UIUtils.TEXT_FONT);
            this.choseBangsMouslisten = new ChoseBangsMouslisten(this);
            this.labbangs.addMouseListener(this.choseBangsMouslisten);
            this.labbangs.setVisible(false);
            (this.labgroups = new JLabel()).setIcon(tips);
            this.labgroups.setBounds(0, ScrenceUntil.Screen_y - 80, 70, 25);
            this.labgroups.setForeground(Color.WHITE);
            this.labgroups.setText("队伍");
            this.labgroups.setHorizontalTextPosition(0);
            this.labgroups.setFont(UIUtils.TEXT_FONT);
            this.choseGroupsMouslisten = new ChoseGroupsMouslisten(this);
            this.labgroups.addMouseListener(this.choseGroupsMouslisten);
            this.labgroups.setVisible(false);
            (this.labnow = new JLabel()).setIcon(tips);
            this.labnow.setBounds(0, ScrenceUntil.Screen_y - 53, 70, 25);
            this.labnow.setForeground(Color.WHITE);
            this.labnow.setText("当前");
            this.labnow.setHorizontalTextPosition(0);
            this.labnow.setFont(UIUtils.TEXT_FONT);
            this.choseNowMouslisten = new ChoseNowMouslisten(this);
            this.labnow.addMouseListener(this.choseNowMouslisten);
            this.labnow.setVisible(false);
            (this.dangqian = new JLabel()).setBounds(0, ScrenceUntil.Screen_y - 26, 70, 25);
            this.dangqian.setForeground(Color.WHITE);
            this.dangqian.setText("当前");
            this.dangqian.setVerticalTextPosition(0);
            this.dangqian.setHorizontalTextPosition(0);
            this.dangqian.setFont(UIUtils.TEXT_HYK16);
            this.choseDangQianMounslisten = new ChoseDangQianMounslisten(this);
            this.dangqian.addMouseListener(this.choseDangQianMounslisten);
            this.setVisible(true);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            (this.jLabelLeftMes = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/ltsrkh.png"));
            this.jLabelLeftMes.setBounds(70, ScrenceUntil.Screen_y - 21, 160, 43);
            (this.jLabelLeftMes1 = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/聊天框0000大1.png"));
            this.jLabelLeftMes1.setBounds(70, ScrenceUntil.Screen_y - 21, ScrenceUntil.Screen_x + 70, 25);
            this.add(this.dangqian);
            this.add(this.labnow);
            this.add(this.labgroups);
            this.add(this.labbangs);
            this.add(this.labworld);
            this.add(this.labnotice);
            this.add(this.jLabelLeftMes);
            (this.Dbiaoqing = new FormsOnOffBtn("inkImg/background1/biaoqing.png", 1, 985)).setBounds(205, ScrenceUntil.Screen_y - 35, 35, 35);
            this.add(this.Dbiaoqing);
            this.add(this.jLabelLeftMes1);
            (this.jLabelSendMes = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/hongmu/s258.png", ScrenceUntil.SendMsg_x + 50, -1));
            this.jLabelSendMes.setBounds(0, ScrenceUntil.Screen_y - 42, ScrenceUntil.SendMsg_x + 1110, 25);
            (ZhuJpanel.lableft = new SmallIconBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, "向左", this)).setBounds(19, ScrenceUntil.Screen_y + 83, 17, 17);
            this.add(ZhuJpanel.lableft);
            (ZhuJpanel.labbottom = new SmallIconBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, 1, "向下", this)).setBounds(19, ScrenceUntil.Screen_y - 47, 19, 19);
            this.add(ZhuJpanel.labbottom);
            (ZhuJpanel.labtop = new SmallIconBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, 0, "向上", this)).setBounds(38, ScrenceUntil.Screen_y - 47, 19, 19);
            this.add(ZhuJpanel.labtop);
            (ZhuJpanel.labstar = new SmallIconBtn("img/xy2uiimg/56_png.button.btn_11.png", 1, 7, "星星", this)).setBounds(57, ScrenceUntil.Screen_y - 47, 18, 18);
            this.add(ZhuJpanel.labstar);
            (ZhuJpanel.labadd = new SmallIconBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 5, "加号", this)).setBounds(76, ScrenceUntil.Screen_y - 47, 17, 17);
            this.add(ZhuJpanel.labadd);
            (ZhuJpanel.labminus = new SmallIconBtn("img/xy2uiimg/33_png.button.btn_6.png", 1, 6, "减号", this)).setBounds(95, ScrenceUntil.Screen_y - 47, 17, 17);
            this.add(ZhuJpanel.labminus);
            (ZhuJpanel.zdrwBtn = new FormsOnOffBtn("inkImg/button1/qqcdh.png", 1, 3072)).addMouseListener(new WLLMouslisten(214));
            this.add(ZhuJpanel.zdrwBtn);
            (ZhuJpanel.childBtn = new FormsOnOffBtn("inkImg/button1/xhdh.png", 1, 1)).setBounds(ScrenceUntil.Screen_x - 319, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.childBtn.addMouseListener(new WLLMouslisten(43));
            this.add(ZhuJpanel.childBtn);
            (ZhuJpanel.knapsackBtn = new FormsOnOffBtn("inkImg/button1/djldh.png", 1, 2)).setBounds(ScrenceUntil.Screen_x - 290, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.knapsackBtn.addMouseListener(new WLLMouslisten(44));
            this.add(ZhuJpanel.knapsackBtn);
            (ZhuJpanel.groupBtn = new MouseStyleBtn("inkImg/button1/dwdh.png", 1, "组队")).setBounds(ScrenceUntil.Screen_x - 261, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.groupBtn.addMouseListener(new WLLMouslisten(45));
            this.add(ZhuJpanel.groupBtn);
            (ZhuJpanel.pkBtn = new MouseStyleBtn("inkImg/button1/pkdh.png", 1, "切磋")).setBounds(ScrenceUntil.Screen_x - 232, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.pkBtn.addMouseListener(new WLLMouslisten(46));
            this.add(ZhuJpanel.pkBtn);
            (ZhuJpanel.transactionBtn1 = new MouseStyleBtn("inkImg/button1/jydh.png", 1, "交易菜单")).setBounds(ScrenceUntil.Screen_x - 174, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.transactionBtn1.addMouseListener(new WLLMouslisten(48));
            this.add(ZhuJpanel.transactionBtn1);
            (ZhuJpanel.rwjnBtn = new FormsOnOffBtn("inkImg/button1/jndh.png", 1, 8)).addMouseListener(new WLLMouslisten(47));
            this.add(ZhuJpanel.rwjnBtn);
            (ZhuJpanel.petBtn = new MouseStyleBtn("inkImg/button1/zqdh.png", 1, "坐骑")).setBounds(ScrenceUntil.Screen_x - 145, ScrenceUntil.Screen_y - 30, 25, 25);
            ZhuJpanel.petBtn.addMouseListener(new WLLMouslisten(49));
            this.add(ZhuJpanel.petBtn);
            (ZhuJpanel.taskBtn = new FormsOnOffBtn("inkImg/button1/rwdh.png", 1, 3)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.taskBtn.addMouseListener(new WLLMouslisten(50));
            this.add(ZhuJpanel.taskBtn);
            (ZhuJpanel.friendsBtn = new FormsOnOffBtn("inkImg/button1/hydh.png", 1, 4)).setBounds(ScrenceUntil.Screen_x - 87, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.friendsBtn.addMouseListener(new WLLMouslisten(51));
            this.add(ZhuJpanel.friendsBtn);
            (ZhuJpanel.gangBtn = new FormsOnOffBtn("inkImg/button1/bpdh.png", 1, 48)).setBounds(ScrenceUntil.Screen_x - 58, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.gangBtn.addMouseListener(new WLLMouslisten(52));
            this.add(ZhuJpanel.gangBtn);
            (ZhuJpanel.systemBtn = new MouseStyleBtn("inkImg/button1/szdh.png", 1, "设置")).setBounds(ScrenceUntil.Screen_x - 29, ScrenceUntil.Screen_y - 30, 29, 29);
            ZhuJpanel.systemBtn.addMouseListener(new WLLMouslisten(53));
            this.add(ZhuJpanel.systemBtn);
            (this.lowerRightImgBack = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/keybarTh.png"));
            this.lowerRightImgBack.setBounds(ScrenceUntil.Screen_x - 338, ScrenceUntil.Screen_y - 38, 338, 38);
            this.add(this.lowerRightImgBack);
            this.add(ZhuJpanel.currentBattlePetImg = new JLabel());
            (this.currentBattlePetSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlePetSwitch);
            (this.currentBattlePetLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlePetLab);
            this.add(ZhuJpanel.currentBattlelingImg = new JLabel());
            (this.currentBattlelingSwitch = new JLabel()).setIcon(new ImageIcon("inkImg/button1/gbzy.png"));
            this.add(this.currentBattlelingSwitch);
            (this.currentBattlelingLab = new JLabel()).setIcon(new ImageIcon("inkImg/button1/daiji.png"));
            this.add(this.currentBattlelingLab);
            (ZhuJpanel.labpetimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
            ZhuJpanel.labpetimg.addMouseListener(new WLLMouslisten(21));
            this.chosePetMouslisten = new PetPanelShow();
            ZhuJpanel.labpetimg.addMouseListener(this.chosePetMouslisten);
            ZhuJpanel.labpetimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 308, 6, 48, 48);
                    super.mousePressed(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labpetimg.setBounds(ScrenceUntil.Screen_x - 309, 5, 48, 48);
                    super.mouseReleased(e);
                }
            });
            this.add(ZhuJpanel.labpetimg);
            (ZhuJpanel.labroleimg = new JLabel()).setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
            ZhuJpanel.labroleimg.addMouseListener(new WLLMouslisten(20));
            this.choseRoleStateMouslisten = new RolePanelShow();
            ZhuJpanel.labroleimg.addMouseListener(this.choseRoleStateMouslisten);
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            if (nao.equals("新")) {
                if (TeststateJpanel.getQhnum().equals("1")) {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
                }
                else {
                    ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58));
                }
            }
            else if (TeststateJpanel.getQhnum().equals("1")) {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            else {
                ZhuJpanel.labroleimg.setIcon(CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58));
            }
            ZhuJpanel.labroleimg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 170, 9, 58, 58);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ZhuJpanel.labroleimg.setBounds(ScrenceUntil.Screen_x - 171, 8, 58, 58);
                }
            });
            this.add(ZhuJpanel.labroleimg);
            (this.bbexe = new JLabel()).addMouseListener(new WLLMouslisten(29));
            this.bbexe.setBounds(ScrenceUntil.Screen_x - 100, 0, 133, 52);
            this.add(this.bbexe);
            (this.bbhp = new JLabel()).addMouseListener(new ZDCYMouslisten(2));
            this.add(this.bbhp);
            (this.bbmp = new JLabel()).addMouseListener(new ZDCYMouslisten(3));
            this.add(this.bbmp);
            (this.roleexe = new JLabel()).setBounds(ScrenceUntil.Screen_x - 116, 2, 78, 10);
            this.roleexe.addMouseListener(new WLLMouslisten(28));
            this.add(this.roleexe);
            (this.rolehp = new JLabel()).addMouseListener(new ZDCYMouslisten(0));
            this.rolehp.setBounds(ScrenceUntil.Screen_x - 116, 16, 78, 10);
            this.add(this.rolehp);
            (this.rolemp = new JLabel()).addMouseListener(new ZDCYMouslisten(1));
            this.rolemp.setBounds(ScrenceUntil.Screen_x - 116, 30, 78, 10);
            this.add(this.rolemp);
            (ZhuJpanel.labrolehead = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/rwtxlh.png"));
            ZhuJpanel.labrolehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 126, 46);
            this.add(ZhuJpanel.labrolehead);
            (ZhuJpanel.petlehead = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/cwtxlh.png"));
            ZhuJpanel.petlehead.setBounds(ScrenceUntil.Screen_x - 100, 0, 108, 41);
            this.add(ZhuJpanel.petlehead);
            Article.manxie(RoleData.getRoleData().getLoginResult());
            List<RoleSummoning> petList = UserMessUntil.getPetListTable();
            if (petList != null && petList.size() > 0) {
                for (RoleSummoning summoning : petList) {
                    if (summoning != null && RoleData.getRoleData().getLoginResult().getSummoning_id() != null && summoning.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                        setLabpetimg("img/head/p" + summoning.getSummoningskin() + ".png");
                        Article.souxie(summoning);
                        break;
                    }
                }
            }
            (ZhuJpanel.MapName = new JLabel()).setBounds(15, 5, 80, 20);
            ZhuJpanel.MapName.setForeground(Color.yellow);
            if (configure.getCjgnkg().equals("开")) {
                this.cjkg = "开";
            }
            else {
                this.cjkg = "关";
            }
            (ZhuJpanel.Timemiao = new JLabel()).addMouseListener(new WLLMouslisten(0));
            ZhuJpanel.Timemiao.setBounds(106, 43, 50, 20);
            ZhuJpanel.Timemiao.setForeground(UIUtils.COLOR_WHITE1[0]);
            ZhuJpanel.Timemiao.setFont(UIUtils.TEXT_FONTZS);
            this.add(ZhuJpanel.Timemiao);
            (ZhuJpanel.xAndY = new JLabel()).setBounds(45, 11, 180, 20);
            ZhuJpanel.xAndY.setForeground(Color.white);
            this.add(ZhuJpanel.xAndY);
            Color backgroundColor = new Color(0, 0, 0);
            this.setBackground(backgroundColor);
            (this.ShopingMenu = new JLabel()).setBounds(205, 5, 35, 35);
            this.ShopingMenu.addMouseListener(new OnlineShopingOpenShopMouslisten());
            this.ShopingMenu.addMouseListener(new WLLMouslisten(300));
            this.add(this.ShopingMenu);
            if (configure.getCjgnkg().equals("开")) {
                (this.DrawMenu = new JLabel()).setBounds(250, 5, 35, 35);
                this.DrawMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                        FormsManagement.showForm(87);
                    }
                });
            }
            (ZhuJpanel.TJBtn = new FormsOnOffBtn("inkImg/button1/XTB1h.png", 1, 114)).setBounds(2, 45, 20, 20);
            ZhuJpanel.TJBtn.addMouseListener(new WLLMouslisten(304));
            this.add(ZhuJpanel.TJBtn);
            (ZhuJpanel.RanklistBtn = new FormsOnOffBtn("inkImg/button1/XTB2h.png", 1, UIUtils.COLOR_BTNTEXT, "", 60)).setBounds(24, 45, 20, 20);
            ZhuJpanel.RanklistBtn.addMouseListener(new WLLMouslisten(305));
            this.add(ZhuJpanel.RanklistBtn);
            (ZhuJpanel.CBGBtn = new FormsOnOffBtn("inkImg/button1/XTB3h.png", 1, UIUtils.COLOR_BTNTEXT, "", 78)).setBounds(46, 45, 20, 20);
            ZhuJpanel.CBGBtn.addMouseListener(new WLLMouslisten(306));
            this.add(ZhuJpanel.CBGBtn);
            (ZhuJpanel.hdrlBtn = new FormsOnOffBtn("inkImg/button1/XTB4h.png", 1, UIUtils.COLOR_BTNTEXT, "", 40)).setBounds(68, 45, 20, 20);
            ZhuJpanel.hdrlBtn.addMouseListener(new WLLMouslisten(307));
            this.add(ZhuJpanel.hdrlBtn);
            (ZhuJpanel.GGBtn = new FormsOnOffBtn("inkImg/button1/gg1.png", 1, 1001)).setBounds(2, 45, 45, 44);
            this.add(ZhuJpanel.GGBtn);
            (ZhuJpanel.mapiconBtn = new FormsOnOffBtn("inkImg/button1/B61h.png", 1, 22)).setBounds(182, 23, 20, 20);
            ZhuJpanel.mapiconBtn.addMouseListener(new WLLMouslisten(302));
            this.add(ZhuJpanel.mapiconBtn);
            (ZhuJpanel.worldMap = new FormsOnOffBtn("inkImg/button1/B62h.png", 1, 1102)).setBounds(182, 3, 20, 20);
            ZhuJpanel.worldMap.addMouseListener(new WLLMouslisten(303));
            this.add(ZhuJpanel.worldMap);
            (ZhuJpanel.zhuBtn = new FormsOnOffBtn("img/xy2uiimg/伙_w17,h51.png", 1, UIUtils.COLOR_BTNTEXT, "", 105)).setBounds(39, 34, 17, 17);
            (this.btnOperation = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "组队操作", UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnOperation.setVisible(false);
            this.add(this.btnOperation);
            (this.btnPlatform = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "组队平台", UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnPlatform.setVisible(false);
            this.add(this.btnPlatform);
            (ZhuJpanel.transactionBtn = new WorkshopBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, "交  易", 203)).setVisible(false);
            this.add(ZhuJpanel.transactionBtn);
            (ZhuJpanel.getBtn = new WorkshopBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_WHITE3, UIUtils.TEXT_HY16s, "给  予", 204)).setVisible(false);
            this.add(ZhuJpanel.getBtn);
            if (this.cjkg.equals("开")) {
                (this.cj = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "幸运抽奖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
                this.cj.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            }
            (this.yueka = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "月卡特权", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.yueka.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.yueka);
            (this.qiandao = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "每日签到", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.qiandao.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 101, 116, 24);
            this.add(this.qiandao);
            (this.gl = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "游戏攻略", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.gl.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.gl);
            (this.xszy = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "新手指引", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.xszy.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.xszy);
            (this.kjcz = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "快捷操作(Tab)", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.kjcz.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.kjcz);
            (this.jpBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "聚宝阁拍卖", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.jpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.jpBtn);
            (this.zdlhBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "自动炼化(F12)", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.zdlhBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.zdlhBtn);
            (this.drjjBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "单人竞技", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.drjjBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.drjjBtn);
            (this.cpBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "全民竞猜", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setVisible(false);
            this.cpBtn.setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            this.add(this.cpBtn);
            (ZhuJpanel.ladderPanelBtn = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "天梯竞技", UIUtils.COLOR_HONG, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 84, 116, 24);
            ZhuJpanel.ladderPanelBtn.setVisible(false);
            this.add(ZhuJpanel.ladderPanelBtn);
            (this.btnSpotCard = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "仙玉寄售", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 72, 116, 24);
            this.btnSpotCard.setVisible(false);
            this.add(this.btnSpotCard);
            (this.btnSystemSettings = new TeamPanelBtn("inkImg/button1/syajh.png", 1, "系统设置", UIUtils.COLOR_WHITE3, UIUtils.TEXT_FONT78, this)).setBounds(ScrenceUntil.Screen_x - 116, ScrenceUntil.Screen_y - 38 - 48, 116, 24);
            this.btnSystemSettings.setVisible(false);
            this.add(this.btnSystemSettings);
            if (configure.getMrczkg().equals("开")) {
                (this.chongjiBtn = new VipShopBtn("Inkimg/background1/SS70.png", 1, 50)).addMouseListener(new WLLMouslisten(8));
                this.chongjiBtn.setBounds(80, 90, 53, 53);
                this.add(this.chongjiBtn);
                (this.rechargeBtn = new VipShopBtn("Inkimg/button/B22.png", 1, 52)).setBounds(79, 132, 53, 53);
                (this.oddsBtn = new VipShopBtn("Inkimg/button/B23.png", 1, 53)).setBounds(139, 116, 53, 53);
                (this.limitShopBtn = new VipShopBtn("Inkimg/button1/xsxg.png", 1, 61)).setBounds(140, 90, 53, 53);
                this.add(this.limitShopBtn);
            }
            this.tournamentsBtn = new FormsOnOffBtn("inkImg/danxin/p/e12.png", 1, UIUtils.COLOR_BTNTEXT, "", 1146);
            this.tournamentsBtn.setBounds(285, 97, 53, 53);
//            if (configure.getZhsdjsx().equals("开")) {
                this.add(this.tournamentsBtn);
//            }
            (ZhuJpanel.ttBtn = new FormsOnOffBtn("inkImg/button/ttIco.png", 1, 604)).setBounds(80, 90, 49, 49);
            (this.showVipBtn = new VipShopBtn("Inkimg/button1/sjajgbh.png", 1, 54)).addMouseListener(new WLLMouslisten(7));
            this.showVipBtn.setBounds(0, 120, 14, 38);
            (this.showIconBtn = new VipShopBtn("Inkimg/button1/sjajdkh.png", 1, 60)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtn);
            (this.caidan = new VipShopBtn("Inkimg/button1/sjajgbh.png", 1, 56)).addMouseListener(new WLLMouslisten(7));
            this.caidan.setBounds(0, 632, 14, 38);
            this.add(this.caidan);
            (this.showIconBtnY = new VipShopBtn("Inkimg/button1/sjajdkh.png", 1, 62)).addMouseListener(new WLLMouslisten(7));
            this.add(this.showIconBtnY);
            (this.kjsfBtn = new JLabel()).setIcon(new ImageIcon("img/xy2uiimg/快捷_w250.png"));
            this.kjsfBtn.setBounds(ScrenceUntil.Screen_x - 190, ScrenceUntil.Screen_y - 76, 190, 38);
            (ZhuJpanel.czlbBtn = new FormsOnOffBtn("Inkimg/background1/SS70.png", 1, 3002)).setBounds(200, 78, 53, 53);
            if (configure.getLbzxkg().equals("开")) {}
//            bsynBtn = new FormsOnOffBtn("inkImg/hongmu/bsyn.png", 1, 3079);//CHA
//            bsynBtn.setBounds(275, 97, 67, 42);
//            bsynBtn.setVisible(true);
//            this.add(bsynBtn);
            (ZhuJpanel.pdBtn = new FormsOnOffBtn("inkImg/button/pd.png", 1, 3003)).setBounds(198, 60, 53, 53);
            if (configure.getPdgnkg().equals("开")) {
                this.add(ZhuJpanel.pdBtn);
            }

            this.aircraftBtns = new AircraftBtn[2];
            (ZhuJpanel.bjczBtn = new FormsOnOffBtn("inkImg/hongmu/shortcut-btn.png", 1, 3000)).addMouseListener(new WLLMouslisten(88));
            ZhuJpanel.bjczBtn.setBounds(20, 90, 53, 53);
            if (configure.getBjczkg().equals("开")) {
                this.add(ZhuJpanel.bjczBtn);
            }
            (ZhuJpanel.guideBtn = new FormsOnOffBtn("inkImg/hongmu/xsyd.png", 1, 68)).addMouseListener(new WLLMouslisten(12));
            ZhuJpanel.guideBtn.setBounds(20, 60, 53, 53);
            for (int k = 0; k < this.aircraftBtns.length; ++k) {
                (this.aircraftBtns[k] = new AircraftBtn("img/xy2uiimg/btnCommon102.png", 1, (k == 0) ? "坐骑" : "飞行器", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, k + 10, this)).setBounds(ScrenceUntil.Screen_x - 181, ScrenceUntil.Screen_y - (67 - k * 17), 102, 17);
                this.aircraftBtns[k].setVisible(false);
                this.add(this.aircraftBtns[k]);
            }
            (this.btnzuoqi = new AircraftBtn("inkImg/button1/syajh.png", 1, "坐  骑", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 10, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 67, 102, 17);
            this.btnzuoqi.setVisible(false);
            this.add(this.btnzuoqi);
            (this.btnfeixingqi = new AircraftBtn("inkImg/button1/syajh.png", 1, "飞  行", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 11, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - 50, 102, 17);
            this.btnfeixingqi.setVisible(false);
            this.add(this.btnfeixingqi);
            (this.btnzuojia = new AircraftBtn("inkImg/button1/syajh.png", 1, "坐  驾", UIUtils.TEXT_HY16s, UIUtils.COLOR_WHITE3, 12, this)).setBounds(ScrenceUntil.Screen_x - 302, ScrenceUntil.Screen_y - (67+17), 102, 17);
            this.btnzuojia.setVisible(false);
            this.add(this.btnzuojia);

            ImageIcon hotKeyBackImg2 = new ImageIcon("inkImg/hongmu1/xbbkjlh.png");
            this.hotKeyMethod(hotKeyBackImg2);
            (ZhuJpanel.labbackground = new JLabel()).setIcon(new ImageIcon("inkImg/hongmu1/S30h.png"));
            ZhuJpanel.labbackground.setBounds(0, 0, 201, 67);
            this.add(ZhuJpanel.labbackground);
            this.setBackground(UIUtils.Color_BACK);
            this.add(this.taskGuideView = new TaskGuideView());
        }
        getxAndY().setText(zb);
        this.BtnChange();
        ZhuJpanel.currentBattlePetImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RoleData roleData = RoleData.getRoleData();
                roleData.addHelpBb(roleData.getHelpBbId());
                SupportListJframe.getSupportListJframe().getSupportListJpanel().init(roleData.getHelpBbName(roleData.getHelpBb()));
                FormsManagement.showForm(62);
            }
        });
        this.currentBattlePetSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.isPetZhiyuan = !ZhuJpanel.isPetZhiyuan;
                ZhuJpanel.this.uptatePetZhiyuan(true);
            }
        });
        ZhuJpanel.currentBattlelingImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RoleData roleData = RoleData.getRoleData();
                String helpLing = roleData.getPackRecord().getHelpLing();
                if (StringUtils.isBlank(helpLing)) {
                    Lingbao[] lingBaos = RoleLingFa.getRoleLingFa().getLingBaos();
                    List<Lingbao> s = new ArrayList<>();
                    for (Lingbao lingBao : lingBaos) {
                        if (lingBao != null) {
                            s.add(lingBao);
                        }
                    }
                    if (s.size() > 0) {
                        BigDecimal[] array = (BigDecimal[])s.<Lingbao, Lingbao>stream().map(Lingbao::getBaoid).toArray(BigDecimal[]::new);
                        String join = StringUtils.join(array, "|");
                        RoleData.getRoleData().sendHelpLingbao(join);
                    }
                }
                String helpLing2 = RoleData.getRoleData().getPackRecord().getHelpLing();
                List<Lingbao> lingbaoList = new ArrayList<>();
                List<String> delList = new ArrayList<>();
                if (StringUtils.isNotEmpty(helpLing2)) {
                    for (Lingbao lingBao2 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                        if (lingBao2 != null && !helpLing2.contains(lingBao2.getBaoid().toString())) {
                            helpLing2 = helpLing2 + "|" + lingBao2.getBaoid().toString();
                        }
                    }
                    String[] split = helpLing2.split("\\|");
                    for (String s2 : split) {
                        Boolean b = Boolean.valueOf(true);
                        try {
                            for (Lingbao lingBao3 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                                if (lingBao3 != null && lingBao3.getBaoid().toString().equals(s2)) {
                                    lingbaoList.add(lingBao3);
                                    b = Boolean.valueOf(false);
                                    break;
                                }
                            }
                        }
                        catch (Exception e2) {
                            continue;
                        }
                        if ((boolean)b) {
                            delList.add(s2);
                        }
                    }
                    List<String> collect = Arrays.asList(split);
                    List<String> collect2 = new ArrayList<>();
                    for (String s2 : collect) {
                        Boolean b = Boolean.valueOf(true);
                        Iterator<String> iterator2 = delList.iterator();
                        if (iterator2.hasNext()) {
                            String string = (String)iterator2.next();
                            b = Boolean.valueOf(false);
                        }
                        if ((boolean)b) {
                            collect2.add(s2);
                        }
                    }
                    if (collect2.size() > 0) {
                        lingbaoList.clear();
                        String[] lingIds = new String[collect2.size()];
                        collect2.toArray(lingIds);
                        String join2 = StringUtils.join(lingIds, "|");
                        RoleData.getRoleData().sendHelpLingbao(join2);
                        for (String s3 : lingIds) {
                            for (Lingbao lingBao4 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                                if (lingBao4 != null && lingBao4.getBaoid().toString().equals(s3.toString())) {
                                    lingbaoList.add(lingBao4);
                                    break;
                                }
                            }
                        }
                    }
                    LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
                }
                else {
                    LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
                }
                FormsManagement.showForm(622);
            }
        });
        this.currentBattlelingSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.isLingZhiyuan = !ZhuJpanel.isLingZhiyuan;
                ZhuJpanel.this.uptateLingZhiyuan(true);
            }
        });
        ZhuJpanel.currentBattlePetImg.setVisible(false);
        this.currentBattlePetSwitch.setVisible(false);
        this.currentBattlePetLab.setVisible(false);
        ZhuJpanel.currentBattlelingImg.setVisible(false);
        this.currentBattlelingSwitch.setVisible(false);
        this.currentBattlelingLab.setVisible(false);
        if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + 6, ScrenceUntil.Screen_y + 28);
        }
        else if (MyIsif.ifs.equals("D")) {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y + 28);
        }
        else {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X - 6, ScrenceUntil.Screen_y);
        }
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public void hideHotKey() {
        this.hotKeyBack.setVisible(false);
        int Flag = 0;
        int count = 1;
        for (int j = 0; j < 8; ++j) {
            this.hotKeyGoodsListLabelFID[j].setVisible(false);
            this.hotKeyGoodsListLabel[j].setVisible(false);
            int row = j % 8 * 40;
            int col = j / 8 * 40;
            this.hotKeyGoodsListLabelFID[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
            this.hotKeyGoodsListLabel[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
        }
    }
    
    public void showHotKey() {
        this.hotKeyBack.setVisible(true);
        int Flag = 0;
        int count = 1;
        for (int j = 0; j < 8; ++j) {
            this.hotKeyGoodsListLabelFID[j].setVisible(true);
            this.hotKeyGoodsListLabel[j].setVisible(true);
            int row = j % 8 * 40;
            int col = j / 8 * 40;
            this.hotKeyGoodsListLabelFID[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
            this.hotKeyGoodsListLabel[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
        }
    }
    
    private void hotKeyMethod(ImageIcon hotKeyBackImg) {
        this.choseGoodsJLabel = new JLabel[8];
        this.hotKeyGoodsListLabel = new JLabel[8];
        this.hotKeyGoodsListLabelFID = new JLabel[8];
        this.goodsMouslistens = new HotKeyMouseListen[8];
        int Flag = 0;
        int count = 1;
        for (int j = 0; j < 8; ++j) {
            int row = j % 8 * 40;
            int col = j / 8 * 40;
            this.hotKeyGoodsListLabelFID[j] = new JLabel();
            this.hotKeyGoodsListLabel[j] = new JLabel();
            String name = "hot_" + String.valueOf(j);
            String value = (String)HotKeyMouseListen.keyMap.get(name);
            if (StrUtil.isNotBlank(value)) {
                boolean number = NumberUtil.isNumber(value);
                if (number) {
                    int chaxuns = GoodsListFromServerUntil.chaxuns(Integer.parseInt(value));
                    if (chaxuns != -1) {
                        Goodstable goodstable = GoodsListFromServerUntil.Getgood(chaxuns);
                        if (goodstable != null) {
                            this.hotKeyGoodsListLabelFID[j].setIcon(new ImageIcon(new ImageIcon("resource/jiuUI/F" + j + ".png").getImage().getScaledInstance(35, 35, 1)));
                            this.hotKeyGoodsListLabel[j].setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(35, 35, 1)));
                        }
                    }
                }
                else {
                    String s = value.split("_")[1];
                    Skill skill = UserMessUntil.getSkillById(s);
                    if (skill != null) {
                        this.hotKeyGoodsListLabelFID[j].setIcon(new ImageIcon(new ImageIcon("resource/jiuUI/F" + j + ".png").getImage().getScaledInstance(35, 35, 10)));
                        this.hotKeyGoodsListLabel[j].setIcon(new ImageIcon(new ImageIcon("img/fighting-skill/" + skill.getSkillid() + ".png").getImage().getScaledInstance(35, 35, 10)));
                    }
                }
                this.hotKeyGoodsListLabelFID[j].setVisible(true);
                this.hotKeyGoodsListLabel[j].setVisible(true);
            }
            this.hotKeyGoodsListLabel[j].setName(name);
            this.goodsMouslistens[j] = new HotKeyMouseListen(j, this);
            this.hotKeyGoodsListLabel[j].addMouseListener(this.goodsMouslistens[j]);
            if (Flag < 8 && count == 1) {
                this.hotKeyGoodsListLabelFID[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
                this.hotKeyGoodsListLabel[j].setBounds(ScrenceUntil.Screen_x - 326 + (row + 8), ScrenceUntil.Screen_y - 85 + (col + 4), 41, 41);
                ++Flag;
                this.add(this.hotKeyGoodsListLabelFID[j]);
                this.add(this.hotKeyGoodsListLabel[j]);
            }
            else if (Flag == 8) {
                Flag = 0;
                ++count;
            }
        }
        (this.hotKeyBack = new JLabel()).setIcon(hotKeyBackImg);
        this.hotKeyBack.setBounds(ScrenceUntil.Screen_x - 321, ScrenceUntil.Screen_y - 81, 321, 41);
        this.hotKeyBack.setForeground(Color.WHITE);
        this.hotKeyBack.setHorizontalTextPosition(0);
        this.hotKeyBack.setFont(UIUtils.TEXT_FONT);
        this.hotKeyBack.setVisible(true);
        this.add(this.hotKeyBack);
        if (MyIsif.getStyle().equals("水墨")) {
            ZhuJpanel.hotKey = new FormsOnOffBtn("Inkimg/button1/kjlsxaj.png", 1, 888);
        }
        else {
            ZhuJpanel.hotKey = new FormsOnOffBtn("Inkimg/button1/kjlsxajh.png", 1, 888);
        }
        ZhuJpanel.hotKey.addMouseListener(new WLLMouslisten(7));
        ZhuJpanel.hotKey.setBounds(ScrenceUntil.Screen_x - 333, ScrenceUntil.Screen_y - 79, 12, 37);
        this.add(ZhuJpanel.hotKey);
    }
    
    public JLabel getHotKeyBack() {
        return this.hotKeyBack;
    }
    
    public void setHotKeyBack(JLabel hotKeyBack) {
        this.hotKeyBack = hotKeyBack;
    }
    
    public static FormsOnOffBtn getHotKey() {
        return ZhuJpanel.hotKey;
    }
    
    public static void setHotKey(FormsOnOffBtn hotKey) {
        ZhuJpanel.hotKey = hotKey;
    }
    
    public void clearHotkeySkill() {
        try {
            String roleId = ImageMixDeal.userimg.getRoleShow().getRole_id().toString();
            JSON parse = JSONUtil.parse(Util.readHotkey(roleId));
            if (parse != null) {
                Map hashMap = HotKeyMouseListen.keyMap = (Map)parse.toBean(Map.class);
                System.out.println(hashMap);
            }
            int Flag = 0;
            int count = 1;
            for (int j = 0; j < 8; ++j) {
                String name = "hot_" + String.valueOf(j);
                String value = (String)HotKeyMouseListen.keyMap.get(name);
                boolean number = NumberUtil.isNumber(value);
                if (number) {
                    int chaxuns = GoodsListFromServerUntil.chaxuns(Integer.parseInt(value));
                    if (chaxuns != -1) {
                        Goodstable goodstable = GoodsListFromServerUntil.Getgood(chaxuns);
                        this.hotKeyGoodsListLabelFID[j].setIcon(new ImageIcon(new ImageIcon("resource/jiuUI/F" + j + ".png").getImage().getScaledInstance(35, 35, 10)));
                        this.hotKeyGoodsListLabel[j].setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(35, 35, 10)));
                        HotKeyMouseListen.keyMap.put(name, goodstable.getGoodsid().toString());
                        String skey = JSONUtil.toJsonStr(HotKeyMouseListen.keyMap);
                        Util.writeHotkey(roleId, skey);
                    }
                }
                else if (StrUtil.isNotBlank(value)) {
                    this.hotKeyGoodsListLabelFID[j].setIcon(null);
                    this.hotKeyGoodsListLabel[j].setIcon(null);
                    HotKeyMouseListen.keyMap.remove(name);
                    String skey2 = JSONUtil.toJsonStr(HotKeyMouseListen.keyMap);
                    Util.writeHotkey(roleId, skey2);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hotKeyImg() {
        try {
            String roleId = ImageMixDeal.userimg.getRoleShow().getRole_id().toString();
            String s = Util.readHotkey(roleId);
            if (s == null) {
                return;
            }
            JSON parse = JSONUtil.parse(s);
            Map hashMap = HotKeyMouseListen.keyMap = (Map)parse.toBean(Map.class);
            System.out.println(hashMap);
            int Flag = 0;
            int count = 1;
            for (int j = 0; j < 8; ++j) {
                int row = j % 8 * 40;
                int col = j / 8 * 40;
                String name = "hot_" + String.valueOf(j);
                String value = (String)HotKeyMouseListen.keyMap.get(name);
                boolean number = NumberUtil.isNumber(value);
                if (number) {
                    int chaxuns = GoodsListFromServerUntil.chaxuns(Integer.parseInt(value));
                    if (chaxuns != -1) {
                        Goodstable goodstable = GoodsListFromServerUntil.Getgood(chaxuns);
                        this.hotKeyGoodsListLabelFID[j].setIcon(new ImageIcon(new ImageIcon("resource/jiuUI/F" + j + ".png").getImage().getScaledInstance(35, 35, 10)));
                        this.hotKeyGoodsListLabel[j].setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(35, 35, 10)));
                    }
                }
                else if (StrUtil.isNotBlank(value)) {
                    value = value.split("_")[1];
                    this.hotKeyGoodsListLabelFID[j].setIcon(new ImageIcon(new ImageIcon("resource/jiuUI/F" + j + ".png").getImage().getScaledInstance(35, 35, 10)));
                    this.hotKeyGoodsListLabel[j].setIcon(new ImageIcon(new ImageIcon("img/fighting-skill/" + value + ".png").getImage().getScaledInstance(35, 35, 10)));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static FormsOnOffBtn getZhsbd() {
        return ZhuJpanel.zhsbd;
    }
    
    public static void setZhsbd(FormsOnOffBtn zhsbd) {
        ZhuJpanel.zhsbd = zhsbd;
    }
    
    public static FormsOnOffBtn getTtBtn() {
        return ZhuJpanel.ttBtn;
    }
    
    public static void setTtBtn(FormsOnOffBtn ttBtn) {
        ZhuJpanel.ttBtn = ttBtn;
    }
    
    public FightingBtn getChehui() {
        return this.chehui;
    }
    
    public void setChehui(FightingBtn chehui) {
        this.chehui = chehui;
    }

    public static FormsOnOffBtn getbsynBtn() {
        return ZhuJpanel.bsynBtn;
    }
    public static void setBsynBtn(FormsOnOffBtn bsynBtn) {
        ZhuJpanel.bsynBtn = bsynBtn;
    }
//    public static FormsOnOffBtn getGZBtn() {
//        return ZhuJpanel.gzBtn;
//    }

    static {
        ZhuJpanel.battlePetZhiSwitch = true;
        ZhuJpanel.battleLingZhiSwitch = true;
        ZhuJpanel.listMount = new ArrayList<>();
        ZhuJpanel.listCar = new ArrayList<>();

        ZhuJpanel.ListFly = new ArrayList<>();
        ZhuJpanel.styles = false;
        ZhuJpanel.MallX = SpriteFactory.VloadSprite("inkImg/hongmu/4389-D1D89FFE.tcp", null);
        ZhuJpanel.tcp2 = SpriteFactory.VloadSprite("inkImg/hongmu/4389-D1D89FFE.tcp", null);
        ZhuJpanel.tcp1 = SpriteFactory.VloadSprite("resource/mouse/mall-border.tcp", null);
        ZhuJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/mall-border.tcp", null);
        if (ZhuJpanel.tcp == null) {
        }
        ZhuJpanel.Mall = SpriteFactory.VloadSprite("resource/mouse/商城.tcp", null);
        ZhuJpanel.Draw = SpriteFactory.VloadSprite("resource/mouse/奖.tcp", null);
        if (ZhuJpanel.Draw == null) {
            ZhuJpanel.Draw = ZhuJpanel.Mall;
        }
    }
    private static Goodstable goodstableAlf;
    public static void setGoodstableAlf(final Goodstable goodstableAlf) {
        ZhuJpanel.goodstableAlf = goodstableAlf;
    }
    public void setObtainGod(ImageIcon obtainGod) {
        this.obtainGod = obtainGod;
    }
    public static ImageIcon obtainGod = new ImageIcon("inkImg/background/S30.png");
    public ImageIcon getObtainGod() {
        return obtainGod;
    }
    public static Goodstable getGoodstableAlf() {
        return ZhuJpanel.goodstableAlf;
    }
    public static boolean dhb = false; // 动画控制变量
    private static double scale = 1.0; // 缩放因子
    private static int xPosition = 0; // 图片的y坐标
    private static int yPosition = 0; // 图片的y坐标
    public static Sprite[] yhbztx = new Sprite[15];
    public static int[] yhx = new int[15];
    public static int[] yhtime = new int[15];
    public void initDisplayTimer() {
        xPosition = (int) (ScrenceUntil.Screen_x - ScrenceUntil.Screen_x / 2.5);
//        xPosition = ScrenceUntil.Screen_x - 298 - 30;
        yPosition = ScrenceUntil.Screen_y - 32 - 120;
        scale = 0.5;
        displayTimer = new Timer(200, new ActionListener() { //延迟
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation(); // 启动动画
                displayTimer.stop(); // 停止显示定时器
            }
        });
        displayTimer.setRepeats(false); // 设置为非重复定时器
        displayTimer.start();
    }
    private static Timer displayTimer; // 显示定时器
    public static void startAnimation() {
        //ScrenceUntil.Screen_x - 290, ScrenceUntil.Screen_y - 30
        // 设置动画开始
        timer.start(); // 启动定时器
    }
    private static Timer timer;

    private void initTimer() {
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dhb) {
                    // 每次更新时缩小并向下移动
                    scale -= 0.02; // 缩放速度
                    yPosition += 9; // 移动速度
                    xPosition += 3;
                    if (scale <= 0) {
                        scale = 0;
                        dhb = false; // 动画结束时设置为false
                        timer.stop(); // 停止定时器
                    }
                    repaint(); // 重绘
                }
            }
        });
    }
}
