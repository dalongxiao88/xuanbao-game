package org.skill.btn;

import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import org.come.bean.LoginResult;
import org.come.Frame.ZhuFrame;
import org.skill.frame.SkillMainFrame;
import org.come.npc.YJSkillLearn;
import com.tool.role.SkillUtil;
import com.tool.image.ImageMixDeal;
import org.come.until.FormsManagement;
import org.skill.frame.SkillPromoteMainFrame;
import org.come.until.AnalysisString;
import com.tool.role.GetExp;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.skill.panel.CopyOfSkillTYCPanel;
import com.tool.btn.MoBanBtn;

public class SkillTYCBtn extends MoBanBtn
{
    private int typeBtn;
    private CopyOfSkillTYCPanel skillTYCPanel;
    
    public SkillTYCBtn(String iconpath, int type, Color[] colors, String text, Font font, int typeBtn, CopyOfSkillTYCPanel skillTYCPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.typeBtn = typeBtn;
        this.skillTYCPanel = skillTYCPanel;
    }
    
    public SkillTYCBtn(String iconpath, int type, Color[] colors, Font font, String text, int typeBtn) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.typeBtn = typeBtn;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.typeBtn == 0) {
            if (this.skillTYCPanel.typeBtn == 1) {
                this.skillTYCPanel.getTYCselect().setVisible(true);
            }
        }
        else if (this.typeBtn == 1) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int parseInt = loginResult.getExtraPointInt("T");
            BigDecimal experience = loginResult.getExperience();
            int tsp = GetExp.getTSP(parseInt);
            int tsx = GetExp.getTSX(parseInt);
            int tsExp = GetExp.getTSExp(tsp + 1);
            String lvl = AnalysisString.lvl((int)loginResult.getGrade());
            int realmNow = realmNow((int)loginResult.getGrade());
            int realmMaxTSP = realmMaxTSP(realmNow);
            String raceConfirm = raceConfirm(loginResult.getRace_id(), realmNow);
            SkillPromoteMainFrame.getSkillPromoteMainFrame().getSkillPromoteMainPanel().panelGetData(experience, tsx, tsExp, lvl, raceConfirm, tsp, realmMaxTSP);
            FormsManagement.showForm(83);
        }
        else if (this.typeBtn == 2) {
            this.skillTYCPanel.washPoint();
        }
        else if (this.typeBtn == 3) {
            this.skillTYCPanel.ChangeBtnPanel((this.skillTYCPanel.typeBtn == 0) ? 1 : 0, ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            this.skillTYCPanel.names = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            this.skillTYCPanel.Washing(this.skillTYCPanel.typeBtn);
        }
        else if (this.typeBtn == 4) {
            if (this.skillTYCPanel.typeBtn == 0) {
                this.skillTYCPanel.addPoint();
            }
        }
        else if (this.typeBtn == 5) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            List<String> raceSkills = SkillUtil.getDetailedSkills(SkillUtil.getSepciesN(loginResult.getSpecies_id()));
            for (String skill : raceSkills) {
                YJSkillLearn.YJSkill(skill);
            }
            SkillMainFrame.getSkillMainFrame().getSkillMainPanel().changeBtnPanel(0);
            ZhuFrame.getZhuJpanel().addPrompt("#R已学会所有技能");
        }
    }
    
    public static int realmNow(int level) {
        if (level >= 437) {
            return 4;
        }
        if (level >= 417) {
            return 3;
        }
        if (level >= 335) {
            return 2;
        }
        if (level >= 315) {
            return 1;
        }
        return 0;
    }
    
    public static String raceConfirm(BigDecimal raceId, int realmNow) {
        String string = raceId.toString();
        int n = -1;
        switch (string.hashCode()) {
            case 46730162: {
                if (string.equals("10001")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730163: {
                if (string.equals("10002")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730165: {
                if (string.equals("10004")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730164: {
                if (string.equals("10003")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return realmConfirm("旋照期|开光期|融合期|灵寂期", realmNow);
            }
            case 1: {
                return realmConfirm("魔光期|凝元期|结丹期|离婴期", realmNow);
            }
            case 2: {
                return realmConfirm("炼魂期|混沌期|空冥期|神游期", realmNow);
            }
            case 3: {
                return realmConfirm("地仙期|天仙期|玄仙期|金仙期", realmNow);
            }
            default: {
                return null;
            }
        }
    }
    
    public static String realmConfirm(String raceNmae, int realmNow) {
        if (realmNow <= 0) {
            return null;
        }
        return raceNmae.split("\\|")[realmNow - 1];
    }
    
    public static int realmMaxTSP(int realm) {
        switch (realm) {
            case 1: {
                return 15;
            }
            case 2: {
                return 25;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 120;
            }
            default: {
                return 0;
            }
        }
    }
}
