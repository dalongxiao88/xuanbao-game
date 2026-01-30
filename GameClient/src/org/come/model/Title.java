package org.come.model;

import java.util.Date;

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
    private Date recordTime;
    
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
    
    public Date getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}
