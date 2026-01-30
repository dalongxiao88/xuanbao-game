package org.come.until;

import org.come.entity.Goodstable;

public class Goodtype
{
    public static boolean CancelMsg(long type) {
        return type == 100L || type == 8L || type == 49L || type == 88L || type == 99L || type == 111L || type == 113L || type == 212L || type == 213L || type == 501L || type == 502L || type == 503L || type == 716L || type == 717L || type == 718L || type == 719L || type == 720L || type == 721L || type == 7005L || type == 118L || type == 190L || type == 1003L || type == 112L;
    }
    
    public static boolean xianlihe(long type) {
        return type == 7005L;
    }

    public static boolean baoshi(long type) {
        return type == 746L || type == 749L || type == 752L || type == 755L || type == 758L || type == 761L || type == 764L || type == 767L;
    }
    
    public static boolean BS(long type) {
        return baoshi(type) || type == 770L || type == 744L;
    }
    
    public static boolean baoshi(int leixing, long type) {
        if (leixing == 0) {
            return type == 746L || type == 755L || type == 767L;
        }
        if (leixing == 1) {
            return type == 749L || type == 761L;
        }
        if (leixing == 2) {
            return type == 749L || type == 764L;
        }
        if (leixing == 3) {
            return type == 761L || type == 764L;
        }
        return leixing == 5 && (type == 752L || type == 758L);
    }
    
    public static int GemPath(int leixing, long type) {
        if (leixing == 0) {
            if (type == 746L) {
                return 1;
            }
            if (type == 767L) {
                return 2;
            }
            if (type == 755L) {
                return 3;
            }
        }
        else if (leixing == 1) {
            if (type == 761L) {
                return 1;
            }
            if (type == 749L) {
                return 2;
            }
        }
        else if (leixing == 2) {
            if (type == 764L) {
                return 1;
            }
            if (type == 749L) {
                return 2;
            }
        }
        else if (leixing == 3) {
            if (type == 761L) {
                return 1;
            }
            if (type == 764L) {
                return 2;
            }
        }
        else if (leixing == 5) {
            if (type == 758L) {
                return 1;
            }
            if (type == 752L) {
                return 2;
            }
        }
        return 0;
    }
    
    public static boolean EquipGem(long type) {
        return Weapons(type) || Helmet(type) || Necklace(type) || Clothes(type) || Shoes(type);
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
        return type == 0L || type == 1L;
    }
    
    public static boolean Accessories(long type) {
        return Mask(type) || Belt(type) || Cloak(type) || Pendant(type) || Ring(type) || Amulet(type);
    }
    
    public static boolean GodEquipment_God(long type) {
        return type == 6500L || type == 6900L || type == 6601L || type == 6600L || type == 6701L || type == 6700L || type == 6800L;
    }
    
    public static boolean GodEquipment_Ding(long type) {
        return type >= 8868L && type <= 8872L;
    }
    
    public static boolean GodEquipment_xian(long type) {
        return type >= 7000L && type <= 7004L;
    }
    
    public static boolean Ore(long type) {
        return type == 500L;
    }
    
    public static int OreLvl(Goodstable goodstable) {
        return Integer.parseInt(goodstable.getValue().split("=")[1]);
    }
    
    public static boolean Wuzhu(long type) {
        return type >= 810L && type <= 815L;
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
        if (Amulet(type) || Amulet2(type)) {
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
        return -1;
    }
    
    public static boolean Weapons(long type) {
        return type == 800L || type == 6500L || type == 7004L || type == 8868L;
    }
    
    public static boolean Helmet(long type) {
        return type == 601L || type == 600L || type == 6600L || type == 6601L || type == 7001L || type == 8869L;
    }
    
    public static boolean Necklace(long type) {
        return type == 603L || type == 7002L || type == 8870L || type == 6800L;
    }
    
    public static boolean Clothes(long type) {
        return type == 605L || type == 604L || type == 6700L || type == 6701L || type == 7000L || type == 8871L;
    }
    
    public static boolean Amulet(long type) {
        return type == 611L;
    }
    
    public static boolean Amulet2(long type) {
        return type == 612L;
    }
    
    public static boolean Shoes(long type) {
        return type == 602L || type == 6900L || type == 7003L || type == 8872L;
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
    
    public static boolean upStarWing(long type) {
        return type == 8889L;
    }
    
    public static boolean isSummonGoods(long type) {
        return type == 498L || type == 497L || type == 513L || type == 514L || type == 515L;
    }
    
    public static boolean isSummonEquip(long type) {
        return type == 510L || type == 511L || type == 512L;
    }
    
    public static boolean isPalGoods(long type) {
        return type >= 7500L && type <= 7550L;
    }
    
    public static boolean isPalEquip(long type) {
        return type == 7503L || type == 7504L || type == 7505L || type == 7506L || type == 7507L || type == 7508L || type == 7509L;
    }
    
    public static boolean isGemRefining(long type) {
        return type == 123L || type == 124L || type == 125L || type == 126L || type == 127L;
    }
    
    public static int isGemRefiningType(long type) {
        if (type == 123L) {
            return 0;
        }
        if (type == 124L) {
            return 2;
        }
        if (type == 125L) {
            return 5;
        }
        if (type == 126L) {
            return 1;
        }
        if (type == 127L) {
            return 3;
        }
        return -1;
    }
    
    public static boolean isGemTapanning(long type) {
        return type == 120L;
    }
}
