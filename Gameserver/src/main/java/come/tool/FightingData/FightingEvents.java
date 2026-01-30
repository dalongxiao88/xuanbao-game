package come.tool.FightingData;

import java.util.List;
/**
 * 战斗事件
 * @author Administrator
 *
 */
public class FightingEvents
{
    private FightingState Originator;//发起人
    private List<FightingState> Accepterlist;// 接受人集合
    private int CurrentRound; //当前回合
    private int Fightingsum;//战斗编号
    private Integer sp;
    private Integer type;
    private boolean ew;
    
    public FightingEvents() {
        this.type = -1;
        this.ew = false;
    }
    
    public FightingEvents(List<FightingState> accepterlist) {
        this.type = -1;
        this.ew = false;
        this.Accepterlist = accepterlist;
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
    
    public boolean isEw() {
        return this.ew;
    }
    
    public void setEw(boolean ew) {
        this.ew = ew;
    }
}
