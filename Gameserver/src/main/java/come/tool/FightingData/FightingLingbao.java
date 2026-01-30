package come.tool.FightingData;
/**
 * 战斗灵宝
 * @author Administrator
 *
 */
public class FightingLingbao
{
    private ManData lingbaonData;  //灵宝数据
    private int Play;//灵宝是否出场 0未上场 1正在上场 2已经下场
    
    public FightingLingbao() {
    }
    
    public FightingLingbao(ManData lingbaonData, int play) {
        this.lingbaonData = lingbaonData;
        this.Play = play;
    }
    
    public ManData getLingbaonData() {
        return this.lingbaonData;
    }
    
    public void setLingbaonData(ManData lingbaonData) {
        this.lingbaonData = lingbaonData;
    }
    
    public int getPlay() {
        return this.Play;
    }
    
    public void setPlay(int play) {
        this.Play = play;
    }
}
