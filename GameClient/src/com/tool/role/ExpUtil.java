package com.tool.role;

public class ExpUtil
{
    public static long YEAR;
    public static long DAY;
    
    public static long LFExp(int lvl) {
        return (long)(lvl * lvl * 15 - (lvl - 1) * (lvl - 1) * 15);
    }
    
    public static long LFExp2(int lvl) {
        return (long)(lvl * lvl * 15);
    }
    
    public static String LFExptoString(long exp) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(exp / ExpUtil.YEAR);
        buffer.append("年");
        exp %= ExpUtil.YEAR;
        buffer.append(exp / ExpUtil.DAY);
        buffer.append("天");
        buffer.append(exp %= ExpUtil.DAY);
        buffer.append("时辰");
        return buffer.toString();
    }
    
    static {
        ExpUtil.YEAR = 4380L;
        ExpUtil.DAY = 12L;
    }
}
