package org.come.bean;

public class Talent
{
    private int id;
    private String TalentName;
    private int camp;
    private String demand;
    private double fail;
    private double touch;
    private String value;
    private String text;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTalentName() {
        return this.TalentName;
    }
    
    public void setTalentName(String talentName) {
        this.TalentName = talentName;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public String getDemand() {
        return this.demand;
    }
    
    public void setDemand(String demand) {
        this.demand = demand;
    }
    
    public double getFail() {
        return this.fail;
    }
    
    public void setFail(double fail) {
        this.fail = fail;
    }
    
    public double getTouch() {
        return this.touch;
    }
    
    public void setTouch(double touch) {
        this.touch = touch;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
