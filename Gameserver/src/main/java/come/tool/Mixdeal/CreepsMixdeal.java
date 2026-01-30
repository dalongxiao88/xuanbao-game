package come.tool.Mixdeal;

import java.util.HashMap;
import java.util.ArrayList;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;
import java.util.List;
import java.util.Map;

public class CreepsMixdeal
{
    static Map<Integer, String> map;
    public static List<String> neiDanNames;
    
    public static int good(int id) {
        if ((id >= 1600 && id <= 1616) || id == 6100 || id == 7006) {
            return 1;
        }
        if ((id >= 1400 && id <= 1416) || id == 6106 || id == 7012) {
            return 2;
        }
        if ((id >= 1100 && id <= 1116) || id == 6124 || id == 7021) {
            return 3;
        }
        if ((id >= 1200 && id <= 1216) || id == 6122 || id == 7022) {
            return 4;
        }
        if ((id >= 2201 && id <= 2216) || id == 6109 || id == 7016) {
            return 5;
        }
        if ((id >= 2400 && id <= 2416) || id == 6119 || id == 7020) {
            return 6;
        }
        if ((id >= 1300 && id <= 1316) || id == 6103 || id == 7009) {
            return 7;
        }
        if ((id >= 1700 && id <= 1716) || id == 6102 || id == 7008) {
            return 8;
        }
        if ((id >= 2101 && id <= 2116) || id == 7013 || id == 6105) {
            return 9;
        }
        if ((id >= 1000 && id <= 1016) || id == 6118 || id == 7007) {
            return 10;
        }
        if ((id >= 2501 && id <= 2516) || id == 7019 || id == 6120) {
            return 11;
        }
        if ((id >= 1800 && id <= 1816) || id == 6104 || id == 7011) {
            return 12;
        }
        if ((id >= 1900 && id <= 1916) || id == 6108 || id == 7017) {
            return 13;
        }
        if ((id >= 2301 && id <= 2316) || id == 6109 || id == 7010) {
            return 14;
        }
        if ((id >= 1500 && id <= 1516) || id == 7014 || id == 6117) {
            return 15;
        }
        if ((id >= 2001 && id <= 2016) || id == 6107 || id == 7015) {
            return 16;
        }
        if ((id >= 2601 && id <= 2616) || id == 7018 || id == 6121) {
            return 17;
        }
        if ((id >= 2617 && id <= 2632) || id == 6125 || id == 7023) {
            return 18;
        }
        return 0;
    }
    
    public static String getL_LL(String model) {
        if (model.contains("24001") || model.contains("4294991297") || model.contains("25769827777") || model.contains("81604402625") || model.contains("103079239105")) {
            return "51101";
        }
        if (model.contains("24002") || model.contains("42949696962") || model.contains("51539631554") || model.contains("128849042882") || model.contains("120259108290")) {
            return "51102";
        }
        if (model.contains("24003") || model.contains("77309435331") || model.contains("47244664259") || model.contains("124554075587") || model.contains("154618846659")) {
            return "51103";
        }
        if (model.contains("24004") || model.contains("12884925892") || model.contains("38654729668") || model.contains("90194337220") || model.contains("115964140996")) {
            return "52101";
        }
        if (model.contains("24005") || model.contains("77309435333") || model.contains("51539631557") || model.contains("154618846661") || model.contains("128849042885")) {
            return "52102";
        }
        if (model.contains("24006") || model.contains("4294991302") || model.contains("73014468038") || model.contains("81604402630") || model.contains("150323879366")) {
            return "52103";
        }
        return model;
    }
    
    public static void addNeiDanSkill(ManData data, int id, int zs, int dj) {
        String goodname = (String)CreepsMixdeal.map.get(Integer.valueOf(id));
        if (goodname == null) {
            return;
        }
        if (goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗")) {
            data.neidang("tj", zs);
        }
        else if (goodname.equals("分光化影") || goodname.equals("天魔解体") || goodname.equals("小楼夜哭") || goodname.equals("青面獠牙")) {
            data.neidang("mj", zs);
        }
        else if (goodname.equals("乘风破浪") || goodname.equals("霹雳流星") || goodname.equals("大海无量") || goodname.equals("祝融取火")) {
            data.neidang("xl", zs);
        }
        else {
            data.neidang("rj", zs);
        }
        if (id >= 1511 && id <= 1524) {
            FightingSkill skill = accessNedanMsg(goodname, dj, zs, dj, zs, 20000000L, (long)(data.getMp_z() / 4));
            data.addSkill(skill);
        }
    }
    
