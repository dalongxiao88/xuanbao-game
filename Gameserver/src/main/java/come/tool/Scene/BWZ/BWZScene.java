package come.tool.Scene.BWZ;

import come.tool.Battle.BattleEnd;
import java.util.Map;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Battle.BattleData;
import come.tool.Battle.RewardLimit;
import org.come.task.MonsterMove;
import org.come.task.MapMonsterBean;
import org.come.bean.PathPoint;
import org.come.server.GameServer;
import org.come.model.Robots;
import org.come.model.Boos;
import org.come.task.MonsterUtil;
import java.util.ArrayList;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.Calendar;
import java.util.Locale;
import org.come.task.MonsterMoveBase;
import java.util.List;
import come.tool.Scene.Scene;

public class BWZScene implements Scene
{
    private int I;
    private int CI;
    public long time;
    public static final long mapId = 1193L;
    private int BTYHP;
    private List<BWZMonster> monsterMap;
    private BWZThread btyThread;
    private MonsterMoveBase[] btyPaths;
    private int day;
    
    public BWZScene() {
        this.BTYHP = 800;
        Calendar rightNow = Calendar.getInstance(Locale.CHINA);
        this.day = rightNow.get(7);
        this.init();
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#R长安保卫战开启");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        this.I = 1;
        this.CI = 0;
        this.btyThread = new BWZThread(this);
        Thread T1 = new Thread(this.btyThread);
        T1.start();
    }
    
    @Override
    public boolean isEnd() {
        if (this.I == 2) {
            return false;
        }
        if (this.CI >= 30 && this.monsterMap.size() == 0) {
            this.I = 2;
            return false;
        }
        if (this.BTYHP <= 0) {
            this.Empty();
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("长安保卫战失败");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            this.I = 2;
            return false;
        }
        return true;
    }
    
    public void init() {
        this.monsterMap = new ArrayList<>();
        this.btyPaths = new MonsterMoveBase[5];
        for (int i = 0; i < this.btyPaths.length; ++i) {
            this.btyPaths[i] = new MonsterMoveBase(5 + i);
        }
    }
    
