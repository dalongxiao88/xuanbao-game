package come.tool.FightingData;

import org.come.model.Monster;

public class GetqualityUntil
{
    public static double csz(String type) {
        try {
            return Double.parseDouble(type);
        }
        catch (Exception e) {
            return 0.0;
        }
    }
    /**
     * 获取野怪抗性
     */
    public static Ql getMonsterQl(Monster monster) {
        Ql ql = new Ql();
        for (int i = 0; i < 7; ++i) {
            String[] vs = null;
            if (i == 0) {
                vs = monster.getK().split("\\|");
            }
            else if (i == 1) {
                vs = monster.getH().split("\\|");
            }
            else if (i == 2) {
                vs = monster.getQ().split("\\|");
            }
            else if (i == 3) {
                vs = monster.getWX().split("\\|");
            }
            else if (i == 4) {
                vs = monster.getSS().split("\\|");
            }
            else if (i == 5) {
                vs = monster.getKB().split("\\|");
            }
            else if (i == 6) {
                vs = monster.getQT().split("\\|");
            }
            if (!vs[0].equals("")) {
                for (int j = 0; j < vs.length; ++j) {
                    String[] vss = vs[j].split("=");
                    if (vss[0].startsWith("抗")) {
                        vss[0] = "抗" + vss[0].substring(1);
                    }
                    AddM(ql, vss[0], csz(vss[1]));
                }
            }
        }
        return ql;
    }
    
