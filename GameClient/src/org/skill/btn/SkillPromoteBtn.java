package org.skill.btn;

import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.AnalysisString;
import com.tool.role.GetExp;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.skill.panel.SkillPromoteMainPanel;
import java.math.BigDecimal;
import com.tool.btn.MoBanBtn;

public class SkillPromoteBtn extends MoBanBtn
{
    private int typeBtn;
    private static BigDecimal bigZero;
    private SkillPromoteMainPanel skillPromoteMainPanel;
    
    public SkillPromoteBtn(String iconpath, int type, Color[] colors, String text, int typeBtn, SkillPromoteMainPanel skillPromoteMainPanel) {
        super(iconpath, type, colors);
        this.typeBtn = typeBtn;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.skillPromoteMainPanel = skillPromoteMainPanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.typeBtn) {
            case 1: {
                this.promotePoint();
                break;
            }
            case 2: {
                this.onekeyPromote();
                break;
            }
        }
    }
    
    public void promotePoint() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        BigDecimal experience = loginResult.getExperience();
        BigDecimal gold = loginResult.getGold();
        if (experience.compareTo(this.skillPromoteMainPanel.getNeedExperienceNum()) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前经验不足");
            return;
        }
        if (gold.compareTo(this.skillPromoteMainPanel.getNeedMoneyNum()) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前大话币不足");
            return;
        }
        int parseInt = loginResult.getExtraPointInt("T");
        int tsp = GetExp.getTSP(parseInt);
        int tsx = GetExp.getTSX(parseInt);
        int tsExp = GetExp.getTSExp(tsp + 1);
        String lvl = AnalysisString.lvl((int)loginResult.getGrade());
        int realmNow = SkillTYCBtn.realmNow((int)loginResult.getGrade());
        int realmMaxTSP = SkillTYCBtn.realmMaxTSP(realmNow);
        String raceConfirm = SkillTYCBtn.raceConfirm(loginResult.getRace_id(), realmNow);
        if (tsp >= realmMaxTSP) {
            ZhuFrame.getZhuJpanel().addPrompt2("已兑换至当前境界上限");
            return;
        }
        experience = experience.subtract(this.skillPromoteMainPanel.getNeedExperienceNum());
        gold = gold.subtract(this.skillPromoteMainPanel.getNeedMoneyNum());
        loginResult.setExperience(experience);
        loginResult.setGold(gold);
        loginResult.setExtraPoint("T", 1);
        String mes = Agreement.getAgreement().rolechangeAgreement("T1");
        SendMessageUntil.toServer(mes);
        ZhuFrame.getZhuJpanel().addPrompt2("当前修炼点加1");
        parseInt = loginResult.getExtraPointInt("T");
        tsp = GetExp.getTSP(parseInt);
        tsx = GetExp.getTSX(parseInt);
        tsExp = GetExp.getTSExp(tsp + 1);
        this.skillPromoteMainPanel.panelGetData(experience, tsx, tsExp, lvl, raceConfirm, tsp, realmMaxTSP);
    }
    
    public void onekeyPromote() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        BigDecimal experience = loginResult.getExperience();
        BigDecimal gold = loginResult.getGold();
        int parseInt = loginResult.getExtraPointInt("T");
        int tsp = GetExp.getTSP(parseInt);
        int tsx = GetExp.getTSX(parseInt);
        int tsExp = GetExp.getTSExp(tsp + 1);
        int tsD = tsExp - tsx;
        String lvl = AnalysisString.lvl((int)loginResult.getGrade());
        int realmNow = SkillTYCBtn.realmNow((int)loginResult.getGrade());
        int realmMaxTSP = SkillTYCBtn.realmMaxTSP(realmNow);
        if (tsp >= realmMaxTSP) {
            ZhuFrame.getZhuJpanel().addPrompt2("已兑换至当前境界上限");
            return;
        }
        if (tsD <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("已兑换至当前境界上限");
            return;
        }
        BigDecimal expDiv = experience.divide(this.skillPromoteMainPanel.getNeedExperienceNum(), 0, 1);
        BigDecimal goldDiv = gold.divide(this.skillPromoteMainPanel.getNeedMoneyNum(), 0, 1);
        switch (expDiv.compareTo(goldDiv)) {
            case -1: {
                if (expDiv.compareTo(SkillPromoteBtn.bigZero) <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前经验不足");
                    return;
                }
                this.onekeyDiff(loginResult, gold, experience, expDiv, tsD);
                break;
            }
            case 0: {
                if (goldDiv.compareTo(SkillPromoteBtn.bigZero) <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前大话币和经验都不足");
                    return;
                }
                this.onekeyDiff(loginResult, gold, experience, expDiv, tsD);
                break;
            }
            case 1: {
                if (goldDiv.compareTo(SkillPromoteBtn.bigZero) <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前大话币不足");
                    return;
                }
                this.onekeyDiff(loginResult, gold, experience, goldDiv, tsD);
                break;
            }
        }
    }
    
    public void onekeyDiff(LoginResult loginResult, BigDecimal gold, BigDecimal experience, BigDecimal value, int tsD) {
        int num = (tsD >= value.intValue()) ? value.intValue() : tsD;
        gold = gold.subtract(new BigDecimal(num).multiply(this.skillPromoteMainPanel.getNeedMoneyNum()));
        experience = experience.subtract(new BigDecimal(num).multiply(this.skillPromoteMainPanel.getNeedExperienceNum()));
        loginResult.setExperience(experience);
        loginResult.setGold(gold);
        loginResult.setExtraPoint("T", num);
        String mes = Agreement.getAgreement().rolechangeAgreement("T" + num);
        SendMessageUntil.toServer(mes);
        ZhuFrame.getZhuJpanel().addPrompt2("当前修炼点加" + num);
        int parseInt = loginResult.getExtraPointInt("T");
        int tsp = GetExp.getTSP(parseInt);
        int tsx = GetExp.getTSX(parseInt);
        int tsExp = GetExp.getTSExp(tsp + 1);
        String lvl = AnalysisString.lvl((int)loginResult.getGrade());
        int realmNow = SkillTYCBtn.realmNow((int)loginResult.getGrade());
        int realmMaxTSP = SkillTYCBtn.realmMaxTSP(realmNow);
        String raceConfirm = SkillTYCBtn.raceConfirm(loginResult.getRace_id(), realmNow);
        this.skillPromoteMainPanel.panelGetData(experience, tsx, tsExp, lvl, raceConfirm, tsp, realmMaxTSP);
    }
    
    static {
        SkillPromoteBtn.bigZero = new BigDecimal(0);
    }
}
