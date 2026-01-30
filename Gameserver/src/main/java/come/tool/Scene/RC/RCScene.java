package come.tool.Scene.RC;

import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import come.tool.Battle.RewardLimit;
import come.tool.Battle.BattleData;
import come.tool.Role.RoleData;
import come.tool.Battle.BattleThreadPool;
import come.tool.Role.RolePool;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;
import org.come.model.Boos;
import org.come.model.Robots;
import org.come.until.GsonUtil;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import org.come.server.GameServer;
import org.come.model.TaskData;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Scene.Scene;

public class RCScene implements Scene
{
    private ConcurrentHashMap<String, RCRole> rcMap;
    private TaskData task;
    private BBRecord bbRecord;
    
    public RCScene() {
        this.rcMap = new ConcurrentHashMap<>();
        this.task = GameServer.getTaskName("混沌幻境");
        this.bbRecord = null;
        String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "bbRecord.txt");
        if (text == null || text.equals("")) {
            this.bbRecord = new BBRecord();
        }
        else {
            this.bbRecord = (BBRecord)GsonUtil.getGsonUtil().getgson().fromJson(text, BBRecord.class);
        }
        this.bbRecord.setBBrobots((Robots)GameServer.getAllRobot().get("905"));
        this.bbRecord.setBBboos((Boos)GameServer.boosesMap.get("290"));
    }
    
    public RCRole getRole(LoginResult login) {
        RCRole rcRole = (RCRole)this.rcMap.get(login.getRolename());
        if (rcRole == null) {
            rcRole = new RCRole(login.getRole_id(), login.getRolename());
            this.rcMap.put(rcRole.getRole(), rcRole);
        }
        return rcRole;
    }
    
    public RCRole getRole(String role) {
        return (RCRole)this.rcMap.get(role);
    }
    
    public String addNo1(LoginResult loginResult) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return "你不是队长";
        }
        RCRole[] rcRoles = new RCRole[teams.length];
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
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            if (roleData == null) {
                return teams[i] + "处于异常状态";
            }
            if (roleData.getTaskWC(this.task.getTaskSetID()) <= 0) {
                return teams[i] + "还未完成前置任务:混沌幻境";
            }
            RCRole role = this.getRole(login);
            if (role.getState() == 1) {
                return teams[i] + "处于挑战状态中";
            }
            if (role.getState() == -1 || role.getAdvance() > 0) {
                return teams[i] + "已经挑战过了";
            }
            rcRoles[i] = role;
        }
        if (BattleThreadPool.RCFB(teams, this.getRobots(1), 1)) {
            for (int i = 0; i < rcRoles.length; ++i) {
                rcRoles[i].setState(1);
            }
        }
        return null;
    }
    
    public String addNo2(LoginResult loginResult) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return "你不是队长";
        }
        RCRole[] rcRoles = new RCRole[teams.length];
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
            RCRole role = this.getRole(login);
            if (role.isBB()) {
                return teams[i] + "已经挑战过了";
            }
            rcRoles[i] = role;
        }
        if (BattleThreadPool.BBFB(teams, this.bbRecord.getBBrobots(), 0)) {
            for (int i = 0; i < rcRoles.length; ++i) {
                rcRoles[i].setBB(true);
            }
        }
        return null;
    }
    
    public String getBBMsg() {
        return this.bbRecord.getMsg();
    }
    
    public Robots getRobots(int advance) {
        if (advance <= 3) {
            return (Robots)GameServer.getAllRobot().get(894 + advance + "");
        }
        return (Robots)GameServer.getAllRobot().get("301");
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        if (battleData.getMonsterBean().getType() == 0) {
            this.JSJS(teams, type);
        }
        else if (battleData.getMonsterBean().getType() == 1) {
            this.BBFB(teams, type, (int)battleData.getMonsterBean().getI());
        }
        return null;
    }
    
    public void BBFB(String[] names, int type, int i) {
        if (type == 2) {
            ++i;
            this.bbRecord.UPRecord(i, names);
            BattleThreadPool.BBFB(names, this.bbRecord.getBBrobots(), i);
        }
        else if (i > 0) {
            String JF = this.bbRecord.getReward(i);
            RewardLimit.rewardS(names, JF);
        }
    }
    
    public void JSJS(String[] names, int type) {
        boolean is = type == 2;
        int advance = 0;
        for (int i = 0; i < names.length; ++i) {
            RCRole rcRole = this.getRole(names[i]);
            if (rcRole != null) {
                if (type == 2) {
                    rcRole.setAdvance(rcRole.getAdvance() + 1);
                    if (rcRole.getAdvance() == 9) {
                        rcRole.setState(0);
                    }
                    if (is) {
                        if (i == 0) {
                            advance = rcRole.getAdvance();
                        }
                        else if (is && advance != rcRole.getAdvance()) {
                            is = false;
                        }
                    }
                }
                else {
                    rcRole.setState(-1);
                }
            }
        }
        if (is && advance <= 8) {
            BattleThreadPool.RCFB(names, this.getRobots(advance + 1), advance + 1);
        }
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return moveMap;
    }
    
    @Override
    public boolean isEnd() {
        return false;
    }
    
    @Override
    public boolean isTime(long time) {
        return false;
    }
    
    public void clean() {
        this.rcMap.clear();
    }
    
    public BBBrush BBOpen() {
        BBBrush bbBrush = new BBBrush();
        int size = this.bbRecord.getBBNum() / 1000;
        ++size;
        bbBrush.setZ(size);
        bbBrush.setBoos(this.bbRecord.getBBboos());
        this.bbRecord.setBBNum(0);
        this.bbRecord.setSize(0);
        this.bbRecord.setTeams((String[])null);
        return bbBrush;
    }
    
    public BBRecord getBbRecord() {
        return this.bbRecord;
    }
    
    public void setBbRecord(BBRecord bbRecord) {
        this.bbRecord = bbRecord;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
}
