package come.tool.teamArena;

import come.tool.newTeam.TeamBean;

public class LaddArenaGroup extends TeamArenaGroup
{
    public LaddArenaGroup(TeamBean team1, TeamBean team2, long time) {
        super(team1, team2, time, Integer.valueOf(2));
    }
}
