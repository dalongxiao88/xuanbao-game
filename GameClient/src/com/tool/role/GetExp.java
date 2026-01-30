package com.tool.role;

import org.come.until.UserMessUntil;
import java.math.BigDecimal;

public class GetExp
{
    public static long getRoleExp(int TurnAround, int grade) {
        if (grade > 199) {
            grade = 199;
        }
        long exp = ((BigDecimal)UserMessUntil.getExp().getRolePetExpMap().get(Integer.valueOf(grade))).longValue();
        if (TurnAround >= 3) {
            exp *= 2L;
        }
        if (grade > 100 && exp < 5000000L) {
            exp = new BigDecimal("6181894660").longValue();
        }
        return exp;
    }
    
    public static long getBBExp(int TurnAround, int grade) {
        if (grade > 199) {
            grade = 199;
        }
        long exp = ((BigDecimal)UserMessUntil.getExp().getRolePetExpMap().get(Integer.valueOf(grade))).longValue();
        if (TurnAround >= 3) {
            exp *= 2L;
        }
        return exp;
    }
    
    public static long getBBNeiExp(int TurnAround, int grade) {
        return (long)((double)getBBExp(TurnAround, grade) * 0.7);
    }
    
    public static int getMountExp(int grade) {
        if (grade > 100) {
            grade -= 90;
        }
        int nextexp = (grade + 1) * (grade + 1) * 15;
        return nextexp;
    }
    
    public static int getTSExp(int lvl) {
        int x = 9;
        int dd = 0;
        for (int i = 0; i < lvl; ++i) {
            if (i % 2 == 0) {
                ++dd;
            }
            if (i % 38 == 4) {
                ++dd;
            }
            x += dd;
        }
        return x;
    }
    
    public static int getTSX(int P) {
        int x = 9;
        int dd = 0;
        int i = 0;
        while (true) {
            if (i % 2 == 0) {
                ++dd;
            }
            if (i % 38 == 4) {
                ++dd;
            }
            x += dd;
            if (P >= x) {
                P -= x;
                ++i;
            }
            else {
                break;
            }
        }
        return P;
    }
    
    public static int getTSP(int P) {
        int x = 9;
        int dd = 0;
        int i = 0;
        while (true) {
            if (i % 2 == 0) {
                ++dd;
            }
            if (i % 38 == 4) {
                ++dd;
            }
            x += dd;
            if (P >= x) {
                P -= x;
                ++i;
            }
            else {
                break;
            }
        }
        return i;
    }
    
    public static int getFlyExp(int grade) {
        if (grade > 100) {
            grade -= 90;
        }
        int nextexp = (grade + 1) * (grade + 1) * 15;
        return nextexp;
    }
}
