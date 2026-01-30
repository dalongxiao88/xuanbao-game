package come.tool.Scene.TAST;

import come.tool.Battle.BattleData;
import come.tool.Battle.BattleEnd;
import java.util.Map;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.model.Boos;
import come.tool.Battle.RewardLimit;
import org.come.task.MonsterMove;
import org.come.task.MapMonsterBean;
import org.come.bean.PathPoint;
import org.come.server.GameServer;
import org.come.model.Robots;
import org.come.task.MonsterUtil;
import java.util.ArrayList;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import org.come.task.MonsterMoveBase;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Scene.Scene;

public class MrScene implements Scene
{
    private int I;
    private int CI;
    public long time;
    public static final long mapId = 3324L;
    private int BTYHP;
    private ConcurrentHashMap<String, MrRole> roleMap;
    private List<MrMonster> monsterMap;
    private MrThread rotoThred;
    private MonsterMoveBase[] btyPaths;
    
    public MrScene() {
        this.BTYHP = 30;
        this.init();
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("守卫蟠桃园活动开启");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        this.I = 1;
        this.CI = 0;
        this.rotoThred = new MrThread(this);
        Thread T1 = new Thread(this.rotoThred);
        T1.start();
    }
    
    @Override
    public boolean isEnd() {
        return true;
    }
    
    public void init() {
        this.roleMap = new ConcurrentHashMap<>();
        this.monsterMap = new ArrayList<>();
        this.btyPaths = new MonsterMoveBase[4];
        for (int i = 0; i < this.btyPaths.length; ++i) {
            this.btyPaths[i] = new MonsterMoveBase(i);
        }
    }
    
    public void move() {
        int size = 0;
        StringBuffer buffer = null;
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            MrMonster monster = (MrMonster)this.monsterMap.get(i);
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
                    ++size;
                    --this.BTYHP;
                }
            }
        }
        if (buffer != null) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(3324L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
    }
    
    public void open() {
        if (this.CI >= 20) {
            return;
        }
        ++this.CI;
        Boos boos = this.getBoos();
        Robots robot = (Robots)GameServer.getAllRobot().get(boos.getBoosrobot());
        int robotId = robot.getRobotID();
        int size = boos.getBoosnum();
        try {
            int y = 0;
            y = (((ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(Long.valueOf(3324L))).size() - 20) / 10;
            if (y > 0) {
                size = (int)((double)size * (1.0 + (double)y * 0.1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer("M");
        for (int j = 0; j < this.btyPaths.length; ++j) {
            MonsterMoveBase moveBase = this.btyPaths[j];
            if (buffer.length() > 1) {
                buffer.append("#");
            }
            buffer.append(moveBase.getMoveMsg());
        }
        buffer.append("|");
        buffer.append(robot.getRobotID());
        buffer.append("#");
        buffer.append(robot.getRobotname());
        buffer.append("#");
        buffer.append(robot.getRobotskin());
        buffer.append("#");
        buffer.append(robot.getRobotType());
        int maxtime = boos.getBoosetime();
        for (int i = 0; i < this.btyPaths.length; ++i) {
            MonsterMoveBase moveBase2 = this.btyPaths[i];
            PathPoint point = (PathPoint)moveBase2.getPoints().get(0);
            for (int k = 0; k < size; ++k) {
                MapMonsterBean bean1 = new MapMonsterBean();
                bean1.setX(Integer.valueOf(point.getX() + MonsterUtil.getPY()));
                bean1.setY((long)(point.getY() + MonsterUtil.getPY()));
                bean1.setRobotid(Integer.valueOf(robotId));
                bean1.setRobotname(robot.getRobotname());
                bean1.setRobotskin(robot.getRobotskin());
                bean1.setRobotType(robot.getRobotType());
                bean1.setI(Integer.valueOf(MonsterUtil.getIncreasesum()));
                bean1.setMap(Long.valueOf(3324L));
                bean1.setMaxtime(maxtime);
                bean1.setSX(1001);
                bean1.setMove(new MonsterMove(moveBase2, -1000 * k, (int)bean1.getX(), bean1.getY()));
                this.monsterMap.add(new MrMonster(bean1, this.CI));
                MonsterUtil.addEMonster(bean1);
                MonsterUtil.monsterBuffer1(bean1, buffer);
                if (k == 0) {
                    bean1.setBoosId(RewardLimit.isBoosDrop(boos));
                }
            }
        }
        SendMessage.sendMessageToMapRoles(Long.valueOf(3324L), Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
        MonsterUtil.boosChat(boos, null, null, null);
    }
    
    public Boos getBoos() {
        String id = this.CI + 334 + "";
        Boos boos = (Boos)GameServer.boosesMap.get(id);
        if (boos == null) {
            boos = (Boos)MonsterUtil.booses.get(0);
        }
        return boos;
    }
    
    public int getI() {
        return this.I;
    }
    
    public MrRole getRole(String name) {
        return (MrRole)this.roleMap.get(name);
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            MrMonster btyMonster = (MrMonster)this.monsterMap.get(i);
            MapMonsterBean bean = btyMonster.getBean();
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
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
}
