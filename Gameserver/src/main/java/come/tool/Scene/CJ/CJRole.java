package come.tool.Scene.CJ;

import come.tool.Role.RoleShow;

import java.math.BigDecimal;

/**玩家信息*/
public class CJRole {

	private BigDecimal roleID;
	private RoleShow roleShow;
	private CJTeam cjTeam;//所在队伍
	private int I;//奖励领取0未领取

	public CJRole(BigDecimal roleID, RoleShow roleShow) {
		super();
		this.roleID = roleID;
		this.roleShow = roleShow;
		this.I = 0;
	}
	public BigDecimal getRoleID() {
		return this.roleID;
	}
	public void setRoleID(BigDecimal roleID) {
		this.roleID = roleID;
	}
	public RoleShow getRoleShow() {
		return this.roleShow;
	}
	public void setRoleShow(RoleShow roleShow) {
		this.roleShow = roleShow;
	}
	public CJTeam getCJTeam() {
		return this.cjTeam;
	}
	public void setCJTeam(CJTeam cjTeam) {
		this.cjTeam = cjTeam;
	}
	public int getI() {
		return this.I;
	}
	public void setI(int i) {
		this.I = i;
	}
	
}
