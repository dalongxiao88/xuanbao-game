package org.come.tool;

import java.math.RoundingMode;
import java.math.BigDecimal;

public class CustomFunction
{
    public static double XS(long v, double xs) {
        if (v <= 0L) {
            return 0.0;
        }
        while (v / 16L > 0L) {
            v /= 16L;
            xs *= 1.86;
        }
        xs *= 1.0 + 0.86 * ((double)v / 16.0);
        return new BigDecimal(xs).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static void main(String[] args) {
        System.out.println(XS(50000000L, 1.3));
    }
}
