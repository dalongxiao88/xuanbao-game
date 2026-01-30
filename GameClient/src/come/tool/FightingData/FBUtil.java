package come.tool.FightingData;

public class FBUtil
{
    public static int getFBlvl(int t, int g, int zlvl, int qv, int lvl) {
        int pj = 0;
        g += t * 25;
        pj += g * 3;
        pj += zlvl;
        qv += lvl;
        pj += qv * 7;
        return pj >> 5;
    }
    
    public static int getFBcx(int id, int lvl) {
        if (id == 5007 || id == 5010 || id == 5012 || id == 5015) {
            return 0;
        }
        if (id == 5006) {
            if (lvl >= 158) {
                return 7;
            }
            if (lvl >= 112) {
                return 6;
            }
            if (lvl >= 65) {
                return 5;
            }
            if (lvl >= 20) {
                return 4;
            }
            return 3;
        }
        else if (id == 5009) {
            if (lvl >= 182) {
                return 8;
            }
            if (lvl >= 138) {
                return 7;
            }
            if (lvl >= 94) {
                return 6;
            }
            if (lvl >= 51) {
                return 5;
            }
            if (lvl >= 7) {
                return 4;
            }
            return 3;
        }
        else {
            if (lvl >= 200) {
                return 8;
            }
            if (lvl >= 156) {
                return 7;
            }
            if (lvl >= 112) {
                return 6;
            }
            if (lvl >= 80) {
                return 5;
            }
            if (lvl >= 60) {
                return 4;
            }
            if (lvl >= 20) {
                return 3;
            }
            return 2;
        }
    }
    
    public static int getFBsum(int id, int lvl) {
        if (id == 5003 || id == 5006 || id == 5008 || id == 5011 || id == 5014 || id == 5015) {
            return 1;
        }
        if (lvl >= 100) {
            return 2;
        }
        return 1;
    }
    
    public static int geshu(int level, int ed, String type) {
        if (type.equals("鬼火") || type.equals("火") || type.equals("水") || type.equals("雷") || type.equals("风")) {
            return xian(ed, level);
        }
        if (type.equals("震慑")) {
            return moz(ed, level);
        }
        if (type.equals("力量") || type.equals("抗性") || type.equals("加速") || type.equals("加狂暴") || type.equals("smmh")) {
            return moq(ed, level);
        }
        if (type.equals("中毒") || type.equals("封印") || type.equals("昏睡") || type.equals("遗忘")) {
            return renq(ed, level);
        }
        if (type.equals("混乱")) {
            return renh(ed, level);
        }
        if (type.equals("三尸虫")) {
            return guis(ed, level);
        }
        if (type.equals("霹雳")) {
            return lpl(ed, level);
        }
        return xian(ed, level);
    }
    
    public static int xian(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 720) {
                return 2;
            }
            if (ed < 5215) {
                return 3;
            }
            if (ed < 16610) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 558) {
                return 3;
            }
            if (ed < 5621) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public static int moz(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 426) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11868) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int moq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 214) {
                return 2;
            }
            if (ed < 2155) {
                return 3;
            }
            if (ed < 8324) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 117) {
                return 3;
            }
            if (ed < 1174) {
                return 4;
            }
            if (ed < 4533) {
                return 5;
            }
            if (ed < 11826) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int renq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 428) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11864) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int renh(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 1362) {
                return 2;
            }
            if (ed < 9866) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 973) {
                return 3;
            }
            if (ed < 7051) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public static int guiv(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 2200) {
                return 2;
            }
            if (ed < 4600) {
                return 3;
            }
            if (ed < 9600) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 4600) {
                return 4;
            }
            if (ed < 9600) {
                return 5;
            }
            if (ed < 12000) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int guis(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 5200) {
                return 2;
            }
            if (ed < 6800) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 6800) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public static int lpl(int ed, int lvl) {
        if (lvl == 3 || lvl == 5) {
            if (ed < 5200) {
                return 2;
            }
            if (ed < 6800) {
                return 3;
            }
            return 4;
        }
        else {
            return 1;
        }
    }
}
