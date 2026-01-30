package com.gl.util;

import come.tool.FightingData.TypeUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.server.GameServer;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import org.come.model.Skill;
import java.util.List;
//灵犀工具类

public class LingXiUtil
{
    public static final String[] BUFF;

    //解析灵犀字符串返回技能列表
    public static List<Skill> getLingXiSkill(String lingxi) {
        List<Skill> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] param = lingxi.split("&");
            // 灵犀技能
            String jn = param[3].split("=")[1];
            String[] jineng;
            for (String jnstr : jineng = jn.split("\\|")) {
                String[] jns = jnstr.split("_");
                if (!jns[1].equals("0")) {
                    Skill skill = GameServer.getSkill(jns[0]);
                    if (skill != null) {
                        skill.setSkilllevel(Integer.parseInt(jns[1]));
                        list.add(skill);
                    }
                }
            }
        }
        return list;
    }
    //返回所选灵犀类型及点数
    public static String isFull(String lingxi) {
        if (StringUtils.isNotEmpty(lingxi)) {
            String[] param = lingxi.split("&");
            String dj = param[2].split("=")[1];
            return param[0].split("=")[1] + "=" + dj;
        }
        return "";
    }

    //查找并返回灵犀技能对应值，如果没有则返回0
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
                    Skill skill = GameServer.getSkill(skillid);
                    if (skill == null) {
                        return 0;
                    }
                    skill.setSkilllevel(Integer.parseInt(jns[1]));
                    return getNumber(skill, idx);
                }
                else {
                    ++i;
                }
            }
        }
        return 0;
    }

    //根据灵犀技能ID获取灵犀值
    public static int getNumberBySkillId(String skillid, int idx, int lvl) {
        Skill skill = GameServer.getSkill(skillid);
        if (skill == null) {
            return 0;
        }
        skill.setSkilllevel(lvl);
        return getNumber(skill, idx);
    }
    //计算灵犀技能数值公式
    public static int getNumber(Skill skill, int idx) {
        String msg = skill.getRemark();
        int lvl = skill.getSkilllevel();
        if (lvl == 0) {
            return 0;
        }
        --lvl;
        Matcher mat = Pattern.compile("<([^>]*)>").matcher(msg);
        int i = 0;
        while (mat.find()) {
            if (++i != idx) {
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
    
    static {
        BUFF = new String[] { TypeUtil.JS, TypeUtil.LL, TypeUtil.KX, TypeUtil.MH };
    }
}
