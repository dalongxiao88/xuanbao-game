package com.tool.time;

import org.come.until.Util;

public class TimerUtil
{
    public static boolean Timeto(long timing) {
        return Util.getTime() >= timing;
    }
    
    public static long TimeReset(long time) {
        return Util.getTime() + time;
    }
    
    public static int fenzhong(long time) {
        return (int)((time - Util.getTime()) / 60000L);
    }
    
    public static int residueDay(long time) {
        return (int)((time - Util.getTime()) / 60000L / 60L / 24L);
    }
    
    public static int xiaoshi(long time) {
        return (int)((time - Util.getTime()) / 60000L / 60L);
    }
}
