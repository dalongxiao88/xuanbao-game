package com.tool.btn;

import org.come.bean.RoleShow;
import org.come.entity.TeamRole;
import org.lottery.frame.LotteryMainFrame;
import org.come.until.GsonUtil;
import org.come.bean.TeamBean;
import org.come.until.MessagrFlagUntil;
import com.tool.role.RoleData;
import org.come.Frame.TeamJframe;
import org.come.Frame.DreamlandTrialMainJframe;
import org.come.until.Music;
import com.tool.imagemonitor.PlayerMonitor;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import org.come.Frame.YuekaJframe;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.TeamPostMessageJpanel;
import org.come.Jpanel.TestpackJapnel;
import org.come.Jpanel.ZhuJpanel;
import org.come.Jpanel.TeamJpanel;
import org.come.Jpanel.TeamApplyJpanel;

public class TeamPanelBtn extends MoBanBtn
{
    private TeamApplyJpanel teamApplyJpanel;
    private TeamJpanel teamJpanel;
    private ZhuJpanel zhuJpanel;
    private TestpackJapnel testpackJapnel;
    private TeamPostMessageJpanel messageJpanel;
    private int index;
    
    public TeamPanelBtn(String iconpath, int type, String text, TeamJpanel teamJpanel, TeamApplyJpanel teamApplyJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.teamJpanel = teamJpanel;
        this.teamApplyJpanel = teamApplyJpanel;
    }
    
    public TeamPanelBtn(String iconpath, int type, String text, Color[] colors, Font font, TeamJpanel teamJpanel, TeamApplyJpanel teamApplyJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.teamJpanel = teamJpanel;
        this.teamApplyJpanel = teamApplyJpanel;
    }
    
    public TeamPanelBtn(String iconpath, int type, String text, Color[] colors, Font font, ZhuJpanel zhuJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.zhuJpanel = zhuJpanel;
    }
    
    public TeamPanelBtn(String iconpath, int type, String text, Color[] colors, Font font, TestpackJapnel testpackJapnel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.testpackJapnel = testpackJapnel;
    }
    
    public TeamPanelBtn(String iconpath, int type, String text, Color[] colors, Font font) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TeamPanelBtn(String iconpath, int type, String text, Color[] colors, Font font, TeamPostMessageJpanel messageJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.messageJpanel = messageJpanel;
    }
    
