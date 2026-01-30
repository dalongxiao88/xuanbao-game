package org.come.readBean;

import java.util.List;
import org.come.model.ActiveBase;
import org.come.model.ActiveAward;

public class AllActive
{
    private ActiveAward[] awards;
    private ActiveBase[] bases;
    
    public AllActive(List<ActiveAward> awardList, List<ActiveBase> baseList) {
        this.awards = new ActiveAward[awardList.size()];
        for (int i = 0; i < this.awards.length; ++i) {
            this.awards[i] = (ActiveAward)awardList.get(i);
        }
        this.bases = new ActiveBase[baseList.size()];
        for (int i = 0; i < this.bases.length; ++i) {
            this.bases[i] = (ActiveBase)baseList.get(i);
        }
    }
    
    public AllActive(List<ActiveBase> baseList) {
        this.bases = new ActiveBase[baseList.size()];
        for (int i = 0; i < this.bases.length; ++i) {
            this.bases[i] = (ActiveBase)baseList.get(i);
        }
    }
    
    public ActiveAward[] getAwards() {
        return this.awards;
    }
    
    public void setAwards(ActiveAward[] awards) {
        this.awards = awards;
    }
    
    public ActiveBase[] getBases() {
        return this.bases;
    }
    
    public void setBases(ActiveBase[] bases) {
        this.bases = bases;
    }
}
