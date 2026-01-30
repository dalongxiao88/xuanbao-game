package come.tool.FightingData;

import java.util.ArrayList;
import java.util.List;
/**
 * 战斗回合
 * @author Administrator
 *
 */
public class FightingRound
{
    private List<FightingEvents> Round;//处理后的回合指令
    private List<ManStateData> mansState;
    private int CurrentRound;//当前回合
    private int Fightingsumber;//战斗编号
    
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
