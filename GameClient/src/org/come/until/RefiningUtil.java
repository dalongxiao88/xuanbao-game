package org.come.until;

import org.come.entity.Goodstable;

public class RefiningUtil
{
    public static boolean DJ(String type, int i, Goodstable good) {
        int n = -1;
        switch (type.hashCode()) {
            case 881316924: {
                if (type.equals("炼化装备")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 880856585: {
                if (type.equals("炼化仙器")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 881192753: {
                if (type.equals("炼化神兵")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 817448089: {
                if (type.equals("普通打造")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 783469: {
                if (type.equals("巫铸")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                if (i == 0) {
                    return Goodtype.OrdinaryEquipment((long)good.getType());
                }
                if (i == 1) {
                    return (long)good.getType() == 498L;
                }
                if (i == 2) {
                    return (long)good.getType() == 499L;
                }
                return i == 3 && (long)good.getType() == 497L;
            }
            case 1: {
                if (i == 0) {
                    return Goodtype.GodEquipment_xian((long)good.getType()) || Goodtype.GodEquipment_Ding((long)good.getType());
                }
                return i == 1 && (Goodtype.GodEquipment_xian((long)good.getType()) || Goodtype.GodEquipment_Ding((long)good.getType()) || (long)good.getType() == 7005L);
            }
            case 2: {
                if (i == 0) {
                    return Goodtype.GodEquipment_God((long)good.getType());
                }
                return i == 1 && (long)good.getType() == 191L;
            }
            case 3: {
                if (i == 0) {
                    return Goodtype.OrdinaryEquipment((long)good.getType());
                }
                return i == 1 && Goodtype.Ore((long)good.getType());
            }
            case 4: {
                if (i == 0) {
                    return Goodtype.OrdinaryEquipment((long)good.getType());
                }
                if (i == 1) {
                    return Goodtype.Ore((long)good.getType());
                }
                if (i == 2) {
                    return (long)good.getType() == 499L;
                }
                return i == 3 && Goodtype.Wuzhu((long)good.getType());
            }
            default: {
                return false;
            }
        }
    }
    
    public static String detection(Goodstable[] goods, int type) {
        int size = 0;
        int p = -1;
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                ++size;
                p = i;
            }
        }
        if (p + 1 != size) {
            return "?";
        }
        if (type == 1) {
            if (size == 6 && (long)goods[1].getType() == 497L && (long)goods[2].getType() == 499L && (long)goods[3].getType() == 498L && (long)goods[4].getType() == 498L && (long)goods[5].getType() == 498L && (Goodtype.OrdinaryEquipment((long)goods[0].getType()) || Goodtype.Amulet2((long)goods[0].getType()) || Goodtype.isPalEquip((long)goods[0].getType()))) {
                return "炼化装备";
            }
            if (size == 2 && Goodtype.Ore((long)goods[1].getType()) && Goodtype.OrdinaryEquipment((long)goods[0].getType())) {
                return "普通打造";
            }
            if (size == 4 && Goodtype.Ore((long)goods[1].getType()) && Goodtype.OrdinaryEquipment((long)goods[0].getType()) && (long)goods[2].getType() == 499L && Goodtype.Wuzhu((long)goods[3].getType())) {
                return "巫铸";
            }
            if (size == 2 && Goodtype.GodEquipment_xian((long)goods[0].getType()) && (Goodtype.GodEquipment_xian((long)goods[1].getType()) || (long)goods[1].getType() == 7005L || (long)goods[1].getType() == 7010L)) {
                return "炼化仙器";
            }
            if (size == 2 && Goodtype.GodEquipment_Ding((long)goods[0].getType()) && (Goodtype.GodEquipment_Ding((long)goods[1].getType()) || (long)goods[1].getType() == 7005L || (long)goods[1].getType() == 7010L)) {
                return "炼化仙器";
            }
            if (size == 2 && Goodtype.GodEquipment_God((long)goods[0].getType()) && (long)goods[1].getType() == 191L) {
                return "炼化神兵";
            }
            if (size == 2 && Goodtype.isPalEquip((long)goods[0].getType()) && (long)goods[1].getType() == 500L) {
                return isUpOrC(AccessSuitMsgUntil.getPalEquipAgree(goods[0].getValue(), "等级").split("=")[1], goods[1].getValue().split("=")[1]);
            }
        }
        else if (type == 2) {
            if (size == 2 && Goodtype.Accessories((long)goods[0].getType()) && (Goodtype.Accessories((long)goods[1].getType()) || (long)goods[1].getType() == 1008L || (long)goods[1].getType() == 702L || (long)goods[1].getType() == 703L || (long)goods[1].getType() == 704L || (long)goods[1].getType() == 705L || (long)goods[1].getType() == 706L || (long)goods[1].getType() == 707L || (long)goods[1].getType() == 708L || (long)goods[1].getType() == 709L || (long)goods[1].getType() == 710L || (long)goods[1].getType() == 711L || (long)goods[1].getType() == 722L || (long)goods[1].getType() == 723L)) {
                return "一键培养";
            }
            if (size == 2 && Goodtype.Accessories((long)goods[0].getType()) && Goodtype.Ore((long)goods[1].getType())) {
                return "佩饰重铸";
            }
            if (size == 2 && Goodtype.Amulet2((long)goods[0].getType()) && Goodtype.Amulet2((long)goods[1].getType())) {
                return "护身培养";
            }
            if (size == 2 && Goodtype.Amulet2((long)goods[0].getType()) && Goodtype.Ore((long)goods[1].getType()) && Goodtype.OreLvl(goods[1]) == 10) {
                return "护身升级";
            }
            if (size == 2 && Goodtype.Amulet2((long)goods[0].getType()) && Goodtype.Ore((long)goods[1].getType()) && Goodtype.OreLvl(goods[1]) == 9) {
                return "护身重铸";
            }
            if (size == 4 && (long)goods[0].getType() == 8889L && (long)goods[1].getType() == 8889L && (long)goods[2].getType() == 8889L && (long)goods[3].getType() == 8889L) {
                return "秘石合成";
            }
            if (goods[0] != null && size == 3 && Goodtype.Accessories((long)goods[0].getType()) && goods[1].getGoodsname().equals("补天神石") && goods[2].getGoodsname().equals("血玲珑")) {
                return "点粹";
            }
            if (goods[0] != null && size == 2 && Goodtype.Accessories((long)goods[0].getType()) && goods[1].getGoodsname().equals("粹玉")) {
                return "点粹强化";
            }
        }
        return "?";
    }
    
    public static String isUpOrC(String lvlStr, String goodlvlStr) {
        int lvl = Integer.parseInt(lvlStr);
        int goodlvl = Integer.parseInt(goodlvlStr);
        if (lvl <= 5) {
            if (lvl + 5 == goodlvl) {
                return "装备升级";
            }
            if (lvl + 4 == goodlvl) {
                return "装备重铸";
            }
        }
        else if (lvl == 6 && goodlvl == 10) {
            return "装备重铸";
        }
        return "?";
    }
}
