package come.tool.Scene.CJ;


/**参赛队伍*/
public class CJTeam {

	private CJRole[] roles;//队伍集合
	private String[] teams;
	private int value;//队伍成绩 0:未参与  1:参与   2:8强   3:季军   4:亚军   5:冠军
	private String teamMsg;//队伍描述
	private int num = 0;//失败次数
	private boolean isPK;
	public CJTeam(CJRole[] roles) {
		super();
		this.roles = roles;
		this.value = 0;
		this.num=0;
		for (int i = 0; i < roles.length; i++) {
			roles[i].setCJTeam(this);
		}
		StringBuffer buffer=new StringBuffer();
		this.teams=new String[roles.length];
		for (int i = 0; i < this.teams.length; i++) {
			this.teams[i]=roles[i].getRoleShow().getRolename();
			if (buffer.length()!=0) {buffer.append(" ");}
			buffer.append(this.teams[i]);
		}
		this.teamMsg=buffer.toString();
	}
	public CJRole[] getRoles() {
		return this.roles;
	}
	public void setRoles(CJRole[] roles) {
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isPK() {
		return isPK;
	}
	public void setPK(boolean PK) {
		isPK = PK;
	}
}
