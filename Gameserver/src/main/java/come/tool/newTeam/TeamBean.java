package come.tool.newTeam;

import come.tool.Role.RoleData;
import come.tool.newTask.TaskRecord;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.teamArena.TeamArenaUtil;
import come.tool.teamArena.LadderArenaUtil;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import java.util.ArrayList;
import come.tool.teamArena.TeamArenaMatch;
import java.util.List;
import java.math.BigDecimal;

public class TeamBean
{
    private BigDecimal teamId;
    private List<TeamRole> teams;
    private transient List<TeamRole> applyTeams;
    private String eTeam;
    private String eTask;
    private String eMsg;
    private transient int teamState;
    private transient String teamInfo;
    private transient Object object;
    private transient long[] mapIds;
    private transient TeamArenaMatch teamArenaMatch;
    
    public TeamBean(BigDecimal teamId, TeamRole role) {
        this.teamId = teamId;
        role.setState(1);
        this.teams = new ArrayList<>();
        this.applyTeams = new ArrayList<>();
        this.teams.add(role);
        this.object = new Object();
        this.mapIds = new long[5];
        this.teamInfo = role.getName();
    }
    
    public boolean applyTeam(TeamRole role) {
        synchronized (this.object) {
            for (int i = 0, length = this.applyTeams.size(); i < length; ++i) {
                TeamRole applyRole = (TeamRole)this.applyTeams.get(i);
                if (role.getRoleId().compareTo(applyRole.getRoleId()) == 0) {
                    this.applyTeams.set(i, role);
                    return false;
                }
            }
            this.applyTeams.add(role);
            return true;
        }
    }
    
    public TeamRole getApply(BigDecimal roleID) {
        synchronized (this.object) {
            for (int i = 0, length = this.applyTeams.size(); i < length; ++i) {
                TeamRole applyRole = (TeamRole)this.applyTeams.get(i);
                if (applyRole.getRoleId().compareTo(roleID) == 0) {
                    return (TeamRole)this.applyTeams.remove(i);
                }
            }
            return null;
        }
    }
    
    public void removeApply() {
        synchronized (this.object) {
            this.applyTeams.clear();
        }
    }
    
    public void upEnlist(TeamBean bean) {
        this.eTeam = bean.eTeam;
        this.eTask = bean.eTask;
        this.eMsg = bean.eMsg;
    }
    
    public TeamRole getTeamRole(BigDecimal roleID) {
        synchronized (this.object) {
            for (int i = 0, length = this.teams.size(); i < length; ++i) {
                TeamRole role = (TeamRole)this.teams.get(i);
                if (role.getRoleId().compareTo(roleID) == 0) {
                    return role;
                }
            }
            return null;
        }
    }
    
    public void addTeamRole(TeamRole role, int type) {
        synchronized (this.object) {
            TeamUtil.addTeamRole(role.getRoleId(), this.teamId);
            this.upteamInfo(role, type, 1);
            TeamUtil.upEnlist(this);
            SendMessage.sendMessageByRoleName(role.getName(), Agreement.getAgreement().team1Agreement(GsonUtil.getGsonUtil().getgson().toJson(this)));
        }
    }
    
