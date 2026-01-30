package come.tool.Fighting;

import java.util.List;

public class FightingEvents
{
    private FightingState Originator;
    private List<FightingState> Accepterlist;
    private int CurrentRound;
    private int Fightingsum;
    private Integer type;
    private Integer sp;
    
    public FightingEvents() {
        this.type = Integer.valueOf(-1);
    }
    
    public FightingState getOriginator() {
        return this.Originator;
    }
    
    public void setOriginator(FightingState originator) {
        this.Originator = originator;
    }
    
    public List<FightingState> getAccepterlist() {
        return this.Accepterlist;
    }
    
    public void setAccepterlist(List<FightingState> accepterlist) {
        this.Accepterlist = accepterlist;
    }
    
    public int getCurrentRound() {
        return this.CurrentRound;
    }
    
    public void setCurrentRound(int currentRound) {
        this.CurrentRound = currentRound;
    }
    
    public int getFightingsum() {
        return this.Fightingsum;
    }
    
    public void setFightingsum(int fightingsum) {
        this.Fightingsum = fightingsum;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getSp() {
        return this.sp;
    }
    
    public void setSp(Integer sp) {
        this.sp = sp;
    }
}
