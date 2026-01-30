package come.tool.Calculation;

import org.come.model.Skill;
import org.come.server.GameServer;
import java.util.ArrayList;
import java.util.List;
import org.come.bean.LoginResult;
import come.tool.Role.PrivateData;
import java.util.Iterator;
import org.come.tool.Arith;
import java.util.HashMap;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Map;
//基础值
public class BaseValue
{
    public static Map<BigDecimal, GradeQl[]> GradeQls;
    public static final double[] bases;
    public static final double[] basevs;
    public static String[] XC;
    public static String[] DC;
    
    public static int getPetValue(int lvl, int P, double G, int base, int type) {
        if (type == 0 || type == 1) {
            return (int)((double)(lvl * P) * G) + (int)((0.7 * (double)lvl * G + 1.0) * (double)base);
        }
        if (type == 2) {
            return (int)((double)(lvl * P) * G / 5.0) + (int)((0.14 * (double)lvl * G + 1.0) * (double)base);
        }
        if (type == 3) {
            return (int)((double)(base + P) * G);
        }
        return P;
    }
    // 获取人物的属性值 种族id 点数 等级是（1转15 等级就是15） 类型 0hp 1mp 2ap 3sp 4定力
    public static int getRoleValue(BigDecimal raceid, int P, int lvl, int type) {
        double G = getValue(raceid, 1, type);
        if (type >= 4) {
            return (int)(G * (double)P);
        }
        double base = getValue(raceid, 0, type);
        if (G > 2.0) {
            G = 0.01;
        }
        if (base > 400.0) {
            G = 10.0;
        }
        int E = (100 - lvl) / 5;
        int LEPG = (int)((double)((lvl + E) * P) * G);
        if (type == 0 || type == 1) {
            return (int)((double)LEPG + base);
        }
        if (type == 2) {
            return (int)((double)(LEPG / 5) + base);
        }
        return (int)((double)(10 + P) * G);
    }
    // 获取值 根据种族 类型0表示值1表示成长 zhi=0血1蓝2ap3sp
    public static double getValue(BigDecimal raceid, int type, int zhi) {
        if (type == 0) {
            return BaseValue.bases[zhi + getratio(raceid) * 4];
        }
        return BaseValue.basevs[zhi + getratio(raceid) * 5];
    }
    /** 获取种族系数 **/
    public static int getratio(BigDecimal raceid) {
        if (raceid.intValue() == 10001) {
            return 0;
        }
        if (raceid.intValue() == 10002) {
            return 1;
        }
        if (raceid.intValue() == 10003) {
            return 2;
        }
        if (raceid.intValue() == 10004) {
            return 3;
        }
        if (raceid.intValue() == 10005) {
            return 4;
        }
        return 0;
    }
    /**完整经脉基础算法*/
    public static int getMeridiansLvl(int i) {
        int i2 = 0;
        do {
            ++i2;
            i -= getMeridiansExp(i2);
        } while (i >= 0);
        return i2;
    }
    //基础数值工具-方法2
    public static int getMeridiansExp(int i) {
        return i * 100;
    }
    //基础数值工具-方法3
    public static int getMeridiansTotalExp(int i) {
        return (i + 1) * i * 50;
    }
    //飞升后抗性上限
    public static void gradeQl(BigDecimal raceid, int lvl, int zs, Map<String, Double> map) {
        GradeQl[] qls = (GradeQl[])BaseValue.GradeQls.get(raceid);
        if (qls != null) {
            int glvl = (lvl > 162) ? 162 : lvl;
            for (int i = 0; i < qls.length; ++i) {
                GradeQl gradeQl = qls[i];
                double v = gradeQl.getV() * (double)glvl / (double)gradeQl.getP();
                if (v != 0.0) {
                    CalculationUtil.addValue(map, gradeQl.getType(), v);
                }
            }
        }
        if (zs == 4) {
            lvl -= 140;
            lvl /= 10;
            if (raceid.intValue() == 10001) {
                CalculationUtil.addValue(map, "四抗上限", (double)lvl * 1.2);
            }
            else if (raceid.intValue() == 10002) {
                CalculationUtil.addValue(map, "四抗上限", (double)lvl * 1.1);
            }
            else if (raceid.intValue() == 10003) {
                CalculationUtil.addValue(map, "四抗上限", (double)lvl * 1.0);
            }
            else if (raceid.intValue() == 10004) {
                CalculationUtil.addValue(map, "四抗上限", (double)lvl * 0.9);
            }
        }
    }
    /**抗性上限*/
    /**人法 遗忘上限*/
    public static double Upper(String key, BigDecimal raceid) {
        if (raceid.intValue() == 10003 || raceid.intValue() == 10002) {
            return 110.0;
        }
        if (raceid.intValue() == 10001) {
            return 140.0;
        }
        if (raceid.intValue() == 10004) {
            if (key.equals("抗遗忘")) {
                return 140.0;
            }
            return 120.0;
        }
        else {
            if (raceid.intValue() == 10005) {
                return 120.0;
            }
            return 110.0;
        }
    }
    /**帮派抗性数值*/
    /**true gx贡献值zf true表示主  false表示副*/
    public static double getBangQuality(BigDecimal gx, boolean isZhu) {
        if (gx == null) {
            return 0.0;
        }
        double x = getCubeRoot(gx.longValue() / 5000L);
        if (isZhu) {
            return (x < 30.0) ? new BigDecimal(x).setScale(1, RoundingMode.DOWN).doubleValue() : 30.0;
        }
        x /= 2.0;
        return (x < 15.0) ? new BigDecimal(x).setScale(1, RoundingMode.DOWN).doubleValue() : 15.0;
    }
    
