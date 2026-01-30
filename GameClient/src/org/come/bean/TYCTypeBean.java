package org.come.bean;

import java.util.ArrayList;
import org.come.until.JmSum;
import java.util.List;

public class TYCTypeBean
{
    private Integer oneTotalPoint;
    private Integer qZID;
    private Integer qZPoint;
    private Integer lvl;
    private List<Integer> totalPoints;
    private List<Integer> exclusiveSkills;
    
    public Integer getOneTotalPoint() {
        if (this.oneTotalPoint == null) {
            return this.oneTotalPoint;
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.oneTotalPoint));
    }
    
    public void setOneTotalPoint(Integer oneTotalPoint) {
        if (oneTotalPoint == null) {
            this.oneTotalPoint = oneTotalPoint;
        }
        else {
            this.oneTotalPoint = Integer.valueOf((int)JmSum.ZM((long)(int)oneTotalPoint));
        }
    }
    
    public Integer getQZID() {
        if (this.qZID == null) {
            return this.qZID;
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.qZID));
    }
    
    public void setQZID(Integer qZID) {
        if (qZID == null) {
            this.qZID = qZID;
        }
        else {
            this.qZID = Integer.valueOf((int)JmSum.ZM((long)(int)qZID));
        }
    }
    
    public Integer getQZPoint() {
        if (this.qZPoint == null) {
            return this.qZPoint;
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.qZPoint));
    }
    
    public void setQZPoint(Integer qZPoint) {
        if (qZPoint == null) {
            this.qZPoint = qZPoint;
        }
        else {
            this.qZPoint = Integer.valueOf((int)JmSum.ZM((long)(int)qZPoint));
        }
    }
    
    public Integer getLvl() {
        if (this.lvl == null) {
            return this.lvl;
        }
        return Integer.valueOf((int)JmSum.MZ((long)(int)this.lvl));
    }
    
    public void setLvl(Integer lvl) {
        if (lvl == null) {
            this.lvl = lvl;
        }
        else {
            this.lvl = Integer.valueOf((int)JmSum.ZM((long)(int)lvl));
        }
    }
    
    public Integer getTotalPoint(int n) {
        if (this.totalPoints == null) {
            return null;
        }
        if (n < this.totalPoints.size()) {
            return Integer.valueOf((int)JmSum.MZ((long)(int)this.totalPoints.get(n)));
        }
        return null;
    }
    
    public void setTotalPoint(Integer totalPoint) {
        if (totalPoint == null) {
            return;
        }
        if (this.totalPoints == null) {
            this.totalPoints = new ArrayList<>();
        }
        this.totalPoints.add(Integer.valueOf((int)JmSum.ZM((long)(int)totalPoint)));
    }
    
    public void setExclusiveSkill(Integer exclusiveSkill) {
        if (exclusiveSkill == null) {
            return;
        }
        if (this.exclusiveSkills == null) {
            this.exclusiveSkills = new ArrayList<>();
        }
        this.exclusiveSkills.add(Integer.valueOf((int)JmSum.ZM((long)(int)exclusiveSkill)));
    }
    
    public List<Integer> getTotalPoints() {
        return this.totalPoints;
    }
    
    public void setTotalPoints(List<Integer> totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    public List<Integer> getExclusiveSkills() {
        return this.exclusiveSkills;
    }
    
    public void setExclusiveSkills(List<Integer> exclusiveSkills) {
        this.exclusiveSkills = exclusiveSkills;
    }
}
