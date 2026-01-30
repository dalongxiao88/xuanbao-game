package com.gl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.tool.Arith;
import org.come.model.Skill;
import org.come.server.GameServer;

public class FaMenUtil
{

    /**
     * 计算法门技能数值公式
     *
     * @param skillId 技能id
     * @param fmsld 法门熟练度
     * @param idx   第几个数值
     * @return 整数
     */
    public static int getInt(String skillId, int fmsld, int idx) {
        Skill skill = GameServer.getSkill(skillId);
        return (int)getNumber(skill.getRemark(), fmsld, idx);
    }
    
    public static int getInt(int skillId, int fmsld, int idx) {
        Skill skill = GameServer.getSkill(skillId + "");
        return (int)getNumber(skill.getRemark(), fmsld, idx);
    }

    /**
     * 计算法门技能数值公式 返回值为百分比
     *
     * @param skillId 技能id
     * @param fmsld 法门熟练度
     * @param idx   第几个数值
     * @return
     */
    public static double getDouble(int skillId, int fmsld, int idx) {
        Skill skill = GameServer.getSkill(skillId + "");
        return getNumber(skill.getRemark(), fmsld, idx);
    }

    /**
     * 计算法门技能数值公式 返回值为百分比小数
     *
     * @param skillId 技能id
     * @param fmsld 法门熟练度
     * @param idx   第几个数值
     * @return 百分比小数
     */
    public static double getDouble(String skillId, int fmsld, int idx) {
        Skill skill = GameServer.getSkill(skillId);
        return Arith.div(getNumber(skill.getRemark(), fmsld, idx), 100.0, 2);
    }

    /**
     * 计算法门技能数值公式
     *
     * @param msg 技能说明
     * @param fmsld 法门熟练度
     * @param idx   第几个数值
     * @return
     */
    private static double getNumber(String msg, int fmsld, int idx) {
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
                        String[] vals = num[1].split(",");
                        if (vals.length == 1) {
                            double s = Double.parseDouble(num[0]);
                            double e = Double.parseDouble(num[1]);
                            return Arith.round(Arith.add(s, Arith.mul(e, (double)fmsld)), 2, 1);
                        }
                        int sum = Integer.parseInt(num[0]);
                        for (int j = 0; j < vals.length && Integer.parseInt(vals[j]) <= fmsld; ++j) {
                            ++sum;
                        }
                        return (double)sum;
                    }
                    else {
                        continue;
                    }
                }
                else if (str.indexOf("-") > -1) {
                    String[] num = str.split("-");
                    if (num.length == 2) {
                        double s2 = Double.parseDouble(num[0]);
                        double e2 = Double.parseDouble(num[1]);
                        return Arith.round(Arith.sub(s2, Arith.mul(e2, (double)fmsld)), 2, 1);
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
        return 0.0;
    }
}
