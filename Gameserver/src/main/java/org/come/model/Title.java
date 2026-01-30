package org.come.model;

import come.tool.Calculation.BaseQl;

public class Title
{
    private Integer id;
    private String titlename;
    private String exist;
    private String text;
    private String value;
    private String skin;
    private int limitTime;
    private String color;
    private transient BaseQl[] qls;
    
    public BaseQl[] getQls() {
        if (this.qls == null && this.value != null && !this.value.equals("")) {
            String[] vs = this.value.split("\\|");
            this.qls = new BaseQl[vs.length];
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                this.qls[i] = new BaseQl(vss[0], Double.parseDouble(vss[1]));
            }
        }
        return this.qls;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitlename() {
        return this.titlename;
    }
    
    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }
    
    public String getExist() {
        return this.exist;
    }
    
    public void setExist(String exist) {
        this.exist = exist;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public int getLimitTime() {
        return this.limitTime;
    }
    
    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}
