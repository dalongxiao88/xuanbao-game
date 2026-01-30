package org.come.bean;

import org.come.entity.RewardHall;

public class RewardDrawingBean
{
    private RewardHall rewardHall;
    private String roleName;
    
    public RewardHall getRewardHall() {
        return this.rewardHall;
    }
    
    public void setRewardHall(RewardHall rewardHall) {
        this.rewardHall = rewardHall;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
