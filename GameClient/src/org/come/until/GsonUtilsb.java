package org.come.until;

import com.google.gson.Gson;

public class GsonUtilsb
{
    private static GsonUtilsb gsonUtilsb;
    private Gson gson;
    
    private GsonUtilsb() {
    }
    
    public static GsonUtilsb getGsonUtil() {
        if (GsonUtilsb.gsonUtilsb == null) {
            GsonUtilsb.gsonUtilsb = new GsonUtilsb();
        }
        return GsonUtilsb.gsonUtilsb;
    }
    
    public Gson getgson() {
        if (this.gson == null) {
            this.gson = new Gson();
        }
        return this.gson;
    }
}