    public static void addNeiDanSkill(ManData data, int id) {
        String goodname = (String)CreepsMixdeal.map.get(Integer.valueOf(id));
        if (goodname == null) {
            return;
        }
        if (goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗")) {
            data.neidang("tj", 4);
        }
        else if (goodname.equals("分光化影") || goodname.equals("天魔解体") || goodname.equals("小楼夜哭") || goodname.equals("青面獠牙")) {
            data.neidang("mj", 4);
        }
        else if (goodname.equals("乘风破浪") || goodname.equals("霹雳流星") || goodname.equals("大海无量") || goodname.equals("祝融取火")) {
            data.neidang("xl", 4);
        }
        else {
            data.neidang("rj", 4);
        }
        if (id >= 1511 && id <= 1524) {
            FightingSkill skill = accessNedanMsg(goodname, 100, 3, 100, 3, 2000000L, (long)(data.getMp_z() / 4));
            data.addSkill(skill);
        }
    }
    
    public static FightingSkill accessNedanMsg(String goodsname, int nddj, int ndzscs, int zhsdj, int zhszscs, long zhsqm, long l) {
        if (goodsname.equals("浩然正气")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(l * 1L + 1L) + ndjl) * 10000.0) / 10000.0;
            return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndcd * 10000.0) / 100.0);
        }
        if (goodsname.equals("隔山打牛")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(l * 1L + 1L) + ndjl * 3.0) * 1000.0) / 1000.0;
            return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndcd * 10000.0) / 100.0);
        }
        if (goodsname.equals("天魔解体")) {
            double ndjl = 0.0;
            if (zhszscs == 0) {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
            }
            else {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 3755.0) * 1000.0) / 1000.0 + 0.01;
            }
            double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(l * 1L) + 0.01) + 0.2) * 1000.0) / 1000.0;
            return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
        }
        else {
            if (goodsname.equals("分光化影")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(l * 1L) + 0.01) + 0.2) * 1000.0) / 1000.0;
                return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            if (goodsname.equals("小楼夜哭")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 206600.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4170.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = ndjl * 0.3;
                return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            if (goodsname.equals("青面獠牙")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 698000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 7500.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = ndjl * 0.7;
                return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)l, 1.57));
            return new FightingSkill(goodsname, (double)Math.round(ndjl * 10000.0) / 100.0, ndcd);
        }
    }
    
    public static double xstz(int zhs_zscs, int nd_zscs) {
        if (zhs_zscs * nd_zscs == 1) {
            return 1.04;
        }
        if (zhs_zscs * nd_zscs == 4) {
            return 1.071;
        }
        if (zhs_zscs * nd_zscs == 6) {
            return 1.073;
        }
        if (zhs_zscs * nd_zscs == 9) {
            return 1.09;
        }
        return 1.0;
    }
    
    static {
        CreepsMixdeal.neiDanNames = new ArrayList<>();
        (CreepsMixdeal.map = new HashMap<>()).put(1511, "分光化影");
        CreepsMixdeal.map.put(1512, "天魔解体");
        CreepsMixdeal.map.put(1513, "小楼夜哭");
        CreepsMixdeal.map.put(1514, "青面獠牙");
        CreepsMixdeal.map.put(1515, "浩然正气");
        CreepsMixdeal.map.put(1516, "隔山打牛");
        CreepsMixdeal.map.put(1517, "乘风破浪");
        CreepsMixdeal.map.put(1518, "霹雳流星");
        CreepsMixdeal.map.put(1519, "大海无量");
        CreepsMixdeal.map.put(1520, "祝融取火");
        CreepsMixdeal.map.put(1521, "红颜白发");
        CreepsMixdeal.map.put(1522, "梅花三弄");
        CreepsMixdeal.map.put(1523, "开天辟地");
        CreepsMixdeal.map.put(1524, "万佛朝宗");

        CreepsMixdeal.neiDanNames.add("分光化影");
        CreepsMixdeal.neiDanNames.add("天魔解体");
        CreepsMixdeal.neiDanNames.add("小楼夜哭");
        CreepsMixdeal.neiDanNames.add("青面獠牙");
        CreepsMixdeal.neiDanNames.add("浩然正气");
        CreepsMixdeal.neiDanNames.add("隔山打牛");
        CreepsMixdeal.neiDanNames.add("乘风破浪");
        CreepsMixdeal.neiDanNames.add("霹雳流星");
        CreepsMixdeal.neiDanNames.add("大海无量");
        CreepsMixdeal.neiDanNames.add("祝融取火");
        CreepsMixdeal.neiDanNames.add("红颜白发");
        CreepsMixdeal.neiDanNames.add("梅花三弄");
        CreepsMixdeal.neiDanNames.add("开天辟地");
        CreepsMixdeal.neiDanNames.add("万佛朝宗");
    }
}
