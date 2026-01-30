package org.come.good;

import java.util.List;
import org.come.bean.PrivateData;
import org.skill.frame.SkillMainFrame;
import org.come.until.UserMessUntil;
import java.util.Random;
import org.come.bean.Skill;
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleData;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.AnalysisString;
import org.come.entity.Goodstable;

public class Consumptions
{
    public static void Consumption(Goodstable goodstable, String value) {
        long[] xiaohao = AnalysisString.xiaohao(value);
        if (xiaohao[4] != 0L) {
            int use = (int)goodstable.getUsetime();
            skillProficiency(goodstable, (int)xiaohao[4]);
            if (use != (int)goodstable.getUsetime()) {
                goodstable.setUsetime(Integer.valueOf(use));
            }
            else {
                goodstable.goodxh(1);
                GoodsMouslisten.gooduse(goodstable, 1);
                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
            }
        }
    }
    
    public static boolean skillProficiency(Goodstable goodPlace, int addsld) {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade(), Integer.valueOf(1));
        String[] vs = data.getSkill("S");
        if (vs == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你还未学习技能");
            goodPlace.setUsetime(Integer.valueOf((int)goodPlace.getUsetime() + 1));
            return false;
        }
        boolean a = false;
        List<Skill> skills_no = new ArrayList<>();
        for (int i = 0; i < vs.length; ++i) {
            String[] vs2 = vs[i].split("_");
            Integer usersld = Integer.valueOf(Integer.parseInt(vs2[1]));
            if (sld > (int)usersld) {
                Skill skill = new Skill();
                skill.setSkillid(vs2[0]);
                skill.setValue(vs2[1]);
                skills_no.add(skill);
            }
        }
        if (skills_no.size() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("技能熟练度已满!");
            goodPlace.setUsetime(Integer.valueOf((int)goodPlace.getUsetime() + 1));
            return false;
        }
        int randomNum = new Random().nextInt(skills_no.size());
        Skill skill2 = (Skill)skills_no.get(randomNum);
        Skill skills = UserMessUntil.getSkillId(skill2.getSkillid());
        if (sld <= addsld) {
            addsld = sld - Integer.parseInt(skill2.getValue());
        }
        else {
            Integer skillGap = Integer.valueOf(sld - Integer.parseInt(skill2.getValue()));
            if ((int)skillGap < addsld) {
                addsld = (int)skillGap;
            }
        }
        String skills2 = data.getSkills();
        String splice = Splice(skills2.substring(1), skill2.getSkillid() + "_" + addsld, 2);
        data.setSkills("S", splice);
        SendRoleAndRolesummingUntil.sendRole(data);
        ZhuFrame.getZhuJpanel().addPrompt2("你的" + skills.getSkillname() + "获得" + addsld + "熟练度");
        SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel().getRaceSkillPanel(RoleData.getRoleData().getLoginResult().getRace_id(), RoleData.getRoleData().getLoginResult().getSex());
        return true;
    }
    
    public static String Splice(String v, String b, int type) {
        boolean s = true;
        boolean s2 = false;
        if (type == 2 || type == 3 || type == 5) {
            s2 = true;
        }
        List<String> jihe = new ArrayList<>();
        if (v == null) {
            v = "";
        }
        String[] vs = v.split("#");
        for (int i = 0; i < vs.length; ++i) {
            if (type == 0) {
                if (!vs[i].equals(b)) {
                    jihe.add(vs[i]);
                }
                else {
                    s = false;
                }
            }
            else {
                String[] vs2 = vs[i].split("_");
                String[] vs3 = b.split("_");
                if (vs2[0].equals(vs3[0])) {
                    if (type == 1) {
                        jihe.add(b);
                    }
                    else if (type == 2) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 += x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "_" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "_" + x1);
                        }
                    }
                    else if (type == 3) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 -= x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "_" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "_" + x1);
                        }
                    }
                    else if (type == 5) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        if (x2 > x1) {
                            x1 = x2;
                        }
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "_" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "_" + x1);
                        }
                    }
                }
                else {
                    jihe.add(vs[i]);
                }
            }
        }
        if (s && type == 0) {
            jihe.add(b);
        }
        if (s2) {
            jihe.add(b);
        }
        StringBuffer genggai = new StringBuffer();
        for (int j = 0; j < jihe.size(); ++j) {
            if (!genggai.toString().equals("")) {
                genggai.append("#" + (String)jihe.get(j));
            }
            else {
                genggai.append((String)jihe.get(j));
            }
        }
        return genggai.toString();
    }
    
    public static void addSkillProficiency(String SkillName, int addsld) {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
        String[] vs = data.getSkill("S");
        if (vs != null) {
            boolean a = false;
            for (int i = 0; i < vs.length; ++i) {
                String[] vs2 = vs[i].split("_");
                double usersld = Double.parseDouble(vs2[1]);
                if (usersld < (double)sld) {
                    Skill skill = UserMessUntil.getSkillId(vs2[0]);
                    if (skill != null && skill.getSkillname().equals(SkillName)) {
                        usersld += (double)addsld;
                        a = true;
                        vs[i] = vs2[0] + "_" + (int)usersld;
                        StringBuffer buffer = new StringBuffer();
                        for (int j = 0; j < vs.length; ++j) {
                            if (buffer.length() != 0) {
                                buffer.append("#");
                            }
                            buffer.append(vs[j]);
                        }
                        data.setSkills("S", buffer.toString());
                        SendRoleAndRolesummingUntil.sendRole(data);
                        break;
                    }
                }
            }
        }
    }
}
