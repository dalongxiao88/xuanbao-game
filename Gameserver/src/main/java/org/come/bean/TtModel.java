package org.come.bean;

import java.util.Date;

public class TtModel
{
    private Integer startHour;
    private Integer endHour;
    private Integer startMinute;
    private Integer endMinute;
    private Date seasonStartTime;
    private Date seasonEndTime;
    private Integer currentSeason;
    private Integer isOpen;
    
    public Integer getStartHour() {
        return this.startHour;
    }
    
    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }
    
    public Integer getEndHour() {
        return this.endHour;
    }
    
    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }
    
    public Integer getStartMinute() {
        return this.startMinute;
    }
    
    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }
    
    public Integer getEndMinute() {
        return this.endMinute;
    }
    
    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }
    
    public Date getSeasonStartTime() {
        return this.seasonStartTime;
    }
    
    public void setSeasonStartTime(Date seasonStartTime) {
        this.seasonStartTime = seasonStartTime;
    }
    
    public Date getSeasonEndTime() {
        return this.seasonEndTime;
    }
    
    public void setSeasonEndTime(Date seasonEndTime) {
        this.seasonEndTime = seasonEndTime;
    }
    
    public Integer getCurrentSeason() {
        return this.currentSeason;
    }
    
    public void setCurrentSeason(Integer currentSeason) {
        this.currentSeason = currentSeason;
    }
    
    public Integer getIsOpen() {
        return this.isOpen;
    }
    
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }
}
