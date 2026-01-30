package com.tool.btn;

import java.util.Iterator;

import come.tool.JDialog.TiShiUtil;
import org.come.Frame.*;
import org.come.Jpanel.WelcomeXyJpanel;
import org.come.XuanBao.RoleXuanBao;
import org.come.annex.Tournaments.Other.GameFiguresBean;
import org.come.bean.*;
import org.come.socket.GameClient;
import com.tool.role.RoleData;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.socket.DownLoadTxt;
import com.tool.tcp.GetTcpPath;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ManagementFactory;
import org.come.Jpanel.GiveJpanel;
import org.come.model.Shop;
import org.come.entity.Baby;
import org.come.Jpanel.NewRefiningJpanel;
import org.come.until.LotteryFromServerUntil;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.mouslisten.HotKeyMouseListen;
import com.tool.imagemonitor.NpcMonitor;
import org.come.until.DDGoodUntil;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ImageMixDeal;
import org.come.daily.JframeDailyMain;
import org.come.until.GsonUtil;
import org.lottery.frame.LotteryMainFrame;
import org.come.until.NpcMenuUntil;
import org.come.action.MapAction;
import org.come.action.NpcMenuAction;

import java.util.List;

import org.come.until.UserStallUntil;
import org.come.until.Util;
import org.come.until.ScrenceUntil;
import org.come.until.Music;
import org.skill.frame.SkillMainFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.MessagrFlagUntil;
import org.come.Jpanel.ZhuJpanel;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URI;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import org.come.until.UserMessUntil;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

public class FormsOnOffBtn extends MoBanBtn
{
    private static final long serialVersionUID = 7822149256715030997L;
    private final int formsid;
    private int x;
    private int y;
    private String text;
    private boolean is;
    
    public FormsOnOffBtn(String iconpath, int type, int id) {
        super(iconpath, type);
        this.formsid = id;
    }
    
    public FormsOnOffBtn(String iconpath, int type, Color[] colors, String text, int id) {
        super(iconpath, type, colors);
        this.formsid = id;
        this.text = text;
        File file = new File(iconpath);
        try {
            BufferedImage src = ImageIO.read(file);
            int destWidth = src.getWidth();
            int destHeight = src.getHeight() / 3;
            this.x = (destWidth - 12) / 2;
            this.y = destHeight / 2 + 3;
        }
        catch (IOException var10) {
            var10.printStackTrace();
        }
    }
    
