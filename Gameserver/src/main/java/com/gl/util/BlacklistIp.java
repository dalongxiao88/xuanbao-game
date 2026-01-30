package com.gl.util;

import java.util.List;

public class BlacklistIp
{
    public static List<String> IPList;
    
    public static List<String> getIPList() {
        return BlacklistIp.IPList;
    }
    
    public static void setIPList(List<String> iPList) {
        BlacklistIp.IPList = iPList;
    }
}
