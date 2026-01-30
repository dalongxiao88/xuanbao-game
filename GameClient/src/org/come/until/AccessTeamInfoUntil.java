package org.come.until;

public class AccessTeamInfoUntil
{
    public static String[] getss(String vs) {
        if (vs == null || vs.equals("")) {
            return null;
        }
        char[] v = vs.toCharArray();
        String[] s = new String[v.length];
        for (int i = 0; i < s.length; ++i) {
            s[i] = String.valueOf(v[i]);
        }
        return s;
    }
    
    public static boolean isJail(String msg) {
        try {
            return Integer.parseInt(msg.split("\\|")[1]) != 0;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
