package com.tool.PlayerKill;

import java.util.List;

public class PKMixdeal
{
    public static long YW;
    
    public static int getPKNmae(String role, List<String> team, int ns) {
        int w = 1;
        int i = 0;
        while (i < team.size()) {
            if (((String)team.get(i)).equals(role)) {
                w = i + 1;
                break;
            }
            else {
                ++i;
            }
        }
        return getPKSign(w, team.size(), ns);
    }
    
    public static int getPKSign(int w, int s, int ns) {
        return ns / s + ((ns % s >= w) ? 1 : 0);
    }
    
    public static long getJailTime(int c) {
        long time = 7200000L;
        if (c <= 1) {
            return time;
        }
        if (c > 3) {
            c = 3;
        }
        time *= (long)c;
        return time;
    }
    
    static {
        PKMixdeal.YW = 36000000L;
    }
}