    public TeamPanelBtn(String iconpath, int type, int index, Color[] colors, Font font) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.index = index;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (FightingMixDeal.State == 0) {
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            if (!this.getText().equals("解散队伍") && !this.getText().equals("离开队伍")) {
                if (this.getText().equals("移交队长") && roleShow.getCaptian() == 1) {
                    TeamRole teamRole = this.teamJpanel.getXZ();
                    this.teamJpanel.upXz(-1);
                    if (teamRole == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的玩家");
                        return;
                    }
                    if (teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能选中你自己");
                        return;
                    }
                    String sendmes = Agreement.getAgreement().team5Agreement("S" + teamRole.getRoleId());
                    SendMessageUntil.toServer(sendmes);
                }
                else if (this.getText().equals("请离队伍") && roleShow.getCaptian() == 1) {
                    TeamRole teamRole = this.teamJpanel.getXZ();
                    this.teamJpanel.upXz(-1);
                    if (teamRole == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的玩家");
                        return;
                    }
                    if (teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能选中你自己");
                        return;
                    }
                    String sendmes = Agreement.getAgreement().team5Agreement("K" + teamRole.getRoleId());
                    SendMessageUntil.toServer(sendmes);
                }
                else if (this.getText().equals("聚宝阁拍卖")) {
                    FormsManagement.showForm(900);
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                }
                else if (this.getText().equals("每日签到")) {
                    SendMessageUntil.toServer(Agreement.getAgreement().qdAgreement("open"));
                }
                else if (this.getText().equals("月卡特权")) {
                    FormsManagement.showForm(901);
                    YuekaJframe.getYuekaJframe().getYuekaJpanel().changeTime();
                    YuekaJframe.getYuekaJframe().getYuekaJpanel().changeTime1();
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                }
                else if (this.getText().equals("八十一难")) {
                    FormsManagement.showForm(3079);
                }
                else if (this.getText().equals("游戏攻略")) {
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
                    }
                    catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                }

                else if (this.getText().equals("申请列表") && roleShow.getCaptian() == 1) {
                    String sendmes = Agreement.getAgreement().team6Agreement("");
                    SendMessageUntil.toServer(sendmes);
                }
                else if (this.getText().equals("自动炼化(F12)")) {
                    FormsManagement.showForm(1119);
                }
                else if (this.getText().equals("加为好友")) {
                    TeamRole teamRole = this.teamJpanel.getXZ();
                    if (teamRole == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的玩家");
                        return;
                    }
                    if (teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能选中你自己");
                        return;
                    }
                    PlayerMonitor.addFriend(teamRole.getRoleId(), teamRole.getName());
                }
                else if (this.getText().equals("仙玉寄售")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    if (!FormsManagement.getframe(114).isVisible()) {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.showForm(114);
                    }
                    else {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.HideForm(114);
                    }
                }
                else if (this.getText().equals("系统设置")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    if (!FormsManagement.getframe(50).isVisible()) {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.showForm(50);
                    }
                    else {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.HideForm(50);
                    }
                }
                else if (this.getText().equals("天梯竞技") || this.getText().equals("天梯比武")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    if (!FormsManagement.getframe(604).isVisible()) {
                        String mes = Agreement.getAgreement().pankinglistAgreement("8");
                        SendMessageUntil.toServer(mes);
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.showForm(604);
                    }
                    else {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.HideForm(604);
                    }
                }
                else if (this.getText().equals("试炼幻境")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    if (!FormsManagement.getframe(111).isVisible()) {
                        DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel().showLvlTier(Integer.valueOf(1));
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.showForm(111);
                    }
                    else {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.HideForm(111);
                    }
                }

                else if (this.getText().equals("全民竞猜")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    if (!FormsManagement.getframe(1105).isVisible()) {
                        SendMessageUntil.toServer(Agreement.getAgreement().LotteryCPAgreement("QPEN"));
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.showForm(1105);
                    }
                    else {
                        Music.addyinxiao("开关窗口.mp3");
                        FormsManagement.HideForm(1105);
                    }
                }
                else if (this.getText().equals("单人竞技")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    String oneArenaSendmes = Agreement.getAgreement().oneArenaAgreement("1");
                    SendMessageUntil.toServer(oneArenaSendmes);
                    Music.addyinxiao("开关窗口.mp3");
                }
                else if (this.getText().equals("自动炼化")) {
                    this.zhuJpanel.showIsSystemBtn(false, 0);
                    ZhuFrame.getZhuJpanel().addPrompt2("自动炼化正在研发中……，敬请期待！");
                }
                else if (this.getText().equals("允许")) {
                    this.teamApplyJpanel.teamAgree();
                }
                else if (this.getText().equals("拒绝")) {
                    this.teamApplyJpanel.teamRefruse();
                }
                else if (this.getText().equals("清空")) {
                    this.teamApplyJpanel.teamClear();
                }
                else if ("组队操作".equals(this.getText())) {
                    this.zhuJpanel.showIsTeamBtn(false, 0);
                    if (ImageMixDeal.userimg.getRoleShow().getTroop_id() != null) {
                        TeamJframe.getTeamJframe().getTeamjpanel().show(ImageMixDeal.userimg.getRoleShow(), RoleData.getRoleData().getTeamBean());
                    }
                    else {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE2);
                    }
                }
                else if ("坐骑".equals(this.getText())) {
                    String sendmes = Agreement.getAgreement().MountAgreement();
                    SendMessageUntil.toServer(sendmes);
                    ZhuFrame.getZhuJpanel().showflybtn(true, 1);
                }
                else if ("飞行".equals(this.getText())) {
                    String sendmes = Agreement.getAgreement().FlyAgreement();
                    SendMessageUntil.toServer(sendmes);
                    ZhuFrame.getZhuJpanel().showflybtn(true, 1);
                }
                else if ("组队平台".equals(this.getText())) {
                    this.zhuJpanel.showIsTeamBtn(false, 0);
                    String sendmes = Agreement.getAgreement().enlistAgreement("");
                    SendMessageUntil.toServer(sendmes);
                }
                else if ("立即发布".equals(this.getText())) {
                    TeamBean teamBean = new TeamBean();
                    teamBean.seteTask(this.messageJpanel.getChooseRestrainStr(1));
                    teamBean.seteTeam(this.messageJpanel.getChooseRestrainStr(2));
                    teamBean.seteMsg(this.messageJpanel.getSendBelTextArea().getText());
                    String sendmes = Agreement.getAgreement().enlistAgreement(GsonUtil.getGsonUtil().getgson().toJson(teamBean));
                    SendMessageUntil.toServer(sendmes);
                    FormsManagement.HideForm(19);
                }
                else if ("新手指引".equals(this.getText())) {
                    FormsManagement.showForm(68);
                }
                else if ("快捷操作(Tab)".equals(this.getText())) {
                    FormsManagement.showForm(3000);
                }
                else if ("幸运抽奖".equals(this.getText())) {
                    LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(1);
                    FormsManagement.showForm(87);
                }
                else if (!this.getText().equals("暂离队伍") && !this.getText().equals("离")) {
                    if (!this.getText().equals("回归队伍") && !this.getText().equals("归")) {
                        if (this.getText().equals("召回") || this.getText().equals("召")) {
                            String sendmes = Agreement.getAgreement().team4Agreement("C" + ((TeamRole)RoleData.getRoleData().getTeamBean().getTeams().get(this.index)).getRoleId());
                            SendMessageUntil.toServer(sendmes);
                        }
                    }
                    else {
                        String sendmes = Agreement.getAgreement().team4Agreement("R");
                        SendMessageUntil.toServer(sendmes);
                    }
                }
                else {
                    String sendmes = Agreement.getAgreement().team4Agreement("L");
                    SendMessageUntil.toServer(sendmes);
                }
            }
            else {
                String sendmes = Agreement.getAgreement().team5Agreement("D");
                SendMessageUntil.toServer(sendmes);
                FormsManagement.HideForm(13);
            }
        }
    }
}
