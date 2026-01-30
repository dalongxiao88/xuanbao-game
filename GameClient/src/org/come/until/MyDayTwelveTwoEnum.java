package org.come.until;

import com.xkzhangsan.time.enums.TwelveTwoEnum;
import java.time.LocalTime;
import java.util.Calendar;

public class MyDayTwelveTwoEnum
{
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(10);
        LocalTime localTime = LocalTime.of(hour, 0, 0);
        System.out.println("当前时辰:" + TwelveTwoEnum.getNameCn(localTime));
    }
}
