package org.come.bean;

import org.come.model.ActiveBase;
import org.come.model.ActiveAward;

public class AllActive
{
    private ActiveAward[] awards;
    private ActiveBase[] bases;
    
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
