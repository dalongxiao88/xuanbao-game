package org.come.npc;

import java.util.List;
import org.come.bean.PrivateData;
import org.come.bean.Skill;
import org.come.until.AnalysisString;
import java.util.ArrayList;
import com.tool.role.RoleData;
import com.tool.ModerateTask.Hero;
import org.come.Jpanel.RaceChangeMainJpanel;
import org.come.until.CutButtonImage;
import org.come.Frame.RaceChangeMainJframe;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.action.NpcMenuAction;

public class RoleConversion implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我想转换种族")) {
            this.roleTrans();
        }
        else if (type.equals("我已做好了转生准备")) {
            this.roleTurnaround();
        }
    }
    
    public void roleTrans() {
        if (ImageMixDeal.userimg.getRoleShow().getTroop_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt("您正在组队中，不能转换种族！！！");
            return;
        }
        if (FormsManagement.getframe(14).isVisible()) {
            ZhuFrame.getZhuJpanel().addPrompt("您正在交易中，不能转换种族！！！");
            return;
        }
        int number = 6;
        if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() > 102 && (int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 338) {
            number = 8;
        }
        else if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() > 338 && (int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 459) {
            number = 10;
        }
        else if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() > 459) {
            number = 12;
        }
        RaceChangeMainJpanel raceChangeMainJpanel = RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel();
        raceChangeMainJpanel.setNumber(number);
        for (int i = 0; i < raceChangeMainJpanel.getSexBtn().length; ++i) {
            raceChangeMainJpanel.getSexBtn()[i].setIcon((i == 0) ? CutButtonImage.getImage("inkImg/button/101.png", 7, 7) : null);
        }
        raceChangeMainJpanel.reloadRace(raceChangeMainJpanel.getRoleType(), raceChangeMainJpanel.getNumber());
        raceChangeMainJpanel.setLeixing(0);
        raceChangeMainJpanel.setSpecies_id(null);
        FormsManagement.showForm(41);
    }
    
    public void roleTurnaround() {
        if (ImageMixDeal.userimg.getRoleShow().getTroop_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt("您正在组队中，不能转生！！！");
            return;
        }
        if (FormsManagement.getframe(14).isVisible()) {
            ZhuFrame.getZhuJpanel().addPrompt("您正在交易中，不能转生！！！");
            return;
        }
        if (Hero.getHero().getDoingTasks().size() != 0) {
            ZhuFrame.getZhuJpanel().addPrompt("你还有未完成的任务，不可转生！");
            return;
        }
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String[] vs = data.getSkill("S");
        List<Skill> skills_no = new ArrayList<>();
        if (vs != null && vs.length >= 15) {
            for (int i = 0; i < vs.length; ++i) {
                String[] vs2 = vs[i].split("_");
                int usersld = Integer.parseInt(vs2[1]);
                int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade(), Integer.valueOf(1));
                if (sld > usersld) {
                    Skill skill = new Skill();
                    skill.setSkillid(vs2[0]);
                    skill.setValue(vs2[1]);
                    skills_no.add(skill);
                }
            }
            if (skills_no.size() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("技能熟练度未满，不能转生!");
                return;
            }
            boolean bool = false;
            int number = 8;
            if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 102) {
                if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() == 102) {
                    bool = true;
                    number = 8;
                }
            }
            else if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 210) {
                if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() == 210) {
                    bool = true;
                    number = 8;
                }
            }
            else if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 338) {
                if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() == 338) {
                    bool = true;
                    number = 10;
                }
            }
            else if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() <= 459 && (int)ImageMixDeal.userimg.getRoleShow().getGrade() == 459) {
                bool = true;
                number = 12;
            }
            if (bool) {
                RaceChangeMainJpanel raceChangeMainJpanel = RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel();
                raceChangeMainJpanel.setNumber(number);
                for (int j = 0; j < raceChangeMainJpanel.getSexBtn().length; ++j) {
                    raceChangeMainJpanel.getSexBtn()[j].setIcon((j == 0) ? CutButtonImage.getImage("inkImg/button/101.png", 7, 7) : null);
                }
                raceChangeMainJpanel.reloadRace(raceChangeMainJpanel.getRoleType(), number);
                raceChangeMainJpanel.setLeixing(1);
                raceChangeMainJpanel.setSpecies_id(null);
                FormsManagement.showForm(41);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("你还不具备转生的条件！");
            }
            return;
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("有未学习的技能并且技能熟练度未满，不能转生!");
            return;
        }
    }
}
