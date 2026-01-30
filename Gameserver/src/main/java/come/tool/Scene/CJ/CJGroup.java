package come.tool.Scene.CJ;

/**分组情况*/
public class CJGroup {

	private CJTeam team1;
	private CJTeam team2;
	//战况 0 无   1 战斗中      2 一队胜利  3二队胜利
	private int state;
	private int fightId;//战斗id
	public CJGroup(CJTeam team1, CJTeam team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}
	public CJTeam getTeam(boolean is) {
		if (is) {
			return this.state==2?this.team1:this.team2;
		}else {
			return this.state==2?this.team2:this.team1;
		}
	}
	public CJTeam getTeam1() {
		return this.team1;
	}
	public void setTeam1(CJTeam team1) {
		this.team1 = team1;
	}
	public CJTeam getTeam2() {
		return this.team2;
	}
	public void setTeam2(CJTeam team2) {
		this.team2 = team2;
	}
	public int getState() {
		return this.state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFightId() {
		return this.fightId;
	}
	public void setFightId(int fightId) {
		this.fightId = fightId;
	}

}
