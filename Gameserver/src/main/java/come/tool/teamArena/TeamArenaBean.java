package come.tool.teamArena;

import come.tool.newTeam.TeamRole;
import java.util.List;

public class TeamArenaBean
{
    private List<TeamRole> team1;
    private List<TeamRole> team2;
    
    public List<TeamRole> getTeam1() {
        return this.team1;
    }
    
    public void setTeam1(List<TeamRole> team1) {
        this.team1 = team1;
    }
    
    public List<TeamRole> getTeam2() {
        return this.team2;
    }
    
    public void setTeam2(List<TeamRole> team2) {
        this.team2 = team2;
    }
}
