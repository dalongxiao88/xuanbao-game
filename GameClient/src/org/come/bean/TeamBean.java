package org.come.bean;

import org.come.entity.TeamRole;
import java.util.List;
import java.math.BigDecimal;

public class TeamBean
{
    private BigDecimal teamId;
    public List<TeamRole> teams;
//    private List<TeamRole> teams;
    private String eTeam;
    private String eTask;
    private String eMsg;
    
    public boolean upTeamRole(TeamRole role, int type) {
        if (type == 0) {
            for (int i1 = 0, length = this.teams.size(); i1 < length; ++i1) {
                TeamRole teamRole = (TeamRole)this.teams.get(i1);
                if (teamRole.getRoleId().compareTo(role.getRoleId()) == 0) {
                    i1 = teamRole.getState();
                    teamRole.upTeamRole(role);
                    return i1 != teamRole.getState();
                }
            }
            this.teams.add(role);
            return true;
        }
        else if (type == 1) {
            for (int i1 = 0, length = this.teams.size(); i1 < length; ++i1) {
                TeamRole teamRole = (TeamRole)this.teams.get(i1);
                if (teamRole.getRoleId().compareTo(role.getRoleId()) == 0) {
                    this.teams.remove(i1);
                    return true;
                }
            }
            return false;
        }
        else {
            if (type == 2) {
                TeamRole captain = (TeamRole)this.teams.get(0);
                TeamRole nCaptain = null;
                int index = -1;
                int i1 = 1;
                int length2 = this.teams.size();
                while (i1 < length2) {
                    TeamRole teamRole2 = (TeamRole)this.teams.get(i1);
                    if (teamRole2.getRoleId().compareTo(role.getRoleId()) == 0) {
                        index = i1;
                        nCaptain = teamRole2;
                        break;
                    }
                    else {
                        ++i1;
                    }
                }
                if (nCaptain != null) {
                    nCaptain.upTeamRole(role);
                    captain.setState(0);
                    this.teams.set(0, nCaptain);
                    this.teams.set(index, captain);
                    return true;
                }
            }
            return false;
        }
    }
    
    public BigDecimal getTeamId() {
        return this.teamId;
    }
    
    public void setTeamId(BigDecimal teamId) {
        this.teamId = teamId;
    }
    
    public List<TeamRole> getTeams() {
        return this.teams;
    }
    
    public void setTeams(List<TeamRole> teams) {
        this.teams = teams;
    }
    
    public String geteTeam() {
        return this.eTeam;
    }
    
    public void seteTeam(String eTeam) {
        this.eTeam = eTeam;
    }
    
    public String geteTask() {
        return this.eTask;
    }
    
    public void seteTask(String eTask) {
        this.eTask = eTask;
    }
    
    public String geteMsg() {
        return this.eMsg;
    }
    
    public void seteMsg(String eMsg) {
        this.eMsg = eMsg;
    }
}
