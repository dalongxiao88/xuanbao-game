package org.skill.btn;

import org.come.until.MessagrFlagUntil;
import org.skill.panel.FaMenXlPanel;
import org.come.bean.LoginResult;
import org.come.until.NpcMenuUntil;
import org.come.action.MapAction;
import org.come.action.NpcMenuAction;
import org.come.until.SendRoleAndRolesummingUntil;
import java.math.BigDecimal;
import org.skill.frame.SkillMainFrame;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.skill.frame.FaMenXlFrame;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.skill.panel.SkillSMGatePanel2;
import com.tool.btn.MoBanBtn;

public class SkillTYCBtn5 extends MoBanBtn
{
    private Integer typeBtn;
    private SkillSMGatePanel2 skillTYCPanel;
    private Integer caozuo;
    SkillSMGatePanel2.FaMenItemView faMenItemView;
    
    public SkillTYCBtn5(String iconpath, int type, Color[] colors, String text, Font font, int typeBtn, SkillSMGatePanel2 skillTYCPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.typeBtn = Integer.valueOf(typeBtn);
        this.skillTYCPanel = skillTYCPanel;
    }
    
    public SkillTYCBtn5(String iconpath, int type, Color[] colors, String text, Font font, int typeBtn, Object o) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.typeBtn = Integer.valueOf(typeBtn);
    }
    
    public SkillTYCBtn5(String iconpath, int type, Color[] colors, String text, int caozuo, SkillSMGatePanel2.FaMenItemView faMenItemView) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = Integer.valueOf(caozuo);
        this.faMenItemView = faMenItemView;
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public SkillTYCBtn5(String iconpath, int type, Color[] colors, String text, int caozuo, Object o) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = Integer.valueOf(caozuo);
        this.setFont(UIUtils.TEXT_FONT82);
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
        if (this.caozuo != null && (int)this.caozuo == 1000) {
            BigDecimal gold = RoleData.getRoleData().getLoginResult().getGold();
            if (gold.longValue() < 100000000L) {
                ZhuFrame.getZhuJpanel().addPrompt("少侠，你的银两不够呀#17");
                return;
            }
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Fmcz, null, "#W您确定要花费:#R1亿#W银两重置心法? (#R修炼进度会保留#W)");
            return;
        }
        else if (this.typeBtn != null && ((int)this.typeBtn == 55 || (int)this.typeBtn == 56)) {
            Integer type1 = FaMenXlFrame.getInstance().getFaMenXlPanel().getType();
            Long exp = Long.valueOf(FaMenXlFrame.getInstance().getFaMenXlPanel().getExp().longValue());
            Long money = Long.valueOf(FaMenXlFrame.getInstance().getFaMenXlPanel().getMoney().longValue());
            int l = ((int)this.typeBtn == 55) ? 1 : 2;
            BigDecimal s1 = RoleData.getRoleData().getLoginResult().getScoretype("法门1");
            BigDecimal s2 = RoleData.getRoleData().getLoginResult().getScoretype("法门2");
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            BigDecimal maxSld = getRoleGradeMaxFMsld((int)loginResult.getGrade());
            if ((int)type1 == 998) {
                BigDecimal f1 = RoleData.getRoleData().getLoginResult().getScoretype("法门1");
                String mes = Agreement.getAgreement().rolechangeAgreement("FM=1=" + l);
                SendMessageUntil.toServer(mes);
            }
            else if ((int)type1 == 999) {
                BigDecimal f1 = RoleData.getRoleData().getLoginResult().getScoretype("法门2");
                String mes = Agreement.getAgreement().rolechangeAgreement("FM=2=" + l);
                SendMessageUntil.toServer(mes);
            }
            return;
        }
        else {
            if (this.caozuo != null && ((int)this.caozuo == 998 || (int)this.caozuo == 999)) {
                FormsManagement.showForm(6022);
                FaMenXlPanel faMenXlPanel = FaMenXlFrame.getInstance().getFaMenXlPanel();
                faMenXlPanel.refresh(this.faMenItemView, this.caozuo);
                return;
            }
            String text = this.getText();
            if (text.equals("选定")) {
                if (!AnalysisString.lvlfull((int)ImageMixDeal.userimg.getRoleShow().getGrade(), "3转181")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("飞升后才能学习法门");
                    return;
                }
                LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
                BigDecimal s3 = RoleData.getRoleData().getLoginResult().getScoretype("法门1");
                BigDecimal s4 = RoleData.getRoleData().getLoginResult().getScoretype("法门2");
                BigDecimal maxSld2 = getRoleGradeMaxFMsld((int)loginResult2.getGrade());
                for (int i = 0; i < SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getFaMenItemViews().length; ++i) {
                    SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getjLabels()[i].setVisible(true);
                    SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getSkillTYCBtn5s()[i].setVisible(false);
                }
                this.setVisible(false);
                this.skillTYCPanel.getjLabels()[(int)this.typeBtn].setVisible(false);
                SkillSMGatePanel2.FaMenItemView faMenItemView = this.skillTYCPanel.getFaMenItemViews()[(int)this.typeBtn];
                faMenItemView.setBackImg(this.skillTYCPanel.icon1, Boolean.valueOf(false));
                faMenItemView.getSkillItemArt1().setVisible(true);
                faMenItemView.getSkillItemArt2().setVisible(true);
                faMenItemView.getSkillItemSld1().setVisible(true);
                faMenItemView.getSkillItemSld2().setVisible(true);
                faMenItemView.getXl1().setVisible(true);
                faMenItemView.getXl2().setVisible(true);
                BigDecimal v = new BigDecimal(100.0 / (double)maxSld2.intValue());
                int j = s3.multiply(v).intValue();
                int i2 = s4.multiply(v).intValue();
                faMenItemView.getSkillItemArt1().setBounds(18, 190, j, 12);
                faMenItemView.getSkillItemArt2().setBounds(18, 272, i2, 12);
                faMenItemView.getSkillItemSld1().setText(s3 + "/" + maxSld2);
                faMenItemView.getSkillItemSld2().setText(s4 + "/" + maxSld2);
                BigDecimal scoretype = RoleData.getRoleData().getLoginResult().getScoretype("法门选定");
                if (scoretype.intValue() == 0) {
                    String mes2 = Agreement.getAgreement().rolechangeAgreement("XDFM=" + ((int)this.typeBtn + 1));
                    SendMessageUntil.toServer(mes2);
                    ZhuFrame.getZhuJpanel().addPrompt2("#Y你选定了#R" + faMenItemView.getTitle() + "#Y心法！");
                    BigDecimal raceId = loginResult2.getRace_id();
                    String sex = loginResult2.getSex();
                    if ((int)this.typeBtn == 0) {
                        if (raceId.compareTo(new BigDecimal("10001")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("清心静气");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("以静制动");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("清心静气");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("以静制动");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10002")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("幻影迷踪");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("心无旁骛");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("刚柔兼备");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("妙法莲华");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10003")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("凝神一击");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("一气呵成");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("凝神一击");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("一气呵成");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10004")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("法魂护体");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("血蛊佑身");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("刚柔兼备");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("妙法莲华");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10005")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("鱼龙潜跃");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("虎踞龙蟠");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("鱼龙潜跃");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("虎踞龙蟠");
                            }
                        }
                    }
                    else if ((int)this.typeBtn == 1) {
                        if (raceId.compareTo(new BigDecimal("10001")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("利刃加身");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("积羽沉舟");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("神力加身");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("云飞烟灭");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10002")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("力挽狂澜");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("披荆斩棘");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("势如破竹");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("暴虎冯河");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10003")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("无坚不摧");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("韬光养晦");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("无坚不摧");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("韬光养晦");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10004")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("无坚不摧");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("韬光养晦");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("无坚不摧");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("韬光养晦");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10005")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("神龙摆尾");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("积健为雄");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("神龙摆尾");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("积健为雄");
                            }
                        }
                    }
                    else if ((int)this.typeBtn == 2) {
                        if (raceId.compareTo(new BigDecimal("10001")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("神不守舍");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("扑朔迷离");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("神不守舍");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("扑朔迷离");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10002")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("兽魂俯首");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("困兽之斗");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("兽魂俯首");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("困兽之斗");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10003")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("气吞山河");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("催筋断骨");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("气吞山河");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("催筋断骨");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10004")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("气聚神凝");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("明镜止水");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("失魂落魄");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("人鬼殊途");
                            }
                        }
                        else if (raceId.compareTo(new BigDecimal("10005")) == 0) {
                            if (sexisMan(sex)) {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("行气如虹");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("浩气凌云");
                            }
                            else {
                                RoleData.getRoleData().getPrivateData().setSkills("F", null);
                                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("行气如虹");
                                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.FMSKILL)).menuControl("浩气凌云");
                            }
                        }
                    }
                }
            }
            return;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    public static boolean sexisMan(String sex) {
        return "男".equals(sex);
    }
    
    public static BigDecimal getRoleGradeMaxFMsld(int grade) {
        if (grade >= 500) {
            return new BigDecimal(10000);
        }
        if (grade >= 490) {
            return new BigDecimal(8000);
        }
        if (grade >= 480) {
            return new BigDecimal(6000);
        }
        if (grade >= 470) {
            return new BigDecimal(4000);
        }
        if (grade >= 460) {
            return new BigDecimal(2000);
        }
        return new BigDecimal(2000);
    }
}
