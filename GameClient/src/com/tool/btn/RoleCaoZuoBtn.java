package com.tool.btn;

import com.tool.Document.RichDocument;
import org.come.Jpanel.*;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import org.come.until.Util;
import org.come.bean.ChatBean;
import org.come.entity.RoleSummoning;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.mouslisten.GoodsMouslisten;
import java.math.BigDecimal;

import org.skill.frame.SkillMainFrame;
import com.tool.role.SkillUtil;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.RaceChangeMainJframe;
import org.come.Frame.ApointJframe;
import org.come.bean.LoginResult;
import org.come.Frame.FactionMainJframe;
import com.tool.image.ManimgAttribute;
import org.come.entity.Friendtable;
import org.come.bean.Role_bean;
import org.come.Frame.WorldTestsmallmapJframe;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.until.MessagrFlagUntil;
import org.come.Frame.FriendMsgJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.Frame.AddFriendJframe;
import org.come.Frame.FriendChatMessageJframe;
import org.come.until.UserMessUntil;
import org.come.until.FormsManagement;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class RoleCaoZuoBtn extends MoBanBtn
{
    private int index;
    private AddFriendJpanel addFriendJpanel;
    private MyOptionalJpanel myOptionalJpanel;
    private Object BigDecimal;
    private String[] taskIds;
    private static long time;
    
    public RoleCaoZuoBtn(String iconpath, int type, String text, int index) {
        super(iconpath, type);
        this.setText(text);
        if (text.equals("加为好友") || text.equals("申请入队") || text.equals("搜索") || text.equals("发送")) {
            this.setFont(UIUtils.TEXT_FONT79);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT79);
        }
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    public RoleCaoZuoBtn(String iconpath, int type, Color[] colors, Font font, String text, int index) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    public RoleCaoZuoBtn(String iconpath, int type, Color[] colors, Font font, String text, int index, String mm) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    public RoleCaoZuoBtn(String iconpath, int type, Color[] colors, Font font, String text, int index,MyOptionalJpanel myOptionalJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.myOptionalJpanel = myOptionalJpanel;
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        this.index = index;
    }
    public RoleCaoZuoBtn(String iconpath, int type, Color[] colors, Font font, String text, int index, String mm, String xx) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    public RoleCaoZuoBtn(String iconpath, int type, Color[] colors, Font font, String text, int index, String mm, String xx, String yy) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    public RoleCaoZuoBtn(String iconpath, int type, String text, int index, Color[] colors) {
        super(iconpath, type, colors);
        this.setText(text);
        if (text.equals("搜索") || text.equals("断交") || text.equals("历史消息")) {
            this.setFont(UIUtils.TEXT_HY88);
        }
        else {
            this.setFont(UIUtils.TEXT_HY16);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    public RoleCaoZuoBtn(String iconpath, int type, int index, AddFriendJpanel addFriendJpanel) {
        super(iconpath, type);
        this.index = index;
        this.addFriendJpanel = addFriendJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("确认") && FightingMixDeal.State == 0 && this.index == 0) {
            this.sureRenMing();
        }
        else if (this.getText().equals("取消") && FightingMixDeal.State == 0 && this.index == 1) {
            FormsManagement.HideForm(37);
        }
        else if (this.getText().equals("取消") && FightingMixDeal.State == 0 && this.index == 3) {
            FormsManagement.HideForm(41);
        }
        else if (FightingMixDeal.State == 0 && this.index == 3) {
            this.sureZhuanSheng();
        }
        else if (this.getText().equals("吐出内丹") && FightingMixDeal.State == 0 && this.index == 0 && UserMessUntil.getChosePetMes() != null) {
            this.spitOutNeDan();
        }
        else if (this.getText().equals("转换经验") && FightingMixDeal.State == 0 && this.index == 0) {
            this.conversionExp();
        }
        else if (this.getText().equals("发送") && this.index == 5) {
            this.sendMessageUntil();
            if (!FriendChatMessageJpanel.closeCk) {
                FormsManagement.HideForm(56);
            }
        }
        else if (this.getText().equals("取消") && this.index == 5) {
            FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().getSetwords().setText("");
            FormsManagement.HideForm(56);
        }
        else if (this.index == 6) {
            Role_bean bean = AddFriendJframe.getAddFriendJframe().getAddFriendJpanel().getRole();
            if (bean == null) {
                return;
            }
            if (this.getText().equals("加为好友")) {
                PlayerMonitor.addFriend(bean.getRole_id(), bean.getRolename());
            }
            else if (this.getText().equals("申请入队")) {
                PlayerMonitor.teamApply(bean.getRole_id());
            }
        }
        else if (this.getText().equals("搜索") && this.index == 7) {
            String text = AddFriendJframe.getAddFriendJframe().getAddFriendJpanel().getFieldText();
            String type = AddFriendJframe.getAddFriendJframe().getAddFriendJpanel().getTj();
            String msg = null;
            if (type.equals("数字ID")) {
                try {
                    Integer.parseInt(text);
                    msg = Agreement.getAgreement().searcahChatRoleIdAgreement(text);
                
                }
                catch (Exception e2) {
                    return;
                }
            }else if (type.equals("昵称")) {
                msg = Agreement.getAgreement().searcahChatRoleNameAgreement(text);
            }
            if (msg != null) {
                AddFriendJframe.getAddFriendJframe().getAddFriendJpanel().CF(null);
                SendMessageUntil.toServer(msg);
            }
        }
        else if (this.index == 8) {
            Friendtable friend = FriendMsgJframe.getFriendMsgJframe().getMsgJpanel().getFriend();
            PlayerMonitor.deleteFriden(friend.getRolename());
        }
        else if (this.index == 9) {
            Friendtable friend = FriendMsgJframe.getFriendMsgJframe().getMsgJpanel().getFriend();
            FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(friend.getRolename()));
            FormsManagement.showForm(56);
        }
        else if (this.index == 10) {
            Friendtable friend = FriendMsgJframe.getFriendMsgJframe().getMsgJpanel().getFriend();
            if (this.getText().equals("发送消息")) {
                FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(friend.getRolename()));
                FormsManagement.showForm(56);
                return;
            }
            if (this.getText().equals("申请入队")) {
                PlayerMonitor.teamApply(friend.getRole_id());
                return;
            }
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(friend.getRolename());
            if (attribute == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("玩家离你太远了");
                return;
            }
            if (friend.getRolename().equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                ZhuFrame.getZhuJpanel().addPrompt2("不能对自己操作");
                return;
            }
            if (this.getText().equals("交易")) {
                PlayerMonitor.transApply(friend.getRolename());
            }
        }
        else if (this.index == 11 || this.index == 12) {
            this.addFriendJpanel.labMenuChange(this.index);
        }
        else if (this.getText().equals("确认") && FightingMixDeal.State == 0 && this.index == 40) {
            DonationsJpanel.giveGangMoney();
        }
        else if (this.getText().equals("取消") && FightingMixDeal.State == 0 && this.index == 41) {
            DonationsJpanel.getGiveSumMoney().setText("");
            FormsManagement.HideForm(112);
        }
        else if (this.getText().equals("确认") && FightingMixDeal.State == 0 && this.index == 44) {
            QZCQJpanel.CUNMoney();
        }
        else if (this.getText().equals("取消") && FightingMixDeal.State == 0 && this.index == 45) {
            QZCQJpanel.getCunqianMoney().setText("");
            FormsManagement.HideForm(911);
        }
        else if (this.getText().equals("确认") && FightingMixDeal.State == 0 && this.index == 46) {
            QZQQJpanel.QuMoney();
        }
        else if (this.getText().equals("取消") && FightingMixDeal.State == 0 && this.index == 47) {
            QZQQJpanel.getQuqianMoney().setText("");
            FormsManagement.HideForm(912);
        }
        else if (FightingMixDeal.State == 0 && this.index == 600) {
            FindDropJpanel.findEnsure();
        }
        else if (FightingMixDeal.State == 0 && this.index == 601) {
            FindDropJpanel.getFindName().setText("");
            FormsManagement.HideForm(600);
        }
        else if (FightingMixDeal.State == 0 && this.index == 1101) {
            ChaojifeiListJpanel.findEnsure();
        }
        else if (FightingMixDeal.State == 0 && this.index == 1102) {
            ChaojifeiListJpanel.iWantToFly();
        }
        else if (this.getText().equals("搜索") && FightingMixDeal.State == 0 && this.index == 2101) {
            GMshopJpanel.findEnsure();
        }
        else if (this.getText().equals("发送") && FightingMixDeal.State == 0 && this.index == 2102) {
            GMshopJpanel.iWantToFly();
        }
        else if (this.index != 3345 && this.index != 3346) {
            if (this.getText().equals("    ")) {
                WorldMapJpanel.showIsTeamBtnSx(false, 0);
                WorldMapJpanel.showduyu(false, 0);
                WorldMapJpanel.showdayanta(false, 0);
                WorldMapJpanel.showfengcao(false, 0);
                WorldMapJpanel.showlongku(false, 0);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
                WorldTestsmallmapJframe worldTestsmallmapJframe = (WorldTestsmallmapJframe)FormsManagement.getframe(1103);
                if (worldTestsmallmapJframe != null) {
                    WorldTestsmallmapJpanel worldTestsmallmapJpanel = WorldTestsmallmapJframe.getWorldTestsmallmapJpanel();
                    if (worldTestsmallmapJpanel != null) {
                        worldTestsmallmapJpanel.clearTps();
                    }
                }
            }
            else if (this.index == 30031) {
                ExpAddMapJpanel.iWantToFlyGo(this.index);
            }
            else if (this.index == 30032) {
                ExpAddMapJpanel.iWantToFlyGo(this.index);
            }
            else if (this.index == 30033) {
                ExpAddMapJpanel.iWantToFlyGo(this.index);
            }
            else if (this.index == 30034) {
                ExpAddMapJpanel.iWantToFlyGo(this.index);
            }
            else if (this.index == 1105 && !this.getText().equals("重选") && !this.getText().equals("购买") && !this.getText().equals("刷新")) {
                LotteryCPJpanel.ChoiceNumber(this.getText());
            }
            else if (this.index == 1105 && this.getText().equals("重选")) {
                LotteryCPJpanel.emptyNumber();
            }
            else if (this.index == 1105 && this.getText().equals("购买")) {
                LotteryCPJpanel.purchaseNumber();
            }
            else if (this.index == 1105 && this.getText().equals("刷新")) {
                LotteryCPJpanel.refresh();
            }
            else if (this.getText().equals("海底迷宫1")) {
                WorldMapJpanel.showIsTeamBtnSx(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("海底迷宫2")) {
                WorldMapJpanel.showIsTeamBtnSx(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("海底迷宫3")) {
                WorldMapJpanel.showIsTeamBtnSx(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("海底迷宫4")) {
                WorldMapJpanel.showIsTeamBtnSx(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("地狱迷宫1")) {
                WorldMapJpanel.showduyu(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("地狱迷宫2")) {
                WorldMapJpanel.showduyu(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("地狱迷宫3")) {
                WorldMapJpanel.showduyu(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("地狱迷宫4")) {
                WorldMapJpanel.showduyu(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔一层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔二层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔三层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔四层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔五层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔六层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("大雁塔七层")) {
                WorldMapJpanel.showdayanta(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢一层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢二层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢三层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢四层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢五层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢六层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("凤巢七层")) {
                WorldMapJpanel.showfengcao(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟一层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟二层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟三层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟四层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟五层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟六层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (this.getText().equals("龙窟七层")) {
                WorldMapJpanel.showlongku(false, 1);
                WorldMapJpanel.iWantToFlyGo(this.index);
                FormsManagement.HideForm(22);
            }
            else if (index == 3008 && this.getText().equals("  收取")) {
                FormsManagement.HideForm(3007);
            }else if (index == 3009 && this.getText().equals("  分享")) {
                if (WeaponGodJpanel.goods != null) {
                    try {
                        JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                        String[] v = WeaponGodJpanel.goods.getValue().split("\\|");
                        String gemMsg = "";//世界显示宝石
                        for (int i = 0; i < v.length; i++) {
                            if(v[i].startsWith(BaptizeBtn.Extras[4])){//"宝石属性"
                                String[] vs = v[i].split("&");
                                for (int j = 1; j < vs.length; j++) {
                                    Goodstable good = GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[j]));
                                    gemMsg = gemMsg+good.getGoodsname()+","+good.getSkin()+","+good.getValue()+"&";
                                }
                            }
                        }
                        ((RichDocument) SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 2,WeaponGodJpanel.goods.getRgid(), "[" + WeaponGodJpanel.goods.getGoodsname() + "]", "G", null);
                        String sendmes = ((RichDocument) SendMes.getDocument()).sendText();
                        System.out.println(sendmes);
                        MyAWTEventListener.Mesage("#Y鸡驴大神附体，我就是最靓的仔#35"+sendmes, "世界");
                        JTextField field = ZhuFrame.getZhuJpanel().getSendMes();
                        field.setText("");
                    } catch (BadLocationException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }else if (index >= 3011 && index <= 3015) {
                /**
                 * 自选礼包
                 */
                String goods = "";
                for(int i=0;i<myOptionalJpanel.godList.size();i++){
                    goods+=myOptionalJpanel.godList.get(i)+"|";
                }
                String sendmes = Agreement.getAgreement().userAgreement("刷你麻痹自选="+goods+"="+(index-3011));
                SendMessageUntil.toServer(sendmes);
                FormsManagement.HideForm(3015);
                MyOptionalJpanel.zhjgood.goodxh(1);
            }
        }
    }
    public void sureRenMing() {
        try {
            FactionCardJpanel factionCardJpanel = FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel();
            factionCardJpanel.getGangResultBean().getRoleTables().get(ApointJpanel.index).setGangpost((String)ApointJframe.getApointJframe().getapointJpanel().getListModel().get(ApointJframe.getApointJframe().getapointJpanel().getListposition().getSelectedIndex()));
            factionCardJpanel.getFactionMemberJpanel().showMenuMessage(factionCardJpanel.getGangResultBean());
            String sendMes = Agreement.GangAppointAgreement(((LoginResult)factionCardJpanel.getGangResultBean().getRoleTables().get(ApointJpanel.index)).getRole_id() + "|" + (String)ApointJframe.getApointJframe().getapointJpanel().getListposition().getSelectedValue());
            SendMessageUntil.toServer(sendMes);
        }
        catch (Exception ex) {}
        FormsManagement.HideForm(37);
    }
    
    public void sureZhuanSheng() {
        RaceChangeMainJpanel raceChangeMainJpanel = RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel();
        int zhuantype = raceChangeMainJpanel.getLeixing();
        if (raceChangeMainJpanel.getSpecies_id() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("选中要转换的角色");
            return;
        }
        int i = 0;
        while (i < GoodsListFromServerUntil.getChoseGoodsList().length) {
            if (GoodsListFromServerUntil.getChoseGoodsList()[i] != null) {
                if (zhuantype == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("转换种族前必须要卸下所有装备！");
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("转生前必须要卸下所有装备！");
                }
                return;
            }
            else {
                ++i;
            }
        }
        int fly_id = 0;
        fly_id = ImageMixDeal.userimg.getRoleShow().getFly_id();
        if (fly_id != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先下飞行器！");
            return;
        }
        String seName = SkillUtil.getSepciesN(raceChangeMainJpanel.getSpecies_id());
        if (seName == null) {
            return;
        }
        String[] vs = SkillUtil.getSepcieswas(seName);
        if (vs != null) {
            SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getCopyOfSkillTYCPanel().Roelder(seName, vs);
        }
        String sendmes = Agreement.getAgreement().RacialTransformationAgreement(zhuantype + "|" + raceChangeMainJpanel.getSpecies_id());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void spitOutNeDan() {
        if (UserMessUntil.getChosePetMes().getPetlock() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt("召唤兽" + UserMessUntil.getChosePetMes().getSummoningname() + "已被加锁，不可吐出内丹！！");
            return;
        }
        int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
        if (packNumber <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt("背包已满！！！");
            return;
        }
        String[] strings = UserMessUntil.getChosePetMes().getInnerGoods().split("\\|");
        if (strings.length > 0) {
            String values = "";
            for (int i = 0; i < strings.length; ++i) {
                if (ZhuJpanel.getNedangoods() != null && ZhuJpanel.getNedangoods().getRgid().compareTo(new BigDecimal(strings[i])) == 0) {
                    ZhuJpanel.getNedangoods().setStatus(Integer.valueOf(0));
                    GoodsListFromServerUntil.fushis.remove(ZhuJpanel.getNedangoods().getRgid());
                    GoodsListFromServerUntil.newgood(ZhuJpanel.getNedangoods());
                    GoodsMouslisten.gooduse(ZhuJpanel.getNedangoods(), 0);
                    FormsManagement.HideForm(47);
                    ZhuJpanel.setNedangoods(null);
                }
                else {
                    if (!values.equals("")) {
                        values += "|";
                    }
                    values += strings[i];
                }
            }
            UserMessUntil.getChosePetMes().setInnerGoods(values);
            ChangeMouseSymbolMouslisten.refreshNedan(UserMessUntil.getChosePetMes());
            SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
        }
    }
    
    public void conversionExp() {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet != null) {
            if (ZhuJpanel.getNedangoods().getRgid() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中的内丹");
            }
            this.BigDecimal = new BigDecimal("5000000");
            String sendmes = Agreement.getAgreement().userpetAgreement("ND|" + pet.getSid() + "|" + ZhuJpanel.getNedangoods().getRgid());
            SendMessageUntil.toServer(sendmes);
        }
    }
    
    public void sendMessageUntil() {
        try {
            String text = FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().getSetwords().getText();
            if (text.equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("发送消息不能为空");
                return;
            }
            long time2 = System.currentTimeMillis();
            if (time2 - RoleCaoZuoBtn.time < 2000L) {
                ZhuFrame.getZhuJpanel().addPrompt("聊天间隔2秒！");
                return;
            }
            RoleCaoZuoBtn.time = time2;
            FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().getSetwords().setText("");
            ChatBean chatBean = new ChatBean();
            chatBean.setRolename(ImageMixDeal.userimg.getRoleShow().getRolename());
            if (FriendChatMessageJpanel.getHyName() != null && !FriendChatMessageJpanel.getHyName().equals("")) {
                chatBean.setFriendName(FriendChatMessageJpanel.getHyName());
            }
            else {
                chatBean.setFriendName(UserMessUntil.getChatFriendName());
            }
            chatBean.setMessage(text);
            chatBean.setTime(Util.getTime());
            String mes = Agreement.getAgreement().friendchatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
            SendMessageUntil.toServer(mes);
            MessagrFlagUntil.ReceiveMessage(chatBean, chatBean.getFriendName());
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
