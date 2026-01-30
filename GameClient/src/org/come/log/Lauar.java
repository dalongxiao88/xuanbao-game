package org.come.log;

import org.apache.commons.logging.LogFactory;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;

public final class Lauar
{
    private static Log log;
    private static int monCyl;
    private static int dayCyl;
    private static int yearCyl;
    private static int year;
    private static int month;
    private static int day;
    private static boolean isLeap;
    private static int[] lunarInfo;
    private static int[] solarMonth;
    private static String[] Gan;
    private static String[] Zhi;
    private static String[] Animals;
    private static int[] sTermInfo;
    private static String[] nStr1;
    private static String[] nStr2;
    private static String[] monthNong;
    private static String[] yearName;
    
    private static int lYearDays(int y) {
        int sum = 348;
        for (int i = 32768; i > 8; i >>= 1) {
            sum += (((Lauar.lunarInfo[y - 1900] & i) != 0x0) ? 1 : 0);
        }
        return sum + leapDays(y);
    }
    
    private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            return ((Lauar.lunarInfo[y - 1900] & 0x10000) == 0x0) ? 29 : 30;
        }
        return 0;
    }
    
    private static int leapMonth(int y) {
        return Lauar.lunarInfo[y - 1900] & 0xF;
    }
    
    private static int monthDays(int y, int m) {
        return ((Lauar.lunarInfo[y - 1900] & 65536 >> m) == 0x0) ? 29 : 30;
    }
    
    private static void Lunar1(Date objDate) {
        int leap = 0;
        int temp = 0;
        Calendar cl = Calendar.getInstance();
        cl.set(1900, 0, 31);
        Date baseDate = cl.getTime();
        int offset = (int)((objDate.getTime() - baseDate.getTime()) / 86400000L);
        Lauar.dayCyl = offset + 40;
        Lauar.monCyl = 14;
        int i;
        for (i = 1900; i < 2050 && offset > 0; offset -= temp, Lauar.monCyl += 12, ++i) {
            temp = lYearDays(i);
        }
        if (offset < 0) {
            offset += temp;
            --i;
            Lauar.monCyl -= 12;
        }
        Lauar.year = i;
        Lauar.yearCyl = i - 1864;
        leap = leapMonth(i);
        Lauar.isLeap = false;
        for (i = 1; i < 13 && offset > 0; ++i) {
            if (leap > 0 && i == leap + 1 && !Lauar.isLeap) {
                --i;
                Lauar.isLeap = true;
                temp = leapDays(Lauar.year);
            }
            else {
                temp = monthDays(Lauar.year, i);
            }
            if (Lauar.isLeap && i == leap + 1) {
                Lauar.isLeap = false;
            }
            offset -= temp;
            if (!Lauar.isLeap) {
                ++Lauar.monCyl;
            }
        }
        if (offset == 0 && leap > 0 && i == leap + 1) {
            if (Lauar.isLeap) {
                Lauar.isLeap = false;
            }
            else {
                Lauar.isLeap = true;
                --i;
                --Lauar.monCyl;
            }
        }
        if (offset < 0) {
            offset += temp;
            --i;
            --Lauar.monCyl;
        }
        Lauar.month = i;
        Lauar.day = offset + 1;
    }
    
    private static int getYear() {
        return Lauar.year;
    }
    
    private static int getMonth() {
        return Lauar.month;
    }
    
    private static int getDay() {
        return Lauar.day;
    }
    
    private static int getMonCyl() {
        return Lauar.monCyl;
    }
    
    private static int getYearCyl() {
        return Lauar.yearCyl;
    }
    
    private static int getDayCyl() {
        return Lauar.dayCyl;
    }
    
    private static boolean getIsLeap() {
        return Lauar.isLeap;
    }
    
    private static String cyclical(int num) {
        return Lauar.Gan[num % 10] + Lauar.Zhi[num % 12];
    }
    
    private static String cDay(int d) {
        String s = null;
        switch (d) {
            case 10: {
                s = "初十";
                break;
            }
            case 20: {
                s = "二十";
                break;
            }
            case 30: {
                s = "三十";
                break;
            }
            default: {
                s = Lauar.nStr2[d / 10];
                s += Lauar.nStr1[d % 10];
                break;
            }
        }
        return s;
    }
    
    private static String cYear(int y) {
        String s;
        int d;
        for (s = " "; y > 0; y = (y - d) / 10, s = Lauar.yearName[d] + s) {
            d = y % 10;
        }
        return s;
    }
    
    public static String getLunar(String year, String month, String day) {
        int SY = Integer.parseInt(year);
        int SM = Integer.parseInt(month);
        int SD = Integer.parseInt(day);
        int sy = (SY - 4) % 12;
        Lauar.log.debug("SY=" + SY + "SM=" + SM + "SD=" + SD + "sy=" + sy);
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        Date sDObj = cl.getTime();
        Lunar1(sDObj);
        String s = " ";
        s = s + cyclical(getYearCyl()) + "虎年   ";
        s = s + (getIsLeap() ? "闰" : "") + Lauar.monthNong[getMonth()] + "月" + ((monthDays(getYear(), getMonth()) == 29) ? "小" : "大");
        s = s + cDay(getDay()) + " ";
        return s;
    }
    
    public static void main(String[] args) {
    }
    
    static {
        Lauar.log = LogFactory.getLog(Lauar.class);
        Lauar.lunarInfo = new int[] { 19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448 };
        Lauar.solarMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        Lauar.Gan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
        Lauar.Zhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
        Lauar.Animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
        Lauar.sTermInfo = new int[] { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758 };
        Lauar.nStr1 = new String[] { "日", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
        Lauar.nStr2 = new String[] { "初", "十", "廿", "卅", "\u3000" };
        Lauar.monthNong = new String[] { "正", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
        Lauar.yearName = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    }
}
