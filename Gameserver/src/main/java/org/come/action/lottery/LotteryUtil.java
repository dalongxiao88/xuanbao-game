package org.come.action.lottery;

import java.util.Random;
import java.util.List;
import org.come.tool.SplitStringTool;
import java.util.HashMap;
import java.util.Map;

public class LotteryUtil
{
    public static int[] getint1() {
        int[] a = { 400107, 400108, 400109, 400110, 400111, 400127, 400121, 400120, 400151 };
        return a;
    }
    
    public static Map<Integer, Integer> getmap1() {
        Map<Integer, Integer> map = new HashMap<>();
        int[] a = getint1();
        for (int i = 0; i < a.length; ++i) {
            if (i < 5) {
                map.put(Integer.valueOf(a[i]), Integer.valueOf(5));
            }
            else if (i == 5) {
                map.put(Integer.valueOf(a[i]), Integer.valueOf(10));
            }
            else if (i == 6) {
                map.put(Integer.valueOf(a[i]), Integer.valueOf(20));
            }
            else if (i == 7) {
                map.put(Integer.valueOf(a[i]), Integer.valueOf(30));
            }
            else if (i == 8) {
                map.put(Integer.valueOf(a[i]), Integer.valueOf(0));
            }
        }
        return map;
    }
    
    public static void main(String[] args) {
        System.out.println(getint2().length);
    }
    
    public static int[] getint2() {
        String msg = "200093-200094|200113|200125|200127|200139|200141|200144|200146|200152|200153|200156|200158|200163-200165|300031-300033|300064|300065|300205|300226|400001|400003|400007|400009|400011|400020|400022|400029|400030|400032|400033|400036|400038|400040|400041|400042|400045|400047-400051|400053|400059|400061|400064|400065|400066|400071-400073|400077|400078|400080|400081|400088-400092|400094|400095|400099|400103|400105-400111|400120|400121|400127|400132|400135|400137|400138|400140-400144|400146|400148|400149|400151|400154|400160|400171";
        List<String> vs = SplitStringTool.splitString(msg);
        int[] a = getint1();
        for (int i = 0; i < a.length; ++i) {
            vs.remove(a[i] + "");
        }
        int size = 50;
        if (size > vs.size()) {
            size = vs.size();
        }
        a = new int[size];
        for (int j = 0; j < size; ++j) {
            a[j] = Integer.parseInt((String)vs.get(j));
        }
        return a;
    }
    
    public static int[] getint3(int[] v) {
        Random random = new Random();
        int[] a = new int[5];
        boolean is = true;
        LOOP:
        while (is) {
            a[0] = v[random.nextInt(v.length)];
            a[1] = v[random.nextInt(v.length)];
            a[2] = v[random.nextInt(v.length)];
            a[3] = v[random.nextInt(v.length)];
            a[4] = v[random.nextInt(v.length)];
            for (int i = 0; i < a.length; ++i) {
                int j = 0;
                while (j < a.length) {
                    if (i != j && a[i] == a[j]) {
                        continue LOOP;
                    }
                    else {
                        ++j;
                    }
                }
            }
            is = false;
        }
        return a;
    }
}
