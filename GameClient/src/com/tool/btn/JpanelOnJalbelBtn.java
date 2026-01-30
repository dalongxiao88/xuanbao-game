package com.tool.btn;

import org.apache.commons.lang.StringUtils;
import org.come.Jpanel.GoodsMsgJpanel;
import org.come.Frame.RoleResistanceJframe;
import java.lang.reflect.Field;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import org.come.Frame.TradeJframe;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.Frame.BoothBoxJframe;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import org.come.bean.LoginResult;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.AnalysisString;
import org.come.until.UserData;
import org.come.until.GoodsListFromServerUntil;
import org.come.bean.RoleAttribute;
import org.come.Frame.ZhuFrame;
import org.wing.panel.WingMainFrame;
import org.come.Frame.SeventyTwoChangesJframe;
import org.come.until.UserStallUntil;
import org.come.until.Util;
import org.skill.frame.SkillMainFrame;
import come.tool.Fighting.FightingMixDeal;
import com.tool.image.ImageMixDeal;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.role.RoleData;
import com.tool.role.RoleProperty;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.come.Jpanel.RoleToggleJpanel;
import org.come.Jpanel.TesttaskJapnel;
import org.come.Jpanel.TeststateJpanel;

public class JpanelOnJalbelBtn extends MoBanBtn
{
    private int BtnId;
    private TeststateJpanel teststateJpanel;
    private TesttaskJapnel testtaskJapnel;
    private static RoleToggleJpanel roleToggleJpanel;
    
