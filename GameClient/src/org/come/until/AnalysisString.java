package org.come.until;

import java.math.BigDecimal;

public class AnalysisString
{
    public static boolean jiaoyi(long type) {
        return type == 1L || type == 3L || type == 5L;
    }
    
    public static boolean geiyu(long type) {
        return type != 0L;
    }
    
    public static int[] yao(String value) {
        int[] yao = new int[4];
        String[] names = value.split("\\|");
        for (int i = 0; i < names.length; ++i) {
            if (names[i].split("=").length >= 2) {
                if (names[i].split("=")[0].equals("HP")) {
                    yao[0] = Integer.parseInt(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("MP")) {
                    yao[1] = Integer.parseInt(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("HP%")) {
                    yao[2] = Integer.parseInt(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("MP%")) {
                    yao[3] = Integer.parseInt(names[i].split("=")[1]);
                }
            }
        }
        return yao;
    }
    
    public static long[] xiaohao(String value) {
        long[] xiaohao = new long[6];
        String[] names = value.split("\\|");
        for (int i = 0; i < names.length; ++i) {
            if (names[i].split("=").length >= 2) {
                if (names[i].split("=")[0].equals("钱")) {
                    xiaohao[0] = Long.parseLong(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("点")) {
                    xiaohao[1] = Long.parseLong(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("经验")) {
                    xiaohao[2] = Long.parseLong(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("亲密")) {
                    xiaohao[3] = Long.parseLong(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("技能")) {
                    xiaohao[4] = Long.parseLong(names[i].split("=")[1]);
                }
                else if (names[i].split("=")[0].equals("转区币")) {
                    xiaohao[5] = Long.parseLong(names[i].split("=")[1]);
                }
            }
        }
        return xiaohao;
    }
    
    public static int shuliandu(int lvl) {
        int sld = 5000;
        if (lvl <= 102) {
            sld += 5000;
        }
        else if (lvl <= 210) {
            sld += 10000;
        }
        else if (lvl <= 338) {
            sld += 15000;
        }
        else {
            sld += 20000;
        }
        return sld;
    }
    
    public static int shuliandu(int lvl, Integer type) {
        int sld = 5000;
        if (lvl <= 102) {
            sld += 5000;
        }
        else if (lvl <= 210) {
            sld += 10000;
        }
        else if (lvl <= 338) {
            sld += 15000;
        }
        else {
            sld += 20000;
        }
        return sld;
    }
    
    public static String lvl(int lvl) {
        if (lvl <= 102) {
            return "0转" + lvl;
        }
        if (lvl <= 210) {
            return "1转" + (lvl - 102 + 14);
        }
        if (lvl <= 338) {
            return "2转" + (lvl - 210 + 14);
        }
        return (lvl <= 459) ? ("3转" + (lvl - 338 + 59)) : ("飞升" + (lvl - 459 + 139));
    }
    
    public static int lvltrue(int lvl) {
        if (lvl <= 102) {
            return 0;
        }
        if (lvl <= 210) {
            return 1;
        }
        if (lvl <= 338) {
            return 2;
        }
        return (lvl <= 459) ? 3 : 4;
    }
    
    public static String petLvl(int lvl) {
        if (lvl <= 100) {
            return "0转" + lvl;
        }
        if (lvl <= 221) {
            return "1转" + (lvl - 101);
        }
        if (lvl <= 362) {
            return "2转" + (lvl - 222);
        }
        return (lvl <= 543) ? ("3转" + (lvl - 363)) : ("点化" + (lvl - 544));
    }
    
    public static int petTurnRount(int lvl) {
        if (lvl <= 100) {
            return 0;
        }
        if (lvl <= 221) {
            return 1;
        }
        if (lvl <= 362) {
            return 2;
        }
        return (lvl <= 543) ? 3 : 4;
    }
    
    public static int petLvlint(int lvl) {
        if (lvl <= 100) {
            return lvl;
        }
        if (lvl <= 221) {
            return lvl - 101;
        }
        if (lvl <= 362) {
            return lvl - 222;
        }
        return (lvl <= 543) ? (lvl - 363) : (lvl - 544);
    }
    
    public static BigDecimal mathDouble(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    
    public static BigDecimal reduceDouble(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }
    
    public static void main(String[] args) {
        System.out.println(lvlint(448));
    }
    
    public static int lvlint(int lvl) {
        if (lvl <= 102) {
            return lvl;
        }
        if (lvl <= 210) {
            return lvl - 102 + 14;
        }
        if (lvl <= 338) {
            return lvl - 210 + 14;
        }
        return (lvl <= 459) ? (lvl - 338 + 59) : (lvl - 459 + 139);
    }
    
    public static int xiuwei(int lvl) {
        return (lvl < 150) ? 6 : (lvl - 144);
    }
    
    public static int xwUP(int lvl) {
        return xiuwei(lvl) * 5;
    }
    
    public static int lvldirection(String lvlstring) {
        String[] lvls = lvlstring.split("转");
        if (lvls.length == 1) {
            return Integer.parseInt(lvls[0]);
        }
        if (lvls[0].equals("1")) {
            return Integer.parseInt(lvls[1]) + 102 - 14;
        }
        if (lvls[0].equals("2")) {
            return Integer.parseInt(lvls[1]) + 210 - 14;
        }
        if (lvls[0].equals("3")) {
            return Integer.parseInt(lvls[1]) + 338 - 59;
        }
        return (lvlstring.indexOf("飞升") == 0) ? (Integer.parseInt(lvlstring.split("升")[1]) + 459 - 139) : Integer.parseInt(lvls[1]);
    }
    
    public static int lvldirection1(String lvlstring) {
        String[] lvlf = lvlstring.split("升");
        String[] lvls = lvlstring.split("转");
        if (!lvlstring.contains("飞升")) {
            if (lvls.length == 1) {
                return Integer.parseInt(lvls[0]);
            }
            if (lvls[0].equals("1")) {
                return Integer.parseInt(lvls[1]) + 102 - 14;
            }
            if (lvls[0].equals("2")) {
                return Integer.parseInt(lvls[1]) + 210 - 14;
            }
            if (lvls[0].equals("3")) {
                return Integer.parseInt(lvls[1]) + 338 - 59;
            }
            return (lvlstring.indexOf("飞升") == 0) ? (Integer.parseInt(lvlstring.split("升")[1]) + 459 - 139) : Integer.parseInt(lvlf[1]);
        }
        else {
            return (lvlstring.indexOf("飞升") == 0) ? (Integer.parseInt(lvlstring.split("升")[1]) + 459 - 139) : Integer.parseInt(lvlf[1]);
        }
    }
    
    public static boolean lvlfull(int lvl, String lvlstring) {
        return lvl >= lvldirection(lvlstring);
    }
    
    public static boolean lvlfull2(int lvl, String lvlstring) {
        return lvl <= lvldirection(lvlstring);
    }
    
    public static double valuejie(String value, String type) {
        String[] v = value.split("\\|");
        int i = 0;
        while (i < v.length) {
            String[] v2 = v[i].split("=");
            if (v2.length > 1 && type.equals(v2[0])) {
                if (!type.equals("活跃") && !type.equals("速度") && !type.equals("负敏")) {
                    return Double.parseDouble(v2[1]) / 100.0;
                }
                return Double.parseDouble(v2[1]);
            }
            else {
                ++i;
            }
        }
        return 0.0;
    }
}
