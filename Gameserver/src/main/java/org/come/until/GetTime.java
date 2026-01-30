package org.come.until;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class GetTime
{
    public static String getNowMinit() {
        TimeZone time = TimeZone.getTimeZone("GMT+8");
        time = TimeZone.getDefault();
        TimeZone.setDefault(time);
        Calendar calendar = Calendar.getInstance();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = calendar.getTime();
        String str = new String();
        str = format1.format(date);
        return str;
    }
}
