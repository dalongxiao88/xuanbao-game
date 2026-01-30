package come.tool.Scene.BTY;

import come.tool.Battle.BattleData;
import come.tool.Battle.BattleEnd;
import java.util.Map;
import org.come.model.Dorp;
import come.tool.Good.DropUtil;
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

public class BTYScene implements Scene
{
    private int I;
    private int CI;
    public long time;
    public static final long mapId = 3324L;
    private int BTYHP;
    private ConcurrentHashMap<String, BTYRole> roleMap;
    private List<BTYMonster> monsterMap;
    private BTYThread btyThread;
    private MonsterMoveBase[] btyPaths;
    static String MSG1;
    static String MSG2;
    static String MSG3;
    static String[] MSGS;
    
    public BTYScene() {
        this.BTYHP = 100;
        this.init();
        NChatBean bean = new NChatBean();
        bean.setId(9);
        bean.setMessage("#R守卫蟠桃园活动开启#Y,请各位勇士前往长安擂台旁或洛阳客栈门口，找NPC进入！");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        bean.setId(5);
        bean.setMessage("守卫蟠桃园活动开启");
        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        this.I = 1;
        this.CI = 0;
        this.btyThread = new BTYThread(this);
        Thread T1 = new Thread(this.btyThread);
        T1.start();
    }
    
    @Override
    public boolean isEnd() {
        if (this.I == 4 || this.I == 3) {
            return false;
        }
        if (this.CI == 20 && this.monsterMap.size() == 0) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("守卫蟠桃园活动胜利,到相关NPC领取奖励");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            this.I = 3;
            return false;
        }
        if (this.BTYHP <= 0) {
            this.Empty();
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("守卫蟠桃园活动失败,到相关NPC领取奖励");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            this.I = 4;
            return false;
        }
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
            BTYMonster monster = (BTYMonster)this.monsterMap.get(i);
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
        if (size != 0) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("被" + size + "只怪物突破防线,剩余蟠桃数量" + this.BTYHP);
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(3324L), msg);
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
                this.monsterMap.add(new BTYMonster(bean1, this.CI));
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
    
    public void Empty() {
        StringBuffer buffer = new StringBuffer("M");
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            BTYMonster monster = (BTYMonster)this.monsterMap.get(i);
            this.monsterMap.remove(i);
            MonsterUtil.removeMonster2(monster.getBean());
            if (buffer.length() > 1) {
                buffer.append("#");
            }
            buffer.append(monster.getBean().getI());
            buffer.append("^2");
        }
        if (buffer.length() > 1) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(3324L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
    }
    
    public Boos getBoos() {
        String id = this.CI + 153 + "";
        Boos boos = (Boos)GameServer.boosesMap.get(id);
        if (boos == null) {
            boos = (Boos)MonsterUtil.booses.get(0);
        }
        return boos;
    }
    
    public int getI() {
        return this.I;
    }
    
    public BTYRole getRole(String name) {
        return (BTYRole)this.roleMap.get(name);
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
        BTYRole role = (BTYRole)this.roleMap.get(loginResult.getRolename());
        if (this.I != 3 && this.I != 4) {
            SendMessage.sendMessageToSlef(ctx, BTYScene.MSG1);
            return;
        }
        if (role == null) {
            SendMessage.sendMessageToSlef(ctx, BTYScene.MSG2);
        }
        else if (role.isAward()) {
            SendMessage.sendMessageToSlef(ctx, BTYScene.MSG3);
        }
        else {
            role.setAward(true);
            Dorp dorp = this.Award(role.getJf());
            if (dorp != null) {
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), "守卫蟠桃园礼包", 22, 1.0, null);
            }
        }
    }
    
    public Dorp Award(int jf) {
        int dc = jf / 100;
        if (dc > 6) {
            dc = 6;
        }
        if (this.I == 3) {
            ++dc;
        }
        Dorp dorp = GameServer.getDorp(dc + 352 + "");
        if (dorp == null) {
            return null;
        }
        return dorp;
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            BTYMonster btyMonster = (BTYMonster)this.monsterMap.get(i);
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
        MapMonsterBean bean = battleData.getMonsterBean();
        bean.setType(type);
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append("M");
        buffer.append(bean.getI());
        buffer.append("^");
        buffer.append(bean.getType());
        if (type == 0 || type == 2) {
            int i = this.monsterMap.size() - 1;
            while (i >= 0) {
                BTYMonster monster = (BTYMonster)this.monsterMap.get(i);
                if ((int)monster.getBean().getI() != (int)bean.getI()) {
                    --i;
                }
                else {
                    int jf = 0;
                    if (type == 2) {
                        this.monsterMap.remove(i);
                        MonsterUtil.removeMonster2(bean);
                        jf = monster.getCI();
                    }
                    if (this.I == 1) {
                        for (int j = 0; j < teams.length; ++j) {
                            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[j]);
                            LoginResult log = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                            if (log != null) {
                                BTYRole btyRole = (BTYRole)this.roleMap.get(log.getRolename());
                                if (btyRole == null) {
                                    btyRole = new BTYRole(log.getRole_id(), log.getRolename());
                                    this.roleMap.put(log.getRolename(), btyRole);
                                }
                                if (jf != 0) {
                                    btyRole.addJF(jf);
                                    SendMessage.sendMessageToSlef(ctx, BTYScene.MSGS[monster.getCI() - 1]);
                                }
                            }
                        }
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return null;
    }
    
    static {
        BTYScene.MSG1 = Agreement.getAgreement().PromptAgreement("本次活动还没有结束");
        BTYScene.MSG2 = Agreement.getAgreement().PromptAgreement("你未参与本次活动无法领取奖励");
        BTYScene.MSG3 = Agreement.getAgreement().PromptAgreement("你已经领取过奖励");
        BTYScene.MSGS = new String[20];
        for (int i = 0; i < BTYScene.MSGS.length; ++i) {
            BTYScene.MSGS[i] = Agreement.getAgreement().PromptAgreement("你获得" + (i + 1) + "积分");
        }
    }
}
