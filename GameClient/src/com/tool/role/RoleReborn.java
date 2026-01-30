package com.tool.role;

import org.come.until.Arith;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class RoleReborn
{
    public static String reborn(String[] skills, String yuben) {
        if (skills == null) {
            return isV(yuben);
        }
        if (yuben == null) {
            yuben = "";
        }
        Map<String, Double> map = new HashMap<>();
        if (yuben != null && !yuben.equals("")) {
            String[] vs = yuben.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                map.put(vss[0], Double.valueOf(Double.parseDouble(vss[1])));
            }
        }
        for (int j = 0; j < skills.length; ++j) {
            String[] ks = skills[j].split("_");
            SureGetMethod(map, ks[0], Integer.parseInt(ks[1]));
        }
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)entry.getKey());
            buffer.append("=");
            if ((double)entry.getValue() == (double)((Double)entry.getValue()).intValue()) {
                buffer.append(((Double)entry.getValue()).intValue());
            }
            else {
                buffer.append(entry.getValue());
            }
        }
        return isV(buffer.toString());
    }
    
    public static String isV(String yuben) {
        if (yuben == null || yuben.equals("")) {
            return null;
        }
        String[] vs = yuben.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            double value = Math.abs(Double.parseDouble(vss[1]));
            double max = 369000.0;
            if (vss[0].equals("抗混乱") || vss[0].equals("抗封印") || vss[0].equals("抗昏睡") || vss[0].equals("抗遗忘") || vss[0].equals("反震程度") || vss[0].equals("速度")) {
                max = Arith.mul(max, 1.25E-4);
            }
            else if (vss[0].equals("抗混乱上限") || vss[0].equals("抗封印上限") || vss[0].equals("抗昏睡上限") || vss[0].equals("抗遗忘上限")) {
                max = Arith.mul(max, 1.6875E-5);
            }
            else if (vss[0].equals("抗毒伤")) {
                max = Arith.mul(max, 0.02);
            }
            else if (vss[0].equals("MP成长") || vss[0].equals("HP成长")) {
                max = Arith.mul(max, 1.0E-6);
            }
            else if (vss[0].equals("物理吸收率")) {
                max = Arith.mul(max, 1.875E-4);
            }
            else if (vss[0].equals("抗震慑")) {
                max = Arith.mul(max, 1.125E-4);
            }
            else if (vss[0].equals("SP成长")) {
                max = Arith.mul(max, 7.5E-7);
            }
            else if (vss[0].equals("抗风") || vss[0].equals("抗雷") || vss[0].equals("抗水") || vss[0].equals("抗火") ||vss[0].equals("抗风法") || vss[0].equals("抗雷法") || vss[0].equals("抗水法") || vss[0].equals("抗火法") || vss[0].equals("抗鬼火")) {
                max = Arith.mul(max, 1.6875E-4);
            }
            else if (vss[0].equals("抗三尸")) {
                max = Arith.mul(max, 0.025);
            }
            else if (vss[0].equals("反震率") ) {
                max = Arith.mul(max, 6.5E-5);
            }
            else if ( vss[0].equals("躲闪")) {
                max = Arith.mul(max, 10E-5);
            }

            else if (vss[0].equals("伤害减免")) {
                max = Arith.mul(max, 1.85E-5);
            }
            else if (vss[0].equals("法术躲闪")) {
                max = Arith.mul(max, 1.464E-5);
            }
            else if (vss[0].equals("抗天魔解体") || vss[0].equals("抗分光化影") || vss[0].equals("抗青面獠牙") || vss[0].equals("抗小楼夜哭")) {
                max = Arith.mul(max, 0.00625);
            }
            else {
                return null;
            }
            if (value > max) {
                return null;
            }
        }
        return yuben;
    }
    
    private static void SureGetMethod(Map<String, Double> map, String str, int ed) {
        int id = Integer.parseInt(str) - 1000;
        int lvl = (id % 5 == 0) ? 5 : (id % 5);
        if (id <= 5) {
            addMap(map, "抗混乱", ed, lvl, 1.25E-4);
            addMap(map, "抗混乱上限", ed, lvl, 1.6875E-5);
        }
        else if (id <= 10) {
            addMap(map, "抗封印", ed, lvl, 1.25E-4);
            addMap(map, "抗封印上限", ed, lvl, 1.6875E-5);
        }
        else if (id <= 15) {
            addMap(map, "抗昏睡", ed, lvl, 1.25E-4);
            addMap(map, "抗昏睡上限", ed, lvl, 1.6875E-5);
        }
        else if (id <= 20) {
            addMap(map, "抗毒伤", ed, lvl, 0.02);
        }
        else if (id <= 25) {
            addMap(map, "MP成长", ed, lvl, 1.0E-6);
        }
        else if (id <= 30) {
            addMap(map, "HP成长", ed, lvl, 1.0E-6);
        }
        else if (id <= 35) {
            addMap(map, "物理吸收率", ed, lvl, 1.875E-4);
            addMap(map, "抗震慑", ed, lvl, 1.125E-4);
        }
        else if (id <= 40) {
            addMap(map, "SP成长", ed, lvl, 7.5E-7);
        }
        else if (id <= 45) {
            addMap(map, "抗风", ed, lvl, 1.6875E-4);
        }
        else if (id <= 50) {
            addMap(map, "抗雷", ed, lvl, 1.6875E-4);
        }
        else if (id <= 55) {
            addMap(map, "抗水", ed, lvl, 1.6875E-4);
        }
        else if (id <= 60) {
            addMap(map, "抗火", ed, lvl, 1.6875E-4);
        }
        else if (id <= 65) {
            addMap(map, "抗鬼火", ed, lvl, 1.6875E-4);
        }
        else if (id <= 70) {
            addMap(map, "抗三尸", ed, lvl, 0.025);
            addMap(map, "速度", ed, lvl, -1.25E-4);
        }
        else if (id <= 75) {
            addMap(map, "抗遗忘", ed, lvl, 1.25E-4);
            addMap(map, "抗遗忘上限", ed, lvl, 1.6875E-5);
        }
        else if (id <= 80) {
            addMap(map, "反震程度", ed, lvl, 1.25E-4);
            addMap(map, "反震率", ed, lvl, 6.5E-5);
            addMap(map, "抗天魔解体", ed, lvl, 0.00625);
            addMap(map, "抗分光化影", ed, lvl, 0.00625);
            addMap(map, "抗青面獠牙", ed, lvl, 0.00625);
            addMap(map, "抗小楼夜哭", ed, lvl, 0.00625);
        }
        else if (id <= 85) {
            addMap(map, "躲闪", ed, lvl, 10E-5);
        }
        else if (id <= 90) {
            addMap(map, "法术躲闪", ed, lvl, 1.464E-5);
        }
        else if (id <= 95) {
            addMap(map, "HP成长", ed, lvl, 1.0E-6);
        }
        else if (id <= 100) {
            addMap(map, "伤害减免", ed, lvl, 1.85E-5);//龙修正
        }
    }
    
    private static void addMap(Map<String, Double> map, String key, int point, int lvl, double xs) {
        double value = Point(lvl, point);
        value = Arith.mul(value, xs);
        Double v = (Double)map.get(key);
        if (v == null) {
            v = Double.valueOf(0.0);
        }
        v = Double.valueOf(Arith.add((double)v, value));
        map.put(key, v);
    }
    
    private static double Point(int lvl, int point) {
        if (lvl == 2) {
            return (double)point * 1.2;
        }
        if (lvl == 3) {
            return (double)point * 1.5;
        }
        if (lvl == 4) {
            return (double)(point * 2);
        }
        if (lvl == 5) {
            return (double)point * 2.5;
        }
        return (double)point;
    }
}
