package com.tool.image;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.math.BigDecimal;
import java.util.Map;

public class Creepsskin
{
    static Map<Integer, String> localNameMap;
    
    public static int getSex(BigDecimal se) {
        Set<BigDecimal> sepcisesone = new HashSet<>();
        sepcisesone.add(new BigDecimal(22009));
        sepcisesone.add(new BigDecimal(22011));
        sepcisesone.add(new BigDecimal(21009));
        sepcisesone.add(new BigDecimal(21011));
        sepcisesone.add(new BigDecimal(20009));
        sepcisesone.add(new BigDecimal(20011));
        Set<BigDecimal> sepcisestwo = new HashSet<>();
        sepcisestwo.add(new BigDecimal(22010));
        sepcisestwo.add(new BigDecimal(22012));
        sepcisestwo.add(new BigDecimal(21010));
        sepcisestwo.add(new BigDecimal(21012));
        sepcisestwo.add(new BigDecimal(20010));
        sepcisestwo.add(new BigDecimal(20012));
        if (sepcisesone.contains(se)) {
            return 1;
        }
        if (sepcisestwo.contains(se)) {
            return 0;
        }
        int id = se.intValue() % 10;
        return (id == 1 || id == 2 || id == 3 || id == 7 || id == 9) ? 1 : 0;
    }
    
    public static int getRace(BigDecimal se) {
        if (se == null) {
            return 0;
        }
        return 9981 + se.intValue() / 1000;
    }
    
    public static String getLocalName(int id) {
        return (String)Creepsskin.localNameMap.get(Integer.valueOf(id));
    }
    
    public static int getLocalID(String localName) {
        for (int key : Creepsskin.localNameMap.keySet()) {
            if (((String)Creepsskin.localNameMap.get(Integer.valueOf(key))).equals(localName)) {
                return key;
            }
        }
        return 20001;
    }
    
    static {
        (Creepsskin.localNameMap = new HashMap<>()).put(Integer.valueOf(20001), "逍遥生");
        Creepsskin.localNameMap.put(Integer.valueOf(20002), "剑侠客");
        Creepsskin.localNameMap.put(Integer.valueOf(20003), "猛壮士");
        Creepsskin.localNameMap.put(Integer.valueOf(20004), "飞燕女");
        Creepsskin.localNameMap.put(Integer.valueOf(20005), "英女侠");
        Creepsskin.localNameMap.put(Integer.valueOf(20006), "俏千金");
        Creepsskin.localNameMap.put(Integer.valueOf(20007), "飞剑侠");
        Creepsskin.localNameMap.put(Integer.valueOf(20008), "燕山雪");
        Creepsskin.localNameMap.put(Integer.valueOf(20009), "纯阳子");
        Creepsskin.localNameMap.put(Integer.valueOf(20010), "红拂女");
        Creepsskin.localNameMap.put(Integer.valueOf(20011), "神秀生");
        Creepsskin.localNameMap.put(Integer.valueOf(20012), "玲珑女");
        Creepsskin.localNameMap.put(Integer.valueOf(21001), "虎头怪");
        Creepsskin.localNameMap.put(Integer.valueOf(21002), "夺命妖");
        Creepsskin.localNameMap.put(Integer.valueOf(21003), "巨魔王");
        Creepsskin.localNameMap.put(Integer.valueOf(21004), "小蛮妖");
        Creepsskin.localNameMap.put(Integer.valueOf(21005), "骨精灵");
        Creepsskin.localNameMap.put(Integer.valueOf(21006), "狐美人");
        Creepsskin.localNameMap.put(Integer.valueOf(21007), "逆天魔");
        Creepsskin.localNameMap.put(Integer.valueOf(21008), "媚灵狐");
        Creepsskin.localNameMap.put(Integer.valueOf(21009), "混天魔");
        Creepsskin.localNameMap.put(Integer.valueOf(21010), "九尾狐");
        Creepsskin.localNameMap.put(Integer.valueOf(21011), "绝影魔");
        Creepsskin.localNameMap.put(Integer.valueOf(21012), "霜月灵");
        Creepsskin.localNameMap.put(Integer.valueOf(22001), "神天兵");
        Creepsskin.localNameMap.put(Integer.valueOf(22002), "智圣仙");
        Creepsskin.localNameMap.put(Integer.valueOf(22003), "龙战将");
        Creepsskin.localNameMap.put(Integer.valueOf(22004), "精灵仙");
        Creepsskin.localNameMap.put(Integer.valueOf(22005), "舞天姬");
        Creepsskin.localNameMap.put(Integer.valueOf(22006), "玄剑娥");
        Creepsskin.localNameMap.put(Integer.valueOf(22007), "武尊神");
        Creepsskin.localNameMap.put(Integer.valueOf(22008), "玄天姬");
        Creepsskin.localNameMap.put(Integer.valueOf(22009), "紫薇神");
        Creepsskin.localNameMap.put(Integer.valueOf(22010), "霓裳仙");
        Creepsskin.localNameMap.put(Integer.valueOf(22011), "青阳使");
        Creepsskin.localNameMap.put(Integer.valueOf(22012), "云中君");
        Creepsskin.localNameMap.put(Integer.valueOf(23001), "祭剑魂");
        Creepsskin.localNameMap.put(Integer.valueOf(23002), "猎魂引");
        Creepsskin.localNameMap.put(Integer.valueOf(23003), "无崖子");
        Creepsskin.localNameMap.put(Integer.valueOf(23004), "墨衣行");
        Creepsskin.localNameMap.put(Integer.valueOf(23005), "夜溪灵");
        Creepsskin.localNameMap.put(Integer.valueOf(23006), "幽梦影");
        Creepsskin.localNameMap.put(Integer.valueOf(23007), "南冠客");
        Creepsskin.localNameMap.put(Integer.valueOf(23008), "镜花影");
        Creepsskin.localNameMap.put(Integer.valueOf(24001), "沧浪君");
        Creepsskin.localNameMap.put(Integer.valueOf(24002), "龙渊客");
        Creepsskin.localNameMap.put(Integer.valueOf(24003), "忘忧子");
        Creepsskin.localNameMap.put(Integer.valueOf(24004), "骊珠儿");
        Creepsskin.localNameMap.put(Integer.valueOf(24005), "木兰行");
        Creepsskin.localNameMap.put(Integer.valueOf(24006), "莫解语");
        Creepsskin.localNameMap.put(Integer.valueOf(24007), "游无极");
        Creepsskin.localNameMap.put(Integer.valueOf(24008), "临九渊");
    }
}
