package org.come.bean;

public class AutoActiveBase
{
    private int id;
    private int type;
    private String aName;
    private int tasksetId;
    private int goodNum;
    private int maxNum;
    private String guide;
    private String text;
    private int comNum;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getaName() {
        return this.aName;
    }
    
    public void setaName(String aName) {
        this.aName = aName;
    }
    
    public String getGuide() {
        return this.guide;
    }
    
    public void setGuide(String guide) {
        this.guide = guide;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getTasksetId() {
        return this.tasksetId;
    }
    
    public void setTasksetId(int tasksetId) {
        this.tasksetId = tasksetId;
    }
    
    public int getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }
    
    public int getMaxNum() {
        return this.maxNum;
    }
    
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
    
    public int getComNum() {
        return this.comNum;
    }
    
    public void setComNum(int comNum) {
        this.comNum = comNum;
    }
}
