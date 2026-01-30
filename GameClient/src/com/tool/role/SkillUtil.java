package com.tool.role;

import org.come.bean.PrivateData;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class SkillUtil
{
    static String[] skillmsg;
    static String[][] skills;
    
    public static List<String> getDetailedSkills(String se) {
        List<String> detailedSkills = new ArrayList<>();
        String[] basicSkills;
        for (String basicSkill : basicSkills = getSepciesSS(se)) {
            int index = Arrays.asList(SkillUtil.skillmsg).indexOf(basicSkill);
            if (index != -1) {
                for (int j = 0; j < 5; ++j) {
                    detailedSkills.add(SkillUtil.skills[index][j]);
                }
            }
            else {
                System.out.println("未找到角色 " + se + " 的基本技能: " + basicSkill);
            }
        }
        return detailedSkills;
    }
    
    public static String[] getSepciesSS(String se) {
        if (se.equals("男鬼")) {
            return new String[] { "忘", "冥", "蛊" };
        }
        if (se.equals("女鬼")) {
            return new String[] { "忘", "冥", "魅" };
        }
        if (se.equals("男仙")) {
            return new String[] { "雷", "水", "风" };
        }
        if (se.equals("女仙")) {
            return new String[] { "雷", "水", "火" };
        }
        if (se.equals("男魔")) {
            return new String[] { "慑", "牛", "速" };
        }
        if (se.equals("女魔")) {
            return new String[] { "慑", "牛", "盘" };
        }
        if (se.equals("男人")) {
            return new String[] { "冰", "睡", "混" };
        }
        if (se.equals("女人")) {
            return new String[] { "冰", "睡", "毒" };
        }
        if (se.equals("男龙")) {
            return new String[] { "霹", "霖", "摇" };
        }
        if (se.equals("女龙")) {
            return new String[] { "霹", "霖", "沧" };
        }
        return null;
    }
    
    public static String getSkillDoor(String skillname) {
        for (int i = 0; i < SkillUtil.skills.length; ++i) {
            for (int j = 0; j < SkillUtil.skills[i].length; ++j) {
                if (SkillUtil.skills[i][j].equals(skillname)) {
                    return SkillUtil.skillmsg[i];
                }
            }
        }
        return null;
    }
    
    public static String getsuitSkill(int id) {
        if (id == 6001) {
            return "加强封印";
        }
        if (id == 6002) {
            return "加强昏睡";
        }
        if (id == 6003) {
            return "加强遗忘";
        }
        if (id == 6004) {
            return "加强混乱";
        }
        if (id == 6005) {
            return "加强风法";
        }
        if (id == 6006 || id == 6007) {
            return "加强法术";
        }
        if (id == 6008) {
            return "加强火法";
        }
        if (id == 6009) {
            return "加强鬼火";
        }
        if (id == 6010) {
            return "忽视抗火";
        }
        if (id == 6011) {
            return "忽视抗混";
        }
        if (id == 6012) {
            return "忽视抗风";
        }
        if (id == 6013) {
            return "忽视抗封";
        }
        if (id == 6014) {
            return "忽视抗睡";
        }
        if (id == 6015) {
            return "忽视遗忘";
        }
        if (id == 6016) {
            return "加强震慑";
        }
        if (id == 6017) {
            return "提抗上限";
        }
        if (id == 6030) {
            return "加强速度法术效果";
        }
        if (id == 6031) {
            return "水魔附身";
        }
        if (id == 6044) {
            return "五行归一";
        }
        if (id == 6032) {
            return "加强三尸虫";
        }
        if (id == 6035) {
            return "加强防御法术效果";
        }
        if (id == 6036) {
            return "加强防御法术效果";
        }
        if (id == 6039) {
            return "加强攻击法术效果";
        }
        if (id == 8001 || id == 8006) {
            return "忽视抗混";
        }
        if (id == 8002 || id == 8007) {
            return "忽视抗封";
        }
        if (id == 8003 || id == 8008) {
            return "忽视抗睡";
        }
        if (id == 8004 || id == 8009) {
            return "忽视抗遗忘";
        }
        if (id == 8005 || id == 8010) {
            return "加强毒";
        }
        if (id == 8011) {
            return "忽视抗雷";
        }
        if (id == 8012) {
            return "忽视抗火";
        }
        if (id == 8013) {
            return "忽视抗风";
        }
        if (id == 8014) {
            return "忽视抗水";
        }
        if (id == 8015) {
            return "忽视抗鬼火";
        }
        return null;
    }
    
    public static double getSuitValue(int id, int lvl) {
        if (id == 6016) {
            return 1.5 + (double)lvl * 0.5;
        }
        if (id == 6031) {
            return (double)lvl;
        }
        if (id == 8015) {
            int v = RoleProperty.getBone(RoleData.getRoleData().getLoginResult()) / 20;
            return (double)v * 0.1;
        }
        if (id == 6017) {
            return (double)(5 + lvl);
        }
        if ((id >= 6001 && id <= 6009) || id == 6030 || id == 6035 || id == 6036 || id == 6039) {
            return (double)(3 + lvl);
        }
        if (id == 3032) {
            return (double)(100 + lvl * 100);
        }
        if (id >= 8001 && id <= 8004) {
            int v = RoleProperty.getBone(RoleData.getRoleData().getLoginResult()) / 50;
            return (double)v * 0.1;
        }
        if (id >= 8006 && id <= 8009) {
            int v = RoleProperty.getPower(RoleData.getRoleData().getLoginResult()) / 30;
            return (double)v * 0.1;
        }
        if ((id >= 8011 && id <= 8014) || id == 8005) {
            int v = RoleProperty.getBone(RoleData.getRoleData().getLoginResult()) / 10;
            return (double)v * 0.1;
        }
        if (id >= 8010) {
            int v = RoleProperty.getPower(RoleData.getRoleData().getLoginResult()) / 10;
            return (double)v * 0.1;
        }
        if (id >= 8015) {
            int v = RoleProperty.getPower(RoleData.getRoleData().getLoginResult()) / 20;
            return (double)v * 0.1;
        }
        return 0.5 + (double)lvl * 0.5;
    }
    
    public static String[] getSkillsAll(String skillDoor) {
        for (int i = 0; i < SkillUtil.skillmsg.length; ++i) {
            if (SkillUtil.skillmsg[i].equals(skillDoor)) {
                return SkillUtil.skills[i];
            }
        }
        return null;
    }
    
    public static String getSepciesN(BigDecimal se) {
        int id = se.intValue();
        if (id == 23001 || id == 23002 || id == 23003 || id == 23007) {
            return "男鬼";
        }
        if (id == 23004 || id == 23005 || id == 23006 || id == 23008) {
            return "女鬼";
        }
        if (id == 24001 || id == 24002 || id == 24003 || id == 24007) {
            return "男龙";
        }
        if (id == 24004 || id == 24005 || id == 24006 || id == 24008) {
            return "女龙";
        }
        if (id == 22001 || id == 22002 || id == 22003 || id == 22007 || id == 22009 || id == 22011) {
            return "男仙";
        }
        if (id == 22004 || id == 22005 || id == 22006 || id == 22008 || id == 22010 || id == 22012) {
            return "女仙";
        }
        if (id == 21001 || id == 21002 || id == 21003 || id == 21007 || id == 21009 || id == 21011) {
            return "男魔";
        }
        if (id == 21004 || id == 21005 || id == 21006 || id == 21008 || id == 21010 || id == 21012) {
            return "女魔";
        }
        if (id == 20001 || id == 20002 || id == 20003 || id == 20007 || id == 20009 || id == 20011) {
            return "男人";
        }
        if (id == 20004 || id == 20005 || id == 20006 || id == 20008 || id == 20010 || id == 20012) {
            return "女人";
        }
        return null;
    }
    
    public static String[] getSepciesS(String se) {
        if (se.equals("男鬼")) {
            return new String[] { "遗忘", "鬼火", "三尸虫" };
        }
        if (se.equals("女鬼")) {
            return new String[] { "遗忘", "鬼火", "魅惑" };
        }
        if (se.equals("男仙")) {
            return new String[] { "雷", "水", "风" };
        }
        if (se.equals("女仙")) {
            return new String[] { "雷", "水", "火" };
        }
        if (se.equals("男魔")) {
            return new String[] { "震慑", "加攻", "加速" };
        }
        if (se.equals("女魔")) {
            return new String[] { "震慑", "加攻", "盘丝" };
        }
        if (se.equals("男人")) {
            return new String[] { "封印", "昏睡", "混乱" };
        }
        if (se.equals("女人")) {
            return new String[] { "封印", "昏睡", "毒" };
        }
        if (se.equals("男龙")) {
            return new String[] { "霹雳", "甘霖", "扶摇" };
        }
        if (se.equals("女龙")) {
            return new String[] { "霹雳", "甘霖", "沧波" };
        }
        return null;
    }
    
    public static void changeSkill(PrivateData data, BigDecimal se) {
        String[] vs = data.getSkill("S");
        String[] ses = getSepciesS(getSepciesN(se));
        if (vs != null) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("_");
                if (buffer.length() != 0) {
                    buffer.append("#");
                }
                buffer.append(changeSkillId(Integer.parseInt(vss[0]), ses));
                buffer.append("_");
                buffer.append(vss[1]);
            }
            data.setSkills("S", buffer.toString());
        }
    }
    
    public static int changeSkillId(int id, String[] ses) {
        int p = 0;
        if ((id >= 1006 && id <= 1010) || (id >= 1046 && id <= 1050) || (id >= 1021 && id <= 1025) || (id >= 1071 && id <= 1075) || (id >= 1081 && id <= 1085)) {
            p = 0;
        }
        else if ((id >= 1011 && id <= 1015) || (id >= 1051 && id <= 1055) || (id >= 1026 && id <= 1030) || (id >= 1061 && id <= 1065) || (id >= 1071 && id <= 1075) || (id >= 1091 && id <= 1095)) {
            p = 1;
        }
        else {
            p = 2;
        }
        String leixing = ses[p];
        int lvl = id % 5;
        if (lvl == 0) {
            lvl = 5;
        }
        if (leixing.equals("封印")) {
            id = 1005 + lvl;
        }
        else if (leixing.equals("昏睡")) {
            id = 1010 + lvl;
        }
        else if (leixing.equals("混乱")) {
            id = 1000 + lvl;
        }
        else if (leixing.equals("毒")) {
            id = 1015 + lvl;
        }
        else if (leixing.equals("雷")) {
            id = 1045 + lvl;
        }
        else if (leixing.equals("水")) {
            id = 1050 + lvl;
        }
        else if (leixing.equals("风")) {
            id = 1040 + lvl;
        }
        else if (leixing.equals("火")) {
            id = 1055 + lvl;
        }
        else if (leixing.equals("震慑")) {
            id = 1020 + lvl;
        }
        else if (leixing.equals("加攻")) {
            id = 1025 + lvl;
        }
        else if (leixing.equals("加速")) {
            id = 1035 + lvl;
        }
        else if (leixing.equals("盘丝")) {
            id = 1030 + lvl;
        }
        else if (leixing.equals("遗忘")) {
            id = 1070 + lvl;
        }
        else if (leixing.equals("鬼火")) {
            id = 1060 + lvl;
        }
        else if (leixing.equals("三尸虫")) {
            id = 1065 + lvl;
        }
        else if (leixing.equals("魅惑")) {
            id = 1075 + lvl;
        }
        else if (leixing.equals("霹雳")) {
            id = 1080 + lvl;
        }
        else if (leixing.equals("沧波")) {
            id = 1085 + lvl;
        }
        else if (leixing.equals("甘霖")) {
            id = 1090 + lvl;
        }
        else if (leixing.equals("扶摇")) {
            id = 1095 + lvl;
        }
        return id;
    }
    
    public static String[] getSepcieswas(String se) {
        if (se.equals("男鬼")) {
            return new String[] { "忘", "冥", "蛊" };
        }
        if (se.equals("女鬼")) {
            return new String[] { "忘", "惑", "冥" };
        }
        if (se.equals("男仙")) {
            return new String[] { "雷", "水", "风" };
        }
        if (se.equals("女仙")) {
            return new String[] { "雷", "水", "火" };
        }
        if (se.equals("男魔")) {
            return new String[] { "震", "攻", "速" };
        }
        if (se.equals("女魔")) {
            return new String[] { "震", "攻", "盘" };
        }
        if (se.equals("男人")) {
            return new String[] { "冰", "睡", "混" };
        }
        if (se.equals("女人")) {
            return new String[] { "冰", "睡", "毒" };
        }
        if (se.equals("男龙")) {
            return new String[] { "霹", "雨", "摇" };
        }
        if (se.equals("女龙")) {
            return new String[] { "霹", "雨", "涌" };
        }
        return null;
    }
    
    static {
        SkillUtil.skills = new String[20][5];
        SkillUtil.skillmsg = new String[] { "雷", "火", "风", "水", "冰", "睡", "毒", "混", "慑", "牛", "盘", "速", "冥", "蛊", "忘", "魅", "霹", "沧", "霖", "摇" };
        SkillUtil.skills[0][0] = "雷霆霹雳";
        SkillUtil.skills[0][1] = "日照光华";
        SkillUtil.skills[0][2] = "雷神怒击";
        SkillUtil.skills[0][3] = "电闪雷鸣";
        SkillUtil.skills[0][4] = "天诛地灭";
        SkillUtil.skills[1][0] = "地狱烈火";
        SkillUtil.skills[1][1] = "天雷怒火";
        SkillUtil.skills[1][2] = "三味真火";
        SkillUtil.skills[1][3] = "烈火骄阳";
        SkillUtil.skills[1][4] = "九阴纯火";
        SkillUtil.skills[2][0] = "飞砂走石";
        SkillUtil.skills[2][1] = "乘风破浪";
        SkillUtil.skills[2][2] = "太乙生风";
        SkillUtil.skills[2][3] = "风雷涌动";
        SkillUtil.skills[2][4] = "袖里乾坤";
        SkillUtil.skills[3][0] = "龙卷雨击";
        SkillUtil.skills[3][1] = "龙腾水溅";
        SkillUtil.skills[3][2] = "龙啸九天";
        SkillUtil.skills[3][3] = "蛟龙出海";
        SkillUtil.skills[3][4] = "九龙冰封";
        SkillUtil.skills[4][0] = "作茧自缚";
        SkillUtil.skills[4][1] = "金蛇缠丝";
        SkillUtil.skills[4][2] = "天罗地网";
        SkillUtil.skills[4][3] = "作壁上观";
        SkillUtil.skills[4][4] = "四面楚歌";
        SkillUtil.skills[5][0] = "催眠咒";
        SkillUtil.skills[5][1] = "瞌睡咒";
        SkillUtil.skills[5][2] = "离魂咒";
        SkillUtil.skills[5][3] = "迷魂醉";
        SkillUtil.skills[5][4] = "百日眠";
        SkillUtil.skills[6][0] = "蛇蝎美人";
        SkillUtil.skills[6][1] = "追魂迷香";
        SkillUtil.skills[6][2] = "断肠烈散";
        SkillUtil.skills[6][3] = "鹤顶红粉";
        SkillUtil.skills[6][4] = "万毒攻心";
        SkillUtil.skills[7][0] = "反间之计";
        SkillUtil.skills[7][1] = "情真意切";
        SkillUtil.skills[7][2] = "谗言相加";
        SkillUtil.skills[7][3] = "借刀杀人";
        SkillUtil.skills[7][4] = "失心狂乱";
        SkillUtil.skills[8][0] = "夺命勾魂";
        SkillUtil.skills[8][1] = "追神摄魄";
        SkillUtil.skills[8][2] = "魔音摄心";
        SkillUtil.skills[8][3] = "销魂蚀骨";
        SkillUtil.skills[8][4] = "阎罗追命";
        SkillUtil.skills[9][0] = "妖之魔力";
        SkillUtil.skills[9][1] = "力神复苏";
        SkillUtil.skills[9][2] = "狮王之怒";
        SkillUtil.skills[9][3] = "兽王神力";
        SkillUtil.skills[9][4] = "魔神附身";
        SkillUtil.skills[10][0] = "红袖添香";
        SkillUtil.skills[10][1] = "莲步轻舞";
        SkillUtil.skills[10][2] = "楚楚可怜";
        SkillUtil.skills[10][3] = "魔神护体";
        SkillUtil.skills[10][4] = "含情脉脉";
        SkillUtil.skills[11][0] = "魔之飞步";
        SkillUtil.skills[11][1] = "急速之魔";
        SkillUtil.skills[11][2] = "魔神飞舞";
        SkillUtil.skills[11][3] = "天外飞魔";
        SkillUtil.skills[11][4] = "乾坤借速";
        SkillUtil.skills[12][0] = "幽冥鬼火";
        SkillUtil.skills[12][1] = "火影迷踪";
        SkillUtil.skills[12][2] = "冥烟销骨";
        SkillUtil.skills[12][3] = "落日熔金";
        SkillUtil.skills[12][4] = "血海深仇";
        SkillUtil.skills[13][0] = "吸血水蛭";
        SkillUtil.skills[13][1] = "六翅毒蝉";
        SkillUtil.skills[13][2] = "啮骨抽髓";
        SkillUtil.skills[13][3] = "血煞之蛊";
        SkillUtil.skills[13][4] = "吸星大法";
        SkillUtil.skills[14][0] = "麻沸散";
        SkillUtil.skills[14][1] = "鬼失惊";
        SkillUtil.skills[14][2] = "乱魂钉";
        SkillUtil.skills[14][3] = "失心疯";
        SkillUtil.skills[14][4] = "孟婆汤";
        SkillUtil.skills[15][0] = "幽怜魅影";
        SkillUtil.skills[15][1] = "醉生梦死";
        SkillUtil.skills[15][2] = "一曲销魂";
        SkillUtil.skills[15][3] = "秦丝冰雾";
        SkillUtil.skills[15][4] = "倩女幽魂";
        SkillUtil.skills[16][0] = "平地生雷";
        SkillUtil.skills[16][1] = "惊霆贯顶";
        SkillUtil.skills[16][2] = "列缺霹雳";
        SkillUtil.skills[16][3] = "风雷万钧";
        SkillUtil.skills[16][4] = "震天动地";
        SkillUtil.skills[17][0] = "碧海潮生";
        SkillUtil.skills[17][1] = "怒涛拍岸";
        SkillUtil.skills[17][2] = "洪波涌起";
        SkillUtil.skills[17][3] = "白浪滔天";
        SkillUtil.skills[17][4] = "沧海横流";
        SkillUtil.skills[18][0] = "久旱初雨";
        SkillUtil.skills[18][1] = "兴云致雨";
        SkillUtil.skills[18][2] = "润物无声";
        SkillUtil.skills[18][3] = "沛然莫御";
        SkillUtil.skills[18][4] = "泽被万物";
        SkillUtil.skills[19][0] = "激浊扬清";
        SkillUtil.skills[19][1] = "狂飙怒号";
        SkillUtil.skills[19][2] = "扶摇而上";
        SkillUtil.skills[19][3] = "凌虚御风";
        SkillUtil.skills[19][4] = "飞举九天";
    }
}
