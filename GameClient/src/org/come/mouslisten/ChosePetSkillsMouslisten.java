package org.come.mouslisten;

import org.come.entity.RoleSummoning;
import org.come.Jpanel.PetSkillsJpanel;
import javax.swing.ImageIcon;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import org.come.Frame.PetSkillsJframe;

public class ChosePetSkillsMouslisten
{
    public static void refreshPetSkills() {
        PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().refuPetSkillsJpanel();
        PetSkillsJpanel petSkillsJpanel = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
        int Skillwl1 = 0;
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getPetSkills() != null && !UserMessUntil.getChosePetMes().getPetSkills().equals("")) {
            String[] petnaturalskill = UserMessUntil.getChosePetMes().getPetSkills().split("\\|");
            for (int i = 0; i < 8; ++i) {
                petSkillsJpanel.getLabPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getLabPetskills()[i].setIcon(img);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(skill);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                if (UserMessUntil.getChosePetMes().getPetSkillswl() != null && !UserMessUntil.getChosePetMes().getPetSkillswl().equals("")) {
                    String[] petskillswl = UserMessUntil.getChosePetMes().getPetSkillswl().split("\\|");
                    for (int i2 = 0; i2 < petskillswl.length; ++i2) {
                        String[] level = petskillswl[i2].split("=");
                        if (petnaturalskill[i].equals(level[0])) {
                            Skillwl1 = Integer.parseInt(level[1]);
                        }
                    }
                }
                petSkillsJpanel.getShowPetSkills()[i].setSkillwl(Skillwl1);
                if (Skillwl1 == 0) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(img);
                }
                else {
                    ImageIcon img2 = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + "x.png");
                    img2.setImage(img2.getImage().getScaledInstance(52, 52, 10));
                    petSkillsJpanel.getLabPetskills()[i].setIcon(img2);
                }
                petSkillsJpanel.getShowPetSkills()[i].setSkill(skill);
                Skillwl1 = 0;
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
            if ((int)UserMessUntil.getChosePetMes().getFoPenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getFoPenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/115.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
        }
        else {
            for (int i = 0; i < 8; ++i) {
                petSkillsJpanel.getLabPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
            if ((int)UserMessUntil.getChosePetMes().getFoPenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getFoPenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/115.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
        }
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getPetQlSkills() != null && !UserMessUntil.getChosePetMes().getPetQlSkills().equals("")) {
            String[] petnaturalskill = UserMessUntil.getChosePetMes().getPetQlSkills().split("\\|");
            for (int i = 0; i < 6; ++i) {
                petSkillsJpanel.getQlPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wljn_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getQlPetskills()[i].setIcon(img);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(skill);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenql() <= 5) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenql(); i < 6; ++i) {
                    petSkillsJpanel.getQlPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
                }
            }
        }
        else {
            for (int i = 0; i < 6; ++i) {
                petSkillsJpanel.getQlPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenql() <= 6) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenql(); i < 6; ++i) {
                    petSkillsJpanel.getQlPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/60_png.xy2uiimg.clip_wxi.png"));
                    petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
                }
            }
        }
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        Integer skllNum = Integer.valueOf(7);
        if (pet.getTurnRount() == 4) {
            skllNum = Integer.valueOf(9);
        }
        else {
            skllNum = Integer.valueOf(7);
        }
        if (UserMessUntil.getChosePetMes().getBeastSkills() != null && !UserMessUntil.getChosePetMes().getBeastSkills().equals("") && !UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
            if ((int)skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(null);
                    petSkillsJpanel.getShowPetSkills()[6].setSkill(null);
                    Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(UserMessUntil.getChosePetMes().getBeastSkills());
                    ImageIcon img = new ImageIcon("img/skill/wxs_" + UserMessUntil.getChosePetMes().getBeastSkills() + ".png");
                    img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                    petSkillsJpanel.getLabPetskills()[6].setIcon(img);
                    petSkillsJpanel.getShowPetSkills()[6].setSkill(skill);
                }
            }
            else if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                petSkillsJpanel.getLabPetskills()[8].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[8].setSkill(null);
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(UserMessUntil.getChosePetMes().getBeastSkills());
                ImageIcon img = new ImageIcon("img/skill/wxs_" + UserMessUntil.getChosePetMes().getBeastSkills() + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getLabPetskills()[8].setIcon(img);
                petSkillsJpanel.getShowPetSkills()[8].setSkill(skill);
            }
        }
        else if ((int)skllNum == 7) {
            if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                if (UserMessUntil.getChosePetMes().getBeastSkills() != null && UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(null);
                }
                else {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                }
                petSkillsJpanel.getShowPetSkills()[6].setSkill(null);
            }
        }
        else if ((int)skllNum == 9 && (pet.getSsn().equals("4") || pet.getSsn().equals("3"))) {
            if (UserMessUntil.getChosePetMes().getBeastSkills() != null && UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
                petSkillsJpanel.getLabPetskills()[8].setIcon(null);
            }
            else {
                petSkillsJpanel.getLabPetskills()[8].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
            }
            petSkillsJpanel.getShowPetSkills()[8].setSkill(null);
        }
        if (UserMessUntil.getChosePetMes().getSkill() != null && !UserMessUntil.getChosePetMes().getSkill().equals("")) {
            String[] petnaturalskill = UserMessUntil.getChosePetMes().getSkill().split("\\|");
            for (int i3 = 0; i3 < petSkillsJpanel.getLabPetskillsTS().length; ++i3) {
                try {
                    petSkillsJpanel.getLabPetskillsTS()[i3].setVisible(false);
                    petSkillsJpanel.getLabPetskillBacks()[i3].setVisible(false);
                }
                catch (Exception e) {}
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                if (petSkillsJpanel.skllNum == 7 || petSkillsJpanel.skllNum == 9) {
                    if (petSkillsJpanel.getLabPetskillBacks()[i] != null) {
                        petSkillsJpanel.getLabPetskillBacks()[i].setVisible(true);
                        Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                        ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                        img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                        petSkillsJpanel.getLabPetskillsTS()[i].setVisible(true);
                        petSkillsJpanel.getLabPetskillBacks()[i].setVisible(true);
                        petSkillsJpanel.getLabPetskillsTS()[i].setIcon(img);
                        petSkillsJpanel.getShowPetSkillsTS()[i].setSkill(skill);
                    }
                }
                else {
                    Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                    PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getModelNaturalskill().add(i, skill.getSkillname());
                }
            }
        }else {
            for (int i3 = 0; i3 < petSkillsJpanel.getLabPetskillsTS().length; ++i3) {
                try {
                    petSkillsJpanel.getLabPetskillsTS()[i3].setVisible(false);
                    petSkillsJpanel.getLabPetskillBacks()[i3].setVisible(false);
                }
                catch (Exception e) {}
            }
        }
    }
}
