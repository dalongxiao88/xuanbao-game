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
            String[] param = lingxi.split("&");
            String s = param[param.length - 1];
            int v = Integer.parseInt(s);
            return (v > 16) ? 16 : v;
        }
        return 0;
    }
    
    public static int getPointCount(String lingxi) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] param = lingxi.split("&");
            String dj = param[2].split("=")[1];
            return Integer.parseInt(dj);
        }
        return 0;
    }
    
    public static int getNumberByStr(String lingxi, String skillid, int idx) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] param = lingxi.split("&");
            String jn = param[3].split("=")[1];
            String[] jineng = jn.split("\\|");
            int length = jineng.length;
            int i = 0;
            while (i < length) {
                String jnstr = jineng[i];
                String[] jns = jnstr.split("_");
                if (jns[0].equals(skillid)) {
                    if (jns[1].equals("0")) {
                        return 0;
                    }
                    Skill skill = UserMessUntil.getSkillId(skillid);
                    if (skill == null) {
                        return 0;
                    }
                    return getNumber(skill, idx, jns[1]);
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
