package org.come.tool;

public class Goodtype
{
    public static boolean CancelMsg(long type) {
        return type == 100L || type == 8L || type == 49L || type == 88L || type == 99L || type == 111L || type == 113L || type == 212L || type == 213L || type == 501L || type == 502L || type == 503L || type == 504L || type == 716L || type == 717L || type == 718L || type == 719L || type == 720L || type == 721L || type == 7005L || type == 118L || type == 190L || type == 112L || type == 1181L;
    }
    
    public static boolean Amulet2(long type) {
        return type == 612L;
    }
    
    public static boolean xianlihe(long type) {
        return type == 7005L;
    }
    
    public static boolean baoshi(long type) {
        return type == 746L || type == 749L || type == 752L || type == 755L || type == 758L || type == 761L || type == 764L || type == 767L;
    }
    
    public static boolean QHbaoshi(long type) {
        return type >= 123L && type <= 127L;
    }
    
    public static boolean EquipGem(long type) {
        return Weapons(type) || Helmet(type) || Necklace(type) || Clothes(type) || Shoes(type);
    }
    
    public static boolean QHEquipGem(long type1, long type2) {
        return (type2 == 123L && Weapons(type1)) || (type2 == 124L && Necklace(type1)) || (type2 == 125L && Shoes(type1)) || (type2 == 126L && Helmet(type1)) || (type2 == 127L && Clothes(type1));
    }
    
    public static boolean TimingGood(long type) {
        return type == 493L || type == 492L;
    }
    
    public static boolean Medicine(long type) {
        return type == 921L || type == 922L;
    }
    
    public static boolean BlueBack(long type) {
        return type == 494L || type == 495L || type == 496L;
    }
    
    public static boolean Yq(long type) {
        return type == 115L;
    }
    
    public static boolean Fh(long type) {
        return type == 116L;
    }
    
    public static boolean Flightchess(long type) {
        return type == 2010L || type == 2011L || type == 2012L;
    }
    
    public static String[] StringParsing(String vlaue) {
        return vlaue.split("\\|");
    }
    
    public static boolean Consumption(long type) {
        return type == 888L || type == 100L || type == 715L || type == 2041L || type == 2040L || type == 2042L || type == 935L || type == 936L || type == 951L;
    }
    
    public static boolean ExerciseMonsterOre(long type) {
        return (type >= 702L && type <= 711L) || type == 722L || type == 723L;
    }
    
    public static boolean FightingMedicine(long type) {
        return type == 0L;
    }
    
    public static boolean Accessories(long type) {
        return Mask(type) || Belt(type) || Cloak(type) || Pendant(type) || Ring(type) || Amulet(type);
    }
    
    public static boolean GodEquipment_God(long type) {
        return type == 6500L || type == 6900L || type == 6601L || type == 6600L || type == 6701L || type == 6700L || type == 6800L;
    }
    
    public static boolean GodEquipment_xian(long type) {
        return type >= 7000L && type <= 7004L;
    }
    
    public static boolean GodEquipment_Ding(long type) {
        return type >= 8868L && type <= 8872L;
    }
    
    public static boolean Ore(long type) {
        return type == 500L;
    }
    
    public static boolean OrdinaryEquipment(long type) {
        return type == 800L || (type >= 600L && type <= 605L);
    }
    
    public static int EquipmentType(long type) {
        if (Weapons(type)) {
            return 0;
        }
        if (Helmet(type)) {
            return 1;
        }
        if (Necklace(type)) {
            return 2;
        }
        if (Clothes(type)) {
            return 3;
        }
        if (Amulet(type)) {
            return 4;
        }
        if (Shoes(type)) {
            return 5;
        }
        if (Mask(type)) {
            return 6;
        }
        if (Belt(type)) {
            return 7;
        }
        if (Cloak(type)) {
            return 8;
        }
        if (Pendant(type)) {
            return 9;
        }
        if (Ring(type)) {
            return 10;
        }
        if (type == 8888L) {
            return 12;
        }
        if (type == 520L) {
            return 13;
        }
        if (type == 9999L) {
            return 14;
        }
        return -1;
    }
    
    public static boolean Weapons(long type) {
        return type == 800L || type == 6500L || type == 7004L;
    }
    
    public static boolean Helmet(long type) {
        return type == 601L || type == 600L || type == 6600L || type == 6601L || type == 600L || type == 7001L;
    }
    
    public static boolean Xqitouk(long type) {
        return type == 7001L;
    }
    
    public static boolean xqiwuqi(long type) {
        return type == 7004L;
    }
    
    public static boolean xqixianglian(long type) {
        return type == 7002L;
    }
    
    public static boolean xqiwyf(long type) {
        return type == 7000L;
    }
    
    public static boolean xqixiezi(long type) {
        return type == 7003L;
    }
    
    public static boolean Necklace(long type) {
        return type == 603L || type == 7002L || type == 6800L;
    }
    
    public static boolean Clothes(long type) {
        return type == 605L || type == 604L || type == 6700L || type == 6701L || type == 7000L;
    }
    
    public static boolean Amulet(long type) {
        return type == 612L || type == 611L;
    }
    
    public static boolean Shoes(long type) {
        return type == 602L || type == 6900L || type == 7003L;
    }
    
    public static boolean Mask(long type) {
        return type == 609L || type == 927L;
    }
    
    public static boolean Ring(long type) {
        return type == 606L || type == 928L;
    }
    
    public static boolean Belt(long type) {
        return type == 608L || type == 929L;
    }
    
    public static boolean Cloak(long type) {
        return type == 610L || type == 930L;
    }
    
    public static boolean Pendant(long type) {
        return type == 607L || type == 931L;
    }
    
    public static boolean isSummonGoods(long type) {
        return type == 498L || type == 497L || type == 513L || type == 514L || type == 515L;
    }
    
    public static boolean isSummonEquip(long type) {
        return type == 510L || type == 511L || type == 512L;
    }
    
    public static boolean isPalEquip(long type) {
        return type >= 7503L && type <= 7509L;
    }
}
