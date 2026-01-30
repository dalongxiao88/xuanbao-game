package org.come.control;

import java.util.ArrayList;
import org.come.Jpanel.PetSkillsJpanel;
import org.come.bean.Skill;
import org.come.entity.RoleSummoning;
import javax.swing.ImageIcon;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.PetSkillsJframe;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.npc.PetConversion;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import org.come.action.NpcMenuAction;

public class DelSkillMenuControl implements NpcMenuAction
{
    public static List<String> skills;
    
    @Override
    public void menuControl(String type) {
        if (StringUtils.isNotEmpty(type)) {
            RoleSummoning pet = PetConversion.getPet();
            String[] petskill;
            for (String s : petskill = pet.getPetSkills().split("\\|")) {
                if (s.equals("3034") || s.equals("3040")) {
                    ZhuFrame.getZhuJpanel().addPrompt("召唤兽身上已有问号技能，无法重修");
                    return;
                }
            }
            int num = DelSkillMenuControl.skills.size();
            BigDecimal money = BigDecimal.valueOf(Numberskill(num));
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(money) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("金币不足" + money + "两!");
                return;
            }
            Skill skill = UserMessUntil.getSkillId("3040");
            PetSkillsJpanel petSkillsJpanel = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
            String sendmes = Agreement.getAgreement().userpetAgreement("CX|" + pet.getSid() + "|" + this.getSkillid(type) + "|" + num);
            SendMessageUntil.toServer(sendmes);
            petSkillsJpanel.setPetskillID("");
            petSkillsJpanel.getLabPetskills()[petSkillsJpanel.getPetskillNO()].setIcon(null);
            petSkillsJpanel.getShowPetSkills()[petSkillsJpanel.getPetskillNO()].setSkill(null);
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
            ImageIcon icon = new ImageIcon("img/fighting-skill/wxs_" + skill.getSkillid() + ".png");
            PetSkillsJpanel petSkillsJpanelx = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
            String sendmesx = Agreement.getAgreement().userpetAgreement("TJJ|" + pet.getSid() + "|" + 3040);
            SendMessageUntil.toServer(sendmesx);
            petSkillsJpanelx.getShowPetSkills()[petSkillsJpanelx.getPetskillNO()].setSkill(skill);
            petSkillsJpanelx.getLabPetskills()[petSkillsJpanelx.getPetskillNO()].setIcon(icon);
        }
    }
    
    public String getSkillid(String name) {
        int n = -1;
        switch (name.hashCode()) {
            case 1834347239: {
                if (name.equals("终极技能：绝境逢生")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1573970603: {
                if (name.equals("终极技能：子虚乌有")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 1644981222: {
                if (name.equals("终极技能：春回大地")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case -288938898: {
                if (name.equals("终极技能：化无")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1488682733: {
                if (name.equals("终极技能：作鸟兽散")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case -288866535: {
                if (name.equals("终极技能：将死")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 1645791413: {
                if (name.equals("终极技能：明察秋毫")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 1514651316: {
                if (name.equals("终极技能：双管齐下")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 1593664752: {
                if (name.equals("终极技能：当头棒喝")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1474866809: {
                if (name.equals("终极技能：以牙还牙")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 1649179940: {
                if (name.equals("终极技能：春暖花开")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 1563572423: {
                if (name.equals("终极技能：夺魂索命")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "1606";
            }
            case 1: {
                return "1607";
            }
            case 2: {
                return "1608";
            }
            case 3: {
                return "1828";
            }
            case 4: {
                return "1829";
            }
            case 5: {
                return "1830";
            }
            case 6: {
                return "1840";
            }
            case 7: {
                return "1841";
            }
            case 8: {
                return "1842";
            }
            case 9: {
                return "1867";
            }
            case 10: {
                return "1868";
            }
            case 11: {
                return "1869";
            }
            default: {
                return "";
            }
        }
    }
    
    public static long Numberskill(int num) {
        switch (num) {
            case 1: {
                return 500000000L;
            }
            case 2: {
                return 2000000000L;
            }
            case 3: {
                return 5000000000L;
            }
            case 4: {
                return 10000000000L;
            }
            case 5: {
                return 20000000000L;
            }
            case 6: {
                return 20000000000L;
            }
            case 7: {
                return 20000000000L;
            }
            case 8: {
                return 20000000000L;
            }
            default: {
                return 0L;
            }
        }
    }
    
    static {
        DelSkillMenuControl.skills = new ArrayList<>();
    }
}
