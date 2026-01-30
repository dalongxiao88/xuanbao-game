package org.skill.panel;

import org.come.bean.PrivateData;
import com.tool.role.SkillUtil;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import java.awt.LayoutManager;
import com.updateNew.MyIsif;
import javax.swing.JPanel;

public class SkillSMGatePanel extends JPanel
{
    private SkillSMPanel[] skillSMPanels;
    
    public SkillSMGatePanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(49, 78, 600, 300);
        }
        else {
            this.setBounds(23, 78, 523, 309);
        }
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        this.skillSMPanels = new SkillSMPanel[3];
        for (int i = 0; i < this.skillSMPanels.length; ++i) {
            this.add(this.skillSMPanels[i] = new SkillSMPanel(i));
        }
    }
    
    public void getRaceSkillPanel(BigDecimal raceId, String sex) {
        if (raceId.compareTo(new BigDecimal("10001")) == 0) {
            if (sexisMan(sex)) {
                String skillName = "冰|睡|混";
                this.changeSkillBar(skillName);
            }
            else {
                String skillName = "冰|睡|毒";
                this.changeSkillBar(skillName);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10002")) == 0) {
            if (sexisMan(sex)) {
                String skillName = "慑|牛|速";
                this.changeSkillBar(skillName);
            }
            else {
                String skillName = "慑|牛|盘";
                this.changeSkillBar(skillName);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10003")) == 0) {
            if (sexisMan(sex)) {
                String skillName = "雷|水|风";
                this.changeSkillBar(skillName);
            }
            else {
                String skillName = "雷|水|火";
                this.changeSkillBar(skillName);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10004")) == 0) {
            if (sexisMan(sex)) {
                String skillName = "忘|冥|蛊";
                this.changeSkillBar(skillName);
            }
            else {
                String skillName = "忘|冥|魅";
                this.changeSkillBar(skillName);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10005")) == 0) {
            if (sexisMan(sex)) {
                String skillName = "霹|霖|摇";
                this.changeSkillBar(skillName);
            }
            else {
                String skillName = "霹|霖|沧";
                this.changeSkillBar(skillName);
            }
        }
    }
    
    public static boolean sexisMan(String sex) {
        return "男".equals(sex);
    }
    
    public void changeSkillBar(String skillName) {
        String[] skillNames = skillName.split("\\|");
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String[] skills = data.getSkill("S");
        int lvltrue = AnalysisString.lvltrue((int)RoleData.getRoleData().getLoginResult().getGrade());
        for (int i = 0; i < skillNames.length; ++i) {
            this.skillSMPanels[i].getSkillTitle().setText(skillNames[i]);
            this.skillSMPanels[i].getSkillsName(SkillUtil.getSkillsAll(skillNames[i]), lvltrue, skills);
        }
    }
}