    public static void AddM(Ql quality, String key, double value) {
        int n = -1;
        switch (key.hashCode()) {
            case 25192340: {
                if (key.equals("抗物理")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 2028007458: {
                if (key.equals("抗附加封印攻击")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case -2131209939: {
                if (key.equals("抗附加混乱攻击")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1928545880: {
                if (key.equals("抗附加中毒攻击")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 858756197: {
                if (key.equals("混乱挣扎")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 723006298: {
                if (key.equals("封印挣扎")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 808530493: {
                if (key.equals("昏睡挣扎")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125198252: {
                if (key.equals("遗忘挣扎")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case -1845144554: {
                if (key.equals("抗一击毙命")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 784565671: {
                if (key.equals("抗致命率")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 821527: {
                if (key.equals("抗风")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 821056: {
                if (key.equals("抗雷")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
            case 810109: {
                if (key.equals("抗水")) {
                    n = 12;
                    break;
                }
                else {
                    break;
                }
            }
            case 811188: {
                if (key.equals("抗火")) {
                    n = 13;
                    break;
                }
                else {
                    break;
                }
            }
            case 24908275: {
                if (key.equals("抗仙法")) {
                    n = 14;
                    break;
                }
                else {
                    break;
                }
            }
            case 25147441: {
                if (key.equals("抗混乱")) {
                    n = 15;
                    break;
                }
                else {
                    break;
                }
            }
            case 25095177: {
                if (key.equals("抗昏睡")) {
                    n = 16;
                    break;
                }
                else {
                    break;
                }
            }
            case 25006182: {
                if (key.equals("抗封印")) {
                    n = 17;
                    break;
                }
                else {
                    break;
                }
            }
            case 24902684: {
                if (key.equals("抗中毒")) {
                    n = 18;
                    break;
                }
                else {
                    break;
                }
            }
            case 24907314: {
                if (key.equals("抗人法")) {
                    n = 19;
                    break;
                }
                else {
                    break;
                }
            }
            case -1837672917: {
                if (key.equals("抗中毒伤害")) {
                    n = 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 25424696: {
                if (key.equals("抗遗忘")) {
                    n = 21;
                    break;
                }
                else {
                    break;
                }
            }
            case 25515398: {
                if (key.equals("抗鬼火")) {
                    n = 22;
                    break;
                }
                else {
                    break;
                }
            }
            case 24897574: {
                if (key.equals("抗三尸")) {
                    n = 23;
                    break;
                }
                else {
                    break;
                }
            }
            case -1580214290: {
                if (key.equals("抗灵宝伤害")) {
                    n = 24;
                    break;
                }
                else {
                    break;
                }
            }
            case 295296601: {
                if (key.equals("抵抗灵宝伤害")) {
                    n = 25;
                    break;
                }
                else {
                    break;
                }
            }
            case -538500623: {
                if (key.equals("抵御强克效果")) {
                    n = 26;
                    break;
                }
                else {
                    break;
                }
            }
            case 61004801: {
                if (key.equals("抗无属性伤害")) {
                    n = 27;
                    break;
                }
                else {
                    break;
                }
            }
            case 25478145: {
                if (key.equals("抗震慑")) {
                    n = 28;
                    break;
                }
                else {
                    break;
                }
            }
            case -1284413843: {
                if (key.equals("抗震慑气血")) {
                    n = 29;
                    break;
                }
                else {
                    break;
                }
            }
            case -1284045886: {
                if (key.equals("抗震慑魔法")) {
                    n = 30;
                    break;
                }
                else {
                    break;
                }
            }
            case 25443507: {
                if (key.equals("抗金箍")) {
                    n = 31;
                    break;
                }
                else {
                    break;
                }
            }
            case 25055235: {
                if (key.equals("抗情网")) {
                    n = 32;
                    break;
                }
                else {
                    break;
                }
            }
            case -1597909547: {
                if (key.equals("抗浩然正气")) {
                    n = 33;
                    break;
                }
                else {
                    break;
                }
            }
            case -1268826016: {
                if (key.equals("抗青面獠牙")) {
                    n = 34;
                    break;
                }
                else {
                    break;
                }
            }
            case -1741749902: {
                if (key.equals("抗天魔解体")) {
                    n = 35;
                    break;
                }
                else {
                    break;
                }
            }
            case -1732293003: {
                if (key.equals("抗小楼夜哭")) {
                    n = 36;
                    break;
                }
                else {
                    break;
                }
            }
            case -1814824587: {
                if (key.equals("抗分光化影")) {
                    n = 37;
                    break;
                }
                else {
                    break;
                }
            }
            case 24921643: {
                if (key.equals("抗内丹")) {
                    n = 38;
                    break;
                }
                else {
                    break;
                }
            }
            case 24958385: {
                if (key.equals("抗反震")) {
                    n = 39;
                    break;
                }
                else {
                    break;
                }
            }
            case 791810161: {
                if (key.equals("抗龙伤害")) {
                    n = 40;
                    break;
                }
                else {
                    break;
                }
            }
            case -1487678765: {
                if (key.equals("忽视防御程度")) {
                    n = 41;
                    break;
                }
                else {
                    break;
                }
            }
            case -1487992193: {
                if (key.equals("忽视防御几率")) {
                    n = 42;
                    break;
                }
                else {
                    break;
                }
            }
            case -1901283295: {
                if (key.equals("反击忽视防御程度")) {
                    n = 43;
                    break;
                }
                else {
                    break;
                }
            }
            case -1901596723: {
                if (key.equals("反击忽视防御几率")) {
                    n = 44;
                    break;
                }
                else {
                    break;
                }
            }
            case 767189232: {
                if (key.equals("忽视风法")) {
                    n = 45;
                    break;
                }
                else {
                    break;
                }
            }
            case 767174631: {
                if (key.equals("忽视雷法")) {
                    n = 46;
                    break;
                }
                else {
                    break;
                }
            }
            case 766835274: {
                if (key.equals("忽视水法")) {
                    n = 47;
                    break;
                }
                else {
                    break;
                }
            }
            case 766868723: {
                if (key.equals("忽视火法")) {
                    n = 48;
                    break;
                }
                else {
                    break;
                }
            }
            case 766602309: {
                if (key.equals("忽视仙法")) {
                    n = 49;
                    break;
                }
                else {
                    break;
                }
            }
            case 766841475: {
                if (key.equals("忽视混乱")) {
                    n = 50;
                    break;
                }
                else {
                    break;
                }
            }
            case 766789211: {
                if (key.equals("忽视昏睡")) {
                    n = 51;
                    break;
                }
                else {
                    break;
                }
            }
            case 766700216: {
                if (key.equals("忽视封印")) {
                    n = 52;
                    break;
                }
                else {
                    break;
                }
            }
            case 766596718: {
                if (key.equals("忽视中毒")) {
                    n = 53;
                    break;
                }
                else {
                    break;
                }
            }
            case 766601348: {
                if (key.equals("忽视人法")) {
                    n = 54;
                    break;
                }
                else {
                    break;
                }
            }
            case -1999915528: {
                if (key.equals("忽视抗震慑")) {
                    n = 55;
                    break;
                }
                else {
                    break;
                }
            }
            case 767119521: {
                if (key.equals("忽视躲闪")) {
                    n = 56;
                    break;
                }
                else {
                    break;
                }
            }
            case 766634743: {
                if (key.equals("忽视反击")) {
                    n = 57;
                    break;
                }
                else {
                    break;
                }
            }
            case 1533321906: {
                if (key.equals("忽视仙法抗性率")) {
                    n = 58;
                    break;
                }
                else {
                    break;
                }
            }
            case 288414768: {
                if (key.equals("忽视仙法抗性程度")) {
                    n = 59;
                    break;
                }
                else {
                    break;
                }
            }
            case 767209432: {
                if (key.equals("忽视鬼火")) {
                    n = 60;
                    break;
                }
                else {
                    break;
                }
            }
            case 767118730: {
                if (key.equals("忽视遗忘")) {
                    n = 61;
                    break;
                }
                else {
                    break;
                }
            }
            case 24667777: {
                if (key.equals("强风法")) {
                    n = 62;
                    break;
                }
                else {
                    break;
                }
            }
            case 24653176: {
                if (key.equals("强雷法")) {
                    n = 63;
                    break;
                }
                else {
                    break;
                }
            }
            case 24313819: {
                if (key.equals("强水法")) {
                    n = 64;
                    break;
                }
                else {
                    break;
                }
            }
            case 24347268: {
                if (key.equals("强火法")) {
                    n = 65;
                    break;
                }
                else {
                    break;
                }
            }
            case 24080854: {
                if (key.equals("强仙法")) {
                    n = 66;
                    break;
                }
                else {
                    break;
                }
            }
            case 24320020: {
                if (key.equals("强混乱")) {
                    n = 67;
                    break;
                }
                else {
                    break;
                }
            }
            case 24267756: {
                if (key.equals("强昏睡")) {
                    n = 68;
                    break;
                }
                else {
                    break;
                }
            }
            case 24178761: {
                if (key.equals("强封印")) {
                    n = 69;
                    break;
                }
                else {
                    break;
                }
            }
            case 24075263: {
                if (key.equals("强中毒")) {
                    n = 70;
                    break;
                }
                else {
                    break;
                }
            }
            case 24079893: {
                if (key.equals("强人法")) {
                    n = 71;
                    break;
                }
                else {
                    break;
                }
            }
            case 1662142798: {
                if (key.equals("强中毒伤害")) {
                    n = 72;
                    break;
                }
                else {
                    break;
                }
            }
            case 24687977: {
                if (key.equals("强鬼火")) {
                    n = 73;
                    break;
                }
                else {
                    break;
                }
            }
            case 24597275: {
                if (key.equals("强遗忘")) {
                    n = 74;
                    break;
                }
                else {
                    break;
                }
            }
            case 746209154: {
                if (key.equals("强三尸虫")) {
                    n = 75;
                    break;
                }
                else {
                    break;
                }
            }
            case 133570847: {
                if (key.equals("强三尸虫回血程度")) {
                    n = 76;
                    break;
                }
                else {
                    break;
                }
            }
            case -252587737: {
                if (key.equals("增加强克效果")) {
                    n = 77;
                    break;
                }
                else {
                    break;
                }
            }
            case -1200392901: {
                if (key.equals("对召唤兽伤害")) {
                    n = 78;
                    break;
                }
                else {
                    break;
                }
            }
            case -307203320: {
                if (key.equals("加强攻击法术效果")) {
                    n = 79;
                    break;
                }
                else {
                    break;
                }
            }
            case 1027248131: {
                if (key.equals("加强加攻法术效果")) {
                    n = 80;
                    break;
                }
                else {
                    break;
                }
            }
            case 1008733975: {
                if (key.equals("加强防御法术效果")) {
                    n = 81;
                    break;
                }
                else {
                    break;
                }
            }
            case -281318022: {
                if (key.equals("加强加防法术效果")) {
                    n = 82;
                    break;
                }
                else {
                    break;
                }
            }
            case -791726641: {
                if (key.equals("加强速度法术效果")) {
                    n = 83;
                    break;
                }
                else {
                    break;
                }
            }
            case -1717393177: {
                if (key.equals("加强加速法术效果")) {
                    n = 84;
                    break;
                }
                else {
                    break;
                }
            }
            case -2091530424: {
                if (key.equals("加强霹雳效果")) {
                    n = 85;
                    break;
                }
                else {
                    break;
                }
            }
            case 1788583743: {
                if (key.equals("加强扶摇效果")) {
                    n = 86;
                    break;
                }
                else {
                    break;
                }
            }
            case 1868425545: {
                if (key.equals("加强沧波效果")) {
                    n = 87;
                    break;
                }
                else {
                    break;
                }
            }
            case 105974082: {
                if (key.equals("加强甘霖回血值")) {
                    n = 88;
                    break;
                }
                else {
                    break;
                }
            }
            case -1009414731: {
                if (key.equals("加强甘霖回血程度")) {
                    n = 89;
                    break;
                }
                else {
                    break;
                }
            }
            case 37329: {
                if (key.equals("金")) {
                    n = 90;
                    break;
                }
                else {
                    break;
                }
            }
            case 26408: {
                if (key.equals("木")) {
                    n = 91;
                    break;
                }
                else {
                    break;
                }
            }
            case 22303: {
                if (key.equals("土")) {
                    n = 92;
                    break;
                }
                else {
                    break;
                }
            }
            case 27700: {
                if (key.equals("水")) {
                    n = 93;
                    break;
                }
                else {
                    break;
                }
            }
            case 28779: {
                if (key.equals("火")) {
                    n = 94;
                    break;
                }
                else {
                    break;
                }
            }
            case 747249735: {
                if (key.equals("强力克金")) {
                    n = 95;
                    break;
                }
                else {
                    break;
                }
            }
            case 747238814: {
                if (key.equals("强力克木")) {
                    n = 96;
                    break;
                }
                else {
                    break;
                }
            }
            case 747234709: {
                if (key.equals("强力克土")) {
                    n = 97;
                    break;
                }
                else {
                    break;
                }
            }
            case 747240106: {
                if (key.equals("强力克水")) {
                    n = 98;
                    break;
                }
                else {
                    break;
                }
            }
            case 747241185: {
                if (key.equals("强力克火")) {
                    n = 99;
                    break;
                }
                else {
                    break;
                }
            }
            case -955631560: {
                if (key.equals("无属性伤害")) {
                    n = 100;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192790294: {
                if (key.equals("风法伤害")) {
                    n = 101;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178758733: {
                if (key.equals("雷法伤害")) {
                    n = 102;
                    break;
                }
                else {
                    break;
                }
            }
            case 852636656: {
                if (key.equals("水法伤害")) {
                    n = 103;
                    break;
                }
                else {
                    break;
                }
            }
            case 884781145: {
                if (key.equals("火法伤害")) {
                    n = 104;
                    break;
                }
                else {
                    break;
                }
            }
            case 628757291: {
                if (key.equals("仙法伤害")) {
                    n = 105;
                    break;
                }
                else {
                    break;
                }
            }
            case 27177057: {
                if (key.equals("毒伤害")) {
                    n = 106;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212202494: {
                if (key.equals("鬼火伤害")) {
                    n = 107;
                    break;
                }
                else {
                    break;
                }
            }
            case 618473630: {
                if (key.equals("三尸伤害")) {
                    n = 108;
                    break;
                }
                else {
                    break;
                }
            }
            case 1179044208: {
                if (key.equals("雷法狂暴")) {
                    n = 109;
                    break;
                }
                else {
                    break;
                }
            }
            case 1193075769: {
                if (key.equals("风法狂暴")) {
                    n = 110;
                    break;
                }
                else {
                    break;
                }
            }
            case 852922131: {
                if (key.equals("水法狂暴")) {
                    n = 111;
                    break;
                }
                else {
                    break;
                }
            }
            case -1157037792: {
                if (key.equals("水系狂暴几率")) {
                    n = 112;
                    break;
                }
                else {
                    break;
                }
            }
            case 885066620: {
                if (key.equals("火法狂暴")) {
                    n = 113;
                    break;
                }
                else {
                    break;
                }
            }
            case 629042766: {
                if (key.equals("仙法狂暴")) {
                    n = 114;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212487969: {
                if (key.equals("鬼火狂暴")) {
                    n = 115;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006621742: {
                if (key.equals("三尸虫狂暴")) {
                    n = 116;
                    break;
                }
                else {
                    break;
                }
            }
            case -808889493: {
                if (key.equals("雷法狂暴程度")) {
                    n = 117;
                    break;
                }
                else {
                    break;
                }
            }
            case -209461260: {
                if (key.equals("风法狂暴程度")) {
                    n = 118;
                    break;
                }
                else {
                    break;
                }
            }
            case -679592882: {
                if (key.equals("水法狂暴程度")) {
                    n = 119;
                    break;
                }
                else {
                    break;
                }
            }
            case 146489975: {
                if (key.equals("火法狂暴程度")) {
                    n = 120;
                    break;
                }
                else {
                    break;
                }
            }
            case -1079297847: {
                if (key.equals("仙法狂暴程度")) {
                    n = 121;
                    break;
                }
                else {
                    break;
                }
            }
            case 1265793756: {
                if (key.equals("鬼火狂暴程度")) {
                    n = 122;
                    break;
                }
                else {
                    break;
                }
            }
            case -75829079: {
                if (key.equals("三尸虫狂暴程度")) {
                    n = 123;
                    break;
                }
                else {
                    break;
                }
            }
            case 36324623: {
                if (key.equals("躲闪率")) {
                    n = 124;
                    break;
                }
                else {
                    break;
                }
            }
            case 21296505: {
                if (key.equals("反击率")) {
                    n = 125;
                    break;
                }
                else {
                    break;
                }
            }
            case 660150973: {
                if (key.equals("反击次数")) {
                    n = 126;
                    break;
                }
                else {
                    break;
                }
            }
            case 36073802: {
                if (key.equals("连击率")) {
                    n = 127;
                    break;
                }
                else {
                    break;
                }
            }
            case 1118247180: {
                if (key.equals("连击次数")) {
                    n = 128;
                    break;
                }
                else {
                    break;
                }
            }
            case 21435447: {
                if (key.equals("命中率")) {
                    n = 129;
                    break;
                }
                else {
                    break;
                }
            }
            case 32670622: {
                if (key.equals("致命率")) {
                    n = 130;
                    break;
                }
                else {
                    break;
                }
            }
            case 29076885: {
                if (key.equals("狂暴率")) {
                    n = 131;
                    break;
                }
                else {
                    break;
                }
            }
            case 21844461: {
                if (key.equals("反震率")) {
                    n = 132;
                    break;
                }
                else {
                    break;
                }
            }
            case 677254229: {
                if (key.equals("反震程度")) {
                    n = 133;
                    break;
                }
                else {
                    break;
                }
            }
            case -1967484242: {
                if (key.equals("仙法连击率")) {
                    n = 134;
                    break;
                }
                else {
                    break;
                }
            }
            case -862510040: {
                if (key.equals("仙法连击次数")) {
                    n = 135;
                    break;
                }
                else {
                    break;
                }
            }
            case 577051339: {
                if (key.equals("附加封印攻击")) {
                    n = 136;
                    break;
                }
                else {
                    break;
                }
            }
            case 712801238: {
                if (key.equals("附加混乱攻击")) {
                    n = 137;
                    break;
                }
                else {
                    break;
                }
            }
            case 477589761: {
                if (key.equals("附加中毒攻击")) {
                    n = 138;
                    break;
                }
                else {
                    break;
                }
            }
            case 1046995715: {
                if (key.equals("附加风法攻击")) {
                    n = 139;
                    break;
                }
                else {
                    break;
                }
            }
            case 738986566: {
                if (key.equals("附加火法攻击")) {
                    n = 140;
                    break;
                }
                else {
                    break;
                }
            }
            case 706842077: {
                if (key.equals("附加水法攻击")) {
                    n = 141;
                    break;
                }
                else {
                    break;
                }
            }
            case 1032964154: {
                if (key.equals("附加雷法攻击")) {
                    n = 142;
                    break;
                }
                else {
                    break;
                }
            }
            case 1030607782: {
                if (key.equals("附加震慑攻击")) {
                    n = 143;
                    break;
                }
                else {
                    break;
                }
            }
            case 472679051: {
                if (key.equals("附加三尸攻击")) {
                    n = 144;
                    break;
                }
                else {
                    break;
                }
            }
            case 626795181: {
                if (key.equals("伤害减免")) {
                    n = 145;
                    break;
                }
                else {
                    break;
                }
            }
            case -1353962039: {
                if (key.equals("仙法伤害减免")) {
                    n = 146;
                    break;
                }
                else {
                    break;
                }
            }
            case -1006974230: {
                if (key.equals("物理伤害减免")) {
                    n = 147;
                    break;
                }
                else {
                    break;
                }
            }
            case -1284476139: {
                if (key.equals("抗震慑效果")) {
                    n = 148;
                    break;
                }
                else {
                    break;
                }
            }
            case 856227905: {
                if (key.equals("法术暴击")) {
                    n = 149;
                    break;
                }
                else {
                    break;
                }
            }
            case 773290854: {
                if (key.equals("法术暴击率")) {
                    n = 150;
                    break;
                }
                else {
                    break;
                }
            }
            case -1798024792: {
                if (key.equals("法术暴击几率")) {
                    n = 151;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178943237: {
                if (key.equals("雷法暴击")) {
                    n = 152;
                    break;
                }
                else {
                    break;
                }
            }
            case -2107435742: {
                if (key.equals("雷法暴击率")) {
                    n = 153;
                    break;
                }
                else {
                    break;
                }
            }
            case -906236052: {
                if (key.equals("雷法暴击几率")) {
                    n = 154;
                    break;
                }
                else {
                    break;
                }
            }
            case 884965649: {
                if (key.equals("火法暴击")) {
                    n = 155;
                    break;
                }
                else {
                    break;
                }
            }
            case 1664160918: {
                if (key.equals("火法暴击率")) {
                    n = 156;
                    break;
                }
                else {
                    break;
                }
            }
            case 49143416: {
                if (key.equals("火法暴击几率")) {
                    n = 157;
                    break;
                }
                else {
                    break;
                }
            }
            case 852821160: {
                if (key.equals("水法暴击")) {
                    n = 158;
                    break;
                }
                else {
                    break;
                }
            }
            case 667681759: {
                if (key.equals("水法暴击率")) {
                    n = 159;
                    break;
                }
                else {
                    break;
                }
            }
            case -776939441: {
                if (key.equals("水法暴击几率")) {
                    n = 160;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192974798: {
                if (key.equals("风法暴击")) {
                    n = 161;
                    break;
                }
                else {
                    break;
                }
            }
            case -1672457351: {
                if (key.equals("风法暴击率")) {
                    n = 162;
                    break;
                }
                else {
                    break;
                }
            }
            case -306807819: {
                if (key.equals("风法暴击几率")) {
                    n = 163;
                    break;
                }
                else {
                    break;
                }
            }
            case 628941795: {
                if (key.equals("仙法暴击")) {
                    n = 164;
                    break;
                }
                else {
                    break;
                }
            }
            case -1977611260: {
                if (key.equals("仙法暴击率")) {
                    n = 165;
                    break;
                }
                else {
                    break;
                }
            }
            case -1176644406: {
                if (key.equals("仙法暴击几率")) {
                    n = 166;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212386998: {
                if (key.equals("鬼火暴击")) {
                    n = 167;
                    break;
                }
                else {
                    break;
                }
            }
            case -1070679151: {
                if (key.equals("鬼火暴击率")) {
                    n = 168;
                    break;
                }
                else {
                    break;
                }
            }
            case 1168447197: {
                if (key.equals("鬼火暴击几率")) {
                    n = 169;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006520771: {
                if (key.equals("三尸虫暴击")) {
                    n = 170;
                    break;
                }
                else {
                    break;
                }
            }
            case 2072631332: {
                if (key.equals("三尸虫暴击率")) {
                    n = 171;
                    break;
                }
                else {
                    break;
                }
            }
            case -173175638: {
                if (key.equals("三尸虫暴击几率")) {
                    n = 172;
                    break;
                }
                else {
                    break;
                }
            }
            case -1797980601: {
                if (key.equals("法术暴击增伤")) {
                    n = 173;
                    break;
                }
                else {
                    break;
                }
            }
            case -906191861: {
                if (key.equals("雷法暴击增伤")) {
                    n = 174;
                    break;
                }
                else {
                    break;
                }
            }
            case -306763628: {
                if (key.equals("风法暴击增伤")) {
                    n = 175;
                    break;
                }
                else {
                    break;
                }
            }
            case -776895250: {
                if (key.equals("水法暴击增伤")) {
                    n = 176;
                    break;
                }
                else {
                    break;
                }
            }
            case 49187607: {
                if (key.equals("火法暴击增伤")) {
                    n = 177;
                    break;
                }
                else {
                    break;
                }
            }
            case -1176600215: {
                if (key.equals("仙法暴击增伤")) {
                    n = 178;
                    break;
                }
                else {
                    break;
                }
            }
            case 1168491388: {
                if (key.equals("鬼火暴击增伤")) {
                    n = 179;
                    break;
                }
                else {
                    break;
                }
            }
            case -173131447: {
                if (key.equals("三尸虫暴击增伤")) {
                    n = 180;
                    break;
                }
                else {
                    break;
                }
            }
            case 856562674: {
                if (key.equals("法术躲闪")) {
                    n = 181;
                    break;
                }
                else {
                    break;
                }
            }
            case 723526: {
                if (key.equals("增伤")) {
                    n = 182;
                    break;
                }
                else {
                    break;
                }
            }
            case 901815747: {
                if (key.equals("物理增伤")) {
                    n = 183;
                    break;
                }
                else {
                    break;
                }
            }
            case 856082378: {
                if (key.equals("法术命中")) {
                    n = 184;
                    break;
                }
                else {
                    break;
                }
            }
            case 768779517: {
                if (key.equals("法术命中率")) {
                    n = 185;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176441338: {
                if (key.equals("震慑命中")) {
                    n = 186;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176227316: {
                if (key.equals("震慑法术命中")) {
                    n = 187;
                    break;
                }
                else {
                    break;
                }
            }
            case 2109972685: {
                if (key.equals("震慑命中率")) {
                    n = 188;
                    break;
                }
                else {
                    break;
                }
            }
            case 884820122: {
                if (key.equals("火法命中")) {
                    n = 189;
                    break;
                }
                else {
                    break;
                }
            }
            case 101112980: {
                if (key.equals("火法法术命中")) {
                    n = 190;
                    break;
                }
                else {
                    break;
                }
            }
            case 1659649581: {
                if (key.equals("火法命中率")) {
                    n = 191;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178797710: {
                if (key.equals("雷法命中")) {
                    n = 192;
                    break;
                }
                else {
                    break;
                }
            }
            case -854266488: {
                if (key.equals("雷法法术命中")) {
                    n = 193;
                    break;
                }
                else {
                    break;
                }
            }
            case -2111947079: {
                if (key.equals("雷法命中率")) {
                    n = 194;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192829271: {
                if (key.equals("风法命中")) {
                    n = 195;
                    break;
                }
                else {
                    break;
                }
            }
            case -254838255: {
                if (key.equals("风法法术命中")) {
                    n = 196;
                    break;
                }
                else {
                    break;
                }
            }
            case -1676968688: {
                if (key.equals("风法命中率")) {
                    n = 197;
                    break;
                }
                else {
                    break;
                }
            }
            case 852675633: {
                if (key.equals("水法命中")) {
                    n = 198;
                    break;
                }
                else {
                    break;
                }
            }
            case -724969877: {
                if (key.equals("水法法术命中")) {
                    n = 199;
                    break;
                }
                else {
                    break;
                }
            }
            case 663170422: {
                if (key.equals("水法命中率")) {
                    n = 200;
                    break;
                }
                else {
                    break;
                }
            }
            case 849756115: {
                if (key.equals("毒法命中")) {
                    n = 201;
                    break;
                }
                else {
                    break;
                }
            }
            case 764340621: {
                if (key.equals("毒法法术命中")) {
                    n = 202;
                    break;
                }
                else {
                    break;
                }
            }
            case 572665364: {
                if (key.equals("毒法命中率")) {
                    n = 203;
                    break;
                }
                else {
                    break;
                }
            }
            case 722884895: {
                if (key.equals("封印命中")) {
                    n = 204;
                    break;
                }
                else {
                    break;
                }
            }
            case -899817511: {
                if (key.equals("封印法术命中")) {
                    n = 205;
                    break;
                }
                else {
                    break;
                }
            }
            case 934624840: {
                if (key.equals("封印命中率")) {
                    n = 206;
                    break;
                }
                else {
                    break;
                }
            }
            case 858634794: {
                if (key.equals("混乱命中")) {
                    n = 207;
                    break;
                }
                else {
                    break;
                }
            }
            case 706816548: {
                if (key.equals("混乱法术命中")) {
                    n = 208;
                    break;
                }
                else {
                    break;
                }
            }
            case 847904413: {
                if (key.equals("混乱命中率")) {
                    n = 209;
                    break;
                }
                else {
                    break;
                }
            }
            case 808409090: {
                if (key.equals("昏睡命中")) {
                    n = 210;
                    break;
                }
                else {
                    break;
                }
            }
            case -315444740: {
                if (key.equals("昏睡法术命中")) {
                    n = 211;
                    break;
                }
                else {
                    break;
                }
            }
            case -709092411: {
                if (key.equals("昏睡命中率")) {
                    n = 212;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125076849: {
                if (key.equals("遗忘命中")) {
                    n = 213;
                    break;
                }
                else {
                    break;
                }
            }
            case -940406357: {
                if (key.equals("遗忘法术命中")) {
                    n = 214;
                    break;
                }
                else {
                    break;
                }
            }
            case 517673526: {
                if (key.equals("遗忘命中率")) {
                    n = 215;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212241471: {
                if (key.equals("鬼火命中")) {
                    n = 216;
                    break;
                }
                else {
                    break;
                }
            }
            case 1220416761: {
                if (key.equals("鬼火法术命中")) {
                    n = 217;
                    break;
                }
                else {
                    break;
                }
            }
            case -1075190488: {
                if (key.equals("鬼火命中率")) {
                    n = 218;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006375244: {
                if (key.equals("三尸虫命中")) {
                    n = 219;
                    break;
                }
                else {
                    break;
                }
            }
            case 1877628825: {
                if (key.equals("三尸法术命中")) {
                    n = 220;
                    break;
                }
                else {
                    break;
                }
            }
            case 2068119995: {
                if (key.equals("三尸虫命中率")) {
                    n = 221;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176921634: {
                if (key.equals("震慑躲闪")) {
                    n = 222;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176707612: {
                if (key.equals("震慑法术躲闪")) {
                    n = 223;
                    break;
                }
                else {
                    break;
                }
            }
            case 885300418: {
                if (key.equals("火法躲闪")) {
                    n = 224;
                    break;
                }
                else {
                    break;
                }
            }
            case 101593276: {
                if (key.equals("火法法术躲闪")) {
                    n = 225;
                    break;
                }
                else {
                    break;
                }
            }
            case 1179278006: {
                if (key.equals("雷法躲闪")) {
                    n = 226;
                    break;
                }
                else {
                    break;
                }
            }
            case -853786192: {
                if (key.equals("雷法法术躲闪")) {
                    n = 227;
                    break;
                }
                else {
                    break;
                }
            }
            case 1193309567: {
                if (key.equals("风法躲闪")) {
                    n = 228;
                    break;
                }
                else {
                    break;
                }
            }
            case -254357959: {
                if (key.equals("风法法术躲闪")) {
                    n = 229;
                    break;
                }
                else {
                    break;
                }
            }
            case 853155929: {
                if (key.equals("水法躲闪")) {
                    n = 230;
                    break;
                }
                else {
                    break;
                }
            }
            case -724489581: {
                if (key.equals("水法法术躲闪")) {
                    n = 231;
                    break;
                }
                else {
                    break;
                }
            }
            case 850236411: {
                if (key.equals("毒法躲闪")) {
                    n = 232;
                    break;
                }
                else {
                    break;
                }
            }
            case 764820917: {
                if (key.equals("毒法法术躲闪")) {
                    n = 233;
                    break;
                }
                else {
                    break;
                }
            }
            case 723365191: {
                if (key.equals("封印躲闪")) {
                    n = 234;
                    break;
                }
                else {
                    break;
                }
            }
            case -899337215: {
                if (key.equals("封印法术躲闪")) {
                    n = 235;
                    break;
                }
                else {
                    break;
                }
            }
            case 859115090: {
                if (key.equals("混乱躲闪")) {
                    n = 236;
                    break;
                }
                else {
                    break;
                }
            }
            case 707296844: {
                if (key.equals("混乱法术躲闪")) {
                    n = 237;
                    break;
                }
                else {
                    break;
                }
            }
            case 808889386: {
                if (key.equals("昏睡躲闪")) {
                    n = 238;
                    break;
                }
                else {
                    break;
                }
            }
            case -314964444: {
                if (key.equals("昏睡法术躲闪")) {
                    n = 239;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125557145: {
                if (key.equals("遗忘躲闪")) {
                    n = 240;
                    break;
                }
                else {
                    break;
                }
            }
            case -939926061: {
                if (key.equals("遗忘法术躲闪")) {
                    n = 241;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212721767: {
                if (key.equals("鬼火躲闪")) {
                    n = 242;
                    break;
                }
                else {
                    break;
                }
            }
            case 1220897057: {
                if (key.equals("鬼火法术躲闪")) {
                    n = 243;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006855540: {
                if (key.equals("三尸虫躲闪")) {
                    n = 244;
                    break;
                }
                else {
                    break;
                }
            }
            case 1878109121: {
                if (key.equals("三尸法术躲闪")) {
                    n = 245;
                    break;
                }
                else {
                    break;
                }
            }
            case -954257074: {
                if (key.equals("水法伤害减免")) {
                    n = 246;
                    break;
                }
                else {
                    break;
                }
            }
            case -484125452: {
                if (key.equals("风法伤害减免")) {
                    n = 247;
                    break;
                }
                else {
                    break;
                }
            }
            case -1083553685: {
                if (key.equals("雷法伤害减免")) {
                    n = 248;
                    break;
                }
                else {
                    break;
                }
            }
            case -128174217: {
                if (key.equals("火法伤害减免")) {
                    n = 249;
                    break;
                }
                else {
                    break;
                }
            }
            case 991129564: {
                if (key.equals("鬼火伤害减免")) {
                    n = 250;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                quality.setRolekwl(quality.getRolekwl() + value);
                break;
            }
            case 1: {
                quality.setKfjfy(quality.getKfjfy() + value);
                break;
            }
            case 2: {
                quality.setKfjhl(quality.getKfjhl() + value);
                break;
            }
            case 3: {
                quality.setKfjzd(quality.getKfjzd() + value);
                break;
            }
            case 4: {
                quality.setHlzz(quality.getHlzz() + value);
                break;
            }
            case 5: {
                quality.setBhzz(quality.getBhzz() + value);
                break;
            }
            case 6: {
                quality.setHszz(quality.getHszz() + value);
                break;
            }
            case 7: {
                quality.setYwzz(quality.getYwzz() + value);
                break;
            }
            case 8: {
                quality.setKyjbm(quality.getKyjbm() + value);
                break;
            }
            case 9: {
                quality.setKzml(quality.getKzml() + value);
                break;
            }
            case 10: {
                quality.setRolekff(quality.getRolekff() + value);
                break;
            }
            case 11: {
                quality.setRoleklf(quality.getRoleklf() + value);
                break;
            }
            case 12: {
                quality.setRoleksf(quality.getRoleksf() + value);
                break;
            }
            case 13: {
                quality.setRolekhf(quality.getRolekhf() + value);
                break;
            }
            case 14: {
                quality.setRolekff(quality.getRolekff() + value);
                quality.setRoleklf(quality.getRoleklf() + value);
                quality.setRoleksf(quality.getRoleksf() + value);
                quality.setRolekhf(quality.getRolekhf() + value);
                break;
            }
            case 15: {
                quality.setRolekhl(quality.getRolekhl() + value);
                break;
            }
            case 16: {
                quality.setRolekhs(quality.getRolekhs() + value);
                break;
            }
            case 17: {
                quality.setRolekfy(quality.getRolekfy() + value);
                break;
            }
            case 18: {
                quality.setRolekzd(quality.getRolekzd() + value);
                break;
            }
            case 19: {
                quality.setRolekhl(quality.getRolekhl() + value);
                quality.setRolekhs(quality.getRolekhs() + value);
                quality.setRolekfy(quality.getRolekfy() + value);
                quality.setRolekzd(quality.getRolekzd() + value);
                break;
            }
            case 20: {
                quality.setKzds(quality.getKzds() + value);
                break;
            }
            case 21: {
                quality.setRolekyw(quality.getRolekyw() + value);
                break;
            }
            case 22: {
                quality.setRolekgh(quality.getRolekgh() + value);
                break;
            }
            case 23: {
                quality.setRoleksc(quality.getRoleksc() + value);
                break;
            }
            case 24:
            case 25: {
                quality.setRoleklb(quality.getRoleklb() + value);
                break;
            }
            case 26: {
                quality.setK_qk(quality.getK_qk() + value);
                break;
            }
            case 27: {
                quality.setK_wsxsh(quality.getK_wsxsh() + value);
                break;
            }
            case 28: {
                quality.setRolekzs(quality.getRolekzs() + value);
                break;
            }
            case 29: {
                quality.setK_zshp(quality.getK_zshp() + value);
                break;
            }
            case 30: {
                quality.setK_zsmp(quality.getK_zsmp() + value);
                break;
            }
            case 31: {
                quality.setK_jge(quality.getK_jge() + value);
                break;
            }
            case 32: {
                quality.setK_qw(quality.getK_qw() + value);
                break;
            }
            case 33: {
                quality.setK_ndhr(quality.getK_ndhr() + value);
                break;
            }
            case 34: {
                quality.setK_ndqm(quality.getK_ndqm() + value);
                break;
            }
            case 35: {
                quality.setK_ndtm(quality.getK_ndtm() + value);
                break;
            }
            case 36: {
                quality.setK_ndxl(quality.getK_ndxl() + value);
                break;
            }
            case 37: {
                quality.setK_ndfg(quality.getK_ndfg() + value);
                break;
            }
            case 38: {
                quality.setK_ndhr(quality.getK_ndhr() + value);
                quality.setK_ndqm(quality.getK_ndqm() + value);
                quality.setK_ndtm(quality.getK_ndtm() + value);
                quality.setK_ndxl(quality.getK_ndxl() + value);
                quality.setK_ndfg(quality.getK_ndfg() + value);
                break;
            }
            case 39: {
                quality.setKfz(quality.getKfz() + value);
                break;
            }
            case 40: {
                quality.setKlsh(quality.getKlsh() + value);
                break;
            }
            case 41: {
                quality.setRolehsfyv(quality.getRolehsfyv() + value);
                break;
            }
            case 42: {
                quality.setRolehsfyl(quality.getRolehsfyl() + value);
                break;
            }
            case 43: {
                quality.setFjhfyv(quality.getFjhfyv() + value);
                break;
            }
            case 44: {
                quality.setFjhfyl(quality.getFjhfyl() + value);
                break;
            }
            case 45: {
                quality.setRolehsff(quality.getRolehsff() + value);
                break;
            }
            case 46: {
                quality.setRolehslf(quality.getRolehslf() + value);
                break;
            }
            case 47: {
                quality.setRolehssf(quality.getRolehssf() + value);
                break;
            }
            case 48: {
                quality.setRolehshf(quality.getRolehshf() + value);
                break;
            }
            case 49: {
                quality.setRolehsff(quality.getRolehsff() + value);
                quality.setRolehslf(quality.getRolehslf() + value);
                quality.setRolehssf(quality.getRolehssf() + value);
                quality.setRolehshf(quality.getRolehshf() + value);
                break;
            }
            case 50: {
                quality.setRolehshl(quality.getRolehshl() + value);
                break;
            }
            case 51: {
                quality.setRolehshs(quality.getRolehshs() + value);
                break;
            }
            case 52: {
                quality.setRolehsfy(quality.getRolehsfy() + value);
                break;
            }
            case 53: {
                quality.setRolehszd(quality.getRolehszd() + value);
                break;
            }
            case 54: {
                quality.setRolehshl(quality.getRolehshl() + value);
                quality.setRolehshs(quality.getRolehshs() + value);
                quality.setRolehsfy(quality.getRolehsfy() + value);
                quality.setRolehszd(quality.getRolehszd() + value);
                break;
            }
            case 55: {
                quality.setRolehszs(quality.getRolehszs() + value);
                break;
            }
            case 56: {
                quality.setRolehsds(quality.getRolehsds() + value);
                break;
            }
            case 57: {
                quality.setRolehsfj(quality.getRolehsfj() + value);
                break;
            }
            case 58: {
                quality.setRolehsxfkl(quality.getRolehsxfkl() + value);
                break;
            }
            case 59: {
                quality.setRolehsxfcd(quality.getRolehsxfcd() + value);
                break;
            }
            case 60: {
                quality.setRolehsgh(quality.getRolehsgh() + value);
                break;
            }
            case 61: {
                quality.setRolehsyw(quality.getRolehsyw() + value);
                break;
            }
            case 62: {
                quality.setRoleqff(quality.getRoleqff() + value);
                break;
            }
            case 63: {
                quality.setRoleqlf(quality.getRoleqlf() + value);
                break;
            }
            case 64: {
                quality.setRoleqsf(quality.getRoleqsf() + value);
                break;
            }
            case 65: {
                quality.setRoleqhf(quality.getRoleqhf() + value);
                break;
            }
            case 66: {
                quality.setRoleqff(quality.getRoleqff() + value);
                quality.setRoleqlf(quality.getRoleqlf() + value);
                quality.setRoleqsf(quality.getRoleqsf() + value);
                quality.setRoleqhf(quality.getRoleqhf() + value);
                break;
            }
            case 67: {
                quality.setRoleqhl(quality.getRoleqhl() + value);
                break;
            }
            case 68: {
                quality.setRoleqhs(quality.getRoleqhs() + value);
                break;
            }
            case 69: {
                quality.setRoleqfy(quality.getRoleqfy() + value);
                break;
            }
            case 70: {
                quality.setRoleqzd(quality.getRoleqzd() + value);
                break;
            }
            case 71: {
                quality.setRoleqhl(quality.getRoleqhl() + value);
                quality.setRoleqhs(quality.getRoleqhs() + value);
                quality.setRoleqfy(quality.getRoleqfy() + value);
                quality.setRoleqzd(quality.getRoleqzd() + value);
                break;
            }
            case 72: {
                quality.setQzds(quality.getQzds() + value);
                break;
            }
            case 73: {
                quality.setRolegstronghostfire(quality.getRolegstronghostfire() + value);
                break;
            }
            case 74: {
                quality.setRolestrongforget(quality.getRolestrongforget() + value);
                break;
            }
            case 75: {
                quality.setRolestrongbodyblood(quality.getRolestrongbodyblood() + value);
                break;
            }
            case 76: {
                quality.setRolestrongbodyblooddeep(quality.getRolestrongbodyblooddeep() + value);
                break;
            }
            case 77: {
                quality.setQ_qk(quality.getQ_qk() + value);
                break;
            }
            case 78: {
                quality.setQ_zhssh(quality.getQ_zhssh() + value);
                break;
            }
            case 79:
            case 80: {
                quality.setJqgjfs(quality.getJqgjfs() + value);
                break;
            }
            case 81:
            case 82: {
                quality.setJqfyfs(quality.getJqfyfs() + value);
                break;
            }
            case 83:
            case 84: {
                quality.setJqsdfs(quality.getJqsdfs() + value);
                break;
            }
            case 85: {
                quality.setQlpl(quality.getQlpl() + value);
                break;
            }
            case 86: {
                quality.setQlfy(quality.getQlfy() + value);
                break;
            }
            case 87: {
                quality.setQlcb(quality.getQlcb() + value);
                break;
            }
            case 88: {
                quality.setQlglv(quality.getQlglv() + value);
                break;
            }
            case 89: {
                quality.setQlglc(quality.getQlglc() + value);
                break;
            }
            case 90: {
                quality.setRolewxj(quality.getRolewxj() + value);
                break;
            }
            case 91: {
                quality.setRolewxm(quality.getRolewxm() + value);
                break;
            }
            case 92: {
                quality.setRolewxt(quality.getRolewxt() + value);
                break;
            }
            case 93: {
                quality.setRolewxs(quality.getRolewxs() + value);
                break;
            }
            case 94: {
                quality.setRolewxh(quality.getRolewxh() + value);
                break;
            }
            case 95: {
                quality.setRolewxqkj(quality.getRolewxqkj() + value);
                break;
            }
            case 96: {
                quality.setRolewxqkm(quality.getRolewxqkm() + value);
                break;
            }
            case 97: {
                quality.setRolewxqkt(quality.getRolewxqkt() + value);
                break;
            }
            case 98: {
                quality.setRolewxqks(quality.getRolewxqks() + value);
                break;
            }
            case 99: {
                quality.setRolewxqkh(quality.getRolewxqkh() + value);
                break;
            }
            case 100: {
                quality.setRolewsxsh(quality.getRolewsxsh() + value);
                break;
            }
            case 101: {
                quality.setRoleffsh(quality.getRoleffsh() + value);
                break;
            }
            case 102: {
                quality.setRolelfsh(quality.getRolelfsh() + value);
                break;
            }
            case 103: {
                quality.setRolesfsh(quality.getRolesfsh() + value);
                break;
            }
            case 104: {
                quality.setRolehfsh(quality.getRolehfsh() + value);
                break;
            }
            case 105: {
                quality.setRoleffsh(quality.getRoleffsh() + value);
                quality.setRolelfsh(quality.getRolelfsh() + value);
                quality.setRolesfsh(quality.getRolesfsh() + value);
                quality.setRolehfsh(quality.getRolehfsh() + value);
                break;
            }
            case 106: {
                quality.setRolezdsh(quality.getRolezdsh() + value);
                break;
            }
            case 107: {
                quality.setRoleghsh(quality.getRoleghsh() + value);
                break;
            }
            case 108: {
                quality.setRolesssh(quality.getRolesssh() + value);
                break;
            }
            case 109: {
                quality.setRolelfkb(quality.getRolelfkb() + value);
                break;
            }
            case 110: {
                quality.setRoleffkb(quality.getRoleffkb() + value);
                break;
            }
            case 111:
            case 112: {
                quality.setRolesfkb(quality.getRolesfkb() + value);
                break;
            }
            case 113: {
                quality.setRolehfkb(quality.getRolehfkb() + value);
                break;
            }
            case 114: {
                quality.setRolelfkb(quality.getRolelfkb() + value);
                quality.setRoleffkb(quality.getRoleffkb() + value);
                quality.setRolesfkb(quality.getRolesfkb() + value);
                quality.setRolehfkb(quality.getRolehfkb() + value);
                break;
            }
            case 115: {
                quality.setRoleghkb(quality.getRoleghkb() + value);
                break;
            }
            case 116: {
                quality.setRolesskb(quality.getRolesskb() + value);
                break;
            }
            case 117: {
                quality.setBlfcd(quality.getBlfcd() + value);
                break;
            }
            case 118: {
                quality.setBffcd(quality.getBffcd() + value);
                break;
            }
            case 119: {
                quality.setBsfcd(quality.getBsfcd() + value);
                break;
            }
            case 120: {
                quality.setBhfcd(quality.getBhfcd() + value);
                break;
            }
            case 121: {
                quality.setBlfcd(quality.getBlfcd() + value);
                quality.setBffcd(quality.getBffcd() + value);
                quality.setBsfcd(quality.getBsfcd() + value);
                quality.setBhfcd(quality.getBhfcd() + value);
                break;
            }
            case 122: {
                quality.setBghcd(quality.getBghcd() + value);
                break;
            }
            case 123: {
                quality.setBsccd(quality.getBsccd() + value);
                break;
            }
            case 124: {
                quality.setRolefdsl(quality.getRolefdsl() + value);
                break;
            }
            case 125: {
                quality.setRoleffjl(quality.getRoleffjl() + value);
                break;
            }
            case 126: {
                quality.setRoleffjv(quality.getRoleffjv() + value);
                break;
            }
            case 127: {
                quality.setRolefljl(quality.getRolefljl() + value);
                break;
            }
            case 128: {
                quality.setRolefljv(quality.getRolefljv() + value);
                break;
            }
            case 129: {
                quality.setRolefmzl(quality.getRolefmzl() + value);
                break;
            }
            case 130: {
                quality.setRolefzml(quality.getRolefzml() + value);
                break;
            }
            case 131: {
                quality.setRolefkbl(quality.getRolefkbl() + value);
                break;
            }
            case 132: {
                quality.setRoleffzl(quality.getRoleffzl() + value);
                break;
            }
            case 133: {
                quality.setRoleffzcd(quality.getRoleffzcd() + value);
                break;
            }
            case 134: {
                quality.setRolexfljl(quality.getRolexfljl() + value);
                break;
            }
            case 135: {
                quality.setRolexfljs(quality.getRolexfljs() + value);
                break;
            }
            case 136: {
                quality.setF_f(quality.getF_f() + value);
                break;
            }
            case 137: {
                quality.setF_h(quality.getF_h() + value);
                break;
            }
            case 138: {
                quality.setF_d(quality.getF_d() + value);
                break;
            }
            case 139: {
                quality.setF_xf(quality.getF_xf() + value);
                break;
            }
            case 140: {
                quality.setF_xh(quality.getF_xh() + value);
                break;
            }
            case 141: {
                quality.setF_xs(quality.getF_xs() + value);
                break;
            }
            case 142: {
                quality.setF_xl(quality.getF_xl() + value);
                break;
            }
            case 143: {
                quality.setF_zs(quality.getF_zs() + value);
                break;
            }
            case 144: {
                quality.setF_sc(quality.getF_sc() + value);
                break;
            }
            case 145: {
                quality.setEjs(quality.getEjs() + value);
                break;
            }
            case 146: {
                quality.setExfjs(quality.getExfjs() + value);
                break;
            }
            case 147: {
                quality.setEwljs(quality.getEwljs() + value);
                break;
            }
            case 148: {
                quality.setEzsjs(quality.getEzsjs() + value);
                break;
            }
            case 149:
            case 150:
            case 151: {
                quality.setFsbj(quality.getFsbj() + value);
                break;
            }
            case 152:
            case 153:
            case 154: {
                quality.setBjlf(quality.getBjlf() + value);
                break;
            }
            case 155:
            case 156:
            case 157: {
                quality.setBjhf(quality.getBjhf() + value);
                break;
            }
            case 158:
            case 159:
            case 160: {
                quality.setBjsf(quality.getBjsf() + value);
                break;
            }
            case 161:
            case 162:
            case 163: {
                quality.setBjff(quality.getBjff() + value);
                break;
            }
            case 164:
            case 165:
            case 166: {
                quality.setBjlf(quality.getBjlf() + value);
                quality.setBjhf(quality.getBjhf() + value);
                quality.setBjsf(quality.getBjsf() + value);
                quality.setBjff(quality.getBjff() + value);
                break;
            }
            case 167:
            case 168:
            case 169: {
                quality.setBjgh(quality.getBjgh() + value);
                break;
            }
            case 170:
            case 171:
            case 172: {
                quality.setBjsc(quality.getBjsc() + value);
                break;
            }
            case 173: {
                quality.setFsbjcd(quality.getFsbjcd() + value);
                break;
            }
            case 174: {
                quality.setBjlfcd(quality.getBjlfcd() + value);
                break;
            }
            case 175: {
                quality.setBjffcd(quality.getBjffcd() + value);
                break;
            }
            case 176: {
                quality.setBjsfcd(quality.getBjsfcd() + value);
                break;
            }
            case 177: {
                quality.setBjhfcd(quality.getBjhfcd() + value);
                break;
            }
            case 178: {
                quality.setBjlfcd(quality.getBjlfcd() + value);
                quality.setBjffcd(quality.getBjffcd() + value);
                quality.setBjsfcd(quality.getBjsfcd() + value);
                quality.setBjhfcd(quality.getBjhfcd() + value);
                break;
            }
            case 179: {
                quality.setBjghcd(quality.getBjghcd() + value);
                break;
            }
            case 180: {
                quality.setBjsccd(quality.getBjsccd() + value);
                break;
            }
            case 181: {
                quality.setEfsds(quality.getEfsds() + value);
                break;
            }
            case 182: {
                quality.setEzs(quality.getEzs() + value);
                break;
            }
            case 183: {
                quality.setEwlzs(quality.getEwlzs() + value);
                break;
            }
            case 184:
            case 185: {
                quality.setFsmz(quality.getFsmz() + value);
                break;
            }
            case 186:
            case 187:
            case 188: {
                quality.setMzs(quality.getMzs() + value);
                break;
            }
            case 189:
            case 190:
            case 191: {
                quality.setMhf(quality.getMhf() + value);
                break;
            }
            case 192:
            case 193:
            case 194: {
                quality.setMlf(quality.getMlf() + value);
                break;
            }
            case 195:
            case 196:
            case 197: {
                quality.setMff(quality.getMff() + value);
                break;
            }
            case 198:
            case 199:
            case 200: {
                quality.setMsf(quality.getMsf() + value);
                break;
            }
            case 201:
            case 202:
            case 203: {
                quality.setMdf(quality.getMdf() + value);
                break;
            }
            case 204:
            case 205:
            case 206: {
                quality.setMfy(quality.getMfy() + value);
                break;
            }
            case 207:
            case 208:
            case 209: {
                quality.setMhl(quality.getMhl() + value);
                break;
            }
            case 210:
            case 211:
            case 212: {
                quality.setMhs(quality.getMhs() + value);
                break;
            }
            case 213:
            case 214:
            case 215: {
                quality.setMyw(quality.getMyw() + value);
                break;
            }
            case 216:
            case 217:
            case 218: {
                quality.setMgh(quality.getMgh() + value);
                break;
            }
            case 219:
            case 220:
            case 221: {
                quality.setMsc(quality.getMsc() + value);
                break;
            }
            case 222:
            case 223: {
                quality.setDzs(quality.getDzs() + value);
                break;
            }
            case 224:
            case 225: {
                quality.setDhf(quality.getDhf() + value);
                break;
            }
            case 226:
            case 227: {
                quality.setDlf(quality.getDlf() + value);
                break;
            }
            case 228:
            case 229: {
                quality.setDff(quality.getDff() + value);
                break;
            }
            case 230:
            case 231: {
                quality.setDsf(quality.getDsf() + value);
                break;
            }
            case 232:
            case 233: {
                quality.setDdf(quality.getDdf() + value);
                break;
            }
            case 234:
            case 235: {
                quality.setDfy(quality.getDfy() + value);
                break;
            }
            case 236:
            case 237: {
                quality.setDhl(quality.getDhl() + value);
                break;
            }
            case 238:
            case 239: {
                quality.setDhs(quality.getDhs() + value);
                break;
            }
            case 240:
            case 241: {
                quality.setDyw(quality.getDyw() + value);
                break;
            }
            case 242:
            case 243: {
                quality.setDgh(quality.getDgh() + value);
                break;
            }
            case 244:
            case 245: {
                quality.setDsc(quality.getDsc() + value);
                break;
            }
            case 246: {
                quality.setJsf(quality.getJsf() + value);
                break;
            }
            case 247: {
                quality.setJff(quality.getJff() + value);
                break;
            }
            case 248: {
                quality.setJlf(quality.getJlf() + value);
                break;
            }
            case 249: {
                quality.setJhf(quality.getJhf() + value);
                break;
            }
            case 250: {
                quality.setJgh(quality.getJgh() + value);
                break;
            }
        }
    }
    
    public static void AddR(Ql quality, String key, double value) {
        int n = -1;
        switch (key.hashCode()) {
            case 1664653415: {
                if (key.equals("加强全系法术")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 654406988: {
                if (key.equals("加强昏睡")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 766761683: {
                if (key.equals("忽视抗睡")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case -2000298496: {
                if (key.equals("忽视抗昏睡")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 654214495: {
                if (key.equals("加强中毒")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 766758724: {
                if (key.equals("忽视抗毒")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case -2000490989: {
                if (key.equals("忽视抗中毒")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case -955631560: {
                if (key.equals("无属性伤害")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 356919262: {
                if (key.equals("加强无属性伤害")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1419999352: {
                if (key.equals("对无属性目标伤害")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 654317993: {
                if (key.equals("加强封印")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 766754675: {
                if (key.equals("忽视抗封")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
            case -2000387491: {
                if (key.equals("忽视抗封印")) {
                    n = 12;
                    break;
                }
                else {
                    break;
                }
            }
            case 1052937: {
                if (key.equals("致命")) {
                    n = 13;
                    break;
                }
                else {
                    break;
                }
            }
            case 32670622: {
                if (key.equals("致命率")) {
                    n = 14;
                    break;
                }
                else {
                    break;
                }
            }
            case 1012551792: {
                if (key.equals("致命几率")) {
                    n = 15;
                    break;
                }
                else {
                    break;
                }
            }
            case 690512: {
                if (key.equals("命中")) {
                    n = 16;
                    break;
                }
                else {
                    break;
                }
            }
            case 21435447: {
                if (key.equals("命中率")) {
                    n = 17;
                    break;
                }
                else {
                    break;
                }
            }
            case 664261367: {
                if (key.equals("命中几率")) {
                    n = 18;
                    break;
                }
                else {
                    break;
                }
            }
            case 937010: {
                if (key.equals("狂暴")) {
                    n = 19;
                    break;
                }
                else {
                    break;
                }
            }
            case 29076885: {
                if (key.equals("狂暴率")) {
                    n = 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 901145945: {
                if (key.equals("狂暴几率")) {
                    n = 21;
                    break;
                }
                else {
                    break;
                }
            }
            case 1162717: {
                if (key.equals("连击")) {
                    n = 22;
                    break;
                }
                else {
                    break;
                }
            }
            case 36073802: {
                if (key.equals("连击率")) {
                    n = 23;
                    break;
                }
                else {
                    break;
                }
            }
            case 1118050372: {
                if (key.equals("连击几率")) {
                    n = 24;
                    break;
                }
                else {
                    break;
                }
            }
            case 686030: {
                if (key.equals("反击")) {
                    n = 25;
                    break;
                }
                else {
                    break;
                }
            }
            case 21296505: {
                if (key.equals("反击率")) {
                    n = 26;
                    break;
                }
                else {
                    break;
                }
            }
            case 659954165: {
                if (key.equals("反击几率")) {
                    n = 27;
                    break;
                }
                else {
                    break;
                }
            }
            case -1487678765: {
                if (key.equals("忽视防御程度")) {
                    n = 28;
                    break;
                }
                else {
                    break;
                }
            }
            case -1487992193: {
                if (key.equals("忽视防御几率")) {
                    n = 29;
                    break;
                }
                else {
                    break;
                }
            }
            case -1901283295: {
                if (key.equals("反击忽视防御程度")) {
                    n = 30;
                    break;
                }
                else {
                    break;
                }
            }
            case -1901596723: {
                if (key.equals("反击忽视防御几率")) {
                    n = 31;
                    break;
                }
                else {
                    break;
                }
            }
            case 654459252: {
                if (key.equals("加强混乱")) {
                    n = 32;
                    break;
                }
                else {
                    break;
                }
            }
            case -2000246232: {
                if (key.equals("忽视抗混乱")) {
                    n = 33;
                    break;
                }
                else {
                    break;
                }
            }
            case 766759273: {
                if (key.equals("忽视抗混")) {
                    n = 34;
                    break;
                }
                else {
                    break;
                }
            }
            case 767172179: {
                if (key.equals("忽视震慑")) {
                    n = 35;
                    break;
                }
                else {
                    break;
                }
            }
            case -1999915528: {
                if (key.equals("忽视抗震慑")) {
                    n = 36;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212202494: {
                if (key.equals("鬼火伤害")) {
                    n = 37;
                    break;
                }
                else {
                    break;
                }
            }
            case 810109: {
                if (key.equals("抗水")) {
                    n = 38;
                    break;
                }
                else {
                    break;
                }
            }
            case 25141240: {
                if (key.equals("抗水法")) {
                    n = 39;
                    break;
                }
                else {
                    break;
                }
            }
            case 811188: {
                if (key.equals("抗火")) {
                    n = 40;
                    break;
                }
                else {
                    break;
                }
            }
            case 25174689: {
                if (key.equals("抗火法")) {
                    n = 41;
                    break;
                }
                else {
                    break;
                }
            }
            case 821056: {
                if (key.equals("抗雷")) {
                    n = 42;
                    break;
                }
                else {
                    break;
                }
            }
            case 25480597: {
                if (key.equals("抗雷法")) {
                    n = 43;
                    break;
                }
                else {
                    break;
                }
            }
            case 821527: {
                if (key.equals("抗风")) {
                    n = 44;
                    break;
                }
                else {
                    break;
                }
            }
            case 25495198: {
                if (key.equals("抗风法")) {
                    n = 45;
                    break;
                }
                else {
                    break;
                }
            }
            case 25095177: {
                if (key.equals("抗昏睡")) {
                    n = 46;
                    break;
                }
                else {
                    break;
                }
            }
            case 25147441: {
                if (key.equals("抗混乱")) {
                    n = 47;
                    break;
                }
                else {
                    break;
                }
            }
            case 25006182: {
                if (key.equals("抗封印")) {
                    n = 48;
                    break;
                }
                else {
                    break;
                }
            }
            case 25424696: {
                if (key.equals("抗遗忘")) {
                    n = 49;
                    break;
                }
                else {
                    break;
                }
            }
            case 25515398: {
                if (key.equals("抗鬼火")) {
                    n = 50;
                    break;
                }
                else {
                    break;
                }
            }
            case 24897574: {
                if (key.equals("抗三尸")) {
                    n = 51;
                    break;
                }
                else {
                    break;
                }
            }
            case 771859205: {
                if (key.equals("抗三尸虫")) {
                    n = 52;
                    break;
                }
                else {
                    break;
                }
            }
            case -1580214290: {
                if (key.equals("抗灵宝伤害")) {
                    n = 53;
                    break;
                }
                else {
                    break;
                }
            }
            case 295296601: {
                if (key.equals("抵抗灵宝伤害")) {
                    n = 54;
                    break;
                }
                else {
                    break;
                }
            }
            case 24902684: {
                if (key.equals("抗中毒")) {
                    n = 55;
                    break;
                }
                else {
                    break;
                }
            }
            case 25478145: {
                if (key.equals("抗震慑")) {
                    n = 56;
                    break;
                }
                else {
                    break;
                }
            }
            case 24958385: {
                if (key.equals("抗反震")) {
                    n = 57;
                    break;
                }
                else {
                    break;
                }
            }
            case 791810161: {
                if (key.equals("抗龙伤害")) {
                    n = 58;
                    break;
                }
                else {
                    break;
                }
            }
            case 766769769: {
                if (key.equals("忽视抗雷")) {
                    n = 59;
                    break;
                }
                else {
                    break;
                }
            }
            case 767174631: {
                if (key.equals("忽视雷法")) {
                    n = 60;
                    break;
                }
                else {
                    break;
                }
            }
            case 766758822: {
                if (key.equals("忽视抗水")) {
                    n = 61;
                    break;
                }
                else {
                    break;
                }
            }
            case 766835274: {
                if (key.equals("忽视水法")) {
                    n = 62;
                    break;
                }
                else {
                    break;
                }
            }
            case 766759901: {
                if (key.equals("忽视抗火")) {
                    n = 63;
                    break;
                }
                else {
                    break;
                }
            }
            case 766868723: {
                if (key.equals("忽视火法")) {
                    n = 64;
                    break;
                }
                else {
                    break;
                }
            }
            case 766770240: {
                if (key.equals("忽视抗风")) {
                    n = 65;
                    break;
                }
                else {
                    break;
                }
            }
            case 767189232: {
                if (key.equals("忽视风法")) {
                    n = 66;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178758733: {
                if (key.equals("雷法伤害")) {
                    n = 67;
                    break;
                }
                else {
                    break;
                }
            }
            case 852636656: {
                if (key.equals("水法伤害")) {
                    n = 68;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192790294: {
                if (key.equals("风法伤害")) {
                    n = 69;
                    break;
                }
                else {
                    break;
                }
            }
            case 884781145: {
                if (key.equals("火法伤害")) {
                    n = 70;
                    break;
                }
                else {
                    break;
                }
            }
            case 654827209: {
                if (key.equals("加强鬼火")) {
                    n = 71;
                    break;
                }
                else {
                    break;
                }
            }
            case 654736507: {
                if (key.equals("加强遗忘")) {
                    n = 72;
                    break;
                }
                else {
                    break;
                }
            }
            case 20969967: {
                if (key.equals("加三尸")) {
                    n = 73;
                    break;
                }
                else {
                    break;
                }
            }
            case -1194311134: {
                if (key.equals("加强三尸虫")) {
                    n = 74;
                    break;
                }
                else {
                    break;
                }
            }
            case 746209154: {
                if (key.equals("强三尸虫")) {
                    n = 75;
                    break;
                }
                else {
                    break;
                }
            }
            case -1639328833: {
                if (key.equals("加强三尸虫回血程度")) {
                    n = 76;
                    break;
                }
                else {
                    break;
                }
            }
            case 1179044208: {
                if (key.equals("雷法狂暴")) {
                    n = 77;
                    break;
                }
                else {
                    break;
                }
            }
            case -1286334403: {
                if (key.equals("雷系狂暴几率")) {
                    n = 78;
                    break;
                }
                else {
                    break;
                }
            }
            case 885066620: {
                if (key.equals("火法狂暴")) {
                    n = 79;
                    break;
                }
                else {
                    break;
                }
            }
            case -330954935: {
                if (key.equals("火系狂暴几率")) {
                    n = 80;
                    break;
                }
                else {
                    break;
                }
            }
            case 852922131: {
                if (key.equals("水法狂暴")) {
                    n = 81;
                    break;
                }
                else {
                    break;
                }
            }
            case -1157037792: {
                if (key.equals("水系狂暴几率")) {
                    n = 82;
                    break;
                }
                else {
                    break;
                }
            }
            case 1193075769: {
                if (key.equals("风法狂暴")) {
                    n = 83;
                    break;
                }
                else {
                    break;
                }
            }
            case -686906170: {
                if (key.equals("风系狂暴几率")) {
                    n = 84;
                    break;
                }
                else {
                    break;
                }
            }
            case -808889493: {
                if (key.equals("雷法狂暴程度")) {
                    n = 85;
                    break;
                }
                else {
                    break;
                }
            }
            case -209461260: {
                if (key.equals("风法狂暴程度")) {
                    n = 86;
                    break;
                }
                else {
                    break;
                }
            }
            case -679592882: {
                if (key.equals("水法狂暴程度")) {
                    n = 87;
                    break;
                }
                else {
                    break;
                }
            }
            case 146489975: {
                if (key.equals("火法狂暴程度")) {
                    n = 88;
                    break;
                }
                else {
                    break;
                }
            }
            case -1079297847: {
                if (key.equals("仙法狂暴程度")) {
                    n = 89;
                    break;
                }
                else {
                    break;
                }
            }
            case 1265793756: {
                if (key.equals("鬼火狂暴程度")) {
                    n = 90;
                    break;
                }
                else {
                    break;
                }
            }
            case -75829079: {
                if (key.equals("三尸虫狂暴程度")) {
                    n = 91;
                    break;
                }
                else {
                    break;
                }
            }
            case 25192340: {
                if (key.equals("抗物理")) {
                    n = 92;
                    break;
                }
                else {
                    break;
                }
            }
            case -2109360276: {
                if (key.equals("物理吸收率")) {
                    n = 93;
                    break;
                }
                else {
                    break;
                }
            }
            case 37329: {
                if (key.equals("金")) {
                    n = 94;
                    break;
                }
                else {
                    break;
                }
            }
            case 26408: {
                if (key.equals("木")) {
                    n = 95;
                    break;
                }
                else {
                    break;
                }
            }
            case 27700: {
                if (key.equals("水")) {
                    n = 96;
                    break;
                }
                else {
                    break;
                }
            }
            case 28779: {
                if (key.equals("火")) {
                    n = 97;
                    break;
                }
                else {
                    break;
                }
            }
            case 22303: {
                if (key.equals("土")) {
                    n = 98;
                    break;
                }
                else {
                    break;
                }
            }
            case 1170808: {
                if (key.equals("躲闪")) {
                    n = 99;
                    break;
                }
                else {
                    break;
                }
            }
            case 36324623: {
                if (key.equals("躲闪率")) {
                    n = 100;
                    break;
                }
                else {
                    break;
                }
            }
            case 902263029: {
                if (key.equals("物理躲闪")) {
                    n = 101;
                    break;
                }
                else {
                    break;
                }
            }
            case 1118247180: {
                if (key.equals("连击次数")) {
                    n = 102;
                    break;
                }
                else {
                    break;
                }
            }
            case 660150973: {
                if (key.equals("反击次数")) {
                    n = 103;
                    break;
                }
                else {
                    break;
                }
            }
            case 21121908: {
                if (key.equals("加强风")) {
                    n = 104;
                    break;
                }
                else {
                    break;
                }
            }
            case 654807009: {
                if (key.equals("加强风法")) {
                    n = 105;
                    break;
                }
                else {
                    break;
                }
            }
            case 21121437: {
                if (key.equals("加强雷")) {
                    n = 106;
                    break;
                }
                else {
                    break;
                }
            }
            case 654792408: {
                if (key.equals("加强雷法")) {
                    n = 107;
                    break;
                }
                else {
                    break;
                }
            }
            case 21111569: {
                if (key.equals("加强火")) {
                    n = 108;
                    break;
                }
                else {
                    break;
                }
            }
            case 654486500: {
                if (key.equals("加强火法")) {
                    n = 109;
                    break;
                }
                else {
                    break;
                }
            }
            case 21110490: {
                if (key.equals("加强水")) {
                    n = 110;
                    break;
                }
                else {
                    break;
                }
            }
            case 654453051: {
                if (key.equals("加强水法")) {
                    n = 111;
                    break;
                }
                else {
                    break;
                }
            }
            case 24650724: {
                if (key.equals("强震慑")) {
                    n = 112;
                    break;
                }
                else {
                    break;
                }
            }
            case 654789956: {
                if (key.equals("加强震慑")) {
                    n = 113;
                    break;
                }
                else {
                    break;
                }
            }
            case 747249735: {
                if (key.equals("强力克金")) {
                    n = 114;
                    break;
                }
                else {
                    break;
                }
            }
            case 747240106: {
                if (key.equals("强力克水")) {
                    n = 115;
                    break;
                }
                else {
                    break;
                }
            }
            case 747241185: {
                if (key.equals("强力克火")) {
                    n = 116;
                    break;
                }
                else {
                    break;
                }
            }
            case 747238814: {
                if (key.equals("强力克木")) {
                    n = 117;
                    break;
                }
                else {
                    break;
                }
            }
            case 747234709: {
                if (key.equals("强力克土")) {
                    n = 118;
                    break;
                }
                else {
                    break;
                }
            }
            case 677254229: {
                if (key.equals("反震程度")) {
                    n = 119;
                    break;
                }
                else {
                    break;
                }
            }
            case 21844461: {
                if (key.equals("反震率")) {
                    n = 120;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212487969: {
                if (key.equals("鬼火狂暴")) {
                    n = 121;
                    break;
                }
                else {
                    break;
                }
            }
            case 1265480328: {
                if (key.equals("鬼火狂暴几率")) {
                    n = 122;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006621742: {
                if (key.equals("三尸虫狂暴")) {
                    n = 123;
                    break;
                }
                else {
                    break;
                }
            }
            case -76142507: {
                if (key.equals("三尸虫狂暴几率")) {
                    n = 124;
                    break;
                }
                else {
                    break;
                }
            }
            case 767119521: {
                if (key.equals("忽视躲闪")) {
                    n = 125;
                    break;
                }
                else {
                    break;
                }
            }
            case 766634743: {
                if (key.equals("忽视反击")) {
                    n = 126;
                    break;
                }
                else {
                    break;
                }
            }
            case -1967484242: {
                if (key.equals("仙法连击率")) {
                    n = 127;
                    break;
                }
                else {
                    break;
                }
            }
            case -862510040: {
                if (key.equals("仙法连击次数")) {
                    n = 128;
                    break;
                }
                else {
                    break;
                }
            }
            case 1533321906: {
                if (key.equals("忽视仙法抗性率")) {
                    n = 129;
                    break;
                }
                else {
                    break;
                }
            }
            case 288414768: {
                if (key.equals("忽视仙法抗性程度")) {
                    n = 130;
                    break;
                }
                else {
                    break;
                }
            }
            case 767118730: {
                if (key.equals("忽视遗忘")) {
                    n = 131;
                    break;
                }
                else {
                    break;
                }
            }
            case -1999968977: {
                if (key.equals("忽视抗遗忘")) {
                    n = 132;
                    break;
                }
                else {
                    break;
                }
            }
            case 767209432: {
                if (key.equals("忽视鬼火")) {
                    n = 133;
                    break;
                }
                else {
                    break;
                }
            }
            case -1999878275: {
                if (key.equals("忽视抗鬼火")) {
                    n = 134;
                    break;
                }
                else {
                    break;
                }
            }
            case -307203320: {
                if (key.equals("加强攻击法术效果")) {
                    n = 135;
                    break;
                }
                else {
                    break;
                }
            }
            case 1027248131: {
                if (key.equals("加强加攻法术效果")) {
                    n = 136;
                    break;
                }
                else {
                    break;
                }
            }
            case 1008733975: {
                if (key.equals("加强防御法术效果")) {
                    n = 137;
                    break;
                }
                else {
                    break;
                }
            }
            case -281318022: {
                if (key.equals("加强加防法术效果")) {
                    n = 138;
                    break;
                }
                else {
                    break;
                }
            }
            case -791726641: {
                if (key.equals("加强速度法术效果")) {
                    n = 139;
                    break;
                }
                else {
                    break;
                }
            }
            case -1717393177: {
                if (key.equals("加强加速法术效果")) {
                    n = 140;
                    break;
                }
                else {
                    break;
                }
            }
            case -252587737: {
                if (key.equals("增加强克效果")) {
                    n = 141;
                    break;
                }
                else {
                    break;
                }
            }
            case -538500623: {
                if (key.equals("抵御强克效果")) {
                    n = 142;
                    break;
                }
                else {
                    break;
                }
            }
            case 61004801: {
                if (key.equals("抗无属性伤害")) {
                    n = 143;
                    break;
                }
                else {
                    break;
                }
            }
            case -1284413843: {
                if (key.equals("抗震慑气血")) {
                    n = 144;
                    break;
                }
                else {
                    break;
                }
            }
            case -1284045886: {
                if (key.equals("抗震慑魔法")) {
                    n = 145;
                    break;
                }
                else {
                    break;
                }
            }
            case -1200392901: {
                if (key.equals("对召唤兽伤害")) {
                    n = 146;
                    break;
                }
                else {
                    break;
                }
            }
            case 25443507: {
                if (key.equals("抗金箍")) {
                    n = 147;
                    break;
                }
                else {
                    break;
                }
            }
            case 25055235: {
                if (key.equals("抗情网")) {
                    n = 148;
                    break;
                }
                else {
                    break;
                }
            }
            case -1597909547: {
                if (key.equals("抗浩然正气")) {
                    n = 149;
                    break;
                }
                else {
                    break;
                }
            }
            case 617269289: {
                if (key.equals("上善若水")) {
                    n = 150;
                    break;
                }
                else {
                    break;
                }
            }
            case -1268826016: {
                if (key.equals("抗青面獠牙")) {
                    n = 151;
                    break;
                }
                else {
                    break;
                }
            }
            case 993331355: {
                if (key.equals("美人迟暮")) {
                    n = 152;
                    break;
                }
                else {
                    break;
                }
            }
            case -1741749902: {
                if (key.equals("抗天魔解体")) {
                    n = 153;
                    break;
                }
                else {
                    break;
                }
            }
            case 667983361: {
                if (key.equals("化血成碧")) {
                    n = 154;
                    break;
                }
                else {
                    break;
                }
            }
            case -1732293003: {
                if (key.equals("抗小楼夜哭")) {
                    n = 155;
                    break;
                }
                else {
                    break;
                }
            }
            case 807672339: {
                if (key.equals("明珠有泪")) {
                    n = 156;
                    break;
                }
                else {
                    break;
                }
            }
            case -1814824587: {
                if (key.equals("抗分光化影")) {
                    n = 157;
                    break;
                }
                else {
                    break;
                }
            }
            case 886469796: {
                if (key.equals("灵犀一点")) {
                    n = 158;
                    break;
                }
                else {
                    break;
                }
            }
            case -1289122148: {
                if (key.equals("抗隔山打牛")) {
                    n = 159;
                    break;
                }
                else {
                    break;
                }
            }
            case 725016296: {
                if (key.equals("尘埃落定")) {
                    n = 160;
                    break;
                }
                else {
                    break;
                }
            }
            case 784565671: {
                if (key.equals("抗致命率")) {
                    n = 161;
                    break;
                }
                else {
                    break;
                }
            }
            case 706842077: {
                if (key.equals("附加水法攻击")) {
                    n = 162;
                    break;
                }
                else {
                    break;
                }
            }
            case 1032964154: {
                if (key.equals("附加雷法攻击")) {
                    n = 163;
                    break;
                }
                else {
                    break;
                }
            }
            case 1046995715: {
                if (key.equals("附加风法攻击")) {
                    n = 164;
                    break;
                }
                else {
                    break;
                }
            }
            case 738986566: {
                if (key.equals("附加火法攻击")) {
                    n = 165;
                    break;
                }
                else {
                    break;
                }
            }
            case 712801238: {
                if (key.equals("附加混乱攻击")) {
                    n = 166;
                    break;
                }
                else {
                    break;
                }
            }
            case 577051339: {
                if (key.equals("附加封印攻击")) {
                    n = 167;
                    break;
                }
                else {
                    break;
                }
            }
            case 703922559: {
                if (key.equals("附加毒法攻击")) {
                    n = 168;
                    break;
                }
                else {
                    break;
                }
            }
            case 1030607782: {
                if (key.equals("附加震慑攻击")) {
                    n = 169;
                    break;
                }
                else {
                    break;
                }
            }
            case 472679051: {
                if (key.equals("附加三尸攻击")) {
                    n = 170;
                    break;
                }
                else {
                    break;
                }
            }
            case 629042766: {
                if (key.equals("仙法狂暴")) {
                    n = 171;
                    break;
                }
                else {
                    break;
                }
            }
            case 766602309: {
                if (key.equals("忽视仙法")) {
                    n = 172;
                    break;
                }
                else {
                    break;
                }
            }
            case 766601348: {
                if (key.equals("忽视人法")) {
                    n = 173;
                    break;
                }
                else {
                    break;
                }
            }
            case 24303180: {
                if (key.equals("强毒伤")) {
                    n = 174;
                    break;
                }
                else {
                    break;
                }
            }
            case -1187098233: {
                if (key.equals("加强毒伤害")) {
                    n = 175;
                    break;
                }
                else {
                    break;
                }
            }
            case 25130601: {
                if (key.equals("抗毒伤")) {
                    n = 176;
                    break;
                }
                else {
                    break;
                }
            }
            case -1267981488: {
                if (key.equals("抗风法狂暴")) {
                    n = 177;
                    break;
                }
                else {
                    break;
                }
            }
            case -1575990637: {
                if (key.equals("抗火法狂暴")) {
                    n = 178;
                    break;
                }
                else {
                    break;
                }
            }
            case -1608135126: {
                if (key.equals("抗水法狂暴")) {
                    n = 179;
                    break;
                }
                else {
                    break;
                }
            }
            case -1282013049: {
                if (key.equals("抗雷法狂暴")) {
                    n = 180;
                    break;
                }
                else {
                    break;
                }
            }
            case -1248569288: {
                if (key.equals("抗鬼火狂暴")) {
                    n = 181;
                    break;
                }
                else {
                    break;
                }
            }
            case 654823494: {
                if (key.equals("加强魅惑")) {
                    n = 182;
                    break;
                }
                else {
                    break;
                }
            }
            case 24616086: {
                if (key.equals("强金箍")) {
                    n = 183;
                    break;
                }
                else {
                    break;
                }
            }
            case 24227814: {
                if (key.equals("强情网")) {
                    n = 184;
                    break;
                }
                else {
                    break;
                }
            }
            case 723131393: {
                if (key.equals("封印狂暴")) {
                    n = 185;
                    break;
                }
                else {
                    break;
                }
            }
            case 858881292: {
                if (key.equals("混乱狂暴")) {
                    n = 186;
                    break;
                }
                else {
                    break;
                }
            }
            case 808655588: {
                if (key.equals("昏睡狂暴")) {
                    n = 187;
                    break;
                }
                else {
                    break;
                }
            }
            case 850002613: {
                if (key.equals("毒法狂暴")) {
                    n = 188;
                    break;
                }
                else {
                    break;
                }
            }
            case 668026692: {
                if (key.equals("加防狂暴")) {
                    n = 189;
                    break;
                }
                else {
                    break;
                }
            }
            case 655980557: {
                if (key.equals("加攻狂暴")) {
                    n = 190;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176687836: {
                if (key.equals("震慑狂暴")) {
                    n = 191;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125323347: {
                if (key.equals("遗忘狂暴")) {
                    n = 192;
                    break;
                }
                else {
                    break;
                }
            }
            case 1208917854: {
                if (key.equals("魅惑狂暴")) {
                    n = 193;
                    break;
                }
                else {
                    break;
                }
            }
            case 856227905: {
                if (key.equals("法术暴击")) {
                    n = 194;
                    break;
                }
                else {
                    break;
                }
            }
            case 773290854: {
                if (key.equals("法术暴击率")) {
                    n = 195;
                    break;
                }
                else {
                    break;
                }
            }
            case -1798024792: {
                if (key.equals("法术暴击几率")) {
                    n = 196;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178943237: {
                if (key.equals("雷法暴击")) {
                    n = 197;
                    break;
                }
                else {
                    break;
                }
            }
            case -2107435742: {
                if (key.equals("雷法暴击率")) {
                    n = 198;
                    break;
                }
                else {
                    break;
                }
            }
            case -906236052: {
                if (key.equals("雷法暴击几率")) {
                    n = 199;
                    break;
                }
                else {
                    break;
                }
            }
            case 884965649: {
                if (key.equals("火法暴击")) {
                    n = 200;
                    break;
                }
                else {
                    break;
                }
            }
            case 1664160918: {
                if (key.equals("火法暴击率")) {
                    n = 201;
                    break;
                }
                else {
                    break;
                }
            }
            case 49143416: {
                if (key.equals("火法暴击几率")) {
                    n = 202;
                    break;
                }
                else {
                    break;
                }
            }
            case 852821160: {
                if (key.equals("水法暴击")) {
                    n = 203;
                    break;
                }
                else {
                    break;
                }
            }
            case 667681759: {
                if (key.equals("水法暴击率")) {
                    n = 204;
                    break;
                }
                else {
                    break;
                }
            }
            case -776939441: {
                if (key.equals("水法暴击几率")) {
                    n = 205;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192974798: {
                if (key.equals("风法暴击")) {
                    n = 206;
                    break;
                }
                else {
                    break;
                }
            }
            case -1672457351: {
                if (key.equals("风法暴击率")) {
                    n = 207;
                    break;
                }
                else {
                    break;
                }
            }
            case -306807819: {
                if (key.equals("风法暴击几率")) {
                    n = 208;
                    break;
                }
                else {
                    break;
                }
            }
            case 628941795: {
                if (key.equals("仙法暴击")) {
                    n = 209;
                    break;
                }
                else {
                    break;
                }
            }
            case -1977611260: {
                if (key.equals("仙法暴击率")) {
                    n = 210;
                    break;
                }
                else {
                    break;
                }
            }
            case -1176644406: {
                if (key.equals("仙法暴击几率")) {
                    n = 211;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212386998: {
                if (key.equals("鬼火暴击")) {
                    n = 212;
                    break;
                }
                else {
                    break;
                }
            }
            case -1070679151: {
                if (key.equals("鬼火暴击率")) {
                    n = 213;
                    break;
                }
                else {
                    break;
                }
            }
            case 1168447197: {
                if (key.equals("鬼火暴击几率")) {
                    n = 214;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006520771: {
                if (key.equals("三尸虫暴击")) {
                    n = 215;
                    break;
                }
                else {
                    break;
                }
            }
            case 2072631332: {
                if (key.equals("三尸虫暴击率")) {
                    n = 216;
                    break;
                }
                else {
                    break;
                }
            }
            case -173175638: {
                if (key.equals("三尸虫暴击几率")) {
                    n = 217;
                    break;
                }
                else {
                    break;
                }
            }
            case -1797980601: {
                if (key.equals("法术暴击增伤")) {
                    n = 218;
                    break;
                }
                else {
                    break;
                }
            }
            case -906191861: {
                if (key.equals("雷法暴击增伤")) {
                    n = 219;
                    break;
                }
                else {
                    break;
                }
            }
            case -306763628: {
                if (key.equals("风法暴击增伤")) {
                    n = 220;
                    break;
                }
                else {
                    break;
                }
            }
            case -776895250: {
                if (key.equals("水法暴击增伤")) {
                    n = 221;
                    break;
                }
                else {
                    break;
                }
            }
            case 49187607: {
                if (key.equals("火法暴击增伤")) {
                    n = 222;
                    break;
                }
                else {
                    break;
                }
            }
            case -1176600215: {
                if (key.equals("仙法暴击增伤")) {
                    n = 223;
                    break;
                }
                else {
                    break;
                }
            }
            case 1168491388: {
                if (key.equals("鬼火暴击增伤")) {
                    n = 224;
                    break;
                }
                else {
                    break;
                }
            }
            case -173131447: {
                if (key.equals("三尸虫暴击增伤")) {
                    n = 225;
                    break;
                }
                else {
                    break;
                }
            }
            case 856562674: {
                if (key.equals("法术躲闪")) {
                    n = 226;
                    break;
                }
                else {
                    break;
                }
            }
            case 626795181: {
                if (key.equals("伤害减免")) {
                    n = 227;
                    break;
                }
                else {
                    break;
                }
            }
            case 626808992: {
                if (key.equals("伤害加深")) {
                    n = 228;
                    break;
                }
                else {
                    break;
                }
            }
            case -2091530424: {
                if (key.equals("加强霹雳效果")) {
                    n = 229;
                    break;
                }
                else {
                    break;
                }
            }
            case 1788583743: {
                if (key.equals("加强扶摇效果")) {
                    n = 230;
                    break;
                }
                else {
                    break;
                }
            }
            case 1868425545: {
                if (key.equals("加强沧波效果")) {
                    n = 231;
                    break;
                }
                else {
                    break;
                }
            }
            case 105974082: {
                if (key.equals("加强甘霖回血值")) {
                    n = 232;
                    break;
                }
                else {
                    break;
                }
            }
            case -1009414731: {
                if (key.equals("加强甘霖回血程度")) {
                    n = 233;
                    break;
                }
                else {
                    break;
                }
            }
            case 856082378: {
                if (key.equals("法术命中")) {
                    n = 234;
                    break;
                }
                else {
                    break;
                }
            }
            case 768779517: {
                if (key.equals("法术命中率")) {
                    n = 235;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176441338: {
                if (key.equals("震慑命中")) {
                    n = 236;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176227316: {
                if (key.equals("震慑法术命中")) {
                    n = 237;
                    break;
                }
                else {
                    break;
                }
            }
            case 2109972685: {
                if (key.equals("震慑命中率")) {
                    n = 238;
                    break;
                }
                else {
                    break;
                }
            }
            case 884820122: {
                if (key.equals("火法命中")) {
                    n = 239;
                    break;
                }
                else {
                    break;
                }
            }
            case 101112980: {
                if (key.equals("火法法术命中")) {
                    n = 240;
                    break;
                }
                else {
                    break;
                }
            }
            case 1659649581: {
                if (key.equals("火法命中率")) {
                    n = 241;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178797710: {
                if (key.equals("雷法命中")) {
                    n = 242;
                    break;
                }
                else {
                    break;
                }
            }
            case -854266488: {
                if (key.equals("雷法法术命中")) {
                    n = 243;
                    break;
                }
                else {
                    break;
                }
            }
            case -2111947079: {
                if (key.equals("雷法命中率")) {
                    n = 244;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192829271: {
                if (key.equals("风法命中")) {
                    n = 245;
                    break;
                }
                else {
                    break;
                }
            }
            case -254838255: {
                if (key.equals("风法法术命中")) {
                    n = 246;
                    break;
                }
                else {
                    break;
                }
            }
            case -1676968688: {
                if (key.equals("风法命中率")) {
                    n = 247;
                    break;
                }
                else {
                    break;
                }
            }
            case 852675633: {
                if (key.equals("水法命中")) {
                    n = 248;
                    break;
                }
                else {
                    break;
                }
            }
            case -724969877: {
                if (key.equals("水法法术命中")) {
                    n = 249;
                    break;
                }
                else {
                    break;
                }
            }
            case 663170422: {
                if (key.equals("水法命中率")) {
                    n = 250;
                    break;
                }
                else {
                    break;
                }
            }
            case 849756115: {
                if (key.equals("毒法命中")) {
                    n = 251;
                    break;
                }
                else {
                    break;
                }
            }
            case 764340621: {
                if (key.equals("毒法法术命中")) {
                    n = 252;
                    break;
                }
                else {
                    break;
                }
            }
            case 572665364: {
                if (key.equals("毒法命中率")) {
                    n = 253;
                    break;
                }
                else {
                    break;
                }
            }
            case 722884895: {
                if (key.equals("封印命中")) {
                    n = 254;
                    break;
                }
                else {
                    break;
                }
            }
            case -899817511: {
                if (key.equals("封印法术命中")) {
                    n = 255;
                    break;
                }
                else {
                    break;
                }
            }
            case 934624840: {
                if (key.equals("封印命中率")) {
                    n = 256;
                    break;
                }
                else {
                    break;
                }
            }
            case 858634794: {
                if (key.equals("混乱命中")) {
                    n = 257;
                    break;
                }
                else {
                    break;
                }
            }
            case 706816548: {
                if (key.equals("混乱法术命中")) {
                    n = 258;
                    break;
                }
                else {
                    break;
                }
            }
            case 847904413: {
                if (key.equals("混乱命中率")) {
                    n = 259;
                    break;
                }
                else {
                    break;
                }
            }
            case 808409090: {
                if (key.equals("昏睡命中")) {
                    n = 260;
                    break;
                }
                else {
                    break;
                }
            }
            case -315444740: {
                if (key.equals("昏睡法术命中")) {
                    n = 261;
                    break;
                }
                else {
                    break;
                }
            }
            case -709092411: {
                if (key.equals("昏睡命中率")) {
                    n = 262;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125076849: {
                if (key.equals("遗忘命中")) {
                    n = 263;
                    break;
                }
                else {
                    break;
                }
            }
            case -940406357: {
                if (key.equals("遗忘法术命中")) {
                    n = 264;
                    break;
                }
                else {
                    break;
                }
            }
            case 517673526: {
                if (key.equals("遗忘命中率")) {
                    n = 265;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212241471: {
                if (key.equals("鬼火命中")) {
                    n = 266;
                    break;
                }
                else {
                    break;
                }
            }
            case 1220416761: {
                if (key.equals("鬼火法术命中")) {
                    n = 267;
                    break;
                }
                else {
                    break;
                }
            }
            case -1075190488: {
                if (key.equals("鬼火命中率")) {
                    n = 268;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006375244: {
                if (key.equals("三尸虫命中")) {
                    n = 269;
                    break;
                }
                else {
                    break;
                }
            }
            case 1877628825: {
                if (key.equals("三尸法术命中")) {
                    n = 270;
                    break;
                }
                else {
                    break;
                }
            }
            case 2068119995: {
                if (key.equals("三尸虫命中率")) {
                    n = 271;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176921634: {
                if (key.equals("震慑躲闪")) {
                    n = 272;
                    break;
                }
                else {
                    break;
                }
            }
            case 1176707612: {
                if (key.equals("震慑法术躲闪")) {
                    n = 273;
                    break;
                }
                else {
                    break;
                }
            }
            case 885300418: {
                if (key.equals("火法躲闪")) {
                    n = 274;
                    break;
                }
                else {
                    break;
                }
            }
            case 101593276: {
                if (key.equals("火法法术躲闪")) {
                    n = 275;
                    break;
                }
                else {
                    break;
                }
            }
            case 1179278006: {
                if (key.equals("雷法躲闪")) {
                    n = 276;
                    break;
                }
                else {
                    break;
                }
            }
            case -853786192: {
                if (key.equals("雷法法术躲闪")) {
                    n = 277;
                    break;
                }
                else {
                    break;
                }
            }
            case 1193309567: {
                if (key.equals("风法躲闪")) {
                    n = 278;
                    break;
                }
                else {
                    break;
                }
            }
            case -254357959: {
                if (key.equals("风法法术躲闪")) {
                    n = 279;
                    break;
                }
                else {
                    break;
                }
            }
            case 853155929: {
                if (key.equals("水法躲闪")) {
                    n = 280;
                    break;
                }
                else {
                    break;
                }
            }
            case -724489581: {
                if (key.equals("水法法术躲闪")) {
                    n = 281;
                    break;
                }
                else {
                    break;
                }
            }
            case 850236411: {
                if (key.equals("毒法躲闪")) {
                    n = 282;
                    break;
                }
                else {
                    break;
                }
            }
            case 764820917: {
                if (key.equals("毒法法术躲闪")) {
                    n = 283;
                    break;
                }
                else {
                    break;
                }
            }
            case 723365191: {
                if (key.equals("封印躲闪")) {
                    n = 284;
                    break;
                }
                else {
                    break;
                }
            }
            case -899337215: {
                if (key.equals("封印法术躲闪")) {
                    n = 285;
                    break;
                }
                else {
                    break;
                }
            }
            case 859115090: {
                if (key.equals("混乱躲闪")) {
                    n = 286;
                    break;
                }
                else {
                    break;
                }
            }
            case 707296844: {
                if (key.equals("混乱法术躲闪")) {
                    n = 287;
                    break;
                }
                else {
                    break;
                }
            }
            case 808889386: {
                if (key.equals("昏睡躲闪")) {
                    n = 288;
                    break;
                }
                else {
                    break;
                }
            }
            case -314964444: {
                if (key.equals("昏睡法术躲闪")) {
                    n = 289;
                    break;
                }
                else {
                    break;
                }
            }
            case 1125557145: {
                if (key.equals("遗忘躲闪")) {
                    n = 290;
                    break;
                }
                else {
                    break;
                }
            }
            case -939926061: {
                if (key.equals("遗忘法术躲闪")) {
                    n = 291;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212721767: {
                if (key.equals("鬼火躲闪")) {
                    n = 292;
                    break;
                }
                else {
                    break;
                }
            }
            case 1220897057: {
                if (key.equals("鬼火法术躲闪")) {
                    n = 293;
                    break;
                }
                else {
                    break;
                }
            }
            case 2006855540: {
                if (key.equals("三尸虫躲闪")) {
                    n = 294;
                    break;
                }
                else {
                    break;
                }
            }
            case 1878109121: {
                if (key.equals("三尸法术躲闪")) {
                    n = 295;
                    break;
                }
                else {
                    break;
                }
            }
            case -954257074: {
                if (key.equals("水法伤害减免")) {
                    n = 296;
                    break;
                }
                else {
                    break;
                }
            }
            case -484125452: {
                if (key.equals("风法伤害减免")) {
                    n = 297;
                    break;
                }
                else {
                    break;
                }
            }
            case -1083553685: {
                if (key.equals("雷法伤害减免")) {
                    n = 298;
                    break;
                }
                else {
                    break;
                }
            }
            case -128174217: {
                if (key.equals("火法伤害减免")) {
                    n = 299;
                    break;
                }
                else {
                    break;
                }
            }
            case 991129564: {
                if (key.equals("鬼火伤害减免")) {
                    n = 300;
                    break;
                }
                else {
                    break;
                }
            }
            case 884815640: {
                if (key.equals("火法反击")) {
                    n = 301;
                    break;
                }
                else {
                    break;
                }
            }
            case 1178793228: {
                if (key.equals("雷法反击")) {
                    n = 302;
                    break;
                }
                else {
                    break;
                }
            }
            case 852671151: {
                if (key.equals("水法反击")) {
                    n = 303;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192824789: {
                if (key.equals("风法反击")) {
                    n = 304;
                    break;
                }
                else {
                    break;
                }
            }
            case -1025915216: {
                if (key.equals("被攻击时释放魔神附身")) {
                    n = 305;
                    break;
                }
                else {
                    break;
                }
            }
            case -1574841255: {
                if (key.equals("被攻击时释放含情脉脉")) {
                    n = 306;
                    break;
                }
                else {
                    break;
                }
            }
            case -1620819259: {
                if (key.equals("被攻击时释放乾坤借速")) {
                    n = 307;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                quality.addQ(value);
                break;
            }
            case 1: {
                quality.setRoleqhs(quality.getRoleqhs() + value);
                break;
            }
            case 2:
            case 3: {
                quality.setRolehshs(quality.getRolehshs() + value);
                break;
            }
            case 4: {
                quality.setRoleqzd(quality.getRoleqzd() + value);
                break;
            }
            case 5:
            case 6: {
                quality.setRolehszd(quality.getRolehszd() + value);
                break;
            }
            case 7:
            case 8:
            case 9: {
                quality.setRolewsxsh(quality.getRolewsxsh() + value);
                break;
            }
            case 10: {
                quality.setRoleqfy(quality.getRoleqfy() + value);
                break;
            }
            case 11:
            case 12: {
                quality.setRolehsfy(quality.getRolehsfy() + value);
                break;
            }
            case 13:
            case 14:
            case 15: {
                quality.setRolefzml(quality.getRolefzml() + value);
                break;
            }
            case 16:
            case 17:
            case 18: {
                quality.setRolefmzl(quality.getRolefmzl() + value);
                break;
            }
            case 19:
            case 20:
            case 21: {
                quality.setRolefkbl(quality.getRolefkbl() + value);
                break;
            }
            case 22:
            case 23:
            case 24: {
                quality.setRolefljl(quality.getRolefljl() + value);
                break;
            }
            case 25:
            case 26:
            case 27: {
                quality.setRoleffjl(quality.getRoleffjl() + value);
                break;
            }
            case 28: {
                quality.setRolehsfyv(quality.getRolehsfyv() + value);
                break;
            }
            case 29: {
                quality.setRolehsfyl(quality.getRolehsfyl() + value);
                break;
            }
            case 30: {
                quality.setFjhfyv(quality.getFjhfyv() + value);
                break;
            }
            case 31: {
                quality.setFjhfyl(quality.getFjhfyl() + value);
                break;
            }
            case 32: {
                quality.setRoleqhl(quality.getRoleqhl() + value);
                break;
            }
            case 33:
            case 34: {
                quality.setRolehshl(quality.getRolehshl() + value);
                break;
            }
            case 35:
            case 36: {
                quality.setRolehszs(quality.getRolehszs() + value);
                break;
            }
            case 37: {
                quality.setRoleghsh(quality.getRoleghsh() + value);
                break;
            }
            case 38:
            case 39: {
                quality.setRoleksf(quality.getRoleksf() + value);
                break;
            }
            case 40:
            case 41: {
                quality.setRolekhf(quality.getRolekhf() + value);
                break;
            }
            case 42:
            case 43: {
                quality.setRoleklf(quality.getRoleklf() + value);
                break;
            }
            case 44:
            case 45: {
                quality.setRolekff(quality.getRolekff() + value);
                break;
            }
            case 46: {
                quality.setRolekhs(quality.getRolekhs() + value);
                break;
            }
            case 47: {
                quality.setRolekhl(quality.getRolekhl() + value);
                break;
            }
            case 48: {
                quality.setRolekfy(quality.getRolekfy() + value);
                break;
            }
            case 49: {
                quality.setRolekyw(quality.getRolekyw() + value);
                break;
            }
            case 50: {
                quality.setRolekgh(quality.getRolekgh() + value);
                break;
            }
            case 51:
            case 52: {
                quality.setRoleksc(quality.getRoleksc() + value);
                break;
            }
            case 53:
            case 54: {
                quality.setRoleklb(quality.getRoleklb() + value);
                break;
            }
            case 55: {
                quality.setRolekzd(quality.getRolekzd() + value);
                break;
            }
            case 56: {
                quality.setRolekzs(quality.getRolekzs() + value);
                break;
            }
            case 57: {
                quality.setKfz(quality.getKfz() + value);
                break;
            }
            case 58: {
                quality.setKlsh(quality.getKlsh() + value);
                break;
            }
            case 59:
            case 60: {
                quality.setRolehslf(quality.getRolehslf() + value);
                break;
            }
            case 61:
            case 62: {
                quality.setRolehssf(quality.getRolehssf() + value);
                break;
            }
            case 63:
            case 64: {
                quality.setRolehshf(quality.getRolehshf() + value);
                break;
            }
            case 65:
            case 66: {
                quality.setRolehsff(quality.getRolehsff() + value);
                break;
            }
            case 67: {
                quality.setRolelfsh(quality.getRolelfsh() + value);
                break;
            }
            case 68: {
                quality.setRolesfsh(quality.getRolesfsh() + value);
                break;
            }
            case 69: {
                quality.setRoleffsh(quality.getRoleffsh() + value);
                break;
            }
            case 70: {
                quality.setRolehfsh(quality.getRolehfsh() + value);
                break;
            }
            case 71: {
                quality.setRolegstronghostfire(quality.getRolegstronghostfire() + value);
                break;
            }
            case 72: {
                quality.setRolestrongforget(quality.getRolestrongforget() + value);
                break;
            }
            case 73:
            case 74:
            case 75: {
                quality.setRolesssh(quality.getRolesssh() + value);
                break;
            }
            case 76: {
                quality.setRolestrongbodyblooddeep(quality.getRolestrongbodyblooddeep() + value);
                break;
            }
            case 77:
            case 78: {
                quality.setRolelfkb(quality.getRolelfkb() + value);
                break;
            }
            case 79:
            case 80: {
                quality.setRolehfkb(quality.getRolehfkb() + value);
                break;
            }
            case 81:
            case 82: {
                quality.setRolesfkb(quality.getRolesfkb() + value);
                break;
            }
            case 83:
            case 84: {
                quality.setRoleffkb(quality.getRoleffkb() + value);
                break;
            }
            case 85: {
                quality.setBlfcd(quality.getBlfcd() + value);
                break;
            }
            case 86: {
                quality.setBffcd(quality.getBffcd() + value);
                break;
            }
            case 87: {
                quality.setBsfcd(quality.getBsfcd() + value);
                break;
            }
            case 88: {
                quality.setBhfcd(quality.getBhfcd() + value);
                break;
            }
            case 89: {
                quality.setBlfcd(quality.getBlfcd() + value);
                quality.setBffcd(quality.getBffcd() + value);
                quality.setBsfcd(quality.getBsfcd() + value);
                quality.setBhfcd(quality.getBhfcd() + value);
                break;
            }
            case 90: {
                quality.setBghcd(quality.getBghcd() + value);
                break;
            }
            case 91: {
                quality.setBsccd(quality.getBsccd() + value);
                break;
            }
            case 92:
            case 93: {
                quality.setRolekwl(quality.getRolekwl() + value);
                break;
            }
            case 94: {
                quality.setRolewxj(quality.getRolewxj() + value);
                break;
            }
            case 95: {
                quality.setRolewxm(quality.getRolewxm() + value);
                break;
            }
            case 96: {
                quality.setRolewxs(quality.getRolewxs() + value);
                break;
            }
            case 97: {
                quality.setRolewxh(quality.getRolewxh() + value);
                break;
            }
            case 98: {
                quality.setRolewxt(quality.getRolewxt() + value);
                break;
            }
            case 99:
            case 100:
            case 101: {
                quality.setRolefdsl(quality.getRolefdsl() + value);
                break;
            }
            case 102: {
                quality.setRolefljv(quality.getRolefljv() + value);
                break;
            }
            case 103: {
                quality.setRoleffjv(quality.getRoleffjv() + value);
                break;
            }
            case 104:
            case 105: {
                quality.setRoleqff(quality.getRoleqff() + value);
                break;
            }
            case 106:
            case 107: {
                quality.setRoleqlf(quality.getRoleqlf() + value);
                break;
            }
            case 108:
            case 109: {
                quality.setRoleqhf(quality.getRoleqhf() + value);
                break;
            }
            case 110:
            case 111: {
                quality.setRoleqsf(quality.getRoleqsf() + value);
                break;
            }
            case 112:
            case 113: {
                quality.setRoleqzs(quality.getRoleqzs() + value);
                break;
            }
            case 114: {
                quality.setRolewxqkj(quality.getRolewxqkj() + value);
                break;
            }
            case 115: {
                quality.setRolewxqks(quality.getRolewxqks() + value);
                break;
            }
            case 116: {
                quality.setRolewxqkh(quality.getRolewxqkh() + value);
                break;
            }
            case 117: {
                quality.setRolewxqkm(quality.getRolewxqkm() + value);
                break;
            }
            case 118: {
                quality.setRolewxqkt(quality.getRolewxqkt() + value);
                break;
            }
            case 119: {
                quality.setRoleffzcd(quality.getRoleffzcd() + value);
                break;
            }
            case 120: {
                quality.setRoleffzl(quality.getRoleffzl() + value);
                break;
            }
            case 121:
            case 122: {
                quality.setRoleghkb(quality.getRoleghkb() + value);
                break;
            }
            case 123:
            case 124: {
                quality.setRolesskb(quality.getRolesskb() + value);
                break;
            }
            case 125: {
                quality.setRolehsds(quality.getRolehsds() + value);
                break;
            }
            case 126: {
                quality.setRolehsfj(quality.getRolehsfj() + value);
                break;
            }
            case 127: {
                quality.setRolexfljl(quality.getRolexfljl() + value);
                break;
            }
            case 128: {
                quality.setRolexfljs(quality.getRolexfljs() + value);
                break;
            }
            case 129: {
                quality.setRolehsxfkl(quality.getRolehsxfkl() + value);
                break;
            }
            case 130: {
                quality.setRolehsxfcd(quality.getRolehsxfcd() + value);
                break;
            }
            case 131:
            case 132: {
                quality.setRolehsyw(quality.getRolehsyw() + value);
                break;
            }
            case 133:
            case 134: {
                quality.setRolehsgh(quality.getRolehsgh() + value);
                break;
            }
            case 135:
            case 136: {
                quality.setJqgjfs(quality.getJqgjfs() + value);
                break;
            }
            case 137:
            case 138: {
                quality.setJqfyfs(quality.getJqfyfs() + value);
                break;
            }
            case 139:
            case 140: {
                quality.setJqsdfs(quality.getJqsdfs() + value);
                break;
            }
            case 141: {
                quality.setQ_qk(quality.getQ_qk() + value);
                break;
            }
            case 142: {
                quality.setK_qk(quality.getK_qk() + value);
                break;
            }
            case 143: {
                quality.setK_wsxsh(quality.getK_wsxsh() + value);
                break;
            }
            case 144: {
                quality.setK_zshp(quality.getK_zshp() + value);
                break;
            }
            case 145: {
                quality.setK_zsmp(quality.getK_zsmp() + value);
                break;
            }
            case 146: {
                quality.setQ_zhssh(quality.getQ_zhssh() + value);
                break;
            }
            case 147: {
                quality.setK_jge(quality.getK_jge() + value);
                break;
            }
            case 148: {
                quality.setK_qw(quality.getK_qw() + value);
                break;
            }
            case 149:
            case 150: {
                quality.setK_ndhr(quality.getK_ndhr() + value);
                break;
            }
            case 151:
            case 152: {
                quality.setK_ndqm(quality.getK_ndqm() + value);
                break;
            }
            case 153:
            case 154: {
                quality.setK_ndtm(quality.getK_ndtm() + value);
                break;
            }
            case 155:
            case 156: {
                quality.setK_ndxl(quality.getK_ndxl() + value);
                break;
            }
            case 157:
            case 158: {
                quality.setK_ndfg(quality.getK_ndfg() + value);
                break;
            }
            case 159:
            case 160: {
                quality.setK_ndgs(quality.getK_ndgs() + value);
                break;
            }
            case 161: {
                quality.setKzml(quality.getKzml() + value);
                break;
            }
            case 162: {
                quality.setF_xs(quality.getF_xs() + value);
                break;
            }
            case 163: {
                quality.setF_xl(quality.getF_xl() + value);
                break;
            }
            case 164: {
                quality.setF_xf(quality.getF_xf() + value);
                break;
            }
            case 165: {
                quality.setF_xh(quality.getF_xh() + value);
                break;
            }
            case 166: {
                quality.setF_h(quality.getF_h() + value);
                break;
            }
            case 167: {
                quality.setF_f(quality.getF_f() + value);
                break;
            }
            case 168: {
                quality.setF_d(quality.getF_d() + value);
                break;
            }
            case 169: {
                quality.setF_zs(quality.getF_zs() + value);
                break;
            }
            case 170: {
                quality.setF_sc(quality.getF_sc() + value);
                break;
            }
            case 171: {
                quality.setRolelfkb(quality.getRolelfkb() + value);
                quality.setRolehfkb(quality.getRolehfkb() + value);
                quality.setRolesfkb(quality.getRolesfkb() + value);
                quality.setRoleffkb(quality.getRoleffkb() + value);
                break;
            }
            case 172: {
                quality.setRolehslf(quality.getRolehslf() + value);
                quality.setRolehssf(quality.getRolehssf() + value);
                quality.setRolehshf(quality.getRolehshf() + value);
                quality.setRolehsff(quality.getRolehsff() + value);
                break;
            }
            case 173: {
                quality.setRolehshs(quality.getRolehshs() + value);
                quality.setRolehsfy(quality.getRolehsfy() + value);
                quality.setRolehshl(quality.getRolehshl() + value);
                quality.setRolehszd(quality.getRolehszd() + value);
                break;
            }
            case 174:
            case 175: {
                quality.setQzds(quality.getQzds() + value);
                break;
            }
            case 176: {
                quality.setKzds(quality.getKzds() + value);
                break;
            }
            case 177: {
                quality.setKbf(quality.getKbf() + value);
                break;
            }
            case 178: {
                quality.setKbh(quality.getKbh() + value);
                break;
            }
            case 179: {
                quality.setKbs(quality.getKbs() + value);
                break;
            }
            case 180: {
                quality.setKbl(quality.getKbl() + value);
                break;
            }
            case 181: {
                quality.setKbg(quality.getKbg() + value);
                break;
            }
            case 182: {
                quality.setQmh(quality.getQmh() + value);
                break;
            }
            case 183: {
                quality.setQjg(quality.getQjg() + value);
                break;
            }
            case 184: {
                quality.setQqw(quality.getQqw() + value);
                break;
            }
            case 185: {
                quality.setBfy(quality.getBfy() + value);
                break;
            }
            case 186: {
                quality.setBhl(quality.getBhl() + value);
                break;
            }
            case 187: {
                quality.setBhs(quality.getBhs() + value);
                break;
            }
            case 188: {
                quality.setBzd(quality.getBzd() + value);
                break;
            }
            case 189: {
                quality.setBjf(quality.getBjf() + value);
                break;
            }
            case 190: {
                quality.setBjg(quality.getBjg() + value);
                break;
            }
            case 191: {
                quality.setBzs(quality.getBzs() + value);
                break;
            }
            case 192: {
                quality.setByw(quality.getByw() + value);
                break;
            }
            case 193: {
                quality.setBmh(quality.getBmh() + value);
                break;
            }
            case 194:
            case 195:
            case 196: {
                quality.setFsbj(quality.getFsbj() + value);
                break;
            }
            case 197:
            case 198:
            case 199: {
                quality.setBjlf(quality.getBjlf() + value);
                break;
            }
            case 200:
            case 201:
            case 202: {
                quality.setBjhf(quality.getBjhf() + value);
                break;
            }
            case 203:
            case 204:
            case 205: {
                quality.setBjsf(quality.getBjsf() + value);
                break;
            }
            case 206:
            case 207:
            case 208: {
                quality.setBjff(quality.getBjff() + value);
                break;
            }
            case 209:
            case 210:
            case 211: {
                quality.setBjlf(quality.getBjlf() + value);
                quality.setBjhf(quality.getBjhf() + value);
                quality.setBjsf(quality.getBjsf() + value);
                quality.setBjff(quality.getBjff() + value);
                break;
            }
            case 212:
            case 213:
            case 214: {
                quality.setBjgh(quality.getBjgh() + value);
                break;
            }
            case 215:
            case 216:
            case 217: {
                quality.setBjsc(quality.getBjsc() + value);
                break;
            }
            case 218: {
                quality.setFsbjcd(quality.getFsbjcd() + value);
                break;
            }
            case 219: {
                quality.setBjlfcd(quality.getBjlfcd() + value);
                break;
            }
            case 220: {
                quality.setBjffcd(quality.getBjffcd() + value);
                break;
            }
            case 221: {
                quality.setBjsfcd(quality.getBjsfcd() + value);
                break;
            }
            case 222: {
                quality.setBjhfcd(quality.getBjhfcd() + value);
                break;
            }
            case 223: {
                quality.setBjlfcd(quality.getBjlfcd() + value);
                quality.setBjffcd(quality.getBjffcd() + value);
                quality.setBjsfcd(quality.getBjsfcd() + value);
                quality.setBjhfcd(quality.getBjhfcd() + value);
                break;
            }
            case 224: {
                quality.setBjghcd(quality.getBjghcd() + value);
                break;
            }
            case 225: {
                quality.setBjsccd(quality.getBjsccd() + value);
                break;
            }
            case 226: {
                quality.setEfsds(quality.getEfsds() + value);
                break;
            }
            case 227: {
                quality.setEjs(quality.getEjs() + value);
                break;
            }
            case 228: {
                quality.setEzs(quality.getEzs() + value);
                break;
            }
            case 229: {
                quality.setQlpl(quality.getQlpl() + value);
                break;
            }
            case 230: {
                quality.setQlfy(quality.getQlfy() + value);
                break;
            }
            case 231: {
                quality.setQlcb(quality.getQlcb() + value);
                break;
            }
            case 232: {
                quality.setQlglv(quality.getQlglv() + value);
                break;
            }
            case 233: {
                quality.setQlglc(quality.getQlglc() + value);
                break;
            }
            case 234:
            case 235: {
                quality.setFsmz(quality.getFsmz() + value);
                break;
            }
            case 236:
            case 237:
            case 238: {
                quality.setMzs(quality.getMzs() + value);
                break;
            }
            case 239:
            case 240:
            case 241: {
                quality.setMhf(quality.getMhf() + value);
                break;
            }
            case 242:
            case 243:
            case 244: {
                quality.setMlf(quality.getMlf() + value);
                break;
            }
            case 245:
            case 246:
            case 247: {
                quality.setMff(quality.getMff() + value);
                break;
            }
            case 248:
            case 249:
            case 250: {
                quality.setMsf(quality.getMsf() + value);
                break;
            }
            case 251:
            case 252:
            case 253: {
                quality.setMdf(quality.getMdf() + value);
                break;
            }
            case 254:
            case 255:
            case 256: {
                quality.setMfy(quality.getMfy() + value);
                break;
            }
            case 257:
            case 258:
            case 259: {
                quality.setMhl(quality.getMhl() + value);
                break;
            }
            case 260:
            case 261:
            case 262: {
                quality.setMhs(quality.getMhs() + value);
                break;
            }
            case 263:
            case 264:
            case 265: {
                quality.setMyw(quality.getMyw() + value);
                break;
            }
            case 266:
            case 267:
            case 268: {
                quality.setMgh(quality.getMgh() + value);
                break;
            }
            case 269:
            case 270:
            case 271: {
                quality.setMsc(quality.getMsc() + value);
                break;
            }
            case 272:
            case 273: {
                quality.setDzs(quality.getDzs() + value);
                break;
            }
            case 274:
            case 275: {
                quality.setDhf(quality.getDhf() + value);
                break;
            }
            case 276:
            case 277: {
                quality.setDlf(quality.getDlf() + value);
                break;
            }
            case 278:
            case 279: {
                quality.setDff(quality.getDff() + value);
                break;
            }
            case 280:
            case 281: {
                quality.setDsf(quality.getDsf() + value);
                break;
            }
            case 282:
            case 283: {
                quality.setDdf(quality.getDdf() + value);
                break;
            }
            case 284:
            case 285: {
                quality.setDfy(quality.getDfy() + value);
                break;
            }
            case 286:
            case 287: {
                quality.setDhl(quality.getDhl() + value);
                break;
            }
            case 288:
            case 289: {
                quality.setDhs(quality.getDhs() + value);
                break;
            }
            case 290:
            case 291: {
                quality.setDyw(quality.getDyw() + value);
                break;
            }
            case 292:
            case 293: {
                quality.setDgh(quality.getDgh() + value);
                break;
            }
            case 294:
            case 295: {
                quality.setDsc(quality.getDsc() + value);
                break;
            }
            case 296: {
                quality.setJsf(quality.getJsf() + value);
                break;
            }
            case 297: {
                quality.setJff(quality.getJff() + value);
                break;
            }
            case 298: {
                quality.setJlf(quality.getJlf() + value);
                break;
            }
            case 299: {
                quality.setJhf(quality.getJhf() + value);
                break;
            }
            case 300: {
                quality.setJgh(quality.getJgh() + value);
                break;
            }
            case 301: {
                quality.setHffj(quality.getHffj() + value);
                break;
            }
            case 302: {
                quality.setLffj(quality.getLffj() + value);
                break;
            }
            case 303: {
                quality.setSffj(quality.getSffj() + value);
                break;
            }
            case 304: {
                quality.setFffj(quality.getFffj() + value);
                break;
            }
            case 305: {
                quality.setSfmsfs(quality.getSfmsfs() + value);
                break;
            }
            case 306: {
                quality.setSfhqmm(quality.getSfhqmm() + value);
                break;
            }
            case 307: {
                quality.setSfqkjs(quality.getSfqkjs() + value);
                break;
            }
        }
    }
}
