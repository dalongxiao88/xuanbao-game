package org.come.until;

import java.util.Calendar;

public class MyDayTGDZ
{
    private static int year_ganZhi;
    private static int month_ganZhi;
    private static int day_ganZhi;
    private static int[] lunar_info;
    private static String[] gan_info;
    private static String[] zhi_info;
    private static volatile MyDayTGDZ instance;
    
    public static void main(String[] args) {
        System.out.println(getGanZhi());
    }
    
    private MyDayTGDZ() {
    }
    
    public static MyDayTGDZ getInstance() {
        if (MyDayTGDZ.instance == null) {
            synchronized (MyDayTGDZ.class) {
                if (MyDayTGDZ.instance == null) {
                    MyDayTGDZ.instance = new MyDayTGDZ();
                }
            }
        }
        return MyDayTGDZ.instance;
    }
    
    private int daysOfYear(int year) {
        int sum = 348;
        for (int i = 32768; i > 8; i >>= 1) {
            sum += (((MyDayTGDZ.lunar_info[year - 1900] & i) == 0x0) ? 0 : 1);
        }
        int daysOfLeapMonth;
        if ((MyDayTGDZ.lunar_info[year - 1900] & 0xF) != 0x0) {
            daysOfLeapMonth = (((MyDayTGDZ.lunar_info[year - 1900] & 0x10000) == 0x0) ? 29 : 30);
        }
        else {
            daysOfLeapMonth = 0;
        }
        return sum + daysOfLeapMonth;
    }
    
    public void initGanZhi(int year, int month, int day) {
        Calendar calendar_now = Calendar.getInstance();
        calendar_now.set(year, month - 1, day);
        long date_now = calendar_now.getTime().getTime();
        Calendar calendar_ago = Calendar.getInstance();
        calendar_ago.set(1900, 0, 31);
        long date_ago = calendar_ago.getTime().getTime();
        long days_distance = (date_now - date_ago) / 86400000L;
        float remainder = (float)((date_now - date_ago) % 86400000L);
        if (remainder > 0.0f) {
            ++days_distance;
        }
        MyDayTGDZ.day_ganZhi = (int)days_distance + 40;
        MyDayTGDZ.month_ganZhi = 14;
        int daysOfYear;
        int i;
        for (daysOfYear = 0, i = 1900; i < 2050 && days_distance > 0L; days_distance -= (long)daysOfYear, MyDayTGDZ.month_ganZhi += 12, ++i) {
            daysOfYear = this.daysOfYear(i);
        }
        if (days_distance < 0L) {
            days_distance += (long)daysOfYear;
            --i;
            MyDayTGDZ.month_ganZhi -= 12;
        }
        int myYear = i;
        MyDayTGDZ.year_ganZhi = myYear - 1864;
        int leap = MyDayTGDZ.lunar_info[myYear - 1900] & 0xF;
        boolean isLeap = false;
        int daysOfLeapMonth = 0;
        for (i = 1; i < 13 && days_distance > 0L; ++i) {
            if (leap > 0 && i == leap + 1 && !isLeap) {
                isLeap = true;
                if ((MyDayTGDZ.lunar_info[myYear - 1900] & 0xF) != 0x0) {
                    daysOfLeapMonth = (((MyDayTGDZ.lunar_info[myYear - 1900] & 0x10000) == 0x0) ? 29 : 30);
                }
                else {
                    daysOfLeapMonth = 0;
                }
                --i;
            }
            else {
                daysOfLeapMonth = (((MyDayTGDZ.lunar_info[myYear - 1900] & 65536 >> i) == 0x0) ? 29 : 30);
            }
            if (isLeap && i == leap + 1) {
                isLeap = false;
            }
            days_distance -= (long)daysOfLeapMonth;
            if (!isLeap) {
                ++MyDayTGDZ.month_ganZhi;
            }
        }
        if (days_distance == 0L && leap > 0 && i == leap + 1 && !isLeap) {
            --MyDayTGDZ.month_ganZhi;
        }
        if (days_distance < 0L) {
            --MyDayTGDZ.month_ganZhi;
        }
    }
    
    private static String ganZhi(int index) {
        return MyDayTGDZ.gan_info[index % 10] + MyDayTGDZ.zhi_info[index % 12];
    }
    
    public static String getGanZhi() {
        return "农历" + ganZhi(MyDayTGDZ.year_ganZhi) + "年 " + ganZhi(MyDayTGDZ.month_ganZhi) + "月 " + ganZhi(MyDayTGDZ.day_ganZhi) + "日";
    }
    
    static {
        MyDayTGDZ.lunar_info = new int[] { 19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448 };
        MyDayTGDZ.gan_info = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
        MyDayTGDZ.zhi_info = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
        MyDayTGDZ.instance = null;
    }
}
