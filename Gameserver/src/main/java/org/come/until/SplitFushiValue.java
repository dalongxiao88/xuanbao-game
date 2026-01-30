package org.come.until;

import java.math.BigDecimal;
import java.util.Random;

public class SplitFushiValue
{
    public static String splitFushiValue(String fushiValue) {
        String newvalue;
        do {
            String[] values = fushiValue.split("\\|");
            newvalue = values[0];
            int[] a = randomArray(1, 3, 1);
            int[] b;
            for (int i : b = randomArray(1, values.length - 1, a[0])) {
                String[] value = values[i].split("=");
                String number = "";
                String[] numbers = value[1].split("-");
                if (value[0].equals("活跃") || value[0].equals("速度") || value[0].equals("负敏")) {
                    int min = Integer.parseInt(numbers[0]);
                    int max = Integer.parseInt(numbers[1]);
                    int[] c = randomArray(min, max, 1);
                    number = c[0] + "";
                }
                else {
                    int min = (int)(Double.parseDouble(numbers[0]) * 10000.0);
                    int max = (int)(Double.parseDouble(numbers[1]) * 10000.0);
                    int[] c = randomArray(min, max, 1);
                    if (c[0] >= 100) {
                        c[0] /= 10;
                        number = (double)c[0] / 10.0 + "";
                    }
                    else {
                        number = (double)c[0] / 100.0 + "";
                    }
                }
                newvalue = newvalue + "|" + value[0] + "=" + number;
            }
        } while ((newvalue.indexOf("速度") != -1 || newvalue.indexOf("负敏") == -1) && (newvalue.indexOf("速度") == -1 || newvalue.indexOf("负敏") != -1));
        return newvalue;
    }
    
    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        int[] source = new int[len];
        for (int i = min; i < min + len; ++i) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int j = 0; j < result.length; ++j) {
            index = Math.abs(rd.nextInt() % len--);
            result[j] = source[index];
            source[index] = source[len];
        }
        return result;
    }
    
    public static BigDecimal fushiid(String lvl, String type) {
        BigDecimal id = new BigDecimal(lvl);
        if (!type.equals("青龙符石")) {
            if (type.equals("朱雀符石")) {
                id = new BigDecimal(5 + Integer.parseInt(lvl));
            }
            else if (type.equals("白虎符石")) {
                id = new BigDecimal(10 + Integer.parseInt(lvl));
            }
            else if (type.equals("玄武符石")) {
                id = new BigDecimal(15 + Integer.parseInt(lvl));
            }
            else if (type.equals("麒麟符石")) {
                id = new BigDecimal(20 + Integer.parseInt(lvl));
            }
        }
        return id;
    }
}
