package come.tool.Battle;

import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingManData;
import java.util.List;
/**
 * 用于战斗重连和观战
 * @author Administrator
 */
public class BattleConnection
{
    private List<FightingManData> datas;//播放数据
    private List<FightingEvents> playeEvents;//播放指令
    private int state;//战斗状态
    private long time;//战斗时间戳
    private int eventState;//指令状态
    private int Round;//回合数
    public int FightingNumber;/**战斗编号*/
    public int BattleType;/**战斗类型*/
    public String buff;/**buff数据*/
    
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
