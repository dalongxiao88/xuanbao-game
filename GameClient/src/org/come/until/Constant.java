package org.come.until;

import java.util.Locale;
import java.time.MonthDay;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Constant
{
    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MILLISECOND_PER_SECONDS = 1000;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int MILLISECOND_PER_DAY = 86400000;
    public static final String PARSE_LOCAL_DATE_EXCEPTION = "Unable to obtain";
    public static final Pattern NUMERIC_REGEX;
    public static final Pattern START_WITH_WORD_REGEX;
    public static final Pattern CHINESE_REGEX;
    public static final LocalTime ONECLOCK;
    public static final LocalTime TWENTYTHREECLOCK;
    public static final String MONTHDAY_FORMAT_PRE = "--";
    public static final MonthDay MONTH_DAY_START;
    public static final MonthDay MONTH_DAY_END;
    public static final MonthDay CAPRICORN_START;
    public static final MonthDay CAPRICORN_END;
    public static final long TIME_NLP_TIMEOUT = 3000L;
    public static final String ZH = "zh";
    public static final String CHUXI = "CHUXI";
    public static final String CHUNJIE = "0101";
    private static volatile Constant constant;
    
    private Constant() {
    }
    
    public static Constant getInstance() {
        if (Constant.constant == null) {
            synchronized (Constant.class) {
                if (Constant.constant == null) {
                    Constant.constant = new Constant();
                }
            }
        }
        return Constant.constant;
    }
    
    public static boolean isChineseLanguage() {
        return Locale.getDefault().getLanguage().equals("zh");
    }
    
    static {
        NUMERIC_REGEX = Pattern.compile("[0-9]+");
        START_WITH_WORD_REGEX = Pattern.compile("^[A-Za-z].*");
        CHINESE_REGEX = Pattern.compile("[一-鿿]");
        ONECLOCK = LocalTime.of(1, 0, 0);
        TWENTYTHREECLOCK = LocalTime.of(23, 0, 0);
        MONTH_DAY_START = MonthDay.parse("--01-01");
        MONTH_DAY_END = MonthDay.parse("--12-31");
        CAPRICORN_START = MonthDay.parse("--12-22");
        CAPRICORN_END = MonthDay.parse("--01-19");
    }
}
