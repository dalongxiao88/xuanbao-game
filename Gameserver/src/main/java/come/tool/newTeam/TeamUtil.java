package come.tool.newTeam;

import java.util.ArrayList;
import org.come.redis.RedisCacheUtil;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.TBean;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;

public class TeamUtil
{
    private static BigDecimal teamID;
    private static ConcurrentHashMap<BigDecimal, TeamBean> teamMap;
    private static ConcurrentHashMap<BigDecimal, BigDecimal> teamRoleMap;
    private static List<TeamBean> enlist;
    private static String enlistText;
    private static Object object;
    private static TBean<List<TeamBean>> t;
    
    public static void addTeamRole(BigDecimal roleId, BigDecimal teamId) {
        TeamUtil.teamRoleMap.put(roleId, teamId);
    }
    
    public static void removeTeamRole(BigDecimal roleId) {
        TeamUtil.teamRoleMap.remove(roleId);
    }
    
    public static TeamBean getTeamRole(BigDecimal roleId) {
        BigDecimal teamID = (BigDecimal)TeamUtil.teamRoleMap.get(roleId);
        if (teamID != null) {
            return getTeam(teamID);
        }
        return null;
    }
    
    public static TeamBean getTeam(BigDecimal teamID) {
        if (teamID == null) {
            return null;
        }
        return (TeamBean)TeamUtil.teamMap.get(teamID);
    }
    
    public static TeamBean addTeam(TeamRole role) {
        BigDecimal teamId = getTeamID();
        TeamBean teamBean = new TeamBean(teamId, role);
        TeamUtil.teamMap.put(teamBean.getTeamId(), teamBean);
        TeamUtil.teamRoleMap.put(role.getRoleId(), teamId);
        return teamBean;
    }
    
    public static void removeTeam(TeamBean teamBean) {
        TeamUtil.teamMap.remove(teamBean.getTeamId());
        for (int i = 0, length = teamBean.getTeams().size(); i < length; ++i) {
            TeamUtil.teamRoleMap.remove(((TeamRole)teamBean.getTeams().get(i)).getRoleId());
        }
        removeEnlist(teamBean);
    }
    
    public static void upEnlist(TeamBean teamBean) {
        synchronized (TeamUtil.object) {
            if (teamBean.getTeamState() != 1) {
                return;
            }
            if (teamBean.getTeams().size() >= 5) {
                removeEnlist(teamBean);
            }
            else {
                TeamUtil.enlistText = null;
            }
        }
    }
    
    public static void addEnlist(TeamBean teamBean) {
        synchronized (TeamUtil.object) {
            if (!TeamUtil.enlist.contains(teamBean)) {
                TeamUtil.enlist.add(teamBean);
                teamBean.setTeamState(1);
            }
            TeamUtil.enlistText = null;
        }
    }
    
    public static void removeEnlist(TeamBean teamBean) {
        synchronized (TeamUtil.object) {
            if (TeamUtil.enlist.remove(teamBean)) {
                teamBean.setTeamState(0);
                TeamUtil.enlistText = null;
            }
        }
    }
    
    public static String getEnlist() {
        synchronized (TeamUtil.object) {
            if (TeamUtil.enlistText != null) {
                return TeamUtil.enlistText;
            }
            return TeamUtil.enlistText = Agreement.getAgreement().enlistAgreement(GsonUtil.getGsonUtil().getgson().toJson(TeamUtil.t));
        }
    }
    
    public static synchronized BigDecimal getTeamID() {
        return TeamUtil.teamID = TeamUtil.teamID.add(RedisCacheUtil.ADD);
    }
    
    public static String getTeamInfo(BigDecimal teamId, String OTeamInfo, String NTeamInfo) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(teamId);
        buffer.append("#");
        buffer.append((OTeamInfo != null && !OTeamInfo.equals("")) ? OTeamInfo : "null");
        buffer.append("#");
        buffer.append((NTeamInfo != null && !NTeamInfo.equals("")) ? NTeamInfo : "null");
        return Agreement.getAgreement().team3Agreement(buffer.toString());
    }
    
    static {
        TeamUtil.teamID = new BigDecimal(0);
        TeamUtil.teamMap = new ConcurrentHashMap<>();
        TeamUtil.teamRoleMap = new ConcurrentHashMap<>();
        TeamUtil.enlist = new ArrayList<>();
        TeamUtil.object = new Object();
        TeamUtil.enlistText = null;
        TeamUtil.t = new TBean(TeamUtil.enlist);
    }
}
