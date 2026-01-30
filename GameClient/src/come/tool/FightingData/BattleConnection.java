package come.tool.FightingData;

import come.tool.Fighting.FightingEvents;
import come.tool.Fighting.FightingManData;
import java.util.List;

public class BattleConnection
{
    private List<FightingManData> datas;
    private List<FightingEvents> playeEvents;
    private int state;
    private long time;
    private int eventState;
    private int Round;
    public int FightingNumber;
    public int BattleType;
    public String buff;
    
    public List<FightingManData> getDatas() {
        return this.datas;
    }
    
    public void setDatas(List<FightingManData> datas) {
        this.datas = datas;
    }
    
    public List<FightingEvents> getPlayeEvents() {
        return this.playeEvents;
    }
    
    public void setPlayeEvents(List<FightingEvents> playeEvents) {
        this.playeEvents = playeEvents;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public int getEventState() {
        return this.eventState;
    }
    
    public void setEventState(int eventState) {
        this.eventState = eventState;
    }
    
    public int getRound() {
        return this.Round;
    }
    
    public void setRound(int round) {
        this.Round = round;
    }
    
    public int getFightingNumber() {
        return this.FightingNumber;
    }
    
    public void setFightingNumber(int fightingNumber) {
        this.FightingNumber = fightingNumber;
    }
    
    public int getBattleType() {
        return this.BattleType;
    }
    
    public void setBattleType(int battleType) {
        this.BattleType = battleType;
    }
    
    public String getBuff() {
        return this.buff;
    }
    
    public void setBuff(String buff) {
        this.buff = buff;
    }
}
