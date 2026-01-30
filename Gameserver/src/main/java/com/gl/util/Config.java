package com.gl.util;

import java.util.HashMap;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Map;

public class Config
{
    private static Map<String, String> CONFIG;
    private static Properties prop;
    private static final String PROPFILE;
    public static final String KEY_LH_SD = "sd_lianhua";
    public static final String KEY_LH_XQ = "xq_lianhua";
    public static final String KEY_LH_SB = "sb_lianhua";
    public static final String KEY_LH_PT = "pt_lianhua";
    public static final String KEY_LQ_XQ = "xq_lianqi";
    public static final String KEY_LQ_SB = "sb_lianqi";
    public static final String KEY_LQ_PT = "pt_lianqi";
    public static final String KEY_TJ_XQ1 = "xq_teji1";
    public static final String KEY_TJ_XQ2 = "xq_teji2";
    public static final String KEY_TJ_SB1 = "sb_teji1";
    public static final String KEY_TJ_SB2 = "sb_teji2";
    public static final String KEY_TJ_PT1 = "pt_teji1";
    public static final String KEY_TJ_PT2 = "pt_teji2";
    public static final String KEY_XK_XZ = "xz_xingka";
    
    public static void main(String[] args) {
        System.out.println(Config.PROPFILE);
        Config.CONFIG.put("sd_lianhua", "1");
        Config.CONFIG.put("xq_lianhua", "4");
        Config.CONFIG.put("sb_lianhua", "4");
        Config.CONFIG.put("pt_lianhua", "4");
        Config.CONFIG.put("xq_teji1", "10");
        Config.CONFIG.put("xq_teji2", "10");
        Config.CONFIG.put("sb_teji1", "10");
        Config.CONFIG.put("sb_teji2", "10");
        Config.CONFIG.put("pt_teji1", "10");
        Config.CONFIG.put("pt_teji2", "10");
        Config.CONFIG.put("xq_lianqi", "2");
        Config.CONFIG.put("sb_lianqi", "2");
        Config.CONFIG.put("pt_lianqi", "5");
        Config.CONFIG.put("xz_xingka", "5");
        write();
    }
    
    public static void reload() {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(Config.PROPFILE));
            Config.prop.load(in);
            for (String key : Config.prop.stringPropertyNames()) {
                Config.CONFIG.put(key, Config.prop.getProperty(key));
            }
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void write() {
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(Config.PROPFILE);
            for (String key : Config.CONFIG.keySet()) {
                Config.prop.setProperty(key, (String)Config.CONFIG.get(key));
            }
            Config.prop.store(oFile, "Property file -- 172747549@qq.com  @lianhua");
            oFile.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public static String getString(String key) {
        if (Config.CONFIG.size() == 0) {
            reload();
        }
        return (String)Config.CONFIG.get(key);
    }
    
    public static int getInt(String key) {
        if (Config.CONFIG.size() == 0) {
            reload();
        }
        return Integer.parseInt((String)Config.CONFIG.get(key));
    }
    
    public static Map<String, String> getValue() {
        if (Config.CONFIG.size() == 0) {
            reload();
        }
        return Config.CONFIG;
    }
    
    public static boolean update(Map<String, String> map) {
        boolean success = false;
        for (String key : map.keySet()) {
            if (Config.CONFIG.containsKey(key) && map.get(key) != null) {
                if (key.equals("pt_lianhua") || key.equals("sb_lianhua") || key.equals("xq_lianhua") || key.equals("pt_lianqi") || key.equals("sb_lianqi") || key.equals("xq_lianqi")) {
                    Config.CONFIG.put(key, Integer.parseInt((String)map.get(key)) / 20 + "");
                }
                else {
                    Config.CONFIG.put(key, map.get(key));
                }
                success = true;
            }
        }
        write();
        return success;
    }
    
    static {
        PROPFILE = Config.class.getResource("/").getPath() + "game.config";
        Config.CONFIG = new HashMap<>();
        Config.prop = new Properties();
    }
}
