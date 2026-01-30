package come.tool.Scene.DNTG;

import come.tool.FightingData.Battlefield;
import org.come.model.Skill;
import come.tool.Battle.BattleData;
import org.come.bean.UseCardBean;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleThreadPool;
import come.tool.Stall.AssetUpdate;
import org.come.bean.NPCDialog;
import java.util.Iterator;
import org.come.task.MonsterMove;
import org.come.bean.PathPoint;
import org.come.model.Robots;
import org.come.task.MonsterUtil;
import org.come.model.Boos;
import come.tool.Role.RoleData;
import org.come.action.sys.ChangeMapAction;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.task.MapMonsterBean;
import org.come.model.Door;
import java.util.HashMap;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.ArrayList;
import org.come.server.GameServer;
import org.come.action.buy.BuyUtil;
import java.util.Map;
import java.util.List;
import org.come.task.MonsterMoveBase;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.come.bean.ChangeMapBean;
import come.tool.Scene.Scene;

public class DNTGScene implements Scene
{
    public static final long mapIdZ = 3201L;
    public static final long mapIdF = 3336L;
    public static final String DNTGBUFF = "DNTG";
    private int I;
    public ChangeMapBean HOME_TT;
    public ChangeMapBean HOME_HGS;
    private ConcurrentHashMap<BigDecimal, DNTGRole> Camp_TT_Man;
    private ConcurrentHashMap<BigDecimal, DNTGRole> Camp_HGS_Man;
    private int roleSize;
    private DNTGCamp TT;
    private DNTGCamp HGS;
    private MonsterMoveBase[] DNTGPaths;
    private DNTG_NV_Monster dntg_NV_Monster;
    private DNTG_SG dntg_SG;
    private List<DNTGMonster> monsterMap;
    private DNTGCountDown countDown;
    private DNTGThread dntgThread;
    private String[] functions;
    private int HPRecord;
    private String JG;
    private Map<Integer, MonsterMoveBase> moveMap;
    
    public DNTGScene() {
        this.Camp_TT_Man = new ConcurrentHashMap<>();
        this.Camp_HGS_Man = new ConcurrentHashMap<>();
        BuyUtil.resetShopType(605);
        this.HPRecord = 0;
        this.functions = new String[] { "攻击", "送我到上古战场", "送我进去", "送我出去", "我要回长安" };
        Door door = GameServer.getDoor(522);
        String[] vs = door.getDoorpoint().split("\\|");
        (this.HOME_TT = new ChangeMapBean()).setMapid(door.getDoormap());
        this.HOME_TT.setMapx(Integer.parseInt(vs[0]));
        this.HOME_TT.setMapy(Integer.parseInt(vs[1]));
        door = GameServer.getDoor(523);
        vs = door.getDoorpoint().split("\\|");
        (this.HOME_HGS = new ChangeMapBean()).setMapid(door.getDoormap());
        this.HOME_HGS.setMapx(Integer.parseInt(vs[0]));
        this.HOME_HGS.setMapy(Integer.parseInt(vs[1]));
        this.Camp_TT_Man = new ConcurrentHashMap<>();
        this.Camp_HGS_Man = new ConcurrentHashMap<>();
        this.monsterMap = new ArrayList<>();
        this.TT = new DNTGCamp(0);
        this.HGS = new DNTGCamp(1);
        this.DNTGPaths = new MonsterMoveBase[6];
        for (int i = 0; i < this.DNTGPaths.length; ++i) {
            this.DNTGPaths[i] = new MonsterMoveBase(10 + i);
        }
        this.dntgThread = new DNTGThread(this);
        Thread T1 = new Thread(this.dntgThread);
        T1.start();
        NChatBean bean = new NChatBean();
        bean.setId(9);
        bean.setMessage("天庭和花果山互相发起了新一轮进攻");
        this.JG = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        this.moveMap = new HashMap<>();
    }
    
    public MapMonsterBean getBuild(int value) {
        DNTGCamp camp = null;
        if (value < 10) {
            camp = this.TT;
        }
        else {
            value -= 10;
            camp = this.HGS;
        }
        if (value == 0) {
            return camp.getDBY();
        }
        if (value == 2) {
            return camp.getT_S();
        }
        if (value == 3) {
            return camp.getT_Z();
        }
        if (value == 4) {
            return camp.getT_X();
        }
        return null;
    }
    
    public int getValue(MapMonsterBean bean) {
        if (bean.getRobotType() == 101) {
            return 0;
        }
        if (bean.getRobotType() == 105) {
            return 1;
        }
        if (bean.getRobotType() == 111) {
            return 10;
        }
        if (bean.getRobotType() == 115) {
            return 11;
        }
        return bean.getRobotType() - 100;
    }
    
    public DNTGRole getRole(BigDecimal roleId) {
        DNTGRole role = (DNTGRole)this.Camp_TT_Man.get(roleId);
        if (role == null) {
            role = (DNTGRole)this.Camp_HGS_Man.get(roleId);
        }
        return role;
    }
    
    public void addCampRole(DNTGRole role) {
        ++this.roleSize;
        if (role.getCamp() == 0) {
            this.Camp_TT_Man.put(role.getRoleId(), role);
        }
        else {
            this.Camp_HGS_Man.put(role.getRoleId(), role);
        }
    }
    
