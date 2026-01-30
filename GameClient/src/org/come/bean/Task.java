package org.come.bean;

import java.math.BigDecimal;

public class Task
{
    private BigDecimal task_id;
    private String type;
    private String action;
    private String target;
    private BigDecimal timelimit;
    private Short gradelimit;
    private Short convey;
    private String reward;
    private String taskdescrip;
    private String tasksuccessdescrip;
    
    public BigDecimal getTask_id() {
        return this.task_id;
    }
    
    public void setTask_id(BigDecimal task_id) {
        this.task_id = task_id;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = ((type == null) ? null : type.trim());
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = ((action == null) ? null : action.trim());
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(String target) {
        this.target = ((target == null) ? null : target.trim());
    }
    
    public BigDecimal getTimelimit() {
        return this.timelimit;
    }
    
    public void setTimelimit(BigDecimal timelimit) {
        this.timelimit = timelimit;
    }
    
    public Short getGradelimit() {
        return this.gradelimit;
    }
    
    public void setGradelimit(Short gradelimit) {
        this.gradelimit = gradelimit;
    }
    
    public Short getConvey() {
        return this.convey;
    }
    
    public void setConvey(Short convey) {
        this.convey = convey;
    }
    
    public String getReward() {
        return this.reward;
    }
    
    public void setReward(String reward) {
        this.reward = ((reward == null) ? null : reward.trim());
    }
    
    public String getTaskdescrip() {
        return this.taskdescrip;
    }
    
    public void setTaskdescrip(String taskdescrip) {
        this.taskdescrip = ((taskdescrip == null) ? null : taskdescrip.trim());
    }
    
    public String getTasksuccessdescrip() {
        return this.tasksuccessdescrip;
    }
    
    public void setTasksuccessdescrip(String tasksuccessdescrip) {
        this.tasksuccessdescrip = ((tasksuccessdescrip == null) ? null : tasksuccessdescrip.trim());
    }
}
