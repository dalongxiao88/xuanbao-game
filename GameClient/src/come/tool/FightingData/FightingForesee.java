package come.tool.FightingData;

import java.util.Map;
import java.util.List;

public class FightingForesee
{
    private String yidui;
    private String erdui;
    private int type;
    private int nd;
    private List<String> Creepids;
    private String alias;
    private String robotid;
    private int I;
    private Map<String, FightingStatistics> fightingStatisticsMap;
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<String> getCreepids() {
        return this.Creepids;
    }
    
    public void setCreepids(List<String> creepids) {
        this.Creepids = creepids;
    }
    
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getYidui() {
        return this.yidui;
    }
    
    public void setYidui(String yidui) {
        this.yidui = yidui;
    }
    
    public String getErdui() {
        return this.erdui;
    }
    
    public void setErdui(String erdui) {
        this.erdui = erdui;
    }
    
    public String getRobotid() {
        return this.robotid;
    }
    
    public void setRobotid(String robotid) {
        this.robotid = robotid;
    }
    
    public int getNd() {
        return this.nd;
    }
    
    public void setNd(int nd) {
        this.nd = nd;
    }
    
    public Map<String, FightingStatistics> getFightingStatisticsMap() {
        return this.fightingStatisticsMap;
    }
    
    public void setFightingStatisticsMap(Map<String, FightingStatistics> fightingStatisticsMap) {
        this.fightingStatisticsMap = fightingStatisticsMap;
    }
}
