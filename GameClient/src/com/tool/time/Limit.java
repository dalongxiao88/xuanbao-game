package com.tool.time;

import javax.swing.JLabel;

public class Limit
{
    private String name;
    private String type;
    private String skin;
    private long time;
    private String value;
    private JLabel jLabel;
    private JLabel TXT;
    
    public Limit(String name, String type, String skin, long time, String value) {
        this.name = name;
        this.type = type;
        this.skin = skin;
        this.time = time;
        this.value = value;
    }
    
    public Limit() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public JLabel getjLabel() {
        return this.jLabel;
    }
    
    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }
    
    public JLabel getTXT() {
        return this.TXT;
    }
    
    public void setTXT(JLabel TXT) {
        this.TXT = TXT;
    }
}
