package org.come.bean;

import java.util.Map;

public class ApplyQianDao
{
    private int type;
    private int dayri;
    private Map choujianBean;
    private String Qiandaoday;
    private String chpujiangjihe;
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getDayri() {
        return this.dayri;
    }
    
    public void setDayri(int dayri) {
        this.dayri = dayri;
    }
    
    public Map getChoujianBean() {
        return this.choujianBean;
    }
    
    public void setChoujianBean(Map choujianBean) {
        this.choujianBean = choujianBean;
    }
    
    public String getQiandaoday() {
        return this.Qiandaoday;
    }
    
    public void setQiandaoday(String qiandaoday) {
        this.Qiandaoday = qiandaoday;
    }
    
    public String getChpujiangjihe() {
        return this.chpujiangjihe;
    }
    
    public void setChpujiangjihe(String chpujiangjihe) {
        this.chpujiangjihe = chpujiangjihe;
    }
}