    public static double getCubeRoot(long input) {
        if (input == 0L) {
            return 0.0;
        }
        double x0;
        double x2;
        for (x0 = (double)input, x2 = 2.0 * x0 / 3.0 + (double)input / (x0 * x0 * 3.0); Math.abs(x2 - x0) > 1.0E-6; x0 = x2, x2 = 2.0 * x0 / 3.0 + (double)input / (x0 * x0 * 3.0)) {}
        return x2;//利用迭代法求解
    }
    /**获取修正属性 */
    public static BaseQl[] reborn(String born) {
        if (born == null || born.equals("")) {
            return null;
        }
        String[] vs = born.split("\\|");
        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            Double value = (Double)map.get(vss[0]);
            if (value == null) {
                value = Math.abs(Double.parseDouble(vss[1]));
            }
            else {
                value = Math.abs(Double.parseDouble(vss[1]));
            }
            map.put(vss[0], value);
        }
        return isV(map);
    }
    /**检测修正是否合理*/
    public static BaseQl[] isV(Map<String, Double> map) {
        BaseQl[] qls = new BaseQl[map.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = (String)entry.getKey();
            double value = (double)entry.getValue();
            double max = 369000.0;
            if (key.equals("抗混乱") || key.equals("抗封印") || key.equals("抗昏睡") || key.equals("抗遗忘") || key.equals("反震程度") || key.equals("速度")) {
                max = Arith.mul(max, 1.25E-4);
            }
            else if (key.equals("抗混乱上限") || key.equals("抗封印上限") || key.equals("抗昏睡上限") || key.equals("抗遗忘上限")) {
                max = Arith.mul(max, 1.6875E-5);
            }
            else if (key.equals("抗毒伤")) {
                max = Arith.mul(max, 0.02);
            }
            else if (key.equals("MP成长") || key.equals("HP成长")) {
                max = Arith.mul(max, 1.0E-6);
            }
            else if (key.equals("物理吸收率")) {
                max = Arith.mul(max, 1.875E-4);
            }
            else if (key.equals("抗震慑")) {
                max = Arith.mul(max, 1.125E-4);
            }
            else if (key.equals("SP成长")) {
                max = Arith.mul(max, 7.5E-7);
            }
            else if (key.equals("抗风") || key.equals("抗雷") || key.equals("抗水") || key.equals("抗火") || key.equals("抗鬼火")) {
                max = Arith.mul(max, 1.6875E-4);
            }
            else if (key.equals("抗三尸")) {
                max = Arith.mul(max, 0.025);
            }
            else if (key.equals("反震率") ) {
                max = Arith.mul(max, 6.5E-5);
            }
            else if ( key.equals("躲闪")) {
                max = Arith.mul(max, 10E-5);
            }

            else if (key.equals("伤害减免")) {
                max = Arith.mul(max, 2.0E-5);
            }
            else if (key.equals("法术躲闪")) {
                max = Arith.mul(max, 1.464E-5);
            }
            else if (key.equals("抗天魔解体") || key.equals("抗分光化影") || key.equals("抗青面獠牙") || key.equals("抗小楼夜哭")) {
                max = Arith.mul(max, 0.00625);
            }
            else {
                return null;
            }
            if (value > max) {
                return null;
            }
            qls[i] = new BaseQl(key, value);
            ++i;
        }
        return qls;
    }
    /**解析帮派掉落*/
    public static BaseQl[] xls(String[] xls) {
        if (xls == null || xls.length == 0) {
            return null;
        }
        BaseQl[] qls = new BaseQl[xls.length];
        for (int i = 0; i < xls.length; ++i) {
            String[] vs = xls[i].split("=");
            qls[i] = new BaseQl(vs[0], Double.parseDouble(vs[1]));
        }
        return qls;
    }
    /**是否是符合字段*/
    public static boolean isXl(String key, String[] v) {
        for (int i = 0; i < v.length; ++i) {
            if (v[i].equals(key)) {
                return false;
            }
        }
        return true;
    }
    /**判断格式是否正常*/
    public static BaseQl[] isXls(BaseQl[] oldXls, String[] xls, int max, int type) {
        try {
            int up = (type == 1) ? 20 : 30;
            int total = 0;
            BaseQl[] qls = new BaseQl[xls.length];
            for (int i = 0; i < xls.length; ++i) {
                String[] vs = xls[i].split("=");
                if (isXl(vs[0], (type == 1) ? BaseValue.XC : BaseValue.DC)) {
                    return null;
                }
                qls[i] = new BaseQl(vs[0], Double.parseDouble(vs[1]));
                for (int j = 0; j < i; ++j) {
                    if (qls[j].getKey().equals(vs[0])) {
                        return null;
                    }
                }
                double value = 0.0;
                int lvl = 0;
                if (vs[0].equals("抗三尸虫")) {
                    lvl = (int)(qls[i].getValue() / 100.0);
                    value = (double)lvl * 100.0;
                }
                else if (vs[0].endsWith("躲闪")) {
                    if (vs[0].equals("物理躲闪")) {
                        lvl = (int)(qls[i].getValue() / 1.0);
                        value = (double)lvl * 1.0;
                    }
                    else {
                        lvl = (int)(qls[i].getValue() / 0.4);
                        value = (double)lvl * 0.4;
                    }
                }
                else if (vs[0].endsWith("减免")) {
                    lvl = (int)(qls[i].getValue() / 0.4);
                    value = (double)lvl * 0.4;
                }
                else {
                    lvl = (int)(qls[i].getValue() / 1.5);
                    value = (double)lvl * 1.5;
                }
                if (lvl > up) {
                    return null;
                }
                total += lvl;
                if (value != qls[i].getValue()) {
                    return null;
                }
            }
            if (total > max) {
                return null;
            }
            if (oldXls != null) {
                int sum = oldXls.length;
                for (int k = 0; k < oldXls.length; ++k) {
                    int j = 0;
                    while (j < qls.length) {
                        if (oldXls[k].getKey().equals(qls[j].getKey())) {
                            sum -= ((oldXls[k].getValue() <= qls[j].getValue()) ? 1 : 0);
                            break;
                        }
                        else {
                            ++j;
                        }
                    }
                }
                if (sum > 0) {
                    return null;
                }
            }
            return qls;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /** 根据翅膀品质获取系数 */
    public static int getQv(String quality) {
        int n = -1;
        switch (quality.hashCode()) {
            case 811615: {
                if (quality.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (quality.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (quality.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (quality.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (quality.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (quality.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 10;
            }
            case 1: {
                return 20;
            }
            case 2: {
                return 30;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 50;
            }
            case 5: {
                return 60;
            }
            default: {
                return 10;
            }
        }
    }
    /**获取人物技能 S技能 T天演策 F法门 Q其他 */
    public static List<BaseSkill> reSkill(PrivateData data, LoginResult login) {
        String skill = data.getSkills();
        if (skill == null || skill.equals("")) {
            return null;
        }
        List<BaseSkill> baseSkills = new ArrayList<>();
        String[] vs = skill.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith("S")) {
                String[] vss = vs[i].split("#");
                vss[0] = vss[0].substring(1);
                for (int j = 0; j < vss.length; ++j) {
                    String[] ss = vss[j].split("_");
                    int id = Integer.parseInt(ss[0]);
                    if (id > 1000 && id <= 1100) {
                        int sld = Integer.parseInt(ss[1]);
                        if (sld > 25000) {
                            sld = 25000;
                        }
                        Skill skill2 = GameServer.getSkill(id + "");
                        if (skill2 != null) {
                            baseSkills.add(new BaseSkill(id, sld, skill2, null));
                        }
                    }
                }
            }
            else if (vs[i].startsWith("T")) {
                String[] vss = vs[i].split("#");
                int T = getTSP(login.getExtraPointInt("T"));
                for (int k = 1; k < vss.length; ++k) {
                    String[] ss2 = vss[k].split("_");
                    int id2 = Integer.parseInt(ss2[0]);
                    if (id2 > 9000 && id2 <= 10166) {
                        int lvl = Integer.parseInt(ss2[1]);
                        if (lvl > 5) {
                            lvl = 5;
                        }
                        T -= lvl;
                        if (T < 0) {
                            data.setSkills("T", null);
                            return reSkill(data, login);
                        }
                        Skill skillBean = GameServer.getSkill(id2 + "");
                        double base = skillBean.getValue();
                        double grow = skillBean.getGrow();
                        BaseQl baseQl = null;
                        if (id2 == 9001) {
                            baseQl = new BaseQl("MP", base + (double)lvl * grow);
                        }
                        else if (id2 == 9002) {
                            baseQl = new BaseQl("HP", base + (double)lvl * grow);
                        }
                        else if (id2 == 9003) {
                            baseQl = new BaseQl("HP", base + (double)lvl * grow);
                        }
                        else if (id2 == 9004) {
                            baseQl = new BaseQl("根骨", base + (double)lvl * grow);
                        }
                        else if (id2 == 9005) {
                            baseQl = new BaseQl("力量", base + (double)lvl * grow);
                        }
                        else if (id2 == 9006) {
                            baseQl = new BaseQl("AP", base + (double)lvl * grow);
                        }
                        else if (id2 == 9007) {
                            baseQl = new BaseQl("SP", base + (double)lvl * grow);
                        }
                        else if (id2 == 9008) {
                            baseQl = new BaseQl("抗风", base + (double)lvl * grow);
                        }
                        else if (id2 == 9009) {
                            baseQl = new BaseQl("抗火", base + (double)lvl * grow);
                        }
                        else if (id2 == 9010) {
                            baseQl = new BaseQl("抗水", base + (double)lvl * grow);
                        }
                        else if (id2 == 9011) {
                            baseQl = new BaseQl("抗雷", base + (double)lvl * grow);
                        }
                        else if (id2 == 9012) {
                            baseQl = new BaseQl("抗鬼火", base + (double)lvl * grow);
                        }
                        else if (id2 == 9013) {
                            baseQl = new BaseQl("抗三尸", base + (double)lvl * grow);
                        }
                        else if (id2 == 9014) {
                            baseQl = new BaseQl("抗反震", base + (double)lvl * grow);
                        }
                        else if (id2 == 9015) {
                            baseQl = new BaseQl("风法狂暴", base + (double)lvl * grow);
                        }
                        else if (id2 == 9016) {
                            baseQl = new BaseQl("火法狂暴", base + (double)lvl * grow);
                        }
                        else if (id2 == 9017) {
                            baseQl = new BaseQl("水法狂暴", base + (double)lvl * grow);
                        }
                        else if (id2 == 9018) {
                            baseQl = new BaseQl("鬼火伤害", base + (double)lvl * grow);
                        }
                        else if (id2 == 9019) {
                            baseQl = new BaseQl("强力克金", base + (double)lvl * grow);
                        }
                        else if (id2 == 9020) {
                            baseQl = new BaseQl("强力克木", base + (double)lvl * grow);
                        }
                        else if (id2 == 9021) {
                            baseQl = new BaseQl("强力克火", base + (double)lvl * grow);
                        }
                        else if (id2 == 9022) {
                            baseQl = new BaseQl("强力克土", base + (double)lvl * grow);
                        }
                        else if (id2 == 9023) {
                            baseQl = new BaseQl("物理吸收率", base + (double)lvl * grow);
                        }
                        else if (id2 == 9024) {
                            baseQl = new BaseQl("躲闪率", base + (double)lvl * grow);
                        }
                        else if (id2 == 9025) {
                            baseQl = new BaseQl("强震慑", base + (double)lvl * grow);
                        }
                        else if (id2 == 9026) {
                            baseQl = new BaseQl("反击率", base + (double)lvl * grow);
                        }
                        else if (id2 == 9027) {
                            baseQl = new BaseQl("反击次数", base + (double)lvl * grow);
                        }
                        else if (id2 == 9028) {
                            baseQl = new BaseQl("狂暴率", base + (double)lvl * grow);
                        }
                        else if (id2 == 9029) {
                            baseQl = new BaseQl("反震率", base + (double)lvl * grow);
                        }
                        else if (id2 == 9030) {
                            baseQl = new BaseQl("敏捷", base + (double)lvl * grow);
                        }
                        else if (id2 == 9031) {
                            baseQl = new BaseQl("恢复气血", base + (double)lvl * grow);
                        }
                        else if (id2 == 9032) {
                            baseQl = new BaseQl("附加混乱攻击", base + (double)lvl * grow);
                        }
                        else if (id2 == 9033) {
                            baseQl = new BaseQl("附加封印攻击", base + (double)lvl * grow);
                        }
                        Skill skill3 = GameServer.getSkill(id2 + "");
                        if (skill3 != null) {
                            baseSkills.add(new BaseSkill(id2, lvl, skill3, baseQl));
                        }
                    }
                }
            }
            else if (vs[i].startsWith("F")) {
                String[] vss = vs[i].split("#");
                vss[0] = vss[0].substring(1);
                for (int j = 0; j < vss.length; ++j) {
                    String[] ss = vss[j].split("_");
                    int id = Integer.parseInt(ss[0]);
                    if (id >= 22000 && id <= 23000) {
                        int sld = Integer.parseInt(ss[1]);
                        if (sld > 25000) {
                            sld = 25000;
                        }
                        BaseQl baseQl2 = null;
                        if (id == 23003) {
                            baseQl2 = new BaseQl("AP", (double)((int)login.getPower() * 5));
                        }
                        Skill skill4 = GameServer.getSkill(id + "");
                        if (skill4 != null) {
                            baseSkills.add(new BaseSkill(id, sld, skill4, null));
                        }
                    }
                }
            }
            else if (vs[i].startsWith("X")) {
                String[] vss = vs[i].split("#");
                vss[0] = vss[0].substring(1);
                for (int j = 0; j < vss.length; ++j) {
                    String[] ss = vss[j].split("_");
                    int id = Integer.parseInt(ss[0]);
                    if (id >= 23000 && id <= 23010) {
                        int sld = Integer.parseInt(ss[1]);
                        if (sld > 25000) {
                            sld = 25000;
                        }
                        BaseQl baseQl2 = null;
                        if (id == 23003) {
                            baseQl2 = new BaseQl("AP", (double)((int)login.getPower() * 5));
                        }
                        Skill skill4 = GameServer.getSkill(id + "");
                        if (skill4 != null) {
                            baseSkills.add(new BaseSkill(id, sld, skill4, null));
                        }
                    }
                }
            }
        }
        return baseSkills;
    }
    /**获取系数 转生次数 人物等级  法宝总等级  法宝品质  法宝等级*/
    public static int getFBlvl(int t, int g, int qv, int lvl) {
        //		200  70基础 30是等级  100是总等级
        int pj = 0;
        g += t * 25;
        pj += g * 3;
        pj += (qv + lvl) * 15;
        qv += lvl;
        pj += qv * 4;
        return pj >> 5;
    }
    /**根据点数获取当前的天枢点*/
    public static int getTSP(int P) {
        int x = 9;
        int dd = 0;
        int i = 0;
        while (true) {
            if (i % 2 == 0) {
                ++dd;
            }
            if (i % 38 == 4) {
                ++dd;
            }
            x += dd;
            if (P >= x) {
                P -= x;
                ++i;
            }
            else {
                break;
            }
        }
        return i;
    }
    
    public static String STTYCraceid(BigDecimal raceid) {
        if (raceid.intValue() == 10001) {//人
            return "9854";
        }
        if (raceid.intValue() == 10002) {//魔
            return "9193";
        }
        if (raceid.intValue() == 10003) {//仙
            return "9943";
        }
        if (raceid.intValue() == 10004) {//鬼
            return "10051";
        }
        if (raceid.intValue() == 10005) {//龙
            return "10111";
        }
        return "";
    }
    
    public static int STTYCraceint(BigDecimal raceid) {
        if (raceid.intValue() == 10001 || raceid.intValue() == 10002 || raceid.intValue() == 10004) {//人
            return 800;
        }
        if (raceid.intValue() == 10003 || raceid.intValue() == 10005) {//仙
            return 600;
        }
        return 0;
    }
    
    static {
        CalculationUtil.initGradeQl();
        bases = new double[] { 360.0, 300.0, 70.0, 8.0, 330.0, 210.0, 80.0, 10.0, 300.0, 390.0, 60.0, 10.0, 270.0, 350.0, 80.0, 9.0, 300.0, 240.0, 80.0, 10.0 };
        basevs = new double[] { 1.2, 1.0, 0.95, 0.8, 1.05, 1.1, 0.6, 1.3, 1.0, 1.0, 1.0, 1.4, 0.7, 1.0, 0.9, 1.25, 1.05, 0.95, 0.75, 0.9, 0.9, 0.7, 1.3, 1.0, 1.0 };
        BaseValue.XC = new String[] { "抗雷", "抗火", "抗风", "抗水", "抗混乱", "抗封印", "抗昏睡", "抗中毒", "抗震慑", "抗物理", "抗三尸虫", "抗鬼火", "抗遗忘" };
        BaseValue.DC = new String[] { "物理躲闪", "震慑躲闪", "火法躲闪", "雷法躲闪", "风法躲闪", "水法躲闪", "毒法躲闪", "封印躲闪", "混乱躲闪", "昏睡躲闪", "遗忘躲闪", "鬼火躲闪", "三尸虫躲闪", "火法伤害减免", "风法伤害减免", "雷法伤害减免", "水法伤害减免", "鬼火伤害减免" };
    }
}
