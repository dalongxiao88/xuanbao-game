package org.come.until;

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
        return new BigDecimal(xs).setScale(2, 4).doubleValue();
    }
    
    public static void main(String[] args) {
        long qm = 20000000L;
        double xs = 0.0;
        double value = 55.0;
        do {
            xs += 0.01;
        } while (XS(qm, xs) < value);
        System.out.println(xs + ":" + XS(qm, xs));
        System.out.println(xs + ":" + XS(qm, 1.3));
    }
}
