package come.tool.FightingData;

import java.util.HashMap;
import org.come.entity.Species;
import java.util.Set;
import java.util.HashSet;
import java.math.BigDecimal;
import java.util.Map;

public class Sepcies_MixDeal
{
    static Map<Integer, String> localNameMap;
    
    public static int isSex(BigDecimal se1, BigDecimal se2) {
        if (se1 == null || se2 == null) {
            return 0;
        }
        return (getSex(se1) == getSex(se2)) ? 1 : 2;
    }
    
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
        int id = se.intValue();
        if (id >= 20001 && id <= 20012) {
            return 10001;
        }
        if (id >= 21001 && id <= 21012) {
            return 10002;
        }
        if (id >= 22001 && id <= 22012) {
            return 10003;
        }
        if (id >= 23001 && id <= 23012) {
            return 10004;
        }
        return 10005;
    }
    
    public static String getRaceString(BigDecimal se) {
        int id = se.intValue();
        if (id >= 20001 && id <= 20012) {
            return "人族";
        }
        if (id >= 21001 && id <= 21012) {
            return "魔族";
        }
        if (id >= 22001 && id <= 22012) {
            return "仙族";
        }
        if (id >= 23001 && id <= 23012) {
            return "鬼族";
        }
        return "龙族";
    }
    
    public static String getLocalName(int id) {
        return (String)Sepcies_MixDeal.localNameMap.get(Integer.valueOf(id));
    }
    
    public static Species getSpecies(BigDecimal species_Id) {
        Species species = new Species();
        species.setSpecies_id(species_Id);
        species.setSex((getSex(species_Id) == 0) ? "女" : "男");
        species.setRace_id(new BigDecimal(getRace(species_Id)));
        species.setLocalname(getLocalName(species_Id.intValue()));
        return species;
    }
    
    static {
        (Sepcies_MixDeal.localNameMap = new HashMap<>()).put(Integer.valueOf(20001), "逍遥生");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20002), "剑侠客");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20003), "猛壮士");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20004), "飞燕女");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20005), "英女侠");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20006), "俏千金");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20007), "飞剑侠");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20008), "燕山雪");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20009), "纯阳子");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20010), "红拂女");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20011), "神秀生");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(20012), "玲珑女");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21001), "虎头怪");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21002), "夺命妖");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21003), "巨魔王");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21004), "小蛮妖");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21005), "骨精灵");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21006), "狐美人");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21007), "逆天魔");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21008), "媚灵狐");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21009), "混天魔");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21010), "九尾狐");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21011), "绝影魔");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(21012), "霜月灵");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22001), "神天兵");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22002), "智圣仙");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22003), "龙战将");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22004), "精灵仙");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22005), "舞天姬");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22006), "玄剑娥");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22007), "武尊神");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22008), "玄天姬");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22009), "紫薇神");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22010), "霓裳仙");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22011), "青阳使");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(22012), "云中君");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23001), "祭剑魂");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23002), "猎魂引");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23003), "无崖子");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23004), "墨衣行");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23005), "夜溪灵");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23006), "幽梦影");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23007), "南冠客");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(23008), "镜花影");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24001), "沧浪君");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24002), "龙渊客");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24003), "忘忧子");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24004), "骊珠儿");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24005), "木兰行");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24006), "莫解语");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24007), "游无极");
        Sepcies_MixDeal.localNameMap.put(Integer.valueOf(24008), "临九渊");
    }
}