    public JpanelOnJalbelBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, TeststateJpanel teststateJpanel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.teststateJpanel = teststateJpanel;
        this.setText(labelName);
        if (BtnId == 0) {
            this.setFont(UIUtils.TEXT_FONT);
        }
        else {
            this.setFont(UIUtils.TEXT_HY88);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelOnJalbelBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, TeststateJpanel teststateJpanel, String mm) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.teststateJpanel = teststateJpanel;
        this.setText(labelName);
        if (BtnId == 0) {
            this.setFont(UIUtils.TEXT_FONT);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT_17);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelOnJalbelBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, TesttaskJapnel testtaskJapnel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.testtaskJapnel = testtaskJapnel;
        this.setText(labelName);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public JpanelOnJalbelBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, RoleToggleJpanel roleToggleJpanel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        JpanelOnJalbelBtn.roleToggleJpanel = roleToggleJpanel;
        this.setText(labelName);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.BtnId) {
            case 0: {
                // 点击确认加点
                // 发送最新点数给服务器
                RoleProperty property = RoleProperty.getRoleProperty();
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                // 根骨输入框转变
                loginResult.setBone(Integer.valueOf(this.teststateJpanel.getLabrootbone().getText()) - (int) property.getvalue("根骨"));
                // 灵性输入框
                loginResult.setSpir(Integer.valueOf(this.teststateJpanel.getLabintelligence().getText()) - (int) property.getvalue("灵性"));
                // 力量输入框
                loginResult.setPower(Integer.valueOf(this.teststateJpanel.getLabpower().getText()) - (int) property.getvalue("力量"));
                // 敏捷输入框
                loginResult.setSpeed(Integer.valueOf(this.teststateJpanel.getLabspeed().getText()) - (int) property.getvalue("敏捷"));
                if (loginResult.getTurnAround() >= 4) {
                    // 定力输入框
                    loginResult.setCalm(Integer.valueOf(this.teststateJpanel.getLabability().getText()) - (int) property.getvalue("定力"));
                }
                // 增加对应的属性
                PetAddPointMouslisten.getplayerValue();
                String mes = Agreement.getAgreement().rolechangeAgreement("D" + loginResult.getBone() + "=" + loginResult.getSpir() + "=" + loginResult.getPower() + "=" + loginResult.getSpeed() + "=" + loginResult.getCalm());
                SendMessageUntil.toServer(mes);
                RoleProperty.ResetEw();// 更新抗性
                break;
            }
            case 1: { // 灵宝
                FormsManagement.showForm(43);
                break;
            }
            case 2: {// 角色抗性
                try {
                    if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                        testReflect(RoleProperty.getRoleProperty().getQuality());
                    }
                    else {// 获取战斗内的抗性
                        SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(RoleData.getRoleData().getLoginResult().getRolename()));
                    }
                    FormsManagement.showForm(8);
                    break;
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    break;
                }
            }
            case 4: {// 技能查看
                SkillMainFrame.getSkillMainFrame().getSkillMainPanel().changeBtnPanel(0);
                FormsManagement.showForm(82);
                break;
            }
            case 5: {
                /** 判断是否解锁 */
                if (Util.isCanBuyOrno1()) {
                    return;
                }
                UserStallUntil.startStall();
                break;
            }
            case 6: {
                if (this.getText().equals("修改密码")) {
                    FormsManagement.showForm(21);
                    break;
                }
                else {
                    FormsManagement.showForm(32);
                    break;
                }
            }
            case 7: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                if (!FormsManagement.getframe(61).isVisible()) {
                    FormsManagement.showForm(61);
                    break;
                }
                else {
                    break;
                }
            }
            case 8: {
                SeventyTwoChangesJframe.getSeventyTwoChangesJframe().getSeventyTwoChangesJpanel().changeMenuBtnSeventyTwoChanges(SeventyTwoChangesJframe.getSeventyTwoChangesJframe().getSeventyTwoChangesJpanel().getChooseMoneyType());
                FormsManagement.showForm(89);
                break;
            }
            case 9: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                WingMainFrame.getWingMainFrame().getWingMainPanel().changLeftType(-1);
                FormsManagement.showForm(86);
                break;
            }
            case 10: {
                this.testtaskJapnel.removeTask();
                break;
            }
            case 112: {
                if (this.testtaskJapnel.getCaozuo() == 1) {
                    ZhuFrame.getZhuJpanel().getTaskGuideView().setVisible(true);
                    this.testtaskJapnel.setCaozuo(0);
                    break;
                }
                else if (this.testtaskJapnel.getCaozuo() == 0) {
                    ZhuFrame.getZhuJpanel().getTaskGuideView().setVisible(false);
                    this.testtaskJapnel.setCaozuo(1);
                    break;
                }
                else {
                    break;
                }
            }
            case 11: {
                RoleAttribute roleAttribute = RoleData.getRoleData().getRoleAttribute();
                if (roleAttribute == null) {
                    roleAttribute = new RoleAttribute();
                }
                LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
                roleAttribute.setROLE_ID(Integer.valueOf(Integer.parseInt(loginResult2.getRole_id().toString())));
                String name1 = JpanelOnJalbelBtn.roleToggleJpanel.getLabrolename1().getText();
                roleAttribute.setATTRIBUTENAMEONE(name1);
                roleAttribute.setLABPOINTNUMBERONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber1().getText()));
                roleAttribute.setBONEONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone1().getText()));
                roleAttribute.setSPIRONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence1().getText()));
                roleAttribute.setPOWERONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabpower1().getText()));
                roleAttribute.setSPEEDONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed1().getText()));
                if (loginResult2.getTurnAround() >= 4) {
                    roleAttribute.setCALMONE(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabability1().getText()));
                }
                else {
                    roleAttribute.setCALMONE(Integer.valueOf(0));
                }
                roleAttribute.setATTRIBUTENAMETWO(JpanelOnJalbelBtn.roleToggleJpanel.getLabrolename2().getText());
                roleAttribute.setLABPOINTNUMBERTWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber2().getText()));
                roleAttribute.setBONETWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone2().getText()));
                roleAttribute.setSPIRTWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence2().getText()));
                roleAttribute.setPOWERTWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabpower2().getText()));
                roleAttribute.setSPEEDTWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed2().getText()));
                if (loginResult2.getTurnAround() >= 4) {
                    roleAttribute.setCALMTWO(Integer.valueOf(JpanelOnJalbelBtn.roleToggleJpanel.getLabability2().getText()));
                }
                else {
                    roleAttribute.setCALMTWO(Integer.valueOf(0));
                }
                String mes2 = Agreement.getAgreement().rolechangeAgreement("S" + roleAttribute.getBONEONE() + "=" + roleAttribute.getSPIRONE() + "=" + roleAttribute.getPOWERONE() + "=" + roleAttribute.getSPEEDONE() + "=" + roleAttribute.getCALMONE() + "=" + roleAttribute.getATTRIBUTENAMEONE() + ",S" + roleAttribute.getBONETWO() + "=" + roleAttribute.getSPIRTWO() + "=" + roleAttribute.getPOWERTWO() + "=" + roleAttribute.getSPEEDTWO() + "=" + roleAttribute.getCALMTWO() + "=" + roleAttribute.getATTRIBUTENAMETWO());
                SendMessageUntil.toServer(mes2);
                RoleData.getRoleData().setRoleAttribute(roleAttribute);
                break;
            }
            case 12: {
                Goodstable[] Goodstables1 = GoodsListFromServerUntil.getChoseGoodsList();
                for (int i = 0; i < Goodstables1.length; ++i) {
                    if (Goodstables1[i] != null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请脱下装备");
                        return;
                    }
                }
                RoleAttribute roleAttributeqh1 = RoleData.getRoleData().getRoleAttribute();
                if (roleAttributeqh1 == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先确认加点一个属性！");
                    return;
                }
                if (UserData.uptael(200000L)) {
                    LoginResult loginResultqh1 = RoleData.getRoleData().getLoginResult();
                    loginResultqh1.setBone(roleAttributeqh1.getBONEONE());
                    loginResultqh1.setSpir(roleAttributeqh1.getSPIRONE());
                    loginResultqh1.setPower(roleAttributeqh1.getPOWERONE());
                    loginResultqh1.setSpeed(roleAttributeqh1.getSPEEDONE());
                    if (loginResultqh1.getTurnAround() >= 4) {
                        loginResultqh1.setCalm(roleAttributeqh1.getCALMONE());
                    }
                    PetAddPointMouslisten.getplayerValue();
                    String mesqh1 = Agreement.getAgreement().rolechangeAgreement("D" + loginResultqh1.getBone() + "=" + loginResultqh1.getSpir() + "=" + loginResultqh1.getPower() + "=" + loginResultqh1.getSpeed() + "=" + loginResultqh1.getCalm() + "=" + loginResultqh1.getCurrentattribute());
                    SendMessageUntil.toServer(mesqh1);
                    loginResultqh1.setCurrentattribute(Integer.valueOf(1));
                    JpanelOnJalbelBtn.roleToggleJpanel.getState1().setForeground(Color.green);
                    JpanelOnJalbelBtn.roleToggleJpanel.getState1().setText("当前启用中");
                    JpanelOnJalbelBtn.roleToggleJpanel.getState2().setForeground(Color.white);
                    JpanelOnJalbelBtn.roleToggleJpanel.getState2().setText("未启用");
                    ZhuFrame.getZhuJpanel().addPrompt2("属性切换成功，当前使用【#G属性一#Y】！");
                    break;
                }
                else {
                    break;
                }
            }
            case 13: {
                Goodstable[] Goodstables2 = GoodsListFromServerUntil.getChoseGoodsList();
                for (int j = 0; j < Goodstables2.length; ++j) {
                    if (Goodstables2[j] != null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请脱下装备");
                        return;
                    }
                }
                RoleAttribute roleAttributeqh2 = RoleData.getRoleData().getRoleAttribute();
                if (roleAttributeqh2 == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先确认加点一个属性！");
                    return;
                }
                if (UserData.uptael(200000L)) {
                    LoginResult loginResultqh2 = RoleData.getRoleData().getLoginResult();
                    loginResultqh2.setBone(roleAttributeqh2.getBONETWO());
                    loginResultqh2.setSpir(roleAttributeqh2.getSPIRTWO());
                    loginResultqh2.setPower(roleAttributeqh2.getPOWERTWO());
                    loginResultqh2.setSpeed(roleAttributeqh2.getSPEEDTWO());
                    if (loginResultqh2.getTurnAround() >= 4) {
                        loginResultqh2.setCalm(roleAttributeqh2.getCALMTWO());
                    }
                    PetAddPointMouslisten.getplayerValue();
                    String mesqh2 = Agreement.getAgreement().rolechangeAgreement("D" + loginResultqh2.getBone() + "=" + loginResultqh2.getSpir() + "=" + loginResultqh2.getPower() + "=" + loginResultqh2.getSpeed() + "=" + loginResultqh2.getCalm() + "=" + loginResultqh2.getCurrentattribute());
                    SendMessageUntil.toServer(mesqh2);
                    loginResultqh2.setCurrentattribute(Integer.valueOf(2));
                    JpanelOnJalbelBtn.roleToggleJpanel.getState1().setForeground(Color.white);
                    JpanelOnJalbelBtn.roleToggleJpanel.getState1().setText("未启用");
                    JpanelOnJalbelBtn.roleToggleJpanel.getState2().setForeground(Color.green);
                    JpanelOnJalbelBtn.roleToggleJpanel.getState2().setText("当前启用中");
                    ZhuFrame.getZhuJpanel().addPrompt2("属性切换成功，当前使用【#G属性二#Y】！");
                    break;
                }
                else {
                    break;
                }
            }
            case 14: {
                if (UserData.uptael(100000L)) {
                    RoleAttribute roleAttributecz1 = RoleData.getRoleData().getRoleAttribute();
                    LoginResult loginResultcz1 = RoleData.getRoleData().getLoginResult();
                    int lvltrue1 = AnalysisString.lvltrue((int)loginResultcz1.getGrade());
                    JpanelOnJalbelBtn.roleToggleJpanel.changeSoaringPanel(lvltrue1);
                    RolePanelShow.changeGrade((int)loginResultcz1.getGrade());
                    int lvl1 = AnalysisString.lvlint((int)loginResultcz1.getGrade());
                    if (roleAttributecz1 == null) {
                        roleAttributecz1 = new RoleAttribute();
                    }
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone1().setText(lvl1 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence1().setText(lvl1 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabpower1().setText(lvl1 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed1().setText(lvl1 + "");
                    if (loginResultcz1.getTurnAround() >= 4) {
                        JpanelOnJalbelBtn.roleToggleJpanel.getLabability1().setText(lvl1 + "");
                    }
                    roleAttributecz1.setBONEONE(Integer.valueOf(lvl1));
                    roleAttributecz1.setSPIRONE(Integer.valueOf(lvl1));
                    roleAttributecz1.setPOWERONE(Integer.valueOf(lvl1));
                    roleAttributecz1.setSPEEDONE(Integer.valueOf(lvl1));
                    if (loginResultcz1.getTurnAround() >= 4) {
                        roleAttributecz1.setCALMONE(Integer.valueOf(lvl1));
                    }
                    else {
                        roleAttributecz1.setCALMONE(Integer.valueOf(0));
                    }
                    JLabel labpointnumber1 = JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber1();
                    RoleToggleJpanel roleToggleJpanel = JpanelOnJalbelBtn.roleToggleJpanel;
                    labpointnumber1.setText(RoleToggleJpanel.getCanpoint1(roleAttributecz1).toString());
                    String mescz1 = Agreement.getAgreement().rolechangeAgreement("S" + roleAttributecz1.getBONEONE() + "=" + roleAttributecz1.getSPIRONE() + "=" + roleAttributecz1.getPOWERONE() + "=" + roleAttributecz1.getSPEEDONE() + "=" + roleAttributecz1.getCALMONE() + "=" + roleAttributecz1.getATTRIBUTENAMEONE() + ",S" + roleAttributecz1.getBONETWO() + "=" + roleAttributecz1.getSPIRTWO() + "=" + roleAttributecz1.getPOWERTWO() + "=" + roleAttributecz1.getSPEEDTWO() + "=" + roleAttributecz1.getCALMTWO() + "=" + roleAttributecz1.getATTRIBUTENAMETWO());
                    SendMessageUntil.toServer(mescz1);
                    ZhuFrame.getZhuJpanel().addPrompt2("【#G属性一#Y】重置成功！");
                    break;
                }
                else {
                    break;
                }
            }
            case 15: {
                if (UserData.uptael(100000L)) {
                    LoginResult loginResultcz2 = RoleData.getRoleData().getLoginResult();
                    RoleAttribute roleAttributecz2 = RoleData.getRoleData().getRoleAttribute();
                    int lvltrue2 = AnalysisString.lvltrue((int)loginResultcz2.getGrade());
                    JpanelOnJalbelBtn.roleToggleJpanel.changeSoaringPanel(lvltrue2);
                    RolePanelShow.changeGrade((int)loginResultcz2.getGrade());
                    int lvl2 = AnalysisString.lvlint((int)loginResultcz2.getGrade());
                    if (roleAttributecz2 == null) {
                        roleAttributecz2 = new RoleAttribute();
                    }
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone2().setText(lvl2 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence2().setText(lvl2 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabpower2().setText(lvl2 + "");
                    JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed2().setText(lvl2 + "");
                    if (loginResultcz2.getTurnAround() >= 4) {
                        JpanelOnJalbelBtn.roleToggleJpanel.getLabability2().setText(lvl2 + "");
                    }
                    roleAttributecz2.setBONETWO(Integer.valueOf(lvl2));
                    roleAttributecz2.setSPIRTWO(Integer.valueOf(lvl2));
                    roleAttributecz2.setPOWERTWO(Integer.valueOf(lvl2));
                    roleAttributecz2.setSPEEDTWO(Integer.valueOf(lvl2));
                    if (loginResultcz2.getTurnAround() >= 4) {
                        roleAttributecz2.setCALMTWO(Integer.valueOf(lvl2));
                    }
                    else {
                        roleAttributecz2.setCALMTWO(Integer.valueOf(0));
                    }
                    JLabel labpointnumber2 = JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber2();
                    RoleToggleJpanel roleToggleJpanel2 = JpanelOnJalbelBtn.roleToggleJpanel;
                    labpointnumber2.setText(RoleToggleJpanel.getCanpoint2(roleAttributecz2).toString());
                    String mescz2 = Agreement.getAgreement().rolechangeAgreement("S" + roleAttributecz2.getBONEONE() + "=" + roleAttributecz2.getSPIRONE() + "=" + roleAttributecz2.getPOWERONE() + "=" + roleAttributecz2.getSPEEDONE() + "=" + roleAttributecz2.getCALMONE() + "=" + roleAttributecz2.getATTRIBUTENAMEONE() + ",S" + roleAttributecz2.getBONETWO() + "=" + roleAttributecz2.getSPIRTWO() + "=" + roleAttributecz2.getPOWERTWO() + "=" + roleAttributecz2.getSPEEDTWO() + "=" + roleAttributecz2.getCALMTWO() + "=" + roleAttributecz2.getATTRIBUTENAMETWO());
                    SendMessageUntil.toServer(mescz2);
                    ZhuFrame.getZhuJpanel().addPrompt2("【#G属性一#Y】重置成功！");
                    break;
                }
                else {
                    break;
                }
            }
        }
    }
    
    public static void stall() {
        if (BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getStall().getId() == 0) {
            try {
                if (MyIsif.getStyle().equals("水墨")) {
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setBtn(1);
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setBtn(1);
                }
                else {
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setIcons(CutButtonImage.cuts("inkImg/hongmu/aaa8.png"));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setBtn(1);
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setIcons(CutButtonImage.cuts("inkImg/hongmu/aaa8.png"));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setBtn(1);
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnStall().setText("摆摊");
        }
        else if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            try {
                if (MyIsif.getStyle().equals("水墨")) {
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setBtn(-1);
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setBtn(-1);
                }
                else {
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setIcon(CutButtonImage.getImage("inkImg/hongmu/6061.png", -1, -1));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnTheshelves().setBtn(-1);
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setIcon(CutButtonImage.getImage("inkImg/hongmu/6061.png", -1, -1));
                    BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnshelves().setBtn(-1);
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().getBtnStall().setText("收摊");
        }
        TradeJframe.getTradejframe().getTradejpanel().getModelname().removeAllElements();
        if (UserMessUntil.getPetListTable() != null) {
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                TradeJframe.getTradejframe().getTradejpanel().getModelname().add(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
        }
        BoothBoxJframe.getBoothboxjframe().setLocation(20, 70);
        FormsManagement.showForm(15);
        FormsManagement.showForm(16);
        FormsManagement.HideForm(0);
    }
    
    public static void testReflect(Object model) throws Exception {
        clearShuXingView();
        int a = 0;
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (Double.parseDouble(field.get(model).toString()) != 0.0) {
                String sx = null;
                sx = getQuaralyPersonalName(field.getName());
                if (sx != null && sx.length() > 2) {
                    mianBanDevide(sx.substring(sx.length() - 1, sx.length()), sx.substring(0, sx.length() - 2), field.get(model).toString(), a);
                    ++a;
                }
            }
        }
        changViewSize();
    }
    
    public static void mianBanDevide(String number, String shuxingName, String shuxingvalue, int a) {
        int valuseFroPanel = (int)Integer.valueOf(number);
        shuXingAdd(shuxingName, shuxingvalue, valuseFroPanel, a);
    }
    
    public static void shuXingAdd(String shuxingName, String shuxingvalue, int number, int a) {
        if (a % 2 == 0) {
            RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[number - 1].getDlm().addElement(shuxingName + ":" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(shuxingvalue)) }) + GoodsMsgJpanel.tianjia(shuxingName));
        }
        else {
            RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[number - 1].getDlm1().addElement(shuxingName + ":" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(shuxingvalue)) }) + GoodsMsgJpanel.tianjia(shuxingName));
        }
    }
    
    public static void changViewSize() {
        for (int i = 0; i < RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; i++) {
            Boolean open = RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getOpen();
            int num = 0;
            if (open) {
                int leftNum = RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i]
                        .getDlm().getSize();
                int rightNum = RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i]
                        .getDlm1().getSize();
                RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getListNo1()
                        .setBounds(7, 26, 150, leftNum * 20);
                RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getListNo2()
                        .setBounds(153, 26, 150, rightNum * 20);
                num = leftNum > rightNum ? leftNum : rightNum;
            } else {
                num = 0;
            }
            num = num > 0 ? (num * 20 + 24) : 24;
            int y = 0;
            for (int j = 0; j < i; j++) {
                y += RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[j].getHeight();
            }
            RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].setBounds(0, y, 300,
                    num);
        }
        int y = 0;
        for (int i = 0; i < RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; i++) {
            y += RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getHeight();
        }
        // RoleResistanceJframe.getResistancejframe().getResistancejpanel().setBounds(RoleResistanceJframe.getResistancejframe().getResistancejpanel().getX(),RoleResistanceJframe.getResistancejframe().getResistancejpanel().getY(),290,y);
        RoleResistanceJframe.getResistancejframe().getResistancejpanel().setSize(300, y + 3);
        RoleResistanceJframe.getResistancejframe().setSize(300, y + 3);
    }
    
    public static void clearShuXingView() {
        for (int i = 0; i < RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; ++i) {
            RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getDlm().removeAllElements();
            RoleResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getDlm1().removeAllElements();
        }
    }
    
    public static String getQuaralyPersonalName(String mes) {
        if (mes != null&&mes.equals("抗雷法")) {//新修仙法修正
            return "抗雷法|1";
        }
        if (mes != null&&mes.equals("抗火法")) {
            return "抗火法|1";
        }
        if (mes != null&&mes.equals("抗水法")) {
            return "抗水法|1";
        }
        if (mes != null&&mes.equals("抗风法")) {
            return "抗风法|1";
        }
        String returnName = null;
        int n = -1;
        switch (mes.hashCode()) {
            case 3486840: {
                if (mes.equals("qzds")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 112475: {
                if (mes.equals("qzd")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 111925: {
                if (mes.equals("qhl")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 111876: {
                if (mes.equals("qfy")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 111932: {
                if (mes.equals("qhs")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 112043: {
                if (mes.equals("qlf")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 112260: {
                if (mes.equals("qsf")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 111919: {
                if (mes.equals("qhf")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 112490: {
                if (mes.equals("qzs")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 103227: {
                if (mes.equals("hfy")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 103826: {
                if (mes.equals("hzd")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 111857: {
                if (mes.equals("qff")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
            case 103270: {
                if (mes.equals("hhf")) {
                    n = 12;
                    break;
                }
                else {
                    break;
                }
            }
            case 103276: {
                if (mes.equals("hhl")) {
                    n = 13;
                    break;
                }
                else {
                    break;
                }
            }
            case 103283: {
                if (mes.equals("hhs")) {
                    n = 14;
                    break;
                }
                else {
                    break;
                }
            }
            case 103208: {
                if (mes.equals("hff")) {
                    n = 15;
                    break;
                }
                else {
                    break;
                }
            }
            case 103394: {
                if (mes.equals("hlf")) {
                    n = 16;
                    break;
                }
                else {
                    break;
                }
            }
            case 103611: {
                if (mes.equals("hsf")) {
                    n = 17;
                    break;
                }
                else {
                    break;
                }
            }
            case 103814: {
                if (mes.equals("hyw")) {
                    n = 18;
                    break;
                }
                else {
                    break;
                }
            }
            case 103241: {
                if (mes.equals("hgh")) {
                    n = 19;
                    break;
                }
                else {
                    break;
                }
            }
            case 103841: {
                if (mes.equals("hzs")) {
                    n = 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 106491: {
                if (mes.equals("ksc")) {
                    n = 21;
                    break;
                }
                else {
                    break;
                }
            }
            case 3200155: {
                if (mes.equals("hfyv")) {
                    n = 22;
                    break;
                }
                else {
                    break;
                }
            }
            case 3200145: {
                if (mes.equals("hfyl")) {
                    n = 23;
                    break;
                }
                else {
                    break;
                }
            }
            case 106709: {
                if (mes.equals("kzd")) {
                    n = 24;
                    break;
                }
                else {
                    break;
                }
            }
            case 3308094: {
                if (mes.equals("kzds")) {
                    n = 25;
                    break;
                }
                else {
                    break;
                }
            }
            case 102551018: {
                if (mes.equals("kzdsh")) {
                    n = 26;
                    break;
                }
                else {
                    break;
                }
            }
            case 106697: {
                if (mes.equals("kyw")) {
                    n = 27;
                    break;
                }
                else {
                    break;
                }
            }
            case 106124: {
                if (mes.equals("kgh")) {
                    n = 28;
                    break;
                }
                else {
                    break;
                }
            }
            case 106159: {
                if (mes.equals("khl")) {
                    n = 29;
                    break;
                }
                else {
                    break;
                }
            }
            case 106166: {
                if (mes.equals("khs")) {
                    n = 30;
                    break;
                }
                else {
                    break;
                }
            }
            case 106110: {
                if (mes.equals("kfy")) {
                    n = 31;
                    break;
                }
                else {
                    break;
                }
            }
            case 106277: {
                if (mes.equals("klf")) {
                    n = 32;
                    break;
                }
                else {
                    break;
                }
            }
            case 106494: {
                if (mes.equals("ksf")) {
                    n = 33;
                    break;
                }
                else {
                    break;
                }
            }
            case 106153: {
                if (mes.equals("khf")) {
                    n = 34;
                    break;
                }
                else {
                    break;
                }
            }
            case 106624: {
                if (mes.equals("kwl")) {
                    n = 35;
                    break;
                }
                else {
                    break;
                }
            }
            case 3295094: {
                if (mes.equals("klsh")) {
                    n = 36;
                    break;
                }
                else {
                    break;
                }
            }
            case 106724: {
                if (mes.equals("kzs")) {
                    n = 37;
                    break;
                }
                else {
                    break;
                }
            }
            case 106091: {
                if (mes.equals("kff")) {
                    n = 38;
                    break;
                }
                else {
                    break;
                }
            }
            case 100276: {
                if (mes.equals("eds")) {
                    n = 39;
                    break;
                }
                else {
                    break;
                }
            }
            case 3110307: {
                if (mes.equals("efjl")) {
                    n = 40;
                    break;
                }
                else {
                    break;
                }
            }
            case 3110317: {
                if (mes.equals("efjv")) {
                    n = 41;
                    break;
                }
                else {
                    break;
                }
            }
            case 3116073: {
                if (mes.equals("eljl")) {
                    n = 42;
                    break;
                }
                else {
                    break;
                }
            }
            case 3116083: {
                if (mes.equals("eljv")) {
                    n = 43;
                    break;
                }
                else {
                    break;
                }
            }
            case 3117530: {
                if (mes.equals("emzl")) {
                    n = 44;
                    break;
                }
                else {
                    break;
                }
            }
            case 3114864: {
                if (mes.equals("ekbl")) {
                    n = 45;
                    break;
                }
                else {
                    break;
                }
            }
            case 3110803: {
                if (mes.equals("efzl")) {
                    n = 46;
                    break;
                }
                else {
                    break;
                }
            }
            case 96434714: {
                if (mes.equals("efzcd")) {
                    n = 47;
                    break;
                }
                else {
                    break;
                }
            }
            case 118185: {
                if (mes.equals("wxj")) {
                    n = 48;
                    break;
                }
                else {
                    break;
                }
            }
            case 118188: {
                if (mes.equals("wxm")) {
                    n = 49;
                    break;
                }
                else {
                    break;
                }
            }
            case 118195: {
                if (mes.equals("wxt")) {
                    n = 50;
                    break;
                }
                else {
                    break;
                }
            }
            case 118194: {
                if (mes.equals("wxs")) {
                    n = 51;
                    break;
                }
                else {
                    break;
                }
            }
            case 118183: {
                if (mes.equals("wxh")) {
                    n = 52;
                    break;
                }
                else {
                    break;
                }
            }
            case 3664058: {
                if (mes.equals("wxqj")) {
                    n = 53;
                    break;
                }
                else {
                    break;
                }
            }
            case 3664061: {
                if (mes.equals("wxqm")) {
                    n = 54;
                    break;
                }
                else {
                    break;
                }
            }
            case 3664068: {
                if (mes.equals("wxqt")) {
                    n = 55;
                    break;
                }
                else {
                    break;
                }
            }
            case 3664067: {
                if (mes.equals("wxqs")) {
                    n = 56;
                    break;
                }
                else {
                    break;
                }
            }
            case 3664056: {
                if (mes.equals("wxqh")) {
                    n = 57;
                    break;
                }
                else {
                    break;
                }
            }
            case 3544009: {
                if (mes.equals("swsx")) {
                    n = 58;
                    break;
                }
                else {
                    break;
                }
            }
            case 113779: {
                if (mes.equals("sff")) {
                    n = 59;
                    break;
                }
                else {
                    break;
                }
            }
            case 113965: {
                if (mes.equals("slf")) {
                    n = 60;
                    break;
                }
                else {
                    break;
                }
            }
            case 114182: {
                if (mes.equals("ssf")) {
                    n = 61;
                    break;
                }
                else {
                    break;
                }
            }
            case 113841: {
                if (mes.equals("shf")) {
                    n = 62;
                    break;
                }
                else {
                    break;
                }
            }
            case 114397: {
                if (mes.equals("szd")) {
                    n = 63;
                    break;
                }
                else {
                    break;
                }
            }
            case 113812: {
                if (mes.equals("sgh")) {
                    n = 64;
                    break;
                }
                else {
                    break;
                }
            }
            case 114179: {
                if (mes.equals("ssc")) {
                    n = 65;
                    break;
                }
                else {
                    break;
                }
            }
            case 97628: {
                if (mes.equals("blf")) {
                    n = 66;
                    break;
                }
                else {
                    break;
                }
            }
            case 97442: {
                if (mes.equals("bff")) {
                    n = 67;
                    break;
                }
                else {
                    break;
                }
            }
            case 97845: {
                if (mes.equals("bsf")) {
                    n = 68;
                    break;
                }
                else {
                    break;
                }
            }
            case 97504: {
                if (mes.equals("bhf")) {
                    n = 69;
                    break;
                }
                else {
                    break;
                }
            }
            case 111890: {
                if (mes.equals("qgh")) {
                    n = 70;
                    break;
                }
                else {
                    break;
                }
            }
            case 107882321: {
                if (mes.equals("qschx")) {
                    n = 71;
                    break;
                }
                else {
                    break;
                }
            }
            case 3129620: {
                if (mes.equals("ezml")) {
                    n = 72;
                    break;
                }
                else {
                    break;
                }
            }
            case 3308366: {
                if (mes.equals("kzml")) {
                    n = 73;
                    break;
                }
                else {
                    break;
                }
            }
            case 97475: {
                if (mes.equals("bgh")) {
                    n = 74;
                    break;
                }
                else {
                    break;
                }
            }
            case 97842: {
                if (mes.equals("bsc")) {
                    n = 75;
                    break;
                }
                else {
                    break;
                }
            }
            case 103159: {
                if (mes.equals("hds")) {
                    n = 76;
                    break;
                }
                else {
                    break;
                }
            }
            case 103212: {
                if (mes.equals("hfj")) {
                    n = 77;
                    break;
                }
                else {
                    break;
                }
            }
            case -1289454661: {
                if (mes.equals("exfljl")) {
                    n = 78;
                    break;
                }
                else {
                    break;
                }
            }
            case -1289454654: {
                if (mes.equals("exfljs")) {
                    n = 79;
                    break;
                }
                else {
                    break;
                }
            }
            case 99722551: {
                if (mes.equals("hxfkl")) {
                    n = 80;
                    break;
                }
                else {
                    break;
                }
            }
            case 99722295: {
                if (mes.equals("hxfcd")) {
                    n = 81;
                    break;
                }
                else {
                    break;
                }
            }
            case 112463: {
                if (mes.equals("qyw")) {
                    n = 82;
                    break;
                }
                else {
                    break;
                }
            }
            case 3468754: {
                if (mes.equals("qgjf")) {
                    n = 83;
                    break;
                }
                else {
                    break;
                }
            }
            case 3468258: {
                if (mes.equals("qfyf")) {
                    n = 84;
                    break;
                }
                else {
                    break;
                }
            }
            case 3480100: {
                if (mes.equals("qsdf")) {
                    n = 85;
                    break;
                }
                else {
                    break;
                }
            }
            case 112203: {
                if (mes.equals("qqk")) {
                    n = 86;
                    break;
                }
                else {
                    break;
                }
            }
            case 106437: {
                if (mes.equals("kqk")) {
                    n = 87;
                    break;
                }
                else {
                    break;
                }
            }
            case 106273: {
                if (mes.equals("klb")) {
                    n = 88;
                    break;
                }
                else {
                    break;
                }
            }
            case 3305681: {
                if (mes.equals("kwsx")) {
                    n = 89;
                    break;
                }
                else {
                    break;
                }
            }
            case 102565100: {
                if (mes.equals("kzshp")) {
                    n = 90;
                    break;
                }
                else {
                    break;
                }
            }
            case 102565255: {
                if (mes.equals("kzsmp")) {
                    n = 91;
                    break;
                }
                else {
                    break;
                }
            }
            case 3486964: {
                if (mes.equals("qzhs")) {
                    n = 92;
                    break;
                }
                else {
                    break;
                }
            }
            case 3292797: {
                if (mes.equals("kjge")) {
                    n = 93;
                    break;
                }
                else {
                    break;
                }
            }
            case 106449: {
                if (mes.equals("kqw")) {
                    n = 94;
                    break;
                }
                else {
                    break;
                }
            }
            case 106165: {
                if (mes.equals("khr")) {
                    n = 95;
                    break;
                }
                else {
                    break;
                }
            }
            case 106135: {
                if (mes.equals("kgs")) {
                    n = 96;
                    break;
                }
                else {
                    break;
                }
            }
            case 106439: {
                if (mes.equals("kqm")) {
                    n = 97;
                    break;
                }
                else {
                    break;
                }
            }
            case 106532: {
                if (mes.equals("ktm")) {
                    n = 98;
                    break;
                }
                else {
                    break;
                }
            }
            case 106655: {
                if (mes.equals("kxl")) {
                    n = 99;
                    break;
                }
                else {
                    break;
                }
            }
            case 106092: {
                if (mes.equals("kfg")) {
                    n = 100;
                    break;
                }
                else {
                    break;
                }
            }
            case 101069: {
                if (mes.equals("f_f")) {
                    n = 101;
                    break;
                }
                else {
                    break;
                }
            }
            case 101071: {
                if (mes.equals("f_h")) {
                    n = 102;
                    break;
                }
                else {
                    break;
                }
            }
            case 101067: {
                if (mes.equals("f_d")) {
                    n = 103;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133874: {
                if (mes.equals("f_zs")) {
                    n = 104;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133641: {
                if (mes.equals("f_sc")) {
                    n = 105;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133799: {
                if (mes.equals("f_xf")) {
                    n = 106;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133801: {
                if (mes.equals("f_xh")) {
                    n = 107;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133812: {
                if (mes.equals("f_xs")) {
                    n = 108;
                    break;
                }
                else {
                    break;
                }
            }
            case 3133805: {
                if (mes.equals("f_xl")) {
                    n = 109;
                    break;
                }
                else {
                    break;
                }
            }
            case 105967: {
                if (mes.equals("kbf")) {
                    n = 110;
                    break;
                }
                else {
                    break;
                }
            }
            case 105969: {
                if (mes.equals("kbh")) {
                    n = 111;
                    break;
                }
                else {
                    break;
                }
            }
            case 105980: {
                if (mes.equals("kbs")) {
                    n = 112;
                    break;
                }
                else {
                    break;
                }
            }
            case 105973: {
                if (mes.equals("kbl")) {
                    n = 113;
                    break;
                }
                else {
                    break;
                }
            }
            case 105968: {
                if (mes.equals("kbg")) {
                    n = 114;
                    break;
                }
                else {
                    break;
                }
            }
            case 112076: {
                if (mes.equals("qmh")) {
                    n = 115;
                    break;
                }
                else {
                    break;
                }
            }
            case 111982: {
                if (mes.equals("qjg")) {
                    n = 116;
                    break;
                }
                else {
                    break;
                }
            }
            case 112215: {
                if (mes.equals("qqw")) {
                    n = 117;
                    break;
                }
                else {
                    break;
                }
            }
            case 97461: {
                if (mes.equals("bfy")) {
                    n = 118;
                    break;
                }
                else {
                    break;
                }
            }
            case 97510: {
                if (mes.equals("bhl")) {
                    n = 119;
                    break;
                }
                else {
                    break;
                }
            }
            case 97517: {
                if (mes.equals("bhs")) {
                    n = 120;
                    break;
                }
                else {
                    break;
                }
            }
            case 98060: {
                if (mes.equals("bzd")) {
                    n = 121;
                    break;
                }
                else {
                    break;
                }
            }
            case 97566: {
                if (mes.equals("bjf")) {
                    n = 122;
                    break;
                }
                else {
                    break;
                }
            }
            case 97567: {
                if (mes.equals("bjg")) {
                    n = 123;
                    break;
                }
                else {
                    break;
                }
            }
            case 98075: {
                if (mes.equals("bzs")) {
                    n = 124;
                    break;
                }
                else {
                    break;
                }
            }
            case 98048: {
                if (mes.equals("byw")) {
                    n = 125;
                    break;
                }
                else {
                    break;
                }
            }
            case 97661: {
                if (mes.equals("bmh")) {
                    n = 126;
                    break;
                }
                else {
                    break;
                }
            }
            case 96428033: {
                if (mes.equals("efsds")) {
                    n = 127;
                    break;
                }
                else {
                    break;
                }
            }
            case 100462: {
                if (mes.equals("ejs")) {
                    n = 128;
                    break;
                }
                else {
                    break;
                }
            }
            case 3473751: {
                if (mes.equals("qlpl")) {
                    n = 129;
                    break;
                }
                else {
                    break;
                }
            }
            case 3473454: {
                if (mes.equals("qlfy")) {
                    n = 130;
                    break;
                }
                else {
                    break;
                }
            }
            case 3473338: {
                if (mes.equals("qlcb")) {
                    n = 131;
                    break;
                }
                else {
                    break;
                }
            }
            case 107677750: {
                if (mes.equals("qlglv")) {
                    n = 132;
                    break;
                }
                else {
                    break;
                }
            }
            case 107677731: {
                if (mes.equals("qlglc")) {
                    n = 133;
                    break;
                }
                else {
                    break;
                }
            }
            case 93823677: {
                if (mes.equals("blfcd")) {
                    n = 134;
                    break;
                }
                else {
                    break;
                }
            }
            case 93644931: {
                if (mes.equals("bffcd")) {
                    n = 135;
                    break;
                }
                else {
                    break;
                }
            }
            case 94032214: {
                if (mes.equals("bsfcd")) {
                    n = 136;
                    break;
                }
                else {
                    break;
                }
            }
            case 93704513: {
                if (mes.equals("bhfcd")) {
                    n = 137;
                    break;
                }
                else {
                    break;
                }
            }
            case 93676644: {
                if (mes.equals("bghcd")) {
                    n = 138;
                    break;
                }
                else {
                    break;
                }
            }
            case 94029331: {
                if (mes.equals("bsccd")) {
                    n = 139;
                    break;
                }
                else {
                    break;
                }
            }
            case 99997: {
                if (mes.equals("dzs")) {
                    n = 140;
                    break;
                }
                else {
                    break;
                }
            }
            case 99426: {
                if (mes.equals("dhf")) {
                    n = 141;
                    break;
                }
                else {
                    break;
                }
            }
            case 99550: {
                if (mes.equals("dlf")) {
                    n = 142;
                    break;
                }
                else {
                    break;
                }
            }
            case 99364: {
                if (mes.equals("dff")) {
                    n = 143;
                    break;
                }
                else {
                    break;
                }
            }
            case 99767: {
                if (mes.equals("dsf")) {
                    n = 144;
                    break;
                }
                else {
                    break;
                }
            }
            case 99302: {
                if (mes.equals("ddf")) {
                    n = 145;
                    break;
                }
                else {
                    break;
                }
            }
            case 99383: {
                if (mes.equals("dfy")) {
                    n = 146;
                    break;
                }
                else {
                    break;
                }
            }
            case 99432: {
                if (mes.equals("dhl")) {
                    n = 147;
                    break;
                }
                else {
                    break;
                }
            }
            case 99439: {
                if (mes.equals("dhs")) {
                    n = 148;
                    break;
                }
                else {
                    break;
                }
            }
            case 99970: {
                if (mes.equals("dyw")) {
                    n = 149;
                    break;
                }
                else {
                    break;
                }
            }
            case 99397: {
                if (mes.equals("dgh")) {
                    n = 150;
                    break;
                }
                else {
                    break;
                }
            }
            case 99764: {
                if (mes.equals("dsc")) {
                    n = 151;
                    break;
                }
                else {
                    break;
                }
            }
            case 105533: {
                if (mes.equals("jsf")) {
                    n = 152;
                    break;
                }
                else {
                    break;
                }
            }
            case 105130: {
                if (mes.equals("jff")) {
                    n = 153;
                    break;
                }
                else {
                    break;
                }
            }
            case 105316: {
                if (mes.equals("jlf")) {
                    n = 154;
                    break;
                }
                else {
                    break;
                }
            }
            case 105192: {
                if (mes.equals("jhf")) {
                    n = 155;
                    break;
                }
                else {
                    break;
                }
            }
            case 105163: {
                if (mes.equals("jgh")) {
                    n = 156;
                    break;
                }
                else {
                    break;
                }
            }
            case 3318718: {
                if (mes.equals("lffj")) {
                    n = 157;
                    break;
                }
                else {
                    break;
                }
            }
            case 3199554: {
                if (mes.equals("hffj")) {
                    n = 158;
                    break;
                }
                else {
                    break;
                }
            }
            case 3527255: {
                if (mes.equals("sffj")) {
                    n = 159;
                    break;
                }
                else {
                    break;
                }
            }
            case 3139972: {
                if (mes.equals("fffj")) {
                    n = 160;
                    break;
                }
                else {
                    break;
                }
            }
            case -905054778: {
                if (mes.equals("sfmsfs")) {
                    n = 161;
                    break;
                }
                else {
                    break;
                }
            }
            case -905205444: {
                if (mes.equals("sfhqmm")) {
                    n = 162;
                    break;
                }
                else {
                    break;
                }
            }
            case -904943178: {
                if (mes.equals("sfqkjs")) {
                    n = 163;
                    break;
                }
                else {
                    break;
                }
            }
            case 3152698: {
                if (mes.equals("fsmz")) {
                    n = 164;
                    break;
                }
                else {
                    break;
                }
            }
            case 108646: {
                if (mes.equals("mzs")) {
                    n = 165;
                    break;
                }
                else {
                    break;
                }
            }
            case 108075: {
                if (mes.equals("mhf")) {
                    n = 166;
                    break;
                }
                else {
                    break;
                }
            }
            case 108199: {
                if (mes.equals("mlf")) {
                    n = 167;
                    break;
                }
                else {
                    break;
                }
            }
            case 108013: {
                if (mes.equals("mff")) {
                    n = 168;
                    break;
                }
                else {
                    break;
                }
            }
            case 108416: {
                if (mes.equals("msf")) {
                    n = 169;
                    break;
                }
                else {
                    break;
                }
            }
            case 107951: {
                if (mes.equals("mdf")) {
                    n = 170;
                    break;
                }
                else {
                    break;
                }
            }
            case 108032: {
                if (mes.equals("mfy")) {
                    n = 171;
                    break;
                }
                else {
                    break;
                }
            }
            case 108081: {
                if (mes.equals("mhl")) {
                    n = 172;
                    break;
                }
                else {
                    break;
                }
            }
            case 108088: {
                if (mes.equals("mhs")) {
                    n = 173;
                    break;
                }
                else {
                    break;
                }
            }
            case 108619: {
                if (mes.equals("myw")) {
                    n = 174;
                    break;
                }
                else {
                    break;
                }
            }
            case 108046: {
                if (mes.equals("mgh")) {
                    n = 175;
                    break;
                }
                else {
                    break;
                }
            }
            case 108413: {
                if (mes.equals("msc")) {
                    n = 176;
                    break;
                }
                else {
                    break;
                }
            }
            case 3152341: {
                if (mes.equals("fsbj")) {
                    n = 177;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024834: {
                if (mes.equals("bjlf")) {
                    n = 178;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024648: {
                if (mes.equals("bjff")) {
                    n = 179;
                    break;
                }
                else {
                    break;
                }
            }
            case 3025051: {
                if (mes.equals("bjsf")) {
                    n = 180;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024710: {
                if (mes.equals("bjhf")) {
                    n = 181;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024681: {
                if (mes.equals("bjgh")) {
                    n = 182;
                    break;
                }
                else {
                    break;
                }
            }
            case 3025048: {
                if (mes.equals("bjsc")) {
                    n = 183;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024667: {
                if (mes.equals("bjfy")) {
                    n = 184;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024716: {
                if (mes.equals("bjhl")) {
                    n = 185;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024723: {
                if (mes.equals("bjhs")) {
                    n = 186;
                    break;
                }
                else {
                    break;
                }
            }
            case 3025266: {
                if (mes.equals("bjzd")) {
                    n = 187;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024772: {
                if (mes.equals("bjjf")) {
                    n = 188;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024773: {
                if (mes.equals("bjjg")) {
                    n = 189;
                    break;
                }
                else {
                    break;
                }
            }
            case 3025281: {
                if (mes.equals("bjzs")) {
                    n = 190;
                    break;
                }
                else {
                    break;
                }
            }
            case 3025254: {
                if (mes.equals("bjyw")) {
                    n = 191;
                    break;
                }
                else {
                    break;
                }
            }
            case 3024867: {
                if (mes.equals("bjmh")) {
                    n = 192;
                    break;
                }
                else {
                    break;
                }
            }
            case -1265564426: {
                if (mes.equals("fsbjcd")) {
                    n = 193;
                    break;
                }
                else {
                    break;
                }
            }
            case -1388098653: {
                if (mes.equals("bjlfcd")) {
                    n = 194;
                    break;
                }
                else {
                    break;
                }
            }
            case -1388277399: {
                if (mes.equals("bjffcd")) {
                    n = 195;
                    break;
                }
                else {
                    break;
                }
            }
            case -1387890116: {
                if (mes.equals("bjsfcd")) {
                    n = 196;
                    break;
                }
                else {
                    break;
                }
            }
            case -1388217817: {
                if (mes.equals("bjhfcd")) {
                    n = 197;
                    break;
                }
                else {
                    break;
                }
            }
            case -1388245686: {
                if (mes.equals("bjghcd")) {
                    n = 198;
                    break;
                }
                else {
                    break;
                }
            }
            case -1387892999: {
                if (mes.equals("bjsccd")) {
                    n = 199;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                returnName = "强中毒伤害|2";
                break;
            }
            case 1: {
                returnName = "强中毒|2";
                break;
            }
            case 2: {
                returnName = "强混乱|2";
                break;
            }
            case 3: {
                returnName = "强封印|2";
                break;
            }
            case 4: {
                returnName = "强昏睡|2";
                break;
            }
            case 5: {
                returnName = "强雷法|2";
                break;
            }
            case 6: {
                returnName = "强水法|2";
                break;
            }
            case 7: {
                returnName = "强火法|2";
                break;
            }
            case 8: {
                returnName = "强震慑|2";
                break;
            }
            case 9: {
                returnName = "忽视封印|2";
                break;
            }
            case 10: {
                returnName = "忽视中毒|2";
                break;
            }
            case 11: {
                returnName = "强风法|2";
                break;
            }
            case 12: {
                returnName = "忽视火法|2";
                break;
            }
            case 13: {
                returnName = "忽视混乱|2";
                break;
            }
            case 14: {
                returnName = "忽视昏睡|2";
                break;
            }
            case 15: {
                returnName = "忽视风法|2";
                break;
            }
            case 16: {
                returnName = "忽视雷法|2";
                break;
            }
            case 17: {
                returnName = "忽视水法|2";
                break;
            }
            case 18: {
                returnName = "忽视遗忘|2";
                break;
            }
            case 19: {
                returnName = "忽视抗鬼火|2";
                break;
            }
            case 20: {
                returnName = "忽视抗震慑|2";
                break;
            }
            case 21: {
                returnName = "抗三尸|1";
                break;
            }
            case 22: {
                returnName = "忽视防御程度|3";
                break;
            }
            case 23: {
                returnName = "忽视防御几率|3";
                break;
            }
            case 24: {
                returnName = "抗中毒|1";
                break;
            }
            case 25: {
                returnName = "抗毒伤|1";
                break;
            }
            case 26: {
                returnName = "抗毒伤害|1";
                break;
            }
            case 27: {
                returnName = "抗遗忘|1";
                break;
            }
            case 28: {
                returnName = "抗鬼火|1";
                break;
            }
            case 29: {
                returnName = "抗混乱|1";
                break;
            }
            case 30: {
                returnName = "抗昏睡|1";
                break;
            }
            case 31: {
                returnName = "抗封印|1";
                break;
            }
            case 32: {
                returnName = "抗雷|1";
                break;
            }
            case 33: {
                returnName = "抗水|1";
                break;
            }
            case 34: {
                returnName = "抗火|1";
                break;
            }
            case 35: {
                returnName = "物理吸收率|3";
                break;
            }
            case 36: {
                returnName = "抗龙伤害|3";
                break;
            }
            case 37: {
                returnName = "抗震慑|1";
                break;
            }
            case 38: {
                returnName = "抗风|1";
                break;
            }
            case 39: {
                returnName = "躲闪率|3";
                break;
            }
            case 40: {
                returnName = "反击率|3";
                break;
            }
            case 41: {
                returnName = "反击次数|3";
                break;
            }
            case 42: {
                returnName = "连击率|3";
                break;
            }
            case 43: {
                returnName = "连击次数|3";
                break;
            }
            case 44: {
                returnName = "命中率|3";
                break;
            }
            case 45: {
                returnName = "狂暴几率|3";
                break;
            }
            case 46: {
                returnName = "反震率|5";
                break;
            }
            case 47: {
                returnName = "反震程度|5";
                break;
            }
            case 48: {
                returnName = "金|4";
                break;
            }
            case 49: {
                returnName = "木|4";
                break;
            }
            case 50: {
                returnName = "土|4";
                break;
            }
            case 51: {
                returnName = "水|4";
                break;
            }
            case 52: {
                returnName = "火|4";
                break;
            }
            case 53: {
                returnName = "强力克金|4";
                break;
            }
            case 54: {
                returnName = "强力克木|4";
                break;
            }
            case 55: {
                returnName = "强力克土|4";
                break;
            }
            case 56: {
                returnName = "强力克水|4";
                break;
            }
            case 57: {
                returnName = "强力克火|4";
                break;
            }
            case 58: {
                returnName = "无属性伤害|5";
                break;
            }
            case 59: {
                returnName = "风法伤害|2";
                break;
            }
            case 60: {
                returnName = "雷法伤害|2";
                break;
            }
            case 61: {
                returnName = "水法伤害|2";
                break;
            }
            case 62: {
                returnName = "火法伤害|2";
                break;
            }
            case 63: {
                returnName = "毒伤害|2";
                break;
            }
            case 64: {
                returnName = "鬼火伤害|2";
                break;
            }
            case 65: {
                returnName = "加强三尸虫|2";
                break;
            }
            case 66: {
                returnName = "雷系狂暴几率|2";
                break;
            }
            case 67: {
                returnName = "风系狂暴几率|2";
                break;
            }
            case 68: {
                returnName = "水系狂暴几率|2";
                break;
            }
            case 69: {
                returnName = "火系狂暴几率|2";
                break;
            }
            case 70: {
                returnName = "强鬼火|2";
                break;
            }
            case 71: {
                returnName = "强三尸回血|2";
                break;
            }
            case 72: {
                returnName = "致命几率|3";
                break;
            }
            case 73: {
                returnName = "抗致命率|3";
                break;
            }
            case 74: {
                returnName = "鬼火狂暴几率|2";
                break;
            }
            case 75: {
                returnName = "三尸虫狂暴几率|2";
                break;
            }
            case 76: {
                returnName = "忽视躲闪|3";
                break;
            }
            case 77: {
                returnName = "忽视反击|3";
                break;
            }
            case 78: {
                returnName = "仙法连击率|2";
                break;
            }
            case 79: {
                returnName = "仙法连击次数|2";
                break;
            }
            case 80: {
                returnName = "忽视仙法抗性率|2";
                break;
            }
            case 81: {
                returnName = "忽视仙法抗性程度|2";
                break;
            }
            case 82: {
                returnName = "加强遗忘|2";
                break;
            }
            case 83: {
                returnName = "加强加攻法术效果|2";
                break;
            }
            case 84: {
                returnName = "加强加防法术效果|2";
                break;
            }
            case 85: {
                returnName = "加强加速法术效果|2";
                break;
            }
            case 86: {
                returnName = "增加强克效果|4";
                break;
            }
            case 87: {
                returnName = "抵御强克效果|4";
                break;
            }
            case 88: {
                returnName = "抗灵宝伤害|5";
                break;
            }
            case 89: {
                returnName = "抗无属性伤害|5";
                break;
            }
            case 90: {
                returnName = "抗震慑气血|1";
                break;
            }
            case 91: {
                returnName = "抗震慑魔法|1";
                break;
            }
            case 92: {
                returnName = "对召唤兽伤害|5";
                break;
            }
            case 93: {
                returnName = "抗金箍|5";
                break;
            }
            case 94: {
                returnName = "抗情网|5";
                break;
            }
            case 95: {
                returnName = "上善若水|5";
                break;
            }
            case 96: {
                returnName = "尘埃落定|5";
                break;
            }
            case 97: {
                returnName = "美人迟暮|5";
                break;
            }
            case 98: {
                returnName = "化血成碧|5";
                break;
            }
            case 99: {
                returnName = "明珠有泪|5";
                break;
            }
            case 100: {
                returnName = "灵犀一点|5";
                break;
            }
            case 101: {
                returnName = "附封攻击|5";
                break;
            }
            case 102: {
                returnName = "附混攻击|5";
                break;
            }
            case 103: {
                returnName = "附毒攻击|5";
                break;
            }
            case 104: {
                returnName = "附震慑攻击|5";
                break;
            }
            case 105: {
                returnName = "附三尸攻击|5";
                break;
            }
            case 106: {
                returnName = "附风攻击|5";
                break;
            }
            case 107: {
                returnName = "附火攻击|5";
                break;
            }
            case 108: {
                returnName = "附水攻击|5";
                break;
            }
            case 109: {
                returnName = "附雷攻击|5";
                break;
            }
            case 110: {
                returnName = "抗风法狂暴|1";
                break;
            }
            case 111: {
                returnName = "抗火法狂暴|1";
                break;
            }
            case 112: {
                returnName = "抗水法狂暴|1";
                break;
            }
            case 113: {
                returnName = "抗雷法狂暴|1";
                break;
            }
            case 114: {
                returnName = "抗鬼火狂暴|1";
                break;
            }
            case 115: {
                returnName = "加强魅惑|2";
                break;
            }
            case 116: {
                returnName = "强金箍|5";
                break;
            }
            case 117: {
                returnName = "强情网|5";
                break;
            }
            case 118: {
                returnName = "封印狂暴|2";
                break;
            }
            case 119: {
                returnName = "混乱狂暴|2";
                break;
            }
            case 120: {
                returnName = "昏睡狂暴|2";
                break;
            }
            case 121: {
                returnName = "毒法狂暴|2";
                break;
            }
            case 122: {
                returnName = "加防狂暴|2";
                break;
            }
            case 123: {
                returnName = "加攻狂暴|2";
                break;
            }
            case 124: {
                returnName = "震慑狂暴|2";
                break;
            }
            case 125: {
                returnName = "遗忘狂暴|2";
                break;
            }
            case 126: {
                returnName = "魅惑狂暴|2";
                break;
            }
            case 127: {
                returnName = "法术躲闪|5";
                break;
            }
            case 128: {
                returnName = "伤害减免|5";
                break;
            }
            case 129: {
                returnName = "加强霹雳效果|2";
                break;
            }
            case 130: {
                returnName = "加强扶摇效果|2";
                break;
            }
            case 131: {
                returnName = "加强沧波效果|2";
                break;
            }
            case 132: {
                returnName = "甘霖回血值|2";
                break;
            }
            case 133: {
                returnName = "甘霖回血程度|2";
                break;
            }
            case 134: {
                returnName = "雷法狂暴程度|2";
                break;
            }
            case 135: {
                returnName = "风法狂暴程度|2";
                break;
            }
            case 136: {
                returnName = "水法狂暴程度|2";
                break;
            }
            case 137: {
                returnName = "火法狂暴程度|2";
                break;
            }
            case 138: {
                returnName = "鬼火狂暴程度|2";
                break;
            }
            case 139: {
                returnName = "三尸虫狂暴程度|2";
                break;
            }
            case 140: {
                returnName = "震慑躲闪|5";
                break;
            }
            case 141: {
                returnName = "火法躲闪|5";
                break;
            }
            case 142: {
                returnName = "雷法躲闪|5";
                break;
            }
            case 143: {
                returnName = "风法躲闪|5";
                break;
            }
            case 144: {
                returnName = "水法躲闪|5";
                break;
            }
            case 145: {
                returnName = "毒法躲闪|5";
                break;
            }
            case 146: {
                returnName = "封印躲闪|5";
                break;
            }
            case 147: {
                returnName = "混乱躲闪|5";
                break;
            }
            case 148: {
                returnName = "昏睡躲闪|5";
                break;
            }
            case 149: {
                returnName = "遗忘躲闪|5";
                break;
            }
            case 150: {
                returnName = "鬼火躲闪|5";
                break;
            }
            case 151: {
                returnName = "三尸虫躲闪|5";
                break;
            }
            case 152: {
                returnName = "水法伤害减免|5";
                break;
            }
            case 153: {
                returnName = "风法伤害减免|5";
                break;
            }
            case 154: {
                returnName = "雷法伤害减免|5";
                break;
            }
            case 155: {
                returnName = "火法伤害减免|5";
                break;
            }
            case 156: {
                returnName = "鬼火伤害减免|5";
                break;
            }
            case 157: {
                returnName = "雷法反击|5";
                break;
            }
            case 158: {
                returnName = "火法反击|5";
                break;
            }
            case 159: {
                returnName = "水法反击|5";
                break;
            }
            case 160: {
                returnName = "风法反击|5";
                break;
            }
            case 161: {
                returnName = "被动魔神附身|5";
                break;
            }
            case 162: {
                returnName = "被动含情脉脉|5";
                break;
            }
            case 163: {
                returnName = "被动乾坤借速|5";
                break;
            }
            case 164: {
                returnName = "法术命中率|2";
                break;
            }
            case 165: {
                returnName = "震慑命中率|2";
                break;
            }
            case 166: {
                returnName = "火法命中率|2";
                break;
            }
            case 167: {
                returnName = "雷法命中率|2";
                break;
            }
            case 168: {
                returnName = "风法命中率|2";
                break;
            }
            case 169: {
                returnName = "水法命中率|2";
                break;
            }
            case 170: {
                returnName = "毒法命中率|2";
                break;
            }
            case 171: {
                returnName = "封印命中率|2";
                break;
            }
            case 172: {
                returnName = "混乱命中率|2";
                break;
            }
            case 173: {
                returnName = "昏睡命中率|2";
                break;
            }
            case 174: {
                returnName = "遗忘命中率|2";
                break;
            }
            case 175: {
                returnName = "鬼火命中率|2";
                break;
            }
            case 176: {
                returnName = "三尸虫命中率|2";
                break;
            }
            case 177: {
                returnName = "法术暴击率|2";
                break;
            }
            case 178: {
                returnName = "雷法暴击率|2";
                break;
            }
            case 179: {
                returnName = "风法暴击率|2";
                break;
            }
            case 180: {
                returnName = "水法暴击率|2";
                break;
            }
            case 181: {
                returnName = "火法暴击率|2";
                break;
            }
            case 182: {
                returnName = "鬼火暴击率|2";
                break;
            }
            case 183: {
                returnName = "三尸虫暴击率|2";
                break;
            }
            case 184: {
                returnName = "封印暴击率|2";
                break;
            }
            case 185: {
                returnName = "混乱暴击率|2";
                break;
            }
            case 186: {
                returnName = "昏睡暴击率|2";
                break;
            }
            case 187: {
                returnName = "毒法暴击率|2";
                break;
            }
            case 188: {
                returnName = "加防暴击率|2";
                break;
            }
            case 189: {
                returnName = "加攻暴击率|2";
                break;
            }
            case 190: {
                returnName = "震慑暴击率|2";
                break;
            }
            case 191: {
                returnName = "遗忘暴击率|2";
                break;
            }
            case 192: {
                returnName = "魅惑暴击率|2";
                break;
            }
            case 193: {
                returnName = "法术暴击程度|2";
                break;
            }
            case 194: {
                returnName = "雷法暴击程度|2";
                break;
            }
            case 195: {
                returnName = "风法暴击程度|2";
                break;
            }
            case 196: {
                returnName = "水法暴击程度|2";
                break;
            }
            case 197: {
                returnName = "火法暴击程度|2";
                break;
            }
            case 198: {
                returnName = "鬼火暴击程度|2";
                break;
            }
            case 199: {
                returnName = "三尸虫暴击程度|2";
                break;
            }
        }
        return returnName;
    }
    
    public static void restQSX() {
        RoleAttribute roleAttributecz1 = RoleData.getRoleData().getRoleAttribute();
        LoginResult loginResultcz1 = RoleData.getRoleData().getLoginResult();
        int lvltrue1 = AnalysisString.lvltrue((int)loginResultcz1.getGrade());
        if (JpanelOnJalbelBtn.roleToggleJpanel == null) {
            return;
        }
        JpanelOnJalbelBtn.roleToggleJpanel.changeSoaringPanel(lvltrue1);
        RolePanelShow.changeGrade((int)loginResultcz1.getGrade());
        int lvl1 = AnalysisString.lvlint((int)loginResultcz1.getGrade());
        if (roleAttributecz1 == null) {
            roleAttributecz1 = new RoleAttribute();
        }
        JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone1().setText(lvl1 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence1().setText(lvl1 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabpower1().setText(lvl1 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed1().setText(lvl1 + "");
        if (loginResultcz1.getTurnAround() >= 4) {
            JpanelOnJalbelBtn.roleToggleJpanel.getLabability1().setText(lvl1 + "");
        }
        roleAttributecz1.setBONEONE(Integer.valueOf(lvl1));
        roleAttributecz1.setSPIRONE(Integer.valueOf(lvl1));
        roleAttributecz1.setPOWERONE(Integer.valueOf(lvl1));
        roleAttributecz1.setSPEEDONE(Integer.valueOf(lvl1));
        if (loginResultcz1.getTurnAround() >= 4) {
            roleAttributecz1.setCALMONE(Integer.valueOf(lvl1));
        }
        else {
            roleAttributecz1.setCALMONE(Integer.valueOf(0));
        }
        JLabel labpointnumber1 = JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber1();
        RoleToggleJpanel roleToggleJpanel = JpanelOnJalbelBtn.roleToggleJpanel;
        labpointnumber1.setText(RoleToggleJpanel.getCanpoint1(roleAttributecz1).toString());
        String mescz1 = Agreement.getAgreement().rolechangeAgreement("S" + roleAttributecz1.getBONEONE() + "=" + roleAttributecz1.getSPIRONE() + "=" + roleAttributecz1.getPOWERONE() + "=" + roleAttributecz1.getSPEEDONE() + "=" + roleAttributecz1.getCALMONE() + "=" + roleAttributecz1.getATTRIBUTENAMEONE() + ",S" + roleAttributecz1.getBONETWO() + "=" + roleAttributecz1.getSPIRTWO() + "=" + roleAttributecz1.getPOWERTWO() + "=" + roleAttributecz1.getSPEEDTWO() + "=" + roleAttributecz1.getCALMTWO() + "=" + roleAttributecz1.getATTRIBUTENAMETWO());
        SendMessageUntil.toServer(mescz1);
        ZhuFrame.getZhuJpanel().addPrompt2("【属性一】重置成功！");
    }
    
    public static void restQSX1() {
        LoginResult loginResultcz2 = RoleData.getRoleData().getLoginResult();
        RoleAttribute roleAttributecz2 = RoleData.getRoleData().getRoleAttribute();
        int lvltrue2 = AnalysisString.lvltrue((int)loginResultcz2.getGrade());
        if (JpanelOnJalbelBtn.roleToggleJpanel == null) {
            return;
        }
        JpanelOnJalbelBtn.roleToggleJpanel.changeSoaringPanel(lvltrue2);
        RolePanelShow.changeGrade((int)loginResultcz2.getGrade());
        int lvl2 = AnalysisString.lvlint((int)loginResultcz2.getGrade());
        if (roleAttributecz2 == null) {
            roleAttributecz2 = new RoleAttribute();
        }
        JpanelOnJalbelBtn.roleToggleJpanel.getLabrootbone2().setText(lvl2 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabintelligence2().setText(lvl2 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabpower2().setText(lvl2 + "");
        JpanelOnJalbelBtn.roleToggleJpanel.getLabspeed2().setText(lvl2 + "");
        if (loginResultcz2.getTurnAround() >= 4) {
            JpanelOnJalbelBtn.roleToggleJpanel.getLabability2().setText(lvl2 + "");
        }
        roleAttributecz2.setBONETWO(Integer.valueOf(lvl2));
        roleAttributecz2.setSPIRTWO(Integer.valueOf(lvl2));
        roleAttributecz2.setPOWERTWO(Integer.valueOf(lvl2));
        roleAttributecz2.setSPEEDTWO(Integer.valueOf(lvl2));
        if (loginResultcz2.getTurnAround() >= 4) {
            roleAttributecz2.setCALMTWO(Integer.valueOf(lvl2));
        }
        else {
            roleAttributecz2.setCALMTWO(Integer.valueOf(0));
        }
        JLabel labpointnumber2 = JpanelOnJalbelBtn.roleToggleJpanel.getLabpointnumber2();
        RoleToggleJpanel roleToggleJpanel = JpanelOnJalbelBtn.roleToggleJpanel;
        labpointnumber2.setText(RoleToggleJpanel.getCanpoint2(roleAttributecz2).toString());
        String mescz2 = Agreement.getAgreement().rolechangeAgreement("S" + roleAttributecz2.getBONEONE() + "=" + roleAttributecz2.getSPIRONE() + "=" + roleAttributecz2.getPOWERONE() + "=" + roleAttributecz2.getSPEEDONE() + "=" + roleAttributecz2.getCALMONE() + "=" + roleAttributecz2.getATTRIBUTENAMEONE() + ",S" + roleAttributecz2.getBONETWO() + "=" + roleAttributecz2.getSPIRTWO() + "=" + roleAttributecz2.getPOWERTWO() + "=" + roleAttributecz2.getSPEEDTWO() + "=" + roleAttributecz2.getCALMTWO() + "=" + roleAttributecz2.getATTRIBUTENAMETWO());
        SendMessageUntil.toServer(mescz2);
        ZhuFrame.getZhuJpanel().addPrompt2("【属性二】重置成功！");
    }
    public static Map<String, Map<String, String>> races;

    static {
        races = new HashMap<>();
        Map<String, String> xian = new HashMap<>();
        Map<String, String> mo = new HashMap<>();
        Map<String, String> ren = new HashMap<>();
        Map<String, String> gui = new HashMap<>();
        Map<String, String> long_ = new HashMap<>();

        //仙
        xian.put("加强混乱", "加强混乱");
        xian.put("加强昏睡", "加强昏睡");
        xian.put("加强中毒", "加强中毒");
        xian.put("加强封印", "加强封印");
        xian.put("忽视抗混", "忽视抗混");
        xian.put("忽视抗混乱", "忽视抗混乱");
        xian.put("忽视抗睡", "忽视抗睡");
        xian.put("忽视抗昏睡", "忽视抗昏睡");
        xian.put("忽视抗毒", "忽视抗毒");
        xian.put("忽视抗中毒", "忽视抗中毒");
        xian.put("忽视抗封", "忽视抗封");
        xian.put("忽视抗封印", "忽视抗封印");
        xian.put("加强攻击法术", "加强攻击法术");
        xian.put("加强防御法术", "加强防御法术");
        xian.put("加强速度法术", "加强速度法术");
        xian.put("加强鬼火", "加强鬼火");
        xian.put("忽视鬼火", "忽视鬼火");
        xian.put("忽视抗鬼火", "忽视抗鬼火");
        xian.put("鬼火伤害", "鬼火伤害");
        xian.put("鬼火狂暴几率", "鬼火狂暴几率");
        xian.put("鬼火狂暴程度", "鬼火狂暴程度");
        xian.put("加强遗忘", "加强遗忘");
        xian.put("三尸虫狂暴几率", "三尸虫狂暴几率");
        xian.put("三尸虫狂暴程度", "三尸虫狂暴程度");
        xian.put("强三尸虫", "强三尸虫");
        xian.put("强三尸回虫血程度", "强三尸回虫血程度");
        xian.put("鬼火连击率", "鬼火连击率");
        xian.put("鬼火连击次数", "鬼火连击次数");
        xian.put("加强霹雳效果", "加强霹雳效果");
        xian.put("加强扶摇效果", "加强扶摇效果");
        xian.put("加强沧波效果", "加强沧波效果");
        xian.put("加强甘霖回血值", "加强甘霖回血值");
        xian.put("甘霖回血程度", "甘霖回血程度");
        xian.put("加强震慑", "加强震慑");
        xian.put("忽视抗震慑", "忽视抗震慑");
        xian.put("致命率", "致命率");
        xian.put("狂暴率", "狂暴率");
        xian.put("连击率", "连击率");
        xian.put("反击率", "反击率");
        xian.put("命中率", "命中率");
        xian.put("连击次数", "连击次数");
        xian.put("反击次数", "反击次数");
        xian.put("忽视防御几率", "忽视防御几率");
        xian.put("忽视防御程度", "忽视防御程度");
        //人
        ren.put("忽视抗水", "忽视抗水");
        ren.put("忽视抗雷", "忽视抗雷");
        ren.put("忽视抗火", "忽视抗火");
        ren.put("忽视抗风", "忽视抗风");
        ren.put("加强水", "加强水");
        ren.put("加强雷", "加强雷");
        ren.put("加强火", "加强火");
        ren.put("加强风", "加强风");
        ren.put("水法狂暴几率", "水法狂暴几率");
        ren.put("雷法狂暴几率", "雷法狂暴几率");
        ren.put("火法狂暴几率", "火法狂暴几率");
        ren.put("风法狂暴几率", "风法狂暴几率");
        ren.put("水法狂暴程度", "水法狂暴程度");
        ren.put("雷法狂暴程度", "雷法狂暴程度");
        ren.put("火法狂暴程度", "火法狂暴程度");
        ren.put("风法狂暴程度", "风法狂暴程度");
        ren.put("水法伤害", "水法伤害");
        ren.put("雷法伤害", "雷法伤害");
        ren.put("火法伤害", "火法伤害");
        ren.put("风法伤害", "风法伤害");
        ren.put("仙法狂暴", "仙法狂暴");
        ren.put("仙法狂暴几率", "仙法狂暴几率");
        ren.put("仙法狂暴程度", "仙法狂暴程度");
        ren.put("忽视仙法", "忽视仙法");
        ren.put("加强鬼火", "加强鬼火");
        ren.put("忽视鬼火", "忽视鬼火");
        ren.put("忽视抗鬼火", "忽视抗鬼火");
        ren.put("鬼火伤害", "鬼火伤害");
        ren.put("鬼火狂暴几率", "鬼火狂暴几率");
        ren.put("鬼火狂暴程度", "鬼火狂暴程度");
        ren.put("加强遗忘", "加强遗忘");
        ren.put("三尸虫狂暴几率", "三尸虫狂暴几率");
        ren.put("三尸虫狂暴程度", "三尸虫狂暴程度");
        ren.put("强三尸虫", "强三尸虫");
        ren.put("强三尸回虫血程度", "强三尸回虫血程度");
        ren.put("仙法连击率", "仙法连击率");
        ren.put("仙法连击次数", "仙法连击次数");
        ren.put("鬼火连击率", "鬼火连击率");
        ren.put("鬼火连击次数", "鬼火连击次数");
        ren.put("加强攻击法术", "加强攻击法术");
        ren.put("加强防御法术", "加强防御法术");
        ren.put("加强速度法术", "加强速度法术");
        ren.put("加强霹雳效果", "加强霹雳效果");
        ren.put("加强扶摇效果", "加强扶摇效果");
        ren.put("加强沧波效果", "加强沧波效果");
        ren.put("加强甘霖回血值", "加强甘霖回血值");
        ren.put("甘霖回血程度", "甘霖回血程度");
        ren.put("加强震慑", "加强震慑");
        ren.put("忽视抗震慑", "忽视抗震慑");
        ren.put("致命率", "致命率");
        ren.put("狂暴率", "狂暴率");
        ren.put("连击率", "连击率");
        ren.put("反击率", "反击率");
        ren.put("命中率", "命中率");
        ren.put("连击次数", "连击次数");
        ren.put("反击次数", "反击次数");
        ren.put("忽视防御几率", "忽视防御几率");
        ren.put("忽视防御程度", "忽视防御程度");
        //魔
        mo.put("忽视抗水", "忽视抗水");
        mo.put("忽视抗雷", "忽视抗雷");
        mo.put("忽视抗火", "忽视抗火");
        mo.put("忽视抗风", "忽视抗风");
        mo.put("加强水", "加强水");
        mo.put("加强雷", "加强雷");
        mo.put("加强火", "加强火");
        mo.put("加强风", "加强风");
        mo.put("水法狂暴几率", "水法狂暴几率");
        mo.put("雷法狂暴几率", "雷法狂暴几率");
        mo.put("火法狂暴几率", "火法狂暴几率");
        mo.put("风法狂暴几率", "风法狂暴几率");
        mo.put("水法狂暴程度", "水法狂暴程度");
        mo.put("雷法狂暴程度", "雷法狂暴程度");
        mo.put("火法狂暴程度", "火法狂暴程度");
        mo.put("风法狂暴程度", "风法狂暴程度");
        mo.put("水法伤害", "水法伤害");
        mo.put("雷法伤害", "雷法伤害");
        mo.put("火法伤害", "火法伤害");
        mo.put("风法伤害", "风法伤害");
        mo.put("仙法狂暴", "仙法狂暴");
        mo.put("仙法狂暴几率", "仙法狂暴几率");
        mo.put("仙法狂暴程度", "仙法狂暴程度");
        mo.put("忽视仙法", "忽视仙法");
        mo.put("加强混乱", "加强混乱");
        mo.put("加强昏睡", "加强昏睡");
        mo.put("加强中毒", "加强中毒");
        mo.put("加强封印", "加强封印");
        mo.put("忽视抗混", "忽视抗混");
        mo.put("忽视抗混乱", "忽视抗混乱");
        mo.put("忽视抗睡", "忽视抗睡");
        mo.put("忽视抗昏睡", "忽视抗昏睡");
        mo.put("忽视抗毒", "忽视抗毒");
        mo.put("忽视抗中毒", "忽视抗中毒");
        mo.put("忽视抗封", "忽视抗封");
        mo.put("忽视抗封印", "忽视抗封印");
        mo.put("仙法连击率", "仙法连击率");
        mo.put("仙法连击次数", "仙法连击次数");
        mo.put("加强鬼火", "加强鬼火");
        mo.put("忽视鬼火", "忽视鬼火");
        mo.put("忽视抗鬼火", "忽视抗鬼火");
        mo.put("鬼火伤害", "鬼火伤害");
        mo.put("鬼火狂暴几率", "鬼火狂暴几率");
        mo.put("鬼火狂暴程度", "鬼火狂暴程度");
        mo.put("加强遗忘", "加强遗忘");
        mo.put("三尸虫狂暴几率", "三尸虫狂暴几率");
        mo.put("三尸虫狂暴程度", "三尸虫狂暴程度");
        mo.put("强三尸虫", "强三尸虫");
        mo.put("强三尸回虫血程度", "强三尸回虫血程度");
        mo.put("鬼火连击率", "鬼火连击率");
        mo.put("鬼火连击次数", "鬼火连击次数");
        mo.put("加强霹雳效果", "加强霹雳效果");
        mo.put("加强扶摇效果", "加强扶摇效果");
        mo.put("加强沧波效果", "加强沧波效果");
        mo.put("加强甘霖回血值", "加强甘霖回血值");
        mo.put("甘霖回血程度", "甘霖回血程度");
        //龙
        long_.put("忽视抗水", "忽视抗水");
        long_.put("忽视抗雷", "忽视抗雷");
        long_.put("忽视抗火", "忽视抗火");
        long_.put("忽视抗风", "忽视抗风");
        long_.put("加强水", "加强水");
        long_.put("加强雷", "加强雷");
        long_.put("加强火", "加强火");
        long_.put("加强风", "加强风");
        long_.put("水法狂暴几率", "水法狂暴几率");
        long_.put("雷法狂暴几率", "雷法狂暴几率");
        long_.put("火法狂暴几率", "火法狂暴几率");
        long_.put("风法狂暴几率", "风法狂暴几率");
        long_.put("水法狂暴程度", "水法狂暴程度");
        long_.put("雷法狂暴程度", "雷法狂暴程度");
        long_.put("火法狂暴程度", "火法狂暴程度");
        long_.put("风法狂暴程度", "风法狂暴程度");
        long_.put("水法伤害", "水法伤害");
        long_.put("雷法伤害", "雷法伤害");
        long_.put("火法伤害", "火法伤害");
        long_.put("风法伤害", "风法伤害");
        long_.put("仙法狂暴", "仙法狂暴");
        long_.put("仙法狂暴几率", "仙法狂暴几率");
        long_.put("仙法狂暴程度", "仙法狂暴程度");
        long_.put("忽视仙法", "忽视仙法");
        long_.put("加强混乱", "加强混乱");
        long_.put("加强昏睡", "加强昏睡");
        long_.put("加强中毒", "加强中毒");
        long_.put("加强封印", "加强封印");
        long_.put("忽视抗混", "忽视抗混");
        long_.put("忽视抗混乱", "忽视抗混乱");
        long_.put("忽视抗睡", "忽视抗睡");
        long_.put("忽视抗昏睡", "忽视抗昏睡");
        long_.put("忽视抗毒", "忽视抗毒");
        long_.put("忽视抗中毒", "忽视抗中毒");
        long_.put("忽视抗封", "忽视抗封");
        long_.put("忽视抗封印", "忽视抗封印");
        long_.put("仙法连击率", "仙法连击率");
        long_.put("仙法连击次数", "仙法连击次数");
        long_.put("加强攻击法术", "加强攻击法术");
        long_.put("加强防御法术", "加强防御法术");
        long_.put("加强速度法术", "加强速度法术");
        long_.put("加强鬼火", "加强鬼火");
        long_.put("忽视鬼火", "忽视鬼火");
        long_.put("忽视抗鬼火", "忽视抗鬼火");
        long_.put("鬼火伤害", "鬼火伤害");
        long_.put("鬼火狂暴几率", "鬼火狂暴几率");
        long_.put("鬼火狂暴程度", "鬼火狂暴程度");
        long_.put("加强遗忘", "加强遗忘");
        long_.put("三尸虫狂暴几率", "三尸虫狂暴几率");
        long_.put("三尸虫狂暴程度", "三尸虫狂暴程度");
        long_.put("强三尸虫", "强三尸虫");
        long_.put("强三尸回虫血程度", "强三尸回虫血程度");
        long_.put("鬼火连击率", "鬼火连击率");
        long_.put("鬼火连击次数", "鬼火连击次数");
        long_.put("加强震慑", "加强震慑");
        long_.put("忽视抗震慑", "忽视抗震慑");
        long_.put("致命率", "致命率");
        long_.put("狂暴率", "狂暴率");
        long_.put("连击率", "连击率");
        long_.put("反击率", "反击率");
        long_.put("命中率", "命中率");
        long_.put("连击次数", "连击次数");
        long_.put("反击次数", "反击次数");
        long_.put("忽视防御几率", "忽视防御几率");
        long_.put("忽视防御程度", "忽视防御程度");
        //鬼
        gui.put("忽视抗水", "忽视抗水");
        gui.put("忽视抗雷", "忽视抗雷");
        gui.put("忽视抗火", "忽视抗火");
        gui.put("忽视抗风", "忽视抗风");
        gui.put("加强水", "加强水");
        gui.put("加强雷", "加强雷");
        gui.put("加强火", "加强火");
        gui.put("加强风", "加强风");
        gui.put("水法狂暴几率", "水法狂暴几率");
        gui.put("雷法狂暴几率", "雷法狂暴几率");
        gui.put("火法狂暴几率", "火法狂暴几率");
        gui.put("风法狂暴几率", "风法狂暴几率");
        gui.put("水法狂暴程度", "水法狂暴程度");
        gui.put("雷法狂暴程度", "雷法狂暴程度");
        gui.put("火法狂暴程度", "火法狂暴程度");
        gui.put("风法狂暴程度", "风法狂暴程度");
        gui.put("水法伤害", "水法伤害");
        gui.put("雷法伤害", "雷法伤害");
        gui.put("火法伤害", "火法伤害");
        gui.put("风法伤害", "风法伤害");
        gui.put("仙法狂暴", "仙法狂暴");
        gui.put("仙法狂暴几率", "仙法狂暴几率");
        gui.put("仙法狂暴程度", "仙法狂暴程度");
        gui.put("忽视仙法", "忽视仙法");
        gui.put("加强混乱", "加强混乱");
        gui.put("加强昏睡", "加强昏睡");
        gui.put("加强中毒", "加强中毒");
        gui.put("加强封印", "加强封印");
        gui.put("忽视抗混", "忽视抗混");
        gui.put("忽视抗混乱", "忽视抗混乱");
        gui.put("忽视抗睡", "忽视抗睡");
        gui.put("忽视抗昏睡", "忽视抗昏睡");
        gui.put("忽视抗毒", "忽视抗毒");
        gui.put("忽视抗中毒", "忽视抗中毒");
        gui.put("忽视抗封", "忽视抗封");
        gui.put("忽视抗封印", "忽视抗封印");
        gui.put("仙法连击率", "仙法连击率");
        gui.put("仙法连击次数", "仙法连击次数");
        gui.put("加强攻击法术", "加强攻击法术");
        gui.put("加强防御法术", "加强防御法术");
        gui.put("加强速度法术", "加强速度法术");
        gui.put("加强霹雳效果", "加强霹雳效果");
        gui.put("加强扶摇效果", "加强扶摇效果");
        gui.put("加强沧波效果", "加强沧波效果");
        gui.put("加强甘霖回血值", "加强甘霖回血值");
        gui.put("甘霖回血程度", "甘霖回血程度");
        gui.put("加强震慑", "加强震慑");
        gui.put("忽视抗震慑", "忽视抗震慑");
        gui.put("致命率", "致命率");
        gui.put("狂暴率", "狂暴率");
        gui.put("连击率", "连击率");
        gui.put("反击率", "反击率");
        gui.put("命中率", "命中率");
        gui.put("连击次数", "连击次数");
        gui.put("反击次数", "反击次数");
        gui.put("忽视防御几率", "忽视防御几率");
        gui.put("忽视防御程度", "忽视防御程度");
        races.put("仙", xian);
        races.put("魔", mo);
        races.put("人", ren);
        races.put("鬼", gui);
        races.put("龙", long_);

    }
    /**
     * @param
     * @throws Exception
     */
    public static void testReflect1(Object model) throws Exception {
        String raceSting = Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id());
        Map<String, String> stringStringMap = races.get(raceSting);
        clearShuXingView();
        int a = 0;
        for (java.lang.reflect.Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (Double.parseDouble(field.get(model).toString()) != 0) {
                String sx = null;
                sx = getQuaralyPersonalName(field.getName());
                String s = stringStringMap.get(sx.split("\\|")[0]);
                if (StringUtils.isNotBlank(s) && sx.startsWith(s)) {
                    a++;
                    continue;
                }
                if (sx != null && sx.length() > 2) {
                    mianBanDevide(sx.substring(sx.length() - 1, sx.length()), sx.substring(0, sx.length() - 2), field
                            .get(model).toString(), a);
                    a++;
                }
            }
        }
        changViewSize();
    }
}
