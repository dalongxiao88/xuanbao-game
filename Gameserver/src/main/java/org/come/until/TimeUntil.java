package org.come.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUntil
{
    public static String getPastDate() {
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(t);
    }
    
    public static String getPastDate(long time) {
        Date t = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(t);
    }
}
