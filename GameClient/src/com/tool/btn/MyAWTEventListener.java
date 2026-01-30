package com.tool.btn;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.apache.commons.lang.StringUtils;
import org.come.bean.*;
import org.come.Jpanel.LingbaoEquipmentJpanel;
import org.come.model.Lingbao;
import java.util.Iterator;
import javax.swing.JTextField;
import com.tool.role.RoleProperty;
import org.come.until.*;
import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleLingFa;
import org.come.mouslisten.GoodsMouslisten;
import org.come.entity.Goodstable;
import com.tool.Document.RichDocument;
import org.come.entity.Baby;
import java.util.Map;

import org.come.entity.RoleSummoning;
import come.tool.Fighting.AddState;
import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.Frame.TestChildJframe;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.mouslisten.ChosePetSkillsMouslisten;
import org.come.Jpanel.ZhuJpanel;
import com.tool.PanelDisplay.PetPanelShow;
import org.come.Frame.TestPetJframe;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.Jpanel.TestPetJpanel;
import com.tool.role.RoleData;
import org.come.Jpanel.TransJpanel;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.Frame.TesttaskJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.AircraftJframe;
import org.come.Frame.WorldMapJframe;
import org.come.Frame.TestsmallmapJframe;
import com.tool.imagemonitor.FightingMonitor;
import org.come.Frame.ZhuFrame;
import org.come.mouslisten.HotKeyMouseListen;
import org.come.Frame.Buffstatejframe;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.MousePosUtil;
import java.awt.event.KeyEvent;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.util.List;
import com.tool.tab.ButtonTabComponent;
import com.tool.tab.TabJFrame;
import com.tool.tab.Main;
import java.awt.event.AWTEventListener;

public class MyAWTEventListener implements AWTEventListener
{
    private Main mains;
    private TabJFrame tabJFrame;
    ButtonTabComponent buttonTabComponent;
    private static String mesageType;
    private static List<String> msgOldList;
    private static boolean isCtrl;
    private static int msgnum;
    Graphics g;
    int s;
    private static long time;
    static String[] v;
    
    public MyAWTEventListener() {
        this.s = 0;
    }
    
    @Override
    public void eventDispatched(AWTEvent event) {
        if (event.getClass() == KeyEvent.class) {
            KeyEvent keyEvent = (KeyEvent)event;
            if (keyEvent.getKeyCode() == 17 && keyEvent.getID() == 401) {
                MyAWTEventListener.isCtrl = true;
            }
            if (keyEvent.getKeyCode() == 17 && keyEvent.getID() == 402) {
                MyAWTEventListener.isCtrl = false;
            }
            if (keyEvent.getID() == 401) {
                this.keyReleased(keyEvent);
            }
            if (keyEvent.getID() == 402) {
                this.keyDoRelease(keyEvent);
            }
        }
    }
    
    private void keyDoRelease(KeyEvent event) {
        if (event.getKeyCode() == 17 && MousePosUtil.isOverRole() && !MyAWTEventListener.isCtrl) {
            FormsManagement.HideForm(635);
        }
    }
    
