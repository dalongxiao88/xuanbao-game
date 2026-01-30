package org.cbg.until;

public class TreasurePavilionSearchUntil
{
    public static String getEquipmentType(String EquipmentName) {
        String type = null;
        int n = -1;
        switch (EquipmentName.hashCode()) {
            case 683136: {
                if (EquipmentName.equals("全部")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 753559662: {
                if (EquipmentName.equals("常规装备")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 984023: {
                if (EquipmentName.equals("神兵")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 647855: {
                if (EquipmentName.equals("仙器")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1192387: {
                if (EquipmentName.equals("配饰")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 25430911: {
                if (EquipmentName.equals("护身符")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 666656: {
                if (EquipmentName.equals("其他")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                type = "0";
                break;
            }
            case 1: {
                type = "11";
                break;
            }
            case 2: {
                type = "12";
                break;
            }
            case 3: {
                type = "13";
                break;
            }
            case 4: {
                type = "14";
                break;
            }
            case 5: {
                type = "15";
                break;
            }
            case 6: {
                type = "6";
                break;
            }
        }
        return type;
    }
    
    public static String getPropType(String propName) {
        String type = null;
        int n = -1;
        switch (propName.hashCode()) {
            case 683136: {
                if (propName.equals("全部")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case -963431065: {
                if (propName.equals("召唤兽装备")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 693667221: {
                if (propName.equals("地煞星符")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 757750: {
                if (propName.equals("宝石")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case -963299882: {
                if (propName.equals("召唤兽饰品")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 720711721: {
                if (propName.equals("孩子装备")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 1008013: {
                if (propName.equals("符石")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 666656: {
                if (propName.equals("其他")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                type = "0";
                break;
            }
            case 1: {
                type = "1";
                break;
            }
            case 2: {
                type = "2";
                break;
            }
            case 3: {
                type = "3";
                break;
            }
            case 4: {
                type = "4";
                break;
            }
            case 5: {
                type = "5";
                break;
            }
            case 6: {
                type = "17";
                break;
            }
            case 7: {
                type = "6";
                break;
            }
        }
        return type;
    }
    
    public static String getGuardianForceType(String guardianForceName) {
        String type = null;
        int n = -1;
        switch (guardianForceName.hashCode()) {
            case 657503984: {
                if (guardianForceName.equals("全部类型")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 46673041: {
                if (guardianForceName.equals("1-14称")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 2135468020: {
                if (guardianForceName.equals("特殊召唤兽")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1212825387: {
                if (guardianForceName.equals("高级守护")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 984031: {
                if (guardianForceName.equals("神兽")) {
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
                type = "0";
                break;
            }
            case 1: {
                type = "7";
                break;
            }
            case 2: {
                type = "8";
                break;
            }
            case 3: {
                type = "9";
                break;
            }
            case 4: {
                type = "10";
                break;
            }
        }
        return type;
    }
    
    public static String getLingbaoType(String lingbaoName) {
        String type = null;
        int n = -1;
        switch (lingbaoName.hashCode()) {
            case 915912: {
                if (lingbaoName.equals("灵宝")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1008013: {
                if (lingbaoName.equals("符石")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                type = "16";
                break;
            }
            case 1: {
                type = "17";
                break;
            }
        }
        return type;
    }
    
    public static Integer GetPublicNoticePeriod(String publicNoticePeriodName) {
        Integer type = null;
        int n = -1;
        switch (publicNoticePeriodName.hashCode()) {
            case 683136: {
                if (publicNoticePeriodName.equals("全部")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 36184439: {
                if (publicNoticePeriodName.equals("道具类")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 661654358: {
                if (publicNoticePeriodName.equals("召唤兽类")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 34385881: {
                if (publicNoticePeriodName.equals("装备类")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 28425139: {
                if (publicNoticePeriodName.equals("灵宝类")) {
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
                type = null;
                break;
            }
            case 1: {
                type = Integer.valueOf(3);
                break;
            }
            case 2: {
                type = Integer.valueOf(4);
                break;
            }
            case 3: {
                type = Integer.valueOf(5);
                break;
            }
            case 4: {
                type = Integer.valueOf(6);
                break;
            }
        }
        return type;
    }
}