    public synchronized String addEnroll(ChannelHandlerContext ctxRole, LoginResult loginResult) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return "你不是队长";
        }
        DNTGRole[] roleids = new DNTGRole[teams.length];
        int I = -1;
        for (int i = 0; i < teams.length; ++i) {
            LoginResult login = null;
            if (i == 0) {
                login = loginResult;
            }
            else {
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                if (ctx != null) {
                    login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                }
            }
            if (login == null) {
                return teams[i] + "处于异常状态";
            }
            if ((int)login.getGrade() < 296) {
                return teams[i] + "未满2转100级";
            }
            DNTGRole role = this.getRole(login.getRole_id());
            if (role != null) {
                if (I == -1) {
                    I = role.getCamp();
                }
                else if (I != role.getCamp()) {
                    return "队伍中存在非同阵营的玩家";
                }
            }
            else {
                if (I != -1) {
                    return "队伍中存在非同阵营的玩家";
                }
                role = new DNTGRole(login.getRole_id(), login.getRolename());
            }
            roleids[i] = role;
        }
        DNTGCamp dntgCamp = null;
        if (I == -1) {
            I = ((this.Camp_TT_Man.size() > this.Camp_HGS_Man.size()) ? 1 : 0);
            if (I == 0) {
                dntgCamp = this.TT;
            }
            else {
                dntgCamp = this.HGS;
            }
        }
        String title = (I == 0) ? "天庭先锋" : "花果山先锋";
        for (int j = 0; j < roleids.length; ++j) {
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleids[j].getRoleName());
            LoginResult log = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
            if (log != null && !title.equals(log.getTitle())) {
                log.setTitle(title);
                SendMessage.sendMessageToSlef(ctx2, Agreement.getAgreement().TitleChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(log.getRoleShow())));
            }
            if (roleids[j].getCamp() == -1) {
                roleids[j].setCamp(I);
                this.addCampRole(roleids[j]);
                if (dntgCamp.getBuff() != null) {
                    RoleData roleData = (log != null) ? RolePool.getRoleData(log.getRole_id()) : null;
                    if (roleData != null) {
                        roleData.addLimit(dntgCamp.getBuff().getUseCardBean());
                        SendMessage.sendMessageToSlef(ctx2, dntgCamp.getBuff().getSendCard());
                    }
                }
            }
        }
        ChangeMapAction.ChangeMap((I == 0) ? this.HOME_TT : this.HOME_HGS, ctxRole);
        return null;
    }
    
    public Boos getBoos(int v) {
        Boos boos = (Boos)GameServer.boosesMap.get(v + "");
        if (boos == null) {
            boos = (Boos)MonsterUtil.booses.get(0);
        }
        return boos;
    }
    
    public int monsterSize(int value) {
        if (value == 0) {
            return 3 + ((this.roleSize > 40) ? (this.roleSize / 40) : 0);
        }
        if (value == 1) {
            return 1 + this.monsterSize(0) / 8;
        }
        if (value == 2 || value == 3) {
            return 1;
        }
        if (value == 4) {
            return 3 + ((this.roleSize > 50) ? (this.roleSize / 50) : 0);
        }
        return 1;
    }
    
    public void open(int CI) {
        if (this.I == 3) {
            if (CI % 3 != 0) {
                Boos boos = this.getBoos(297);
                MonsterUtil.refreshMonsters(boos, null, null, 132, 0, 1011, null);
            }
            return;
        }
        else {
            this.moveMap.clear();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < 8; ++i) {
                if (i % 4 != 2 || CI % 3 == 2) {
                    Robots robot = (Robots)GameServer.getAllRobot().get((i == 0) ? "907" : ((i == 1) ? "909" : ((i == 2) ? "910" : ((i == 3) ? "919" : ((i == 4) ? "912" : ((i == 5) ? "914" : ((i == 6) ? "915" : "920")))))));
                    if (robot != null) {
                        int robotId = robot.getRobotID();
                        int size = this.monsterSize(i % 4);
                        int hurt = 1 + ((i == 2 || i == 6) ? 1 : 0);
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(robot.getRobotID());
                        buffer.append("#");
                        buffer.append(robot.getRobotname());
                        buffer.append("#");
                        buffer.append(robot.getRobotskin());
                        buffer.append("#");
                        buffer.append((i < 4) ? 120 : 121);
                        int maxtime = 30;
                        for (int j = 0; j < 3; ++j) {
                            int path = (i < 4) ? (j + 12) : (j + 2);
                            MapMonsterBean bean = this.getBuild(path);
                            if (bean != null && bean.getHp().getHp() > 0L) {
                                MonsterMoveBase movePath = this.DNTGPaths[(i < 4) ? j : (j + 3)];
                                this.moveMap.put(Integer.valueOf(movePath.getBh()), movePath);
                                PathPoint point = (PathPoint)movePath.getPoints().get(0);
                                for (int k = 0; k < size; ++k) {
                                    MapMonsterBean Bean1 = new MapMonsterBean();
                                    Bean1.setX(Integer.valueOf(point.getX() + MonsterUtil.getPY()));
                                    Bean1.setY((long)(point.getY() + MonsterUtil.getPY()));
                                    Bean1.setRobotid(Integer.valueOf(robotId));
                                    Bean1.setRobotname(robot.getRobotname());
                                    Bean1.setRobotskin(robot.getRobotskin());
                                    Bean1.setRobotType((i < 4) ? 120 : 121);
                                    Bean1.setI(Integer.valueOf(MonsterUtil.getIncreasesum()));
                                    Bean1.setMap(Long.valueOf(3201L));
                                    Bean1.setMaxtime(maxtime);
                                    Bean1.setSX(1011);
                                    Bean1.setMove(new MonsterMove(movePath, -1000 * k, (int)Bean1.getX(), Bean1.getY()));
                                    this.monsterMap.add(new DNTGMonster(Bean1, (i < 4) ? 0 : 1, path, hurt));
                                    MonsterUtil.addEMonster(Bean1);
                                    MonsterUtil.monsterBuffer1(Bean1, buffer);
                                }
                            }
                        }
                    }
                }
            }
            if (this.moveMap.size() != 0) {
                StringBuffer moveBuffer = new StringBuffer();
                moveBuffer.append("M");
                for (MonsterMoveBase move : this.moveMap.values()) {
                    if (moveBuffer.length() > 1) {
                        moveBuffer.append("#");
                    }
                    moveBuffer.append(move.getMoveMsg());
                }
                moveBuffer.append("|");
                buffer = moveBuffer.append(buffer);
            }
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
            if (CI % 10 == 0) {
                SendMessage.sendMessageToAllRoles(this.JG);
            }
            return;
        }
    }
    
    public void move(long time) {
        StringBuffer buffer = null;
        StringBuffer buffer2 = null;
        for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
            DNTGMonster monster = (DNTGMonster)this.monsterMap.get(i);
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
                MapMonsterBean bean = this.getBuild(monster.getValue());
                if (bean != null && bean.getHp().getHp() > 0L) {
                    long hp = bean.getHp().getHp() - (long)monster.getHurt();
                    if (hp < 0L) {
                        hp = 0L;
                    }
                    bean.getHp().setHp(hp);
                    this.XGHPRecord(monster.getValue());
                }
            }
        }
        if (time % 3L == 0L) {
            int v = this.XGHPRecord(-1);
            if (v != 0) {
                for (int j = 0; j < 5; ++j) {
                    if ((v >>> j & 0x1) == 0x1) {
                        MapMonsterBean bean = this.getBuild(j);
                        if (bean == null) {
                            continue;
                        }
                        else {
                            if (buffer == null) {
                                buffer = new StringBuffer("M");
                            }
                            else {
                                buffer.append("#");
                            }
                            buffer.append(bean.getI());
                            buffer.append("^0^H");
                            buffer.append(bean.getHp().getHp());
                            buffer.append(",");
                            buffer.append(bean.getHp().getHpMax());
                            if (bean.getHp().getHp() <= 20L) {
                                NChatBean nChatBean = new NChatBean();
                                if (bean.getHp().getHp() == 0L) {
                                    nChatBean.setId(9);
                                    nChatBean.setMessage(((bean.getRobotType() == 101) ? "天庭大本营" : bean.getRobotname()) + "已被攻破");
                                }
                                else {
                                    nChatBean.setId(5);
                                    nChatBean.setMessage(((bean.getRobotType() == 101) ? "天庭大本营" : bean.getRobotname()) + "剩余血量:" + bean.getHp().getHp());
                                }
                                SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean)));
                            }
                        }
                    }
                    if ((v >>> j + 10 & 0x1) == 0x1) {
                        MapMonsterBean bean = this.getBuild(j + 10);
                        if (bean != null) {
                            if (buffer == null) {
                                buffer = new StringBuffer("M");
                            }
                            else {
                                buffer.append("#");
                            }
                            buffer.append(bean.getI());
                            buffer.append("^0^H");
                            buffer.append(bean.getHp().getHp());
                            buffer.append(",");
                            buffer.append(bean.getHp().getHpMax());
                            if (bean.getHp().getHp() <= 20L) {
                                NChatBean nChatBean = new NChatBean();
                                if (bean.getHp().getHp() == 0L) {
                                    nChatBean.setId(9);
                                    nChatBean.setMessage(((bean.getRobotType() == 111) ? "花果山大本营" : bean.getRobotname()) + "已被攻破");
                                }
                                else {
                                    nChatBean.setId(5);
                                    nChatBean.setMessage(((bean.getRobotType() == 111) ? "花果山大本营" : bean.getRobotname()) + "剩余血量:" + bean.getHp().getHp());
                                }
                                SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean)));
                            }
                        }
                    }
                }
                if (this.dntg_NV_Monster != null) {
                    if ((v >>> 20 & 0x1) == 0x1) {
                        MapMonsterBean bean2 = this.dntg_NV_Monster.getBean();
                        if (bean2 != null) {
                            if (buffer == null) {
                                buffer = new StringBuffer("M");
                            }
                            else {
                                buffer.append("#");
                            }
                            buffer.append(bean2.getI());
                            buffer.append("^0^H");
                            buffer.append(bean2.getHp().getHp());
                            buffer.append(",");
                            buffer.append(bean2.getHp().getHpMax());
                        }
                    }
                    if ((v >>> 21 & 0x1) == 0x1) {
                        if (buffer2 == null) {
                            buffer2 = new StringBuffer();
                            buffer2.append(1011);
                        }
                        buffer2.append("|");
                        buffer2.append(this.dntg_NV_Monster.getTTRanking().getRankingSting());
                    }
                    if ((v >>> 22 & 0x1) == 0x1) {
                        if (buffer2 == null) {
                            buffer2 = new StringBuffer();
                            buffer2.append(1011);
                        }
                        buffer2.append("|");
                        buffer2.append(this.dntg_NV_Monster.getHGSRanking().getRankingSting());
                    }
                }
                if (this.dntg_SG != null) {
                    if ((v >>> 25 & 0x1) == 0x1) {
                        if (buffer2 == null) {
                            buffer2 = new StringBuffer();
                            buffer2.append(1011);
                        }
                        buffer2.append("|S0");
                        buffer2.append(this.dntg_SG.getTT_GX());
                    }
                    if ((v >>> 26 & 0x1) == 0x1) {
                        if (buffer2 == null) {
                            buffer2 = new StringBuffer();
                            buffer2.append(1011);
                        }
                        buffer2.append("|S1");
                        buffer2.append(this.dntg_SG.getHGS_GX());
                    }
                }
                if ((v >>> 23 & 0x1) == 0x1) {
                    if (buffer2 == null) {
                        buffer2 = new StringBuffer();
                        buffer2.append(1011);
                    }
                    buffer2.append("|K0");
                    buffer2.append(this.TT.getKJValue());
                }
                if ((v >>> 24 & 0x1) == 0x1) {
                    if (buffer2 == null) {
                        buffer2 = new StringBuffer();
                        buffer2.append(1011);
                    }
                    buffer2.append("|K1");
                    buffer2.append(this.HGS.getKJValue());
                }
            }
        }
        if (buffer != null && buffer.length() != 0) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
        if (buffer2 != null && buffer2.length() != 0) {
            String msg = Agreement.getAgreement().sceneAgreement(buffer2.toString());
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
            SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg);
        }
    }
    
    public void upI(int ST) {
        this.I = ST;
    }
    
    public void isDNTG(long time) {
        if (this.TT.getBuff() != null && this.TT.getBuff().isTime()) {
            this.TT.setBuff(null);
        }
        if (this.HGS.getBuff() != null && this.HGS.getBuff().isTime()) {
            this.HGS.setBuff(null);
        }
        if (time < 10800L && this.TT.getDBY().getHp().getHp() > 0L && this.HGS.getDBY().getHp().getHp() > 0L) {
            return;
        }
        if (this.I == 4) {
            return;
        }
        this.I = 4;
        NChatBean chatBean = new NChatBean();
        if (this.TT.getDBY().getHp().getHp() > this.HGS.getDBY().getHp().getHp()) {
            this.addQJJF(this.Camp_TT_Man, 0.25);
            this.addQJJF(this.Camp_HGS_Man, 0.1);
            chatBean.setMessage("#c00E3E3本次#R大闹天宫#c00E3E3已经结束#23获胜方为#天庭#c00E3E3请退出场景后找#R日月灯芯#c00E3E3领取您的奖励#23");
        }
        else if (this.HGS.getDBY().getHp().getHp() > this.TT.getDBY().getHp().getHp()) {
            this.addQJJF(this.Camp_HGS_Man, 0.25);
            this.addQJJF(this.Camp_TT_Man, 0.1);
            chatBean.setMessage("#c00E3E3本次#R大闹天宫#c00E3E3已经结束#23获胜方为#Y花果山#c00E3E3请退出场景后找#R日月灯芯#c00E3E3领取您的奖励#23");
        }
        else {
            this.addQJJF(this.Camp_TT_Man, 0.15);
            this.addQJJF(this.Camp_HGS_Man, 0.15);
            chatBean.setMessage("#c00E3E3本次#R大闹天宫#c00E3E3已经结束#23#Y平局收场#c00E3E3请退出场景后找#R日月灯芯#c00E3E3领取您的奖励#23");
        }
        chatBean.setId(4);
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
        SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg);
    }
    
    public void removeMonsterBean() {
        StringBuffer buffer = new StringBuffer("M");
        this.TT.removeMapMonster(buffer);
        this.HGS.removeMapMonster(buffer);
        if (this.dntg_NV_Monster != null) {
            if (buffer.length() > 1) {
                buffer.append("#");
            }
            buffer.append(this.dntg_NV_Monster.getBean().getI());
            buffer.append("^2");
            MonsterUtil.removeMonster2(this.dntg_NV_Monster.getBean());
        }
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
    }
    
    public void activity(int state) {
        if (state == 0) {
            this.countDown = new DNTGCountDown(0, 30);
            StringBuffer buffer = new StringBuffer();
            buffer.append(1011);
            buffer.append("|D");
            this.countDown.toString(buffer);
            String msg = Agreement.getAgreement().sceneAgreement(buffer.toString());
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
            SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg);
        }
        else if (state == 1) {
            this.countDown = null;
            String msg2 = Agreement.getAgreement().sceneAgreement("1011|D");
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg2);
            SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg2);
            this.NVOpen();
        }
        else if (state == 2) {
            this.NVEnd();
        }
        else if (state == 3) {
            this.countDown = new DNTGCountDown(1, 30);
            StringBuffer buffer = new StringBuffer();
            buffer.append(1011);
            buffer.append("|D");
            this.countDown.toString(buffer);
            String msg = Agreement.getAgreement().sceneAgreement(buffer.toString());
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
            SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg);
        }
        else if (state == 4) {
            this.countDown = null;
            String msg2 = Agreement.getAgreement().sceneAgreement("1011|D");
            SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg2);
            SendMessage.sendMessageToMapRoles(Long.valueOf(3336L), msg2);
            this.SGOpen();
        }
        else if (state == 5) {
            this.SGEnd();
        }
    }
    
    public String getDialog(MapMonsterBean bean, LoginResult loginResult) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return null;
        }
        DNTGRole dntgRole = this.getRole(loginResult.getRole_id());
        if (dntgRole == null) {
            return null;
        }
        NPCDialog npcDialog = null;
        if (this.I == 0) {
            npcDialog = new NPCDialog();
            npcDialog.setMsg("活动准备阶段");
        }
        else if (this.I == 4) {
            npcDialog = new NPCDialog();
            npcDialog.setMsg("活动已结束");
            List<String> functions = new ArrayList<>();
            functions.add("我要回长安");
            npcDialog.setFunctions(functions);
        }
        else {
            int robotType = bean.getRobotType();
            if (robotType == 101 || robotType == 111) {
                if (robotType == ((dntgRole.getCamp() != 0) ? 101 : 111)) {
                    npcDialog = new NPCDialog();
                    List<String> functions2 = new ArrayList<>();
                    functions2.add("攻击");
                    npcDialog.setFunctions(functions2);
                }
            }
            else if ((robotType >= 102 && robotType <= 104) || (robotType >= 112 && robotType <= 114)) {
                npcDialog = new NPCDialog();
                List<String> functions2 = new ArrayList<>();
                if (bean.getHp() != null && bean.getHp().getHp() > 0L && ((robotType < 102 || robotType > 104) ? 1 : 0) != dntgRole.getCamp()) {
                    functions2.add("攻击");
                }
                else {
                    functions2.add("送我进去");
                    functions2.add("送我出去");
                }
                npcDialog.setFunctions(functions2);
            }
            else if (robotType == 130) {
                if (this.dntg_NV_Monster == null) {
                    return null;
                }
                npcDialog = new NPCDialog();
                List<String> functions2 = new ArrayList<>();
                functions2.add("攻击");
                npcDialog.setFunctions(functions2);
            }
            else if (robotType == 131) {
                npcDialog = new NPCDialog();
                List<String> functions2 = new ArrayList<>();
                functions2.add("送我到上古战场");
                npcDialog.setFunctions(functions2);
            }
        }
        if (npcDialog != null) {
            npcDialog.setType("M" + bean.getI());
            return Agreement.getAgreement().npcAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcDialog));
        }
        return null;
    }
    
    public String SGBX(LoginResult loginResult, String[] teams, int type) {
        DNTGRole dntgRole = this.getRole(loginResult.getRole_id());
        if (dntgRole == null) {
            return null;
        }
        if (dntgRole.getCamp() != ((type != 42) ? 1 : 0)) {
            return "我不是你们阵营的NPC";
        }
        MapMonsterBean bean = MonsterUtil.getFollowMonster(teams);
        if (bean == null || bean.getRobotType() != 132) {
            return "你没有上古宝箱";
        }
        MonsterUtil.removeMonster2(bean);
        if (this.dntg_SG != null) {
            DNTGAward dntgAward = GameServer.getAllDntg(11);
            if (dntgAward != null) {
                for (int i = 0; i < teams.length; ++i) {
                    ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                    LoginResult log = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                    DNTGRole role = (log != null) ? this.getRole(log.getRole_id()) : null;
                    if (role != null) {
                        AssetUpdate assetUpdate = this.upDNTGAward(dntgAward, role, null, i);
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                }
            }
            if (!this.dntg_SG.isEnd()) {
                this.SGEnd();
            }
        }
        return null;
    }
    
    public String isJG(String[] teams) {
        long time = System.currentTimeMillis();
        DNTGRole[] roleids = new DNTGRole[teams.length];
        for (int i = 0; i < teams.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            DNTGRole role = (login != null) ? this.getRole(login.getRole_id()) : null;
            String value = (role != null) ? role.isTime(time) : null;
            if (value != null) {
                return value;
            }
            roleids[i] = role;
        }
        for (int i = 0; i < roleids.length; ++i) {
            if (roleids[i] != null) {
                roleids[i].setTime(time);
            }
        }
        return null;
    }
    
    public void Function(MapMonsterBean bean, LoginResult loginResult, String msg) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return;
        }
        DNTGRole dntgRole = this.getRole(loginResult.getRole_id());
        if (dntgRole == null) {
            return;
        }
        int robotType = bean.getRobotType();
        String prompt = null;
        if (msg.equals(this.functions[0])) {
            if (robotType == 101 || robotType == 111 || robotType == 120 || robotType == 121) {
                if (dntgRole.getCamp() != ((robotType != 101 && robotType != 120) ? 1 : 0)) {
                    if (robotType == 101 && this.TT.getBoosXS() == 3.0) {
                        prompt = "最少需要破一个箭塔才能攻击首领";
                    }
                    else if (robotType == 111 && this.HGS.getBoosXS() == 3.0) {
                        prompt = "最少需要破一个箭塔才能攻击首领";
                    }
                    prompt = this.isJG(teams);
                    if (prompt == null) {
                        BattleThreadPool.addDNTGBattle(loginResult, teams, bean, this.getMonsterXS(bean));
                    }
                }
                else {
                    prompt = "不可攻击同阵营";
                }
            }
            else if ((robotType >= 102 && robotType <= 104) || (robotType >= 112 && robotType <= 114)) {
                if (bean.getHp() == null || bean.getHp().getHp() <= 0L) {
                    prompt = "该箭塔已被攻破";
                }
                else if (((robotType < 102 || robotType > 104) ? 1 : 0) == dntgRole.getCamp()) {
                    prompt = "不可攻击同阵营";
                }
                else {
                    prompt = this.isJG(teams);
                    if (prompt == null) {
                        BattleThreadPool.addDNTGBattle(loginResult, teams, bean, this.getMonsterXS(bean));
                    }
                }
            }
            else if (robotType == 130) {
                if (this.dntg_NV_Monster == null || this.dntg_NV_Monster.getBean() == null || this.dntg_NV_Monster.getBean().getHp().getHp() <= 0L) {
                    prompt = "活动已经结束";
                }
                else {
                    prompt = this.isJG(teams);
                    if (prompt == null) {
                        BattleThreadPool.addDNTGBattle(loginResult, teams, bean, 1.0);
                    }
                }
            }
        }
        else if (!msg.equals(this.functions[1])) {
            if (msg.equals(this.functions[2]) || msg.equals(this.functions[3])) {
                int value = this.getValue(bean);
                boolean is = msg.equals(this.functions[2]);
                Door door = null;
                if (value == 2) {
                    door = GameServer.getDoor(is ? 509 : 508);
                }
                else if (value == 3) {
                    door = GameServer.getDoor(is ? 511 : 510);
                }
                else if (value == 4) {
                    door = GameServer.getDoor(is ? 513 : 512);
                }
                else if (value == 12) {
                    door = GameServer.getDoor(is ? 521 : 520);
                }
                else if (value == 13) {
                    door = GameServer.getDoor(is ? 519 : 518);
                }
                else if (value == 14) {
                    door = GameServer.getDoor(is ? 517 : 516);
                }
                if (door != null) {
                    ChangeMapBean changeMapBean = new ChangeMapBean();
                    changeMapBean.setMapid(door.getDoormap());
                    String[] vs = door.getDoorpoint().split("\\|");
                    changeMapBean.setMapx(Integer.parseInt(vs[0]));
                    changeMapBean.setMapy(Integer.parseInt(vs[1]));
                    ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                    if (ctx != null) {
                        ChangeMapAction.ChangeMap(changeMapBean, ctx);
                    }
                }
            }
            else if (msg.equals(this.functions[4])) {
                ChangeMapBean changeMapBean2 = new ChangeMapBean();
                changeMapBean2.setMapid("1207");
                changeMapBean2.setMapx(4294);
                changeMapBean2.setMapy(2887);
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                if (ctx2 != null) {
                    ChangeMapAction.ChangeMap(changeMapBean2, ctx2);
                }
            }
        }
        if (prompt != null) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement(prompt));
        }
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        DNTGRole dntgRole = this.getRole(loginResult.getRole_id());
        if (dntgRole == null) {
            return 0;
        }
        if (v == 0 || v == 1) {
            if (this.I == 4) {
                return 0;
            }
            if (bean != null) {
                DNTGAward award = this.getDNTGAward(bean);
                if (award != null) {
                    battleEnd.setAssetUpdate(this.upDNTGAward(award, dntgRole, battleEnd.getAssetUpdate(), v));
                    if (this.dntg_SG != null && !this.dntg_SG.isEnd()) {
                        this.SGEnd();
                    }
                }
            }
            else if (this.dntg_SG != null) {
                DNTGAward award = GameServer.getAllDntg(12);
                if (award != null) {
                    battleEnd.setAssetUpdate(this.upDNTGAward(award, dntgRole, battleEnd.getAssetUpdate(), v));
                    if (!this.dntg_SG.isEnd()) {
                        this.SGEnd();
                    }
                }
            }
        }
        else {
            dntgRole.setTime(System.currentTimeMillis());
            if (v == 2) {
                if ((long)loginResult.getMapid() == 3201L) {
                    return (dntgRole.getCamp() == 0) ? 522 : 523;
                }
                if ((long)loginResult.getMapid() == 3336L) {
                    return (dntgRole.getCamp() == 0) ? 524 : 525;
                }
            }
        }
        return 0;
    }
    
    public AssetUpdate upDNTGAward(DNTGAward award, DNTGRole dntgRole, AssetUpdate assetUpdate, int v) {
        if (assetUpdate == null) {
            assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        }
        if (award.getAddKJValue() != 0) {
            if (v == 0) {
                if (dntgRole.getCamp() == 0) {
                    this.TTKJValue(award.getAddKJValue());
                }
                else {
                    this.HGSKJValue(award.getAddKJValue());
                }
            }
            assetUpdate.upmsg("为乙方阵营添加" + award.getAddKJValue() + "点科技值");
        }
        if (award.getStealKJValue() != 0) {
            if (v == 0) {
                if (dntgRole.getCamp() == 0) {
                    this.HGSKJValue(-award.getStealKJValue());
                }
                else {
                    this.TTKJValue(-award.getStealKJValue());
                }
            }
            assetUpdate.upmsg("偷取对方阵营" + award.getAddKJValue() + "点科技值");
        }
        if (award.getAddGX() != 0) {
            if (v == 0) {
                if (dntgRole.getCamp() == 0) {
                    this.TTGXValue(award.getStealKJValue());
                }
                else {
                    this.HGSGXValue(award.getStealKJValue());
                }
            }
            assetUpdate.upmsg("为乙方阵营添加" + award.getStealKJValue() + "点功勋");
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(dntgRole.getRoleName());
        LoginResult log = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
        RoleData roleData = (log != null) ? RolePool.getRoleData(log.getRole_id()) : null;
        UseCardBean limit = (roleData != null) ? roleData.getLimit("DNTG") : null;
        StringBuffer buffer = null;
        if (award.getAddJ() != 0) {
            int add = award.getAddJ();
            if (limit != null && limit.getValue().indexOf("积分") != -1) {
                add = (int)((double)add * 1.1);
            }
            dntgRole.addDNJF(add);
            assetUpdate.upmsg("获取了" + add + "积分");
            if (buffer == null) {
                buffer = new StringBuffer();
                buffer.append(1011);
            }
            buffer.append("|J");
            buffer.append(dntgRole.getDNJF());
        }
        if (award.getAddM() != 0) {
            int add = award.getAddM();
            if (limit != null && limit.getValue().indexOf("金币") != -1) {
                add = (int)((double)add * 1.2);
            }
            dntgRole.addDNJB(add);
            assetUpdate.upmsg("获取了" + add + "金币");
            if (buffer == null) {
                buffer = new StringBuffer();
                buffer.append(1011);
            }
            buffer.append("|M");
            buffer.append(dntgRole.getDNJB());
        }
        if (buffer != null) {
            assetUpdate.setSceneMsg(buffer.toString());
        }
        return assetUpdate;
    }
    
    public DNTGAward getDNTGAward(MapMonsterBean bean) {
        if (bean.getRobotType() == 120 || bean.getRobotType() == 121) {
            if ((int)bean.getRobotid() == 910 || (int)bean.getRobotid() == 915) {
                return GameServer.getAllDntg(5);
            }
            if ((int)bean.getRobotid() == 909 || (int)bean.getRobotid() == 914) {
                return GameServer.getAllDntg(4);
            }
            return GameServer.getAllDntg(3);
        }
        else {
            if (bean.getRobotType() == 130) {
                return GameServer.getAllDntg(7);
            }
            if (bean.getRobotType() == 132) {
                return GameServer.getAllDntg(10);
            }
            if (bean.getRobotType() == 101 || bean.getRobotType() == 111) {
                return GameServer.getAllDntg(6);
            }
            if (bean.getRobotType() >= 102 && bean.getRobotType() <= 115) {
                return GameServer.getAllDntg(1);
            }
            return null;
        }
    }
    
    public synchronized void TTKJValue(int value) {
        this.TT.addKJValue(value);
        this.XGHPRecord(23);
    }
    
    public synchronized void HGSKJValue(int value) {
        this.HGS.addKJValue(value);
        this.XGHPRecord(24);
    }
    
    public synchronized void TTGXValue(int value) {
        if (this.dntg_SG != null) {
            this.dntg_SG.setTT_GX(this.dntg_SG.getTT_GX() + value);
            this.XGHPRecord(25);
        }
    }
    
    public synchronized void HGSGXValue(int value) {
        if (this.dntg_SG != null) {
            this.dntg_SG.setHGS_GX(this.dntg_SG.getHGS_GX() + value);
            this.XGHPRecord(26);
        }
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        MapMonsterBean bean = battleData.getMonsterBean();
        if (type == 2) {
            if (bean.getRobotType() == 120 || bean.getRobotType() == 121) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^2");
                for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
                    DNTGMonster monster = (DNTGMonster)this.monsterMap.get(i);
                    if ((int)monster.getBean().getI() == (int)bean.getI()) {
                        this.monsterMap.remove(i);
                        MonsterUtil.removeMonster2(bean);
                    }
                }
            }
            else if (bean.getFollow() != null) {
                if (bean.getFollow().getTime() == 0L) {
                    bean.getFollow().setTime(1L);
                }
                else {
                    bean.getFollow().setTime(System.currentTimeMillis());
                }
                bean.setType(0);
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[0]);
                LoginResult log2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
                if (log2 != null) {
                    bean.getFollow().setFollowID(log2.getRole_id());
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^");
                    buffer.append(bean.getType());
                    buffer.append("^G");
                    buffer.append(log2.getRole_id());
                    StringBuffer bufferTwo = new StringBuffer();
                    bufferTwo.append("#R");
                    bufferTwo.append(log2.getRolename());
                    bufferTwo.append("#Y在");
                    bufferTwo.append(GameServer.getMapName(log2.getMapid().toString()));
                    bufferTwo.append("(");
                    bufferTwo.append((long)log2.getX() / 20L);
                    bufferTwo.append(",");
                    bufferTwo.append((long)log2.getY() / 20L);
                    bufferTwo.append(")抢到#R");
                    bufferTwo.append(bean.getRobotname());
                    DNTGRole dntgRole = this.getRole(log2.getRole_id());
                    if (dntgRole != null) {
                        bufferTwo.append(",正在前往");
                        bufferTwo.append((dntgRole.getCamp() == 0) ? "天庭" : "花果山");
                        bufferTwo.append("营地邀功");
                    }
                    NChatBean chatBean = new NChatBean();
                    chatBean.setId(9);
                    chatBean.setMessage(bufferTwo.toString());
                    SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
                }
            }
            else if (bean.getRobotType() == 130) {
                if (this.dntg_NV_Monster != null && this.dntg_NV_Monster.getBean().getHp().getHp() > 0L) {
                    long hp = bean.getHp().getHp() - 1L;
                    if (hp < 0L) {
                        hp = 0L;
                    }
                    bean.getHp().setHp(hp);
                    this.XGHPRecord(20);
                    ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[0]);
                    LoginResult log3 = (ctx3 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx3)) : null;
                    if (log3 != null) {
                        boolean is = false;
                        DNTGRole dntgRole2 = this.getRole(log3.getRole_id());
                        if (dntgRole2 != null) {
                            DNTG_NV_Ranking ranking = (dntgRole2.getCamp() == 0) ? this.dntg_NV_Monster.getTTRanking() : this.dntg_NV_Monster.getHGSRanking();
                            is = ranking.upRanking(dntgRole2);
                            StringBuffer bufferTwo2 = new StringBuffer();
                            bufferTwo2.append(1011);
                            bufferTwo2.append("|N2");
                            bufferTwo2.append(dntgRole2.getNVNum());
                            bufferTwo2.append("$");
                            if (is) {
                                bufferTwo2.append(ranking.getPlace(dntgRole2));
                                this.XGHPRecord((dntgRole2.getCamp() == 0) ? 21 : 22);
                            }
                            else {
                                bufferTwo2.append("0");
                            }
                            SendMessage.sendMessageToSlef(ctx3, Agreement.getAgreement().sceneAgreement(bufferTwo2.toString()));
                        }
                    }
                    if (hp <= 0L) {
                        StringBuffer bufferTwo3 = new StringBuffer();
                        bufferTwo3.append(bean.getRobotname());
                        bufferTwo3.append("已被#R");
                        bufferTwo3.append(teams[0]);
                        bufferTwo3.append("#Y所带领的队伍完成最后一击");
                        NChatBean chatBean2 = new NChatBean();
                        chatBean2.setId(9);
                        chatBean2.setMessage(bufferTwo3.toString());
                        SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean2)));
                        DNTGAward award = GameServer.getAllDntg(8);
                        if (award != null) {
                            for (int j = 0; j < teams.length; ++j) {
                                ChannelHandlerContext ctx4 = (j == 0) ? ctx3 : ((ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[j]));
                                if (ctx4 != null) {
                                    LoginResult log4 = (j == 0) ? log3 : ((LoginResult)GameServer.getAllLoginRole().get(ctx4));
                                    if (log4 != null) {
                                        DNTGRole dntgRole3 = this.getRole(log4.getRole_id());
                                        if (dntgRole3 != null) {
                                            AssetUpdate assetUpdate = this.upDNTGAward(award, dntgRole3, null, j);
                                            SendMessage.sendMessageToSlef(ctx4, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                        }
                                    }
                                }
                            }
                        }
                        this.NVEnd();
                    }
                    else {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("M");
                        buffer.append(bean.getI());
                        buffer.append("^");
                        buffer.append(bean.getType());
                        buffer.append("^H");
                        buffer.append(bean.getHp().getHp());
                        buffer.append(",");
                        buffer.append(bean.getHp().getHpMax());
                    }
                }
            }
            else if (bean.getRobotType() >= 101 && bean.getRobotType() <= 115) {
                if (bean.getHp().getHp() > 0L) {
                    int hurt = 1;
                    ChannelHandlerContext ctx5 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[0]);
                    LoginResult log5 = (ctx5 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx5)) : null;
                    RoleData roleData = (log5 != null) ? RolePool.getRoleData(log5.getRole_id()) : null;
                    UseCardBean limit = (roleData != null) ? roleData.getLimit("DNTG") : null;
                    if (limit != null && limit.getValue().indexOf("建筑物") != -1) {
                        hurt += 2;
                    }
                    long hp2 = bean.getHp().getHp() - (long)hurt;
                    if (hp2 < 0L) {
                        hp2 = 0L;
                    }
                    bean.getHp().setHp(hp2);
                    int value = this.getValue(bean);
                    this.XGHPRecord(value);
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^");
                    buffer.append(bean.getType());
                    buffer.append("^H");
                    buffer.append(bean.getHp().getHp());
                    buffer.append(",");
                    buffer.append(bean.getHp().getHpMax());
                }
            }
            else {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^2");
                MonsterUtil.removeMonster2(bean);
            }
        }
        else if (type == 1) {
            if (bean.getHp() == null || !bean.getHp().isMuch()) {
                bean.setType(type);
                if (bean.getMove() != null) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^1");
                }
            }
        }
        else if (type == 0 && (bean.getHp() == null || !bean.getHp().isMuch())) {
            bean.setType(type);
            if (bean.getMove() != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^0");
            }
        }
        return null;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        if (3201L == mapId) {
            this.TT.getMapMonster(buffer);
            this.HGS.getMapMonster(buffer);
            if (this.dntg_NV_Monster != null) {
                MapMonsterBean bean = this.dntg_NV_Monster.getBean();
                moveMap = MonsterUtil.monsterBuffer(bean, buffer, moveMap);
            }
            for (int i = this.monsterMap.size() - 1; i >= 0; --i) {
                DNTGMonster dntgMonster = (DNTGMonster)this.monsterMap.get(i);
                MapMonsterBean bean2 = dntgMonster.getBean();
                moveMap = MonsterUtil.monsterBuffer(bean2, buffer, moveMap);
            }
        }
        return moveMap;
    }
    
    public synchronized int XGHPRecord(int v) {
        if (v == -1) {
            v = this.HPRecord;
            this.HPRecord = 0;
            return v;
        }
        return this.HPRecord |= 1 << v;
    }
    
    @Override
    public boolean isEnd() {
        return this.I != 4;
    }
    
    @Override
    public boolean isTime(long time) {
        return false;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        DNTGRole dntgRole = this.getRole(loginResult.getRole_id());
        if (dntgRole == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(1011);
        if (oldMapId != 3201L && oldMapId != 3336L) {
            buffer.append("|C");
            buffer.append(dntgRole.getCamp());
            buffer.append("|M");
            buffer.append(dntgRole.getDNJB());
            buffer.append("|J");
            buffer.append(dntgRole.getDNJF());
            buffer.append("|K0");
            buffer.append(this.TT.getKJValue());
            buffer.append("|K1");
            buffer.append(this.HGS.getKJValue());
            if (this.countDown != null) {
                buffer.append("|D");
                this.countDown.toString(buffer);
            }
        }
        if (mapId == 3201L) {
            if (oldMapId == 3336L) {
                this.clearMap(buffer, 1);
            }
            if (this.dntg_NV_Monster != null) {
                this.dntg_NV_Monster.toString(dntgRole, buffer);
            }
        }
        else if (mapId == 3336L) {
            if (oldMapId == 3201L) {
                this.clearMap(buffer, 0);
            }
            if (this.dntg_SG != null) {
                this.dntg_SG.toString(buffer);
            }
        }
        return buffer.toString();
    }
    
    public void clearMap(StringBuffer buffer, int type) {
        if (type == 0) {
            buffer.append("|N");
        }
        else if (type == 1) {
            buffer.append("|S");
        }
    }
    
    public void learnSLJC(LoginResult login, Skill skill) {
        if (!this.isEnd()) {
            return;
        }
        DNTGRole dntgRole = this.getRole(login.getRole_id());
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (dntgRole == null || roleData == null) {
            return;
        }
        String msg = dntgRole.upSLJC(skill, (dntgRole.getCamp() == 0) ? this.TT.getKJValue() : this.HGS.getKJValue());
        if (msg != null) {
            SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement(msg));
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(1011);
        buffer.append("|M");
        buffer.append(dntgRole.getDNJB());
        buffer.append("|L");
        buffer.append(dntgRole.getSLJC());
        SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().sceneAgreement(buffer.toString()));
    }
    
    public synchronized void NVOpen() {
        if (this.I == 4) {
            return;
        }
        this.I = 2;
        this.dntg_NV_Monster = new DNTG_NV_Monster();
        MapMonsterBean bean = this.dntg_NV_Monster.getBean();
        String msg = Agreement.getAgreement().sceneAgreement("1011|N20$0");
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.getRobotid());
        buffer.append("#");
        buffer.append(bean.getRobotname());
        buffer.append("#");
        buffer.append(bean.getRobotskin());
        buffer.append("#");
        buffer.append(bean.getRobotType());
        MonsterUtil.monsterBuffer1(bean, buffer);
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
        NChatBean chatBean = new NChatBean();
        chatBean.setId(9);
        chatBean.setMessage("女武神活动开启了");
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
    }
    
    public synchronized void NVEnd() {
        if (this.dntg_NV_Monster == null) {
            return;
        }
        if (this.I == 4) {
            return;
        }
        int end = this.dntg_NV_Monster.end();
        NChatBean chatBean = new NChatBean();
        chatBean.setId(9);
        StringBuffer buffer = new StringBuffer();
        buffer.append("女武神试炼结束,");
        ConcurrentHashMap<BigDecimal, DNTGRole> map = null;
        DNTGBuff buff = null;
        String[] teams = null;
        if (end == -1) {
            buffer.append("平局");
        }
        else if (end == 0) {
            buff = new DNTGBuff(Battlefield.random.nextInt(3), 60L);
            this.TT.setBuff(buff);
            map = this.Camp_TT_Man;
            buffer.append("获胜方:天宫,获得大闹先锋BUFF:");
            buffer.append(buff.toString());
        }
        else if (end == 1) {
            buff = new DNTGBuff(Battlefield.random.nextInt(3), 60L);
            this.HGS.setBuff(buff);
            map = this.Camp_HGS_Man;
            buffer.append("获胜方:花果山,获得大闹先锋BUFF:");
            buffer.append(buff.toString());
        }
        DNTGRole TTRole = this.dntg_NV_Monster.getTTRanking().getOne();
        if (TTRole != null) {
            buffer.append(",天庭击杀榜第一队伍:#R");
            buffer.append(TTRole.getRoleName());
            buffer.append("#Y,获得大闹先锋统帅BUFF");
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(TTRole.getRoleName());
            LoginResult log = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (log != null) {
                String[] team = log.getTeam().split("\\|");
                for (int i = 0; i < team.length; ++i) {
                    ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team[i]);
                    log = ((ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null);
                    RoleData roleData = (log != null) ? RolePool.getRoleData(log.getRole_id()) : null;
                    if (roleData != null) {
                        roleData.addLimit(buff.getUseCardBean());
                        SendMessage.sendMessageToSlef(ctx, buff.getSendCard());
                    }
                }
                if (end == 0) {
                    teams = team;
                }
            }
        }
        DNTGRole HGSRole = this.dntg_NV_Monster.getHGSRanking().getOne();
        if (HGSRole != null) {
            buffer.append(",花果山击杀榜第一队伍:#R");
            buffer.append(HGSRole.getRoleName());
            buffer.append("#Y,获得大闹先锋统帅BUFF");
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(HGSRole.getRoleName());
            LoginResult log2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
            if (log2 != null) {
                String[] team2 = log2.getTeam().split("\\|");
                for (int j = 0; j < team2.length; ++j) {
                    ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team2[j]);
                    log2 = ((ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null);
                    RoleData roleData2 = (log2 != null) ? RolePool.getRoleData(log2.getRole_id()) : null;
                    if (roleData2 != null) {
                        roleData2.addLimit(buff.getUseCardBean());
                        SendMessage.sendMessageToSlef(ctx2, buff.getSendCard());
                    }
                }
                if (end == 1) {
                    teams = team2;
                }
            }
        }
        chatBean.setMessage(buffer.toString());
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
        if (buff != null && map != null) {
        	LOOP:
            for (Map.Entry<BigDecimal, DNTGRole> entrys : map.entrySet()) {
                DNTGRole dntgRole = (DNTGRole)entrys.getValue();
                if (teams != null) {
                    int j = 0;
                    while (j < teams.length) {
                        if (teams[j].equals(dntgRole.getRoleName())) {
                            continue LOOP;
                        }
                        else {
                            ++j;
                        }
                    }
                }
                ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(dntgRole.getRoleName());
                LoginResult log3 = (ctx3 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx3)) : null;
                RoleData roleData3 = (log3 != null) ? RolePool.getRoleData(log3.getRole_id()) : null;
                if (roleData3 != null) {
                    roleData3.addLimit(buff.getUseCardBean());
                    SendMessage.sendMessageToSlef(ctx3, buff.getSendCard());
                }
            }
        }
        this.I = 1;
        MonsterUtil.removeMonster2(this.dntg_NV_Monster.getBean());
        SendMessage.sendMessageToMapRoles(this.dntg_NV_Monster.getBean().getMap(), Agreement.getAgreement().battleStateAgreement("M" + this.dntg_NV_Monster.getBean().getI() + "^2"));
        this.dntg_NV_Monster = null;
        String msg = Agreement.getAgreement().sceneAgreement("1011|N");
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
    }
    
    public synchronized void SGOpen() {
        if (this.I == 4) {
            return;
        }
        this.dntg_SG = new DNTG_SG();
        NChatBean chatBean = new NChatBean();
        chatBean.setId(9);
        chatBean.setMessage("上古试炼开启了,请玩家前往各自营地通过上古传送门进入上古战场,上古试炼开启期间主战场静止");
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
        this.I = 3;
    }
    
    public synchronized void SGEnd() {
        if (this.dntg_SG == null) {
            return;
        }
        if (this.I == 4) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("上古试炼结束,");
        ConcurrentHashMap<BigDecimal, DNTGRole> map = null;
        double xs = 0.0;
        int end = this.dntg_SG.getEnd();
        if (end == -1) {
            buffer.append("平局");
        }
        else if (end == 0) {
            this.TTKJValue(1000);
            map = this.Camp_TT_Man;
            buffer.append("获胜方:天宫,奖励当前积分加百分之10科技值加1000");
            xs = 0.1;
        }
        else if (end == 1) {
            this.HGSKJValue(1000);
            map = this.Camp_HGS_Man;
            buffer.append("获胜方:花果山,奖励当前积分加百分之10科技值加1000");
            xs = 0.1;
        }
        if (map != null && xs != 0.0) {
            this.addQJJF(map, xs);
        }
        NChatBean chatBean = new NChatBean();
        chatBean.setId(9);
        chatBean.setMessage(buffer.toString());
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
        this.I = 1;
        this.dntg_SG = null;
        String msg = Agreement.getAgreement().sceneAgreement("1011|S");
        SendMessage.sendMessageToMapRoles(Long.valueOf(3201L), msg);
    }
    
    public void addQJJF(ConcurrentHashMap<BigDecimal, DNTGRole> map, double xs) {
        for (Map.Entry<BigDecimal, DNTGRole> entrys : map.entrySet()) {
            DNTGRole dntgRole = (DNTGRole)entrys.getValue();
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(dntgRole.getRoleName());
            LoginResult log = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (log != null) {
                int add = (int)((double)dntgRole.getDNJF() * xs);
                if (add != 0) {
                    if (add > 100000) {
                        add = 100000;
                    }
                    dntgRole.addDNJF(add);
                    if ((long)log.getMapid() == 3201L || (long)log.getMapid() == 3336L) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().sceneAgreement("1011|J" + dntgRole.getDNJF()));
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
        }
    }
    
    public int getI() {
        return this.I;
    }
    
    public double getMonsterXS(MapMonsterBean bean) {
        double xs = 1.0;
        if (bean.getRobotType() == 120 || (bean.getRobotType() >= 101 && bean.getRobotType() <= 105)) {
            int kjv = this.TT.getKJValue() / 80;
            if (kjv >= 300) {
                kjv = 300;
            }
            xs += (double)kjv * 0.01;
            if (bean.getRobotType() == 101) {
                xs += this.TT.getBoosXS();
            }
        }
        else if (bean.getRobotType() == 121 || (bean.getRobotType() >= 111 && bean.getRobotType() <= 115)) {
            int kjv = this.HGS.getKJValue() / 80;
            if (kjv >= 300) {
                kjv = 300;
            }
            xs += (double)kjv * 0.01;
            if (bean.getRobotType() == 111) {
                xs += this.HGS.getBoosXS();
            }
        }
        return xs;
    }
}
