package come.tool.Scene.DNTG;

import org.come.task.MonsterHp;
import org.come.task.MonsterUtil;
import org.come.server.GameServer;
import org.come.model.Robots;
import org.come.task.MapMonsterBean;

public class DNTGCamp
{
    private int KJValue;
    private MapMonsterBean DBY;
    private MapMonsterBean K_JS;
    private MapMonsterBean T_S;
    private MapMonsterBean T_Z;
    private MapMonsterBean T_X;
    private DNTGBuff buff;
    //大闹的怪物
    public DNTGCamp(int camp) {
        Robots robots = (Robots)GameServer.getAllRobot().get((camp == 0) ? "911" : "916");
        (this.DBY = new MapMonsterBean(Long.valueOf(3201L), Integer.valueOf(MonsterUtil.getIncreasesum()), (camp == 0) ? 101 : 111, 1011)).setRobotname("大本营");
        this.DBY.setRobotid(Integer.valueOf(robots.getRobotID()));
        this.DBY.setRobotskin((camp == 0) ? "400577" : "500027");
        this.DBY.setX(Integer.valueOf((camp == 0) ? 2120 : 7360));
        this.DBY.setY((camp == 0) ? 900L : 3520L);
        this.DBY.setHp(new MonsterHp(250L, true));
        MonsterUtil.addEMonster(this.DBY);
        robots = (Robots)GameServer.getAllRobot().get((camp == 0) ? "908" : "913");
        (this.T_S = new MapMonsterBean(Long.valueOf(3201L), Integer.valueOf(MonsterUtil.getIncreasesum()), (camp == 0) ? 102 : 112, 1011)).setRobotname((camp == 0) ? "天庭上塔" : "花果山上塔");
        this.T_S.setRobotid(Integer.valueOf(robots.getRobotID()));
        this.T_S.setRobotskin((camp == 0) ? "500029" : "500028");
        this.T_S.setX(Integer.valueOf((camp == 0) ? 2580 : 8640));
        this.T_S.setY((camp == 0) ? 1000L : 2800L);
        this.T_S.setHp(new MonsterHp(500L, true));
        MonsterUtil.addEMonster(this.T_S);
        (this.T_Z = new MapMonsterBean(Long.valueOf(3201L), Integer.valueOf(MonsterUtil.getIncreasesum()), (camp == 0) ? 103 : 113, 1011)).setRobotname((camp == 0) ? "天庭中塔" : "花果山中塔");
        this.T_Z.setRobotid(this.T_S.getRobotid());
        this.T_Z.setRobotskin(this.T_S.getRobotskin());
        this.T_Z.setX(Integer.valueOf((camp == 0) ? 2100 : 6800));
        this.T_Z.setY((camp == 0) ? 1760L : 3060L);
        this.T_Z.setHp(new MonsterHp(500L, true));
        MonsterUtil.addEMonster(this.T_Z);
        (this.T_X = new MapMonsterBean(Long.valueOf(3201L), Integer.valueOf(MonsterUtil.getIncreasesum()), (camp == 0) ? 104 : 114, 1011)).setRobotname((camp == 0) ? "天庭下塔" : "花果山下塔");
        this.T_X.setRobotid(this.T_S.getRobotid());
        this.T_X.setRobotskin(this.T_S.getRobotskin());
        this.T_X.setX(Integer.valueOf((camp == 0) ? 740 : 6640));
        this.T_X.setY((camp == 0) ? 1600L : 3720L);
        this.T_X.setHp(new MonsterHp(500L, true));
        MonsterUtil.addEMonster(this.T_X);
    }
    
    public void removeMapMonster(StringBuffer buffer) {
        if (buffer.length() > 1) {
            buffer.append("#");
        }
        buffer.append(this.DBY.getI());
        buffer.append("^2");
        MonsterUtil.removeMonster2(this.DBY);
        if (buffer.length() > 1) {
            buffer.append("#");
        }
        buffer.append(this.T_S.getI());
        buffer.append("^2");
        MonsterUtil.removeMonster2(this.T_S);
        if (buffer.length() > 1) {
            buffer.append("#");
        }
        buffer.append(this.T_Z.getI());
        buffer.append("^2");
        MonsterUtil.removeMonster2(this.T_Z);
        if (buffer.length() > 1) {
            buffer.append("#");
        }
        buffer.append(this.T_X.getI());
        buffer.append("^2");
        MonsterUtil.removeMonster2(this.T_X);
    }
    
    public void getMapMonster(StringBuffer buffer) {
        for (int i = 0; i < 5; ++i) {
            if (i != 1) {
                MapMonsterBean bean = (i == 0) ? this.DBY : ((i == 1) ? this.K_JS : ((i == 2) ? this.T_S : ((i == 3) ? this.T_Z : this.T_X)));
                MonsterUtil.monsterBuffer(bean, buffer, null);
            }
        }
    }
    
    public MapMonsterBean getDBY() {
        return this.DBY;
    }
    
    public void setDBY(MapMonsterBean dBY) {
        this.DBY = dBY;
    }
    
    public MapMonsterBean getT_S() {
        return this.T_S;
    }
    
    public void setT_S(MapMonsterBean t_S) {
        this.T_S = t_S;
    }
    
    public MapMonsterBean getT_Z() {
        return this.T_Z;
    }
    
    public void setT_Z(MapMonsterBean t_Z) {
        this.T_Z = t_Z;
    }
    
    public MapMonsterBean getT_X() {
        return this.T_X;
    }
    
    public void setT_X(MapMonsterBean t_X) {
        this.T_X = t_X;
    }
    
    public int getKJValue() {
        return this.KJValue;
    }
    
    public void setKJValue(int kJValue) {
        this.KJValue = kJValue;
    }
    
    public void addKJValue(int add) {
        this.KJValue += add;
    }
    
    public DNTGBuff getBuff() {
        return this.buff;
    }
    
    public void setBuff(DNTGBuff buff) {
        this.buff = buff;
    }
    
    public double getBoosXS() {
        double xs = 0.0;
        if (this.T_S.getHp().getHp() > 0L) {
            ++xs;
        }
        if (this.T_Z.getHp().getHp() > 0L) {
            ++xs;
        }
        if (this.T_X.getHp().getHp() > 0L) {
            ++xs;
        }
        return xs;
    }
}
