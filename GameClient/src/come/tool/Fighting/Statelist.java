package come.tool.Fighting;

import java.util.List;

public class Statelist
{
    private List<FightingEvents> Round;
    private List<StateProgress> State;
    private int Progress;
    
    public Statelist() {
        this.Progress = 0;
    }
    
    public List<StateProgress> getState() {
        return this.State;
    }
    
    public void setState(List<StateProgress> state) {
        this.State = state;
    }
    
    public int getProgress() {
        return this.Progress;
    }
    
    public void setProgress(int progress) {
        this.Progress = progress;
    }
    
    public List<FightingEvents> getRound() {
        return this.Round;
    }
    
    public void setRound(List<FightingEvents> round) {
        this.Round = round;
    }
}
