package come.tool.Scene.DNTG;

import org.come.task.MonsterHp;
import org.come.task.MonsterUtil;
import org.come.server.GameServer;
import org.come.model.Robots;
import org.come.task.MapMonsterBean;

public class DNTG_NV_Monster
{
    private MapMonsterBean bean;
    private DNTG_NV_Ranking TTRanking;
    private DNTG_NV_Ranking HGSRanking;
    
    public DNTG_NV_Monster() {
        Robots robots = (Robots)GameServer.getAllRobot().get("917");
        (this.bean = new MapMonsterBean(Long.valueOf(3201L), Integer.valueOf(MonsterUtil.getIncreasesum()), 130, 1011)).setRobotname(robots.getRobotname());
        this.bean.setRobotid(Integer.valueOf(917));
        this.bean.setRobotskin(robots.getRobotskin());
        this.bean.setX(Integer.valueOf(5380));
        this.bean.setY(1380L);
        this.bean.setHp(new MonsterHp(1000L, true));
        MonsterUtil.addEMonster(this.bean);
        this.TTRanking = new DNTG_NV_Ranking(0);
        this.HGSRanking = new DNTG_NV_Ranking(1);
    }
    
    public void toString(DNTGRole dntgRole, StringBuffer buffer) {
        buffer.append("|N2");
        buffer.append(dntgRole.getNVNum());
        buffer.append("$");
        if (dntgRole.getCamp() == 0) {
            buffer.append(this.TTRanking.getPlace(dntgRole));
        }
        else {
            buffer.append(this.HGSRanking.getPlace(dntgRole));
        }
        if (this.TTRanking.getRankingSting() != null) {
            buffer.append("|");
            buffer.append(this.TTRanking.getRankingSting());
        }
        if (this.HGSRanking.getRankingSting() != null) {
            buffer.append("|");
            buffer.append(this.HGSRanking.getRankingSting());
        }
    }
    
    public int end() {
        if (this.TTRanking.getSize() > this.HGSRanking.getSize()) {
            return 0;
        }
        if (this.HGSRanking.getSize() > this.TTRanking.getSize()) {
            return 1;
        }
        return -1;
    }
    
    public MapMonsterBean getBean() {
        return this.bean;
    }
    
    public void setBean(MapMonsterBean bean) {
        this.bean = bean;
    }
    
    public DNTG_NV_Ranking getHGSRanking() {
        return this.HGSRanking;
    }
    
    public void setHGSRanking(DNTG_NV_Ranking hGSRanking) {
        this.HGSRanking = hGSRanking;
    }
    
    public DNTG_NV_Ranking getTTRanking() {
        return this.TTRanking;
    }
    
    public void setTTRanking(DNTG_NV_Ranking tTRanking) {
        this.TTRanking = tTRanking;
    }
}
