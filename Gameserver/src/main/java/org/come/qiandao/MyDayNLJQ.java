package org.come.qiandao;

import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class MyDayNLJQ
{
    private static final long[] lunarInfo;
    private static final int[] year20;
    private static final int[] year19;
    private static final int[] year2000;
    public static final String[] nStr1;
    private static final String[] Gan;
    private static final String[] Zhi;
    private static final String[] Animals;
    static final long[] STermInfo;
    private static final String[] solarTerm;
    private static SimpleDateFormat sdf;
    
    public static String getLunarHoliday(String month, String day) {
        if (month.equals("正")) {
            if (day.equals("初一")) {
                return "春节";
            }
            if (day.equals("十五")) {
                return "元宵节 ";
            }
        }
        if (month.equals("二") && day.equals("初二")) {
            return "龙抬头";
        }
        if (month.equals("五") && day.equals("初五")) {
            return "端午节 ";
        }
        if (month.equals("七")) {
            if (day.equals("初七")) {
                return "情人节 ";
            }
            if (day.equals("十五")) {
                return "中元节 ";
            }
        }
        if (month.equals("八") && day.equals("十五")) {
            return "中秋节";
        }
        if (month.equals("九") && day.equals("初九")) {
            return "重阳节 ";
        }
        if (month.equals("腊")) {
            if (day.equals("初八")) {
                return "腊八节 ";
            }
            if (day.equals("廿三")) {
                return "小年 ";
            }
            if (day.equals("三十")) {
                return "除夕";
            }
        }
        return null;
    }
    
    public static String getHoliday(int month, int day) {
        switch (month) {
            case 1: {
                if (day == 1) {
                    return "元旦节";
                }
                else {
                    break;
                }
            }
            case 2: {
                if (day == 14) {
                    return "情人节";
                }
                else {
                    break;
                }
            }
            case 3: {
                if (day == 8) {
                    return "妇女节";
                }
                if (day == 15) {
                    return "消费者权益日";
                }
                else {
                    break;
                }
            }
            case 4: {
                if (day == 1) {
                    return "愚人节";
                }
                else {
                    break;
                }
            }
            case 5: {
                if (day == 1) {
                    return "劳动节";
                }
                if (day == 4) {
                    return "青年节";
                }
                else {
                    break;
                }
            }
            case 6: {
                if (day == 1) {
                    return "儿童节";
                }
                else {
                    break;
                }
            }
            case 7: {
                if (day == 1) {
                    return "建党节";
                }
                if (day == 7) {
                    return "中国人民抗日战争纪念日";
                }
                else {
                    break;
                }
            }
            case 8: {
                if (day == 1) {
                    return "建军节";
                }
                else {
                    break;
                }
            }
            case 9: {
                if (day == 3) {
                    return "抗战胜利日";
                }
                if (day == 10) {
                    return "教师节";
                }
                else {
                    break;
                }
            }
            case 10: {
                if (day == 1) {
                    return "国庆节";
                }
                else {
                    break;
                }
            }
            case 11: {
                if (day == 11) {
                    return "光棍节";
                }
                else {
                    break;
                }
            }
            case 12: {
                if (day == 24) {
                    return "平安夜";
                }
                if (day == 25) {
                    return "圣诞节";
                }
                else {
                    break;
                }
            }
        }
        return null;
    }
    
    private static final int lYearDays(int y) {
        int sum = 348;
        for (int i = 32768; i > 8; i >>= 1) {
            if ((MyDayNLJQ.lunarInfo[y - 1900] & (long)i) != 0x0L) {
                ++sum;
            }
        }
        return sum + leapDays(y);
    }
    
    private static final int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((MyDayNLJQ.lunarInfo[y - 1900] & 0x10000L) != 0x0L) {
                return 30;
            }
            return 29;
        }
        else {
            return 0;
        }
    }
    
    private static final int leapMonth(int y) {
        return (int)(MyDayNLJQ.lunarInfo[y - 1900] & 0xFL);
    }
    
    private static final int monthDays(int y, int m) {
        if ((MyDayNLJQ.lunarInfo[y - 1900] & (long)(65536 >> m)) == 0x0L) {
            return 29;
        }
        return 30;
    }
    
    public static final String AnimalsYear(int y) {
        return MyDayNLJQ.Animals[(y - 4) % 12];
    }
    
    private static final String cyclicalm(int num) {
        return MyDayNLJQ.Gan[num % 10] + MyDayNLJQ.Zhi[num % 12];
    }
    
    public static final String cyclical(int y) {
        int num = y - 1900 + 36;
        return cyclicalm(num);
    }
    
    private final long[] Lunar(int y, int m) {
        long[] nongDate = new long[7];
        int i = 0;
        int temp = 0;
        int leap = 0;
        Date baseDate = new GregorianCalendar(3800, 1, 31).getTime();
        Date objDate = new GregorianCalendar(y + 1900, m, 1).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        if (y < 2000) {
            offset += (long)MyDayNLJQ.year19[m - 1];
        }
        if (y > 2000) {
            offset += (long)MyDayNLJQ.year20[m - 1];
        }
        if (y == 2000) {
            offset += (long)MyDayNLJQ.year2000[m - 1];
        }
        nongDate[5] = offset + 40L;
        nongDate[4] = 14L;
        int n;
        for (i = 1900; i < 2050 && offset > 0L; offset -= (long)temp, n = 4, nongDate[n] += 12L, ++i) {
            temp = lYearDays(i);
        }
        if (offset < 0L) {
            offset += (long)temp;
            --i;
            int n2 = 4;
            nongDate[n2] -= 12L;
        }
        nongDate[0] = (long)i;
        nongDate[3] = (long)(i - 1864);
        leap = leapMonth(i);
        nongDate[6] = 0L;
        for (i = 1; i < 13 && offset > 0L; ++i) {
            if (leap > 0 && i == leap + 1 && nongDate[6] == 0L) {
                --i;
                nongDate[6] = 1L;
                temp = leapDays((int)nongDate[0]);
            }
            else {
                temp = monthDays((int)nongDate[0], i);
            }
            if (nongDate[6] == 1L && i == leap + 1) {
                nongDate[6] = 0L;
            }
            offset -= (long)temp;
            if (nongDate[6] == 0L) {
                int n3 = 4;
                ++nongDate[n3];
            }
        }
        if (offset == 0L && leap > 0 && i == leap + 1) {
            if (nongDate[6] == 1L) {
                nongDate[6] = 0L;
            }
            else {
                nongDate[6] = 1L;
                --i;
                int n4 = 4;
                --nongDate[n4];
            }
        }
        if (offset < 0L) {
            offset += (long)temp;
            --i;
            int n5 = 4;
            --nongDate[n5];
        }
        nongDate[1] = (long)i;
        nongDate[2] = offset + 1L;
        return nongDate;
    }
    
    public static final long[] calElement(int y, int m, int d) {
        long[] nongDate = new long[7];
        int i = 0;
        int temp = 0;
        int leap = 0;
        Date baseDate = new GregorianCalendar(1900, 0, 31).getTime();
        Date objDate = new GregorianCalendar(y, m - 1, d).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        nongDate[5] = offset + 40L;
        nongDate[4] = 14L;
        int n;
        for (i = 1900; i < 2050 && offset > 0L; offset -= (long)temp, n = 4, nongDate[n] += 12L, ++i) {
            temp = lYearDays(i);
        }
        if (offset < 0L) {
            offset += (long)temp;
            --i;
            int n2 = 4;
            nongDate[n2] -= 12L;
        }
        nongDate[0] = (long)i;
        nongDate[3] = (long)(i - 1864);
        leap = leapMonth(i);
        nongDate[6] = 0L;
        for (i = 1; i < 13 && offset > 0L; ++i) {
            if (leap > 0 && i == leap + 1 && nongDate[6] == 0L) {
                --i;
                nongDate[6] = 1L;
                temp = leapDays((int)nongDate[0]);
            }
            else {
                temp = monthDays((int)nongDate[0], i);
            }
            if (nongDate[6] == 1L && i == leap + 1) {
                nongDate[6] = 0L;
            }
            offset -= (long)temp;
            if (nongDate[6] == 0L) {
                int n3 = 4;
                ++nongDate[n3];
            }
        }
        if (offset == 0L && leap > 0 && i == leap + 1) {
            if (nongDate[6] == 1L) {
                nongDate[6] = 0L;
            }
            else {
                nongDate[6] = 1L;
                --i;
                int n4 = 4;
                --nongDate[n4];
            }
        }
        if (offset < 0L) {
            offset += (long)temp;
            --i;
            int n5 = 4;
            --nongDate[n5];
        }
        nongDate[1] = (long)i;
        nongDate[2] = offset + 1L;
        return nongDate;
    }
    
    public static final String getChinaDate(int day) {
        String a = "";
        if (day == 10) {
            return "初十";
        }
        if (day == 20) {
            return "二十";
        }
        if (day == 30) {
            return "三十";
        }
        int two = day / 10;
        if (two == 0) {
            a = "初";
        }
        if (two == 1) {
            a = "十";
        }
        if (two == 2) {
            a = "廿";
        }
        if (two == 3) {
            a = "三";
        }
        int one = day % 10;
        switch (one) {
            case 1: {
                a += "一";
                break;
            }
            case 2: {
                a += "二";
                break;
            }
            case 3: {
                a += "三";
                break;
            }
            case 4: {
                a += "四";
                break;
            }
            case 5: {
                a += "五";
                break;
            }
            case 6: {
                a += "六";
                break;
            }
            case 7: {
                a += "七";
                break;
            }
            case 8: {
                a += "八";
                break;
            }
            case 9: {
                a += "九";
                break;
            }
        }
        return a;
    }
    
    public static String today() {
        Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        int year = today.get(1);
        int month = today.get(2) + 1;
        int date = today.get(5);
        long[] l = calElement(year, month, date);
        StringBuffer sToday = new StringBuffer();
        try {
            sToday.append(MyDayNLJQ.sdf.format(today.getTime()));
            sToday.append(" 农历");
            sToday.append(cyclical(year));
            sToday.append('(');
            sToday.append(AnimalsYear(year));
            sToday.append(")年");
            sToday.append(MyDayNLJQ.nStr1[(int)l[1]]);
            sToday.append("月");
            sToday.append(getChinaDate((int)l[2]));
            sToday.append(" " + getSoralTerm(new Date()));
            sToday.append(" " + getHoliday(month, date));
            sToday.append(" " + getLunarHoliday(MyDayNLJQ.nStr1[(int)l[1]], getChinaDate((int)l[2])));
            return sToday.toString();
        }
        finally {
            sToday = null;
        }
    }
    
    public static String getWeekOfDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        int number = calendar.get(7);
        String[] str = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        return str[number];
    }
    
    public static String oneDay(int year, int month, int day) {
        String week = getWeekOfDate(year, month, day);
        long[] l = calElement(year, month, day);
        StringBuffer sToday = new StringBuffer();
        String m = "";
        String d = "";
        try {
            if (month < 10) {
                m = "0" + month;
            }
            else {
                m = month + "";
            }
            if (day < 10) {
                d = "0" + day;
            }
            else {
                d = day + "";
            }
            sToday.append(year + "-" + m + "-" + d);
            sToday.append("," + week);
            sToday.append("," + MyDayNLJQ.nStr1[(int)l[1]]);
            sToday.append("月");
            sToday.append(getChinaDate((int)l[2]));
            sToday.append("," + getSoralTerm(year, month, day));
            sToday.append("," + getLunarHoliday(MyDayNLJQ.nStr1[(int)l[1]], getChinaDate((int)l[2])));
            return sToday.toString();
        }
        finally {
            sToday = null;
        }
    }
    
    public static String getSoralTerm(Date Date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date);
        int y = cal.get(1);
        int m = cal.get(2) + 1;
        int d = cal.get(5);
        return getSoralTerm(y, m, d);
    }
    
    public static String getSoralTerm(int y, int m, int d) {
        String solarTerms = null;
        if (d == sTerm(y, (m - 1) * 2)) {
            solarTerms = MyDayNLJQ.solarTerm[(m - 1) * 2];
        }
        else if (d == sTerm(y, (m - 1) * 2 + 1)) {
            solarTerms = MyDayNLJQ.solarTerm[(m - 1) * 2 + 1];
        }
        return solarTerms;
    }
    
    private static int sTerm(int y, int n) {
        Calendar cal = Calendar.getInstance();
        cal.set(1900, 0, 6, 2, 5, 0);
        long temp = cal.getTime().getTime();
        cal.setTime(new Date((long)(3.15569259747E10 * (double)(y - 1900) + (double)(MyDayNLJQ.STermInfo[n] * 60000L) + (double)temp)));
        return cal.get(5);
    }
    
    public static String[] getMyDaynljq() {
        Calendar now = Calendar.getInstance();
        int year = now.get(1);
        int month = now.get(2) + 1;
        int day = now.get(5);
        now.set(year, month, day);
        now.add(5, 1);
        String sr = oneDay(now.get(1), now.get(2), now.get(5));
        String str1 = "";
        String[] strArray;
        for (String s : strArray = sr.split(",")) {
            if (!s.equals("null")) {
                str1 = str1 + s + ",";
            }
        }
        String[] strArrayData = str1.split(",");
        return strArrayData;
    }
    
    public static void main(String[] args) {
        String[] s;
        for (String ss : s = getMyDaynljq()) {
            System.out.println(ss);
        }
    }
    
    static {
        lunarInfo = new long[] { 19416L, 19168L, 42352L, 21717L, 53856L, 55632L, 91476L, 22176L, 39632L, 21970L, 19168L, 42422L, 42192L, 53840L, 119381L, 46400L, 54944L, 44450L, 38320L, 84343L, 18800L, 42160L, 46261L, 27216L, 27968L, 109396L, 11104L, 38256L, 21234L, 18800L, 25958L, 54432L, 59984L, 28309L, 23248L, 11104L, 100067L, 37600L, 116951L, 51536L, 54432L, 120998L, 46416L, 22176L, 107956L, 9680L, 37584L, 53938L, 43344L, 46423L, 27808L, 46416L, 86869L, 19872L, 42448L, 83315L, 21200L, 43432L, 59728L, 27296L, 44710L, 43856L, 19296L, 43748L, 42352L, 21088L, 62051L, 55632L, 23383L, 22176L, 38608L, 19925L, 19152L, 42192L, 54484L, 53840L, 54616L, 46400L, 46496L, 103846L, 38320L, 18864L, 43380L, 42160L, 45690L, 27216L, 27968L, 44870L, 43872L, 38256L, 19189L, 18800L, 25776L, 29859L, 59984L, 27480L, 21952L, 43872L, 38613L, 37600L, 51552L, 55636L, 54432L, 55888L, 30034L, 22176L, 43959L, 9680L, 37584L, 51893L, 43344L, 46240L, 47780L, 44368L, 21977L, 19360L, 42416L, 86390L, 21168L, 43312L, 31060L, 27296L, 44368L, 23378L, 19296L, 42726L, 42208L, 53856L, 60005L, 54576L, 23200L, 30371L, 38608L, 19415L, 19152L, 42192L, 118966L, 53840L, 54560L, 56645L, 46496L, 22224L, 21938L, 18864L, 42359L, 42160L, 43600L, 111189L, 27936L, 44448L };
        year20 = new int[] { 1, 4, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 };
        year19 = new int[] { 0, 3, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 };
        year2000 = new int[] { 0, 3, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 };
        nStr1 = new String[] { "", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊" };
        Gan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
        Zhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
        Animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
        STermInfo = new long[] { 0L, 21208L, 42467L, 63836L, 85337L, 107014L, 128867L, 150921L, 173149L, 195551L, 218072L, 240693L, 263343L, 285989L, 308563L, 331033L, 353350L, 375494L, 397447L, 419210L, 440795L, 462224L, 483532L, 504758L };
        solarTerm = new String[] { "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至" };
        MyDayNLJQ.sdf = new SimpleDateFormat("yyyy-M-d EEEEE");
    }
}
