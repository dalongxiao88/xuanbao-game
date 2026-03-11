package com.gl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;

public class LingXiUtil
{
    public static int getLingXiGe(String lingxi) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] lingxiSegments = lingxi.split("&");
            String openedSlotCount = lingxiSegments[lingxiSegments.length - 1];
            int v = Integer.parseInt(openedSlotCount);
            return (v > 16) ? 16 : v;
        }
        return 0;
    }
    
    public static int getPointCount(String lingxi) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] lingxiSegments = lingxi.split("&");
            String allocatedPointCount = lingxiSegments[2].split("=")[1];
            return Integer.parseInt(allocatedPointCount);
        }
        return 0;
    }
    
    public static int getNumberByStr(String lingxi, String skillid, int idx) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] lingxiSegments = lingxi.split("&");
            String openedSkillConfig = lingxiSegments[3].split("=")[1];
            String[] openedSkills = openedSkillConfig.split("\\|");
            int length = openedSkills.length;
            int i = 0;
            while (i < length) {
                String skillConfig = openedSkills[i];
                String[] skillPair = skillConfig.split("_");
                if (skillPair[0].equals(skillid)) {
                    if (skillPair[1].equals("0")) {
                        return 0;
                    }
                    Skill skill = UserMessUntil.getSkillId(skillid);
                    if (skill == null) {
                        return 0;
                    }
                    return getNumber(skill, idx, skillPair[1]);
                }
                else {
                    ++i;
                }
            }
        }
        return 0;
    }
    
    public static int getNumberBySkillId(String skillid, int idx, int lvl) {
        Skill skill = UserMessUntil.getSkillId(skillid);
        if (skill == null) {
            return 0;
        }
        return getNumber(skill, idx, lvl + "");
    }
    
    public static int getNumber(Skill skill, int idx, String dj) {
        String msg = skill.getRemark();
        if (!StringUtils.isNotEmpty(skill.getRemark())) {
            return 0;
        }
        int lvl = Integer.parseInt(dj);
        if (lvl == 0) {
            return 0;
        }
        --lvl;
        Matcher mat = Pattern.compile("<([^>]*)>").matcher(msg);
        int i = 1;
        while (mat.find()) {
            if (i != idx) {
                continue;
            }
            else {
                String str = mat.group();
                str = str.replaceAll("<", "").replaceAll(">", "");
                if (str.indexOf("+") > -1) {
                    String[] num = str.split("\\+");
                    if (num.length == 2) {
                        double s = Double.parseDouble(num[0]);
                        double e = Double.parseDouble(num[1]);
                        return (int)(s + e * (double)lvl);
                    }
                    else {
                        continue;
                    }
                }
                else if (str.indexOf("-") > -1) {
                    String[] num = str.split("-");
                    if (num.length == 2) {
                        double s = Double.parseDouble(num[0]);
                        double e = Double.parseDouble(num[1]);
                        return (int)(s - e * (double)lvl);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
        }
        return 0;
    }
}
