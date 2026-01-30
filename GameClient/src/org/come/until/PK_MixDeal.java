package org.come.until;

public class PK_MixDeal
{
    public static boolean isPK(int type) {
        return (type >= 5 && type <= 14) || type == 21 || (type >= 31 && type <= 33) || type == 101 || type == 102;
    }
    
    public static boolean isBB(int type) {
        return type == 34;
    }
    
    public static boolean isArena(int type) {
        return type == 101;
    }
    
    public static boolean isPal(int type) {
        return !isBB(type) && type != 4 && type != 101 && type != 102 && type != 31;
    }
}
