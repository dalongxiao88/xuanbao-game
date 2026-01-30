package com.tool.role;

import java.math.RoundingMode;
import java.math.BigDecimal;

public class BaseValue
{
    public static final double[] bases;
    public static final double[] basevs;
    
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
    
    public static double getValue(BigDecimal raceid, int type, int zhi) {
        if (type == 0) {
            return BaseValue.bases[zhi + getratio(raceid) * 4];
        }
        return BaseValue.basevs[zhi + getratio(raceid) * 5];
    }
    
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
        return x2;
    }
    
    public static int getMeridiansExp(int lvl) {
        return lvl * 100;
    }
    
    public static int getMeridiansTotalExp(int lvl) {
        return (lvl + 1) * lvl * 50;
    }
    
    public static int getMeridiansLvl(int value) {
        int lvl = 0;
        do {
            ++lvl;
            value -= getMeridiansExp(lvl);
        } while (value >= 0);
        return lvl;
    }
    
    static {
        bases = new double[] { 360.0, 300.0, 70.0, 8.0, 330.0, 210.0, 80.0, 10.0, 300.0, 390.0, 60.0, 10.0, 270.0, 350.0, 80.0, 9.0, 300.0, 240.0, 80.0, 10.0 };
        basevs = new double[] { 1.2, 1.0, 0.95, 0.8, 1.05, 1.1, 0.6, 1.3, 1.0, 1.0, 1.0, 1.4, 0.7, 1.0, 0.9, 1.25, 1.05, 0.95, 0.75, 0.9, 0.9, 0.7, 1.3, 1.0, 1.0 };
    }
}
