package come.tool.Fighting;

import java.util.ArrayList;
import java.util.List;

public class FightingRound
{
    private List<FightingEvents> Round;
    private List<ManStateData> mansState;
    private int CurrentRound;
    private int Fightingsumber;
    
    public FightingRound() {
        this.Round = new ArrayList<>();
        this.mansState = new ArrayList<>();
    }
    
    public int getCurrentRound() {
        return this.CurrentRound;
    }
    
    public void setCurrentRound(int currentRound) {
        this.CurrentRound = currentRound;
    }
    
    public List<FightingEvents> getRound() {
        return this.Round;
    }
    
    public void setRound(List<FightingEvents> round) {
        this.Round = round;
    }
    
    public int getFightingsumber() {
        return this.Fightingsumber;
    }
    
    public void setFightingsumber(int fightingsumber) {
        this.Fightingsumber = fightingsumber;
    }
    
    public List<ManStateData> getMansState() {
        return this.mansState;
    }
    
    public void setMansState(List<ManStateData> mansState) {
        this.mansState = mansState;
    }
}
