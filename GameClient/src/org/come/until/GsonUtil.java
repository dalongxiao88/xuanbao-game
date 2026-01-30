package org.come.until;

import com.google.gson.Gson;

public class GsonUtil
{
    private static GsonUtil gsonUtil;
    private Gson gson;
    
    private GsonUtil() {
    }
    
    public static GsonUtil getGsonUtil() {
        if (GsonUtil.gsonUtil == null) {
            GsonUtil.gsonUtil = new GsonUtil();
        }
        return GsonUtil.gsonUtil;
    }
    
    public Gson getgson() {
        if (this.gson == null) {
            this.gson = new Gson();
        }
        return this.gson;
    }
}
