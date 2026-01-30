package com.updateNew;

public class MyIsif
{
    public static String ifs;
    public static Boolean dun;
    public static boolean debug;
    public static boolean isVideo;
    public static String style;
    
    public String getIfs() {
        return MyIsif.ifs;
    }
    
    public void setIfs(String ifs) {
        MyIsif.ifs = ifs;
    }
    
    public static String getStyle() {
        return MyIsif.style;
    }
    
    public static void setStyle(String style) {
        MyIsif.style = style;
    }
    
    static {
        MyIsif.ifs = "DK";
        MyIsif.dun = Boolean.valueOf(false);
        MyIsif.debug = false;
        MyIsif.isVideo = true;
    }
}
