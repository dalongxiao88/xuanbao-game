package come.tool.FightingData;
/**
 * 战斗响应
 * @author Administrator
 *
 */
public class FightingResponse
{
    // 战斗编号
    private int FightingNumber;
    private ManData manData;//玩家数据以及召唤兽数据
    
    public int getFightingNumber() {
        return this.FightingNumber;
    }
    
    public void setFightingNumber(int fightingNumber) {
        this.FightingNumber = fightingNumber;
    }
    
    public ManData getManData() {
        return this.manData;
    }
    
    public void setManData(ManData manData) {
        this.manData = manData;
    }
}
