package come.tool.Scene.SLDH;

public class SLDHTeam
{
    private SLDHRole[] roles;
    private String[] teams;
    private int value;
    private String teamMsg;
    
    public SLDHTeam(SLDHRole[] roles) {
        this.roles = roles;
        this.value = 0;
        for (int i = 0; i < roles.length; ++i) {
            roles[i].setSldhTeam(this);
        }
        StringBuffer buffer = new StringBuffer();
        this.teams = new String[roles.length];
        for (int j = 0; j < this.teams.length; ++j) {
            this.teams[j] = roles[j].getRoleShow().getRolename();
            if (buffer.length() != 0) {
                buffer.append(" ");
            }
            buffer.append(this.teams[j]);
        }
        this.teamMsg = buffer.toString();
    }
    
    public SLDHRole[] getRoles() {
        return this.roles;
    }
    
    public void setRoles(SLDHRole[] roles) {
        this.roles = roles;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public String[] getTeams() {
        return this.teams;
    }
    
    public void setTeams(String[] teams) {
        this.teams = teams;
    }
    
    public String getTeamMsg() {
        return this.teamMsg;
    }
    
    public void setTeamMsg(String teamMsg) {
        this.teamMsg = teamMsg;
    }
}