    public void move() {
        StringBuffer buffer = null;
        int mmin = 0;
        int mmax = 0;
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            BWZMonster monster = (BWZMonster)this.monsterMap.get(i);
            if (monster.move()) {
                this.monsterMap.remove(i);
                MonsterUtil.removeMonster2(monster.getBean());
                if (buffer == null) {
                    buffer = new StringBuffer("M");
                }
                else {
                    buffer.append("#");
                }
                buffer.append(monster.getBean().getI());
                buffer.append("^2");
                if (this.BTYHP > 0) {
                    this.BTYHP -= ((monster.getI() == 4) ? 5 : 1);
                    if (monster.getI() == 4) {
                        ++mmax;
                    }
                    else {
                        ++mmin;
                    }
                }
            }
        }
        if (buffer != null) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(1193L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
        if (mmin != 0 || mmax != 0) {
            buffer.setLength(0);
            buffer.append("被");
            if (mmax != 0) {
                buffer.append(mmax);
                buffer.append("只BOOS");
            }
            if (mmin != 0) {
                if (mmax != 0) {
                    buffer.append(",");
                }
                buffer.append(mmin);
                buffer.append("只小怪");
            }
            buffer.append("突破防线扣除");
            buffer.append(mmin + 5 * mmax);
            buffer.append("点血量,剩余城门剩余血量:#R");
            buffer.append(this.BTYHP);
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage(buffer.toString());
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(1193L), msg);
        }
    }
    
    public void open() {
        if (this.CI >= 30) {
            return;
        }
        ++this.CI;
        StringBuffer buffer = new StringBuffer("M");
        for (int i = 0; i < this.btyPaths.length; ++i) {
            MonsterMoveBase moveBase = this.btyPaths[i];
            if (buffer.length() > 1) {
                buffer.append("#");
            }
            buffer.append(moveBase.getMoveMsg());
        }
        for (int i = 0; i < this.btyPaths.length; ++i) {
            this.open(this.getBoos(i), this.btyPaths[i], i, buffer);
        }
        SendMessage.sendMessageToMapRoles(Long.valueOf(1193L), Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
    }
    
    public void open(Boos boos, MonsterMoveBase bwzPath, int I, StringBuffer buffer) {
        Robots robot = (Robots)GameServer.getAllRobot().get(boos.getBoosrobot());
        int robotId = robot.getRobotID();
        int size = boos.getBoosnum();
        size *= this.CI;
        buffer.append("|");
        buffer.append(robot.getRobotID());
        buffer.append("#");
        buffer.append(robot.getRobotname());
        buffer.append("#");
        buffer.append(robot.getRobotskin());
        buffer.append("#");
        buffer.append(robot.getRobotType());
        int maxtime = boos.getBoosetime();
        PathPoint point = (PathPoint)bwzPath.getPoints().get(0);
        for (int i = 0; i < size; ++i) {
            MapMonsterBean Bean1 = new MapMonsterBean();
            Bean1.setX(Integer.valueOf(point.getX() + MonsterUtil.getPY()));
            Bean1.setY((long)(point.getY() + MonsterUtil.getPY()));
            Bean1.setRobotid(Integer.valueOf(robotId));
            Bean1.setRobotname(robot.getRobotname());
            Bean1.setRobotskin(robot.getRobotskin());
            Bean1.setRobotType(robot.getRobotType());
            Bean1.setI(Integer.valueOf(MonsterUtil.getIncreasesum()));
            Bean1.setMap(Long.valueOf(1193L));
            Bean1.setMaxtime(maxtime);
            Bean1.setSX(1007);
            Bean1.setMove(new MonsterMove(bwzPath, -30000 * (i % 30), (int)Bean1.getX(), Bean1.getY()));
            this.monsterMap.add(new BWZMonster(Bean1, I));
            MonsterUtil.addEMonster(Bean1);
            MonsterUtil.monsterBuffer1(Bean1, buffer);
            if (i == 0) {
                Bean1.setBoosId(RewardLimit.isBoosDrop(boos));
            }
        }
        MonsterUtil.boosChat(boos, null, null, null);
    }
    
    public void Empty() {
        StringBuffer buffer = new StringBuffer("M");
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            BWZMonster monster = (BWZMonster)this.monsterMap.get(i);
            this.monsterMap.remove(i);
            MonsterUtil.removeMonster2(monster.getBean());
            if (buffer.length() > 1) {
                buffer.append("#");
            }
            buffer.append(monster.getBean().getI());
            buffer.append("^2");
        }
        if (buffer.length() > 1) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(1193L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
    }
    
    public Boos getBoos(int i) {
        String id = i + ((this.day == 5) ? 282 : 182) + "";
        Boos boos = (Boos)GameServer.boosesMap.get(id);
        if (boos == null) {
            boos = (Boos)MonsterUtil.booses.get(0);
        }
        return boos;
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        MapMonsterBean bean = battleData.getMonsterBean();
        bean.setType(type);
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append("M");
        buffer.append(bean.getI());
        buffer.append("^");
        buffer.append(bean.getType());
        if (type == 2) {
            int i = this.monsterMap.size() - 1;
            while (i >= 0) {
                BWZMonster monster = (BWZMonster)this.monsterMap.get(i);
                if ((int)monster.getBean().getI() != (int)bean.getI()) {
                    --i;
                }
                else {
                    this.monsterMap.remove(i);
                    MonsterUtil.removeMonster2(bean);
                    break;
                }
            }
        }
        return null;
    }
    
    public int getI() {
        return this.I;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            BWZMonster bwzMonster = (BWZMonster)this.monsterMap.get(i);
            MapMonsterBean bean = bwzMonster.getBean();
            moveMap = MonsterUtil.monsterBuffer(bean, buffer, moveMap);
        }
        return moveMap;
    }
    
    @Override
    public boolean isTime(long time) {
        return true;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
}