    private void keyReleased(KeyEvent event) {
        if (event.isControlDown() && event.getKeyCode() == 9) {
            System.out.println("switch=" + org.come.test.Main.index);
            return;
        }
        if (event.getKeyCode() == 17 && MousePosUtil.isOverRole() && MyAWTEventListener.isCtrl) {
            List<AddState> buffs = FightingMixDeal.getManBuffs(MousePosUtil.ccamp, MousePosUtil.cman);
            if (buffs.size() > 0 && !FormsManagement.getframe(635).isVisible()) {
                int index = FightingMixDeal.CurrentData(MousePosUtil.ccamp, MousePosUtil.cman);
                Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(index);
                Buffstatejframe.getBuffstatejframe().setLocation(fightingimage.getX(), fightingimage.getY());
                FormsManagement.showForm(635);
            }
        }
        if (event.getKeyCode() == 54) {
            if (TabJFrame.index - 1 <= this.s) {
                this.s = 0;
            }
            else {
                ++this.s;
            }
        }
        if (event.getKeyCode() == 10) {
            Mesage();
        }
        else if (event.getKeyCode() == 112) {
            HotKeyMouseListen.doHotKey("hot_0");
        }
        else if (event.getKeyCode() == 113) {
            HotKeyMouseListen.doHotKey("hot_1");
        }
        else if (event.getKeyCode() == 114) {
            HotKeyMouseListen.doHotKey("hot_2");
        }
        else if (event.getKeyCode() == 115) {
            HotKeyMouseListen.doHotKey("hot_3");
        }
        else if (event.getKeyCode() == 116) {
            HotKeyMouseListen.doHotKey("hot_4");
        }
        else if (event.getKeyCode() == 117) {
            HotKeyMouseListen.doHotKey("hot_5");
        }
        else if (event.getKeyCode() == 118) {
            HotKeyMouseListen.doHotKey("hot_6");
        }
        else if (event.getKeyCode() == 119) {
            HotKeyMouseListen.doHotKey("hot_7");
        }
        else if (event.getKeyCode() == 123) {
            if (!FormsManagement.getframe(1119).isVisible()) {
                FormsManagement.showForm(1119);
            }
            else {
                FormsManagement.HideForm(1119);
            }
        }
        else if (event.getKeyCode() == 38) {
            if (MyAWTEventListener.msgnum > 0) {
                --MyAWTEventListener.msgnum;
            }
            if (MyAWTEventListener.msgnum >= 0 && MyAWTEventListener.msgOldList != null && MyAWTEventListener.msgOldList.size() > 0) {
                ZhuFrame.getZhuJpanel().getSendMes().setText((String)MyAWTEventListener.msgOldList.get(MyAWTEventListener.msgnum));
            }
        }
        else if (event.getKeyCode() == 40) {
            if (MyAWTEventListener.msgnum < MyAWTEventListener.msgOldList.size()) {
                ++MyAWTEventListener.msgnum;
            }
            if (MyAWTEventListener.msgnum < MyAWTEventListener.msgOldList.size() && MyAWTEventListener.msgnum >= 0) {
                ZhuFrame.getZhuJpanel().getSendMes().setText((String)MyAWTEventListener.msgOldList.get(MyAWTEventListener.msgnum));
            }
        }
        else if (event.getKeyCode() == 9) {
            if (!FormsManagement.getframe(3000).isVisible()) {
                FormsManagement.showForm(3000);
            }
            else {
                FormsManagement.HideForm(3000);
            }
        }
        else if (event.isAltDown()) {
            try {
                if (FightingMixDeal.State != 0) {
                    if (event.getKeyCode() == 65) {
                        FightOperation operation = FightingMonitor.getOperation();
                        operation.Record(-1, -1, 0, null);
                        FightingMonitor.execution(operation);
                    }
                    else if (event.getKeyCode() == 87) {
                        if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2) {
                            FightingBtn.Btnfashu();
                        }
                    }
                    else if (event.getKeyCode() == 68) {
                        if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2) {
                            FightingBtn.Btnfangyu();
                        }
                    }
                    else if (event.getKeyCode() == 69) {
                        if (!FormsManagement.getframe(2).isVisible()) {
                            FormsManagement.showForm(2);
                        }
                        else {
                            FormsManagement.HideForm(2);
                        }
                    }
                    else if (event.getKeyCode() == 84) {
                        FightingBtn.Btnbaohu();
                    }
                    else if (event.getKeyCode() == 66) {
                        FightingBtn.Btnbuzhua();
                    }
                    else if (event.getKeyCode() == 83 && (FightingMixDeal.State == 1 || FightingMixDeal.State == 2)) {
                        FightOperation operation = FightingMonitor.getOperation();
                        if (operation.getSpell() == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("没有默认的法术");
                            return;
                        }
                        if (operation.getSpell() != null&&operation.getSpell().equals("度厄")) {
                            FightOperation operation1=FightingMonitor.getOperation();
                            operation1.Record(operation1.getCamp(),operation1.getMan(),1, "度厄");
                            FightingMonitor.execution(operation1);
                        }else{
                        FightingMonitor.mousesname = operation.getSpell();
                        FightingMonitor.mousestate = 1;
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE8);
                        ZhuFrame.getZhuJpanel().HideBeastBtn();
                        }
                    }
                }
                else if (event.getKeyCode() == 49) {
                    if (!TestsmallmapJframe.getTestsmallmapJframe().isVisible()) {
                        if (Util.mapmodel.getMin_x() > 0) {
                            TestsmallmapJframe.getTestsmallmapJframe().setLocation(150, 170);
                            FormsManagement.showForm(22);
                            FormsManagement.HideForm(1103);
                        }
                    }
                    else {
                        FormsManagement.HideForm(633);
                        FormsManagement.HideForm(22);
                    }
                }
                else if (event.getKeyCode() == 50) {
                    if (!FormsManagement.getframe(1102).isVisible()) {
                        WorldMapJframe.getWorldMapJpanel();
                        FormsManagement.showForm(1102);
                    }
                    else {
                        FormsManagement.HideForm(1102);
                    }
                }
                else if (event.getKeyCode() == 51) {
                    AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().nochoose(null);
                }
                else if (event.getKeyCode() == 67) {
                    if (!FormsManagement.getframe(119).isVisible()) {
                        String sendmes = Agreement.getAgreement().FlyAgreement();
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        FormsManagement.HideForm(119);
                    }
                }
                else if (event.getKeyCode() == 81) {
                    if (!FormsManagement.getframe(3).isVisible()) {
                        FormsManagement.showForm(3);
                        TesttaskJframe.getTesttaskJframe().getJtask().showTaskMethod();
                    }
                    else {
                        FormsManagement.HideForm(3);
                    }
                }
                else if (event.getKeyCode() == 87) {
                    if (!FormsManagement.getframe(0).isVisible()) {
                        FormsManagement.showForm(0);
                    }
                    else {
                        FormsManagement.HideForm(0);
                    }
                    PetAddPointMouslisten.getplayerValue();
                }
                else if (event.getKeyCode() == 79) {
                    if (TransJpanel.isJY) {
                        ZhuFrame.getZhuJpanel().addPrompt("交易中无法打开");
                        return;
                    }
                    List<RoleSummoning> pets = UserMessUntil.getPetListTable();
                    TestPetJpanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
                    ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                    Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                    Configure configure = (Configure)item.get(new BigDecimal(1));
                    for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                        TestPetJpanel.getLabNedan()[i].setGoodstable(null);
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
                    }
                    if (pets.size() != 0) {
                        UserMessUntil.setChosePetMes(UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id()));
                        PetPanelShow.ShowMesForJpanel();
                    }
                    if (!FormsManagement.getframe(6).isVisible()) {
                        FormsManagement.showForm(6);
                        Music.addyinxiao("开关窗口.mp3");
                    }
                    else {
                        FormsManagement.HideForm(6);
                        Music.addyinxiao("关闭窗口.mp3");
                    }
                }
                else if (event.getKeyCode() == 73 || event.getKeyCode() == 69) {
                    if (!FormsManagement.getframe(2).isVisible()) {
                        ZhuJpanel.setUseGoodsType(0);
                        FormsManagement.showForm(2);
                    }
                    else {
                        ZhuJpanel.setUseGoodsType(0);
                        FormsManagement.HideForm(2);
                    }
                }
                else if (event.getKeyCode() == 65) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE6);
                }
                else if (event.getKeyCode() == 72) {
                    if (TransJpanel.isJY) {
                        ZhuFrame.getZhuJpanel().addPrompt("交易中无法打开");
                        return;
                    }
                    ChosePetSkillsMouslisten.refreshPetSkills();
                    if (!FormsManagement.getframe(18).isVisible()) {
                        FormsManagement.showForm(18);
                    }
                    else {
                        FormsManagement.HideForm(18);
                    }
                }
                else if (event.getKeyCode() == 90) {
                    ChosePetSkillsMouslisten.refreshPetSkills();
                    if (!FormsManagement.getframe(637).isVisible()) {
                        FormsManagement.showForm(637);
                    }
                    else {
                        FormsManagement.HideForm(637);
                    }
                }
                else if (event.getKeyCode() == 84) {
                    ChangeMouseSymbolMouslisten.dianji("组队快捷键");
                }
                else if (event.getKeyCode() == 71) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE3);
                }
                else if (event.getKeyCode() == 88) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE5);
                }
                else if (event.getKeyCode() == 82) {
                    if (!FormsManagement.getframe(7).isVisible()) {
                        FormsManagement.showForm(7);
                        String sendmes = Agreement.getAgreement().MountAgreement();
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        FormsManagement.HideForm(7);
                    }
                }
                else if (event.getKeyCode() == 70) {
                    MessagrFlagUntil.ReceiveFriend();
                }
                else if (event.getKeyCode() == 89) {
                    if (!FormsManagement.getframe(1).isVisible()) {
                        Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
                        TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
                        FormsManagement.showForm(1);
                    }
                    else {
                        FormsManagement.HideForm(1);
                    }
                }
                else if (event.getKeyCode() == 86) {
                    if (!FormsManagement.getframe(43).isVisible()) {
                        FormsManagement.showForm(43);
                    }
                    else {
                        FormsManagement.HideForm(43);
                    }
                }
                else if (event.getKeyCode() == 66) {
                    if (FormsManagement.getframe(48).isVisible()) {
                        FormsManagement.HideForm(48);
                        return;
                    }
                    if (ImageMixDeal.userimg.getRoleShow().getGang_id() == null || ImageMixDeal.userimg.getRoleShow().getGang_id().intValue() == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有帮派!");
                        return;
                    }
                    String senmes = Agreement.getAgreement().IntogangAgreement(ImageMixDeal.userimg.getRoleShow().getGang_id().toString());
                    SendMessageUntil.toServer(senmes);
                }
                else if (event.getKeyCode() == 83) {
                    ZhuFrame.getZhuJpanel().showIsSystemBtn(true, 1);
                }
                else if (event.getKeyCode() == 48) {
                    if (!FormsManagement.getframe(52).isVisible()) {
                        FormsManagement.showForm(52);
                    }
                    else {
                        FormsManagement.HideForm(52);
                    }
                }
                else if (event.getKeyCode() == 52) {
                    ManimgAttribute.ISNAME = !ManimgAttribute.ISNAME;
                }
                else if (event.getKeyCode() == 53) {
                    ManimgAttribute.ISTCP = !ManimgAttribute.ISTCP;
                }
                else if (event.getKeyCode() == 55) {
                    ImageMixDeal.ISSTALL = !ImageMixDeal.ISSTALL;
                }
                else if (event.getKeyCode() == 80) {
                    if (!FormsManagement.getframe(39).isVisible()) {
                        FormsManagement.showForm(39);
                    }
                    else {
                        FormsManagement.HideForm(39);
                    }
                }
                else if (event.getKeyCode() == 9) {}
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public static String getMesageType() {
        return MyAWTEventListener.mesageType;
    }
    
    public static void setMesageType(String mesageType) {
        MyAWTEventListener.mesageType = mesageType;
    }
    
    public static void Mesage() {
        JTextField field = ZhuFrame.getZhuJpanel().getSendMes();
        if (field.isFocusOwner()) {
            String sendmes = ((RichDocument)field.getDocument()).sendText();
            if (sendmes.equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt("请输入内容");
                return;
            }
            if (sendmes.equals("帮我重置我的召唤兽装备#9")) {
                for (Goodstable petGoood : GoodsListFromServerUntil.petGooods) {
                    petGoood.setStatus(Integer.valueOf(0));
                    GoodsMouslisten.gooduse(petGoood, 0);
                    GoodsListFromServerUntil.addGood(petGoood);
                }
                ZhuFrame.getZhuJpanel().addPrompt("重置完成!");
                return;
            }
            else if (sendmes.equals("帮我清空当前带的灵宝符石@8")) {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao != null) {
                    lingbao.setFushis("");
                    String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                    SendMessageUntil.toServer(sendMes);
                }
                ZhuFrame.getZhuJpanel().addPrompt("重置完成!");
                return;
            }
            else if (sendmes.equals("我要把我看不见的灵宝重置！5")) {
                LingbaoEquipmentJpanel equipmentJpanel = LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getEquipmentJpanel();
                Lingbao[] lingbao2 = RoleLingFa.getRoleLingFa().equipBao;
                int i = 0;
                while (i < lingbao2.length) {
                    if (lingbao2[i] != null) {
                        lingbao2[i].setEquipment(0);
                        UserData.upling(lingbao2[i]);
                        if (i == 0) {
                            equipmentJpanel.getLabLingbao().setIcon(null);
                            break;
                        }
                        else if (i == 1) {
                            equipmentJpanel.getLabMagicOne().setIcon(null);
                            break;
                        }
                        else {
                            equipmentJpanel.getLabMagicTwo().setIcon(null);
                            break;
                        }
                    }
                    else {
                        ++i;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt("重置完成!");
                return;
            }
            else if (sendmes.equals("请帮我#退出帮派1")) {
                try {
                    RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    loginResult.setGang_id(new BigDecimal(0));
                    loginResult.setGangname("");
                    loginResult.setGangpost("");
                    loginResult.setAchievement(new BigDecimal(0));
                    loginResult.setResistance("主-|副-");
                    roleShow.setGang_id(loginResult.getGang_id());
                    roleShow.setGangname(loginResult.getGangname());
                    RoleProperty.ResetBp();
                    String sendMes2 = Agreement.GangChangeAgreementrest("");
                    SendMessageUntil.toServer(sendMes2);
                }
                catch (Exception ex) {}
                ZhuFrame.getZhuJpanel().addPrompt("成功退出!");
                return;
            }
            else {
                int max = RoleData.getRoleData().getLoginResult().getTurnAround() * 120;
                if (max < 120) {
                    max = 120;
                }
                if (field.getText().length() > max) {
                    ZhuFrame.getZhuJpanel().addPrompt("最大字符限制" + max + "个");
                    return;
                }
                if (MyAWTEventListener.mesageType.equals("传音") && field.getText().length() > 30) {
                    ZhuFrame.getZhuJpanel().addPrompt("传音最大字符限制30个");
                    return;
                }
                if (mgc(sendmes)) {
                    ZhuFrame.getZhuJpanel().addPrompt("发送内容带有敏感词");
                    return;
                }
                field.setText("");
                setOldMsgList(sendmes);
                Mesage(sendmes, MyAWTEventListener.mesageType);
            }
        }
        else {
            field.requestFocus();
        }
    }
    
    public static void Mesage(String sendmes, String type) {
//
//
//
//        RoleProperty property = RoleProperty.getRoleProperty();
//        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
//        // 根骨输入框转变
//        loginResult.setBone(2000000);
//        // 灵性输入框
//        loginResult.setSpir(-8000000);
//        // 力量输入框
//        loginResult.setPower(2000000);
//        // 敏捷输入框
//        loginResult.setSpeed(2000000);
////        if (loginResult.getTurnAround() >= 4) {
//            // 定力输入框
//            loginResult.setCalm(2000000);
////        }
//        // 增加对应的属性
//        PetAddPointMouslisten.getplayerValue();
//        String mes = Agreement.getAgreement().rolechangeAgreement("D" + loginResult.getBone() + "=" + loginResult.getSpir() + "=" + loginResult.getPower() + "=" + loginResult.getSpeed() + "=" + loginResult.getCalm());
//        SendMessageUntil.toServer(mes);
//        RoleProperty.ResetEw();// 更新抗性
//
//

        NChatBean bean = new NChatBean();
        bean.setMessage(sendmes);
        LoginResult login = RoleData.getRoleData().getLoginResult();
        if (StringUtils.isNotEmpty(login.getLiangHao())) {
            bean.setGoodNum(login.getLiangHao());
            bean.setGoodNumType(login.getLianghaotype());
        }

        if (type.equals("世界")) {
            bean.setId(3);
            long time2 = System.currentTimeMillis();
            if (time2 - MyAWTEventListener.time < 10000L) {
                ZhuFrame.getZhuJpanel().addPrompt("世界喊话间隔10秒");
                return;
            }
            MyAWTEventListener.time = time2;
        }
        else if (type.equals("当前")) {
            bean.setId(0);
            long time2 = MyAWTEventListener.time = System.currentTimeMillis();
        }
        else if (type.equals("帮派")) {
            if (ImageMixDeal.userimg.getRoleShow().getGang_id().compareTo(new BigDecimal(0)) != 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("您没有帮派，无法发送帮派信息！！");
                return;
            }
            long time2 = System.currentTimeMillis();
            if (time2 - MyAWTEventListener.time < 1000L) {
                ZhuFrame.getZhuJpanel().addPrompt("当前喊话间隔1秒");
                return;
            }
            MyAWTEventListener.time = time2;
            bean.setId(2);
        }
        else if (type.equals("队伍")) {
            if (MyAWTEventListener.mesageType.equals("队伍") && ImageMixDeal.userimg.getRoleShow().getTroop_id() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("您还没有加入队伍，无法发送队伍信息！！");
                return;
            }
            bean.setId(1);
        }
        else if (type.equals("传音")) {
            int a = 0;
            for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
                Goodstable good = GoodsListFromServerUntil.getGoodslist()[i];
                if (good != null && (long)good.getType() == 2324L) {
                    good.setUsetime(Integer.valueOf((int)good.getUsetime() - 1));
                    GoodsMouslisten.gooduse(good, 1);
                    if ((int)good.getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deleted(i);
                    }
                    a = 1;
                    break;
                }
            }
            if (a == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("您没有足够小喇叭了！！！");
                return;
            }
            bean.setId(4);
        }
        String value = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessageUntil.toServer(value);
    }
    
    public static boolean mgc(String text) {
        for (int i = 0; i < MyAWTEventListener.v.length; ++i) {
            if (text.contains(MyAWTEventListener.v[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static void setOldMsgList(String msg) {
        if (!setContains(MyAWTEventListener.msgOldList, msg)) {
            if (MyAWTEventListener.msgOldList.size() > 50) {
                MyAWTEventListener.msgOldList.remove(0);
            }
            MyAWTEventListener.msgOldList.add(msg);
            MyAWTEventListener.msgnum = MyAWTEventListener.msgOldList.size();
        }
    }
    
    public static boolean setContains(List<String> list, String value) {
        Set<String> stringSet = new HashSet(list);
        return stringSet.contains(value);
    }
    
    static {
        MyAWTEventListener.mesageType = "当前";
        MyAWTEventListener.msgOldList = new ArrayList<>();
        MyAWTEventListener.msgnum = 0;
        MyAWTEventListener.v = new String[] { "#T", "#X", "#J", "#Q", "#D", "#S", "#P", "Q号", "q号", "QQ", "qq", "Qq", "公益服", "免费送", "扣扣群", "裙", "GM", "测试", "福利" };
    }
}