    public FormsOnOffBtn(String iconpath, int type, Color[] colors, Font font, String text, int id) {
        super(iconpath, type, colors);
        this.formsid = id;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.text != null) {
            if (this.type == 2) {
                g.drawString(this.text, this.x + 1, this.y + 1);
            }
            else {
                g.drawString(this.text, this.x, this.y);
            }
        }
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        System.out.println("formid is:"+this.formsid);
        if (FormsManagement.getInternalForm3(this.formsid) == null) {
            switch (this.formsid) {
                case 0: {
                    RolePanelShow.Show();
                    break;
                }
                case 1: {
                    Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
                    FormsManagement.showForm(1);
                    break;
                }
                case 2999: {
                    this.complainOpenWeb1();
                    break;
                }
                case 114: {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("resource\\other\\gonglue.txt"));
                        String line = "";
                        StringBuffer sb = new StringBuffer();
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                            String stringParams = sb.toString();
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.browse(new URI(stringParams));
                            }
                            catch (IOException var7) {
                                var7.printStackTrace();
                            }
                            catch (URISyntaxException var8) {
                                var8.printStackTrace();
                            }
                        }
                        break;
                    }
                    catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                        break;
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                        break;
                    }
                }
                case 2: {
                    ZhuJpanel.setUseGoodsType(0);
                    FormsManagement.showForm(2);
                    break;
                }
                case 3: {
                    FormsManagement.showForm(3);
                    TesttaskJframe.getTesttaskJframe().getJtask().showTaskMethod();
                    break;
                }
                case 4: {
                    MessagrFlagUntil.ReceiveFriend();
                    break;
                }
                case 5: {
                    String oneArenaSendmes = Agreement.getAgreement().oneArenaAgreement("1");
                    SendMessageUntil.toServer(oneArenaSendmes);
                    break;
                }
                case 7: {
                    String sendmes = Agreement.getAgreement().MountAgreement();
                    SendMessageUntil.toServer(sendmes);
                    break;
                }
                case 8: {
                    SkillMainFrame.getSkillMainFrame().getSkillMainPanel().changeBtnPanel(0);
                    FormsManagement.showForm(82);
                    Music.addyinxiao("开关窗口.mp3");
                    break;
                }
                case 22: {
                    TestsmallmapJframe.getTestsmallmapJframe().setLocation(ScrenceUntil.Screen_x / 2 - Util.mapmodel.getMin_x() / 2 - 41, ScrenceUntil.Screen_y / 2 - Util.mapmodel.getMin_y() / 2 - 20);
                    FormsManagement.showForm(this.formsid);
                    break;
                }
                case 29: {
                    if (UserStallUntil.isStall()) {
                        return;
                    }
                    FormsManagement.showForm(this.formsid);
                    break;
                }
                case 40: {
                    if (FormsManagement.getInternalForm2(40) != null) {
                        ActivityJframe.getActivityJframe().getActivityJpanel().refreshView();
                    }
                    FormsManagement.showForm(this.formsid);
                    break;
                }
                case 77:
                case 130: {//符文商店
                    List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("88");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop, "88", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 48: {
                    Show();
                    break;
                }
                case 60: {
                    String mes = Agreement.getAgreement().pankinglistAgreement("1");
                    SendMessageUntil.toServer(mes);
                    break;
                }
                case 78: {
                    ZhuFrame.getZhuJpanel().addPrompt2("功能未开放!");
                    return;
                }
                case 79: {
                    if (!FormsManagement.getframe(23).isVisible()) {
                        List<Shop> npcshop2 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("5");
                        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop2, "5", null);
                        FormsManagement.showForm(23);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(23);
                        break;
                    }
                }
                case 80: {
                    ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.getCurerolesumming())).menuControl("治疗所有");
                    break;
                }
                case 81: {
                    NPCJfram.getNpcJfram().getNpcjpanel().shuangbeijingyan();
                    break;
                }
                case 83: {
                    ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.getMenu9())).menuControl("我要住店(扣除2000银两)");
                    break;
                }
                case 84: {
                    GemMakeFrame.getGemMakeFrame().getJpanel().qh(0);
                    GemMakeFrame.getGemMakeFrame().getJpanel().qh(0);
                    GemMakeFrame.getGemMakeFrame().getJpanel().lingNumChange(0);
                    FormsManagement.showForm(84);
                    break;
                }
                case 851: {
                    List<Shop> npcshop3 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("85");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop3, "85", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 82: {
                    ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.DBEXP)).menuControl("我要冻结双倍时间");
                    break;
                }
                case 87: {
                    LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                    FormsManagement.showForm(87);
                    break;
                }
                case 88: {
                    System.exit(0);
                    break;
                }
                case 89: {
                    GoodSEarchBean goods = new GoodSEarchBean();
                    goods.setType(0);
                    goods.setGoodname(null);
                    String sendmes2 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(goods));
                    SendMessageUntil.toServer(sendmes2);
                    FormsManagement.showForm(90);
                    break;
                }
                case 91: {
                    STRTMP();
                    break;
                }
                case 111: {//试炼幻境
                    DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel().showLvlTier(Integer.valueOf(1));
                    FormsManagement.showForm(111);
                    return;
                }
                case 125: {
                    ApplyQianDao applyQianDao = new ApplyQianDao();
                    applyQianDao.setType(1);
                    String sendmes3 = Agreement.getAgreement().APPQIANDAOAgreement(GsonUtil.getGsonUtil().getgson().toJson(applyQianDao));
                    SendMessageUntil.toServer(sendmes3);
                    FormsManagement.showForm(125);
                    break;
                }
                case 90: {
                    JframeDailyMain.getJframeDailyMain().getJpanelDailyMain().getEventMap(-1);
                    FormsManagement.showForm(90);
                    break;
                }
                case 99: {
                    FormsManagement.showForm(99);
                    IphoneVerifyFrame.getIphoneVerifyFrame().getIphoneVerifyPanel().changeMenu(-1);
                    break;
                }
                case 105: {
                    PartnerJframe.getPartnerJframe().getPartnerMainJpanel().addPartnerUnit();
                    break;
                }
                case 604: {
                    SendMessageUntil.toServer(Agreement.getAgreement().pankinglistAgreement("8"));
                    Music.addyinxiao("开关窗口.mp3");
                    FormsManagement.showForm(604);
                    break;
                }
                case 801: {
                    String npctype = "5";
                    List<Shop> npcshop4 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get(npctype);
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop4, npctype, null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 802: {
                    GiveJpanel giveJpanel = GiveJframe.getGivejframe().getGivejpanel();
                    if (FormsManagement.getframe(12).isVisible()) {
                        ZhuFrame.getZhuJpanel().addPrompt("您当前状态不可操作,请稍后再试！");
                        return;
                    }
                    giveJpanel.giveShow(ImageMixDeal.getNpc("400256"));
                    break;
                }
                case 804: {
                    GoodsListFromServerUntil.zhengli();
                    return;
                }
                case 985: {
                    if (!FormsManagement.getframe(52).isVisible()) {
                        FormsManagement.showForm(52);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(52);
                        break;
                    }
                }
                case 805: {
                    DDGoodUntil.zhengli();
                    return;
                }
                case 999: {
                    NPCJfram.getNpcJfram().getNpcjpanel().anniutanchuang();
                    break;
                }
                case 9999: {//神兽大将
                    NPCJfram.getNpcJfram().getNpcjpanel().shenshoudajiang();
                    break;
                }
                case 1024: {
                    NPCJfram.getNpcJfram().getNpcjpanel().dangpu();
                    break;
                }
                case 1025: {
                    NPCJfram.getNpcJfram().getNpcjpanel().wupinduihuan();
                    break;
                }
                case 1026: {
                    NPCJfram.getNpcJfram().getNpcjpanel().yiyuan();
                    break;
                }
                case 1100: {
                    SendMessageUntil.toServer(Agreement.getAgreement().qdAgreement("open"));
                    break;
                }
                case 1101: {//任我行
                    ChaojifeiJframe.getChaojifeiListJpanel();
                    FormsManagement.showForm(1101);
                    break;
                }
                case 1102: {//任我行
                    WorldMapJframe.getWorldMapJpanel();
                    FormsManagement.showForm(1102);
                    break;
                }
                case 1103: {
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel();
                    FormsManagement.showForm(1103);
                    break;
                }
                case 1104: {//活动日历
                    EventCalendarJframe.getEventCalendarJpanel();
                    FormsManagement.showForm(1104);
                    break;
                }
                case 1105: {
                    SendMessageUntil.toServer(Agreement.getAgreement().LotteryCPAgreement("QPEN"));
                    FormsManagement.showForm(1105);
                    break;
                }
                case 2000: {
                    GMshopJframe.getGMshopJpanel();
                    FormsManagement.showForm(2000);
                    break;
                }
                case 3000: {
                    FormsManagement.showForm(3000);
                    break;
                }
                case 30001: {
                    ZhuFrame.getZhuJpanel().addPrompt("功能正在开发中尽情期待！");
                    break;
                }
                case 3001: {
                    AthChartJframe.getAthChartJPanel();
                    FormsManagement.showForm(3001);
                    LaborRank laborRank = new LaborRank();
                    laborRank.setRank(11);
                    laborRank.setValue1("");
                    AthChartJframe.getAthChartJPanel().showViewData(laborRank);
                    break;
                }
                case 3002: {
                    RechargeVIPJframe.getRechargeVIPJpanel();
                    FormsManagement.showForm(3002);
                    RechargeVIPJframe.getRechargeVIPJpanel().getShop();
                    break;
                }
                case 3003: {
                    FormsManagement.showForm(3003);
                    break;
                }
                case 120: {//慈恩寺监院大雁塔积分
                    List<Shop> npcshop5 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("120");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop5, "120", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 121: {//地宫
                    List<Shop> npcshop6 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("121");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop6, "121", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 123: {//寻访
                    List<Shop> npcshop7 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("123");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop7, "123", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 126: {//水路
                    List<Shop> npcshop8 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("126");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop8, "126", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 127: {//天梯积分
                    List<Shop> npcshop9 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("2029");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop9, "2029", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 128: {//大闹NPC+商店
                    List<Shop> npcshop10 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("605");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop10, "605", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 129: {//师门NPC+师门商店
                    List<Shop> npcshop11 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("89");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop11, "89", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 131: {//洛阳药店+
                    List<Shop> npcshop12 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("9");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop12, "9", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 887: {//孤竹城
                    List<Shop> npcshop13 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("887");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop13, "887", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 180: {//符石商店
                    List<Shop> npcshop14 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("180");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop14, "180", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 181: {//人物测试
                    List<Shop> npcshop15 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("181");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop15, "181", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 99129: {//人物测试
                    List<Shop> npcshop15 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("99129");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop15, "99129", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 182: {//宝宝测试
                    List<Shop> npcshop16 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("182");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop16, "182", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 185: {
                    List<Shop> npcshop17 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("185");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop17, "185", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 184: {
                    List<Shop> npcshop18 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("131");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop18, "131", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 7120:
                    List<Shop> npcshop7120 = UserMessUntil.getNpcshop().getNpcShopMap().get("7120");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop7120, "7120", null);
                    FormsManagement.showForm(23);
                    break;
                case 8900: {//副本积分
                    List<Shop> npcshop8900= (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("8900");
                    ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop8900, "8900", null);
                    FormsManagement.showForm(23);
                    break;
                }
                case 600: {//掉落查询
                    FindDropJfram.getFindDropJfram().getFindDropJpanel();
                    FormsManagement.showForm(600);
                    break;
                }
                case 803 :
                    NPCJfram.getNpcJfram().getNpcjpanel().xuanyinhetanchuang();
                    break;
                case 806: {
                    NpcMonitor.npc(ImageMixDeal.getNpc("300014"));
                    return;
                }
                case 8039:
                    // 删除玄宝功能
                    XuanBao chosenXuanBao = RoleXuanBao.getRoleXuanBao().choseBao;
                    if (chosenXuanBao == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个玄宝");
                        return;
                    }
                    // 显示确认对话框，确认是否删除玄宝
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(
                            TiShiUtil.DeleteXuanBao,
                            chosenXuanBao,
                            "#W此功能将删除当前选中的玄宝，删除后无法恢复，你确定要删除吗？"
                    );
                    break;
                case 888: {
                    try {
                        if ((boolean)HotKeyMouseListen.isHide) {
                            if (MyIsif.getStyle().equals("水墨")) {
                                ZhuFrame.getZhuJpanel();
                                ZhuJpanel.getHotKey().setIcons(CutButtonImage.cuts("inkImg/button1/kjlsxaj.png"));
                            }
                            else {
                                ZhuFrame.getZhuJpanel();
                                ZhuJpanel.getHotKey().setIcons(CutButtonImage.cuts("inkImg/button1/kjlsxajh.png"));
                            }
                            ZhuFrame.getZhuJpanel();
                            ZhuJpanel.getHotKey().setBounds(ScrenceUntil.Screen_x - 333, ScrenceUntil.Screen_y - 79, 14, 37);
                            ZhuFrame.getZhuJpanel().showHotKey();
                            HotKeyMouseListen.isHide = Boolean.valueOf(false);
                        }
                        else {
                            if (MyIsif.getStyle().equals("水墨")) {
                                ZhuFrame.getZhuJpanel();
                                ZhuJpanel.getHotKey().setIcons(CutButtonImage.cuts("inkImg/button1/kjlkzaj.png"));
                            }
                            else {
                                ZhuFrame.getZhuJpanel();
                                ZhuJpanel.getHotKey().setIcons(CutButtonImage.cuts("inkImg/button1/kjlkzajh.png"));
                            }
                            ZhuFrame.getZhuJpanel();
                            ZhuJpanel.getHotKey().setBounds(ScrenceUntil.Screen_x - 12, ScrenceUntil.Screen_y - 79, 12, 37);
                            ZhuFrame.getZhuJpanel().hideHotKey();
                            HotKeyMouseListen.isHide = Boolean.valueOf(true);
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return;
                }
                case 3100: {
                    FormsManagement.showForm(637);
                    return;
                }
//                case 3072: {
//                    FormsManagement.showForm(3072);
//                    return;
//                }
                case 638: {
                    if (!FormsManagement.getframe(638).isVisible()) {
                        FormsManagement.showForm(638);
                    }
                    else {
                        FormsManagement.HideForm(638);
                    }
                    return;
                }
                case 4000: {
                    NPCJfram.getNpcJfram().getNpcjpanel().xianqihecheng();
                    return;
                }
                case 4001: {
                    NPCJfram.getNpcJfram().getNpcjpanel().shenbingdazao();
                    return;
                }
                case 4002: {
                    NPCJfram.getNpcJfram().getNpcjpanel().baoshidazao();
                    return;
                }
                case 4003: {
                    NPCJfram.getNpcJfram().getNpcjpanel().renwuzhuansheng();
                    return;
                }
                case 400301: {
                    NPCJfram.getNpcJfram().getNpcjpanel().huangdaxian();
                    return;
                }
                case 400302: {//终极重修灵兽村长
                    NPCJfram.getNpcJfram().getNpcjpanel().lingshoucun();
                    return;
                }
                case 4004: {
                    NPCJfram.getNpcJfram().getNpcjpanel().shipinpeiyang();
                    return;
                }
                case 4005: {
                    NPCJfram.getNpcJfram().getNpcjpanel().taozhuangdazao();
                    return;
                }
                case 4006: {
                    FormsManagement.showForm(36);
                    return;
                }
                case 40031: {
                    FormsManagement.showForm(40);
                    return;
                }
                case 4007: {
                    FormsManagement.showForm(700);
                    return;
                }
                case 4008: {
                    if (!FormsManagement.getframe(23).isVisible()) {
                        List<Shop> npcshop19 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("15");
                        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop19, "15", null);
                        FormsManagement.showForm(23);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(23);
                        break;
                    }
                }
                case 4009: {
                    if (!FormsManagement.getframe(23).isVisible()) {
                        List<Shop> npcshop20 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("14");
                        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop20, "14", null);
                        FormsManagement.showForm(23);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(23);
                        break;
                    }
                }
                case 4010: {
                    if (!FormsManagement.getframe(23).isVisible()) {
                        List<Shop> npcshop21 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("13");
                        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop21, "13", null);
                        FormsManagement.showForm(23);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(23);
                        break;
                    }
                }
                case 4011: {
                    if (!FormsManagement.getframe(23).isVisible()) {
                        List<Shop> npcshop22 = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("7");
                        ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop22, "7", null);
                        FormsManagement.showForm(23);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(23);
                        break;
                    }
                }
                case 43: {
                    if (!FormsManagement.getframe(43).isVisible()) {
                        FormsManagement.showForm(43);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(43);
                        break;
                    }
                }
                case 1001: {
                    if (!FormsManagement.getframe(57).isVisible()) {
                        FormsManagement.showForm(57);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(57);
                        break;
                    }
                }
                case 1002: {
                    if (!FormsManagement.getframe(1002).isVisible()) {
                        FormsManagement.showForm(1002);
                        break;
                    }
                    else {
                        FormsManagement.HideForm(1002);
                        break;
                    }
                }
                case 1146:
                    GameFiguresBean gameFiguresBean = new GameFiguresBean();
                    gameFiguresBean.setTotal(6);
                    String mes1 = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
                    SendMessageUntil.toServer(mes1);
                    break;
                case 60888 :{
                    FormsManagement.showForm(60888);
//                    GJBtn[] gjBtns = GZframe.getGZJpanel().getGjBtns();
//                    if(gjBtns.length>0){
//                        gjBtns[0].nochoose(null);
//                    }
                    break;
                }
                default: {
                    FormsManagement.showForm(this.formsid);
                    break;
                }
            }
            System.out.println("id:"+this.formsid);
            Music.addyinxiao("开关窗口.mp3");
        }
        else {
            if (this.formsid == 2) {
                ZhuJpanel.setUseGoodsType(0);
            }
            if (this.formsid == 999) {
                LotteryFromServerUntil.drop();
            }
            if (this.formsid == 11) {
                NewRefiningJpanel.isLH = false;
            }
            if (this.formsid == 3081){
                WelcomeXyJpanel.updateCX();
                return;
            }
            if (this.formsid == 14) {
                String send = Agreement.getAgreement().TransStateAgreement("2");
                SendMessageUntil.toServer(send);
            }
            else {
                if (this.formsid != 16 && this.formsid != 15) {
                    if (this.formsid == 47) {
                        ZhuJpanel.setNedangoods(null);
                    }
                }
                else {
                    FormsManagement.HideForm((this.formsid == 16) ? 15 : 16);
                }
                FormsManagement.HideForm(this.formsid);
                if (this.formsid == 1103) {
                    WorldTestsmallmapJframe worldTestsmallmapJframe = (WorldTestsmallmapJframe)FormsManagement.getframe(this.formsid);
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
                }
                Music.addyinxiao("关闭窗口.mp3");
            }
        }
    }
    
    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return (int)Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
    }
    
    public static void STRTMP() {
        if (GetTcpPath.STRTMP.equals("1")) {
            GetTcpPath.STRTMP = "2";
            run("已切换为全屏法术！");
        }
        else {
            GetTcpPath.STRTMP = "1";
            run("已切换为非全屏法术!");
        }
    }
    
    public void complainOpenWeb1() {
        DownLoadTxt.getDownLoadTxt().initMes("configure.txt");
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String url = configure.getJumpurl();
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        String stringParams = sb.toString();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(stringParams));
        }
        catch (IOException var7) {
            var7.printStackTrace();
        }
        catch (URISyntaxException var8) {
            var8.printStackTrace();
        }
    }
    
    public void complainOpenWeb() {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", ImageMixDeal.userimg.getRoleShow().getRolename());
        params.put("roleAccount", RoleData.getRoleData().getLoginResult().getUserName());
        params.put("roleQuid", GameClient.potAndIpStrings[5]);
        String url = "http://www.dongmengzhongchou.com:80801/question";
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        if (params != null) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sb.append((String)e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        String stringParams = sb.toString();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(stringParams));
        }
        catch (IOException var6) {
            var6.printStackTrace();
        }
        catch (URISyntaxException var7) {
            var7.printStackTrace();
        }
    }
    
    public static void Show() {
        if (ImageMixDeal.userimg.getRoleShow().getGang_id() != null && ImageMixDeal.userimg.getRoleShow().getGang_id().intValue() != 0) {
            if (FormsManagement.getframe(48).isVisible()) {
                FormsManagement.HideForm(48);
            }
            else {
                String senmes = Agreement.getAgreement().IntogangAgreement(ImageMixDeal.userimg.getRoleShow().getGang_id().toString());
                SendMessageUntil.toServer(senmes);
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有帮派!");
        }
    }
    
    public static void run(String type) {
        try {
            ZhuFrame.getZhuJpanel().addPrompt2(type);
        }
        catch (Exception ex) {}
    }
}
