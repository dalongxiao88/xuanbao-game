package org.come.bean;

import org.come.entity.RewardHall;
import java.util.List;

public class RewardListBean
{
    private List<RewardHall> rewardHalls;
    
    public List<RewardHall> getRewardHalls() {
        return this.rewardHalls;
    }
    
    public void setRewardHalls(List<RewardHall> rewardHalls) {
        this.rewardHalls = rewardHalls;
    }
}