    public boolean removeTeamRole(BigDecimal roleID) {
        synchronized (this.object) {
            TeamRole role = null;
            int i = 1;
            int length = this.teams.size();
            while (i < length) {
                TeamRole teamRole = (TeamRole)this.teams.get(i);
                if (teamRole.getRoleId().compareTo(roleID) == 0) {
                    role = (TeamRole)this.teams.get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
            if (role != null) {
                this.upteamInfo(role, -3, 2);
                TeamUtil.removeTeamRole(roleID);
                TeamUtil.upEnlist(this);
            }
            return role != null;
        }
    }
    
    public String setCaptain(LoginResult loginResult) {
        synchronized (this.object) {
            TeamRole role = null;
            TeamRole captain = (TeamRole)this.teams.get(0);
            int index = -1;
            int i = 1;
            int length = this.teams.size();
            while (i < length) {
                TeamRole teamRole = (TeamRole)this.teams.get(i);
                if (teamRole.getRoleId().compareTo(loginResult.getRole_id()) == 0) {
                    index = i;
                    role = (TeamRole)this.teams.get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
            if (role == null) {
                return "该队员状态异常";
            }
            if (role.getState() != 0) {
                return "该队员处于离队状态";
            }
            role.setState(1);
            captain.setState(0);
            this.teams.set(0, role);
            this.teams.set(index, captain);
            this.upteamInfo(null, 0, 0);
            this.upTeamRoleState(role);
            TeamUtil.upEnlist(this);
            return "队长设立成功";
        }
    }
    
    public void dismissTeam() {
        synchronized (this.object) {
            this.upteamInfo(null, 0, 3);
            TeamUtil.removeTeam(this);
        }
    }
    
    public void stateLeave(TeamRole role, int type) {
        synchronized (this.object) {
            this.upteamInfo(role, type, 2);
            if (this.teamArenaMatch != null && this.teamArenaMatch.getState() <= 2) {
                if (this.teamArenaMatch.getType() == 2) {
                    LadderArenaUtil.quitTeamArena(this, role);
                }
                else {
                    TeamArenaUtil.quitTeamArena(this, role);
                }
            }
        }
    }
    
    public void stateCome(TeamRole role) {
        synchronized (this.object) {
            TeamRole captain = (TeamRole)this.teams.get(0);
            this.upteamInfo(role, (captain.getRoleId().compareTo(role.getRoleId()) == 0) ? 1 : 0, 0);
        }
    }
    
    public void upTeamRoleState(TeamRole role) {
        String msg = Agreement.getAgreement().team4Agreement(GsonUtil.getGsonUtil().getgson().toJson(role));
        for (int i = 0, length = this.teams.size(); i < length; ++i) {
            SendMessage.sendMessageByRoleName(((TeamRole)this.teams.get(i)).getName(), msg);
        }
    }
    
    public void upteamInfo(TeamRole role, int state, int type) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferState = null;
        int ostate = 0;
        if (role != null) {
            role.setState(state);
        }
        for (int i = 0, length = this.teams.size(); i < length; ++i) {
            TeamRole teamRole = (TeamRole)this.teams.get(i);
            if (type == 3) {
                if (bufferState == null) {
                    bufferState = new StringBuffer();
                }
                bufferState.append("#");
                bufferState.append(teamRole.getName());
                bufferState.append("&");
                bufferState.append(-3);
                teamRole.setState(-3);
            }
            else if (i == 0 || teamRole.getState() >= 0) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(teamRole.getName());
            }
            else {
                if (bufferState == null) {
                    bufferState = new StringBuffer();
                }
                bufferState.append("#");
                bufferState.append(teamRole.getName());
                bufferState.append("&");
                bufferState.append(teamRole.getState());
            }
        }
        if (type == 1) {
            if (role.getState() >= 0) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(role.getName());
            }
            else {
                if (bufferState == null) {
                    bufferState = new StringBuffer();
                }
                bufferState.append("#");
                bufferState.append(role.getName());
                bufferState.append("&");
                bufferState.append(role.getState());
            }
        }
        this.teamInfo = ((buffer.length() != 0) ? buffer.toString() : null);
        buffer.setLength(0);
        buffer.append(this.teamId);
        buffer.append("#");
        buffer.append(this.teamInfo);
        if (bufferState != null) {
            buffer.append(bufferState.toString());
        }
        String msg = Agreement.getAgreement().team3Agreement(buffer.toString());
        this.mapIds[0] = 0L;
        this.mapIds[1] = 0L;
        this.mapIds[2] = 0L;
        this.mapIds[3] = 0L;
        this.mapIds[4] = 0L;
        for (int j = 0, length2 = this.teams.size(); j < length2; ++j) {
            TeamRole teamRole2 = (TeamRole)this.teams.get(j);
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teamRole2.getName());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                login.setTeamInfo((teamRole2.getState() >= 0) ? this.teamInfo : null);
                if (teamRole2.getState() == -3) {
                    login.setTroop_id(null);
                }
                if ((type != 0 && type != 1) || teamRole2.getState() >= 0) {
                    if (type == 2) {
                        if (state == -3) {
                            if (role != teamRole2) {
                                if (ostate < 0) {
                                    continue;
                                }
                                else if (teamRole2.getState() < 0) {
                                    continue;
                                }
                            }
                        }
                        else if (ostate == 1) {
                            continue;
                        }
                    }
                    int k = 0;
                    while (k < this.mapIds.length) {
                        if (this.mapIds[k] == (long)login.getMapid()) {
                            break;
                        }
                        else if (this.mapIds[k] == 0L) {
                            this.mapIds[k] = (long)login.getMapid();
                            break;
                        }
                        else {
                            ++k;
                        }
                    }
                }
            }
        }
        if (type == 1) {
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role.getName());
            LoginResult login2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
            if (login2 != null) {
                login2.setTeamInfo((role.getState() >= 0) ? this.teamInfo : null);
                login2.setTroop_id(this.teamId);
                int l = 0;
                while (l < this.mapIds.length) {
                    if (this.mapIds[l] == (long)login2.getMapid()) {
                        break;
                    }
                    else if (this.mapIds[l] == 0L) {
                        this.mapIds[l] = (long)login2.getMapid();
                        break;
                    }
                    else {
                        ++l;
                    }
                }
            }
        }
        for (int j = 0; j < this.mapIds.length; ++j) {
            if (this.mapIds[j] != 0L) {
                SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapIds[j]), msg);
            }
        }
        if (role != null) {
            this.upTeamRoleState(role);
        }
        if (type == 2 && state == -3) {
            this.teams.remove(role);
        }
        else if (type == 1) {
            this.teams.add(role);
        }
    }
    
    public void sendTeam(String msg) {
        for (int i = 0, length = this.teams.size(); i < length; ++i) {
            SendMessage.sendMessageByRoleName(((TeamRole)this.teams.get(i)).getName(), msg);
        }
    }
    
    public void addTaskAndSendTeam(int taskSetID, String msg) {
        for (int i = 0, length = this.teams.size(); i < length; ++i) {
            TeamRole teamRole = (TeamRole)this.teams.get(i);
            RoleData roleData = RolePool.getRoleData(teamRole.getRoleId());
            if (roleData != null) {
                TaskRecord taskRecord = roleData.getTaskRecord(taskSetID);
                if (taskRecord == null) {
                    taskRecord = new TaskRecord(5);
                    roleData.addTaskRecord(taskRecord);
                }
                taskRecord.addCSum(1);
            }
            SendMessage.sendMessageByRoleName(teamRole.getName(), msg);
        }
    }
    
    public int getTeamState() {
        return this.teamState;
    }
    
    public void setTeamState(int teamState) {
        this.teamState = teamState;
    }
    
    public boolean isCaptian(BigDecimal roleID) {
        synchronized (this.object) {
            return ((TeamRole)this.teams.get(0)).getRoleId().compareTo(roleID) == 0;
        }
    }
    
    public BigDecimal getTeamId() {
        return this.teamId;
    }
    
    public String getTeamInfo() {
        return this.teamInfo;
    }
    
    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }
    
    public List<TeamRole> getTeams() {
        return this.teams;
    }
    
    public List<TeamRole> getApplyTeams() {
        return this.applyTeams;
    }
    
    public int getTeamSize() {
        return this.teams.size();
    }
    
    public String getTeamName() {
        return ((TeamRole)this.teams.get(0)).getName();
    }
    
    public TeamArenaMatch getTeamArenaMatch() {
        return this.teamArenaMatch;
    }
    
    public TeamArenaMatch getTeamArenaMatch(Integer type) {
        this.teamArenaMatch.setType((int)type);
        return this.teamArenaMatch;
    }
    
    public void setTeamArenaMatch(TeamArenaMatch teamArenaMatch) {
        this.teamArenaMatch = teamArenaMatch;
    }
}
