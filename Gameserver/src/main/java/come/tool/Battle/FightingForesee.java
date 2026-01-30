package come.tool.Battle;

import come.tool.FightingData.ManData;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import come.tool.FightingData.Battlefield;
import java.util.Map;
import java.util.List;
/**
 * 战斗预知
 * @author Administrator
 *
 */
public class FightingForesee
{
    private String yidui;
    private String erdui;
    // 战斗类型 0暗雷 1明雷 2任务 5切磋 10强pk（1阵营主动强p） 15 天兵抓人 16 拒绝贿赂   21水路 31种族赛匹配赛 32种族赛淘汰赛  33(PK赛)擂台赛     34召唤兽比斗副本  60武神山守护
    private int type;
    //任务挑战选择的难度等级
    private int nd;
    // 战斗暗雷怪
    private List<String> Creepids;
    //野怪的别名称
    private String alias;
    //robotID
    private String robotid;
    private int I;
    private Map<String, FightingStatistics> fightingStatisticsMap;
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
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
    
    public Map<String, FightingStatistics> getFightingStatisticsMap(Battlefield battlefield) {
        BattleData battleData = battlefield.battleData;
        if (this.fightingStatisticsMap == null) {
            this.fightingStatisticsMap = new HashMap<>();
            if (battleData.getRoleId1() != null) {
                FightingStatistics fightingStatistics = new FightingStatistics();
                ManData m = battlefield.getBattleEndData(battleData.getTeam1()[0]);
                if (m != null) {
                    fightingStatistics.setCamp(m.getCamp());
                    fightingStatistics.settName(battleData.getTeam1()[0]);
                    this.fightingStatisticsMap.put(m.getCamp() + "", fightingStatistics);
                }
            }
            if (battleData.getRoleId2() != null) {
                FightingStatistics fightingStatistics = new FightingStatistics();
                ManData m = battlefield.getBattleEndData(battleData.getTeam2()[0]);
                if (m != null) {
                    fightingStatistics.setCamp(m.getCamp());
                    fightingStatistics.settName(battleData.getTeam2()[0]);
                    this.fightingStatisticsMap.put(m.getCamp() + "", fightingStatistics);
                }
            }
            if (battleData.getRobots() != null || StringUtils.isNotBlank(battlefield.yename)) {
                FightingStatistics fightingStatistics = new FightingStatistics();
                ManData m = battlefield.getBattleEndData(battlefield.yename, 1);
                if (m != null) {
                    fightingStatistics.setCamp(m.getCamp());
                    fightingStatistics.settName(battlefield.yename);
                    this.fightingStatisticsMap.put(m.getCamp() + "", fightingStatistics);
                }
            }
            battleData.getFightingForesee().setFightingStatisticsMap(this.fightingStatisticsMap);
        }
        return this.fightingStatisticsMap;
    }
    
    public void setFightingStatisticsMap(Map<String, FightingStatistics> fightingStatisticsMap) {
        this.fightingStatisticsMap = fightingStatisticsMap;
    }
    
    @Override
    public String toString() {
        return "FightingForesee{yidui='" + this.yidui + '\'' + ", erdui='" + this.erdui + '\'' + ", type=" + this.type + ", nd=" + this.nd + ", Creepids=" + this.Creepids + ", alias='" + this.alias + '\'' + ", robotid='" + this.robotid + '\'' + ", I=" + this.I + ", fightingStatisticsMap=" + this.fightingStatisticsMap + '}';
    }
}
